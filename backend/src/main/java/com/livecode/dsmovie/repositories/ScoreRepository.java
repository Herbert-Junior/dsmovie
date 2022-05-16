package com.livecode.dsmovie.repositories;


import com.livecode.dsmovie.entities.Score;
import com.livecode.dsmovie.entities.ScorePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {
}
