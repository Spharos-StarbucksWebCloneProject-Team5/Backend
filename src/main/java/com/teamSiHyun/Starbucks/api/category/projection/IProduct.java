package com.teamSiHyun.Starbucks.api.category.projection;

public interface IProduct {
    Long getId();
    String getName();
    Integer getPrice();
    String getThumbnail();
    Integer getMain_Category_Id();
    Integer getMiddle_Category_Id();
}
