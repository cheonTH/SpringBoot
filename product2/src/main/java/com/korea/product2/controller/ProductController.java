package com.korea.product2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product2.dto.ProductDTO;
import com.korea.product2.model.ProductEntity;
import com.korea.product2.model.ResponseDTO;
import com.korea.product2.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService service;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		List<ProductDTO> product = service.getFindAll();
		return ResponseEntity.ok(product);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDTO dto){
		ProductEntity entity = ProductDTO.toEntity(dto);
		ProductDTO product = service.addProduct(entity);
		
		return ResponseEntity.ok(product);
	}
}
