package com.example.lesson1.controller;

import com.example.lesson1.dto.PlayerDTO;
import com.example.lesson1.dto.ResponseDTO;
import com.example.lesson1.entity.PlayerEntity;
import com.example.lesson1.entity.PlayerStatus;
import com.example.lesson1.service.PlayerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Controller {

    private final PlayerService service;

    @GetMapping("/player/similar/{name}")
    public Float countPercentPlayersWithSimilarNickName(@PathVariable("name") String name) {
        return service.countPercentPlayersWithSimilarNickName(name);
    }

    @GetMapping("/player/created/")
    public List<PlayerEntity> findPlayersThatCreatedBetweenDates(@RequestParam("start") Date from,
                                                                  @RequestParam("end") Date to) {
     return service.findPlayersThatCreatedBetweenDates(from, to);
    }

    @GetMapping("/player/{id}")
    public PlayerEntity loadPlayer(@PathVariable("id") Long id) {
        return service.getPlayerById(id);
    }

    @GetMapping("/player")
    public ResponseEntity<ResponseDTO> loadPlayers(@RequestParam("status") PlayerStatus status) {
        return service.findPlayersByStatus(status);
    }

    @PostMapping("/player")
    public void create(@RequestBody PlayerDTO playerDTO) {
        service.create(playerDTO);
    }

    @PutMapping("/player")
    public PlayerDTO update(@RequestBody PlayerDTO playerDTO) {
        return service.update(playerDTO);
    }

}
