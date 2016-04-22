package com.smartactivities;

/**
 * Created by mikolevy on 22.04.16.
 */
public class ManagerAction {

    private Long id;
    private String name;
    private String imgUrl;
    private Integer orderNumber;
    private Long kidActivityId;

    public ManagerAction(Long id, String name, String imgUrl, Integer orderNumber, Long kidActivityId) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.orderNumber = orderNumber;
        this.kidActivityId = kidActivityId;
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

    public Long getKidActivityId() {
        return kidActivityId;
    }

    public void setKidActivityId(Long kidActivityId) {
        this.kidActivityId = kidActivityId;
    }
}
