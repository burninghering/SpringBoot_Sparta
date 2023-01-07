package com.example.sparta_4.domain;

import com.example.sparta_4.dto.ItemDto;
import com.example.sparta_4.dto.ProductMypriceRequestDto;
import com.example.sparta_4.dto.ProductRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    public Product(ProductRequestDto productRequestDto){
        this.title=productRequestDto.getTitle();
        this.image=productRequestDto.getImage();
        this.link=productRequestDto.getLink();
        this.lprice= productRequestDto.getLprice();
        this.myprice=0;
    }

    public void update(ProductMypriceRequestDto productMypriceRequestDto){
        this.myprice=productMypriceRequestDto.getMyprice();
    }

    public void updateByItemDto(ItemDto itemDto){
        this.lprice=itemDto.getLprice();
    }

}
