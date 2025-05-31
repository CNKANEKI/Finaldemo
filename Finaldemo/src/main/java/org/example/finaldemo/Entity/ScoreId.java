package org.example.finaldemo.Entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ScoreId implements Serializable {

    private long sid;            // 学生ID
    private String course;       // 课程名称

    // 必须提供无参构造
    public ScoreId() {}

    // 添加带参数的构造函数（可选）
    public ScoreId(long sid, String course) {
        this.sid = sid;
        this.course = course;
    }

    // Getters and Setters
    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // 重写equals方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                  // 同一对象
        if (o == null || getClass() != o.getClass()) return false; // 空或不同类

        ScoreId scoreId = (ScoreId) o;
        return sid == scoreId.sid && Objects.equals(course, scoreId.course); // 进行字段比较
    }

    // 重写hashCode方法
    @Override
    public int hashCode() {
        return Objects.hash(sid, course); // 基于sid和course生成哈希值
    }
}
