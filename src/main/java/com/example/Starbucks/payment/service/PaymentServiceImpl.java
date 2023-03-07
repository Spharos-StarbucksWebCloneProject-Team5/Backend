package com.example.Starbucks.payment.service;

import com.example.Starbucks.payment.model.Payment;
import com.example.Starbucks.payment.repository.IPaymentRepository;
import com.example.Starbucks.payment.vo.*;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentServiceImpl implements IPaymentService {

    private final IPaymentRepository iPaymentRepository;
    private final IProductRepository iProductRepository;
    private final UserRepository userRepository;
    @Override
    public ResponsePayment addPayment(RequestPayment requestPayment) {
        Payment payment = Payment.builder()
                .payType(requestPayment.getPayType())
                .shipping_phone(requestPayment.getShipping_phone())
                .product_count(requestPayment.getProduct_count())
                .shipping_address(requestPayment.getShipping_address())
                .product(iProductRepository.findById(requestPayment.getProductId()).get())
                .user(userRepository.findById(requestPayment.getUserId()).get())
                .isGift(requestPayment.isGift())
                .build();
        log.info(payment.toString());
        payment.setAmount(payment.getProduct().getPrice() * payment.getProduct_count());
        payment.setShippingStatus(1);
        payment.setPayStatus(1);
        iPaymentRepository.save(payment);
        ResponsePayment responsePayment = ResponsePayment.builder()
                .payType(requestPayment.getPayType())
                .userId(payment.getUser().getId())
                .product_count(requestPayment.getProduct_count())
                .productId(requestPayment.getProductId())
                .productPrice(payment.getProduct().getPrice())
                .shipping_phone(requestPayment.getShipping_phone())
                .shipping_address(requestPayment.getShipping_address())
                .shippingStatus(payment.getShippingStatus())
                .payStatus(payment.getPayStatus())
                .amount(payment.getAmount())
                .isGift(payment.isGift())
                .build();

        return responsePayment;
    }

    @Override
    public void cancelPayment(RequestPaymentCancel requestPaymentCancel) {
        Payment payment = iPaymentRepository.findById(requestPaymentCancel.getId()).get();
        payment.setPayStatus(0);
        payment.setShippingStatus(0);
        iPaymentRepository.save(payment);
    }

    @Override
    public void shippingPayment(ResponsePaymentShipping responsePaymentShipping) {
        Payment payment = iPaymentRepository.findById(responsePaymentShipping.getId()).get();
        payment.setShippingStatus(responsePaymentShipping.getShipping_status());
        iPaymentRepository.save(payment);
    }

    @Override
    public ResponseShipping getShippingStatus(Long userId){

        List<Payment> payment = iPaymentRepository.findAllByUserId(userId);
        int ship1=0, ship2=0, ship3=0, ship4=0;

        for (Payment value : payment) {
            Integer shippingStatus = value.getShippingStatus();
            Integer payStatus = value.getPayStatus();//주문 취소하면
            if (payStatus == 0){//주문취소
                shippingStatus =5; //배송에 안뜸
            }

            LocalDateTime createDate = value.getCreateDate();//결제완료날짜
            LocalDateTime now = LocalDateTime.now();//현재날짜

            if(now.minusMonths(3).isAfter(createDate)){//현재날짜 - 3개월이 결제완료일보다 크면
                shippingStatus =5;//배송에 안뜸
            }

            if (shippingStatus == 1) {//상품준비중
                ship1++;
            } else if (shippingStatus == 2) {//배송준비중
                ship2++;
            } else if (shippingStatus == 3) {//배송중
                ship3++;
            } else if (shippingStatus == 4) {//배송완료
                ship4++;
            }
        }
        ResponseShipping responseShipping = ResponseShipping.builder()
                .preparingProduct(ship1)
                .preparingForDelivery(ship2)
                .shipping(ship3)
                .deliveryCompleted(ship4)
                .build();
        return responseShipping;
    }
   /* public List<ResponsePayment> getPayment(LocalDateTime date1 , LocalDateTime date2, Long userId, Integer type){
        List<Payment> payment = new ArrayList<>();
        if(type==0){//전체-전체
            payment = iPaymentRepository.findAllByUserId(userId);
        }
        else if(type==1){//전체-주문완료
            payment = iPaymentRepository.findByUserIdAndPayStatus(userId,1);
        }
        else if(type==2){//전체-주문취소
            payment = iPaymentRepository.findByUserIdAndPayStatus(userId,2);
        }
        else if(type==3){//전체-교환/반품
            payment = iPaymentRepository.findByUserIdAndPayStatus(userId,3);
        }
        else if(type==4){//일반-전체
            payment = iPaymentRepository.findByUserIdAndGift(userId,false);
        }
        else if(type==5){//일반-상품준비중
            payment = iPaymentRepository.findByUserIdAndShippingStatus(userId,1);
        }
        else if(type==6){//일반-배송준비중
            payment = iPaymentRepository.findByUserIdAndShippingStatus(userId,2);
        }
        else if(type==7){//일반-배송중
            payment = iPaymentRepository.findByUserIdAndShippingStatus(userId,3);
        }
        else if(type==8){//일반-배송완료
            payment = iPaymentRepository.findByUserIdAndShippingStatus(userId,4);
        }
        else if(type==9){//일반-주문취소
            payment = iPaymentRepository.findByUserIdAndPayStatusAndGift(userId,1,false);
        }
        else if(type==10){//일반-교환반품
            payment = iPaymentRepository.findByUserIdAndPayStatusAndGift(userId,2,false);
        }
        else if(type==11){//일반-배송중
            payment = iPaymentRepository.findByUserIdAndPayStatusAndGift(userId,3,false);
        }
        else if(type==12){//선물-전체
            payment = iPaymentRepository.findByUserIdAndGift(userId,true);
        }
        else if(type==13){//선물-수락대기
            payment = iPaymentRepository.findByUserIdAndPayStatusAndGift(userId,4,true);
        }
        else if(type==14){//선물-선물수락
            payment = iPaymentRepository.findByUserIdAndPayStatusAndGift(userId,5,true);
        }
        else if(type==15){//선물-선물거절
            payment = iPaymentRepository.findByUserIdAndPayStatusAndGift(userId,6,true);
        }
        else if(type==16){//선물-주문취소
            payment = iPaymentRepository.findByUserIdAndPayStatusAndGift(userId,2,true);
        }
        else if(type==17){//선물-교환/반품
            payment = iPaymentRepository.findByUserIdAndPayStatusAndGift(userId,3,true);
        }

        List<ResponsePayment> responsePaymentList = new ArrayList<>();
        for (Payment value : payment) {
            LocalDateTime createDate = value.getCreateDate();
            if(createDate.compareTo(date1)>=0 && createDate.compareTo(date2)<=0){
                responsePaymentList.add(ResponsePayment.builder()
                        .userId(value.getId())
                        .productId(value.getProduct().getId())
                        .productPrice(value.getProduct().getPrice())
                        .product_count(value.getProduct_count())
                        .shippingStatus(value.getShippingStatus())
                        .shipping_address(value.getShipping_address())
                        .shipping_phone(value.getShipping_phone())
                        .payType(value.getPayType())
                        .payStatus(value.getPayStatus())
                        .amount(value.getAmount())
                        .isGift(value.isGift())
                        .build());
            }
        }
        return responsePaymentList;

    }*/
}