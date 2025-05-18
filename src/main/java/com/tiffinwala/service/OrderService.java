package com.tiffinwala.service;

import com.tiffinwala.dto.OrderDTO;
import com.tiffinwala.entity.Menu;
import com.tiffinwala.entity.Order;
import com.tiffinwala.entity.User;
import com.tiffinwala.repository.MenuRepository;
import com.tiffinwala.repository.OrderRepository;
import com.tiffinwala.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    public OrderDTO placeOrder(OrderDTO orderDTO) {
        User customer = userRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        User cook = userRepository.findById(orderDTO.getCookId())
                .orElseThrow(() -> new RuntimeException("Cook not found"));
        Menu menu = menuRepository.findById(orderDTO.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setCook(cook);
        order.setMenu(menu);
        order.setQuantity(orderDTO.getQuantity());
        order.setDeliveryTime(orderDTO.getDeliveryTime());
        order.setStatus("PENDING");
        order.setTotalAmount(menu.getPrice() * orderDTO.getQuantity());
        order = orderRepository.save(order);

        OrderDTO response = new OrderDTO();
        response.setId(order.getId());
        response.setCustomerId(order.getCustomer().getId());
        response.setCookId(order.getCook().getId());
        response.setMenuId(order.getMenu().getId());
        response.setQuantity(order.getQuantity());
        response.setDeliveryTime(order.getDeliveryTime());
        response.setStatus(order.getStatus());
        response.setTotalAmount(order.getTotalAmount());
        return response;
    }
}