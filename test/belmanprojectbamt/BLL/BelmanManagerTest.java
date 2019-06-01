/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.BLL;

import belmanprojectbamt.BE.DepartmentTask;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Theis
 */
public class BelmanManagerTest
{

    public BelmanManagerTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    /**
     * Test of isStartDateReached method, of class BelmanManager expects a true
     * result to ensure that it can detect when start date is reached.
     */
    @Test
    public void testIsStartDateReached()
    {
        int oneDayMilli = 86400000;
        String testDepName = "Halvfab";
        Date testEndDate = new Date(System.currentTimeMillis());
        Date testStartDate = new Date(System.currentTimeMillis() - oneDayMilli);

        DepartmentTask dTask = new DepartmentTask(0, testDepName, testStartDate, testEndDate, 0, true);
        BelmanManager bManager = new BelmanManager(null);

        boolean startDateReached = bManager.isStartDateReached(dTask);

        assertTrue("StartDateReached returned false, but it should be true", startDateReached);
    }

    /**
     * Test of isStartDateReached method, of class BelmanManager expects a false
     * result to ensure that it can detect when start date is not yet reached.
     */
    @Test
    public void testIsStartDateNotReached()
    {
        int oneDayMilli = 86400000;
        String testDepName = "Halvfab";
        Date testEndDate = new Date(System.currentTimeMillis());
        Date testStartDate = new Date(System.currentTimeMillis() + oneDayMilli);

        DepartmentTask dTask = new DepartmentTask(0, testDepName, testStartDate, testEndDate, 0, true);
        BelmanManager bManager = new BelmanManager(null);

        boolean startDateReached = bManager.isStartDateReached(dTask);

        assertFalse("StartDateReached returned true, but it should be false", startDateReached);
    }

    /**
     * Test of isEndDateReached method, of class BelmanManager expects a true
     * result to ensure that it actually detects when end date is reached
     */
    @Test
    public void testIsEndDateReached()
    {
        int oneDayMilli = 86400000;
        String testDepName = "Halvfab";
        Date testEndDate = new Date(System.currentTimeMillis());
        Date testStartDate = new Date(System.currentTimeMillis() + oneDayMilli);

        DepartmentTask dTask = new DepartmentTask(0, testDepName, testStartDate, testEndDate, 0, true);
        BelmanManager bManager = new BelmanManager(null);

        boolean endDateReached = bManager.isEndDateReached(dTask);

        assertFalse("endDateReached returned false, but it should be true", endDateReached);
    }

    /**
     * Test of isEndDateReached method, of class BelmanManager expects a false
     * result to ensure that it actually detects when end date is not reached .
     */
    @Test
    public void testIsEndDateNotReached()
    {
        int oneDayMilli = 86400000;
        String testDepName = "Halvfab";
        Date testEndDate = new Date(System.currentTimeMillis());
        Date testStartDate = new Date(System.currentTimeMillis() - oneDayMilli);

        DepartmentTask dTask = new DepartmentTask(0, testDepName, testStartDate, testEndDate, 0, true);
        BelmanManager bManager = new BelmanManager(null);

        boolean endDateReached = bManager.isEndDateReached(dTask);

        assertFalse("endDateReached returned true, but it should be false", endDateReached);
    }
}
