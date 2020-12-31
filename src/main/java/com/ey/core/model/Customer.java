package com.ey.core.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Table(name = "whlr_customer_detail")
@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Size(min = 1, max = 24)
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Size(min = 1, max = 24)
	@Column(name = "last_name")
	private String lastName;

	@Email
	@Column(name = "email")
	private String email;

	@Range(max = 9999999999L)
	@Column(name = "phone")
	private Long phone;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subscriber", fetch = FetchType.EAGER)
	private List<Address> address;
}
