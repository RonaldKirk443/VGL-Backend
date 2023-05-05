package com.uio443.vglbackend.service;

import com.uio443.vglbackend.model.Game;
import com.uio443.vglbackend.model.User;
import com.uio443.vglbackend.repository.GameRepository;
import com.uio443.vglbackend.repository.UserGameRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserGameRepository userGameRepository;

    @Autowired
    WebClient igdbWebClient;


    @Autowired
    public GameService(GameRepository gameRepository, UserGameRepository userGameRepository) {
        this.gameRepository = gameRepository;
        this.userGameRepository = userGameRepository;
    }

    public Game getGameByIgdbId(Long igdbId) {
        return gameRepository.findGameByIgdbId(igdbId).orElseThrow(() -> new RuntimeException("Bad ID"));
    }

    public Game getGameByTitle(String title) {
        return gameRepository.findGameByTitle(title).orElseThrow(() -> new RuntimeException("No such Game exists"));
    }

    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    public Game addGame(Long igdbId) {
        String gameInfoResponse = igdbWebClient.post()
                .uri("/games")
                .bodyValue(String.format("fields name, genres, cover; where id = %d;", igdbId))
                .retrieve().bodyToMono(String.class).block();
        JSONObject gameInfoJsonObject = new JSONArray(gameInfoResponse).getJSONObject(0);
        List<Object> genreList = gameInfoJsonObject.getJSONArray("genres").toList();
        Set<Integer> genreSet = new HashSet<>();
        for(Object o : genreList){
            genreSet.add((int)o);
        }

        int coverId = gameInfoJsonObject.getInt("cover");
        String gameCoverResponse = igdbWebClient.post()
                .uri("/covers")
                .bodyValue(String.format("fields image_id; where id = %d;",coverId))
                .retrieve().bodyToMono(String.class).block();
        String coverImgUrl = new JSONArray(gameCoverResponse).getJSONObject(0).getString("image_id");
        coverImgUrl = String.format("https://images.igdb.com/igdb/image/upload/t_cover_big/%s.jpg", coverImgUrl);

        Game newGame = new Game();
        newGame.setIgdbId(igdbId);
        newGame.setTitle(gameInfoJsonObject.getString("name"));
        newGame.setGenres(genreSet);
        newGame.setCoverImgUrl(coverImgUrl);
        return gameRepository.save(newGame);
    }

    public Game updateGame(Game game) {
        Optional<Game> gameIdCheck = gameRepository.findGameByIgdbId(game.getIgdbId());
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
        if (gameRepository.findGameByIgdbId(igdbId).isEmpty()) {
            throw new RuntimeException(String.format("Game does not exist with ID %d", igdbId));
        }

        gameRepository.deleteGameByIgdbId(igdbId);
    }


    public List<Game> getAllGames() {
        return gameRepository.getAllGames();
    }

    public List<Long> getUsersByIgdbId(Long igdbId) {
        if (gameRepository.findGameByIgdbId(igdbId).isEmpty()) {
            throw new RuntimeException(String.format("Game does not exist with ID %d", igdbId));
        }
        return userGameRepository.getAllUserIdsByIgdbId(igdbId);
    }
}
