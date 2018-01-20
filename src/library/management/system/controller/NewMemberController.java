/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import library.management.system.database.Database;
import library.management.system.model.Member;
import library.management.system.model.MemberDAO;
import library.management.system.util.MessageBox;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class NewMemberController implements Initializable {
    
    private MemberDAO memberDAO;

    @FXML
    private JFXButton CancelBtn;
    @FXML
    private JFXTextField idField;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField mobileField;
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXButton saveField;
    private Database db;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       memberDAO= new MemberDAO();
    }    

    @FXML
    private void closeWindow(ActionEvent event) {
          Stage stage=(Stage)CancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveMembers(ActionEvent event) {
        String id=idField.getText();
        String name=nameField.getText();
        String mobile=mobileField.getText();
        String address=addressField.getText();
        
        if(id.isEmpty()||name.isEmpty()||mobile.isEmpty()||address.isEmpty()){
            System.out.println("Plz fill in all field");
        }
       
        try {
            memberDAO.addMember(new Member(id,name,mobile,address));
            MessageBox.showMessage("success", "Member was successfully added");
            Stage stage=(Stage)saveField.getScene().getWindow();
            stage.close();
        } catch (SQLException ex) {
            System.out.println("Fail");
            Logger.getLogger(NewMemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
    
}
