package com.diningreview.dining.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.diningreview.dining.entities.DiningReview;
import com.diningreview.dining.entities.User;
import com.diningreview.dining.entities.Restaurant;
import com.diningreview.dining.repositories.DiningReviewRepository;
import com.diningreview.dining.services.RestaurantService;
import com.diningreview.dining.services.UserService;
import com.diningreview.dining.entities.AdminReview;
import com.diningreview.dining.exceptions.CannotPerformModeratorActionException;

@Service
public class DiningReviewService {

    // to make calls to dining review database
    private final DiningReviewRepository diningReviewRepository;

    // to use functions that perform business logic from User database like making sure user exists before approving review
    private final UserService userService;

    private final RestaurantService restaurantService;

    public DiningReviewService(final DiningReviewRepository diningReviewRepository, final UserService userService, final RestaurantService restaurantService) {
        this.diningReviewRepository = diningReviewRepository;
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    public List<DiningReview> getPendingReviews() {
        return this.diningReviewRepository.findByReviewStatus(AdminReview.PENDING);
    }

    public List<DiningReview> getApprovedReviews() {
        return this.diningReviewRepository.findByReviewStatus(AdminReview.APPROVED);
    }

    // either approve or reject review, not just approve
    public DiningReview approveReview(DiningReview diningReview, Boolean approved, String userName) {

        try {

            // if user is not a moderator, then cannot approve/disapprove
            if (!this.userService.checkIfUserIsModerator(userName)) {
                throw new CannotPerformModeratorActionException("Username: " + userName + " does not have moderator privileges");
            }

        } catch (CannotPerformModeratorActionException e) {

            System.err.println(e.getMessage());
            return null;

        }

        Optional<DiningReview> diningReviewOptional = this.diningReviewRepository.findById(diningReview.getId());

        // review not found
        if (!diningReviewOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "dining review with id: " + diningReview.getId() + " not found");
        }

        DiningReview diningReviewToApprove = diningReviewOptional.get();

        AdminReview reviewDecision = approved ? AdminReview.APPROVED : AdminReview.REJECTED;

        // can approve or disapprove
        diningReviewToApprove.setReviewStatus(reviewDecision);
        DiningReview updatedDiningReview = this.diningReviewRepository.save(diningReviewToApprove);
        return updatedDiningReview;

    }

    public DiningReview createReview(DiningReview diningReview, String userName, Long id) {
        
        // getUser() handles if user is not found
        User retrievedUser = this.userService.getUser(userName);

        // make sure restuarant with id exists before posting review
        Restaurant fetchedRestaurant = this.restaurantService.getRestaurantById(id);


        if (fetchedRestaurant != null) {
            diningReview.setRestaurant(fetchedRestaurant);
            return this.diningReviewRepository.save(diningReview);
        }
    
        return null;
        
    }

    // @GetMapping
    public List<DiningReview> getApprovedReviewsForRestaurant(Long id) {

        // need a function probably from the dining entity to get reviews based on approved
        List<DiningReview> reviewsFromRestaurant = this.diningReviewRepository.findByRestaurant_IdAndReviewStatus(id, AdminReview.APPROVED);
        
        if (reviewsFromRestaurant.isEmpty()) {
            return null;
        } else {
            return reviewsFromRestaurant;
        }

    }

    
}
