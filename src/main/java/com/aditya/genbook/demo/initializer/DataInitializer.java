package com.aditya.genbook.demo.initializer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aditya.genbook.demo.entity.Owner;
import com.aditya.genbook.demo.entity.Pet;
import com.aditya.genbook.demo.repository.OwnerRepository;
import com.aditya.genbook.demo.repository.PetRepository;

@Component
public class DataInitializer implements CommandLineRunner
{
    @Autowired
    private OwnerRepository mOwnerRepository;

    @Autowired
    private PetRepository mPetRepository;

    @Override
    public void run( String... strings ) throws Exception
    {
        Owner lJohn = new Owner( "John", "Fassman", "NewYork" );
        lJohn = this.mOwnerRepository.save( lJohn );
        mPetRepository.save( new Pet( "Lucy", new Date( System.currentTimeMillis() - 10000000000L ), lJohn ) );
        mPetRepository.save( new Pet( "Akka", new Date( System.currentTimeMillis() - 20000000000L ), lJohn ) );

        Owner lBob = new Owner( "Bob", "Jr", "London" );
        lBob = this.mOwnerRepository.save( lBob );
        mPetRepository.save( new Pet( "Daisy", new Date( System.currentTimeMillis() - 30000000000L ), lBob ) );
        mPetRepository.save( new Pet( "Benji", new Date( System.currentTimeMillis() - 40000000000L ), lBob ) );

        Owner lJack = new Owner( "Jack", "Newman", "Sydney" );
        lJack = this.mOwnerRepository.save( lJack );

    }
}