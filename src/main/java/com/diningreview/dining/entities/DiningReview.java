package com.diningreview.dining.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.diningreview.dining.entities.AdminReview;
import com.diningreview.dining.entities.Restaurant;

@Entity
@Table(name = "DINING_REVIEWS")
@Getter
@Setter
@EqualsAndHashCode
public class DiningReview {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PEANUT_SCORE")
    private Float peanutScore;

    @Column(name = "EGG_SCORE")
    private Float eggScore;

    @Column(name = "DAIRY_SCORE")
    private Float dairyScore;

    @Column(name = "COMMENTARY")
    private String commentary;

    @Column(name = "REVIEW_STATUS")
    @Enumerated(EnumType.STRING)
    private AdminReview reviewStatus;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;


}

