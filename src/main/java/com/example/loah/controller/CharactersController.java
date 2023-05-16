package com.example.loah.controller;

import com.example.loah.service.CharactersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CharactersController {

    private final CharactersService charactersService;

    /**
     * 랭킹 정보 리스트를 가져옵니다.
     *
     * @return
     */
    @GetMapping(path = "/{request}")
    public ResponseEntity<?> getRankList(@PathVariable String request) throws IOException, InterruptedException {

        charactersService.getRankList(request);

        return null;

    }

}
