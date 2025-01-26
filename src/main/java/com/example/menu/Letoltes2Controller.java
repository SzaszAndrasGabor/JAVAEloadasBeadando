package com.example.menu;

import com.example.mnbapi.MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.menu.MnbKliens;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;

public class Letoltes2Controller {
    @FXML
    public ComboBox currencyComboBox;
    @FXML
    public DatePicker startDatePicker;
    @FXML
    public Button SaveButton;
    @FXML
    public MnbKliens Mkliens=new MnbKliens();
    @FXML
    public Label uzenetLabel;

    public void initialize()  {
currencyComboBox.getItems().addAll(Mkliens.getCurrencies());


    }

    public void letolt2()throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage {
        String currency = currencyComboBox.getSelectionModel().getSelectedItem().toString();
        String startDate = startDatePicker.getValue().toString();
        Boolean formazott = true;

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setInitialFileName("MNB2"+startDate+".txt");
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {

            try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {

                String mnbResponse = Mkliens.getExchangeRate(currency, startDate, formazott);

                if (formazott) {
                    mnbResponse = "A(z) "+currency+" valuta árfolyama a "+startDate+"-i napon: "+mnbResponse+" forint.";
                }

                writer.write(mnbResponse);
                uzenetLabel.setText(mnbResponse);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }


    }
}
