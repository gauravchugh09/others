package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class AddressEntity {

	@Id
	private Integer id;

	@Column(name = "address line 1")
	private String adrLn1;

	@Column(name = "address line 2")
	private String adrLn2;

	private String city;

	private String state;

	private Integer pinCode;

	/*
	 * @OneToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "studentid", referencedColumnName = "id") private
	 * StudentEntity studentEntity;
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentid", referencedColumnName = "id")
	private StudentEntity studentEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdrLn1() {
		return adrLn1;
	}

	public void setAdrLn1(String adrLn1) {
		this.adrLn1 = adrLn1;
	}

	public String getAdrLn2() {
		return adrLn2;
	}

	public void setAdrLn2(String adrLn2) {
		this.adrLn2 = adrLn2;
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

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public StudentEntity getStudentEntity() {
		return studentEntity;
	}

	public void setStudentEntity(StudentEntity studentEntity) {
		this.studentEntity = studentEntity;
	}

}