package com.jaba.vgl.controllers;


import com.jaba.vgl.models.dto.UserDto;
import com.jaba.vgl.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Bye world");
    }

    @PutMapping("update-steamId")
    public ResponseEntity<UserDto> updateSteamId(@RequestParam String email, @RequestParam String steamId){
        UserDto userDto = userService.updateSteamId(email, steamId);

        return ResponseEntity.ok(userDto);
    }
}
