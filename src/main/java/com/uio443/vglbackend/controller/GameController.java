package com.uio443.vglbackend.controller;


import com.uio443.vglbackend.model.Game;
import com.uio443.vglbackend.repository.GameRepository;
import com.uio443.vglbackend.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/{igdbID}")
    public ResponseEntity<Game> getGamebyigdbID(@PathVariable("igdbID") Long igdbID) {
        Game game = gameService.getGamebyidgbID(igdbID);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        Game newGame = gameService.addGame(game);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        Game newGame = gameService.updateGame(game);
        return new ResponseEntity<>(newGame, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{igdbID}")
    public ResponseEntity deleteGame(@PathVariable("igdbID") Long igdbID) {
        gameService.deleteGame(igdbID);
        return new ResponseEntity(HttpStatus.OK);
    }

}
