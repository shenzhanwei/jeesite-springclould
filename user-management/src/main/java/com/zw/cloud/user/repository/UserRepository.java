package com.zw.cloud.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zw.cloud.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
