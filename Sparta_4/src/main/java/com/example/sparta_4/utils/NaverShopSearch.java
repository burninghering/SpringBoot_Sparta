package com.example.sparta_4.utils;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class NaverShopSearch{
    public String search(String query){

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "IDtmePcJq67Dqc_9wJeC");
        headers.add("X-Naver-Client-Secret", "KiAyT_TCh7");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=adidas&display=30&start=30&sort=dsc", HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);
        return response;
    }

    public static void main(String[] args){
//        NaverShopSearch naverShopSearch = new NaverShopSearch();
//        naverShopSearch.search("아디다스");
    }
}
