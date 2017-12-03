package com.example.uytai.ontap;

/**
 * Created by uytai on 12/3/2017.
 */

public class Model {
    private int Id;
    private String TenCV;

    public Model(int id, String tenCV) {
        Id = id;
        TenCV = tenCV;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String tenCV) {
        TenCV = tenCV;
    }
}
