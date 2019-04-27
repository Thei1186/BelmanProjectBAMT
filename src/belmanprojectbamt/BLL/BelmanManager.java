/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanprojectbamt.BLL;

import belmanprojectbamt.DAL.IFacade;

/**
 *
 * @author Theis
 */
public class BelmanManager implements LogicInterface
{
    IFacade bDao;
    public BelmanManager(IFacade facade)
    {
     bDao = facade;
    }
    
}
