package com.corey.vidlib.rest;

import com.corey.vidlib.entity.User;
import com.corey.vidlib.entity.Video;
import com.corey.vidlib.payload.UserSummary;
import com.corey.vidlib.security.CurrentUser;
import com.corey.vidlib.service.UserService;
import com.corey.vidlib.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService theUserService) {
        userService = theUserService;
    }

    // Add mapping for getting the current user

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserDetails userDetails) {

        User currentUser = userService.findByUserName(userDetails.getUsername());

        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUserName(),
                                                currentUser.getFirstName()+ " " + currentUser.getLastName());
        return userSummary;
    }

    // Add mapping for GET /videos/{videoId}

    @GetMapping("/users/{userName}")
    public User getUser(@PathVariable String userName) {

        User theUser = userService.findByUserName(userName);

        if (theUser == null) {
            throw new RuntimeException("Username is not found - " + userName);
        }

        return theUser;

    }

}
