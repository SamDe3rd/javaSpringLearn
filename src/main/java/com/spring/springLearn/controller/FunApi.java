package com.spring.springLearn.controller;

import com.spring.springLearn.model.FunApiResponse;
import com.spring.springLearn.service.FunService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FunApi {

    private final FunService funService;

    public FunApi(FunService funService) {
        this.funService = funService;
    }

    @GetMapping("haveFun")
    public ResponseEntity<Mono<FunApiResponse>> getFunApi(){
        return new ResponseEntity<>(funService.constructFunApiResponse(), HttpStatus.OK);
    }

}
