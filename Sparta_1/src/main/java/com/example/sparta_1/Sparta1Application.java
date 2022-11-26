package com.example.sparta_1;

import com.example.sparta_1.Service.PersonService;
import com.example.sparta_1.domain.Person;
import com.example.sparta_1.domain.PersonRepository;
import com.example.sparta_1.models.PersonRequestDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Sparta1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sparta1Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(PersonRepository personRepository, PersonService personService) {
		return (args) -> {
			personRepository.save(new Person(26,"김혜린"));
			personRepository.save(new Person(31,"류형수"));
			List<Person> personList=personRepository.findAll();
			for (int i=0;i<personList.size();i++){
				Person person=personList.get(i);
				System.out.println(person.getName());
			}

			PersonRequestDto personRequestDto = new PersonRequestDto(31,"류형수");
			personService.update(1L,personRequestDto);

		};
	}
}