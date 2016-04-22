package com.smartactivities;

/**
 * Created by mikolevy on 22.04.16.
 */
public class ManagerKidActivity {

    private Long id;
    private String name;
    private String imgUrl;
    private Integer orderNumber;
    private Boolean isDone;

    public ManagerKidActivity(Long id, String name, String imgUrl, Integer orderNumber, Boolean isDone) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.orderNumber = orderNumber;
        this.isDone = isDone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }
}
