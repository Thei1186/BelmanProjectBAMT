/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.GUI.Model;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.ProductionOrder;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author asvor
 */
public class PostItFactory
{

    private FlowPane flowPane;
    private AnchorPane ancPostIt;

    private List<ProductionOrder> productionOrders;
    private int index;
    private DateFormatter dFormat;
    private String currentDept;
    private int timeOffset;
    private final BelmanModel belModelInstance;

    public PostItFactory(FlowPane flowPane, List<ProductionOrder> productionOrders) throws IOException, Exception
    {
        belModelInstance = BelmanModel.getInstance();
        dFormat = new DateFormatter();
        this.flowPane = flowPane;
        this.productionOrders = productionOrders;
        this.index = 0;
        this.currentDept = belModelInstance.getDepartmentName();
        this.timeOffset = belModelInstance.getOffSet();
    }

    public void createPostIt()
    {
        if (checkIfPostItReady())
        {
            ancPostIt = new AnchorPane();
            setProgressBar();
            createAnchorPane();
            createLabels();
            createButton(index);
            setLabelText(index);
            createHBox();
        }
        index++;

//        return ancPostIt;
    }

    /**
     * Gets Department tasks from production order
     *
     * @return DepartmentTask
     */
    private DepartmentTask getDeptTask()
    {
        return productionOrders.get(index).getDeptTasks().get(departmentIndex());
    }

    private boolean isStartDateReached()
    {
        Calendar cal = Calendar.getInstance();
        Long timeOffsetInMilli = TimeUnit.DAYS.toMillis(timeOffset);
        Long curDateMilli = cal.getTimeInMillis();

        return curDateMilli >= (getDeptTask().getStartDate().getTime() - timeOffsetInMilli);
    }

    private boolean isEndDateReached()
    {
        Calendar cal = Calendar.getInstance();
        Long curDateMilli = cal.getTimeInMillis();

        return curDateMilli > getDeptTask().getEndDate().getTime();
    }

    private boolean checkIfPostItReady()
    {
        return isStartDateReached()
                && !getDeptTask().isFinishedTask()
                && getDeptTask().getDepartmentName().toLowerCase().equals(currentDept.toLowerCase());
    }

    /**
     * Creates progress bar and adds it to anchorpane.
     */
    private void setProgressBar()
    {
        ProgressBar prgBarDate = new ProgressBar();

        prgBarDate.setLayoutX(14);
        prgBarDate.setLayoutY(260);
        prgBarDate.setPrefSize(562, 24);

        ancPostIt.getChildren().addAll(prgBarDate);

        Date startDate = getDeptTask().getStartDate();
        Date endDate = getDeptTask().getEndDate();
        prgBarDate.setProgress(belModelInstance.getProgressBarData(startDate, endDate));
    }

    /**
     * Retrieves information and sets it on labels.
     *
     * @param index
     */
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

        String formattedStartDate = dFormat.formatDate(productionOrders.get(index).getDeptTasks().get(departmentIndex()).getStartDate());
        String formattedEndDate = dFormat.formatDate(productionOrders.get(index).getDeptTasks().get(departmentIndex()).getEndDate());

        delDate.setLayoutX(155);
        delDate.setLayoutY(160);
        delDate.setText(formattedDate);

        orderNr.setLayoutX(200);
        orderNr.setLayoutY(20);
        orderNr.setText(productionOrders.get(index).getOrderNumber());

        startDate.setLayoutX(120);
        startDate.setLayoutY(220);
        startDate.setText(formattedStartDate);

        endDate.setLayoutX(462);
        endDate.setLayoutY(220);
        endDate.setText(formattedEndDate);

        ancPostIt.getChildren().addAll(customerName, delDate, orderNr, startDate, endDate);
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

    /**
     * Creates labels with static text.
     */
    private void createLabels()
    {
        Label customerLabel = new Label("Customer:");
        Label orderLabel = new Label("Order Number:");
        Label delDatelabel = new Label("Delivery Date:");
        Label startDatelabel = new Label("Start Date:");
        Label endDatelabel = new Label("End Date:");

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
        startDatelabel.setLayoutY(220);
        startDatelabel.getStyleClass().add("label-sub-header");

        endDatelabel.setLayoutX(365);
        endDatelabel.setLayoutY(220);
        endDatelabel.getStyleClass().add("label-sub-header");

        ancPostIt.getChildren().addAll(customerLabel, orderLabel, delDatelabel, startDatelabel, endDatelabel);
    }

    /**
     * Creates anchorPane.
     */
    private void createAnchorPane()
    {
        ancPostIt.setPrefSize(570, 410);
        ancPostIt.getStyleClass().add("anchorpane");

        flowPane.getChildren().add(ancPostIt);
    }

    /**
     * Creates done button with confirmation message.
     *
     * @param postItIndex
     */
    private void createButton(int postItIndex)
    {
        Button doneButton = new Button();
        doneButton.setText("Done");
        doneButton.setLayoutX(498);
        doneButton.setLayoutY(360);
        doneButton.setPrefSize(80, 20);
        doneButton.setOnMouseClicked(event ->
        {
            List<DepartmentTask> dTasks = productionOrders.get(postItIndex).getDeptTasks();
            for (DepartmentTask dTask : dTasks)
            {
                if (dTask.getDepartmentName().equals(currentDept))
                {
                    Alert iAlert = new Alert(Alert.AlertType.CONFIRMATION, productionOrders.get(postItIndex).getOrderNumber()
                            + " is set to done for department: " + dTask.getDepartmentName());

                    Optional<ButtonType> result = iAlert.showAndWait();

                    if (result.get() == ButtonType.OK)
                    {
                        belModelInstance.setTaskAsDone(dTask);
                        
                        String logMessage = "DepartmentTask set as done";
                        belModelInstance.updateLog(productionOrders.get(postItIndex), dTask, logMessage);                        
                    }
                }
            }
        });
        ancPostIt.getChildren().add(doneButton);
    }

    /**
     * Creates HBox with department labels Sets the colour for department
     * progress.
     */
    private void createHBox()
    {
        HBox hbox = new HBox();

        hbox.setLayoutX(14);
        hbox.setLayoutY(305);
        hbox.setAlignment(Pos.CENTER_RIGHT);

        Label dep1 = new Label("Halvfab");
        Label dep2 = new Label("Bælg");
        Label dep3 = new Label("Montage 1");
        Label dep4 = new Label("Montage 2");
        Label dep5 = new Label("Bertel");
        Label dep6 = new Label("Maler");
        Label dep7 = new Label("Forsendelse");
        dep1.setUserData("Halvfab");
        dep2.setUserData("BÃ¦lg");
        dep3.setUserData("Montage 1");
        dep4.setUserData("Montage 2");
        dep5.setUserData("Bertel");
        dep6.setUserData("Maler");
        dep7.setUserData("Forsendelse");

        dep1.setLayoutX(505);
        dep1.setLayoutY(20);
        dep1.setPadding(new Insets(20));
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

        hbox.getChildren().addAll(dep1, dep2, dep3, dep4, dep5, dep6, dep7);

        ancPostIt.getChildren().add(hbox);
        setDeptStatus(hbox);
    }

    private void setDeptStatus(HBox hbox)
    {
        List<Node> nodes = hbox.getChildren();
        List<DepartmentTask> dTasks = productionOrders.get(index).getDeptTasks();
        for (DepartmentTask dTask : dTasks)
        {
            for (Node node : nodes)
            {
                if (dTask.isFinishedTask() && dTask.getDepartmentName().toLowerCase().equals(node.getUserData().toString().toLowerCase()))
                {
                    node.getStyleClass().add("label-done");
                } 
                else if (!dTask.isFinishedTask() && dTask.getEndDate().getTime() < System.currentTimeMillis()
                        && dTask.getDepartmentName().toLowerCase().equals(node.getUserData().toString().toLowerCase()))
                {
                    node.getStyleClass().add("label-late");
                }
                else if (isStartDateReached() && !dTask.isFinishedTask()
                        && dTask.getDepartmentName().toLowerCase().equals(node.getUserData().toString().toLowerCase()))
                {
                    node.getStyleClass().add("label-ongoing");
                }
//                if ()
//                {
//                    node.getStyleClass().add("label-irrelevant");
//
//                }
            }
        }
    }
}
