package com.example.dxc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userEmail;
    private String vehicleName;
    private String vehicleModel;
    private String vehicleType;
    private int vehicleAge;
    private String vehicleCoverage;
    private String quoteGenerateDate;
    
    private String popupMessage;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public int getVehicleAge() {
		return vehicleAge;
	}
	public void setVehicleAge(int vehicleAge) {
		this.vehicleAge = vehicleAge;
	}
	public String getVehicleCoverage() {
		return vehicleCoverage;
	}
	public void setVehicleCoverage(String vehicleCoverage) {
		this.vehicleCoverage = vehicleCoverage;
	}
	public User(Long id, String userName, String userEmail, String vehicleName, String vehicleModel, String vehicleType,
			int vehicleAge, String vehicleCoverage) {
		super();
		this.id = id;
		this.userName = userName;
		this.userEmail = userEmail;
		this.vehicleName = vehicleName;
		this.vehicleModel = vehicleModel;
		this.vehicleType = vehicleType;
		this.vehicleAge = vehicleAge;
		this.vehicleCoverage = vehicleCoverage;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPopupMessage() {
		return popupMessage;
	}
	public void setPopupMessage(String popupMessage) {
		this.popupMessage = popupMessage;
	}
	public User(String popupMessage) {
		super();
		this.popupMessage = popupMessage;
	}
	public String getQuoteGenerateDate() {
		return quoteGenerateDate;
	}
	public void setQuoteGenerateDate(String quoteGenerateDate) {
		this.quoteGenerateDate = quoteGenerateDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userEmail=" + userEmail + ", vehicleName=" + vehicleName
				+ ", vehicleModel=" + vehicleModel + ", vehicleType=" + vehicleType + ", vehicleAge=" + vehicleAge
				+ ", vehicleCoverage=" + vehicleCoverage + ", quoteGenerateDate=" + quoteGenerateDate
				+ ", popupMessage=" + popupMessage + "]";
	}
	
    
	
	
	
	
	
}