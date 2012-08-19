package com.ecwidtest.phonebook.common.bean;

import java.io.Serializable;

public class PhoneNumberDTO implements Serializable {

    private Long id;
    private String phoneNum;
    private HumanDTO owner;

    public HumanDTO getOwner() {
        return owner;
    }

    public void setOwner(HumanDTO owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
