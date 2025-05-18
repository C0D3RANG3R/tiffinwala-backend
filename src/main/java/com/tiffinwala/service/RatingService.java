package com.tiffinwala.service;

import com.tiffinwala.dto.RatingDTO;
import com.tiffinwala.entity.Order;
import com.tiffinwala.entity.Rating;
import com.tiffinwala.entity.User;
import com.tiffinwala.repository.OrderRepository;
import com.tiffinwala.repository.RatingRepository;
import com.tiffinwala.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public RatingService(RatingRepository ratingRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public RatingDTO submitRating(RatingDTO ratingDTO) {
        Order order = orderRepository.findById(ratingDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        User customer = userRepository.findById(ratingDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        User cook = userRepository.findById(ratingDTO.getCookId())
                .orElseThrow(() -> new RuntimeException("Cook not found"));

        Rating rating = new Rating();
        rating.setOrder(order);
        rating.setCustomer(customer);
        rating.setCook(cook);
        rating.setRating(ratingDTO.getRating());
        rating.setComment(ratingDTO.getComment());
        rating = ratingRepository.save(rating);

        RatingDTO response = new RatingDTO();
        response.setId(rating.getId());
        response.setOrderId(rating.getOrder().getId());
        response.setCustomerId(rating.getCustomer().getId());
        response.setCookId(rating.getCook().getId());
        response.setRating(rating.getRating());
        response.setComment(rating.getComment());
        return response;
    }
}