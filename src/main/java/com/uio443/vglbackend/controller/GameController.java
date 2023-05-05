package com.uio443.vglbackend.controller;


import com.uio443.vglbackend.model.Game;
import com.uio443.vglbackend.model.User;
import com.uio443.vglbackend.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/id/{igdbId}")
    public ResponseEntity<Game> getGameByIgdbId(@PathVariable("igdbId") Long igdbId) {
        Game game = gameService.getGameByIgdbId(igdbId);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Game> getGameByIgdbId(@PathVariable("title") String title) {
        Game game = gameService.getGameByTitle(title);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping("/id/{igdbId}/users")
    public ResponseEntity<List<Long>> getUserIdsByIgdbId(@PathVariable("igdbId") Long igdbId) {
        List<Long> userIdList = gameService.getUsersByIgdbId(igdbId);
        return new ResponseEntity<>(userIdList, HttpStatus.OK);
    }

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> gameList = gameService.getAllGames();
        return new ResponseEntity<>(gameList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        Game newGame = gameService.addGame(game);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }

    @PostMapping("/add/{igdbId}")
    public ResponseEntity<Game> addGame(@PathVariable("igdbId") Long igdbId) {
        Game newGame = gameService.addGame(igdbId);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        Game newGame = gameService.updateGame(game);
        return new ResponseEntity<>(newGame, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{igdbId}")
    public ResponseEntity<String> deleteGame(@PathVariable("igdbId") Long igdbId) {
        gameService.deleteGame(igdbId);
        return new ResponseEntity<>(String.format("Game with id %d has been deleted", igdbId), HttpStatus.OK);
    }

}
