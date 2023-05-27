package com.practice.boardgame;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardGameService {
    @Autowired
    private BoardGameRepo repo;

    public boolean saveBoardGame(BoardGame boardGame){
        return repo.save(boardGame);
    }

    public Optional<BoardGame> findById(String id){
        return repo.findById(id);
    }

    public List<BoardGame> getAllBoardGames(){
        return repo.getAllBoardGames();
    }


}

