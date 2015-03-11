
package mypack;

import javax.ejb.*;
import java.rmi.*; 
import java.sql.*;

public class EmployeeBean implements EntityBean
{
	String name,id,job;
	int salary;
	static Connection con;
	static 
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:mydsn");
		
		}
		catch(Exception e)
		{	e.printStackTrace();
		}
	}
	public void setName(String name)
	{	this.name=name;
	}
	public void setId(String id)
	{	this.id=id;
	}
	public void setSalary(int salary)
	{	this.salary=salary;
	}
	public void setJob(String job)
	{	this.job=job;
	}
	public String getName()
	{	return(name);
	}
	public String getId()
	{	return(id);
	}
	public int getSalary()
	{	return(salary);	
	}
	public String getJob()
	{	return(job);	
	}
	
	public String ejbCreate(String name , String id , int salary , String job)
	{
		try
		{
			this.name=name;
			this.id=id;
			this.salary=salary;
			this.job=job;
			
			PreparedStatement ps=con.prepareStatement("insert into employee values(?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,id);
			ps.setInt(3,salary);
			ps.setString(4,job);
			ps.executeUpdate();
			System.out.println("record inserted");
			return(id);
		}
		catch(Exception e)
		{	e.printStackTrace();
			return(null);
		}	
	}
	public String ejbFindByPrimaryKey(String id)throws FinderException
	{
		try
		{
			this.id=id;
			PreparedStatement ps=con.prepareStatement("select * from employee where id=?");
			ps.setString(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				System.out.println("record found");
				this.name=name;	
				this.salary=salary;	
				this.job=job;
				return(id);
			}
			else
			{
				throw new Exception();
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		throw new ObjectNotFoundException();
		}
	}
	public void ejbStore()
	{
		try
		{
			PreparedStatement ps=con.prepareStatement("update employee set name=? , salary=? , job=? where id=? ");
			
			ps.setString(1,name);
			ps.setInt(2,salary);
			ps.setString(3,job);
			ps.setString(4,id);
			ps.executeUpdate();
			System.out.println("record updated successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
	}
	public void ejbLoad()
	{
		try
		{
			PreparedStatement ps=con.prepareStatement("select * from employee where id=?");
			ps.setString(1,id);
			ResultSet rs=ps.executeQuery();
			rs.next();	
			this.name=rs.getString(1);
			this.id=id;	
			this.salary=rs.getInt(3);
			this.job=rs.getString(4);
		}
		catch(Exception e)
		{	e.printStackTrace();
		}		
	}
	public void ejbRemove()
	{
		try
		{
			PreparedStatement ps=con.prepareStatement("delete from employee where id=?");
			ps.setString(1,id);
			ps.executeUpdate();
			System.out.println("record deleted");
		}
		catch(Exception e)
		{	e.printStackTrace();
		}
	}
	public void ejbActivate()
	{	}
	public void ejbPassivate()
	{	}
	public void setEntityContext(EntityContext ctx)
	{	}
	public void unsetEntityContext()
	{	}

	public void ejbPostCreate(String name , String id ,int salary , String job)
	{
	}
}
		
				
		