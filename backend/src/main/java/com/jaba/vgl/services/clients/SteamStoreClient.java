package com.jaba.vgl.services.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "steamStoreClient", url = "${steam.store.api.baseurl}")
public interface SteamStoreClient {

    //Gets the game information for a specific game from the catalogue
    //Example JSON
    //{
    //    "230410": {
    //        "success": true,
    //        "data": {
    //            "type": "game",
    //            "name": "Warframe",
    //            "steam_appid": 230410,
    //            "required_age": "18",
    //            "is_free": true,
    //            "controller_support": "full",
    //            "dlc": [
    //                2716340,
    //                2716320,
    //                2716330,
    //                2636170,
    //                2636140,
    //                2636150,
    //                2636160,
    //                2648430,
    //                2633910,
    //                1968100,
    //                1968101
    //            ],
    //            "detailed_description": "<h1>New DLC Available</h1>"
    //            "about_the_game": "Confront warring factions throughout a sprawling interplanetary system."
    //            "short_description": "Awaken as an unstoppable warrior and battle alongside your friends in this story-driven free-to-play online action game",
    //            "supported_languages": "English",
    //            "header_image": "https://cdn.akamai.steamstatic.com/steam/apps/230410/header.jpg?t=1703111209",
    //            "capsule_image": "https://cdn.akamai.steamstatic.com/steam/apps/230410/capsule_231x87.jpg?t=1703111209",
    //            "capsule_imagev5": "https://cdn.akamai.steamstatic.com/steam/apps/230410/capsule_184x69.jpg?t=1703111209",
    //            "website": "http://www.warframe.com",
    //            "pc_requirements": {
    //                "minimum": "<strong>Minimum:</strong><br><ul class=\"bb_ul\"><li><strong>OS *:</strong>Windows 7 64-Bit"
    //            },
    //            "mac_requirements": [],
    //            "linux_requirements": [],
    //            "legal_notice": "Warframe and the Warframe logo are registered trademarks of Digital Extremes Ltd.",
    //            "ext_user_account_notice": "Warframe Account ",
    //            "developers": [
    //                "Digital Extremes"
    //            ],
    //            "publishers": [
    //                "Digital Extremes"
    //            ],
    //            "packages": [
    //                199291
    //            ],
    //            "package_groups": [
    //                {
    //                    "name": "default",
    //                    "title": "Buy Warframe",
    //                    "description": "",
    //                    "selection_text": "Select a purchase option",
    //                    "save_text": "",
    //                    "display_type": 0,
    //                    "is_recurring_subscription": "false",
    //                    "subs": [
    //                        {
    //                            "packageid": 199291,
    //                            "percent_savings_text": " ",
    //                            "percent_savings": 0,
    //                            "option_text": "Warframe - Free",
    //                            "option_description": "",
    //                            "can_get_free_license": "0",
    //                            "is_free_license": true,
    //                            "price_in_cents_with_discount": 0
    //                        }
    //                    ]
    //                }
    //            ],
    //            "platforms": {
    //                "windows": true,
    //                "mac": false,
    //                "linux": false
    //            },
    //            "metacritic": {
    //                "score": 69,
    //                "url": "https://www.metacritic.com/game/pc/warframe?ftag=MCD-06-10aaa1f"
    //            },
    //            "categories": [
    //                {
    //                    "id": 2,
    //                    "description": "Single-player"
    //                },
    //            ],
    //            "genres": [
    //                {
    //                    "id": "1",
    //                    "description": "Action"
    //                },
    //                {
    //                    "id": "3",
    //                    "description": "RPG"
    //                },
    //                {
    //                    "id": "37",
    //                    "description": "Free to Play"
    //                }
    //            ],
    //            "screenshots": [
    //                {
    //                    "id": 0,
    //                    "path_thumbnail": "https://cdn.akamai.steamstatic.com/steam/apps/230410/ss_2e4077f215eccde84171a4b8e0f2bc8a3264c776.600x338.jpg?t=1703111209",
    //                    "path_full": "https://cdn.akamai.steamstatic.com/steam/apps/230410/ss_2e4077f215eccde84171a4b8e0f2bc8a3264c776.1920x1080.jpg?t=1703111209"
    //                },
    //            ],
    //            "movies": [
    //                {
    //                    "id": 256988593,
    //                    "name": "Whispers in the Walls",
    //                    "thumbnail": "https://cdn.akamai.steamstatic.com/steam/apps/256988593/movie.293x165.jpg?t=1702491677",
    //                    "webm": {
    //                        "480": "http://cdn.akamai.steamstatic.com/steam/apps/256988593/movie480_vp9.webm?t=1702491677",
    //                        "max": "http://cdn.akamai.steamstatic.com/steam/apps/256988593/movie_max_vp9.webm?t=1702491677"
    //                    },
    //                    "mp4": {
    //                        "480": "http://cdn.akamai.steamstatic.com/steam/apps/256988593/movie480.mp4?t=1702491677",
    //                        "max": "http://cdn.akamai.steamstatic.com/steam/apps/256988593/movie_max.mp4?t=1702491677"
    //                    },
    //                    "highlight": true
    //                },
    //            ],
    //            "recommendations": {
    //                "total": 2895
    //            },
    //            "achievements": {
    //                "total": 193,
    //                "highlighted": [
    //                    {
    //                        "name": "We Shape Our Tools",
    //                        "path": "https://cdn.akamai.steamstatic.com/steamcommunity/public/images/apps/230410/696dc084e8cc668428ea5a9a022f5c41127eed7c.jpg"
    //                    },
    //                ]
    //            },
    //            "release_date": {
    //                "coming_soon": false,
    //                "date": "25 Mar, 2013"
    //            },
    //            "support_info": {
    //                "url": "http://support.warframe.com/",
    //                "email": ""
    //            },
    //            "background": "https://cdn.akamai.steamstatic.com/steam/apps/230410/page_bg_generated_v6b.jpg?t=1703111209",
    //            "background_raw": "https://cdn.akamai.steamstatic.com/steam/apps/230410/page.bg.jpg?t=1703111209",
    //            "content_descriptors": {
    //                "ids": [
    //                    2,
    //                    5
    //                ],
    //                "notes": null
    //            }
    //        }
    //    }
    //}
    @GetMapping("appdetails/")
    String getGameDescription(@RequestParam("appids") String gameId);
}
