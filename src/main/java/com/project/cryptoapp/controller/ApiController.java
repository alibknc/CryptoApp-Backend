package com.project.cryptoapp.controller;

import com.project.cryptoapp.entities.Record;
import com.project.cryptoapp.repositories.RecordRepository;
import com.project.cryptoapp.services.*;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private final BinanceApiService apiService;
    private final RecordRepository recordRepository;

    public ApiController(BinanceApiService apiService, RecordRepository databaseService) {
        this.apiService = apiService;
        this.recordRepository = databaseService;
    }

    @GetMapping("/")
    public String getHello(){
        return "Hello World!";
    }

    @GetMapping("/ticker")
    public HashMap<String, Object> getTickerBySymbol(@RequestParam String symbol){
        return apiService.getTickerBySymbol(symbol);
    }

    @GetMapping("/info")
    public HashMap<String, Object> getSymbolList(){
        return apiService.getSymbolList();
    }

    @PostMapping("/save/record")
    public Record saveRecord(@RequestBody Record record){
        return recordRepository.save(record);
    }
}
