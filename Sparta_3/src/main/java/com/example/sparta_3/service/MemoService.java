package com.example.sparta_3.service;

import com.example.sparta_3.domain.Memo;
import com.example.sparta_3.domain.MemoRepository;
import com.example.sparta_3.domain.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public Long update(Long id, MemoRequestDto requestDto){
        Memo memo=memoRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("아이디가 존재하지 않는다.")
        );
        memo.update(requestDto);
        return memo.getId();
    }
}
