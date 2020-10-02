package com.synclab.ecommerce.service.courier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.Courier;
import com.synclab.ecommerce.repository.CourierRepository;

@Service
public class CourierServiceImplementation implements CourierService{

	@Autowired
	private CourierRepository repository;
	
	@Override
	public Courier insert(Courier entity) {
		return repository.save(entity);
	}

	@Override
	public Courier findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Courier> findAll() {
		return repository.findAll();
	}

	@Override
	public Courier update(Courier entity) {
		return repository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
