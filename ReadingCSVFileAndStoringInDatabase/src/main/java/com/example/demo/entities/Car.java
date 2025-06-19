package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	 
	private String model;
	private double milesPerGallone;
	private int numberOfCylinders;
	private double displacement;
	private double horsePower;
	private double rearAxelRatio;
	private double weight;
	private double mileTime;
	private String engineShape;
	private String transmission;
	private int numberOfForwaedGears;
	private int numerOfCaburetors;

}
