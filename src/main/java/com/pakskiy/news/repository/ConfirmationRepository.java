package com.pakskiy.news.repository;

import com.pakskiy.news.model.ConfirmationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationRepository extends JpaRepository<ConfirmationCodeEntity, Long> {

    @Query(nativeQuery = true, value = "" +
            "select count(id) from confirmation_code " +
            "where user_id = :userId and code = :code")
    int findCodeByUid(Long userId, String code);

    @Query(value = "FROM ConfirmationCodeEntity CC WHERE CC.userId =:userId ORDER BY CC.createdAt DESC LIMIT 1")
    Optional<ConfirmationCodeEntity> findLastByUserId(Long userId);

    @Query(value = "SELECT COUNT(CC.id)>0 FROM ConfirmationCodeEntity CC WHERE CC.userId =:userId AND CC.code =:code AND CC.status=0")
    boolean isValidCode(Long userId, String code);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "update confirmation_code set status = 1, session_id = :sessionId where user_id = :userId and code = :code")
    void changeStatus(Long userId, String code, String sessionId);
}
