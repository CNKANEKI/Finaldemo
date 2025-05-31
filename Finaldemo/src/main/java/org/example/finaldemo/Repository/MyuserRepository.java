package org.example.finaldemo.Repository;
import org.example.finaldemo.Entity.Myuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyuserRepository extends JpaRepository<Myuser, Long> {
    List<Myuser> findAllByUsername(String name);
    Myuser findByUsername(String name);
}