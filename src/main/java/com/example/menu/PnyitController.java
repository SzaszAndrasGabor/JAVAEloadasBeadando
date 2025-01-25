package com.example.menu;

import com.example.oanda.v20.ContextBuilder;
import com.example.oanda.v20.ExecuteException;
import com.example.oanda.v20.RequestException;
import com.example.oanda.v20.account.AccountID;
import com.example.oanda.v20.order.MarketOrderRequest;
import com.example.oanda.v20.order.OrderCreateRequest;
import com.example.oanda.v20.order.OrderCreateResponse;
import com.example.oanda.v20.primitives.InstrumentName;
import com.example.oanda.v20.trade.Trade;
import javafx.fxml.FXML;
import com.example.oanda.v20.Context;
import javafx.scene.control.TextArea;
import java.util.List;

    
public class PnyitController {
    static Context ctx;
    static AccountID accountId;
    @FXML
    private TextArea tablazat;
        @FXML
        protected void poznyit() {
            try {

                 ctx = new ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("StepByStepOrder").build();
                accountId = Config.ACCOUNTID;
                if(true) Nyitás();
                tablazat.setText(tablazat.getText() + "\n" +"Done");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        void Nyitás(){
            tablazat.setText(tablazat.getText() + "\n" +"Place a Market Order");
            InstrumentName instrument = new InstrumentName("AUD_USD");
            try {
                OrderCreateRequest request = new OrderCreateRequest(accountId);
                MarketOrderRequest marketorderrequest = new MarketOrderRequest();
                marketorderrequest.setInstrument(instrument);
// Ha pozitív, akkor LONG, ha negatív, akkor SHORT:
                marketorderrequest.setUnits(-10);
                request.setOrder(marketorderrequest);
                OrderCreateResponse response = ctx.order.create(request);
                tablazat.setText(tablazat.getText() + "\n" +"tradeId: "+response.getOrderFillTransaction().getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

}
