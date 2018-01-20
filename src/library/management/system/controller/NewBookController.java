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
import library.management.system.model.Book;
import library.management.system.model.BookDAO;
import library.management.system.util.MessageBox;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class NewBookController implements Initializable {

    @FXML
    private JFXButton CancelBtn;
    @FXML
    private JFXTextField idField;
    @FXML
    private JFXTextField titleField;
    @FXML
    private JFXTextField authorField;
    @FXML
    private JFXTextField publisherField;
    @FXML
    private JFXButton saveBtn;

   private BookDAO bookDAO;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   bookDAO=new BookDAO();
    }
    @FXML
    private void closeWindow(ActionEvent event) {
    
        Stage stage=(Stage)CancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveBook(ActionEvent event) {
        String id=idField.getText();
        String title=titleField.getText();
         String author=authorField.getText();
          String publisher=publisherField.getText();
          
          if(id.isEmpty()||title.isEmpty()||author.isEmpty()||publisher.isEmpty()){
              System.out.println("plz fill in all field");
          }
        try {
            bookDAO.addBook(new Book(id,title,author,publisher));
            MessageBox.showMessage("success", "Book was successfully added");
            Stage stage=(Stage)saveBtn.getScene().getWindow();
            stage.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}
    

