package com.jaba.vgl.services.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        return playerSummaryDto;
    }

    // Fetches game news
    public List<NewsItemDto> getGameNews(String steamId) {

        Stream<String> newsDtos = getOwnedGames(steamId)
                .stream()
                .map(g -> steamClient.getGameNews(String.valueOf(g.getGameId()), 3, 300, "json"))
                .limit(15);

        List<JsonNode> newsItemsNodes = newsDtos
                .map(this::parseJson)
                .map(rootNode -> rootNode.path("appnews").path("newsitems"))
                .toList();

        return newsItemsNodes.stream()
                .flatMap(newsItemsNode ->
                        StreamSupport.stream(newsItemsNode.spliterator(), false)
                )
                .map(this::createNewsItemDto)
                .collect(Collectors.toList());
    }

    // Constructs a NewsItemDto from a JsonNode
    private NewsItemDto createNewsItemDto(JsonNode itemNode) {
        NewsItemDto newsItem = new NewsItemDto();
        newsItem.setAppid(itemNode.path("appid").asInt());
        newsItem.setTitle(itemNode.path("title").asText());
        newsItem.setUrl(itemNode.path("url").asText());
        newsItem.setAuthor(itemNode.path("author").asText());
        newsItem.setContents(itemNode.path("contents").asText());

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
            List<String> gameDescriptions = StreamSupport.stream(gamesNode.spliterator(), false)
                    .map(this::getGameId)
                    .map(i -> steamStoreClient.getGameDescription(String.valueOf(i)))
                    .toList();
            List<OwnedGameDto> dtos = new ArrayList<>();
            for (String gameDescription : gameDescriptions) {
                JsonNode n = objectMapper.readTree(gameDescription);
                JsonNode data = n.fields().next().getValue().path("data");
                OwnedGameDto ownedGameDto = createOwnedGameDto(data);
                dtos.add(ownedGameDto);
            }
            return dtos;
//                    .map(g -> getGameDescription(String.valueOf(g)));
//                    .map(this::createOwnedGameDto)

//            gameDescriptionJson.map(g -> )

//            return StreamSupport.stream(gamesNode.spliterator(), false)
//                    .map(this::createOwnedGameDto)
//                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new GamesNotFoundException("Error parsing API response", e);
        }
    }

    // Constructs an OwnedGameDto from a JsonNode
    private OwnedGameDto createOwnedGameDto(JsonNode gameNode) {
        OwnedGameDto gameDto = new OwnedGameDto();
        gameDto.setGameId(gameNode.path("steam_appid").asInt());
        gameDto.setName(gameNode.path("name").asText());
        gameDto.setStudio(gameNode.path("developers").get(0).asText());
        gameDto.setTrailerUrl(extractMovieWebmMaxLink(gameNode.path("movies")));
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
        gameDto.setStudio(dataNode.path("developers").get(0).asText());
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
    private JsonNode parseJson(String jsonString) {
        try {
            return objectMapper.readTree(jsonString);
        } catch (IOException e) {
            return objectMapper.nullNode();
        }
    }

    private int getGameId(JsonNode gameNode){
        return gameNode.path("appid").asInt();
    }
    public String extractMovieWebmMaxLink(JsonNode moviesNode) {
        JsonNode firstMovieNode = moviesNode.get(0);
        if(firstMovieNode == null)
            return null;
        JsonNode webmNode = firstMovieNode.path("webm");
        JsonNode maxLinkNode = webmNode.path("max");

        if (maxLinkNode.isTextual()) {
            return maxLinkNode.asText();
        } else {
            throw new IllegalArgumentException("The 'max' link is not found or not a text node");
        }
    }
}


