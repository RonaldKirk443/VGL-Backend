package com.uio443.vglbackend.controller;

import com.uio443.vglbackend.model.UserGame;
import com.uio443.vglbackend.service.UserGameService;
import com.uio443.vglbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserGameController {
    private final UserGameService userGameService;

    public UserGameController(UserGameService userGameService) {
        this.userGameService = userGameService;
    }

    @PostMapping("/{userId}/add-game")
    public ResponseEntity<UserGame> addGame(@PathVariable(value = "userId") Long userId, @RequestBody UserGame userGame) {
        UserGame newUserGame = userGameService.addGame(userId, userGame);
        return new ResponseEntity<>(newUserGame, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/update-game")
    public ResponseEntity<UserGame> updateGame(@PathVariable(value = "userId") Long userId, @RequestBody UserGame userGame) {
        UserGame newUserGame = userGameService.updateGame(userId, userGame);
        return new ResponseEntity<>(newUserGame, HttpStatus.OK);
    }

    @GetMapping("/{userId}/games")
    public ResponseEntity<List<UserGame>> getGamesByUserId(@PathVariable(value = "userId") Long userId) {
        List<UserGame> userGameList = userGameService.getGameList(userId);
        return new ResponseEntity<>(userGameList, HttpStatus.OK);
    }

    @GetMapping("/{userId}/games/{igdbId}")
    public ResponseEntity<UserGame> getGameByUserIdIgdbId(@PathVariable(value = "userId") Long userId, @PathVariable(value = "igdbId") Long igdbId) {
        UserGame userGame = userGameService.getGame(userId, igdbId);
        return new ResponseEntity<>(userGame, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/games/delete/{igdbId}")
    public ResponseEntity<String> deleteGame(@PathVariable(value = "userId") Long userId, @PathVariable(value = "igdbId") Long igdbId){
        userGameService.deleteGame(userId, igdbId);
        return new ResponseEntity<>(String.format("Game with id %d was removed from user %d", igdbId, userId), HttpStatus.OK);
    }

}
