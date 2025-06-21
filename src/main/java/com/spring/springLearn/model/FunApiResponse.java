package com.spring.springLearn.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class FunApiResponse {

    private String catFact;
    private List<Joke> joke;

    @Builder
    @Data
    public static class Joke{
        private String setup;
        private String punchline;
    }

}
