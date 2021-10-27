package com.example.lesson1.data;

import com.example.lesson1.dto.PlayerDTO;
import com.example.lesson1.entity.PlayerEntity;
import com.example.lesson1.entity.PlayerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    @Query(value = "SELECT p FROM PlayerEntity  p WHERE p.terminated <> :status")
    List<PlayerEntity> findAllPlayersFilterByStatus(boolean status);

    @Query(value = "SELECT p FROM PlayerEntity  p ORDER BY p.nickName DESC")
    List<PlayerEntity> findAllOrderByNickNameDesc();

    @Query(value = "SELECT p FROM PlayerEntity  p ORDER BY p.id DESC")
    List<PlayerEntity> findAllOrderByIDDesc();

    @Query(value = "SELECT p FROM PlayerEntity  p ORDER BY p.profileInfo DESC")
    List<PlayerEntity> findAllOrderByProfileInfoDesc();

    PlayerEntity findByNickName(String nickName);


    @Query(value = "SELECT (COUNT(*)*100/(SELECT COUNT(*)FROM player_entity))  FROM player_entity WHERE player_entity.nick_name LIKE '%' || :nickName || '%'", nativeQuery = true)
    Float countPercentPlayersWithSimilarNickName(String nickName);

    List<PlayerEntity> findAllByCreatedAtBetween(Date from, Date to);
//
//    List<PlayerEntity> findAllByCreatedAtBetween(Date from, Date to);
//
//    List<PlayerEntity> findAllByUpdatedAtBetween(Date from, Date to);

}
