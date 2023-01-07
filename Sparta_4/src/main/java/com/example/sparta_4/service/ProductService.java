package com.example.sparta_4.service;

import com.example.sparta_4.domain.Product;
import com.example.sparta_4.dto.ItemDto;
import com.example.sparta_4.dto.ProductMypriceRequestDto;
import com.example.sparta_4.dto.ProductRequestDto;
import com.example.sparta_4.repository.ProductRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor //final로 선언된 멤버 변수를 자동으로 생성합니다.
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional// 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Long update(Long id, ProductMypriceRequestDto productMypriceRequestDto){
        Product product = productRepository.findById(id).orElseThrow(
                ()->new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        product.update(productMypriceRequestDto);
        return id;
    }

    @Transactional //DB가 업데이트 되어야 한다
    public Long updateBySearch(Long id, ItemDto itemDto){
        Product product = productRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("아이디가 없습니다.")
        );
        product.updateByItemDto(itemDto);
        return id;
    }
}
