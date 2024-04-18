package service;


import models.Currency;
import java.io.IOException;


public class CurrencyService {

    CurrencyManager currencyManager;

    public CurrencyService(CurrencyManager currencyManager) throws IOException {
      this.currencyManager=new CurrencyManager();
    }

    public Currency typeExchange(String currency) throws IOException, InterruptedException {
        return currencyManager.typeExchange(currency);
    }
    public Currency pairConversion(String currencyBase, String currencyTarget) throws IOException, InterruptedException {
        return currencyManager.pairConversion(currencyBase,currencyTarget);
    }

}
