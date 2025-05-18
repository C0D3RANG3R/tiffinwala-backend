package com.tiffinwala.service;

import com.tiffinwala.dto.MenuDTO;
import com.tiffinwala.entity.Menu;
import com.tiffinwala.entity.User;
import com.tiffinwala.repository.MenuRepository;
import com.tiffinwala.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    public MenuService(MenuRepository menuRepository, UserRepository userRepository) {
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
    }

    public MenuDTO createMenu(MenuDTO menuDTO) {
        User cook = userRepository.findById(menuDTO.getCookId())
                .orElseThrow(() -> new RuntimeException("Cook not found"));
        Menu menu = new Menu();
        menu.setCook(cook);
        menu.setName(menuDTO.getName());
        menu.setDescription(menuDTO.getDescription());
        menu.setPrice(menuDTO.getPrice());
        menu.setImageUrl(menuDTO.getImageUrl());
        menu.setCuisineType(menuDTO.getCuisineType());
        menu = menuRepository.save(menu);
        return toDTO(menu);
    }

    public List<MenuDTO> getMenus(Double latitude, Double longitude) {
        // TODO: Add geolocation filtering if needed
        return menuRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private MenuDTO toDTO(Menu menu) {
        MenuDTO dto = new MenuDTO();
        dto.setId(menu.getId());
        dto.setCookId(menu.getCook().getId());
        dto.setName(menu.getName());
        dto.setDescription(menu.getDescription());
        dto.setPrice(menu.getPrice());
        dto.setImageUrl(menu.getImageUrl());
        dto.setCuisineType(menu.getCuisineType());
        return dto;
    }
}