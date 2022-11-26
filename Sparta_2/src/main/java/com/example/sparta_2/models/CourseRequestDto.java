package com.example.sparta_2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CourseRequestDto {
    private String title;
    private String tutor;

    public CourseRequestDto(String title,String tutor){
        this.title=title;
        this.tutor=tutor;
    }
}
