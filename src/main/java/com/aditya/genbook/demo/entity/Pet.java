/**
 * 
 */
package com.aditya.genbook.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.aditya.genbook.demo.common.EntityValueObject;
import com.aditya.genbook.demo.dto.PetDto;

@Entity
@Table( name = "PET" )
public class Pet implements Serializable
{
    private static final long serialVersionUID = 8062287176257692909L;

    public Pet()
    {
        // TODO Auto-generated constructor stub
    }

    public Pet( @Size( message = "INVALID_SIZE", min = 0, max = 64 ) @NotNull( message = "NOT_NULL" ) String name,
        Date birthDate,
        @NotNull( message = "NOT_NULL" ) Owner owner )
    {
        super();
        this.name = name;
        this.birthDate = birthDate;
        this.owner = owner;
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
    private String name;

    /**
     * Returns the name value.
     * @return name The name of the Pet.
     */
    @Column( name = "NAME", nullable = false, length = 64 )
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name value.
     * @param name The name of the Pet.
     */
    public void setName( String name )
    {
        this.name = name;
    }

    private Date birthDate;

    /**
     * Returns the birth date.
     * @return birthDate The birth date of the Pet.
     */
    @Column( name = "BIRTH_DATE", nullable = true )
    @Type( type = "date" )
    public Date getBirthDate()
    {
        return birthDate;
    }

    /**
     * Sets the birth date value.
     * @param birthDate The birth date of the Pet.
     */
    public void setBirthDate( Date birthDate )
    {
        this.birthDate = birthDate;
    }

    @NotNull( message = "NOT_NULL" )
    private Owner owner;

    /**
     * Returns the {@link Owner} value
     * @return owner The {@link Owner} of the Pet
     */
    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "OWNER_FK", nullable = false )
    public Owner getOwner()
    {
        return this.owner;
    }

    /**
    * Sets  the {@link Owner} value
    * @param owner The {@link Owner} of the Pet
    */
    public void setOwner( Owner owner )
    {
        this.owner = owner;
    }

    /**
     * Returns <code>true</code> if the argument is an Pet instance and all identifiers for this entity
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
        if( !( object instanceof Pet ) )
        {
            return false;
        }
        final Pet lOtherPet = (Pet) object;

        if( this.getId() == null || lOtherPet.getId() == null || !this.getId().equals( lOtherPet.getId() ) )
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
     * @return PetDto
    */
    public EntityValueObject toData()
    {
        PetDto lDto = new PetDto();

        lDto.setId( getId() );
        lDto.setModifiedOn( getModifiedOn() );
        lDto.setCreatedOn( getCreatedOn() );

        lDto.setName( getName() );
        lDto.setBirthDate( getBirthDate() );

        return lDto;
    }

    /**
     * Populates this business object with values from DTO.
     * @param dto The DTO.
    */
    public void fromData( EntityValueObject dto )
    {
        PetDto lDto = (PetDto) dto;

        setName( lDto.getName() );
        setBirthDate( lDto.getBirthDate() );
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
