package com.example.menu;

import com.example.oanda.v20.Context;
import com.example.oanda.v20.ContextBuilder;
import com.example.oanda.v20.account.AccountID;
import com.example.oanda.v20.trade.TradeCloseRequest;
import com.example.oanda.v20.trade.TradeSpecifier;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class PzarController {
    static Context ctx;
    static AccountID accountId;
    @FXML
    private TextArea tablazat;

    @FXML
    private void pozzar() {

        try {
            ctx = new ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("StepByStepOrder").build();
            accountId = Config.ACCOUNTID;
            if (true) Zaras();
            tablazat.setText(tablazat.getText() + "\n" + "Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Zaras() {
        tablazat.setText(tablazat.getText() + "\n" + "Close a Trade");
        try {
            String tradeId = "41"; // a zárni kívánt tradeId
            ctx.trade.close(new TradeCloseRequest(accountId, new TradeSpecifier(tradeId)));
            tablazat.setText(tablazat.getText() + "\n" + "tradeId: " + tradeId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

