package com.example.menu;
import com.example.oanda.v20.ContextBuilder;
import com.example.oanda.v20.pricing.ClientPrice;
import com.example.oanda.v20.pricing.PricingGetRequest;
import com.example.oanda.v20.pricing.PricingGetResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import com.example.oanda.v20.account.AccountID;
import com.example.oanda.v20.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class AktInfoController {


    @FXML
    private TextArea tablazat;
    @FXML
    private Label welcomeText;
    @FXML
    protected void aktarak() {
        try {
            Context ctx = new ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("PricePolling").build();
            AccountID accountId = Config.ACCOUNTID;
            List<String> instruments = new ArrayList<>( Arrays.asList("EUR_USD", "USD_JPY", "GBP_USD", "USD_CHF"));
            PricingGetRequest request = new PricingGetRequest(accountId, instruments);
            PricingGetResponse resp = ctx.pricing.get(request);
                tablazat.setText("Aktuális árak");
            for (ClientPrice price : resp.getPrices()) {
                tablazat.setText(tablazat.getText() + "\n\n" + price.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}