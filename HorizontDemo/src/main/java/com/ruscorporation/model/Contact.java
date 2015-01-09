package com.ruscorporation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "contact")
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
// Describe child record entity name(Use for SINGLE_TABLE inheritance strategy)
// @DiscriminatorColumn(name="contactType",
// discriminatorType=DiscriminatorType.STRING)

// This annotation(XmlRootElement) required if you want send response in XML format
@XmlRootElement(name = "contact")
public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull
	@Size(min = 2, max = 50)
	private String firstName;

	@NotNull
	@Size(min = 2, max = 50)
	private String lastName;

	private Short age;

	private String driverLicense;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Short getAge() {
		return age;
	}

	@Column(name = "driver_license")
	public String getDriverLicense() {
		return driverLicense;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}

}
