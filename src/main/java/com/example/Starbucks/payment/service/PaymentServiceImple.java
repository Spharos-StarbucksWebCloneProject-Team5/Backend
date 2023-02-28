package com.example.Starbucks.payment.service;

import com.example.Starbucks.payment.model.Payment;
import com.example.Starbucks.payment.repository.IPaymentRepository;
import com.example.Starbucks.payment.vo.RequestPayment;
import com.example.Starbucks.payment.vo.ResponsePayment;
import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.users.model.User;
import com.example.Starbucks.users.repository.UserRepository;
import com.example.Starbucks.payment.repository.IPaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentServiceImple implements IPaymentService {

    private final IPaymentRepository iPaymentRepository;
    private final IProductRepository iProductRepository;
    private final UserRepository userRepository;
    @Override
    public ResponsePayment addPayment(RequestPayment requestPayment) {
        Payment payment = Payment.builder()
                .pay_type(requestPayment.getPay_type())
                .shipping_phone(requestPayment.getShipping_phone())
                .product_count(requestPayment.getProduct_count())
                .shipping_address(requestPayment.getShipping_address())
                .product(iProductRepository.findById(requestPayment.getProductId()).get())
                .user(userRepository.findById(requestPayment.getUserId()).get())
                .build();
        log.info(payment.toString());
        iPaymentRepository.save(payment);
        ResponsePayment responsePayment = ResponsePayment.builder()
                .pay_type(requestPayment.getPay_type())
                .userId(payment.getUser().getId())
                .shipping_phone(requestPayment.getShipping_phone())
                .shipping_address(requestPayment.getShipping_address())
                .productId(payment.getProduct().getId())
                .productId(requestPayment.getProductId())
                .build();

        /*
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        //정확히 일치하는 필드만 매핑
        ResponsePayment responsePayment = modelMapper.map(requestPayment, ResponsePayment.class);
        //log.info(requestPayment.toString());
        //Integer pr = iPaymentRepository.findAllByProductId(requestPayment.getProductId()).getProduct().getPrice();
        //log.info(pr.toString());
        //getProductId가 null인 이유는....???
        //responsePayment.setProductPrice(pr);
        //responsePayment.setAmount(pr * requestPayment.getProduct_count())
        */

        return responsePayment;
    }
}