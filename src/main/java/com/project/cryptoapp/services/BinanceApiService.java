package com.project.cryptoapp.services;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerStatistics;

public class BinanceApiService {

    public BinanceApiRestClient createClient(){
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("API-KEY", "SECRET");
        return factory.newRestClient();
    }

    public String getTickerBySymbol(String symbol){
        BinanceApiRestClient client = createClient();
        TickerStatistics tickerStatistics = client.get24HrPriceStatistics(symbol);
        return tickerStatistics.getLastPrice();
    }

}
