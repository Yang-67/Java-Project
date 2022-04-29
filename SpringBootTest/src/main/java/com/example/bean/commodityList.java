package com.example.bean;

public class commodityList {
    private int commodityId;
    private String commodityName;
    private String commodityWeight;
    private String commodityPrice;
    private String commodityImg;
    private String commodityState;
    private String dateCreated;
    private String brandName;
    private String specification;
    private String sortName;

    public commodityList() {
    }

    public commodityList(int commodityId, String commodityName, String commodityWeight, String commodityPrice, String commodityImg, String commodityState, String dateCreated, String brandName, String specification, String sortName) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.commodityWeight = commodityWeight;
        this.commodityPrice = commodityPrice;
        this.commodityImg = commodityImg;
        this.commodityState = commodityState;
        this.dateCreated = dateCreated;
        this.brandName = brandName;
        this.specification = specification;
        this.sortName = sortName;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityWeight() {
        return commodityWeight;
    }

    public void setCommodityWeight(String commodityWeight) {
        this.commodityWeight = commodityWeight;
    }

    public String getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(String commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getCommodityImg() {
        return commodityImg;
    }

    public void setCommodityImg(String commodityImg) {
        this.commodityImg = commodityImg;
    }

    public String getCommodityState() {
        return commodityState;
    }

    public void setCommodityState(String commodityState) {
        this.commodityState = commodityState;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    @Override
    public String toString() {
        return "commodityList{" +
                "commodityId=" + commodityId +
                ", commodityName='" + commodityName + '\'' +
                ", commodityWeight='" + commodityWeight + '\'' +
                ", commodityPrice='" + commodityPrice + '\'' +
                ", commodityImg='" + commodityImg + '\'' +
                ", commodityState='" + commodityState + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", brandName='" + brandName + '\'' +
                ", specification='" + specification + '\'' +
                ", sortName='" + sortName + '\'' +
                '}';
    }
}
