package com.example.menu;
import com.example.menu.models.OandaData;
import com.example.oanda.v20.ContextBuilder;
import com.example.oanda.v20.pricing.ClientPrice;
import com.example.oanda.v20.pricing.PricingGetRequest;
import com.example.oanda.v20.pricing.PricingGetResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.oanda.v20.account.AccountID;
import com.example.oanda.v20.Context;
import javafx.scene.control.cell.PropertyValueFactory;

import com.example.menu.models.OandaData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import com.example.oanda.v20.account.AccountID;
import com.example.oanda.v20.account.AccountSummary;
import com.example.oanda.v20.Context;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class SzamlaInfoController {
    @FXML
    private TextArea tablazat;

    @FXML TableView<OandaData> SzamlaInfotableView;
    @FXML
    private TableColumn<OandaData, String> nevColumn;
@FXML
private Button btn1;
    @FXML
    private TableColumn<OandaData, String> ertekColumn;
    // Adatlista
    private final ObservableList<OandaData> dataList = FXCollections.observableArrayList();


    @FXML
    protected void szamlainf() {
        try {
            Context ctx = new ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("PricePolling").build();
            AccountID accountId = Config.ACCOUNTID;
            List<String> instruments = new ArrayList<>(Arrays.asList("EUR_USD", "USD_JPY", "GBP_USD", "USD_CHF"));
            PricingGetRequest request = new PricingGetRequest(accountId, instruments);
            PricingGetResponse resp = ctx.pricing.get(request);
                tablazat.setText("Számlainformációk");
            for (ClientPrice price : resp.getPrices()) {
                tablazat.setText(tablazat.getText() + "\n\n" + price.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*@FXML
    public void initialize() throws IOException {

        // Oszlopok összekapcsolása a Data osztály mezőivel
        nevColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ertekColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        Properties properties = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

        properties.load(input);

        String apiKey = Config.TOKEN;
        String userId = Config.ACCOUNTID.toString();

        try {
            Context ctx = new Context("https://api-fxpractice.oanda.com",apiKey);
            AccountSummary summary = ctx.account.summary(new AccountID(userId)).getAccount();
            String stringPart = summary.toString();
            String[] pairs = stringPart.split(",");


            // Táblázathoz adatok hozzáadása
            SzamlaInfotableView.setItems(dataList);

            for (String pair : pairs) {
                // Szétválasztjuk '=' alapján
                String[] keyValue = pair.split("=");
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                addData(key, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    public void addData(String name, String value) {
        dataList.add(new OandaData(name, value));
    }
}


