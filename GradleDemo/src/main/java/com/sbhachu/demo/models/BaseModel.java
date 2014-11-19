package com.sbhachu.demo.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sbhachu.demo.util.JsonDateSerializerUtil;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = -2625405200606950485L;

    private Long id;

    private Date dateCreated;

    private Date dateModified;

    public BaseModel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonSerialize(using = JsonDateSerializerUtil.class)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @JsonSerialize(using = JsonDateSerializerUtil.class)
    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

}
