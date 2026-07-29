package com.cfg_assignment_3.controller;

import com.cfg_assignment_3.model.Apple;
import com.cfg_assignment_3.model.AppleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class AppleController {

    @Autowired
    private AppleRepository appleRepository;

    @Value("${shop.name}")
    private String shopName;

    @GetMapping("/shopInventory/fruits/apple/braeburn")
    public ResponseEntity<List<Apple>> searchApples(@RequestParam String variety){
        log.info("search apples by variety");
        log.info("Shop: {}", shopName);
        List<Apple> results = appleRepository.findByVariety(variety);
        if (results.isEmpty()) {
            log.warn("no apples found");
        }
        return ResponseEntity.ok(results);
    }

    @PostMapping("/shopInventory/fruits/apple")
    public ResponseEntity<Apple> addApple(@RequestBody Apple apple){
        log.debug("saving new apple: {}", apple.getVariety());
        Apple saved = appleRepository.save(apple);
        return ResponseEntity.ok(saved);
    }
}