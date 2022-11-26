package com.example.sparta_1.Controller;

import com.example.sparta_1.Service.PersonService;
import com.example.sparta_1.domain.Person;
import com.example.sparta_1.domain.PersonRepository;
import com.example.sparta_1.models.PersonRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController //- 클라이언트의 요청(Request)을 전달받는 코드를 Controller 라고 부릅니다.
//→ JSON 만을 돌려주는 것은 RestController
public class PersonController {

    private final PersonRepository personRepository;

    @GetMapping("/api/persons")
    public List<Person> getPerson(){
        return personRepository.findAll();
    }

    @PostMapping("/api/persons")
    public Person createPerson(@RequestBody PersonRequestDto personRequestDto){
        Person person = new Person(personRequestDto);
        return personRepository.save(person);
    }

    private final PersonService personService;

    @PutMapping("/api/persons/{id}")
    public Long updatePerson(@PathVariable Long id,@RequestBody PersonRequestDto personRequestDto){
        return personService.update(id,personRequestDto);
    }

    @DeleteMapping("/api/persons/{id}")
    public Long deletePerson(@PathVariable Long id){
        personRepository.deleteById(id);
        return id;
    }

}



