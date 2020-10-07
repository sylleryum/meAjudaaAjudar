package com.sylleryum.meajudaaajudar.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiUserRepository extends CrudRepository<ApiUserEntity, Long> {

    Optional<ApiUserEntity> findByUsername(String username);
    Optional<ApiUserEntity> findByToken(String token);
}
