package com.project.cryptoapp.controller;

import com.project.cryptoapp.services.BinanceApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private final BinanceApiService apiService;

    public ApiController(BinanceApiService apiService) {
        this.apiService = apiService;
    }
    @GetMapping("/")
    public String getHello(){
        return "Hello World!";
    }

    @GetMapping("/ticker")
    public String getTickerBySymbol(@RequestParam String symbol){
        return apiService.getTickerBySymbol(symbol);
    }
}
