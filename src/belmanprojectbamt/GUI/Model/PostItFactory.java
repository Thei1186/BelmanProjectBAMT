/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.GUI.Model;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.Order;
import java.util.List;
import javafx.application.Platform;
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

    private List<Order> productionOrders;
    private List<DepartmentTask> departmentTasks;
    private int index;
    private DateFormatter dFormat;

    public PostItFactory(FlowPane flowPane, List<Order> productionOrders, List<DepartmentTask> departmentTasks)
    {
        dFormat = new DateFormatter();
        this.flowPane = flowPane;
        this.productionOrders = productionOrders;
        this.departmentTasks = departmentTasks;
        this.index = 0;
    }

    public void createPostIt()
    {
        Platform.runLater(() ->
        {
            ancPostIt = new AnchorPane();
            setProgressBar();
            createAnchorPane();
            createLabels();
            createComboBox();
            createButton();
            setLabelText(index);
        index++;
        
        });
//        return ancPostIt;
    }

    private void setProgressBar()
    {
        ProgressBar prgBarDate = new ProgressBar();
        ProgressBar prgBarActual = new ProgressBar();
        
        prgBarDate.setLayoutX(14);
        prgBarDate.setLayoutY(240);
        prgBarDate.setProgress(0.4);
        prgBarDate.setPrefSize(565, 24);
        
        prgBarActual.setLayoutX(14);
        prgBarActual.setLayoutY(305);
        prgBarActual.setProgress(0.6);
        prgBarActual.setPrefSize(565, 24);

        ancPostIt.getChildren().addAll(prgBarDate, prgBarActual);
    }

    private void setLabelText(int index)
    {
        Label customerName = new Label();
        Label delDate = new Label();
        Label orderNr = new Label();
        Label startDate = new Label();
        Label endDate = new Label();
        
        customerName.setLayoutX(120);
        customerName.setLayoutY(120);
        customerName.setText(productionOrders.get(index).getCustomerName());

        String formattedDate = dFormat.formatDate(productionOrders.get(index).getDeliveryDate());
        String formattedStartDate = dFormat.formatDate(productionOrders.get(index).getDeptTasks().get(0).getStartDate());
        String formattedEndDate = dFormat.formatDate(productionOrders.get(index).getDeptTasks().get(0).getEndDate());
        
        delDate.setLayoutX(155);
        delDate.setLayoutY(160);
        delDate.setText(formattedDate);

        orderNr.setLayoutX(200);
        orderNr.setLayoutY(70);
        orderNr.setText(productionOrders.get(index).getOrderNumber());

        startDate.setLayoutX(120);
        startDate.setLayoutY(208);
        startDate.setText(formattedStartDate);
        
        endDate.setLayoutX(462);
        endDate.setLayoutY(208);
        endDate.setText(formattedEndDate);
        ancPostIt.getChildren().addAll(customerName, delDate, orderNr, startDate, endDate);
    }

    private void createLabels()
    {
        Label customerLabel = new Label("Customer:");
        Label orderLabel = new Label("Order Number:");
        Label delDatelabel = new Label("Delivery Date:");
        Label startDatelabel = new Label("Start Date:");
        Label endDatelabel = new Label("End Date:");
        Label actualTimelabel = new Label("Actual Time:");
        Label lastActivelabel = new Label("Last Department Active:");
        Label departmentlabel = new Label("Department");

        customerLabel.setLayoutX(14);
        customerLabel.setLayoutY(120);
        customerLabel.getStyleClass().add("label-sub-header");

        orderLabel.setLayoutX(14);
        orderLabel.setLayoutY(65);
        orderLabel.getStyleClass().add("label-header");

        delDatelabel.setLayoutX(14);
        delDatelabel.setLayoutY(160);
        delDatelabel.getStyleClass().add("label-sub-header");

        startDatelabel.setLayoutX(14);
        startDatelabel.setLayoutY(208);
        startDatelabel.getStyleClass().add("label-sub-header");

        endDatelabel.setLayoutX(365);
        endDatelabel.setLayoutY(208);
        endDatelabel.getStyleClass().add("label-sub-header");

        actualTimelabel.setLayoutX(14);
        actualTimelabel.setLayoutY(275);
        actualTimelabel.getStyleClass().add("label-sub-header");

        lastActivelabel.setLayoutX(350);
        lastActivelabel.setLayoutY(65);
        lastActivelabel.getStyleClass().add("label-sub-header");

        departmentlabel.setLayoutX(350);
        departmentlabel.setLayoutY(85);
        departmentlabel.getStyleClass().add("label");

        ancPostIt.getChildren().addAll(customerLabel, orderLabel, delDatelabel, startDatelabel, endDatelabel, actualTimelabel, lastActivelabel, departmentlabel);
    }

    private void createAnchorPane()
    {
        ancPostIt.setPrefSize(570, 390);
        ancPostIt.getStyleClass().add("anchorpane");

        flowPane.getChildren().add(ancPostIt);
    }

    private void createComboBox()
    {
        ComboBox comboDept = new ComboBox();

        comboDept.setPromptText("Departments");
        comboDept.setLayoutX(380);
        comboDept.setLayoutY(14);
        comboDept.setPrefSize(200, 15);

        ancPostIt.getChildren().add(comboDept);

    }

    private void createButton()
    {
        Button doneButton = new Button();

        doneButton.setText("Done");
        doneButton.setLayoutX(498);
        doneButton.setLayoutY(345);
        doneButton.setPrefSize(80, 20);

        ancPostIt.getChildren().add(doneButton);
    }
}
