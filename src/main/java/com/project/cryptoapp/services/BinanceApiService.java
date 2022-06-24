package com.project.cryptoapp.services;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerStatistics;
import com.binance.api.client.exception.BinanceApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
public class BinanceApiService {
    @Value("#{'${symbols}'.split(',')}")
    private List<String> symbolList;

    public BinanceApiRestClient createClient(){
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        return factory.newRestClient();
    }

    public HashMap<String, Object> getTickerBySymbol(String symbol){
        BinanceApiRestClient client = createClient();
        TickerStatistics tickerStatistics;
        HashMap<String, Object> result = new HashMap<>();

        try{
            tickerStatistics = client.get24HrPriceStatistics(symbol);
            result.put("lastPrice", tickerStatistics.getLastPrice());
            return result;
        }catch (BinanceApiException e){
            result.put("lastPrice", -1);
            return result;
        }
    }

    public HashMap<String, Object> getSymbolList(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("symbols", symbolList);
        return result;
    }
}
