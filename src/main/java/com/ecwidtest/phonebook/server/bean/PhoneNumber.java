package com.ecwidtest.phonebook.server.bean;

import javax.persistence.*;

/**
 * @author d.sapaev
 *
 */
@Entity
@Table(name="PhoneNumber")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "human_id")
    private Human owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Human getOwner() {
        return owner;
    }

    public void setOwner(Human owner) {
        this.owner = owner;
    }
}
