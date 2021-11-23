package com.project.dancebook.service;

import com.project.dancebook.entity.Comment;
import com.project.dancebook.entity.Event;
import com.project.dancebook.entity.User;
import com.project.dancebook.repository.CommentRepository;
import com.project.dancebook.repository.EventRepository;
import com.project.dancebook.repository.UserRepository;
import com.project.dancebook.dto.CommentCreationDTO;
import org.springframework.stereotype.Service;

@Service
public class CommentService{

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public Comment saveComment(CommentCreationDTO request) {
        User user = userRepository.getById(request.getUserId());
        Event event = eventRepository.getById(request.getEventId());
        Comment  comment = new Comment(request.getText(),user,event);
        commentRepository.save(comment);
        return comment;
    }
}
