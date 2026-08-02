package com.cfg_assignment_3.controller;

import com.cfg_assignment_3.model.Apple;
import com.cfg_assignment_3.model.AppleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cfg_assignment_3.service.AppleService;
import com.cfg_assignment_3.exceptions.AppleException;

import java.util.List;

@RestController
@Slf4j
public class AppleController {

    @Autowired
    private AppleRepository appleRepository;

    @Autowired
    private AppleService appleService;

    @GetMapping("/shopInventory/fruits/apple/search")
    public ResponseEntity<?> searchApplesByVariety(@RequestParam String variety){
        try {
            List<Apple> results = appleService.searchApples(variety);
            return ResponseEntity.ok(results);
        } catch (AppleException e) {
            log.warn("Apple search failed: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/shopInventory/fruits/apple")
    public ResponseEntity<Apple> addApple(@RequestBody Apple apple){
        log.debug("saving new apple: {}", apple.getVariety());
        Apple saved = appleRepository.save(apple);
        return ResponseEntity.ok(saved);
    }
}