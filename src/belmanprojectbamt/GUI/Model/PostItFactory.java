/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.GUI.Model;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.Order;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author asvor
 */
public class PostItFactory
{

    private FlowPane flowPane;
    private AnchorPane ancPostIt;

    private List<Order> productionOrders;
    private int index;
    private DateFormatter dFormat;
    private String currentDept;

    public PostItFactory(FlowPane flowPane, List<Order> productionOrders)
    {
        dFormat = new DateFormatter();
        this.flowPane = flowPane;
        this.productionOrders = productionOrders;
        this.index = 0;
        this.currentDept = "Halvfab";
    }

    public void createPostIt()
    {
        if (productionOrders.get(index).getDeptTasks().get(departmentIndex()).getDepartmentName().toLowerCase().equals(currentDept.toLowerCase()))
        {
            ancPostIt = new AnchorPane();
            setProgressBar();
            createAnchorPane();
            createLabels();
//            createComboBox();
            createButton(index);
            setLabelText(index);
            createVBox();
        }
        index++;

//        return ancPostIt;
    }

    private void setProgressBar()
    {
        ProgressBar prgBarDate = new ProgressBar();
//        ProgressBar prgBarActual = new ProgressBar();

        prgBarDate.setLayoutX(14);
        prgBarDate.setLayoutY(305);
        prgBarDate.setProgress(0.4);
        prgBarDate.setPrefSize(565, 24);

//        prgBarActual.setLayoutX(14);
//        prgBarActual.setLayoutY(305);
//        prgBarActual.setProgress(0.6);
//        prgBarActual.setPrefSize(565, 24);

        ancPostIt.getChildren().addAll(prgBarDate);
    }

    private void setLabelText(int index)
    {
        Label customerName = new Label();
        Label delDate = new Label();
        Label orderNr = new Label();
        Label startDate = new Label();
        Label endDate = new Label();
        Label departmentlabel = new Label();

        customerName.setLayoutX(120);
        customerName.setLayoutY(120);
        customerName.setText(productionOrders.get(index).getCustomerName());

        String formattedDate = dFormat.formatDate(productionOrders.get(index).getDeliveryDate());

        String formattedStartDate = dFormat.formatDate(productionOrders.get(index).getDeptTasks().get(departmentIndex()).getStartDate());
        String formattedEndDate = dFormat.formatDate(productionOrders.get(index).getDeptTasks().get(departmentIndex()).getEndDate());

        delDate.setLayoutX(155);
        delDate.setLayoutY(160);
        delDate.setText(formattedDate);

        orderNr.setLayoutX(200);
        orderNr.setLayoutY(20);
        orderNr.setText(productionOrders.get(index).getOrderNumber());

        startDate.setLayoutX(120);
        startDate.setLayoutY(273);
        startDate.setText(formattedStartDate);

        endDate.setLayoutX(462);
        endDate.setLayoutY(273);
        endDate.setText(formattedEndDate);

        String previousDepartment = getPreviousDepartment();

        departmentlabel.setLayoutX(350);
        departmentlabel.setLayoutY(85);
        departmentlabel.getStyleClass().add("label");
        departmentlabel.setText(previousDepartment);

        ancPostIt.getChildren().addAll(customerName, delDate, orderNr, startDate, endDate, departmentlabel);
    }

    private String getPreviousDepartment()
    {
        String previousDepartment = "";
        if (departmentIndex() > 0)
        {
            previousDepartment = productionOrders.get(index).getDeptTasks().get(departmentIndex() - 1).getDepartmentName();
            if (productionOrders.get(index).getDeptTasks().get(departmentIndex()).isFinishedTask())
            {
                Label isDone = new Label("Done");
                isDone.setLayoutX(360);
                isDone.setLayoutY(85);
                isDone.getStyleClass().add("label-done");
                isDone.setTextFill(Color.web("#33ff33"));
            }
        }
        return previousDepartment;
    }

    private int departmentIndex()
    {
        int departmentIndex = 0;
        List<DepartmentTask> tempList = productionOrders.get(index).getDeptTasks();
        for (DepartmentTask departmentTask : tempList)
        {
            if (departmentTask.isFinishedTask())
            {
                if (tempList.size() != departmentIndex + 1)
                {
                    departmentIndex++;
                }
            }
        }
        return departmentIndex;
    }

    private void createLabels()
    {
        Label customerLabel = new Label("Customer:");
        Label orderLabel = new Label("Order Number:");
        Label delDatelabel = new Label("Delivery Date:");
        Label startDatelabel = new Label("Start Date:");
        Label endDatelabel = new Label("End Date:");
//        Label actualTimelabel = new Label("Actual Time:");
//        Label lastActivelabel = new Label("Last Department Active:");


        customerLabel.setLayoutX(14);
        customerLabel.setLayoutY(120);
        customerLabel.getStyleClass().add("label-sub-header");

        orderLabel.setLayoutX(14);
        orderLabel.setLayoutY(20);
        orderLabel.getStyleClass().add("label-header");

        delDatelabel.setLayoutX(14);
        delDatelabel.setLayoutY(160);
        delDatelabel.getStyleClass().add("label-sub-header");

        startDatelabel.setLayoutX(14);
        startDatelabel.setLayoutY(273);
        startDatelabel.getStyleClass().add("label-sub-header");

        endDatelabel.setLayoutX(365);
        endDatelabel.setLayoutY(273);
        endDatelabel.getStyleClass().add("label-sub-header");

//        actualTimelabel.setLayoutX(14);
//        actualTimelabel.setLayoutY(275);
//        actualTimelabel.getStyleClass().add("label-sub-header");

//        lastActivelabel.setLayoutX(350);
//        lastActivelabel.setLayoutY(65);
//        lastActivelabel.getStyleClass().add("label-sub-header");



        ancPostIt.getChildren().addAll(customerLabel, orderLabel, delDatelabel, startDatelabel, endDatelabel);
    }

    private void createAnchorPane()
    {
        ancPostIt.setPrefSize(570, 390);
        ancPostIt.getStyleClass().add("anchorpane");

        flowPane.getChildren().add(ancPostIt);
    }

//    private void createComboBox()
//    {
//        ComboBox comboDept = new ComboBox();
//
//        comboDept.setPromptText("Departments");
//        comboDept.setLayoutX(380);
//        comboDept.setLayoutY(14);
//        comboDept.setPrefSize(200, 15);
//
//        ancPostIt.getChildren().add(comboDept);
//
//    }

    private void createButton(int index)
    {
        Button doneButton = new Button();
        int postItIndex = index;
        doneButton.setText("Done");
        doneButton.setLayoutX(498);
        doneButton.setLayoutY(345);
        doneButton.setPrefSize(80, 20);
        doneButton.setOnMouseClicked(event ->
        {
            Button b = (Button) event.getSource();
            List<DepartmentTask> dTasks = productionOrders.get(postItIndex).getDeptTasks();
            for (DepartmentTask dTask : dTasks)
            {
                if (dTask.getDepartmentName().equals(currentDept))
                {
                    dTask.setFinishedTask(true);
                    Alert iAlert = new Alert(Alert.AlertType.INFORMATION, productionOrders.get(postItIndex).getOrderNumber()
                            + " is set to done for department: " + dTask.getDepartmentName(), ButtonType.OK);
                    iAlert.showAndWait();
                }
            }
        });
        ancPostIt.getChildren().add(doneButton);
    }

    private void createVBox()
    {
        VBox vbox = new VBox();
        
        vbox.setLayoutX(478);
        vbox.setLayoutY(20);
        vbox.setAlignment(Pos.CENTER_RIGHT);
//        vbox.setSpacing(5);
        
        Label dep1 = new Label("Halvfab");
        Label dep2 = new Label("Bælg");
        Label dep3 = new Label("Montage 1");
        Label dep4 = new Label("Montage 2");
        Label dep5 = new Label("Bertel");
        Label dep6 = new Label("Maler");
        Label dep7 = new Label("Forsendelse");
        
        dep1.setLayoutX(505);
        dep1.setLayoutY(20);
        dep1.getStyleClass().add("label-dep");
        
        dep2.setLayoutX(525);
        dep2.setLayoutY(60);
        dep2.getStyleClass().add("label-dep");
        
        dep3.setLayoutX(478);
        dep3.setLayoutY(100);
        dep3.getStyleClass().add("label-dep");
        
        dep4.setLayoutX(480);
        dep4.setLayoutY(140);
        dep4.getStyleClass().add("label-dep");
        
        dep5.setLayoutX(500);
        dep5.setLayoutY(180);
        dep5.getStyleClass().add("label-dep");
        
        dep6.setLayoutX(500);
        dep6.setLayoutY(220);
        dep6.getStyleClass().add("label-dep");
        
        dep7.setLayoutX(500);
        dep7.setLayoutY(260);
        dep7.getStyleClass().add("label-dep");
        
        vbox.getChildren().addAll(dep1, dep2, dep3, dep4, dep5, dep6, dep7);
        
        ancPostIt.getChildren().add(vbox);
    }
}
