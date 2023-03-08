package com.example.Starbucks.shippingAddress.repository;

import com.example.Starbucks.shippingAddress.model.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShippingAddressRepository extends JpaRepository<ShippingAddress,Long> {
}
