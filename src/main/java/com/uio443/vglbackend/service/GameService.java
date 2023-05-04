package com.uio443.vglbackend.service;

import com.uio443.vglbackend.model.Game;
import com.uio443.vglbackend.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;


    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getGamebyidgbId(Long igdbId) {
        return gameRepository.findGameByigdbId(igdbId).orElseThrow(() -> new RuntimeException("Bad ID"));
    }

    public Game getGamebytitle(String title) {
        return gameRepository.findGameBytitle(title).orElseThrow(() -> new RuntimeException("No such Game exists"));
    }

    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Game game) {
        Optional<Game> gameIdCheck = gameRepository.findGameByigdbId(game.getIgdbId());
        if (gameIdCheck.isEmpty()) throw new RuntimeException(String.format("Game does not exist with %d", game.getIgdbId()));

        Game newGame = gameIdCheck.get();

        if (game.getTitle() != null && !newGame.getTitle().equals(game.getTitle())) {
            newGame.setTitle(game.getTitle());
        }

        if (game.getCoverImgUrl() != null && !newGame.getCoverImgUrl().equals(game.getCoverImgUrl())) {
            newGame.setCoverImgUrl(game.getCoverImgUrl());
        }

        if (game.getGenres() != null && !newGame.getGenres().equals(game.getGenres())) {
            newGame.setGenres(game.getGenres());
        }

        return gameRepository.save(newGame);
    }

    public void deleteGame(Long igdbId) {
        if (gameRepository.findGameByigdbId(igdbId).isEmpty()) {
            throw new RuntimeException(String.format("Game does not exist with ID %d", igdbId));
        }

        gameRepository.deleteGameByigdbId(igdbId);
    }


    public List<Game> getAllGames() {
        return gameRepository.getAllGames();
    }
}
