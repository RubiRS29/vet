package com.vet.web.app.repository;

import com.vet.web.app.entity.Refuge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefugeRepository extends JpaRepository<Refuge, Long> {

    @Query(value = "SELECT COUNT(*) FROM refuge WHERE email = :email",  nativeQuery = true)
    Long countRefuge(@Param("email") String email);



}
