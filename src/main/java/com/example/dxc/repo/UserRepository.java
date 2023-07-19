package com.example.dxc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dxc.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUserEmail(String userEmail);
}

