package com.diningreview.dining.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.diningreview.dining.repositories.RestaurantRepository;
import com.diningreview.dining.entities.Restaurant;
import com.diningreview.dining.exceptions.DuplicateRestaurantException;

@Service
public class RestaurantService {
    
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(final RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        return this.restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        Optional<Restaurant> fetchedRestaurantOptional =  this.restaurantRepository.findById(id);

        if (!fetchedRestaurantOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "restaurant with id: " + id + " not found");
        } else {
            return fetchedRestaurantOptional.get();
        }

    }

    public List<Restaurant> getRestaurantsByZipCodeWithReviews(String zipCode, String allergyCategory) {

        if (allergyCategory == null || zipCode == null) {
            System.out.println("Invalid input: allergyCategory or zipCode is null");
            return null;
        }

        List<Restaurant> restaurantsByZipCodeWithRating;
        
        // fetches restaurants based on allergy and zipcode with at least one review, sorted by highest ratings
        switch (allergyCategory.toLowerCase()) {
            case "peanutbutter":
                restaurantsByZipCodeWithRating = this.restaurantRepository.findByZipCodeAndPeanutButterAllergyRatingGreaterThanOrderByPeanutButterAllergyRatingDesc(zipCode, 0f);
                break;
            case "egg":
                restaurantsByZipCodeWithRating = this.restaurantRepository.findByZipCodeAndEggAllergyRatingGreaterThanOrderByEggAllergyRatingDesc(zipCode, 0f);
                break;
            case "dairy":
                restaurantsByZipCodeWithRating = this.restaurantRepository.findByZipCodeAndDairyAllergyRatingGreaterThanOrderByDairyAllergyRatingDesc(zipCode, 0f);
                break;
            default:
                System.out.println("not a valid allergy category");
                return null;
        }

        return restaurantsByZipCodeWithRating;

    }

    public Restaurant createRestaurant(Restaurant restaurantNew) {

        // first check that new restaurant has values for zipcode and name
        if (restaurantNew.getZipCode() == null || restaurantNew.getRestaurantName() == null) {
            System.out.println("New restaurant needs zipcode and name");
            return null;
        }

        // check if restaurant with same zip code and same name exists
        try {
            Optional<Restaurant> duplicateRestaurantOptional = this.restaurantRepository.findByZipCodeAndRestaurantName(restaurantNew.getZipCode(), restaurantNew.getRestaurantName());
            
            // duplicate restaurant found, throw error
            if (duplicateRestaurantOptional.isPresent()) {
                throw new DuplicateRestaurantException("Restaurant with same name and zip code already exists!");
            }

        } catch (DuplicateRestaurantException e) {
            System.err.println(e.getMessage());
            return null;
        }

        return this.restaurantRepository.save(restaurantNew);

    }


}
