package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement logic to check if price has changed and log it
        String code = stockPrice.getCode();
        double price = stockPrice.getAvgPrice();


        if(lastPrices.containsKey(code)){
            if(lastPrices.get(code) != price){
                Logger.logRealtime(code, price);   
            }
        }
        else{
            //Logger.logRealtime(code, price);
        }
        lastPrices.put(code, price);
    }
}
