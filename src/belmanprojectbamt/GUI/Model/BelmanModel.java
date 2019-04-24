/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.GUI.Model;

/**
 *
 * @author Theis
 */
public class BelmanModel
{

    private static BelmanModel bModel;

    private BelmanModel()
    {
        bModel = null;
    }

    public static BelmanModel getInstance()
    {
        if (bModel == null)
        {
            bModel = new BelmanModel();
        }
        return bModel;
    }

}
