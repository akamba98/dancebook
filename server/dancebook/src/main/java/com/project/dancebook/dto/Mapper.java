package com.project.dancebook.dto;

import com.project.dancebook.entity.Comment;
import com.project.dancebook.entity.Event;
import com.project.dancebook.entity.User;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class Mapper{

    public CommentDTO toDto(Comment comment){
        return new CommentDTO(
            comment.getCommentId(),
            comment.getText(),
            toDto(comment.getUser()),
            comment.getEvent().getEventId(),
            comment.getCreatedAt()
        );
    }

    public EventDTO toDto(Event event){
        Set<CommentDTO> commentResponses=new HashSet<>();
        Set<Comment> comments= event.getComments();
        for (Comment comment: comments){
            commentResponses.add(toDto(comment));
        }
        return new EventDTO(
            event.getEventId(),
            event.getName(),
            event.getDescription(),
            event.getLocation(),
            event.getStartAt(),
            event.getEndAt(),
            event.getInterestCount(),
            commentResponses
        );
    }

    public UserDTO toDto(User user){
        return new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getClubName()
        );
    }
}
