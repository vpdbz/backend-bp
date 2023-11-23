package com.codingame.backendbp.bpclientservice.dao.entity;

import com.codingame.backendbp.bpclientservice.model.Person;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public class PersonEntity {
    @NotNull
    private String name;
    
    @NotNull
    private String gender;

    private int age;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String address;

    @NotNull
    private String phone;

    public PersonEntity() {
        this.name = "";
        this.gender = "";
        this.age = 0;
        this.id = 0;
        this.address = "";
        this.phone = "";
    }

    public PersonEntity(Person person) {
        this.name = person.getName();
        this.gender = person.getGender();
        this.age = person.getAge();
        this.id = person.getId();
        this.address = person.getAddress();
        this.phone = person.getPhone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
