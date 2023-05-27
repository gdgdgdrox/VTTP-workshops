package day26.game.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import day26.game.model.BoardGame;
import day26.game.repository.BoardGameRepository;

@Service
public class BoardGameService {
    
    @Autowired
    private BoardGameRepository repo;

    public long findTotalNumberOfGames(){
        return repo.findTotalNumberOfGames();
    }

    public List<BoardGame> findAllBoardGames(int limit, int offset){
        List<BoardGame> boardGames = new LinkedList<>();
        List<Document> documents = repo.findAllBoardGames(limit, offset);
        for (Document doc : documents){
            BoardGame bg = new BoardGame();
            bg.setGameId(doc.getInteger("gid"));
            bg.setName(doc.getString("name"));
            boardGames.add(bg);
        }
        return boardGames;
    }

    public List<BoardGame> boardGamesAscOrder(int limit, int offset){
        return repo.findGamesInAscOrder(limit, offset);
    }

    public Optional<Document> findGameById(Integer id){
        return repo.findGameById(id);
    }

    public Optional<Document> findGameByIdReturnDoc(Integer id){
        return repo.findGameByIdReturnDoc(id);
    }

    public void insertBG(){
        repo.insertBG();
    }
}
