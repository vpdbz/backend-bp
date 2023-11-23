package com.codingame.backendbp.bpclientservice.model;

import com.codingame.backendbp.bpclientservice.dto.ClientPatchRequest;
import com.codingame.backendbp.bpclientservice.dto.ClientRequest;

public class Person {
    private String name;
    private String gender;
    private Integer age;
    private long id;
    private String address;
    private String phone;

    public Person() {
        this.name = "";
        this.gender = "";
        this.age = 0;
        this.id = 0;
        this.address = "";
        this.phone = "";
    }

    public Person(String name, String gender, int age, long id, String address, String phone) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.id = id;
        this.address = address;
        this.phone = phone;
    }

    public Person(ClientRequest clientRequest) {
        this.name = clientRequest.name();
        this.gender = clientRequest.gender();
        this.age = clientRequest.age();
        this.address = clientRequest.address();
        this.phone = clientRequest.phone();
    }

    public Person(ClientPatchRequest clientPatchRequest) {
        this.name = clientPatchRequest.name();
        this.gender = clientPatchRequest.gender();
        this.age = clientPatchRequest.age();
        this.address = clientPatchRequest.address();
        this.phone = clientPatchRequest.phone();
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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
