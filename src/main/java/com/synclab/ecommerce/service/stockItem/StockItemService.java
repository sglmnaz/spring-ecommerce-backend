package com.synclab.ecommerce.service.stockItem;

import com.synclab.ecommerce.model.StockItem;

import java.util.List;

public interface StockItemService {

    // insert
    StockItem insert(StockItem entity);

    // retrieve
    StockItem findById(String id);

    List<StockItem> findAll();

    // update
    StockItem update(StockItem entity);

    // delete
    void deleteById(String id);

}
