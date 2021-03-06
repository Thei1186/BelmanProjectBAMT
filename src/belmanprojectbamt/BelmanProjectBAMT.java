/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Theis
 */
public class BelmanProjectBAMT extends Application
{
    
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("GUI/View/BelmanView.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("CascadeStyleSheet.css");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.getIcons().add(new Image("logo.png"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
