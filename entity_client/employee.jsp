
<html>
<body>
<%@ page import="javax.naming.* , javax.rmi.* , mypack.*" %>

<%!
	EmployeeHome home=null;
		
	public void jspInit()
	{
		try
		{
			InitialContext ctx=new InitialContext();
			Object ob=ctx.lookup("EmployeeBean2");
			home=(EmployeeHome)PortableRemoteObject.narrow(ob , EmployeeHome.class);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		
	}
%>
<%
	String opt=request.getParameter("option");
	String id=request.getParameter("txtid");
	if(opt.equals("search"))
	{
		Employee e=home.findByPrimaryKey(id);
		out.println(e.getName()+"\t"+e.getId()+"\t"+e.getSalary()+"\t"+e.getJob());
	}
	else if(opt.equals("delete"))
	{
		Employee e=home.findByPrimaryKey(id);
		e.remove();
		out.println("record removed successfully");	
	}
	else
	{
		String name=request.getParameter("txtname");
		int salary=Integer.parseInt(request.getParameter("txtsalary"));
		String job=request.getParameter("txtjob");
		if(opt.equals("insert"))
		{
			Employee e=home.create(name,id,salary,job);
			out.println("record inserted");		
		}
		else
		{
			Employee e=home.findByPrimaryKey(id);
			e.setName(name);
			e.setSalary(salary);
			e.setJob(job);
			out.println("record updated");
		}
	}
	
%>
<br> <jsp:include page="index.html"/>
</body>
</html>
