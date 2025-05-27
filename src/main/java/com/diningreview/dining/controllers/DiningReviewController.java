package com.diningreview.dining.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


import com.diningreview.dining.services.DiningReviewService;
import com.diningreview.dining.entities.DiningReview;

@RestController
@RequestMapping("/reviews")
public class DiningReviewController {

    private final DiningReviewService diningReviewService;

    public DiningReviewController(final DiningReviewService diningReviewService) {
        this.diningReviewService = diningReviewService;
    }

    @GetMapping("/pending")
    public List<DiningReview> getPendingReviews() {
        return this.diningReviewService.getPendingReviews();
    }

    // refresher that requestparam gets the queried values from url like approve?approved=true&userName=john
    @PutMapping("/approve")
    public DiningReview approveReview(@RequestBody DiningReview diningReview, @RequestParam Boolean approved, @RequestParam String userName) {
        
        return this.diningReviewService.approveReview(diningReview, approved, userName);

    }


    // Come back to this after finishing restaurant service and controller class
    // @PostMapping("/{id}")
    // public DiningReview createReview(@RequestBody DiningReview diningReview, String userName, @PathVariable("id") Long id) {

    //     this.diningReviewService
    //     return new DiningReview();
    // }

    
}
