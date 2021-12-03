package com.secretsanta.repo;

import com.secretsanta.entity.Teammate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecretSantaRepository extends JpaRepository<Teammate, Integer> {
    List<Teammate> findAll(Integer year);
}
