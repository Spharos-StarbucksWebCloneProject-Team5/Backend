package com.example.Starbucks.payment.service;

import com.example.Starbucks.payment.model.Payment;
import com.example.Starbucks.payment.repository.IPaymentRepository;
import com.example.Starbucks.payment.vo.RequestPayment;
import com.example.Starbucks.payment.vo.RequestPaymentCancel;
import com.example.Starbucks.payment.vo.ResponsePayment;
import com.example.Starbucks.payment.vo.ResponsePaymentShipping;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        payment.setAmount(payment.getProduct().getPrice() * payment.getProduct_count());
        payment.setShipping_status(1);
        payment.setPay_status(1);
        iPaymentRepository.save(payment);
        ResponsePayment responsePayment = ResponsePayment.builder()
                .pay_type(requestPayment.getPay_type())
                .userId(payment.getUser().getId())
                .product_count(requestPayment.getProduct_count())
                .productId(requestPayment.getProductId())
                .productPrice(payment.getProduct().getPrice())
                .shipping_phone(requestPayment.getShipping_phone())
                .shipping_address(requestPayment.getShipping_address())
                .shipping_status(payment.getShipping_status())
                .pay_status(payment.getPay_status())
                .amount(payment.getAmount())
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

    @Override
    public void cancelPayment(RequestPaymentCancel requestPaymentCancel) {
        Payment payment = iPaymentRepository.findById(requestPaymentCancel.getId()).get();
        payment.setPay_status(0);
        payment.setShipping_status(0);
        iPaymentRepository.save(payment);
    }

    @Override
    public void shippingPayment(ResponsePaymentShipping responsePaymentShipping) {
        Payment payment = iPaymentRepository.findById(responsePaymentShipping.getId()).get();
        payment.setShipping_status(responsePaymentShipping.getShipping_status());
        iPaymentRepository.save(payment);
    }
}