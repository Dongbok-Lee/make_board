package com.dongbok.board.controller;

import com.dongbok.board.model.Board;
import com.dongbok.board.model.User;
import com.dongbok.board.repository.BoardRepository;
import com.dongbok.board.repository.UserRepository;
import com.dongbok.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class Board_Controller {

    private final BoardService boardService;
    private final UserRepository userRepository;

    @Autowired
    public Board_Controller(BoardService boardService, UserRepository userRepository){
        this.boardService = boardService;
        this.userRepository = userRepository;
    }

    @GetMapping("/write")
    public String writeForm(){ return "write";}

    @PostMapping("/write_post")
    public String writePost(@ModelAttribute Board board, Principal principal){
        String email = principal.getName();
        System.out.println(email);
        User user = userRepository.findByEmail(email).get();
        System.out.println(user);
        board.setUser(user);
        boardService.save(board);
        return "redirect:/";
    }
}