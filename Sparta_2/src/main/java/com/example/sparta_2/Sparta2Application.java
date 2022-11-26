package com.example.sparta_2;

import com.example.sparta_2.domain.Course;
import com.example.sparta_2.domain.CourseRepository;
import com.example.sparta_2.models.CourseRequestDto;
import com.example.sparta_2.service.CourseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class Sparta2Application {

    public static void main(String[] args) {

        SpringApplication.run(Sparta2Application.class, args);
    }

    // Week02Application.java 의 main 함수 아래에 붙여주세요.
    @Bean
    public CommandLineRunner demo(CourseRepository courseRepository, CourseService courseService) {
        return (args) -> {
            courseRepository.save(new Course("혜린이의 이름은","김헤린 입니다"));

            System.out.println("데이터 인쇄");
            List<Course> courseList = courseRepository.findAll();
            for (int i=0;i<courseList.size();i++){
                Course course = courseList.get(i);
                System.out.println(course.getId());
                System.out.println(course.getTitle());
                System.out.println(course.getTutor());
            }

            CourseRequestDto requestDto = new CourseRequestDto("형수의 이름은","류형수 입니다");
            courseService.update(1L,requestDto);
            courseList=courseRepository.findAll();
            for (int i=0;i<courseList.size();i++){
                Course course=courseList.get(i); //리스트에 있는 한 요소마다 Id,Title,Tutor를 알고 싶어서
                System.out.println(course.getId());
                System.out.println(course.getTitle());
                System.out.println(course.getTutor());
            }
        };
    }
}
