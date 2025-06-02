package com.diningreview.dining.repositories;

import java.util.List;
import java.util.Optional;

import com.diningreview.dining.entities.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
    // want to fetch restaurants that match zip code and have at least one review for given allergy
    public List<Restaurant> findByZipCodeAndPeanutButterAllergyRatingGreaterThanOrderByPeanutButterAllergyRatingDesc(String zipcode, Float score);
    public List<Restaurant> findByZipCodeAndEggAllergyRatingGreaterThanOrderByEggAllergyRatingDesc(String zipcode, Float score);
    public List<Restaurant> findByZipCodeAndDairyAllergyRatingGreaterThanOrderByDairyAllergyRatingDesc(String zipcode, Float score);
    public Optional<Restaurant> findByZipCodeAndRestaurantName(String zipcode, String restaurantName);

}
