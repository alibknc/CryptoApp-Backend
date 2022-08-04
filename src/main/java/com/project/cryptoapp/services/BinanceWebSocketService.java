package com.project.cryptoapp.services;

import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.project.cryptoapp.entities.AggTradeObject;
import com.project.cryptoapp.entities.Record;
import com.project.cryptoapp.repositories.AggTradeObjectRepository;
import com.project.cryptoapp.repositories.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@Service
public class BinanceWebSocketService {
    private SimpMessagingTemplate template;

    @Value("#{'${symbols}'}")
    private String symbolList;

    private HashMap<String, Object> results;
    private final RecordRepository recordRepository;
    private final AggTradeObjectRepository aggTradeObjectRepository;

    public BinanceWebSocketService(RecordRepository recordRepository, AggTradeObjectRepository aggTradeObjectRepository, SimpMessagingTemplate template) {
        this.recordRepository = recordRepository;
        this.aggTradeObjectRepository = aggTradeObjectRepository;
        this.template = template;
    }

    @PostConstruct
    public void init() {
        results = new HashMap<>();
        for (String symbol : symbolList.split(",")) {
            results.put(symbol, -1);
        }

        createWebSocketClient();
    }

    public void createWebSocketClient() {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        BinanceApiWebSocketClient client = factory.newWebSocketClient();

        client.onAggTradeEvent(symbolList.toLowerCase(), new BinanceApiCallback<AggTradeEvent>() {
            @Override
            public void onResponse(final AggTradeEvent response) {
                saveLastResponse(response);
            }

            @Override
            public void onFailure(final Throwable cause) {
                System.err.println("Web socket failed");
                cause.printStackTrace(System.err);
            }
        });
    }

    public void saveLastResponse(AggTradeEvent response) {
        results.replace(response.getSymbol(), response.getPrice());

        template.convertAndSend("/topic/lastprice", response);

        AggTradeObject tradeObject = new AggTradeObject(response);
        aggTradeObjectRepository.save(tradeObject);

        log.trace("Last price from websocket for {}: {}", response.getSymbol(), response.getPrice());
    }

    public HashMap<String, Object> getTickerBySymbol(String symbol, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("lastPrice", results.get(symbol));

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Record record = new Record();
        record.setIp(request.getRemoteAddr());
        record.setSymbol(symbol);
        record.setPrice(Double.parseDouble((String) result.get("lastPrice")));
        record.setTime(formatter.format(date));
        recordRepository.save(record);

        log.trace("The received data is saved in the database as {}", symbol);

        return result;
    }

}
