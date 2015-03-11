package mypack;

import javax.ejb.*;
import java.rmi.*;

public interface EmployeeHome extends EJBHome
{

	Employee create(String name , String id , int salary , String job)throws CreateException , RemoteException;

	Employee findByPrimaryKey(String id)throws FinderException , RemoteException;

}
