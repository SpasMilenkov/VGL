package com.jaba.vgl.services.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaba.vgl.exceptions.*;
import com.jaba.vgl.models.dto.GameDescriptionDto;
import com.jaba.vgl.models.dto.NewsItemDto;
import com.jaba.vgl.models.dto.OwnedGameDto;
import com.jaba.vgl.models.dto.PlayerSummaryDto;
import com.jaba.vgl.repositories.UserRepository;
import com.jaba.vgl.models.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SteamService {
    @Value("${steam.api.baseurl}")
    private String baseUrl;

    @Value("${steam.api.key}")
    private String apiKey;

    private final SteamClient steamClient;
    private final SteamStoreClient steamStoreClient;
    private final UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Reuse ObjectMapper instance

    // Fetches player summary information
    public PlayerSummaryDto getPlayerSummary(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with that email does not exist"));

        String steamId = user.getSteamId();
        String jsonResponse = steamClient.getPlayerSummary(apiKey, steamId);

        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode playerNode = rootNode.path("response").path("players").get(0);

            if (playerNode == null) {
                throw new PlayerNotFoundException("Player not found");
            }

            return createPlayerSummaryDto(playerNode);
        } catch (IOException e) {
            throw new PlayerNotFoundException("Error parsing API response", e);
        }
    }

    // Constructs a PlayerSummaryDto from a JsonNode
    private PlayerSummaryDto createPlayerSummaryDto(JsonNode playerNode) {
        PlayerSummaryDto playerSummaryDto = new PlayerSummaryDto();
        playerSummaryDto.setSteamId(playerNode.path("steamid").asText());
        playerSummaryDto.setUsername(playerNode.path("personaname").asText());
        playerSummaryDto.setAvatarUrl(playerNode.path("avatar").asText());
        playerSummaryDto.setProfileurl(playerNode.path("profileurl").asText());
        return playerSummaryDto;
    }

    // Fetches game news
    public List<NewsItemDto> getGameNews(String gameId) {
        List<NewsItemDto> newsItems = new ArrayList<>();

        String jsonResponse = steamClient.getGameNews(gameId, 10, 300, "json");

        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode newsItemsNode = rootNode.path("appnews").path("newsitems");

            if (!newsItemsNode.isArray()) {
                throw new NewsItemsNotFoundException("News items not found");
            }

            return StreamSupport.stream(newsItemsNode.spliterator(), false)
                    .map(this::createNewsItemDto)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new NewsItemsNotFoundException("Error parsing API response", e);
        }
    }

    // Constructs a NewsItemDto from a JsonNode
    private NewsItemDto createNewsItemDto(JsonNode itemNode) {
        NewsItemDto newsItem = new NewsItemDto();
        newsItem.setId(itemNode.path("gid").asText());
        newsItem.setTitle(itemNode.path("title").asText());
        newsItem.setUrl(itemNode.path("url").asText());
        newsItem.setAuthor(itemNode.path("author").asText());
        newsItem.setContents(itemNode.path("contents").asText());
        newsItem.setFeedLabel(itemNode.path("feedlabel").asText());
        newsItem.setDate(itemNode.path("date").asLong());
        newsItem.setFeedName(itemNode.path("feedname").asText());
        newsItem.setAppid(itemNode.path("appid").asInt());
        return newsItem;
    }

    // Fetches owned games
    public List<OwnedGameDto> getOwnedGames(String steamId) {
        String jsonResponse = steamClient.getOwnedGames(apiKey, steamId, true, "json");

        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode gamesNode = rootNode.path("response").path("games");

            if (!gamesNode.isArray()) {
                throw new GamesNotFoundException("Owned games not found");
            }

            return StreamSupport.stream(gamesNode.spliterator(), false)
                    .map(this::createOwnedGameDto)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new GamesNotFoundException("Error parsing API response", e);
        }
    }

    // Constructs an OwnedGameDto from a JsonNode
    private OwnedGameDto createOwnedGameDto(JsonNode gameNode) {
        OwnedGameDto gameDto = new OwnedGameDto();
        gameDto.setGameId(gameNode.path("appid").asInt());
        gameDto.setName(gameNode.path("name").asText());
        gameDto.setPlaytimeForever(gameNode.path("playtime_forever").asInt());
        gameDto.setBannerUrl(constructImageUrl(gameDto.getGameId(), "page.bg.jpg"));
        gameDto.setHeaderUrl(constructImageUrl(gameDto.getGameId(), "header.jpg"));
        return gameDto;
    }

    // Helper method to construct image URLs
    private String constructImageUrl(int gameId, String imageType) {
        return String.format("https://cdn.akamai.steamstatic.com/steam/apps/%d/%s", gameId, imageType);
    }

    // Fetches game descriptions
    public GameDescriptionDto getGameDescription(String gameId) {
        String jsonResponse = steamStoreClient.getGameDescription(gameId);

        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = rootNode.fields().next().getValue().path("data");

            if (dataNode.isMissingNode()) {
                throw new GameDetailsNotFoundException("Details for this game are not available.");
            }

            return createGameDescriptionDto(dataNode);
        } catch (IOException e) {
            throw new GameDetailsNotFoundException("Error parsing API response");
        }
    }

    // Constructs a GameDescriptionDto from a JsonNode
    private GameDescriptionDto createGameDescriptionDto(JsonNode dataNode) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM, yyyy");

        GameDescriptionDto gameDto = new GameDescriptionDto();
        gameDto.setGameId(dataNode.path("steam_appid").asInt());
        gameDto.setName(dataNode.path("name").asText());
        gameDto.setHeaderUrl(dataNode.path("header_image").asText());
        gameDto.setBannerUrl(dataNode.path("background_raw").asText());
        gameDto.setShortDescription(dataNode.path("short_description").asText());

        gameDto.setGenres(extractGenres(dataNode));

        try {
            String releaseDateString = dataNode.path("release_date").path("date").asText();
            Date releaseDate = dateFormat.parse(releaseDateString);
            gameDto.setReleaseDate(releaseDate);
        } catch (ParseException e) {
            throw new GameDetailsNotFoundException("Error parsing API response");
        }

        return gameDto;
    }

    // Helper method to extract genres from a JsonNode
    private List<String> extractGenres(JsonNode dataNode) {
        List<String> genres = new ArrayList<>();
        if (dataNode.has("genres")) {
            for (JsonNode genreNode : dataNode.path("genres")) {
                genres.add(genreNode.path("description").asText());
            }
        }
        return genres;
    }
}


