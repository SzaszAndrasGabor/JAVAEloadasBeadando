package com.example.menu;
import com.example.oanda.v20.ContextBuilder;
import com.example.oanda.v20.ExecuteException;
import com.example.oanda.v20.RequestException;
import com.example.oanda.v20.account.AccountID;
import com.example.oanda.v20.trade.Trade;
import javafx.fxml.FXML;
import com.example.oanda.v20.Context;
import javafx.scene.control.TextArea;
import java.util.List;
public class NyitottController {
    static Context ctx;
    static AccountID accountId;
    @FXML
    private TextArea tablazat;
    @FXML
    protected void onHelloButtonClick() {
        try {
            ctx = new ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("StepByStepOrder").build();
            accountId = Config.ACCOUNTID;
            if(true) nyitottpoz();
            tablazat.setText(tablazat.getText() + "\n" +"Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void nyitottpoz() throws ExecuteException, RequestException {
        tablazat.setText(tablazat.getText() + "\n" +"Nyitott tradek:");
        List<Trade> trades = ctx.trade.listOpen(accountId).getTrades();
        for(Trade trade: trades)
            tablazat.setText(tablazat.getText() + "\n" + trade.toString());
        for(Trade trade: trades)
            tablazat.setText(tablazat.getText() + "\n" +trade.getId()+"\t"+trade.getInstrument()+"\t"+trade.getOpenTime()+"\t"+trade.getCurrentUnits()+"\t"+trade.getPrice()+"\t"+trade.getUnrealizedPL());
    }


}
