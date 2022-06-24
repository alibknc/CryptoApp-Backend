package com.project.cryptoapp.services;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerStatistics;
import org.springframework.stereotype.Service;

@Service
public class BinanceApiService {

    public BinanceApiRestClient createClient(){
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        return factory.newRestClient();
    }

    public String getTickerBySymbol(String symbol){
        BinanceApiRestClient client = createClient();
        TickerStatistics tickerStatistics = client.get24HrPriceStatistics(symbol);
        return tickerStatistics.getLastPrice();
    }

}
