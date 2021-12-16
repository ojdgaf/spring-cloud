package com.ojdgaf.cloud.user;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public User find(@PathVariable final String id) {
        return userService.find(id);
    }

    @PostMapping
    public User create(@RequestBody @Valid final User user) {
        return userService.create(user);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable final String id) {
        userService.delete(id);
    }
}
