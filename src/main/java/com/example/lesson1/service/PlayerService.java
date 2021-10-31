package com.example.lesson1.service;

import com.example.lesson1.data.PlayerRepository;
import com.example.lesson1.data.WeaponRepository;
import com.example.lesson1.dto.PlayerDTO;
import com.example.lesson1.dto.ResponseDTO;
import com.example.lesson1.entity.PlayerEntity;
import com.example.lesson1.entity.PlayerStatus;
import com.example.lesson1.entity.Weapon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final WeaponRepository weaponRepository;

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
                    .filter(playerEntity -> status.isVal() != playerEntity.getTerminated())
                    .map(this::map)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(ResponseDTO.builder().result(collect).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public void create(PlayerEntity playerEntity) {
        playerEntity.setAccountCreated(LocalDateTime.now());
        playerRepository.save(playerEntity);
    }

    public void update(PlayerEntity playerEntity, Long id) {

        Optional<PlayerEntity> player = playerRepository.findById(id);

        if (!player.isPresent()) {
            throw new EntityExistsException("Player with this ID doesn't exist!");
        }

        PlayerEntity oldPlayer = player.get();

        String newNickName = playerEntity.getNickName();
        String newDescription = playerEntity.getProfileInfo();
        Boolean newStatus = playerEntity.getTerminated();
        LocalDateTime AccountUpdated = LocalDateTime.now();

        oldPlayer.setId(id);

        if (newNickName != null) { oldPlayer.setNickName(newNickName); }

        if (newDescription != null) { oldPlayer.setProfileInfo(newDescription); }

        if (newStatus != null) { oldPlayer.setTerminated(newStatus); }

        oldPlayer.setAccountUpdated(AccountUpdated);

        playerRepository.save(oldPlayer);
    }

    public void playerTakesWeapon(Long idPlayer, Long idWeapon) {

        Optional<PlayerEntity> playerEntity = playerRepository.findById(idPlayer);

        if (!playerEntity.isPresent()) {
            throw new EntityExistsException("Player with this ID doesn't exist!");
        }

        PlayerEntity player = playerEntity.get();


        Optional<Weapon> selectedWeapon = weaponRepository.findById(idWeapon);

        if (!selectedWeapon.isPresent()) {
            throw new EntityExistsException("Weapon with this ID doesn't exist!");
        }

        Weapon weapon = selectedWeapon.get();

        weapon.setId(idWeapon);

        if (weapon.getPlayerEntity() == null) {
            weapon.setPlayerEntity(player);
        }
        else {
            throw new EntityExistsException("Weapon with this ID is already taken by other player");
        }

        weaponRepository.save(weapon);
    }

    public PlayerDTO map(PlayerEntity playerEntity) {
        return PlayerDTO.builder()
                .nickname(playerEntity.getNickName())
                .id(playerEntity.getId())
                .description(playerEntity.getProfileInfo())
                .accountCreated(playerEntity.getAccountCreated())
                .accountUpdated(playerEntity.getAccountUpdated()).build();
    }


}
