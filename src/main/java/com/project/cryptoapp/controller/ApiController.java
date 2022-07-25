package com.project.cryptoapp.controller;

import com.project.cryptoapp.services.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private final BinanceApiService apiService;
    private final BinanceWebSocketService webSocketService;

    public ApiController(BinanceApiService apiService, BinanceWebSocketService webSocketService) {
        this.apiService = apiService;
        this.webSocketService = webSocketService;
    }

    @GetMapping("/")
    public String getHello(){
        return "Hello World!";
    }

    @GetMapping("/ticker")
    public HashMap<String, Object> getTickerBySymbol(@RequestParam String symbol, HttpServletRequest request){
        //return apiService.getTickerBySymbol(symbol, request);
        return webSocketService.getTickerBySymbol(symbol, request);
    }

    @GetMapping("/info")
    public HashMap<String, Object> getSymbolList(){
        return apiService.getSymbolList();
    }
}
