package com.example.taskManager.service;

import com.example.taskManager.dto.UserRequest;
import com.example.taskManager.dto.UserResponse;
import com.example.taskManager.exception.DuplicateEmailException;
import com.example.taskManager.exception.UserNotFoundException;
import com.example.taskManager.model.User;
import com.example.taskManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public UserResponse toResponse(User u){
        return UserResponse.builder().
                id(u.getId())
                .name(u.getName())
                .email(u.getEmail())
                .createdAt(u.getCreatedAt())
                .build();
    }
    public UserResponse create(UserRequest dto) {
        if(userRepository.existsByEmailIgnoreCase(dto.getEmail())) {
            throw new DuplicateEmailException(dto.getEmail());
        }
            User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
        User saved = userRepository.save(user);
        return toResponse(saved);
    }
    public List<UserResponse> list(Optional<String> name){

        if(name.isPresent())
        {
            return userRepository.findByNameContainingIgnoreCase(name).stream().map(this::toResponse).collect(java.util.stream.Collectors.toList());

        }
            return userRepository.findAll().stream().map(this::toResponse).collect(java.util.stream.Collectors.toList());
        }

    public UserResponse get(Long id){
            User u =  userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
            return toResponse(u);
        }

    public UserResponse update(Long id, UserRequest dto){
            User u = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));

            if(userRepository.existsByEmailIgnoreCase(dto.getEmail())) {
                throw new DuplicateEmailException(dto.getEmail());
            }
            u.setEmail(dto.getEmail());
            u.setName(dto.getName());
            return toResponse(u);
        }
    public void delete(Long id) {
            if(!userRepository.existsById(id))
                throw new UserNotFoundException(id);

            User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
            userRepository.delete(u);
        }

    public List<UserResponse> listEmail( Optional<String> email) {

        if(email.isPresent())
        {
            String par = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            Pattern p = Pattern.compile(par);
            Matcher m = p.matcher(email.get());
            if (!m.matches()) {
                throw new RuntimeException("Please verify that the email you are searching for is valid");
            }

//            while (m.find()) {
//                throw new RuntimeException("Please verify that the email u are searching for is valid");
//            }
            return userRepository.findByEmailContainingIgnoreCase(email).stream().map(this::toResponse).collect(java.util.stream.Collectors.toList());

        }
        return userRepository.findAll().stream().map(this::toResponse).collect(java.util.stream.Collectors.toList());
    }
    }

