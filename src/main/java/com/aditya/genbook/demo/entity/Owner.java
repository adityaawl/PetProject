package com.aditya.genbook.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.aditya.genbook.demo.common.EntityValueObject;
import com.aditya.genbook.demo.dto.OwnerDto;

@Entity
@Table( name = "OWNER" )
public class Owner implements Serializable
{
    private static final long serialVersionUID = -6837813264428611802L;

    public Owner()
    {
    }

    public Owner( @Size( message = "INVALID_SIZE", min = 0, max = 64 ) @NotNull( message = "NOT_NULL" ) String firstName,
        @Size( message = "INVALID_SIZE", min = 0, max = 64 ) @NotNull( message = "NOT_NULL" ) String lastName,
        @Size( message = "INVALID_SIZE", min = 0, max = 64 ) String city )
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    private Long id = null;

    /**
     * Returns the id value. 
     * @return id The primary key of entity.
     */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID", nullable = false, unique = true, updatable = false, columnDefinition = "NUMBER" )
    public Long getId()
    {
        return this.id;
    }

    /**
     * Sets the Id value. 
     * @param id The primary key of entity.
     */
    public void setId( Long id )
    {

        this.id = id;
    }

    /**
     * Returns the date on which this entity was created.
     */
    private Date createdOn;

    /**
     * Returns the date on which this entity was created. 
     * @return creationDate The date on which this entity was created.
     */
    @Column( name = "CREATED_ON", nullable = false, updatable = false )
    public Date getCreatedOn()
    {
        return this.createdOn;
    }

    /**
     * Sets the creation date of the entity. 
     * @param newValue The creation date of the entity.
     */

    public void setCreatedOn( Date newValue )
    {
        this.createdOn = newValue;
    }

    /**
    * The last modified date of this entity.
    */
    private Date modifiedOn;

    /**
     * Returns the last modified date for the entity. 
     * @return modifyDate The last modified date for the entity.
     */
    @Column( name = "MODIFIED_ON" )
    public Date getModifiedOn()
    {
        return this.modifiedOn;
    }

    /**
     * Sets the last modified date for the entity. 
     * @param newValue The last modified date for the entity.
     */

    public void setModifiedOn( Date newValue )
    {
        this.modifiedOn = newValue;
    }

    /**
    * Stores the current version number.
    */
    private int version;

    /**
     * Returns the version property. 
     * @return version The version property.
     */
    @Version
    @Column( name = "VERSION", precision = 10, scale = 0 )
    public int getVersion()
    {
        return version;
    }

    /**
     * Sets the version property. 
     * @param ver The version property.
     */
    public void setVersion( int ver )
    {
        version = ver;

    }

    @Size( message = "INVALID_SIZE", min = 0, max = 64 )
    @NotNull( message = "NOT_NULL" )
    private String firstName;

    /**
     * @return the mFirstName
     */
    @Column( name = "FIRST_NAME", nullable = true, length = 64 )
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param mFirstName the mFirstName to set
     */
    public void setFirstName( String mFirstName )
    {
        this.firstName = mFirstName;
    }

    @Size( message = "INVALID_SIZE", min = 0, max = 64 )
    @NotNull( message = "NOT_NULL" )
    private String lastName;

    /**
     * @return the mLastName
     */
    @Column( name = "LAST_NAME", nullable = true, length = 64 )
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param mLastName the mLastName to set
     */
    public void setLastName( String mLastName )
    {
        this.lastName = mLastName;
    }

    @Size( message = "INVALID_SIZE", min = 0, max = 64 )
    private String city;

    /**
     * @return the mCity
     */
    @Column( name = "CITY", nullable = true, length = 64 )
    public String getCity()
    {
        return city;
    }

    /**
     * @param mCity the mCity to set
     */
    public void setCity( String mCity )
    {
        this.city = mCity;
    }

    private Collection<Pet> pets = new ArrayList<>();

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner", orphanRemoval = true )
    public Collection<Pet> getPets()
    {
        return pets;
    }

    public void setPets( Collection<Pet> pets )
    {
        pets.addAll( pets );
        pets.stream().forEach( pet -> pet.setOwner( this ) );
    }

    public void addPet( Pet pet )
    {
        pets.add( pet );
        pet.setOwner( this );
    }

    public void removePet( Pet pet )
    {
        pet.setOwner( null );
        this.pets.remove( pet );
    }

    /**
     * Returns <code>true</code> if the argument is an Owner instance and all identifiers for this entity
     * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
     */
    /**
     * Implementation of equals method.
     * @param object The object to compare with.
     * @return boolean true if equals else false.
    */
    public boolean equals( Object object )
    {
        if( this == object )
        {
            return true;
        }
        if( !( object instanceof Owner ) )
        {
            return false;
        }
        final Owner lOtherOwner = (Owner) object;

        if( this.getId() == null || lOtherOwner.getId() == null || !this.getId().equals( lOtherOwner.getId() ) )
        {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code based on this entity's identifiers.
     */
    /**
     * Returns the hash code
     * @return The hash code.
    */
    public int hashCode()
    {
        int hashCode = ( getId() == null ) ? 0
            : getId().hashCode();
        return hashCode;
    }

    /**
     * Populates and return a DTO with values from current business object.
     * @return OwnerDto
    */
    public EntityValueObject toData()
    {
        OwnerDto lDto = new OwnerDto();

        lDto.setId( getId() );
        lDto.setModifiedOn( getModifiedOn() );
        lDto.setCreatedOn( getCreatedOn() );

        lDto.setFirstName( getFirstName() );
        lDto.setLastName( getLastName() );
        lDto.setCity( getCity() );
        //lDto.setPetIds(  );

        return lDto;
    }

    /**
     * Populates this business object with values from DTO.
     * @param dto The DTO.
     * 
    */
    public void fromData( EntityValueObject dto )
    {
        OwnerDto lDto = (OwnerDto) dto;

        setFirstName( lDto.getFirstName() );
        setLastName( lDto.getLastName() );
        setCity( lDto.getCity() );

    }

    @PrePersist
    public void preCreate()
    {
        this.setCreatedOn( new Date() );
    }

    @PreUpdate
    public void preUpdate()
    {
        this.setModifiedOn( new Date() );
    }
}
