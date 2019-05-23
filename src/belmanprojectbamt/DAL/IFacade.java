/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.DAL;

import belmanprojectbamt.BE.DepartmentTask;
import belmanprojectbamt.BE.ProductionOrder;
import java.util.List;

/**
 *
 * @author Theis
 */
public interface IFacade
{

    /**
     *
     * @return @throws java.lang.Exception
     */
    public List<ProductionOrder> getProductionOrder() throws Exception;

    /**
     *
     * @param task
     */
    public void setTaskAsDone(DepartmentTask task);

    /**
     *
     * @return
     */
    public String getDepartmentName();

    /**
     *
     * @return
     */
    public int getOffSet();

    /**
     *
     * @param pOrder
     * @param dTask
     * @param logMessage
     */
    public void updateLog(ProductionOrder pOrder, DepartmentTask dTask, String logMessage);
}
