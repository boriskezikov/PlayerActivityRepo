package com.example.lesson1.service;

import com.example.lesson1.data.PlayerRepository;
import com.example.lesson1.dto.PlayerDTO;
import com.example.lesson1.dto.ResponseDTO;
import com.example.lesson1.entity.PlayerEntity;
import com.example.lesson1.entity.PlayerStatus;
import com.example.lesson1.entity.Weapon;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.*;
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
            var collect = playerRepository
                    .findAllPlayersFilterByStatus(status.isVal())
                    .stream()
                    .map(this::map)
                    .collect(Collectors.toList());
            //todo сделать оптимальный запрос
            return ResponseEntity.ok(ResponseDTO.builder().result(collect).build());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public Float countPercentPlayersWithSimilarNickName(String name) {
        return playerRepository.countPercentPlayersWithSimilarNickName(name);
    }

    public void create(PlayerDTO playerDTO) {
        playerRepository.save(
                map(playerDTO)
        );
    }

    public PlayerDTO update(PlayerDTO playerDTO) {
        log.info("update.in - searching player {}", playerDTO.getId());
        var optionalPlayer = playerRepository.findById(playerDTO.getId());
        if (optionalPlayer.isPresent()) {
            log.info("update.get - player was found.");

            var player = optionalPlayer.get();
            player.setNickName(playerDTO.getNickname());
            player.setProfileInfo(playerDTO.getDescription());
            playerRepository.save(player);

            log.info("player with id {} was updated", player.getId());
            return playerDTO;
        } else {
            log.error("update.get - player was not found");
            throw new EntityNotFoundException("Entity with given id not found!");
        }
    }


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

    public List<PlayerEntity> findPlayersThatCreatedBetweenDates(Date from, Date to) {
        return playerRepository.findAllByCreatedAtBetween(from, to);
    }

//    @PostConstruct
//    public void test() {
//        PlayerEntity saved = playerRepository.save(
//                PlayerEntity.builder()
//                        .nickName("test")
//                        .profileInfo("test")
//                        .terminated(false)
//                        .weapons(Collections.singletonList(
//                                Weapon.builder()
//                                        .damage(10)
//                                        .range(199)
//                                        .detailedName("test dt")
//                                        .name("test weapon name")
//                                        .broken(false).build()
//                        ))
//                        .build()
//        );
//        System.out.println(saved);
//    }
}
