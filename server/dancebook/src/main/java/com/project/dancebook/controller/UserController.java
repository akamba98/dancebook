package com.project.dancebook.controller;

import com.project.dancebook.dto.*;
import com.project.dancebook.entity.User;
import com.project.dancebook.service.CommentService;
import com.project.dancebook.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController{

    private final UserService userService;
    private final CommentService commentService;
    private final Mapper mapper;

    public UserController(UserService userService,CommentService commentService, Mapper mapper) {
        this.userService = userService;
        this.commentService = commentService;
        this.mapper = mapper;
    }

    @PostMapping(value="/register")
    public ResponseDTO register(@RequestParam String dancer,@RequestBody User user) throws IllegalArgumentException{
        boolean isDancer = dancer.equals("true");
        user.trimColumns();
        boolean isBodyEmpty = isDancer ?
                user.getUsername().isEmpty() ||
                user.getPassword().isEmpty() ||
                user.getClubName().isEmpty() ||
                user.getFirstName().isEmpty() ||
                user.getLastName().isEmpty()
                :
                user.getUsername().isEmpty() ||
                user.getPassword().isEmpty() ||
                user.getClubName().isEmpty();
        if(isBodyEmpty){
            throw new IllegalArgumentException("Please fill all of the fields");
        }
        boolean passwordCheck = Pattern.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$",user.getPassword());
        if(!passwordCheck){
            throw new IllegalArgumentException("Password must be at least 8 characters with with  1 number and 1 character");
        }
        userService.saveUser(user);
        return new ResponseDTO(200,"You have registered successfully");
    }

    @PostMapping(value={"/comment"})
    public CommentDTO comment(@RequestBody CommentCreationDTO comment) throws IllegalArgumentException{
        comment.trimColumns();
        boolean isBodyEmpty = comment.getText().isEmpty() || comment.getUserId()==null || comment.getEventId()==null;
        if(isBodyEmpty){
            throw new IllegalArgumentException("Please fill everything");
        }
        return mapper.toDto(commentService.saveComment(comment));
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody UserLoginDTO user) throws IllegalArgumentException{
        user.trimColumns();
        boolean isInputEmpty = user.getUsername().isEmpty() || user.getPassword().isEmpty();
        if(isInputEmpty){
            throw new IllegalArgumentException("Please fill all of the fields");
        }
        return mapper.toDto(userService.loginUser(user));
    }
}
