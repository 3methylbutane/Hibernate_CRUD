package com.work;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	while(true)
    	{
    		System.out.println("***************Employee Details***************");
    		System.out.println("1. Add");
    		System.out.println("2. Update");
    		System.out.println("3. Delete");
    		System.out.println("4. Display");
    		System.out.println("5. Sort based on id");
    		System.out.println("6. Get the data with range");
    		System.out.println("7. Exit");
    		System.out.println("\n\nEnter your choice (1-7)");
    		
    		Scanner sc=new Scanner(System.in);
    		int x=Integer.parseInt(sc.nextLine());
    		if(x==1)
    		{
    			System.out.println("Add is Called");
    			int id;
    			int age;
    			String name;
    			System.out.println("Enter id -");
    			id=Integer.parseInt(sc.nextLine());
    			System.out.println("Enter name -");
    			name=sc.nextLine();
    			System.out.println("Enter age -");
    			age=Integer.parseInt(sc.nextLine());
    			
    			Configuration cfg=new Configuration().addAnnotatedClass(Employee.class);
    			cfg.configure("hibernate.cfg.xml");
    			SessionFactory sF=cfg.buildSessionFactory();
    			Session session=sF.openSession();
    			Transaction tx=session.beginTransaction();
    			
    			Employee employee=new Employee();
    			employee.setId(id);
    			employee.setName(name);
    			employee.setAge(age);
    			session.save(employee);
    			tx.commit();
    			session.close();
    			System.out.println("Successfully Added");
    		}
    		else if(x==2)
    		{
    			System.out.println("Update is Called");
    			int id;
    			int age;
    			String name;
    			System.out.println("Enter id -");
    			id=Integer.parseInt(sc.nextLine());
    			System.out.println("Enter name -");
    			name=sc.nextLine();
    			System.out.println("Enter age -");
    			age=Integer.parseInt(sc.nextLine());
    			
    			Configuration cfg=new Configuration().addAnnotatedClass(Employee.class);
    			cfg.configure("hibernate.cfg.xml");
    			SessionFactory sF=cfg.buildSessionFactory();
    			Session session=sF.openSession();
    			Transaction tx=session.beginTransaction();
    			
    			Employee employee=session.get(Employee.class,id);
    			employee.setName(name);
    			employee.setAge(age);
    			tx.commit();
    			session.close();
    			
    			System.out.println("Successfully Updated");
    		}
    		else if(x==3)
    		{
    			System.out.println("Delete is Called");
    			int id;
    			System.out.println("Enter id -");
    			id=Integer.parseInt(sc.nextLine());
    			Configuration cfg=new Configuration().addAnnotatedClass(Employee.class);
    			cfg.configure("hibernate.cfg.xml");
    			SessionFactory sF=cfg.buildSessionFactory();
    			Session session=sF.openSession();
    			Transaction tx=session.beginTransaction();
    			Employee employee=session.get(Employee.class,id);
    			session.delete(employee);
    			tx.commit();
    			session.close();
    			System.out.println("Successfully Deleted");
    			
    		}
    		else if(x==4)
    		{
    			System.out.println("Display is Called");
    			Configuration cfg=new Configuration().addAnnotatedClass(Employee.class);
    			cfg.configure("hibernate.cfg.xml");
    			SessionFactory sF=cfg.buildSessionFactory();
    			Session session=sF.openSession();
    			Query query=session.createQuery("from Employee");
    			List<Employee> employeeList=query.list();
    			for(Employee e:employeeList)
    			{
    				System.out.println("Id :"+e.getId()+" Name : "+e.getName()+" Age : "+e.getAge());
    			}
    			session.close();
    			System.out.println("Successfully called Display");
    		}
    		else if(x==5)
    		{
    			System.out.println("Sort based on Id is Called");
    			Configuration cfg=new Configuration().addAnnotatedClass(Employee.class);
    			cfg.configure("hibernate.cfg.xml");
    			SessionFactory sF=cfg.buildSessionFactory();
    			Session session=sF.openSession();
    			Query query=session.createQuery("from Employee");
    			List<Employee> employeeList=query.list();
    			Collections.sort(employeeList,Collections.reverseOrder());
    			for(Employee e:employeeList)
    			{
    				System.out.println("Id :"+e.getId()+" Name : "+e.getName()+" Age : "+e.getAge());
    			}
    			session.close();
    			System.out.println("Successfully called Sort based on ID");
    		}
    		else if(x==6)
    		{
    			System.out.println("Range based Data is Called");
    			int startId;
    			int endId;
    			System.out.println("Enter start range of Id -");
    			startId=Integer.parseInt(sc.nextLine());
    			System.out.println("Enter end range of Id -");
    			endId=Integer.parseInt(sc.nextLine());
    			Configuration cfg=new Configuration().addAnnotatedClass(Employee.class);
    			cfg.configure("hibernate.cfg.xml");
    			SessionFactory sF=cfg.buildSessionFactory();
    			Session session=sF.openSession();
    			Query query=session.createQuery("from Employee E where E.id between startId and endId");
    			query.setParameter("start", startId);
    			query.setParameter("end", endId);
    			List<Employee> employeeList=query.list();
    			for(Employee e:employeeList)
    			{
    				System.out.println("Id :"+e.getId()+" Name : "+e.getName()+" Age : "+e.getAge());
    			}
    			session.close();
    			System.out.println("Successfully called data within a range");	
    		}
    		else if(x==7)
    		{
    			System.out.println("Thanks");
    			break;
    		}
    	}	
    }
}
