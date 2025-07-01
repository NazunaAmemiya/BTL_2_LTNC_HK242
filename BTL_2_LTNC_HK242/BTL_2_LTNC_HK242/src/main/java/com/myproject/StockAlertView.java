package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        // TODO: Implement constructor
        this.alertThresholdHigh = highThreshold;
        this.alertThresholdLow = lowThreshold;
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement alert logic based on threshold conditions

        double price = stockPrice.getAvgPrice();

        
        if(lastAlertedPrices.containsKey(stockPrice.getCode())){
            if(lastAlertedPrices.get(stockPrice.getCode()) == price){
                return;
            }
        }

        if (price >= alertThresholdHigh){
            alertAbove(stockPrice.getCode(), price);
            
        }
        else if (price <= alertThresholdLow){
            alertBelow(stockPrice.getCode(), price);
        }
        lastAlertedPrices.put(stockPrice.getCode(),price);
    }

    private void alertAbove(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        Logger.logAlert(stockCode, price);

        //Logger.notImplementedYet("alertAbove");
    }

    private void alertBelow(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        Logger.logAlert(stockCode, price);

        //Logger.notImplementedYet("alertBelow");
    }
}
