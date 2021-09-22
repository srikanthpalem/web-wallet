package org.cap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;
	private String doorNo;
	private String streetName;
	private String city;
	private String pincode;
	private String state;
	
	/*@OneToOne
	@JoinColumn(name="customer_fk")
	private Customer customer;*/
	
	public Address() {
		
	}
	
	
	public Address(int addressId, String doorNo, String streetName, String city, String pincode, String state) {
		super();
		this.addressId = addressId;
		this.doorNo = doorNo;
		this.streetName = streetName;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
	}
	
	
	/*
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}*/


	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", doorNo=" + doorNo + ", streetName=" + streetName + ", city="
				+ city + ", pincode=" + pincode + ", state=" + state + "]";
	}
	


}
