package com.project.cryptoapp.controller;

import com.project.cryptoapp.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
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
        log.debug("getHello is called");
        return "Hello World!";
    }

    @GetMapping("/ticker")
    public HashMap<String, Object> getTickerBySymbol(@RequestParam String symbol, HttpServletRequest request){
        log.debug("getTickerBySymbol is called");
        //return apiService.getTickerBySymbol(symbol, request);
        return webSocketService.getTickerBySymbol(symbol, request);
    }

    @GetMapping("/info")
    public HashMap<String, Object> getSymbolList(){
        log.debug("getSymbolList is called");
        return apiService.getSymbolList();
    }
}
