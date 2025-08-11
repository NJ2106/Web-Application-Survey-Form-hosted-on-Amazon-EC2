package srinivas.SOAP;

import java.util.Date;

import javax.persistence.*;

public class Survey implements java.io.Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
int id;
String firstname;
String lastname;
String city;
String state;
String zip;
String saddress;
String phone;
String menu;
String raffle;
String comments;
String email;
Date surveyDate;
Date startDate;




public Survey() {}

public Survey(String firstname, String lastname, String city, String state, String zip, String saddress, String phone,
		String menu, String raffle, String comments, String email, Date surveyDate, Date startDate) {
	super();
	this.firstname = firstname;
	this.lastname = lastname;
	this.city = city;
	this.state = state;
	this.zip = zip;
	this.saddress = saddress;
	this.phone = phone;
	this.menu = menu;
	this.raffle = raffle;
	this.comments = comments;
	this.email = email;
	this.surveyDate = surveyDate;
	this.startDate = startDate;
	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getZip() {
	return zip;
}
public void setZip(String zip) {
	this.zip = zip;
}
public String getSaddress() {
	return saddress;
}
public void setSaddress(String saddress) {
	this.saddress = saddress;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getRecommendation() {
	return menu;
}
public void setRecommendation(String menu) {
	this.menu = menu;
}
public String getRaffle() {
	return raffle;
}
public String getMenu() {
	return menu;
}

public void setMenu(String menu) {
	this.menu = menu;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
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
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
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


}
