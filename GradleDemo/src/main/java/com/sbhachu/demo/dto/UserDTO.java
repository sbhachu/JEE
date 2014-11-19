package com.sbhachu.demo.dto;

import com.sbhachu.demo.models.UserModel;

import java.util.List;

public class UserDTO {
    private List<UserModel> records;

    private int count;

    private String status;

    public UserDTO() {
    }

    public List<UserModel> getRecords() {
        return records;
    }

    public void setRecords(List<UserModel> records) {
        this.records = records;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
