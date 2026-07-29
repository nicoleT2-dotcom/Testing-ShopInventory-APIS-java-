package com.cfg_assignment_3.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppleRepository extends JpaRepository<Apple, Long> {
    List<Apple> findByVariety(String variety);
}
