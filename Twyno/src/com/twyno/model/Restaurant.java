package com.twyno.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Restaurant implements TwynoObject {

	@Id
	Long id;
	private String name;
	private String addressStreet;
	private String addressNumber;
	private String addressCity;
	private String addressPostcode;
	private String addressPlace;
	private String website;
	private String email;
	private String phonenumber;
	private String openinghoursMo;
	private String openinghoursTu;
	private String openinghoursWe;
	private String openinghoursTh;
	private String openinghoursFr;
	private String openinghoursSa;
	private String openinghoursSu;
	private Boolean childrensMenu;
	private Boolean playground;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String[] getFields() {

		return new String[] { "Name", "Website", "AddressStreet",
				"AddressNumber", "AddressPostcode", "AddressPlace", "Email",
				"Phonenumber", "OpeninghoursMo", "OpeninghoursTu",
				"OpeninghoursWe", "OpeninghoursTh", "OpeninghoursFr",
				"OpeninghoursSa", "OpeninghoursSu", "ChildrensMenu", "Playground" };
	}

	public Boolean getChildrensMenu() {
		return childrensMenu;
	}

	public void setChildrensMenu(Boolean childrensMenu) {
		this.childrensMenu = childrensMenu;
	}

	public Boolean getPlayground() {
		return playground;
	}

	public void setPlayground(Boolean playground) {
		this.playground = playground;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getOpeninghoursMo() {
		return openinghoursMo;
	}

	public void setOpeninghoursMo(String openinghoursMo) {
		this.openinghoursMo = openinghoursMo;
	}

	public String getOpeninghoursTu() {
		return openinghoursTu;
	}

	public void setOpeninghoursTu(String openinghoursTu) {
		this.openinghoursTu = openinghoursTu;
	}

	public String getOpeninghoursWe() {
		return openinghoursWe;
	}

	public void setOpeninghoursWe(String openinghoursWe) {
		this.openinghoursWe = openinghoursWe;
	}

	public String getOpeninghoursTh() {
		return openinghoursTh;
	}

	public void setOpeninghoursTh(String openinghoursTh) {
		this.openinghoursTh = openinghoursTh;
	}

	public String getOpeninghoursFr() {
		return openinghoursFr;
	}

	public void setOpeninghoursFr(String openinghoursFr) {
		this.openinghoursFr = openinghoursFr;
	}

	public String getOpeninghoursSa() {
		return openinghoursSa;
	}

	public void setOpeninghoursSa(String openinghoursSa) {
		this.openinghoursSa = openinghoursSa;
	}

	public String getOpeninghoursSu() {
		return openinghoursSu;
	}

	public void setOpeninghoursSu(String openinghoursSu) {
		this.openinghoursSu = openinghoursSu;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
