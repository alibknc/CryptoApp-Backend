package com.project.cryptoapp.services;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerStatistics;
import com.binance.api.client.exception.BinanceApiException;
import com.project.cryptoapp.entities.Record;
import com.project.cryptoapp.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class BinanceApiService {
    @Value("#{'${symbols}'.split(',')}")
    private List<String> symbolList;

    private final RecordRepository recordRepository;

    public BinanceApiService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public BinanceApiRestClient createClient() {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        return factory.newRestClient();
    }

    public HashMap<String, Object> getTickerBySymbol(String symbol, HttpServletRequest request) {
        BinanceApiRestClient client = createClient();
        TickerStatistics tickerStatistics;
        HashMap<String, Object> result = new HashMap<>();

        try {
            tickerStatistics = client.get24HrPriceStatistics(symbol);
            result.put("lastPrice", tickerStatistics.getLastPrice());
        } catch (BinanceApiException e) {
            result.put("lastPrice", -1);
        } finally {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            Record record = new Record();
            record.setIp(request.getRemoteAddr());
            record.setSymbol(symbol);
            record.setPrice(Double.parseDouble((String) result.get("lastPrice")));
            record.setTime(formatter.format(date));

            recordRepository.save(record);
        }

        return result;
    }

    public HashMap<String, Object> getSymbolList() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("symbols", symbolList);
        return result;
    }
}
