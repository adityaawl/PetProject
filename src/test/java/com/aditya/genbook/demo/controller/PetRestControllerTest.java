package com.aditya.genbook.demo.controller;

import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.aditya.genbook.demo.PetProjectApplication;
import com.aditya.genbook.demo.dto.OwnerDto;
import com.aditya.genbook.demo.dto.PetDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith( SpringRunner.class )
@SpringBootTest( webEnvironment = WebEnvironment.MOCK, classes = PetProjectApplication.class )
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class PetRestControllerTest
{
    @Autowired
    private MockMvc mvc;

    // DataInitializer loads dummy data which we are using for this unit test.
    // new Owner( "John", "Fassman", "NewYork" );
    // new Pet( "Lucy", new Date( System.currentTimeMillis() - 10000000000L ), 1 );
    // new Pet( "Akka", new Date( System.currentTimeMillis() - 20000000000L ), 1 );
    // new Owner( "Bob", "Jr", "London" );
    // new Pet( "Daisy", new Date( System.currentTimeMillis() - 30000000000L ), 2 );
    // new Pet( "Benji", new Date( System.currentTimeMillis() - 40000000000L ), 2 );
    // new Owner( "Jack", "Newman", "Sydney" );

    @Test
    public void allOwnerTest() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders.get( "/api/owners" ).contentType( MediaType.APPLICATION_JSON ) )
           .andDo( MockMvcResultHandlers.print() )
           .andExpect( MockMvcResultMatchers.status().isOk() )
           .andExpect( MockMvcResultMatchers.content().contentTypeCompatibleWith( MediaType.APPLICATION_JSON ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$", Matchers.hasSize( 3 ) ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$[0].firstName" ).value( "John" ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$[2].city" ).value( "Sydney" ) );
    }

    @Test
    public void allPetTest() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders.get( "/api/pets" ).contentType( MediaType.APPLICATION_JSON ) )
           .andDo( MockMvcResultHandlers.print() )
           .andExpect( MockMvcResultMatchers.status().isOk() )
           .andExpect( MockMvcResultMatchers.content().contentTypeCompatibleWith( MediaType.APPLICATION_JSON ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$", Matchers.hasSize( 4 ) ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$[1].name" ).value( "Akka" ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$[1].ownerId" ).value( "1" ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$[3].name" ).value( "Benji" ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$[3].ownerId" ).value( "2" ) );
    }

    @Test( )
    public void createOwnerTest() throws Exception
    {
        OwnerDto lDto = new OwnerDto();
        lDto.setFirstName( "FirstName" );
        lDto.setLastName( "LastName" );
        lDto.setCity( "City" );

        String lDtoJsonString = new ObjectMapper().writeValueAsString( lDto );

        mvc.perform( MockMvcRequestBuilders.post( "/api/create/owner" )
                                           .contentType( MediaType.APPLICATION_JSON )
                                           .accept( MediaType.APPLICATION_JSON )
                                           .content( lDtoJsonString ) )
           .andDo( MockMvcResultHandlers.print() )
           .andExpect( MockMvcResultMatchers.status().isOk() );

        mvc.perform( MockMvcRequestBuilders.get( "/api/owners" ).contentType( MediaType.APPLICATION_JSON ) )
           .andDo( MockMvcResultHandlers.print() )
           .andExpect( MockMvcResultMatchers.status().isOk() )
           .andExpect( MockMvcResultMatchers.content().contentTypeCompatibleWith( MediaType.APPLICATION_JSON ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$", Matchers.hasSize( 4 ) ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$[3].firstName" ).value( "FirstName" ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$[3].city" ).value( "City" ) );
    }

    @Test( )
    public void createPetTest() throws Exception
    {
        PetDto lDto = new PetDto();
        lDto.setName( "Pet Name" );
        lDto.setBirthDate( new Date() );
        lDto.setOwnerId( 4L );

        String lDtoJsonString = new ObjectMapper().writeValueAsString( lDto );

        mvc.perform( MockMvcRequestBuilders.post( "/api/create/pet" )
                                           .contentType( MediaType.APPLICATION_JSON )
                                           .accept( MediaType.APPLICATION_JSON )
                                           .content( lDtoJsonString ) )
           .andDo( MockMvcResultHandlers.print() )
           .andExpect( MockMvcResultMatchers.status().isOk() );

        mvc.perform( MockMvcRequestBuilders.get( "/api/pets" ).contentType( MediaType.APPLICATION_JSON ) )
           .andDo( MockMvcResultHandlers.print() )
           .andExpect( MockMvcResultMatchers.status().isOk() )
           .andExpect( MockMvcResultMatchers.content().contentTypeCompatibleWith( MediaType.APPLICATION_JSON ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$", Matchers.hasSize( 5 ) ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$[4].name" ).value( "Pet Name" ) )
           .andExpect( MockMvcResultMatchers.jsonPath( "$[4].ownerId" ).value( "4" ) );

    }
}