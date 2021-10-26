package com.example.lesson1.data;

import com.example.lesson1.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    List<PlayerEntity> findByTerminated(Boolean status);
    List<PlayerEntity> findByNickNameStartingWith(String nickName);
    List<PlayerEntity> findByNickNameLike(String nickName);
    List<PlayerEntity> findByTerminatedAndStatusChangedGreaterThan(boolean terminated, LocalDate date);
    Long countAllBy();
}
