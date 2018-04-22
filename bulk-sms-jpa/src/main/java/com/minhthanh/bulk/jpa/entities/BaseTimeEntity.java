package com.minhthanh.bulk.jpa.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tung on 7/13/2016.
 */
@MappedSuperclass
public class BaseTimeEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
    public Date created;

    @Temporal(TemporalType.TIMESTAMP)
    public Date modified;

    @PrePersist
    public void pre() {
        created = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        modified = new Date();
    }
}
