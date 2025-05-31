package org.example.finaldemo.Repository;
import org.example.finaldemo.Entity.Score;
import org.example.finaldemo.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public void saveScore(Score score) {
        scoreRepository.save(score);
    }

    @Override
    public List<Score> getScoreBysid(long sid){

        return scoreRepository.findAllBySid(sid);
    }

    @Override
    public Score getScoreBySidAndCourse(long sid, String course){
        return scoreRepository.findBySidAndCourse(sid, course);
    }

    @Override
    public void deleteScoreBySidAndCourse(long sid, String course){
        this.scoreRepository.deleteBySidAndCourse(sid, course);
    }

}
