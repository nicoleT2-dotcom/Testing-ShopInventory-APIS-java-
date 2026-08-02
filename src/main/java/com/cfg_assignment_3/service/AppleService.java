package com.cfg_assignment_3.service;

import com.cfg_assignment_3.exceptions.AppleException;
import com.cfg_assignment_3.model.Apple;
import com.cfg_assignment_3.model.AppleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger; //logging
import org.slf4j.LoggerFactory;

@Service
public class AppleService {
    private static final Logger log = LoggerFactory.getLogger(AppleService.class);
    @Autowired
    private AppleRepository appleRepository;


    //Use streams to take a list and keep only the ones matching the given variety
    public List<Apple> filterByVariety(List<Apple> apples, String variety) {
        return apples.stream()
                .filter(apple -> apple.getVariety().equalsIgnoreCase(variety))
                .toList();
    }

    // generics: list of ANY type <T>

    public <T> List<T> requireNonEmpty(List<T> list, String message) throws AppleException {
        // if the list is empty, throw an error instead of returning nothing
        if (list.isEmpty()) {
            throw new AppleException(message);
        }
        return list;
    }

    // method can throw AppleException, caller must handle it
    public List<Apple> searchApples(String variety) throws AppleException {
        log.debug("Searching apples for variety: {}", variety);

        // get every apple from the database
        List<Apple> allApples = appleRepository.findAll();

        // narrow list down to just the matching variety (uses streams above)
        List<Apple> matches = filterByVariety(allApples, variety);

        // if nothing matched, this throws AppleException, otherwise returns matches
        return requireNonEmpty(matches, "No apples found for variety: " + variety);
    }
}