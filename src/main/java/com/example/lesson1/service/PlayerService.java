package com.example.lesson1.service;

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

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerEntity getPlayerById(Long id) {
        log.info("getPlayerById.in - searching player {}", id);
        Optional<PlayerEntity> byId = playerRepository.findById(id);
        if (byId.isPresent()) {
            log.info("getPlayerById.out - found player {}", id);
            return byId.get();
        } else {
            log.error("getPlayerById.out - not found player {}", id);
            throw new EntityNotFoundException("Entity with given id not found!");
        }
    }

    public ResponseEntity<ResponseDTO> findPlayersByStatus(PlayerStatus status) {
        try {
            List<PlayerDTO> collect = playerRepository.findAll().stream()
                    .filter(playerEntity -> status.isVal() != playerEntity.isTerminated())
                    .map(this::map)
                    .collect(Collectors.toList());
            //сделать оптимальный запрос
            return ResponseEntity.ok(ResponseDTO.builder().result(collect).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //todo реализовать метод сохранения сущности

    public void create(PlayerDTO playerDTO) {
        playerRepository.save(
                map(playerDTO)
        );
    }

    //todo реализовать метод обновления сущности


    public PlayerDTO map(PlayerEntity playerEntity) {
        return PlayerDTO.builder()
                .nickname(playerEntity.getNickName())
                .id(playerEntity.getId())
                .description(playerEntity.getProfileInfo()).build();
    }

    public PlayerEntity map(PlayerDTO playerDTO) {
        return PlayerEntity.builder()
                .nickName(playerDTO.getNickname())
                .id(playerDTO.getId())
                .profileInfo(playerDTO.getDescription()).build();
    }

}
