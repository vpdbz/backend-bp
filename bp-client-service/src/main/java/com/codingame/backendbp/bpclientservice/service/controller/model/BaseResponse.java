package com.codingame.backendbp.bpclientservice.service.controller.model;

import java.util.List;
import java.util.ArrayList;

public class BaseResponse {
    private Integer status;
    private List<String> errors;
    private String message;
    protected Object data;

    public BaseResponse() {
        this.status = 200;
        this.errors = null;
        this.message = null;
    }

    public BaseResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }

        this.errors.add(error);
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }

}
