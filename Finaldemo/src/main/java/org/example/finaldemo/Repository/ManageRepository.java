package org.example.finaldemo.Repository;
import org.example.finaldemo.Entity.Manage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManageRepository extends JpaRepository<Manage, Long> {
    List<Manage> findAllByName(String name);
}