package com.project.cryptoapp.entities;

import com.binance.api.client.domain.event.AggTradeEvent;
import javax.persistence.*;

@Entity
@Table(name="agg_trade")
public class AggTradeObject {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="event_type")
    private String eventType;

    @Column(name="event_time")
    private long eventTime;

    @Column(name="symbol")
    private String symbol;

    @Column(name="aggregated_trade_id")
    private long aggregatedTradeId;

    @Column(name="price")
    private String price;

    @Column(name="quantity")
    private String quantity;

    @Column(name="first_breakdown_trade_id")
    private long firstBreakdownTradeId;

    @Column(name="last_breakdown_trade_id")
    private long lastBreakdownTradeId;

    @Column(name="trade_time")
    private long tradeTime;

    @Column(name="is_buyer_maker")
    private boolean isBuyerMaker;

    public AggTradeObject(int id, String eventType, long eventTime, String symbol, long aggregatedTradeId, String price, String quantity, long firstBreakdownTradeId, long lastBreakdownTradeId, long tradeTime, boolean isBuyerMaker) {
        this.id = id;
        this.eventType = eventType;
        this.eventTime = eventTime;
        this.symbol = symbol;
        this.aggregatedTradeId = aggregatedTradeId;
        this.price = price;
        this.quantity = quantity;
        this.firstBreakdownTradeId = firstBreakdownTradeId;
        this.lastBreakdownTradeId = lastBreakdownTradeId;
        this.tradeTime = tradeTime;
        this.isBuyerMaker = isBuyerMaker;
    }

    public AggTradeObject(AggTradeEvent event) {
        this.eventType = event.getEventType();
        this.eventTime = event.getEventTime();
        this.symbol = event.getSymbol();
        this.aggregatedTradeId = event.getAggregatedTradeId();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
        this.firstBreakdownTradeId = event.getFirstBreakdownTradeId();
        this.lastBreakdownTradeId = event.getLastBreakdownTradeId();
        this.tradeTime = event.getTradeTime();
        this.isBuyerMaker = event.isBuyerMaker();
    }

    public AggTradeObject() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getAggregatedTradeId() {
        return aggregatedTradeId;
    }

    public void setAggregatedTradeId(long aggregatedTradeId) {
        this.aggregatedTradeId = aggregatedTradeId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public long getFirstBreakdownTradeId() {
        return firstBreakdownTradeId;
    }

    public void setFirstBreakdownTradeId(long firstBreakdownTradeId) {
        this.firstBreakdownTradeId = firstBreakdownTradeId;
    }

    public long getLastBreakdownTradeId() {
        return lastBreakdownTradeId;
    }

    public void setLastBreakdownTradeId(long lastBreakdownTradeId) {
        this.lastBreakdownTradeId = lastBreakdownTradeId;
    }

    public long getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(long tradeTime) {
        this.tradeTime = tradeTime;
    }

    public boolean isBuyerMaker() {
        return isBuyerMaker;
    }

    public void setBuyerMaker(boolean buyerMaker) {
        isBuyerMaker = buyerMaker;
    }
}