package com.spring.springLearn.client;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ClientApi {

    private final WebClient webClient;

    public ClientApi(WebClient webClient) {
        this.webClient = webClient;
    }

    /** api: catFact **/
    public Mono<JsonNode> callCatFactApi(){
        return webClient.get()
                .uri("https://catfact.ninja/fact")
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    /** api: joke **/
    public Mono<JsonNode> callJokeApi(){
        return webClient.get()
                .uri("https://official-joke-api.appspot.com/random_joke")
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    /** General get call to JsonNode **/
     public Mono<JsonNode> getCallToJsNode(String apiName, String apiUrl) {
         return webClient.get()
                 .uri(apiUrl).retrieve()
                 .bodyToMono(JsonNode.class)
                 .doOnNext(response -> log.info("{}: {}", apiName, response));
     }


}
