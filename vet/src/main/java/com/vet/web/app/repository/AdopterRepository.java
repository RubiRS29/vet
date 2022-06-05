package com.vet.web.app.repository;

import com.vet.web.app.entity.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdopterRepository extends JpaRepository<Adopter, Long> {
}
