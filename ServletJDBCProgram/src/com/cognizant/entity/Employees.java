package com.cognizant.entity;

public class Employees {
	
		private int empId;
		private String empName;
		private double empSalary;
		private String empDesignation;
		
		
		public Employees(){
			
		}


		public int getEmpId() {
			return empId;
		}


		public void setEmpId(int empId) {
			this.empId = empId;
		}


		public String getEmpName() {
			return empName;
		}


		@Override
		public String toString() {
			return "Employees [empId=" + empId + ", empName=" + empName + ", empSalary=" + empSalary
					+ ", empDesignation=" + empDesignation + "]";
		}


		public void setEmpName(String empName) {
			this.empName = empName;
		}


		public double getEmpSalary() {
			return empSalary;
		}


		public void setEmpSalary(double empSalary) {
			this.empSalary = empSalary;
		}


		public String getEmpDesignation() {
			return empDesignation;
		}


		public void setEmpDesignation(String empDesignation) {
			this.empDesignation = empDesignation;
		}
		
		
		
}
