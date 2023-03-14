package com.example.Starbucks.event.dto;

import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventProductDto {

    private Long id;

    private Product product;

    private Event event;
}
