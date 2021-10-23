package com.example.lesson1.data;

import com.example.lesson1.dto.PlayerDTO;
import com.example.lesson1.entity.PlayerEntity;
import com.example.lesson1.entity.PlayerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    @Query(value = "SELECT p FROM PlayerEntity  p WHERE p.terminated <> :status")
    List<PlayerEntity> findAllPlayersFilterByStatus(boolean status);


    List<PlayerEntity> findByTerminatedNot(boolean status);
}
