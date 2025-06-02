package com.diningreview.dining.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "USER_NAME")
    @NotBlank(message = "Username cannot be blank")
    @NotBlank(message = "Username cannot be null")
    private String userName;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATES")
    private String states;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "INTEREST_IN_PEANUT_ALLERGIES")
    private Boolean interestInPeanutAllergies;

    @Column(name = "INTEREST_IN_EGG_ALLERGIES")
    private Boolean interestInEggAllergies;

    @Column(name = "INTEREST_IN_DAIRY_ALLERGIES")
    private Boolean interestInDairyAllergies;
    
    @Column(name = "IS_MODERATOR", nullable = false)
    private Boolean isModerator = false;

    
}
