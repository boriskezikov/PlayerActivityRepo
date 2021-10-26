package com.example.lesson1.service;

import com.example.lesson1.MyException.MyExceptionNotFound;
import com.example.lesson1.data.PlayerRepository;
import com.example.lesson1.dto.PlayerDTO;
import com.example.lesson1.dto.ResponseDTO;
import com.example.lesson1.entity.PlayerEntity;
import com.example.lesson1.entity.PlayerStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerDTO getPlayerById(Long id) {
        log.info("getPlayerById.in - searching player {}", id);
        Optional<PlayerEntity> byId = playerRepository.findById(id);
        if (byId.isPresent()) {
            log.info("getPlayerById.out - found player {}", id);
            return map(byId.get());
        } else {
            log.error("getPlayerById.out - not found player {}", id);
            throw new MyExceptionNotFound(id);
        }
    }

    public ResponseEntity<ResponseDTO> findPlayersByStatus(PlayerStatus status) {
        try {
           List<PlayerDTO> collect = playerRepository.findByTerminated(status.isVal()).stream()
                    .map(this::map)
                    .collect(Collectors.toList());

            //todo сделать оптимальный запрос
            return ResponseEntity.ok(ResponseDTO.builder().result(collect).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public void createPlayer(PlayerEntity playerEntity) {
        playerRepository.save(playerEntity);
    }

    public void update(PlayerEntity playerEntity) {
        playerRepository.save(playerEntity);
    }

    public int nickNameContains(String nickName) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < nickName.length(); i++) {

            stringBuilder.append(".*");
            stringBuilder.append(nickName.charAt(i));

        }
        stringBuilder.append(".*");

        List<PlayerEntity> playerEntities = playerRepository.findAll();

        Predicate<PlayerEntity> predicate;
        predicate = ((pe) -> pe.getNickName().matches(stringBuilder.toString()));
        playerEntities = playerEntities.parallelStream().filter(predicate).collect(Collectors.toList());

        int sizeOfTable = playerRepository.countAllBy().intValue();

        return (playerEntities.size() * 100 / sizeOfTable);
    } 

    public ResponseEntity<ResponseDTO> getTerminatedByDate(LocalDate date) {
        List<PlayerEntity> playerEntities = playerRepository
                .findByTerminatedAndStatusChangedGreaterThan(true, date);

        List<PlayerDTO> playerDTOS = playerEntities.stream().map(this::map).collect(Collectors.toList());
        return new ResponseEntity<>(ResponseDTO.builder().result(playerDTOS).build(), HttpStatus.OK);
    }

    public PlayerDTO map(PlayerEntity playerEntity) {
        return PlayerDTO.builder()
                .nickname(playerEntity.getNickName())
                .id(playerEntity.getId())
                .profileInfo(playerEntity.getProfileInfo())
                .accountCreated(playerEntity.getAccountCreated())
                .statusChanged(playerEntity.getStatusChanged())
                .build();
    }

}
