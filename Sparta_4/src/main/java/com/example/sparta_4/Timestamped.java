package com.example.sparta_4;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //멤버 변수가 컬럼이 되도록 해주는 어노테이션
@EntityListeners(AuditingEntityListener.class) //변경 시점 자동 기록
public abstract class Timestamped{

    @CreatedDate
    private LocalDateTime creatAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
