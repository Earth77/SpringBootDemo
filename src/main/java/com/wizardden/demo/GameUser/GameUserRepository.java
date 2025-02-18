package com.wizardden.demo.GameUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameUserRepository extends JpaRepository<GameUser, Long> {
    GameUser findByEmail(String email);
}
