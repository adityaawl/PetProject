package com.aditya.genbook.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.aditya.genbook.demo.common.EntityValueObject;

public class OwnerDto extends EntityValueObject
{
    private static final long serialVersionUID = -1869009168698466526L;

    private String firstName;

    /**
     * @return the mFirstName
     */
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

    private String lastName;

    /**
     * @return the mLastName
     */
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

    private String city;

    /**
     * @return the mCity
     */
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

    private List<Long> petIds = new ArrayList<>();

    /**
     * @return the petIds
     */
    public List<Long> getPetIds()
    {
        return petIds;
    }

    /**
     * @param petIds the petIds to set
     */
    public void setPetIds( List<Long> petIds )
    {
        this.petIds = petIds;
    }
}
