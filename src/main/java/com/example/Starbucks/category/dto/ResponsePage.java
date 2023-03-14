package com.example.Starbucks.category.dto;

import com.example.Starbucks.category.model.CategoryList;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePage {
    private List<Contents> content;
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long totalElements;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Contents {
        private Long productId;
        private String productName;
        private Integer price;
        private String thumbnail;
    }

    public static List<Contents> ofContents(Page<CategoryList> categories) {
        List<ResponsePage.Contents> contents = categories.getContent().stream()
                .map(element -> ResponsePage.Contents.builder()
                        .productId(element.getId())
                        .productName(element.getProduct().getName())
                        .price(element.getProduct().getPrice())
                        .thumbnail(element.getProduct().getThumbnail())
                        .build()).collect(Collectors.toList());
        return contents;
    }
}
