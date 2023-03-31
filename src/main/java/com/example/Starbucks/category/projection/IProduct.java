package com.example.Starbucks.category.projection;

import lombok.ToString;

public interface IProduct {
    Long getId();
    String getName();
    Integer getPrice();
    String getThumbnail();
    Integer getMain_Category_Id();
    Integer getMiddle_Category_Id();
}
