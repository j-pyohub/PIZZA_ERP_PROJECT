package com.erp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class AddressRestController {
    @Value("${juso.addrKey.key}")
    private String addrKey;

    @Value("${juso.coordKey.key}")
    private String coordKey;

    @GetMapping("/addr")
    public ResponseEntity<Map<String, Object>> getAddress() throws JsonProcessingException {
        StringBuilder uri = new StringBuilder("https://business.juso.go.kr/addrlink/addrLinkApi.do");
        uri.append("?confmKey=").append(addrKey);
        uri.append("&keyword=").append("과천대로 1길 15");
        uri.append("&resultType=json");

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> result = restTemplate.getForObject(uri.toString(), Map.class);
        System.out.println(result);

        if(result == null){
            return ResponseEntity.notFound().build();
        }
//        String result = restTemplate.getForObject(uri.toString(), String.class);
//
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Object> json = mapper.readValue(result, Map.class);
//
//        if(result != null) return ResponseEntity.ok().body(json);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("coord")
    public ResponseEntity<Map<String, Object>> getCoord(@RequestParam String admCd,
                                                        @RequestParam String rnMgtSn,
                                                        @RequestParam String buldMnnm,
                                                        @RequestParam String buldSlno) throws JsonProcessingException {
        StringBuilder uri = new StringBuilder("https://business.juso.go.kr/addrlink/addrCoordApi.do");
        uri.append("?confmKey=").append(coordKey);
        uri.append("&admCd=").append(admCd);
        uri.append("&rnMgtSn=").append(rnMgtSn);
        uri.append("&udrtYn=0");
        uri.append("&buldMnnm=").append(buldMnnm);
        uri.append("&buldSlno=").append(buldSlno);
        uri.append("&resultType=json");

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> result = restTemplate.getForObject(uri.toString(), Map.class);

        System.out.println(result);

        if(result == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(result);
    }
}
