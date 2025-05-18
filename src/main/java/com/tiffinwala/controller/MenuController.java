package com.tiffinwala.controller;

import com.tiffinwala.dto.MenuDTO;
import com.tiffinwala.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    @PreAuthorize("hasRole('COOK')")
    public ResponseEntity<MenuDTO> createMenu(@RequestBody MenuDTO menuDTO) {
        return ResponseEntity.ok(menuService.createMenu(menuDTO));
    }

    @GetMapping
    public ResponseEntity<List<MenuDTO>> getMenus(
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        return ResponseEntity.ok(menuService.getMenus(latitude, longitude));
    }
}