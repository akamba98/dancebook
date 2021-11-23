package com.project.dancebook.service;

import com.project.dancebook.entity.Event;
import com.project.dancebook.entity.Interest;
import com.project.dancebook.repository.EventRepository;
import com.project.dancebook.repository.InterestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestService{

    private final InterestRepository interestRepository;
    private final EventRepository eventRepository;

    public InterestService(InterestRepository interestRepository, EventRepository eventRepository) {
        this.interestRepository = interestRepository;
        this.eventRepository = eventRepository;
    }

    public void updateInterest(Interest interest) {
        boolean exist = interestRepository.findById(interest.getInterestId()).isPresent();
        if(exist){
            Event event = eventRepository.getById(interest.getInterestId().getEventId());
            event.setInterestCount(event.getInterestCount()-1);
            eventRepository.save(event);
            interestRepository.deleteById(interest.getInterestId());
        }
        else {
            Event event = eventRepository.getById(interest.getInterestId().getEventId());
            event.setInterestCount(event.getInterestCount()+1);
            eventRepository.save(event);
            interestRepository.save(interest);
        }
    }

    public int getEventInterest(Long eventId){
       List<Interest>interests =  interestRepository.findAll().stream()
               .filter(interest -> interest.getInterestId().getEventId()==eventId)
               .toList();
       return interests.size();
    }

    public boolean isInterested(Interest interest){
        return interestRepository.findById(interest.getInterestId()).isPresent();
    }

    public List<Interest> getAllInterests() {
        return interestRepository.findAll();
    }
}
