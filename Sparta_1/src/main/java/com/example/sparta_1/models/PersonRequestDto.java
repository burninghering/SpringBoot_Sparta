package com.example.sparta_1.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PersonRequestDto {

    private int age;

    private String name;

    public PersonRequestDto(int age,String name){
        this.age=age;
        this.name=name;
    }
}
