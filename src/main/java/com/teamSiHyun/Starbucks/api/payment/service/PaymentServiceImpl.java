package com.teamSiHyun.Starbucks.api.payment.service;

import com.teamSiHyun.Starbucks.api.payment.vo.RequestCartPayment;
import com.teamSiHyun.Starbucks.api.payment.vo.RequestPayment;
import com.teamSiHyun.Starbucks.api.product.model.Product;
import com.teamSiHyun.Starbucks.api.cart.model.Cart;
import com.teamSiHyun.Starbucks.api.cart.repository.ICartRepository;
import com.teamSiHyun.Starbucks.jwt.JwtTokenProvider;
import com.teamSiHyun.Starbucks.api.payment.dto.PaymentDto;
import com.teamSiHyun.Starbucks.api.payment.dto.PaymentShippingDto;
import com.teamSiHyun.Starbucks.api.payment.dto.ResponseBest;
import com.teamSiHyun.Starbucks.api.payment.dto.UserShippingDto;
import com.teamSiHyun.Starbucks.api.payment.model.Payment;
import com.teamSiHyun.Starbucks.api.payment.repository.IPaymentRepository;
import com.teamSiHyun.Starbucks.api.product.ResponseInventory;
import com.teamSiHyun.Starbucks.api.product.repository.IProductRepository;
import com.teamSiHyun.Starbucks.api.shippingAddress.repository.IShippingAddressRepository;
import com.teamSiHyun.Starbucks.api.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final IShippingAddressRepository iShippingAddressRepository;
    private final ResponseInventory responseInventory;

    @Override
    public ResponseEntity<?> addPayment(Authentication authentication, RequestPayment requestPayment) {
        if(requestPayment.getProductCount() > iProductRepository.findById(requestPayment.getProductId()).get().getCount()){
            return responseInventory.paymentFail(iProductRepository.findById(requestPayment.getProductId()).get().getCount());
        }
        Long userId = userRepository.findByEmail(authentication.getName()).get().getId();
        iPaymentRepository.save(Payment.builder()
                        .user(userRepository.findById(userId).get())
                        .product(iProductRepository.findById(requestPayment.getProductId()).get())
                        .productCount(requestPayment.getProductCount())
                        .shippingAddress(iShippingAddressRepository.findById(requestPayment.getShippingAddressId()).get())
                        .shippingStatus(1)
                        .payType(requestPayment.getPayType())
                        .amount(iProductRepository.findById(requestPayment.getProductId()).get().getPrice() * requestPayment.getProductCount())
                        .payStatus(1)
                .build());
        //결제내역 저장
        Product product = iProductRepository.findById(requestPayment.getProductId()).get();
        product.setUpdateCount(product.getCount()- requestPayment.getProductCount());
        iProductRepository.save(product);
        //상품 재고 수량 감소
        return responseInventory.paymentSuccess();
    }
    @Override
    public void cancelPayment(Long id) {
        Payment payment = iPaymentRepository.findById(id).get();

        Product product = payment.getProduct();
        product.setUpdateCount(product.getCount()+ payment.getProductCount());
        iProductRepository.save(product);
        //상품 재고수량 추가
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
        //관리자의 배송상태 변경
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
    public UserShippingDto getShippingStatus(Authentication authentication) {
        //3개월간 배송 상태 조회
        Long userId = userRepository.findByEmail(authentication.getName()).get().getId();
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

    @SneakyThrows
    @Override
    public List<PaymentDto> getPayment(Authentication authentication, String startDate, String  endDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        Long userId = userRepository.findByEmail(authentication.getName()).get().getId();
        List<PaymentDto> userPaymentDto = iPaymentRepository.findAllByUserId(userId).stream()
                .filter(payment -> payment.getCreateDate().isAfter(start.atTime(0,0,0))
                        && payment.getCreateDate().isBefore(end.atTime(23,59,59)))
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
        //카트에서 한번에 주문하기
        for(RequestCartPayment.Carts carts : requestCartPayments.getCarts()){
            Optional<Cart> cart = iCartRepository.findById(carts.getCartId());
            Product product = cart.get().getProduct();
            product.setUpdateCount(product.getCount() - cart.get().getCount());
            iProductRepository.save(product);
            //상품 재고 수량 감소

            iPaymentRepository.save(Payment.builder()
                    .user(cart.get().getUser())
                    .product(cart.get().getProduct())
                    .productCount(cart.get().getCount())
                    .shippingAddress(iShippingAddressRepository.findById(requestCartPayments.getShippingAddressId()).get())
                    .payType(requestCartPayments.getPayType())
                    .amount(cart.get().getProduct().getPrice() * cart.get().getCount())
                    .payStatus(1)
                    .build());
            //결제정보 저장

            iCartRepository.save(Cart.builder()
                        .id(cart.get().getId())
                        .user(cart.get().getUser())
                        .product(cart.get().getProduct())
                        .count(0)
                        .now(false)
                    .build());
            //카트목록에서 삭제
        }
    }
}