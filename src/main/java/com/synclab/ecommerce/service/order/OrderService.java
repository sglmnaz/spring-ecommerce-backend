package com.synclab.ecommerce.service.order;

import com.synclab.ecommerce.model.Order;

import java.util.List;

public interface OrderService {

    //insert
    Order insert(Order entity);

    //retrieve
    Order findById(String id);

    List<Order> findByUser_UserId(String id);

    List<Order> findAll();

    //update
    Order update(Order entity);

    //delete
    void deleteById(String id);

}
