/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.GUI.Controller;

import belmanprojectbamt.GUI.Model.PostItFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author asvor
 */
public class FXMLDocumentController implements Initializable 
{
    @FXML
    FlowPane flowPane;
    PostItFactory pFactory;
    @FXML
    private AnchorPane slider;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        pFactory = new PostItFactory(flowPane);
        generatePostIt();
        
        flowPane.setVgap(5);
        flowPane.setHgap(5);
        flowPane.setOrientation(Orientation.VERTICAL);
    }    
    
    public void generatePostIt()
    {
        for (int i = 0; i < 10; i++) 
        {
            pFactory.createPostIt();
            
        }
    }
}
