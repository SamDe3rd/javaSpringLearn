package com.spring.springLearn.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.spring.springLearn.client.ClientApi;
import com.spring.springLearn.model.FunApiResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.ArrayList;
import java.util.List;

@Service
public class FunService {

    private final ClientApi clientApi;

    public FunService(ClientApi clientApi) {
        this.clientApi = clientApi;
    }

    public Mono<FunApiResponse> constructFunApiResponse(){

        Mono<Tuple2<JsonNode, JsonNode>> apiResponses = Mono.zip(
                clientApi.getCallToJsNode("catFactApi", "https://catfact.ninja/fact"),
                clientApi.getCallToJsNode("jokeApi", "https://official-joke-api.appspot.com/random_joke"));

        Mono<FunApiResponse> finalResponse = apiResponses.map(tuple -> {
            JsonNode catFactJsNode = tuple.getT1();
            JsonNode jokeJsNode = tuple.getT2();

            FunApiResponse.Joke joke = FunApiResponse.Joke.builder()
                    .setup(jokeJsNode.path("setup").asText())
                    .punchline(jokeJsNode.path("punchline").asText())
                    .build();

            List<FunApiResponse.Joke> jokeList = new ArrayList<>();
            jokeList.add(joke);

            return FunApiResponse.builder()
                    .catFact(catFactJsNode.path("fact").asText())
                    .joke(jokeList)
                    .build();

        });

        return finalResponse;
    }

}
