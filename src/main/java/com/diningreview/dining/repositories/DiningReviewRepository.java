package com.diningreview.dining.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.diningreview.dining.entities.DiningReview;
import com.diningreview.dining.entities.AdminReview;

public interface DiningReviewRepository extends JpaRepository<DiningReview, Long> {
    
    public List<DiningReview> findByReviewStatus(AdminReview status);
    public List<DiningReview> findByRestaurant_IdAndReviewStatus(Long id, AdminReview status);

}
