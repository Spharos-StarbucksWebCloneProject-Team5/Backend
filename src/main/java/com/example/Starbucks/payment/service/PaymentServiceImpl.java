package com.example.Starbucks.payment.service;

import com.example.Starbucks.cart.model.Cart;
import com.example.Starbucks.cart.repository.ICartRepository;
import com.example.Starbucks.payment.dto.PaymentDto;
import com.example.Starbucks.payment.dto.PaymentShippingDto;
import com.example.Starbucks.payment.dto.ResponseBest;
import com.example.Starbucks.payment.dto.UserShippingDto;
import com.example.Starbucks.payment.model.Payment;
import com.example.Starbucks.payment.repository.IPaymentRepository;
import com.example.Starbucks.payment.vo.*;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.shippingAddress.repository.IShippingAddressRepository;
import com.example.Starbucks.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentServiceImpl implements IPaymentService {

    private final IPaymentRepository iPaymentRepository;
    private final IProductRepository iProductRepository;
    private final UserRepository userRepository;
    private final ICartRepository iCartRepository;
    private final IShippingAddressRepository iShippingAddressRepository;

    @Override
    public void addPayment(RequestPayment requestPayment) {
        iPaymentRepository.save(Payment.builder()
                        .user(userRepository.findById(requestPayment.getUserId()).get())
                        .product(iProductRepository.findById(requestPayment.getProductId()).get())
                        .productCount(requestPayment.getProductCount())
                        .shippingAddress(iShippingAddressRepository.findById(requestPayment.getShippingAddressId()).get())
                        .shippingStatus(1)
                        .payType(requestPayment.getPayType())
                        .amount(iProductRepository.findById(requestPayment.getProductId()).get().getPrice() * requestPayment.getProductCount())
                        .payStatus(1)
                .build());
    }
    @Override
    public void cancelPayment(RequestPaymentCancel requestPaymentCancel) {
        Payment payment = iPaymentRepository.findById(requestPaymentCancel.getId()).get();
        iPaymentRepository.save(Payment.builder()
                        .id(payment.getId())
                        .user(payment.getUser())
                        .product(payment.getProduct())
                        .productCount(payment.getProductCount())
                        .shippingAddress(payment.getShippingAddress())
                        .shippingStatus(0)
                        .payType(payment.getPayType())
                        .amount(payment.getAmount())
                        .payStatus(0)
                .build());
    }

    @Override
    public void shippingPayment(PaymentShippingDto paymentShippingDto) {
        Payment payment = iPaymentRepository.findById(paymentShippingDto.getPaymentId()).get();
        iPaymentRepository.save(Payment.builder()
                .id(payment.getId())
                .user(payment.getUser())
                .product(payment.getProduct())
                .productCount(payment.getProductCount())
                .shippingAddress(payment.getShippingAddress())
                .shippingStatus(paymentShippingDto.getShippingStatus())
                .payType(payment.getPayType())
                .amount(payment.getAmount())
                .payStatus(payment.getPayStatus())
                .build());
    }

    @Override
    public UserShippingDto getShippingStatus(Long userId) {
        List<Payment> userPayment = iPaymentRepository.findAllByUserId(userId).stream()
                .filter(payment -> payment.getPayStatus() !=0 && LocalDate.now().atTime(0,0,0).minusMonths(3).isBefore(payment.getCreateDate()))
                .collect(Collectors.toList());
        Integer ship1 = (int) userPayment.stream().filter(payment -> payment.getShippingStatus() == 1).count();
        Integer ship2 = (int) userPayment.stream().filter(payment -> payment.getShippingStatus() == 2).count();
        Integer ship3 = (int) userPayment.stream().filter(payment -> payment.getShippingStatus() == 3).count();
        Integer ship4 = (int) userPayment.stream().filter(payment -> payment.getShippingStatus() == 4).count();

        return UserShippingDto.builder()
                .preparingProduct(ship1)
                .preparingForDelivery(ship2)
                .shipping(ship3)
                .deliveryCompleted(ship4)
                .build();
    }

    @Override
    public List<PaymentDto> getPayment(Long userId, RequestPaymentList requestPaymentList) {
        List<PaymentDto> userPaymentDto = iPaymentRepository.findAllByUserId(userId).stream()
                .filter(payment -> payment.getCreateDate().isAfter(requestPaymentList.getStartDate().atTime(0,0,0))
                        && payment.getCreateDate().isBefore(requestPaymentList.getEndDate().atTime(23,59,59)))
                .map(payment -> PaymentDto.builder()
                        .date(payment.getCreateDate())
                        .payStatus(payment.getPayStatus())
                        .productId(payment.getProduct().getId())
                        .productName(payment.getProduct().getName())
                        .productPrice(payment.getProduct().getPrice())
                        .productThumbnail(payment.getProduct().getThumbnail())
                        .count(payment.getProductCount())
                        .build())
                .sorted(Comparator.comparing(PaymentDto::getDate).reversed())
                .collect(Collectors.toList());

        return userPaymentDto;
    }

    @Override
    public List<ResponseBest> getBest(Integer main) {
        return iPaymentRepository.bestProduct(main).stream()
                .map(element -> ResponseBest.builder()
                        .id(element.getId())
                        .name(element.getName())
                        .price(element.getPrice())
                        .thumbnail(element.getThumbnail())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public void addCartPayment(RequestCartPayment requestCartPayments) {
        for(RequestCartPayment.Carts carts : requestCartPayments.getCarts()){
            Optional<Cart> cart = iCartRepository.findById(carts.getCartId());
            iPaymentRepository.save(Payment.builder()
                    .user(cart.get().getUser())
                    .product(cart.get().getProduct())
                    .productCount(cart.get().getCount())
                    .shippingAddress(iShippingAddressRepository.findById(requestCartPayments.getShippingAddressId()).get())
                    .payType(requestCartPayments.getPayType())
                    .amount(cart.get().getProduct().getPrice() * cart.get().getCount())
                    .payStatus(1)
                    .build());
            iCartRepository.save(Cart.builder()
                        .id(cart.get().getId())
                        .user(cart.get().getUser())
                        .product(cart.get().getProduct())
                        .count(0)
                        .now(false)
                    .build());
        }
    }
}