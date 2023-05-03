package com.uio443.vglbackend.service;

import com.uio443.vglbackend.model.Game;
import com.uio443.vglbackend.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getGamebyidgbID(Long igdbID) {
        return gameRepository.findGameByigdbID(igdbID).orElseThrow(() -> new RuntimeException("Bad ID"));
    }

    public Game getGamebytitle(String title) {
        return gameRepository.findGameBytitle(title).orElseThrow(() -> new RuntimeException("No such Game exists"));
    }

    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Game game) {
        Optional<Game> gameIdCheck = gameRepository.findGameByigdbID(game.getIgdbID());
        if (gameIdCheck.isEmpty()) throw new RuntimeException(String.format("Game does not exist with %d", game.getIgdbID()));

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

    public void deleteGame(Long igdbID) {
        if (gameRepository.findGameByigdbID(igdbID).isEmpty()) {
            throw new RuntimeException(String.format("Game does not exist with ID %d", igdbID));
        }

        gameRepository.deleteGameByigdbID(igdbID);
    }
}
