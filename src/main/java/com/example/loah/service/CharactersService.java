package com.example.loah.service;

import com.example.loah.dto.response.CharactersResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CharactersService {
    private final String requestApp = "application/json";
    private final String apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAyNDYzMTUifQ.BoK9VT1tbvg2-0_yHNzUwvbf6vUvMZrxeBBgSzrt4aFJcNi98t_tcO870VuqbuNUWTApU7uRNbpO23lXj4kaLfHQSeIpU1ZYfQfTorUyN8S72G0qTFH3mifqO1WMhVSL3imq-d26yuanbgWHkLT_xTr-juzPU2zDDtVdjfndNKiIPJo3wLLxAwQ5ZE3pOk_zx0F6KecHPMv3Fl-LwuuS5dj5vQYMfPN0Bet3J6LdvxZSGaY5GxTByPfeIALgeN5W7Q6wTiYeYAZez76dRnv6XXJ0DQ6Yn_aMqrUmolaYbCbWyIZWxwPZgpfJQMnfQnKU7o05n4pviil2Yf2lr8gOmQ";

    /**
     * 랭크 리스트를 가져옵니다.
     *
     * @param keyword
     * @return
     */
    public void getRankList(String keyword){

        CharactersResponse.CharactersList charactersList = sendHttpRequest(makeHttpRequest(keyword));

        System.out.println(charactersList.getBody());

    }

    /**
     * accpet와 authorization헤더를 추가하여 HttpRequest를 생성합니다.
     *
     * @param keyword 검색할 keyword
     * @return HttpRequest
     */
    public HttpRequest makeHttpRequest(String keyword){

        URI uri = URI.create("https://developer-lostark.game.onstove.com/characters/"+ keyword +"/siblings");

        log.info("uri:"+uri.toString());

        return HttpRequest.newBuilder()
                .uri(uri)   // 문자열 자동 incoding
                .header("accept","application/json")
                .header("Authorization","Bearer "+apiKey)
                .build();
    }

    /**
     * HttpRequest를 전송합니다.
     *
     * @param request HttpRequest
     * @return HttpResponse<String>
     */
    public CharactersResponse.CharactersList sendHttpRequest(HttpRequest request){

        try {

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            log.info("Status Code: {}, body: {}", response.statusCode(), response.body());

            return CharactersResponse.CharactersList.builder()
                    .StatusCode(response.statusCode())
                    .body(response.body())
                    .build();

        }catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

    }

}
