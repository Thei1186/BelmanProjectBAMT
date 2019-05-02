/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.GUI.Controller;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.Order;
import belmanprojectbamt.GUI.Model.BelmanModel;
import belmanprojectbamt.GUI.Model.PostItFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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
    private FlowPane flowPane;
    @FXML
    private AnchorPane slider;
    @FXML
    private AnchorPane ancPane;

    private PostItFactory pFactory;

    private List<Order> productionOrders;

    private List<DepartmentTask> departmentTasks;

    private int index;

    private final BelmanModel belModelInstance = BelmanModel.getInstance();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        productionOrders = belModelInstance.getProductionOrder();
        departmentTasks = belModelInstance.getDepartmentTasks();

        
        pFactory = new PostItFactory(flowPane, productionOrders, departmentTasks, index);
        generatePostIt();


        pFactory = new PostItFactory(flowPane, productionOrders, departmentTasks);

        handlePostIts();

        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setOrientation(Orientation.VERTICAL);
    }

    public void handlePostIts()
    {
        AtomicInteger runCount = new AtomicInteger();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () ->
        {
            generatePostIt();
            runCount.incrementAndGet();
            if (runCount.get() >= productionOrders.size())
            {
                executor.shutdownNow();
            }
        };
        executor.scheduleWithFixedDelay(task, 0, 200, TimeUnit.MILLISECONDS);

    }

    public void generatePostIt()
    {
        pFactory.createPostIt();
    }

}
