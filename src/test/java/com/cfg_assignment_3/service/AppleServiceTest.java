package com.cfg_assignment_3.service;

import com.cfg_assignment_3.exceptions.AppleException;
import com.cfg_assignment_3.model.Apple;
import com.cfg_assignment_3.model.AppleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // activate Mockito so I can use @Mock and @InjectMocks
public class AppleServiceTest {

    @Mock
    private AppleRepository appleRepository; // mock repository, no real DB used

    @InjectMocks
    private AppleService appleService; // real AppleService, with the mock repo injected in

    private Apple gala;
    private Apple braeburn;

    // use @BeforeEach to refresh test data
    @BeforeEach
    void setUp() {
        gala = new Apple();
        gala.setVariety("Gala");

        braeburn = new Apple();
        braeburn.setVariety("Braeburn");
    }

    //Test exact case match and if variety exists and
    @Test
    void searchApples_returnsMatches_whenVarietyExists() throws AppleException {
        // Arrange: pretend the DB has these two apples
        when(appleRepository.findAll()).thenReturn(List.of(gala, braeburn));

        List<Apple> result = appleService.searchApples("Gala");

        // my assertion: only the Gala apple comes back
        assertEquals(1, result.size());
        assertEquals("Gala", result.get(0).getVariety());
    }

    // Test edge case: case sensitivity, if the user types the wrong case what will be returned
    @Test
    void searchApplesIsCaseInsensitive() throws AppleException {
        when(appleRepository.findAll()).thenReturn(List.of(gala));
        //use wrong case for gala ()gAla
        List<Apple> result = appleService.searchApples("gAla");

        // my assertion: still matches despite casing difference
        assertEquals(1, result.size());
    }

    // Test case invalid variety input, should throw exception
    @Test
    void searchApples_throwsException_whenNoMatches() {
        when(appleRepository.findAll()).thenReturn(List.of(gala, braeburn));

        // if we search for non-existent item from list, throw exception
        AppleException exception = assertThrows(AppleException.class,
                () -> appleService.searchApples("Pink Lady"));

        // my assertion: exception message
        assertEquals("No apples found for variety: Pink Lady", exception.getMessage());
    }


    @Test
        // test case: empty database
    void searchApples_throwsException_whenDatabaseIsEmpty() {
        //set up db that has nothing in it
        when(appleRepository.findAll()).thenReturn(Collections.emptyList());

        // assertion: throws AppleException when db is empty
        AppleException exception = assertThrows(AppleException.class,
                () -> appleService.searchApples("Gala"));

        // assertion: message come through correct
        assertEquals("No apples found for variety: Gala", exception.getMessage());
    }


    @Test
        //Test for empty string as input when a user submits blank search
    void searchApples_throwsException_whenVarietyIsEmptyString() {
        when(appleRepository.findAll()).thenReturn(List.of(gala));

        //Assertion: empty string won't match any real variety
        assertThrows(AppleException.class, () -> appleService.searchApples(""));
    }

    // test filterByVariety with empty input list, nothing to filter
    @Test
    void filterByVariety_returnsEmptyList_whenInputListIsEmpty() {
        List<Apple> result = appleService.filterByVariety(new ArrayList<>(), "Gala");

        // Assertion: empty input, empty output.
        assertTrue(result.isEmpty());
    }
}