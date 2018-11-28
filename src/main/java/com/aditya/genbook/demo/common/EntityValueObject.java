package com.aditya.genbook.demo.common;

import java.io.Serializable;
import java.util.Date;

public abstract class EntityValueObject implements Serializable
{
    private static final long serialVersionUID = 4389799815672884893L;

    private Long id = null;

    /**
     * Returns the id value. 
     * @return id The primary key of entity.
     */
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

}
