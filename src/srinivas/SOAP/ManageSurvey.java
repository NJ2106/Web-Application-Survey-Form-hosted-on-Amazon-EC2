package srinivas.SOAP;
import org.hibernate.Criteria;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;


import com.restful.resources.SurveyBean;

import antlr.StringUtils;

import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.management.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator; 

@WebService(endpointInterface="srinivas.SOAP.SearchInterface")
@SOAPBinding(style = Style.DOCUMENT)
public class ManageSurvey implements SearchInterface{
//	private SessionFactory factory = new Configuration().configure().buildSessionFactory();
	String res="";
	public ManageSurvey() {
		
		//System.out.println("Inside managesurvey");
		
	}
 /*public static void main(String[] args) {
	try {
		
	} catch (Throwable ex) {
		System.err.println("Failed to create sessionFactory object." + ex);
        throw new ExceptionInInitializerError(ex);// TODO: handle exception
	}
}*/
	//private String []list;
	
 public void addMember(String fname,String lname,String city,String state,String zip,String saddress,String phone,String menu,String raffle,String comments,String email,Date surveyDate, Date startDate ) {
	 Configuration config=new Configuration();
	 config.configure("hibernate.cfg.xml");
	 SessionFactory factory=config.buildSessionFactory();
	 Session session=factory.openSession();
	 Transaction tx=null;
	 Integer id=null;
	 try {
		tx=session.beginTransaction();
		Survey survey=new Survey(fname, lname, city, state,zip, saddress, phone, menu, raffle, comments, email, surveyDate, startDate);
		//System.out.println("List "+fname+lname+city+state);
		id=(Integer) session.save(survey);
		System.out.println(id);
		tx.commit();
		
	} catch (HibernateException e) {
		if(tx!=null) tx.rollback();
		new HibernateException("Error");
		e.getMessage();
		// TODO: handle exception
	}
	 finally {
		 
		session.close();
	}
	
 }
 
 public void deleteMember(Integer id) {
	 Configuration config=new Configuration();
	 config.configure("hibernate.cfg.xml");
	 SessionFactory factory=config.buildSessionFactory();
	Session session=factory.openSession();
	 Transaction tx=null;
	 try {
		tx=session.beginTransaction();
		Survey survey=(Survey)session.get(Survey.class, id);
		session.delete(survey);
		tx.commit();
	} catch (HibernateException e) {
		if(tx!=null)tx.rollback();
		e.printStackTrace();// TODO: handle exception
	}
	 finally {
		 session.close();
	 }
 }
 @Override
 public String listResults(String FirstName,String LastName,String City,String State) {
	 this.res="";
	 Configuration config=new Configuration();
	 config.configure("hibernate.cfg.xml");
	 SessionFactory factory=config.buildSessionFactory();
	 Session session=factory.openSession();
	 Transaction tx=null;
	 Survey sr=new Survey();
	 SurveyBean sb=new SurveyBean();
	
	 try {
		 tx=session.beginTransaction();
		 /*Criteria cr=session.createCriteria(Survey.class);
		 cr.add(Restrictions.eq("firstname", sr.getFirstname()));
		 List results=cr.list();
		 System.out.println(results);*/
		 //List results=session.createQuery("SELECT * FROM Survey").list();
		 /*String hql = "FROM Survey WHERE firstname=?1";
		 sr.setFirstname(sb.getFname());
		 org.hibernate.Query query = session.createQuery(hql);*/
		 if(!State.equals("")&&!(FirstName.equals(""))){//&&LastName.equals("")&&City.equals(""))) {
		 org.hibernate.Query query=session.createQuery("FROM Survey WHERE state=:state AND first_name=:first_name");
		 //Object[] array= {FirstName,LastName,City,State};
		 //Type[] types= {String string, String string,String string,String string};
		
		 query.setParameter("state", State);
		 query.setParameter("first_name",FirstName);
		
		 //query.setParameters(array, types);
		 List results =  query.list();
		 for(Iterator iterator=results.iterator();iterator.hasNext();) {
			 ArrayList<String>list1=new ArrayList<String>();
			 Survey survey=(Survey)iterator.next();
			 /*System.out.println("First Name: "+survey.getFirstname());
			 System.out.println("Last Name: "+survey.getLastname());
			 System.out.println("City: "+survey.getCity());
			 System.out.println("State: "+survey.getState());
			 System.out.println("Zip: "+survey.getZip());*/
			 list1.add(survey.getId()+"");
			 list1.add(survey.getFirstname());
			 list1.add(survey.getLastname());
			 list1.add(survey.getCity());
			 list1.add(survey.getState());
			 list1.add(survey.getZip());
			 list1.add(survey.getPhone());
			 list1.add(survey.getEmail());
			 list1.add(survey.getMenu());
			 list1.add(survey.getRaffle());
			 list1.add(survey.getComments());
			  //res=String.join(",", list1);
			 //list.add(list1);
			 //list.
			 res=res+ " ["+ String.join(",", list1)+ " ]";
			 
			 
			 
			 
		 }
		 return res;
		 }
		 if(!State.equals("")&& !(LastName.equals(""))) {
			 org.hibernate.Query query=session.createQuery("FROM Survey WHERE state=:state AND last_name=:last_name");
			 
			 query.setParameter("state", State);
			 query.setParameter("last_name", LastName);
			 List results =  query.list();
			 for(Iterator iterator=results.iterator();iterator.hasNext();) {
				 ArrayList<String>list2=new ArrayList<String>();
				 Survey survey=(Survey)iterator.next();
				 System.out.println("First Name: "+survey.getFirstname());
				 System.out.println("Last Name: "+survey.getLastname());
				 System.out.println("City: "+survey.getCity());
				 System.out.println("State: "+survey.getState());
				 System.out.println("Zip: "+survey.getZip());
				 list2.add(survey.getId()+"");
				 list2.add(survey.getFirstname());
				 list2.add(survey.getLastname());
				 list2.add(survey.getCity());
				 list2.add(survey.getState());
				 list2.add(survey.getZip());
				 list2.add(survey.getPhone());
				 list2.add(survey.getEmail());
				 list2.add(survey.getMenu());
				 list2.add(survey.getRaffle());
				 list2.add(survey.getComments());
				 //String res=String.join(",", list2);
				 //list.add(list2);
				 res=res+ " ["+ String.join(",", list2)+ " ]";
				
				 
			 }
			 return res;
		 }
		 if(!FirstName.equals("")&& !LastName.equals("")) {
			 org.hibernate.Query query=session.createQuery("FROM Survey WHERE first_name=:firstname AND last_name=:last_name");
			 
			 query.setParameter("firstname", FirstName);
			 query.setParameter("last_name", LastName);
			 List results =  query.list();
			 for(Iterator iterator=results.iterator();iterator.hasNext();) {
				 ArrayList<String>list3=new ArrayList<String>();
				 Survey survey=(Survey)iterator.next();
				 System.out.println("First Name: "+survey.getFirstname());
				 System.out.println("Last Name: "+survey.getLastname());
				 System.out.println("City: "+survey.getCity());
				 System.out.println("State: "+survey.getState());
				 System.out.println("Zip: "+survey.getZip());
				 list3.add(survey.getId()+"");
				 list3.add(survey.getFirstname());
				 list3.add(survey.getLastname());
				 list3.add(survey.getCity());
				 list3.add(survey.getState());
				 list3.add(survey.getZip());
				 list3.add(survey.getPhone());
				 list3.add(survey.getEmail());
				 list3.add(survey.getMenu());
				 list3.add(survey.getRaffle());
				 list3.add(survey.getComments());
				 //res=String.join(",", list3);
				 //list.add(list3);
				 res=res+ " ["+ String.join(",", list3)+ " ]";
				 
		 }
			 return res;
		 }
		 if( !FirstName.equals("")&& !(City.equals(""))) {
			 org.hibernate.Query query=session.createQuery("FROM Survey WHERE first_name=:first_name AND city=:city");
			 
			 query.setParameter("first_name", FirstName);
			 query.setParameter("city", City);
			 List results =  query.list();
			 for(Iterator iterator=results.iterator();iterator.hasNext();) {
				 ArrayList<String>list4=new ArrayList<String>();
				 Survey survey=(Survey)iterator.next();
				 System.out.println("First Name: "+survey.getFirstname());
				 System.out.println("Last Name: "+survey.getLastname());
				 System.out.println("City: "+survey.getCity());
				 System.out.println("State: "+survey.getState());
				 System.out.println("Zip: "+survey.getZip());
				 list4.add(survey.getFirstname());
				 list4.add(survey.getLastname());
				 list4.add(survey.getCity());
				 list4.add(survey.getState());
				 list4.add(survey.getZip());
				 list4.add(survey.getPhone());
				 list4.add(survey.getEmail());
				 list4.add(survey.getMenu());
				 list4.add(survey.getRaffle());
				 list4.add(survey.getComments());
				 //res=String.join(",", list4);
				 //list.add(list4);
				 res=res+ " ["+ String.join(",", list4)+ " ]";
				 
		 }
			 return res;
		 }
		 if(!State.equals("")&& !(City.equals(""))) {
			 org.hibernate.Query query=session.createQuery("FROM Survey WHERE state=:state AND city=:city");
			 query.setParameter("city", City);
			 query.setParameter("state", State);
			 List results=query.list();
			 for(Iterator iterator=results.iterator();iterator.hasNext();) {
				 ArrayList<String>list5=new ArrayList<String>();
				 Survey survey=(Survey)iterator.next();
				 System.out.println("First Name: "+survey.getFirstname());
				 System.out.println("Last Name: "+survey.getLastname());
				 System.out.println("City: "+survey.getCity());
				 System.out.println("State: "+survey.getState());
				 System.out.println("Zip: "+survey.getZip());
				 list5.add(survey.getId()+""+"");
				 list5.add(survey.getFirstname());
				 list5.add(survey.getLastname());
				 list5.add(survey.getCity());
				 list5.add(survey.getState());
				 list5.add(survey.getZip());
				 list5.add(survey.getPhone());
				 list5.add(survey.getEmail());
				 list5.add(survey.getMenu());
				 list5.add(survey.getRaffle());
				 list5.add(survey.getComments());
				 //String res=String.join(",", list5);
				 //list.add(list5);
				 res=res+ " ["+ String.join(",", list5)+ " ]";
				
		 }
			 return res;
		 
	}
		 if(!LastName.equals("")&& !(City.equals(""))) {
			 org.hibernate.Query query=session.createQuery("FROM Survey WHERE last_name=:last_name AND city=:city");
			 query.setParameter("last_name", LastName);
			 query.setParameter("city", City);
			 List results=query.list();
			 for(Iterator iterator=results.iterator();iterator.hasNext();) {
				 ArrayList<String>list6=new ArrayList<String>();
				 Survey survey=(Survey)iterator.next();
				 System.out.println("First Name: "+survey.getFirstname());
				 System.out.println("Last Name: "+survey.getLastname());
				 System.out.println("City: "+survey.getCity());
				 System.out.println("State: "+survey.getState());
				 System.out.println("Zip: "+survey.getZip());
				 list6.add(survey.getId()+"");
				 list6.add(survey.getFirstname());
				 list6.add(survey.getLastname());
				 list6.add(survey.getCity());
				 list6.add(survey.getState());
				 list6.add(survey.getZip());
				 list6.add(survey.getPhone());
				 list6.add(survey.getEmail());
				 list6.add(survey.getMenu());
				 list6.add(survey.getRaffle());
				 list6.add(survey.getComments());
				 //res=String.join(",", list6);
				 //list.add(list6);
				 res=res+ " ["+ String.join(",", list6)+ " ]";
				
		 }
			 return res;
		 
	}
		 if(!LastName.equals("")&& !(City.equals("")) && !FirstName.equals("")) {
			 org.hibernate.Query query=session.createQuery("FROM Survey WHERE last_name=:last_name AND city=:city AND first_name:=first_name");
			 query.setParameter("last_name", LastName);
			 query.setParameter("city", City);
			 query.setParameter("first_name",FirstName);
			 List results=query.list();
			 for(Iterator iterator=results.iterator();iterator.hasNext();) {
				 ArrayList<String>list7=new ArrayList<String>();
				 Survey survey=(Survey)iterator.next();
				 System.out.println("First Name: "+survey.getFirstname());
				 System.out.println("Last Name: "+survey.getLastname());
				 System.out.println("City: "+survey.getCity());
				 System.out.println("State: "+survey.getState());
				 System.out.println("Zip: "+survey.getZip());
				 list7.add(survey.getId()+"");
				 list7.add(survey.getFirstname());
				 list7.add(survey.getLastname());
				 list7.add(survey.getCity());
				 list7.add(survey.getState());
				 list7.add(survey.getZip());
				 list7.add(survey.getPhone());
				 list7.add(survey.getEmail());
				 list7.add(survey.getMenu());
				 list7.add(survey.getRaffle());
				 list7.add(survey.getComments());
				 //res=String.join(",", list7);
				 //list.add(list7);
				 res=res+ " ["+ String.join(",", list7)+ " ]";
				
		 }
			 return res;
		 
	}
		 if(!LastName.equals("")&& !(City.equals("")) && !State.equals("")) {
			 org.hibernate.Query query=session.createQuery("FROM Survey WHERE last_name=:last_name AND city=:city AND state:=state");
			 query.setParameter("last_name", LastName);
			 query.setParameter("city", City);
			 query.setParameter("state",State);
			 List results=query.list();
			 for(Iterator iterator=results.iterator();iterator.hasNext();) {
				 ArrayList<String>list8=new ArrayList<String>();
				 Survey survey=(Survey)iterator.next();
				 System.out.println("First Name: "+survey.getFirstname());
				 System.out.println("Last Name: "+survey.getLastname());
				 System.out.println("City: "+survey.getCity());
				 System.out.println("State: "+survey.getState());
				 System.out.println("Zip: "+survey.getZip());
				 list8.add(survey.getId()+"");
				 list8.add(survey.getFirstname());
				 list8.add(survey.getLastname());
				 list8.add(survey.getCity());
				 list8.add(survey.getState());
				 list8.add(survey.getZip());
				 list8.add(survey.getPhone());
				 list8.add(survey.getEmail());
				 list8.add(survey.getMenu());
				 list8.add(survey.getRaffle());
				 list8.add(survey.getComments());
				 //res=String.join(",",list8);
				 //list.add(list8);
				 res=res+ " ["+String.join(",", list8)+ " ]";
				
		 }
			 return res;
		 
	}
		 if( !(City.equals("")) ) {
			 org.hibernate.Query query=session.createQuery("FROM Survey WHERE  city=:city ");
			 //query.setParameter("last_name", LastName);
			 query.setParameter("city", City);
			// query.setParameter("first_name",FirstName);
			 List results=query.list();
			 for(Iterator iterator=results.iterator();iterator.hasNext();) {
				 ArrayList<String>list7=new ArrayList<String>();
				 Survey survey=(Survey)iterator.next();
				 System.out.println("First Name: "+survey.getFirstname());
				 System.out.println("Last Name: "+survey.getLastname());
				 System.out.println("City: "+survey.getCity());
				 System.out.println("State: "+survey.getState());
				 System.out.println("Zip: "+survey.getZip());
				 list7.add(survey.getId()+"");
				 list7.add(survey.getFirstname());
				 list7.add(survey.getLastname());
				 list7.add(survey.getCity());
				 list7.add(survey.getState());
				 list7.add(survey.getZip());
				 list7.add(survey.getPhone());
				 list7.add(survey.getEmail());
				 list7.add(survey.getMenu());
				 list7.add(survey.getRaffle());
				 list7.add(survey.getComments());
				 //String res=String.join(",", list7);
				 //list.add(list7);
				 res=res+ " ["+ String.join(",", list7)+ " ]";
				
		 }
			 return res;
		 
	}
		 if(!LastName.equals("")) {
			 org.hibernate.Query query=session.createQuery("FROM Survey WHERE last_name=:last_name ");
			 query.setParameter("last_name", LastName);
			// query.setParameter("city", City);
			//query.setParameter("first_name",FirstName);
			 List results=query.list();
			 for(Iterator iterator=results.iterator();iterator.hasNext();) {
				 ArrayList<String>list7=new ArrayList<String>();
				 Survey survey=(Survey)iterator.next();
				 System.out.println("First Name: "+survey.getFirstname());
				 System.out.println("Last Name: "+survey.getLastname());
				 System.out.println("City: "+survey.getCity());
				 System.out.println("State: "+survey.getState());
				 System.out.println("Zip: "+survey.getZip());
				 list7.add(survey.getId()+"");
				 list7.add(survey.getFirstname());
				 list7.add(survey.getLastname());
				 list7.add(survey.getCity());
				 list7.add(survey.getState());
				 list7.add(survey.getZip());
				 list7.add(survey.getPhone());
				 list7.add(survey.getEmail());
				 list7.add(survey.getMenu());
				 list7.add(survey.getRaffle());
				 list7.add(survey.getComments());
				//String res=String.join(",", list7);
				 //list.add(list7);
				 res=res+ " ["+ String.join(",", list7)+ " ]";
		 }
			 return res;
		 
	}
		 if( !FirstName.equals("")) {
			 org.hibernate.Query query=session.createQuery("FROM Survey WHERE first_name=:first_name");
			 //query.setParameter("last_name", LastName);
			// query.setParameter("city", City);
			 query.setParameter("first_name",FirstName);
			 List results=query.list();
			 for(Iterator iterator=results.iterator();iterator.hasNext();) {
				 ArrayList<String>list7=new ArrayList<String>();
				 Survey survey=(Survey)iterator.next();
				 System.out.println("First Name: "+survey.getFirstname());
				 System.out.println("Last Name: "+survey.getLastname());
				 System.out.println("City: "+survey.getCity());
				 System.out.println("State: "+survey.getState());
				 System.out.println("Zip: "+survey.getZip());
				 list7.add(survey.getId()+"");
				 list7.add(survey.getFirstname());
				 list7.add(survey.getLastname());
				 list7.add(survey.getCity());
				 list7.add(survey.getState());
				 list7.add(survey.getZip());
				 list7.add(survey.getPhone());
				 list7.add(survey.getEmail());
				 list7.add(survey.getMenu());
				 list7.add(survey.getRaffle());
				 list7.add(survey.getComments());
				 //res=String.join(",", list7);
				 //list.add(list7);
				 res=res+  " ["+String.join(",", list7)+ " ]";
				
		 }
			 return res;
		 
	}
		 if(!State.equals("")) {
			 org.hibernate.Query query=session.createQuery("FROM Survey WHERE state=:state");
			 /*query.setParameter("last_name", LastName);
			 query.setParameter("city", City);
			 query.setParameter("first_name",FirstName);*/
			 query.setParameter("state", State);
			 List results=query.list();
			
			 for(Iterator iterator=results.iterator();iterator.hasNext();) {
				 ArrayList<String>list7=new ArrayList<String>();
				 Survey survey=(Survey)iterator.next();
				 System.out.println("First Name: "+survey.getFirstname());
				 System.out.println("Last Name: "+survey.getLastname());
				 System.out.println("City: "+survey.getCity());
				 System.out.println("State: "+survey.getState());
				 System.out.println("Zip: "+survey.getZip());
				 list7.add(survey.getId()+"");
				 list7.add(survey.getFirstname());
				 list7.add(survey.getLastname());
				 list7.add(survey.getCity());
				 list7.add(survey.getState());
				 list7.add(survey.getZip());
				 list7.add(survey.getPhone());
				 list7.add(survey.getEmail());
				 list7.add(survey.getMenu());
				 list7.add(survey.getRaffle());
				 list7.add(survey.getComments());
				 
				 res=res+ " ["+String.join(",", list7)+ " ]";
				 //list.add(list7);
				
		 }
			 return res;
		 
	}
		 //return list;
	 }
		 catch (HibernateException e) {
		if(tx!=null)tx.rollback();
		e.printStackTrace();
		// TODO: handle exception
	}
	 finally {
		session.close();
	}
	return res;
	
 }

/*public ArrayList<String> getList() {
	return list;
}

public void setList(ArrayList<String> list) {
	this.list = list;
}*/
}


