package com.minhthanh.bulk.admin.form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * Created by phuongtm on 9/26/16.
 */
public class BundleEditForm {

    @NotNull
    private long id;

    @NotEmpty(message = "Bundle name is required")
    private String name;

    @NotNull(message = "Bundle price is required")
    private Float price;

    @NotNull(message = "Bundle quantity is required")
    @Min(value = 1, message = "Please provide at least one")
    private Integer quantity;

    @NotNull(message = "Bundle start date is required")
    private Date createdDate;

    @NotNull(message = "Bundle renewal date is required")
    private Date renewalDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }
}
