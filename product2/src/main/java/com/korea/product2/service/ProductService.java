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
	
	public List<ProductDTO> getFindAll() {
		return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	}
	
	public ProductDTO addProduct(ProductEntity entity){
		ProductEntity save = repository.save(entity);
		
		return new ProductDTO(save);
	}
}
