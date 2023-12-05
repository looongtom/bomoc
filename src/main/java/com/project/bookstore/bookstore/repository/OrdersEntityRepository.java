package com.project.bookstore.bookstore.repository;

import com.project.bookstore.bookstore.model.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersEntityRepository extends JpaRepository<OrdersEntity, Integer> {
    OrdersEntity getOrdersEntitiesByCartId(int cartId);

    OrdersEntity getOrdersEntityById(int id);

    List<OrdersEntity> getOrdersEntitiesByCustomerIdCus(int customerId);
}