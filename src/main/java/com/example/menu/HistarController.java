package com.example.menu;
import com.example.oanda.v20.ContextBuilder;
import com.example.oanda.v20.instrument.Candlestick;
import com.example.oanda.v20.instrument.InstrumentCandlesRequest;
import com.example.oanda.v20.instrument.InstrumentCandlesResponse;
import com.example.oanda.v20.primitives.InstrumentName;
import javafx.fxml.FXML;
import com.example.oanda.v20.Context;
import javafx.scene.control.TextArea;
import static com.example.oanda.v20.instrument.CandlestickGranularity.H1;
public class HistarController {
    @FXML
    private TextArea tablazat;
    @FXML
    protected void histarak() {
        try {
            Context ctx = new ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("HistorikusAdatok").build();
            InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName("EUR_USD"));
            request.setGranularity(H1);
            request.setCount(10L); // 10 adat L: long adattípus
            InstrumentCandlesResponse resp = ctx.instrument.candles(request);
            for(Candlestick candle: resp.getCandles())
                tablazat.setText(tablazat.getText() + "\n" + candle.toString());
            for(Candlestick candle: resp.getCandles())
                tablazat.setText(tablazat.getText() + "\n" + candle.getTime()+"\t"+candle.getMid().getC());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
