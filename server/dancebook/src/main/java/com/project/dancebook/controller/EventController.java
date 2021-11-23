package com.project.dancebook.controller;

import com.project.dancebook.dto.Mapper;
import com.project.dancebook.dto.ResponseDTO;
import com.project.dancebook.entity.Event;
import com.project.dancebook.dto.EventDTO;
import com.project.dancebook.service.EventService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
@CrossOrigin
public class EventController {

    private final EventService eventService;
    private final Mapper mapper;

    public EventController(EventService eventService, Mapper mapper) {
        this.eventService = eventService;
        this.mapper = mapper;
    }

    @PostMapping("")
    public ResponseDTO addEvent(@RequestBody Event event) throws IllegalArgumentException{
        LocalDateTime dateNow = LocalDateTime.now();
        event.trimColumns();
        boolean isBodyEmpty = event.getName().isEmpty() ||
                event.getLocation().isEmpty() ||
                event.getDescription().isEmpty() ||
                event.getStartAt() == null ||
                event.getEndAt() == null;
        if(isBodyEmpty){
            throw new IllegalArgumentException("Please fill all of the fields");
        }
        if(event.getStartAt().isBefore(dateNow)){
            throw new IllegalArgumentException("You can't add event that already started");
        }
        if(event.getStartAt().isEqual(event.getEndAt())){
            throw new IllegalArgumentException("Your start and end of event can't be the same time");
        }
        if(event.getStartAt().isAfter(event.getEndAt())){
            throw new IllegalArgumentException("The start of the event can't be after the end");
        }
        eventService.saveEvent(event);
        return new ResponseDTO(200, "New event has been added");
    }

    @GetMapping("")
    public List<EventDTO> getAllEvents(){
        List<Event>events=eventService.getAllEvents();
        List<EventDTO>eventResponses = new ArrayList<>();
        for(Event event : events){
            eventResponses.add(mapper.toDto(event));
        }
       return eventResponses ;
    }

    @GetMapping(value={"/{key}"})
    public EventDTO getEvent(@PathVariable(value = "key") String key){
        Long id = Long.parseLong(key);
        Optional<Event> event = eventService.getEvent(id);
        if(event.isPresent()) {
            return mapper.toDto(event.get());
        }
        return new EventDTO();
    }

    @DeleteMapping("/old")
    public void deleteOldEvents(){
        eventService.deleteOldEvents();
    }
}
