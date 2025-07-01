package com.myproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    // TODO: Implement Singleton pattern
    private StockFeeder() {
        
    }

    public static StockFeeder getInstance() {
        // TODO: Implement Singleton logic
        if(instance == null) {
            instance = new StockFeeder();
        }
        return instance;
    }

    public void addStock(Stock stock) {
        // TODO: Implement adding a stock to stockList
        for(int i=0;i<stockList.size();i++){
            if(stockList.get(i).getCode() == stock.getCode()){
                stockList.remove(i);
                stockList.add(stock);
                return;
            }
        }

        stockList.add(stock);
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // TODO: Implement registration logic, including checking stock existence
        for(Integer i=0;i<stockList.size();i++){
            if(stockList.get(i).getCode() == code){
                if(viewers.containsKey(code)){
                    if(viewers.get(code).contains(stockViewer)){
                        break;
                    }
                    viewers.get(code).add(stockViewer);
                    return;
                }
                else{
                    List<StockViewer> newViewers = new ArrayList<>();
                    newViewers.add(stockViewer);
                    viewers.put(code,newViewers);
                    return;
                }
            }
        }
        Logger.errorRegister(code);
    }    

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // TODO: Implement unregister logic, including error logging
        if(!viewers.containsKey(code)){
            Logger.errorUnregister(code);
            return;
        }
        for(Integer i=0;i<stockList.size();i++){
            if(stockList.get(i).getCode() == code){
                if(viewers.containsKey(code)){
                   if(viewers.get(code).contains(stockViewer)){
                       viewers.get(code).remove(stockViewer);
                       return;
                   }
                }
            }
        }
        Logger.errorUnregister(code);
    }

    public void notify(StockPrice stockPrice) {
        // TODO: Implement notifying registered viewers about price updates
        if(!viewers.containsKey(stockPrice.getCode())){
            return;
        }

        List<StockViewer> viewerList = viewers.get(stockPrice.getCode());
        for( int i=0;i<viewerList.size();i++){
            viewerList.get(i).onUpdate(stockPrice);
        }   
    }
}
