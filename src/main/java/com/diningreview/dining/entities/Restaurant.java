package com.diningreview.dining.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.diningreview.dining.entities.DiningReview;
import com.fasterxml.jackson.annotation.JsonIgnore;

// restaurant will have 4 fields, peanutScore, eggScore, dairyScore, overallScore each having rating from 1-5 and each repressenting the average from ratings of user reviews
@Entity
@Table(name = "RESTAURANTS")
@Getter
@Setter
@EqualsAndHashCode
public class Restaurant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 4 ratings

    @Column(name = "PEANUT_ALLERGY_RATING")
    private Float peanutButterAllergyRating;

    @Column(name = "EGG_ALLERGY_RATING")
    private Float eggAllergyRating;

    @Column(name = "DAIRY_ALLERGY_RATING")
    private Float dairyAllergyRating;

    @Column(name = "TOTAL_RATING")
    private Float totalRating;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "RESTAURANT_NAME")
    private String restaurantName;

    // one restaurant will map to multiple reviews and the mapping is handled by restaurant so it avoids the need to create a third table
    @OneToMany(mappedBy="restaurant", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DiningReview> reviews;
    
}
