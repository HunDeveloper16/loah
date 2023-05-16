package com.example.loah.dto.response;

import lombok.Builder;
import lombok.Data;

public class CharactersResponse {

    @Data
    @Builder
    public static class CharactersList{

        private String body;

        private int StatusCode;

    }

}
