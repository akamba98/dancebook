package com.project.dancebook.service;

import com.project.dancebook.entity.Comment;
import com.project.dancebook.entity.Event;
import com.project.dancebook.entity.Interest;
import com.project.dancebook.repository.CommentRepository;
import com.project.dancebook.repository.EventRepository;
import com.project.dancebook.repository.InterestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService{

    private final EventRepository eventRepository;
    private final CommentRepository commentRepository;
    private final InterestRepository interestRepository;

    public EventService(EventRepository eventRepository, CommentRepository commentRepository, InterestRepository interestRepository) {
        this.eventRepository = eventRepository;
        this.commentRepository = commentRepository;
        this.interestRepository = interestRepository;
    }

    public void saveEvent(Event event){
        eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEvent(long id) {
        return eventRepository.findById(id);
    }

    public void deleteOldEvents() {
        LocalDateTime dateNow = LocalDateTime.now();
        List<Event> events = eventRepository.findAll().stream().filter(element -> element.getEndAt().isBefore(dateNow)).toList();
        for(Event event: events){
            for (Comment comment: event.getComments()){
               commentRepository.deleteById(comment.getCommentId());
            }
            List<Interest>interests =  interestRepository.findAll().stream()
                    .filter(interest -> interest.getInterestId().getEventId()==event.getEventId())
                    .toList();
            for (Interest interest: interests){
                interestRepository.deleteById(interest.getInterestId());
            }
            eventRepository.deleteById(event.getEventId());
        }
    }
}
