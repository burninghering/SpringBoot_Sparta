package com.example.sparta_4.utils;


import com.example.sparta_4.dto.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//ARC에서 자바 코드 그대로 긁어옴
@Component // 이제부터, @RequiredArgsConstructor 와 함께 사용할 경우 스프링이 자동으로 생성합니다.
public class NaverShopSearch {
    public String search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "zdqMoIkFaK8uKvC2oNY2");
        headers.add("X-Naver-Client-Secret", "LiZfsgtuD5");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=" + query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }

    public List<ItemDto> fromJSONtoItems(String result) {
        JSONObject rjson = new JSONObject(result); //검색 결과를 넣은 rjson 객체 만들기
        JSONArray items  = rjson.getJSONArray("items"); //JSON 배열 items에 집어넣기
        List<ItemDto> ret = new ArrayList<>(); //ret이라는 새로운 배열 만들기
        for (int i=0; i<items.length(); i++) {
            JSONObject itemJson = (JSONObject) items.get(i); //items 배열에 있는 요소들 하나씩 itemjson객체에 주기
            ItemDto itemDto = new ItemDto(itemJson); //itemDto에 원하는 정보만 들어감
            ret.add(itemDto); //그것을 새로운 배열 ret에 그대로 넣어줌
        }
        return ret;
    }



    public static void main(String[] args){

    }
}
