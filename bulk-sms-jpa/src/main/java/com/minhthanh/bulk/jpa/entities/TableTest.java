package com.minhthanh.bulk.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by tung on 7/13/2016.
 */
@Entity
public class TableTest extends BaseTimeEntity {
    @Id
    @GeneratedValue
    public long id;

    public String mycolumn;
}
