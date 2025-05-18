package com.tiffinwala.controller;

import com.tiffinwala.dto.RatingDTO;
import com.tiffinwala.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<RatingDTO> submitRating(@RequestBody RatingDTO ratingDTO) {
        return ResponseEntity.ok(ratingService.submitRating(ratingDTO));
    }
}