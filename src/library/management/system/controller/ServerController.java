/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ServerController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private Spinner<Integer> portSpinner;
    @FXML
    private PasswordField passField;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton cancelBtn;

    private  Preferences prefs;
    @FXML
    private TextField hostField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prefs = Preferences.userRoot().node("lbdb");
        int port = prefs.getInt("port", 3306);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3300, 3330, port);
        String host = prefs.get("host", "local host");
        String user = prefs.get("user", "root");
        String pass = prefs.get("password", "");

          hostField.setText(host);
        portSpinner.setValueFactory(valueFactory);
        nameField.setText(user);
        passField.setText(pass);
    }

    @FXML
    public void SaveServerConfig(ActionEvent event) {
        prefs.put("host", hostField.getText());
        prefs.putInt("port", portSpinner.getValue());
        prefs.put("user", nameField.getText());
        prefs.put("pass", passField.getText());
    }

    @FXML
    private void closeWindow(ActionEvent event) {
    }

}
