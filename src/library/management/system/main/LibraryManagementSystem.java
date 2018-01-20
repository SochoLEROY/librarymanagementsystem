 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system.main;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.management.system.database.Database;
import library.management.system.util.MessageBox;

/**
 *
 * @author ASUS
 */
public class LibraryManagementSystem extends Application {
    private Database db;
    @Override
    public void start(Stage stage) throws Exception {
      
        try{
              db=Database.getInstance();
        }catch(SQLException ex){
            MessageBox.showAndWaitErrorMessage("Error", "Plz check your Server");
        }
      
        Parent root = FXMLLoader.load(getClass().getResource("/library/management/system/view/main.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
