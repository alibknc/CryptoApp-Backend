package com.project.cryptoapp.controller;

import com.project.cryptoapp.entities.Record;
import com.project.cryptoapp.repositories.RecordRepository;
import com.project.cryptoapp.services.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public HashMap<String, Object> getTickerBySymbol(@RequestParam String symbol, HttpServletRequest request){
        HashMap<String, Object> result =  apiService.getTickerBySymbol(symbol);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Record record = new Record();
        record.setIp(request.getRemoteAddr());
        record.setSymbol(symbol);
        record.setPrice(Double.parseDouble((String) result.get("lastPrice")));
        record.setTime(formatter.format(date));

        recordRepository.save(record);
        return result;
    }

    @GetMapping("/info")
    public HashMap<String, Object> getSymbolList(){
        return apiService.getSymbolList();
    }
}
