package com.vet.web.app.repository;

import com.vet.web.app.entity.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdopterRepository extends JpaRepository<Adopter, Long> {

    long countByEmail(String email);

    Optional<Adopter> findByEmail(String email);
}
