package com.example.sparta_1.Service;

import com.example.sparta_1.domain.Person;
import com.example.sparta_1.domain.PersonRepository;
import com.example.sparta_1.models.PersonRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    @Transactional
    public Long update(Long id, PersonRequestDto requestDto){
        Person person=personRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        person.update(requestDto);
        return person.getId();
    }
}
