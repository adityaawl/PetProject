package com.aditya.genbook.demo.dto;

import java.util.Date;

import com.aditya.genbook.demo.common.EntityValueObject;
import com.aditya.genbook.demo.entity.Owner;

public class PetDto extends EntityValueObject
{
    private static final long serialVersionUID = 8468110673632251491L;

    private String name;

    /**
     * Returns the name value.
     * @return name The name of the Pet.
     */
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

    private Long ownerId;

    /**
     * Returns the {@link Owner} id value
     * @return ownerId The {@link Owner} id of the Pet.
     */
    public Long getOwnerId()
    {
        return ownerId;
    }

    /**
     * Sets  the {@link Owner} id value
     * @param mOwnerId The {@link Owner} id of the Pet
     * @param mOwnerId the mOwnerId to set
     */
    public void setOwnerId( Long mOwnerId )
    {
        this.ownerId = mOwnerId;
    }

}
