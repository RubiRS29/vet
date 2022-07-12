package com.vet.web.app.repository;

import com.vet.web.app.entity.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {

    long countByEmail(String email);

    Optional<Veterinarian> findByEmail(String email);
}
