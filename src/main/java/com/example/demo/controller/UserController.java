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

    


@PutMapping("/{id}")
public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User request) {
    User user = userRepository.findById(id).orElseThrow();
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    return ResponseEntity.ok(userRepository.save(user));
}



    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
  

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserId(@PathVariable("id") Long id){

        return userRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> deleteUser( @PathVariable("id") Long id){
        return userRepository.findById(id).map(user ->{
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
     }

     @PatchMapping("/{id}")
     public ResponseEntity<?> patchUser(@PathVariable("id") Long id, @RequestBody User request) {
         return userRepository.findById(id)
             .map(user -> {
                 if (request.getName() != null) {
                     user.setName(request.getName());
                 }
                 if (request.getEmail() != null) {
                     user.setEmail(request.getEmail());
                 }
                 return ResponseEntity.ok(userRepository.save(user));
             })
             .orElse(ResponseEntity.notFound().build());
     }
}
