package com.project.dancebook.service;

import com.project.dancebook.dto.UserDTO;
import com.project.dancebook.dto.UserLoginDTO;
import com.project.dancebook.entity.User;
import com.project.dancebook.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Base64;
import java.util.List;

@Service
public class UserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) throws IllegalArgumentException{
        List<User>check=userRepository.findAll().stream().filter(item -> user.getUsername().equals(item.getUsername())).toList();
        if (check.isEmpty()){
            String encodedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }
        else {
            throw new IllegalArgumentException("Username already exist");
        }
    }

    public User loginUser(UserLoginDTO user)throws IllegalArgumentException{
        List<User>check=userRepository.findAll().stream().filter(item -> user.getUsername().equals(item.getUsername())).toList();
        if(check.isEmpty()){
             throw new IllegalArgumentException("Username doesn't exist");
        }
        else {
            byte[] decodedBytes = Base64.getDecoder().decode(check.get(0).getPassword());
            String decodedPassword = new String(decodedBytes);
            if (user.getPassword().equals(decodedPassword)){
                return check.get(0);
            }
            else {
                throw new IllegalArgumentException("Password is wrong");
            }
        }
    }
}
