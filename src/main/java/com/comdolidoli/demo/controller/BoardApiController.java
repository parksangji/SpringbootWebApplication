package com.comdolidoli.demo.controller;

import com.comdolidoli.demo.model.Board;
import com.comdolidoli.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardApiController {

    @Autowired
    private final BoardRepository boardRepository;

    public BoardApiController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false,defaultValue = "")String title,
                    @RequestParam(required = false,defaultValue = "") String content){
        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)){
            return boardRepository.findAll();
        }
        else{
            return boardRepository.findByTitleOrContent(title,content);
        }
    }

    @PostMapping("/boards")
    Board createBoard(@RequestBody Board newBoard){
        return boardRepository.save(newBoard);
    }

    // single item

    @GetMapping("/boards/{id}")
    Board one(@PathVariable Long id){
        return boardRepository.findById(id).orElse(null);
    }

    @PutMapping("/boards/{id}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id){
        return boardRepository.findById(id).map(board -> {
            board.setTitle(newBoard.getTitle());
            board.setContent(newBoard.getContent());
            return boardRepository.save(board);
        }).orElseGet(()->{
           newBoard.setId(id);
           return boardRepository.save(newBoard);
        });
    }
    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id){
        boardRepository.deleteById(id);
    }
}
