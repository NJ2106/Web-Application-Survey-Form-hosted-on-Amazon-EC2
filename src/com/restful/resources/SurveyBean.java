package com.restful.resources;
import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import srinivas.SOAP.ManageSurvey;

//import org.primefaces.util.DateUtils;

@ManagedBean(name="s")
@ApplicationScoped
public class SurveyBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String fname,lname,zip,phone,email,city,state,saddress,likes[],interests,raffle,comments;
	public  String menuString = "Very Likely,Likely,Unlikely";
	public  String[] menuArray=menuString.split(",");
	private String menu;
	public Date surveyDate;
	public Date startDate;
	List<String> FinalList=new ArrayList<String>();
	private String list1;
	 
	 
	

	
	public String getList1() {
		return list1;
	}
	/*public SurveyBean() {
		
	}
	public SurveyBean(String fname,String lname,String saddress,String zip,String city,String state,String email,Date surveyDate,Date startDate,String interests,String menu,String raffle,String comments) {
		this.fname=fname;
		this.lname=lname;
		this.saddress=saddress;
		this.zip=zip;
		this.city=city;
		this.state=state;
		this.email=email;
		this.surveyDate=surveyDate;
		this.startDate=startDate;
		this.interests=interests;
		this.raffle=raffle;
		this.comments=comments;
		this.menu=menu;
	}*/
	//private  ArrayList<SurveyBean> list=new ArrayList<SurveyBean>(Arrays.asList(new SurveyBean(getFname(),getLname(),getSaddress(),getZip(),getCity(),getState(),getEmail(),getSurveyDate(),getStartDate(),getInterests(),getMenu(),getRaffle(),getComments())));
	private  ArrayList<Object> list=new ArrayList<Object>();
	
	public ArrayList<Object> getList(){
		return list;
	}
	WinningResult w1=new WinningResult();
	StudentService s1=new StudentService();
	
	public StudentService getS1() {
		return s1;
	}

	public void setS1(StudentService s1) {
		this.s1 = s1;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	

	public Date getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String[] getLikes() {
		return likes;
	}

	public void setLikes(String[] likes) {
		this.likes = likes;
	}
	
	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	

	public String getRaffle() {
		return raffle;
	}

	public void setRaffle(String raffle) {
		this.raffle = raffle;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	
public List<String>completeMenu(String menuprefix){
		
		List<String> matches=new ArrayList<String>();
		for(String possibleOption : menuArray) {
			if(possibleOption.toUpperCase().startsWith(menuprefix.toUpperCase())) {
				matches.add(possibleOption);
			}
		}
		return matches;
		
	
}
public void ajaxcheck() {
	//System.out.println("Zip:"+ getZip());
	Client client=ClientBuilder.newClient();
	String string=client.target("http://ec2-34-238-116-176.compute-1.amazonaws.com/Trial11/rest/Zip").path("{zip}").resolveTemplate("zip",getZip()).request().get(String.class);
	//String string=client.target("http://localhost:8080/Trial11/rest/Zip").path("{zip}").resolveTemplate("zip",getZip()).request().get(String.class);
	//String string=client.target()
	//System.out.println(string);
	String[] parts= string.split(",");
	String part1=parts[0];
	String part2=parts[1];
	setCity(part1);
	setState(part2);
	
	
}
public void startDateValid(FacesContext context, UIComponent component, Object value) throws ValidatorException{
	UIInput start= (UIInput)component.getAttributes().get("date");
	Object startvalue=start.getValue();
	//System.out.println("date" +startvalue);
	Date surveyDate=(Date)startvalue;
	Date startDate=(Date)value;
	if(startDate.before(surveyDate)) {
		FacesMessage message=new FacesMessage("Semester start date should later than survey date");
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		throw new ValidatorException(message);
	}
}


public String Submit() {
	ManageSurvey ms=new ManageSurvey();
	 //ms.addMember(fname, lname, city, state, zip, saddress, phone, menu, raffle, comments, email, surveyDate, startDate);
	//System.out.println("After MS "+ms);
	
	ms.addMember(getFname(), getLname(), getCity(), getState(), getZip(), getSaddress(), getPhone(), getMenu(), getRaffle(), getComments(), getEmail(), getSurveyDate(), getStartDate());
	//ms.addMember(getFname(), getLname(), getCity(), getState(), getZip(), getSaddress(), getPhone(), getMenu(), getRaffle(), getComments(), getEmail(), getSurveyDate(), getStartDate());
	ArrayList<String> singlelist=new ArrayList<String>();
	singlelist.add(getFname());
	singlelist.add(getLname());
	singlelist.add(getSaddress());
	singlelist.add(getZip());
	singlelist.add(getCity());
	singlelist.add(getState());
	singlelist.add(getEmail());
	singlelist.add(getPhone());
	singlelist.add(getRaffle());
	singlelist.add(getComments());
	list.add(singlelist);
	
	w1=StudentService.computeMeanSD(getRaffle());
	 if(w1.getMean()<90)
		return "SimpleAcknowledgement.xhtml";
	 else
		 return "WinnerAcknowledgement.xhtml";
	 
	 
} 
public WinningResult getW1() {
	return w1;
}

public void setW1(WinningResult w1) {
	this.w1 = w1;
}

public String Cancel() {
	return  null;
}
public void searchRecord() {
	if(getFname()!=null||getFname()==null||getCity()!=null||getCity()==null||getState()!=null||getState()==null||getLname()==null||getLname()!=null) {
		ManageSurvey ms=new ManageSurvey();
		//List array= {'getFname()','getLname()','getCity()','getState()'};
		//setList1(ms.listResults(array));
		setList1(ms.listResults(getFname(),getLname(),getCity(),getState()));
		//setTotalList(ms.listResults(FirstName, LastName, City, State);//getZip(), getSaddress(), getPhone(), getMenu(), getRaffle(), getComments(), getEmail(), getSurveyDate(), getStartDate());
	}
		
}
private void setList1(String listResults) {
	this.list1=listResults;// TODO Auto-generated method stub
	
}

public String DeleteRecord(String record) {
	String serialID=record.substring(1, record.indexOf(','));
	ManageSurvey ms=new ManageSurvey();
	ms.deleteMember(Integer.parseInt(serialID));
	searchRecord();
	return "Search.xhtml";
}
/*public List<String> makeList(){
	List<String> FinalList=new ArrayList<String>();
ArrayList<String> list=new ArrayList<String>();
list.add(getFname());
list.add(getLname());
list.add(getSaddress());
list.add(getZip());
list.add(getCity());
list.add(getState());   //cntl+alt+/
list.add(getEmail());
list.add(getPhone());
list.add(getRaffle());
list.add(getComments());
FinalList.addAll(list);
return FinalList;
}*/
}
