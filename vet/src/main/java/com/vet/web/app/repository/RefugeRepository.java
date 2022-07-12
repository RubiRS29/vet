package com.vet.web.app.repository;

import com.vet.web.app.entity.Refuge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefugeRepository extends JpaRepository<Refuge, Long> {

    long countByEmail(String email);

    Optional<Refuge> findByEmail(String email);

}
