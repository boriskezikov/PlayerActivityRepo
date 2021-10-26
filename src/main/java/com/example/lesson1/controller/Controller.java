package com.example.lesson1.controller;

import com.example.lesson1.dto.PlayerDTO;
import com.example.lesson1.dto.ResponseDTO;
import com.example.lesson1.entity.PlayerEntity;
import com.example.lesson1.entity.PlayerStatus;
import com.example.lesson1.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.time.LocalDate;


@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Controller {

    private final PlayerService service;

    @GetMapping("/player/{id}")
    public PlayerDTO loadPlayer(@PathVariable("id") Long id) {
        return service.getPlayerById(id);
    }
    
    @GetMapping("/player")
    public ResponseEntity<ResponseDTO> loadPlayers(@RequestParam("status") PlayerStatus status) {
        return service.findPlayersByStatus(status);
    }

    @Transactional
    @PutMapping("/player/update")
    public @ResponseBody ResponseEntity<String> updatePlayer(@RequestBody PlayerEntity playerEntity) {

        service.update(playerEntity);
        return new ResponseEntity<>("Запись успешно обновлена", HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/player/add")
    public ResponseEntity<String> createPlayer(@RequestBody PlayerEntity playerEntity) {

        service.createPlayer(playerEntity);
        return new ResponseEntity<>("Запись успешно добавлена", HttpStatus.CREATED);

    }

    @GetMapping("/player/find")
    public ResponseEntity<String> findMatches(@RequestParam String name) {

        return new ResponseEntity<>("A match with the part of name \"" + name + "\" was "
                + service.nickNameContains(name) + "%"
                , HttpStatus.OK);
    }

    @GetMapping("/player/terminated")
    public ResponseEntity<ResponseDTO> getTerminatedByDate(@RequestBody LocalDate localDate) {

//       Calendar calendar = new Calendar.Builder().setFields(Calendar.YEAR, dateDTO.getYear()
//                                        , Calendar.MONTH, dateDTO.getMonth() - 1
//                                        , Calendar.DAY_OF_MONTH, dateDTO.getDay()).build();
//
//        Date date = new Date(calendar.getTimeInMillis());

        return service.getTerminatedByDate(localDate);
    }

}
