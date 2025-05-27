package com.diningreview.dining.repositories;

import com.diningreview.dining.entities.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    

}
