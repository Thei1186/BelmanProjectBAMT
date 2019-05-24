/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.GUI.Controller;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.ProductionOrder;
import belmanprojectbamt.DAL.ConfigReader;
import belmanprojectbamt.GUI.Model.BelmanModel;
import belmanprojectbamt.GUI.Model.PostItFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private AnchorPane ancPane;

    private PostItFactory pFactory;

    private ObservableList<ProductionOrder> productionOrders;

    private final BelmanModel belModelInstance;

    public FXMLDocumentController() throws Exception
    {
        this.belModelInstance = BelmanModel.getInstance();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            productionOrders = belModelInstance.getProductionOrders();
            handleGetProductionOrders();
            handlePostIts();
            pFactory = new PostItFactory(flowPane, productionOrders);
        } catch (Exception ex)
        {
            if (ex instanceof SQLException)
            {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);                
            }
        }

        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setOrientation(Orientation.VERTICAL);
    }

    public ObservableList<ProductionOrder> getProductionOrders() throws Exception
    {
        ObservableList<ProductionOrder> tempList = belModelInstance.getProductionOrders();
        if (tempList.size() != productionOrders.size())
        {
            flowPane.getChildren().clear();
            productionOrders = tempList;
            pFactory = new PostItFactory(flowPane, productionOrders);
            handlePostIts();
            return productionOrders;
        }
        else
        {
            return null;
        }
    }

    private void handleGetProductionOrders()
    {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () ->
        {
            Platform.runLater(() ->
            {
                try
                {
                    getProductionOrders();
                } catch (Exception ex)
                {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        };
        executor.scheduleWithFixedDelay(task, 0, 5, TimeUnit.SECONDS);
    }

    public void handlePostIts()
    {
        if (productionOrders.size() > 0 && productionOrders != null)
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
    }

    public void generatePostIt()
    {
        Platform.runLater(() ->
        {
            pFactory.createPostIt();
        });
    }

}
