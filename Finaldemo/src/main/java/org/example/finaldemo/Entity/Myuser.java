package org.example.finaldemo.Entity;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "myuser")
public class Myuser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name="role")
    private  String role;
}
