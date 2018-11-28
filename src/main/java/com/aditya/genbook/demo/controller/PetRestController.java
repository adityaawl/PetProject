package com.aditya.genbook.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.aditya.genbook.demo.dto.OwnerDto;
import com.aditya.genbook.demo.dto.PetDto;
import com.aditya.genbook.demo.entity.Owner;
import com.aditya.genbook.demo.entity.Pet;
import com.aditya.genbook.demo.repository.OwnerRepository;
import com.aditya.genbook.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "api" )
public class PetRestController
{

    @Autowired
    private OwnerRepository mOwnerRepository;

    @Autowired
    private PetRepository mPetRepository;

    @GetMapping( value = "/owners", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<OwnerDto> getAllOwners()
    {
        List<Owner> lOwners = this.mOwnerRepository.findAll();
        List<OwnerDto> lOwnerDtos = lOwners.stream().map( lOwner -> {
            OwnerDto lDto = (OwnerDto) lOwner.toData();
            lDto.setPetIds( mPetRepository.findPetIdsByOwner( lDto.getId() ) );
            return lDto;
        } ).collect( Collectors.toList() );
        return lOwnerDtos;
    }

    @PostMapping( "/create/owner" )
    OwnerDto createOwner( @RequestBody OwnerDto owner )
    {
        Owner lOwner = new Owner();
        lOwner.fromData( owner );
        lOwner = mOwnerRepository.save( lOwner );

        OwnerDto lOwnerDto = (OwnerDto) lOwner.toData();

        return lOwnerDto;
    }

    @GetMapping( value = "/pets", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<PetDto> getAllPets()
    {
        List<Pet> lPets = this.mPetRepository.findAll();
        List<PetDto> lPetDtos = lPets.stream().map( lPet -> {
            PetDto lDto = (PetDto) lPet.toData();
            lDto.setOwnerId( lPet.getOwner().getId() );
            return lDto;
        } ).collect( Collectors.toList() );
        return lPetDtos;
    }

    @PostMapping( "/create/pet" )
    PetDto createPet( @RequestBody PetDto pet )
    {
        Pet lPet = new Pet();
        lPet.fromData( pet );
        lPet.setOwner( mOwnerRepository.getOne( pet.getOwnerId() ) );
        lPet = mPetRepository.save( lPet );

        PetDto lPetDto = (PetDto) lPet.toData();
        lPetDto.setOwnerId( pet.getOwnerId() );

        return lPetDto;
    }
}
