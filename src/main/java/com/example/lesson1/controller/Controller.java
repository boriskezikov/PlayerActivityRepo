package com.example.lesson1.controller;

import com.example.lesson1.dto.PlayerDTO;
import com.example.lesson1.dto.ResponseDTO;
import com.example.lesson1.entity.PlayerEntity;
import com.example.lesson1.entity.PlayerStatus;
import com.example.lesson1.entity.Weapon;
import com.example.lesson1.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


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

    @PostMapping("/player/create")
    public ResponseEntity<String> create(@RequestBody PlayerEntity playerEntity) {
        service.create(playerEntity);
        return new ResponseEntity<>("Запись успешно добавлена", HttpStatus.CREATED);
    }

    @PostMapping("/player/update/{id}")
    public ResponseEntity<String> update(@RequestBody PlayerEntity playerEntity, @PathVariable("id") Long id) {
        service.update(playerEntity, id);
        return new ResponseEntity<>("Запись успешно обновлена", HttpStatus.CREATED);
    }

    @PostMapping("/player/{id_player}/take_weapon/{id_weapon}")
    public ResponseEntity<String> playerTakesWeapon(@PathVariable("id_player") Long idPlayer, @PathVariable("id_weapon") Long idWeapon) {
        service.playerTakesWeapon(idPlayer, idWeapon);
        return new ResponseEntity<>("Запись успешно обновлена", HttpStatus.CREATED);
    }
}
