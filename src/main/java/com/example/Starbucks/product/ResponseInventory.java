package com.example.Starbucks.product;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseInventory {

    @Getter
    @Builder
    private static class InventoryBody{
        private HttpStatus status;
        private String error;
        private String message;
    }


    public ResponseEntity<?> cartFail(Integer inventory) {
        InventoryBody inventoryBody = InventoryBody.builder()
                .status(HttpStatus.BAD_REQUEST)
                .error("해당상품의 재고가 부족합니다.")
                .message("상품 재고 수량 :" + inventory)
                .build();
        return ResponseEntity.badRequest().body(inventoryBody);
    }

    public ResponseEntity<?> cartSuccess(){
        InventoryBody inventoryBody = InventoryBody.builder()
                .status(HttpStatus.ACCEPTED)
                .error("")
                .message("상품이 정상적으로 담겼습니다.")
                .build();
        return ResponseEntity.ok().body(inventoryBody);
    }

    public ResponseEntity<?> paymentFail(Integer inventory){
        InventoryBody inventoryBody = InventoryBody.builder()
                .status(HttpStatus.BAD_REQUEST)
                .error("해당상품의 재고가 부족합니다.")
                .message("상품 재고 수량 :" + inventory)
                .build();
        return ResponseEntity.badRequest().body(inventoryBody);
    }

    public ResponseEntity<?> paymentSuccess(){
        InventoryBody inventoryBody = InventoryBody.builder()
                .status(HttpStatus.ACCEPTED)
                .error("")
                .message("상품이 정상적으로 결제되었습니다.")
                .build();
        return ResponseEntity.ok().body(inventoryBody);
    }

}
