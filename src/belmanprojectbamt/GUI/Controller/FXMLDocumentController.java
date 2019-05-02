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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        this.index = 0;
        productionOrders = belModelInstance.getProductionOrder();
        departmentTasks = belModelInstance.getDepartmentTasks();
        
        pFactory = new PostItFactory(flowPane, productionOrders, departmentTasks, index);
        generatePostIt();
//        
        flowPane.setVgap(6);
        flowPane.setHgap(6);
//        flowPane.setVgap(30);
//        flowPane.setHgap(18);
        flowPane.setOrientation(Orientation.VERTICAL);
    }    
    
    public void generatePostIt()
    {
        for (int i = 0; i < productionOrders.size(); i++) 
        {
            pFactory.createPostIt();
        }
    }

}
