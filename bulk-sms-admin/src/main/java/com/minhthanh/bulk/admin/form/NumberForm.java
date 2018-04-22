package com.minhthanh.bulk.admin.form;

import com.minhthanh.bulk.admin.converter.DateConverter;
import com.minhthanh.bulk.jpa.entities.BlacklistNumber;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by youngkaka on 24/10/2016.
 */
public class NumberForm {

    private int id;

    @Pattern(regexp="\\d{10,12}", message="Must be a phone number that  has length size: between 10 and 12")
    private String number;

    @Pattern(regexp="\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}", message="Must be in date format: MM-dd-yyyy hh:mm")
    private String date;

    public NumberForm() {
        this.date = DateConverter.dateToString(new Date());
    }

    public NumberForm(BlacklistNumber blacklistNumber) {
        this.id = blacklistNumber.getId();
        this.date = DateConverter.dateToString(blacklistNumber.getCreatedDate());
        this.number = blacklistNumber.getNumber();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BlacklistNumber createNew() {
        return new BlacklistNumber(this.number);
    }

    public BlacklistNumber updateNumber(BlacklistNumber blacklistNumber){
        blacklistNumber.setNumber(this.number);
        blacklistNumber.setCreatedDate(DateConverter.stringToDate(this.date));
        return blacklistNumber;
    }
}
