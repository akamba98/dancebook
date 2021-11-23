package com.project.dancebook.controller;

import com.project.dancebook.entity.Interest;
import com.project.dancebook.entity.InterestId;
import com.project.dancebook.service.InterestService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/interest")
@CrossOrigin
public class InterestController {

    private final InterestService interestService;

    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @PutMapping("")
    public void updateInterest(@RequestBody Interest interest){
        interestService.updateInterest(interest);
    }

    @GetMapping(value={"/event/{key}"})
    public int getEventInterest(@PathVariable(value = "key") String id) throws IllegalArgumentException{
        Long eventId = Long.parseLong(id);
        if(eventId == null){
            throw new IllegalArgumentException("Please fill everything");
        }
        return interestService.getEventInterest(eventId);
    }

    @GetMapping(value={"/{userId}/{eventId}"})
    public boolean isInteresteed(@PathVariable(value = "userId") Long userId,
                                 @PathVariable(value = "eventId") Long eventId
    ) throws IllegalArgumentException {
        if(userId==null || eventId == null){
            throw new IllegalArgumentException("Please fill everything");
        }
        Interest interest = new Interest(new InterestId(userId,eventId));
        return interestService.isInterested(interest);
    }

    @GetMapping("")
    public List<Interest> list(){
        List<Interest>lis =interestService.getAllInterests();
        return lis;
    }
}
