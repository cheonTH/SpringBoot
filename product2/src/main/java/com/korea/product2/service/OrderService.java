package com.korea.product2.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;

import com.korea.product2.dto.OrderDTO;
import com.korea.product2.dto.ProductDTO;
import com.korea.product2.model.OrderEntity;
import com.korea.product2.model.ProductEntity;
import com.korea.product2.persistence.OrderRepository;
import com.korea.product2.persistence.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository o_repository;
	private final ProductRepository p_repository;
	
	//주문 내역 조회
	public List<OrderDTO> getAllOrderTotalPrice(){
		List<Object[]> results = o_repository.findAllOrderTotalPrices();
		return OrderDTO.toListOrderDTO(results);
	}
	
	//주문하기 기능 만들기
	//1. 상품의 존재 여부 확인
	//2. 재고확인(재고 < 주문 개수) 예외 발생 시킴 throw new RuntimeException("재고가 부족합니다...")
	//3. 주문하기 (주문 내역을 저장)
	//4. 재고 감소시키기
	//5. 수정된 재고로 다시 데이터베이스에 저장
	//6. 전체내용 반환
	
	
	
	public List<ProductDTO> save(OrderDTO dto){
		Optional<ProductEntity> option = p_repository.findById(dto.getProductId());
		
		ProductEntity productEntity;
		
		//상품이 조회가 된다면
		if(option.isPresent()) {
			productEntity = option.get(); //option객체에서 데이터를 꺼내옴
		}else {
			throw new RuntimeException("상품을 찾을 수 없습니다...");
		}
		
		//재고 확인
		if(productEntity.getProductCount() < dto.getProductCount()) {
			throw new RuntimeException("재고가 부족합니다. 현재 재고 : " + productEntity.getProductCount());
		}
		
		OrderEntity order = OrderEntity.builder()
									.product(productEntity)
									.productCount(dto.getProductCount())
									.build();
		
		//주문 내역 저장
		o_repository.save(order);
		
		//재고 감소
		productEntity.setProductCount(productEntity.getProductCount() - dto.getProductCount());
		
		//db에 수정된 재고로 업데이트
		p_repository.save(productEntity);
		
		return p_repository.findAll().stream()
	            .map(entity -> new ProductDTO(entity)) // ProductDTO로 변환
	            .collect(Collectors.toList());
	}
}
