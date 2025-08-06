package com.example.demo.controller;

 import com.example.demo.dto.UserRequest;
// import com.example.demo.model.Booking;
// import com.example.demo.model.ServiceEntity;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor


public class UserController {
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User request){

        User user =  userRepository.findById(id).orElseThrow();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setId(id);

        return ResponseEntity.ok(userRepository.save(user));
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
  

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserId(@PathVariable Long id){

        return userRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
