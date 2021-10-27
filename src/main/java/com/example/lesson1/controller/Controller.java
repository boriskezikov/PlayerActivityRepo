package com.example.lesson1.controller;

import com.example.lesson1.dto.PlayerDTO;
import com.example.lesson1.dto.ResponseDTO;
import com.example.lesson1.entity.PlayerEntity;
import com.example.lesson1.entity.PlayerStatus;
import com.example.lesson1.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Controller {

    private final PlayerService service;

    @GetMapping("/player/{id}")
    public PlayerEntity loadPlayer(@PathVariable("id") Long id) {
        return service.getPlayerById(id);
    }

    @GetMapping("/player")
    public ResponseEntity<ResponseDTO> loadPlayers(@RequestParam("status") PlayerStatus status) {
        return service.findPlayersByStatus(status);
    }

    //todo реализовать метод сохранения сущности
    public void create() {

    }

    //todo реализовать метод обновления сущности
    public PlayerDTO update() {
        new Object();
        return null;
    }
}
