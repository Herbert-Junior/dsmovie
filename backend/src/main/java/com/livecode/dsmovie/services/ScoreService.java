package com.livecode.dsmovie.services;

import com.livecode.dsmovie.dto.MovieDTO;
import com.livecode.dsmovie.dto.ScoreDTO;
import com.livecode.dsmovie.entities.Movie;
import com.livecode.dsmovie.entities.Score;
import com.livecode.dsmovie.entities.User;
import com.livecode.dsmovie.repositories.MovieRepository;
import com.livecode.dsmovie.repositories.ScoreRepository;
import com.livecode.dsmovie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO dto){

        User user = userRepository.findByEmail(dto.getEmail());
        if(user == null){
            user = new User();
            user.setEmail(dto.getEmail());
            user = userRepository.saveAndFlush(user);
        }

        Movie movie = movieRepository.findById(dto.getMovieId()).get();

        Score score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(dto.getScore());

        score = scoreRepository.saveAndFlush(score);

        double sum = 0.0;
        for( Score s : movie.getScores()){

            sum = sum + s.getValue();
        }
        int tamanho = movie.getScores().size();
        double avg = sum / tamanho;

        movie.setScore(avg);
        movie.setCount(tamanho);

        movie = movieRepository.save(movie);

        return new MovieDTO(movie);


    }
}
