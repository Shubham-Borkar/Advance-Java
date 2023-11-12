package com.sunbeaminfo.pojos;

import java.time.LocalDate;
import javax.persistence.*;

@Embeddable
public class Passport {
	@Column(name="passport_no",unique = true,length = 20)
	private String passportNo;
	@Column(name="issued_on")
	private LocalDate issuedOn;
	@Column(name="exp_date")
	private LocalDate expDate;
	@Column(length = 20)
	private String country;

	public Passport() {
		// TODO Auto-generated constructor stub
	}

	public Passport(String passportNo, LocalDate issuedOn, LocalDate expDate, String country) {
		super();
		this.passportNo = passportNo;
		this.issuedOn = issuedOn;
		this.expDate = expDate;
		this.country = country;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public LocalDate getIssuedOn() {
		return issuedOn;
	}

	public void setIssuedOn(LocalDate issuedOn) {
		this.issuedOn = issuedOn;
	}

	public LocalDate getExpDate() {
		return expDate;
	}

	public void setExpDate(LocalDate expDate) {
		this.expDate = expDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Passport [passportNo=" + passportNo + ", issuedOn=" + issuedOn + ", expDate=" + expDate + ", country="
				+ country + "]";
	}

}
