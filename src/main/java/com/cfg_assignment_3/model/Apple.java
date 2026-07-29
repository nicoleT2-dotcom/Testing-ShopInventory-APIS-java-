package com.cfg_assignment_3.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "apple")
@Data
@NoArgsConstructor
public class Apple {
    @Id //entity needs ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto Increment
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "variety", nullable = false)
    private String variety;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "colour", nullable = false)
    private String colour;

    @Column(name = "weight_grams", nullable = false)
    private Integer weightGrams;

    @Column(name = "in_season", nullable = false)
    private boolean inSeason;
}