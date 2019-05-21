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
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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

    private List<ProductionOrder> productionOrders;

    private final BelmanModel belModelInstance;

    public FXMLDocumentController() throws IOException
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
        productionOrders = belModelInstance.getProductionOrder();

        try
        {
            pFactory = new PostItFactory(flowPane, productionOrders);
        } catch (IOException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        handlePostIts();

        flowPane.setVgap(6);
        flowPane.setHgap(6);
        flowPane.setOrientation(Orientation.VERTICAL);
    }

    public void handlePostIts()
    {
        if (productionOrders.size() > 0)
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
