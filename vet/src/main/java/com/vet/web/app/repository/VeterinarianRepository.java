package com.vet.web.app.repository;

import com.vet.web.app.entity.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {

    @Query(value = "SELECT COUNT(*) FROM veterinarian WHERE email = :email",  nativeQuery = true)
    Long countVeterinarian(@Param("email") String email);
}
