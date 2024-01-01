package com.jaba.vgl.services.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaba.vgl.models.dto.GameDescriptionDto;
import com.jaba.vgl.models.dto.NewsItemDto;
import com.jaba.vgl.models.dto.OwnedGameDto;
import com.jaba.vgl.models.dto.PlayerSummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SteamService {
    @Value("${steam.api.baseurl}")
    private String baseUrl;

    @Value("${steam.api.key}")
    private String apiKey;
    private final SteamClient steamClient;
    private final SteamStoreClient steamStoreClient;
    @Autowired
    public SteamService(SteamClient steamClient, SteamStoreClient steamStoreClient) {
        this.steamClient = steamClient;
        this.steamStoreClient = steamStoreClient;
    }

    public PlayerSummaryDto getPlayerSummary(String steamId) {
        try {
            String jsonResponse = steamClient.getPlayerSummary(apiKey, steamId); // Replace with your actual method
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);

            JsonNode playerNode = rootNode.path("response").path("players").get(0);
            if (playerNode != null) {
                PlayerSummaryDto playerSummaryDto = new PlayerSummaryDto();
                playerSummaryDto.setSteamId(playerNode.path("steamid").asText());
                playerSummaryDto.setUsername(playerNode.path("personaname").asText());
                playerSummaryDto.setAvatarUrl(playerNode.path("avatar").asText());
                playerSummaryDto.setProfileurl(playerNode.path("profileurl").asText());

                return playerSummaryDto;
            }
        } catch (IOException e) {
            // Handle exceptions
            return null;
        }

        return null;
    }
    public List<NewsItemDto> getGameNews(String gameId) {
        List<NewsItemDto> newsItems = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonResponse = steamClient.getGameNews(gameId, 10, 300, "json");
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode newsItemsNode = rootNode.path("appnews").path("newsitems");

            if (newsItemsNode.isArray()) {
                for (JsonNode itemNode : newsItemsNode) {
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

                    newsItems.add(newsItem);
                }
            }
        } catch (IOException e) {
            // Handle exceptions
        }

        return newsItems;
    }

    public List<OwnedGameDto> getOwnedGames(String steamId) {
        List<OwnedGameDto> ownedGames = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonResponse = steamClient.getOwnedGames(apiKey, steamId, true, "json");
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode gamesNode = rootNode.path("response").path("games");

            if (gamesNode.isArray()) {
                for (JsonNode gameNode : gamesNode) {
                    OwnedGameDto gameDto = new OwnedGameDto();
                    gameDto.setGameId(gameNode.path("appid").asInt());
                    gameDto.setName(gameNode.path("name").asText());
                    gameDto.setPlaytimeForever(gameNode.path("playtime_forever").asInt());
                    //Example url:
                    //https://cdn.akamai.steamstatic.com/steam/apps/50300/page.bg.jpg
                    gameDto.setBannerUrl("https://cdn.akamai.steamstatic.com/steam/apps/" + gameDto.getGameId() + "/page.bg.jpg");
                    //Example url:
                    //https://cdn.akamai.steamstatic.com/steam/apps/50300/page.bg.jpg
                    //this one is a fallback in case the above one returns 404 due to inconsistencies on steam's side
                    gameDto.setHeaderUrl("https://cdn.akamai.steamstatic.com/steam/apps/" + gameDto.getGameId() + "/header.jpg");
                    ownedGames.add(gameDto);
                }
            }
        } catch (IOException e) {
            // Handle exceptions
        }
        return ownedGames;
    }

    public GameDescriptionDto getGameDescription(String gameId) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM, yyyy");

        try {
            String jsonResponse = steamStoreClient.getGameDescription(gameId);
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode dataNode = rootNode.fields().next().getValue().path("data");

            GameDescriptionDto gameDto = new GameDescriptionDto();
            gameDto.setGameId(dataNode.path("steam_appid").asInt());
            gameDto.setName(dataNode.path("name").asText());
            gameDto.setHeaderUrl(dataNode.path("header_image").asText());
            gameDto.setBannerUrl(dataNode.path("background_raw").asText());
            gameDto.setShortDescription(dataNode.path("short_description").asText());

            // Extract genres
            List<String> genres = new ArrayList<>();
            if (dataNode.has("genres")) {
                for (JsonNode genreNode : dataNode.path("genres")) {
                    genres.add(genreNode.path("description").asText());
                }
            }
            gameDto.setGenres(genres);

            // Parse release date
            String releaseDateString = dataNode.path("release_date").path("date").asText();
            try {
                Date releaseDate = dateFormat.parse(releaseDateString);
                gameDto.setReleaseDate(releaseDate);
            } catch (ParseException e) {
                // Handle parse exception someday
            }

            return gameDto;
        } catch (IOException e) {
            // Handle exceptions
        }

        return null;
    }

}
