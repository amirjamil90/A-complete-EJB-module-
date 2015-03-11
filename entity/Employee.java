
package mypack;
import javax.ejb.*;
import java.rmi.*;

public interface Employee extends EJBObject
{
	void setName(String name)throws RemoteException;

	void setId(String id)throws RemoteException;
	
	void setSalary(int salary)throws RemoteException;
	
	void setJob(String job)throws RemoteException;

	String getName()throws RemoteException;

	String getId()throws RemoteException;

	int getSalary()throws RemoteException;

	String getJob()throws RemoteException;
}
