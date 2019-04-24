/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author asvor
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private FlowPane flowPane;
    private AnchorPane ancPostIt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ancPostIt = new AnchorPane();
        createAnchorPane();
        createLabels();
        setProgressBar();
        createComboBox();
    }    
    
    public void setProgressBar()
    {
        ProgressBar prgBarDate = new ProgressBar();
        ProgressBar prgBarActual = new ProgressBar();
        
        prgBarActual.setLayoutX(14);
        prgBarActual.setLayoutY(375);
        prgBarActual.setProgress(0.6);
        prgBarActual.setPrefSize(508, 24);
        
        prgBarDate.setLayoutX(14);
        prgBarDate.setLayoutY(340);
        prgBarDate.setProgress(0.4);
        prgBarDate.setPrefSize(508, 24);
        
        ancPostIt.getChildren().addAll(prgBarDate, prgBarActual);
    }
    
    public void createLabels()
    {
        Label customerLabel = new Label("Customer:");
        Label orderLabel = new Label("Order Number:");
        
        orderLabel.setLayoutX(14);
        orderLabel.setLayoutY(100);
        orderLabel.getStyleClass().add("label-header");
        
        customerLabel.setLayoutX(14);
        customerLabel.setLayoutY(200);

        customerLabel.getStyleClass().add("label");
        
        ancPostIt.getChildren().addAll(customerLabel, orderLabel);
    }
    
    public void createAnchorPane()
    {
        ancPostIt.setPrefSize(530, 410);
        ancPostIt.getStyleClass().add("anchorpane");
        
        flowPane.getChildren().add(ancPostIt);
    }
    
    public void createComboBox()
    {
        ComboBox comboDept = new ComboBox();
        
        comboDept.setLayoutX(310);
        comboDept.setPromptText("Departments");
        comboDept.setLayoutY(14);
        comboDept.setPrefSize(200, 15);
        
        ancPostIt.getChildren().add(comboDept);
        
    }
}
