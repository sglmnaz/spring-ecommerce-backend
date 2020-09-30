package com.synclab.ecommerce.service.cartItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.CartItem;
import com.synclab.ecommerce.repository.CartItemRepository;

@Service
public class CartItemServiceImplementation implements CartItemService {
	
	@Autowired
	private CartItemRepository repository;

	@Override
	public CartItem insert(CartItem record) {
		return repository.save(record);
	}

	@Override
	public CartItem findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<CartItem> findAll() {
		return repository.findAll();
	}

	@Override
	public CartItem update(CartItem record) {
		return repository.save(record);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public List<CartItem> findByCart_CartId(Long cartId) {
		return repository.findByCart_CartId(cartId);
	}

}
