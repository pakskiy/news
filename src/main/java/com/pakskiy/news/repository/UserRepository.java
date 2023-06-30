package com.pakskiy.news.repository;

import com.pakskiy.news.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "FROM UserEntity WHERE phone =:phone AND userStatus='ACTIVE'")
    Optional<UserEntity> findByPhone(String phone);

    @Query(nativeQuery = true, value = "SELECT u.id, u.email, u.password, u.phone, u.status, u.role_name, u.created_at, u.updated_at " +
            "FROM users u LEFT JOIN confirmation_code cc ON cc.user_id = u.id " +
            "WHERE u.email = :email AND u.status = 'ACTIVE' AND cc.session_id = :sessionId")
    Optional<UserEntity> getUserForLogin(String email, String sessionId);

    Optional<UserEntity> findByEmail(String email);
}
