package com.diningreview.dining.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.diningreview.dining.repositories.RestaurantRepository;
import com.diningreview.dining.entities.Restaurant;

public class RestaurantService {
    
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(final RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // @GetMapping
    public Restaurant getRestaurantsById(Long id) {
        Optional<Restaurant> fetchedRestaurantOptional =  this.restaurantRepository.findById(id);

        if (!fetchedRestaurantOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "restaurant with id: " + id + " not found");
        } else {
            return fetchedRestaurantOptional.get();
        }

    }

    
    // @GetMapping
    // public 


    // @PostMapping


}
