package org.example.finaldemo.Entity;

import lombok.Data;
import jakarta.persistence.*;


@Data
@Entity
public class Score
{
    @EmbeddedId
    private ScoreId id;
    @Column(name = "grade")
    private int grade;
}
