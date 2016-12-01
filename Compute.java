/* 5.2 This assignment will test your knowledge on Inheritance and Overriding. */

abstract class Employee {

	int empid;
	String empname;
	int total_leaves=30;
	double total_salary;

	abstract void calculate_balance_leaves();	// abstract method
	abstract boolean avail_leave(int no_of_leaves, char type_of_leave);
	abstract void calculate_salary();
}

class PermanentEmp extends Employee {

	int paid_leave=10, sick_leave=10, casual_leave=10;	//initialized leaves
	double basic;
	double hra;  
	double pf; 

	void print_leave_details(){
		System.out.println("\nLEAVE DETAILS FOR PERMANENT EMPLOYEES");
		System.out.println("=====================================");
		System.out.println("Employee Id: " + empid);
		System.out.println("Employee Name: " + empname);
		System.out.println("Remaining Paid Leaves: " + paid_leave);
		System.out.println("Remaining Sick Leaves: " + sick_leave);
		System.out.println("Remaining Casual Leaves: " + casual_leave);
	}
	
	@Override 	// @Override keyword to override a method inside a class.
	void calculate_balance_leaves() {
		System.out.println("\nLEAVE MGMT SYSTEM FOR PERMANENT EMPLOYEES");
		System.out.println("=========================================");
		System.out.print("Enter type of leave (p=Paid, s=Sick, c=Casual): ");
		char type_of_leave = (System.console().readLine()).charAt(0);
		System.out.print("Enter no of leaves (upto 10): ");
		int no_of_leaves = Integer.parseInt(System.console().readLine());		
		
		if (avail_leave(no_of_leaves, type_of_leave) == true)
		{
			switch (type_of_leave){		// switch logic to select a particular condition.
			case 'p':
				paid_leave = paid_leave - no_of_leaves;
				break;
			case 's':
				sick_leave = sick_leave - no_of_leaves;
				break;
			case 'c':
				casual_leave = casual_leave - no_of_leaves;
				break;
			}
		}
	}

	@Override 
	boolean avail_leave(int no_of_leaves, char type_of_leave) {
		switch (type_of_leave){
		case 'p':
			if (no_of_leaves <= paid_leave)
			return true;
			break;
		case 's':
			if (no_of_leaves <= sick_leave)
			return true;
			break;
		case 'c':
			if (no_of_leaves <= casual_leave)
			return true;
			break;
		}
		return false;
	}

	@Override 
	void calculate_salary(){
		System.out.println("ENTER DETAILS FOR PERMANENT EMPLOYEE");
		System.out.println("====================================");
		System.out.print("Enter permanent employee id: ");
		empid = Integer.parseInt(System.console().readLine());
		System.out.print("Enter permanent employee name: ");	
		empname = System.console().readLine();
		System.out.print("Enter the basic salary: ");
		basic = Double.parseDouble(System.console().readLine());
		hra = .50*basic;
		pf  = .20*basic;
		total_salary = basic + hra - pf;
		System.out.println("Total Salary of Employee: " + total_salary);
	}	
}

class TemporaryEmp extends Employee {		// Temporary employee awarded only basic salary. No leaves No HRA No PF.
	
	double basic;

	@Override 
	void calculate_balance_leaves(){
	//	kept it blank!	As no leaves allocated.
	}
	
	@Override 
	boolean avail_leave(int no_of_leaves, char type_of_leave){
		return false;	// return statement required for methods having return type.
	}
	
	@Override 
	void calculate_salary(){
		System.out.println("\nENTER DETAILS FOR TEMPORARY EMPLOYEE");
		System.out.println("====================================");
		System.out.print("Enter temporary employee id: ");
		empid = Integer.parseInt(System.console().readLine());
		System.out.print("Enter temporary employee name: ");	
		empname = System.console().readLine();
		System.out.print("Enter the basic salary: ");
		basic = Double.parseDouble(System.console().readLine());
		total_salary = basic;
		System.out.println("Total Salary of Employee: " + total_salary);
	}

	void print_leave_details(){
		System.out.println("\nLEAVE DETAILS FOR TEMPORARY EMPLOYEES");
		System.out.println("=====================================");
		System.out.println("NO LEAVES ALLOCATED FOR TEMPORARY EMPLOYEES!");
	}
}

class Compute {

	public static void main(String[] args){

		PermanentEmp pe = new PermanentEmp();		// Object created with constructor to initialize.
		pe.calculate_salary();		
		pe.calculate_balance_leaves();
		pe.print_leave_details();
		TemporaryEmp te = new TemporaryEmp();
		te.calculate_salary();		
		te.calculate_balance_leaves();
		te.print_leave_details();
	}

}