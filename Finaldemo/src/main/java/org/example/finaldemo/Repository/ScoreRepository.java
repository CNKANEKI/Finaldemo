package org.example.finaldemo.Repository;
import org.example.finaldemo.Entity.Score;
import org.example.finaldemo.Entity.ScoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, ScoreId> {
    @Query("SELECT s FROM Score s WHERE s.id.sid = ?1")
    List<Score> findAllBySid(long sid);
    @Query("SELECT s FROM Score s WHERE s.id.sid = ?1 AND s.id.course = ?2")
    Score findBySidAndCourse(long sid, String course);
    @Query("SELECT s FROM Score s WHERE s.id.sid = ?1 AND s.id.course = ?2")
    void deleteBySidAndCourse(long sid, String course);
}
