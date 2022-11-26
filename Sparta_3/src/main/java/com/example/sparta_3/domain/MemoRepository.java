package com.example.sparta_3.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


//쿼리 메소드 리턴 타입은 Page<T>, Slice<T>, List<T> 와 같은 Collection<T> 형태이며
// 엔티티에서 ()에 해당하는 값을 조회해서 List 컬렉션 타입으로 리턴
public interface MemoRepository extends JpaRepository<Memo, Long> { //JpaRepository 상속, Memo라는 녀석의 id가 Long인 녀석

    //수정 전
//    List<Memo> findAllByOrderByModifiedAtDesc(); // 생성시간 최신순을 정렬해줘라

    //수정 후
    List<Memo> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);
//    findAll: 모두 찾아라
//    ByModifiedAtBetween: 수정된 시간들 사이(start,end) 에서
//    OrderByModifiedAtDesc: 수정된 시간을 기준으로 내림차순

}
