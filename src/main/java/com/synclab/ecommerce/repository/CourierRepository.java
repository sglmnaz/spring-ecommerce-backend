package com.synclab.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synclab.ecommerce.model.Courier;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long>{

}