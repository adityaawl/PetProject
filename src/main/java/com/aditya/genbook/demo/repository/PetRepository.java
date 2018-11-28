package com.aditya.genbook.demo.repository;

import java.util.List;

import com.aditya.genbook.demo.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>
{
    @Query( "SELECT id FROM Pet tbl0 WHERE tbl0.owner.id = ?1" )
    List<Long> findPetIdsByOwner( Long pOwnerId );
}