package org.example.finaldemo.Service;
import org.example.finaldemo.Entity.Score;

import java.util.List;

public interface ScoreService {

    // 新增或更新学生成绩
    void saveScore(Score score);

    // 获取学生成绩
    List<Score> getScoreBysid(long sid);

    Score getScoreBySidAndCourse(long sid, String course);

    void deleteScoreBySidAndCourse(long sid, String course);

}
