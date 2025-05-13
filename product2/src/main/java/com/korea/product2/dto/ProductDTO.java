package com.korea.product2.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.korea.product2.model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

	private int id;
	private String productName;
	private int productCount;
	private int productPrice;
	@CreationTimestamp
	private LocalDateTime addTime;
	@UpdateTimestamp
	private LocalDateTime changeTime;
	
	public ProductDTO(ProductEntity entity) {
		this.id = entity.getId();
		this.productName = entity.getProductName();
		this.productCount = entity.getProductCount();
		this.productPrice = entity.getProductPrice();
		this.addTime = entity.getAddTime();
		this.changeTime = entity.getChangeTime();
	}
	
	public static ProductEntity toEntity(ProductDTO dto) {
		return ProductEntity.builder()
							.id(dto.getId())
							.productName(dto.getProductName())
							.productCount(dto.getProductCount())
							.productPrice(dto.getProductPrice())
							.addTime(dto.getAddTime())
							.changeTime(dto.getChangeTime())
							.build();
	}
}
