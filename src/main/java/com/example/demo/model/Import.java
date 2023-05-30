package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="import_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Import 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="serial")
	private Integer serial;
	
	@Column(name="name")
	private String name;
	
	@Column(name="place")
	private String place;
	
	@Column(name="cell")
	private Long cell;
	
	@Column(name="mail")
	private String mail;

}
