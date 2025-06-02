package com.diningreview.dining.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diningreview.dining.entities.Restaurant;
import com.diningreview.dining.services.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(final RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return this.restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return this.restaurantService.getRestaurantById(id);
    }

    @GetMapping("/search-by-zipcode")
    public List<Restaurant> getRestaurantsByZipCodeWithReviews(@RequestParam(required = true) String zipcode, @RequestParam(required = true) String allergy) {
        return this.restaurantService.getRestaurantsByZipCodeWithReviews(zipcode, allergy);
    }

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurantNew) {
        return this.restaurantService.createRestaurant(restaurantNew);
    }
    


    
}
