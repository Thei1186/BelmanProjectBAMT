/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.GUI.Model;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author asvor
 */
public class PostItFactory 
{
    private FlowPane flowPane;
    private AnchorPane ancPostIt;

    public PostItFactory(FlowPane flowPane) {
        this.flowPane = flowPane;
    }
    
    public AnchorPane createPostIt()
    {
        ancPostIt = new AnchorPane();
        setProgressBar();
        createAnchorPane();
        createLabels();
        createComboBox();
        createButton();
       
        return ancPostIt;
    }
    
    private void setProgressBar()
    {
        ProgressBar prgBarDate = new ProgressBar();
        ProgressBar prgBarActual = new ProgressBar();
        
        prgBarActual.setLayoutX(14);
        prgBarActual.setLayoutY(270);
        prgBarActual.setProgress(0.6);
        prgBarActual.setPrefSize(508, 24);
        
        prgBarDate.setLayoutX(14);
        prgBarDate.setLayoutY(205);
        prgBarDate.setProgress(0.4);
        prgBarDate.setPrefSize(508, 24);
        
        ancPostIt.getChildren().addAll(prgBarDate, prgBarActual);
    }
    
    private void createLabels()
    {
        Label customerLabel = new Label("Customer:");
        Label orderLabel = new Label("Order Number:");
        Label delDatelabel = new Label("Delivery Date:");
        Label startDatelabel = new Label("Start Date:");
        Label endDatelabel = new Label("End Date:");
        Label actualTimelabel = new Label("Actual Time:");
        
        customerLabel.setLayoutX(14);
        customerLabel.setLayoutY(120);
        customerLabel.getStyleClass().add("label");
        
        orderLabel.setLayoutX(14);
        orderLabel.setLayoutY(65);
        orderLabel.getStyleClass().add("label-header");
        
        delDatelabel.setLayoutX(14);
        delDatelabel.setLayoutY(145);
        delDatelabel.getStyleClass().add("label");
        
        startDatelabel.setLayoutX(14);
        startDatelabel.setLayoutY(185);
        startDatelabel.getStyleClass().add("label");
        
        endDatelabel.setLayoutX(450);
        endDatelabel.setLayoutY(185);
        endDatelabel.getStyleClass().add("label");
        
        actualTimelabel.setLayoutX(14);
        actualTimelabel.setLayoutY(250);
        actualTimelabel.getStyleClass().add("label");
        
        ancPostIt.getChildren().addAll(customerLabel, orderLabel, delDatelabel, startDatelabel, endDatelabel, actualTimelabel);
    }
    
    private void createAnchorPane()
    {
        ancPostIt.setPrefSize(500, 350);
        ancPostIt.getStyleClass().add("anchorpane");
        
        flowPane.getChildren().add(ancPostIt);
    }
    
    private void createComboBox()
    {
        ComboBox comboDept = new ComboBox();
        
        comboDept.setLayoutX(310);
        comboDept.setPromptText("Departments");
        comboDept.setLayoutY(14);
        comboDept.setPrefSize(200, 15);
        
        ancPostIt.getChildren().add(comboDept);
        
    }
    
    private void createButton()
    {
        Button doneButton = new Button();
        
        doneButton.setText("Done");
        doneButton.setLayoutX(440);
        doneButton.setLayoutY(310);
        doneButton.setPrefSize(80, 20);
        
        ancPostIt.getChildren().add(doneButton);
    }
}
