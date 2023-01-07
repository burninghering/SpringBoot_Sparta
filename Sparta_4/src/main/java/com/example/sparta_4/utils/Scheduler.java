package com.example.sparta_4.utils;

import com.example.sparta_4.domain.Product;
import com.example.sparta_4.dto.ItemDto;
import com.example.sparta_4.repository.ProductRepository;
import com.example.sparta_4.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor // final 멤버 변수를 자동으로 생성합니다.
@Component // 스프링이 필요 시 자동으로 생성하는 클래스 목록에 추가합니다.
// 스프링이 자동으로 생성해서 실행해야 하므로 이거 권한을 갖는 녀석이라고 등록해줘야 함
public class Scheduler {

    //아래의 것들을 잘 주입하기 위해 @RequiredArgsConstructor 필요
    private final ProductRepository productRepository; //멤버 변수로 선언
    private final ProductService productService; //private final을 붙여주면
    private final NaverShopSearch naverShopSearch; //내가 필요한 메소드를 실행할 때 이 녀석들이 필요하면 스프링이 책임짐

    // 초, 분, 시, 일, 월, 주 순서
    @Scheduled(cron = "0 0 1 * * *") //이 시간과 맞을 때 updatePrice를 실행하는 것
    //cron은 예약한 시간에 작동하는 것 "초 분 시 일 월 주" *-> 뭐든지 상관 없다
    // "* * 1 * * *" -> 1시 00분부터 1시 59분까지 매 초 실행됨
    public void updatePrice() throws InterruptedException { //새벽 1시마다 돌면서 체크하는 함수
        //throws InterruptedException 오류가 발생하면 -> 방해가 생겼다가 오류를 띄우라는 의미
        System.out.println("가격 업데이트 실행");

        // 저장된 모든 관심상품을 조회합니다.
        List<Product> productList = productRepository.findAll();

        for (int i=0; i<productList.size(); i++) {
            // 1초에 한 상품 씩 조회합니다 (Naver 제한)
            TimeUnit.SECONDS.sleep(1); //time단위로 초마다 쉬어라, timeout -> 1초마다 한번 씩 쉬어라

            // i 번째 관심 상품을 꺼냅니다.
            Product p = productList.get(i);

            // i 번째 관심 상품의 제목으로 검색을 실행합니다.
            String title = p.getTitle();
            String resultString = naverShopSearch.search(title);

            // i 번째 관심 상품의 검색 결과 목록 중에서 첫 번째 결과를 꺼냅니다.
            //유사성을 검색할테니까 제일 위에 있는 것이 내가 원하는 물건이라고 판단
            List<ItemDto> itemDtoList = naverShopSearch.fromJSONtoItems(resultString);
            ItemDto itemDto = itemDtoList.get(0);

            // i 번째 관심 상품 정보를 업데이트합니다.
            Long id = p.getId(); //내가 원하는 녀석의 아이디가 내가 찾는 아이디라고 판단 후 id에 넣기
            productService.updateBySearch(id, itemDto);
        }
    }
}
