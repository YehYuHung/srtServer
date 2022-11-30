package com.sister.report.Models;

public class Customer {
	private String customerName;
	private Produce produce;

	public Customer() {
	}

	public Customer(String customerName, Produce produce) {
		this.customerName = customerName;
		this.produce = produce;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Produce getProduce() {
		return produce;
	}

	public void setProduce(Produce produce) {
		this.produce = produce;
	}
}
