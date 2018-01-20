/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.util;

import javafx.scene.control.Alert;

/**
 *
 * @author ASUS
 */
public class MessageBox {
    public static void showMessage(String title,String message){
         Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
    }
      public static void showErrorMessage(String title,String message){
           Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.show();
    }
        public static void showAndWaitErrorMessage(String title,String message){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait(); 
    }
        public static void showComfirmMessage(){
    }
}
