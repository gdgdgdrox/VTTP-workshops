package com.practice.boardgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardGameRepo {

    private static final Logger logger = LoggerFactory.getLogger(BoardGameController.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public boolean boardGameExists(String id){
        boolean exists = false;
        if (redisTemplate.hasKey(id)){
            exists = true;
            return exists;
        }
        return exists;
    }

    public boolean save(BoardGame boardGame){
        boolean save;
        String id = boardGame.getId().toString();
        if (boardGameExists(id)){
            save = false;
            return save;
        }
        else{
            redisTemplate.opsForValue().set(boardGame.getId().toString(), boardGame);
            save = true;
            return save;
        }
    }
    
    public Optional<BoardGame> findById(String id){
        BoardGame obj = (BoardGame) redisTemplate.opsForValue().get(id);
        if (null != obj){
            return Optional.of(obj);
        }
        return Optional.empty();
    }

    public List<BoardGame> getAllBoardGames(){
        Set<String> keys = redisTemplate.keys("*");
        List<String> list = keys.stream()
                                .map(k -> Integer.parseInt(k))
                                .sorted().map(k -> Integer.toString(k))
                                .toList();
        List<BoardGame> boardGameList = new ArrayList<>();
        for (String key : list){
            BoardGame bg = (BoardGame)redisTemplate.opsForValue().get(key);
            boardGameList.add(bg);
        }
        logger.info("BOARD GAME LIST SORTED BY ID : " + boardGameList);
        return boardGameList;
    }
}
