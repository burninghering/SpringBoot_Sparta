package com.example.sparta_1.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface
PersonRepository extends JpaRepository<Person,Long> {
}
