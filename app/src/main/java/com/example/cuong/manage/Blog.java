package com.example.cuong.manage;

/**
 * Created by Cuong on 4/6/2018.
 */

public class Blog {
    private String day;
    private String month;
    private String year;
    private String type;
    private String category;
    private String amount;


    public Blog( String day, String month, String year, String type, String category, String amount) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.type = type;
        this.category = category;
        this.amount = amount;
    }



    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
