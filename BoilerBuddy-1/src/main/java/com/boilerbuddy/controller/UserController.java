package com.boilerbuddy.controller;

import com.boilerbuddy.model.UserProfile;
import com.boilerbuddy.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private FirebaseService firebaseService;

    @GetMapping("/profile/{uid}")
    public UserProfile getProfile(@PathVariable String uid) throws ExecutionException, InterruptedException {
        return firebaseService.getUserProfile(uid);
    }

    @PostMapping("/profile")
    public void updateProfile(@RequestBody UserProfile profile) throws ExecutionException, InterruptedException {
        firebaseService.saveUserProfile(profile);
    }

    @GetMapping("/match/{uid}")
    public List<UserProfile> getMatches(@PathVariable String uid) throws ExecutionException, InterruptedException {
        UserProfile user = firebaseService.getUserProfile(uid);
        if (user == null) return List.of();
        List<UserProfile> allUsers = firebaseService.getAllUserProfiles();
        return allUsers.stream()
                .filter(u -> !u.getUid().equals(uid))
                .filter(u -> u.getInterests() != null && user.getInterests() != null &&
                        u.getInterests().stream().anyMatch(user.getInterests()::contains))
                .collect(Collectors.toList());
    }
} 