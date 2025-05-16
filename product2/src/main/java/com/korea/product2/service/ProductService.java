package com.korea.product2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.product2.dto.ProductDTO;
import com.korea.product2.model.ProductEntity;
import com.korea.product2.persistence.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository repository;
	
	public List<ProductEntity> getFindAll() {
		return repository.findAll();
	}
	
	private void validate(ProductEntity entity) {
		if(entity == null) {
			throw new RuntimeException("Entity cannot be null");
		}
	}
	
	public List<ProductEntity> addProduct(ProductEntity entity){
		validate(entity);
		repository.save(entity);
		
		return repository.findAll();
	}
}
