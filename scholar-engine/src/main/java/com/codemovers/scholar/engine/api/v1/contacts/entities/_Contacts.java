/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.contacts.entities;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractEntity;
import com.codemovers.scholar.engine.helper.enums.ContactTypes;
import com.codemovers.scholar.engine.helper.enums.ParentTypes;
import com.codemovers.scholar.engine.helper.enums.StatusEnum;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Mover 12/14/2017
 */
public class _Contacts extends AbstractEntity {

    private Integer id;
    private ParentTypes parentType;
    private Integer parentId;
    private ContactTypes contactType;
    private String details;
    private StatusEnum status;
    private Date dateCreated;
    private Integer authorId;

    public _Contacts() {
    }

    public _Contacts(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ParentTypes getParentType() {
        return parentType;
    }

    public void setParentType(ParentTypes parentType) {
        this.parentType = parentType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public ContactTypes getContactType() {
        return contactType;
    }

    public void setContactType(ContactTypes contactType) {
        this.contactType = contactType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.parentType);
        hash = 89 * hash + Objects.hashCode(this.parentId);
        hash = 89 * hash + Objects.hashCode(this.contactType);
        hash = 89 * hash + Objects.hashCode(this.details);
        hash = 89 * hash + Objects.hashCode(this.status);
        hash = 89 * hash + Objects.hashCode(this.dateCreated);
        hash = 89 * hash + Objects.hashCode(this.authorId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final _Contacts other = (_Contacts) obj;
        if (!Objects.equals(this.details, other.details)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.parentType != other.parentType) {
            return false;
        }
        if (!Objects.equals(this.parentId, other.parentId)) {
            return false;
        }
        if (this.contactType != other.contactType) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.dateCreated, other.dateCreated)) {
            return false;
        }
        return Objects.equals(this.authorId, other.authorId);
    }


    @Override
    public void validate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "_Contacts{"
                + "id=" + id
                + ", parentType=" + parentType
                + ", parentId=" + parentId
                + ", contactType=" + contactType
                + ", details=" + details
                + ", status=" + status
                + ", dateCreated=" + dateCreated
                + ", authorId=" + authorId
                + "}";
    }

}
