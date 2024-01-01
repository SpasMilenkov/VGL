package com.jaba.vgl.services.clients;

import com.jaba.vgl.models.dto.PlayerSummaryDto;
import com.jaba.vgl.models.dto.steam.SteamNewsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "steamClient", url = "${steam.api.baseurl}")
public interface SteamClient {
    //Example JSON response
    // {
    //    "response": {
    //        "players": [
    //            {
    //                "steamid": "76561197960435530",
    //                "communityvisibilitystate": 3,
    //                "profilestate": 1,
    //                "personaname": "Robin",
    //                "profileurl": "https://steamcommunity.com/id/robinwalker/",
    //                "avatar": "https://avatars.steamstatic.com/81b5478529dce13bf24b55ac42c1af7058aaf7a9.jpg",
    //                "avatarmedium": "https://avatars.steamstatic.com/81b5478529dce13bf24b55ac42c1af7058aaf7a9_medium.jpg",
    //                "avatarfull": "https://avatars.steamstatic.com/81b5478529dce13bf24b55ac42c1af7058aaf7a9_full.jpg",
    //                "avatarhash": "81b5478529dce13bf24b55ac42c1af7058aaf7a9",
    //                "personastate": 0,
    //                "realname": "Robin Walker",
    //                "primaryclanid": "103582791429521412",
    //                "timecreated": 1063407589,
    //                "personastateflags": 0,
    //                "loccountrycode": "US",
    //                "locstatecode": "WA",
    //                "loccityid": 3961
    //            }
    //        ]
    //    }
    //}
    @GetMapping("ISteamUser/GetPlayerSummaries/v0002/")
    String getPlayerSummary(@RequestParam("key") String apiKey, @RequestParam("steamids") String steamId);
    //Example JSON response
    //{
    //    "appnews": {
    //        "appid": 440,
    //        "newsitems": [
    //            {
    //                "gid": "5410576585131644867",
    //                "title": "Team Fortress 2 Update Released",
    //                "url": "https://steamstore-a.akamaihd.net/news/externalpost/steam_community_announcements/5410576585131644867",
    //                "is_external_url": true,
    //                "author": "erics",
    //                "contents": "An update to Team Fortress 2 has been released. The update will be applied automatically when you restart Team Fortress 2. The major changes include: Updated cp_carrier Fixed a clipping issue near the bell tower Fixed the carrier being able to take teleporters Fixed players dropping flags during wai...",
    //                "feedlabel": "Community Announcements",
    //                "date": 1703204195,
    //                "feedname": "steam_community_announcements",
    //                "feed_type": 1,
    //                "appid": 440,
    //                "tags": [
    //                    "patchnotes",
    //                    "mod_reviewed",
    //                    "ModAct_1415588329_1703205030_0",
    //                    "mod_require_rereview"
    //                ]
    //            },
    //        ],
    //        "count": 3495
    //    }
    //}
    @GetMapping("ISteamNews/GetNewsForApp/v0002/")
    String getGameNews(@RequestParam("appId") String gameId, @RequestParam("count") int newsCount, @RequestParam("maxlength") int newsLength, @RequestParam("format") String format);
    @GetMapping("IPlayerService/GetOwnedGames/v0001/")
    String getOwnedGames(@RequestParam("key") String apiKey, @RequestParam("steamid") String steamId, @RequestParam("include_appinfo") boolean includeAppInfo, @RequestParam("format") String responseFormat);
}
