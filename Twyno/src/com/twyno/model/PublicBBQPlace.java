package com.twyno.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PublicBBQPlace implements TwynoObject {

	@Id
	Long id;
	private String name;
	private String addressStreet;
	private String addressNumber;
	private String addressCity;
	private String addressPostcode;
	private String addressPlace;
	private String latitude;
	private String longitude;

	public String[] getFields() {

		return new String[] { "Name", "AddressStreet",
				"AddressNumber", "AddressPostcode", "AddressPlace", "Latitude", "Longitude" };
	}


	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressPostcode() {
		return addressPostcode;
	}

	public void setAddressPostcode(String addressPostcode) {
		this.addressPostcode = addressPostcode;
	}

	public String getAddressPlace() {
		return addressPlace;
	}

	public void setAddressPlace(String addressPlace) {
		this.addressPlace = addressPlace;
	}


}
