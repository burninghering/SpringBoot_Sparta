package com.example.sparta_1.domain;

import com.example.sparta_1.models.PersonRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String name;

    public Person(int age, String name) {
        this.age=age;
        this.name=name;
    }

    public void update(PersonRequestDto requestDto){
        this.age=requestDto.getAge();
        this.name=requestDto.getName();
    }

    public Person(PersonRequestDto personRequestDto){
        this.age=personRequestDto.getAge();
        this.name=personRequestDto.getName();
    }

}
