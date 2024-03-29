package com.synclab.ecommerce.service.stockItem;

import com.synclab.ecommerce.model.StockItem;
import com.synclab.ecommerce.repository.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockItemServiceImplementation implements StockItemService {

    @Autowired
    private StockItemRepository repository;

    @Override
    public StockItem insert(StockItem entity) {
        return repository.save(entity);
    }

    @Override
    public StockItem findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public List<StockItem> findAll() {
        return repository.findAll();
    }

    @Override
    public StockItem update(StockItem entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

}