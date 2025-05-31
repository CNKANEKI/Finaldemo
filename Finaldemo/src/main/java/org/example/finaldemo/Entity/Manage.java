package org.example.finaldemo.Entity;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class Manage
{
    @Id
    private long sid;
    @Column(name = "name")
    private String name;
    @Column(name = "ssex")
    private String ssex;
    @Column(name = "sclass")
    private String sclass;
}