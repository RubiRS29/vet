package com.vet.web.app.repository;

import com.vet.web.app.entity.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdopterRepository extends JpaRepository<Adopter, Long> {


    @Query(value = "SELECT COUNT(id) FROM adopt WHERE email = :email",  nativeQuery = true)
    Long countAdopter(@Param("email") String email);

    long countByEmail(String email);
}
