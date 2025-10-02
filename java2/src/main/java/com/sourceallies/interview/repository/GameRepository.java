package com.sourceallies.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourceallies.interview.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
