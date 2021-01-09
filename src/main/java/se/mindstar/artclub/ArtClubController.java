package se.mindstar.artclub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.mindstar.artclub.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ArtClubController {

    @Autowired
    UserRepository repository;

    @GetMapping("/")
    String sayHello() {
        return "Hello";
    }

    @GetMapping("/user")
    List<User> getAllUsers() {
        Iterable<User> users = repository.findAll();
        ArrayList<User> result = new ArrayList<User>();
        for(User user : users) {
            result.add(user);
        }
        return result;
    }

    @GetMapping("/user/{id}")
    User getUser(@PathVariable Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceException(HttpStatus.NOT_FOUND, "User with id " + id + " not found.");
        }
    }

    @PostMapping("/user")
    User addUser(@RequestBody User newUser) {
        User user = repository.save(newUser);
        return user;
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
