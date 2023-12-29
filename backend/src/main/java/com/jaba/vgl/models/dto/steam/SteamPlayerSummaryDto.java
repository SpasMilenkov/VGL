package com.jaba.vgl.models.dto.steam;

import lombok.Data;

@Data
public class SteamPlayerSummaryDto {
    private String steamid;
    private String personaname;
    private String profileurl;
    private String avatar;
    private String avatarmedium;
    private String avatarfull;
    private int personastate;
    private int communityvisibilitystate;
    private int profilestate;
    private long lastlogoff;
    private int commentpermission;
    private String realname; // Optional: The player's "Real Name", if set
    private String primaryclanid; // Optional: The player's primary group
    private long timecreated; // Optional: Time the player's account was created
    private int gameid; // Optional: The ID of the game the user is currently playing
    private String gameserverip; // Optional: IP and port of the game server the user is playing on
    private String gameextrainfo; // Optional: Name of the game the user is playing
    private int cityid; // Optional: Internal code indicating the user's city of residence
    private String loccountrycode; // Optional: User's country of residence (2-character ISO country code)
    private String locstatecode; // Optional: User's state of residence
    private int loccityid; // Optional: Internal code indicating the user's city of residence
}
