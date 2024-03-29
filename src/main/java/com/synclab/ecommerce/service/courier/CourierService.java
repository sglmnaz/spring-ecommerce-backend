package com.synclab.ecommerce.service.courier;

import com.synclab.ecommerce.model.Courier;

import java.util.List;

public interface CourierService {

    //insert
    Courier insert(Courier entity);

    //retrieve
    Courier findById(String id);

    List<Courier> findAll();

    //update
    Courier update(Courier entity);

    //delete
    void deleteById(String id);

}
