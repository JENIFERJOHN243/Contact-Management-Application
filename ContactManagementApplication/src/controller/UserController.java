package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.UserDetails;
import dao.ContactManagementDAO;
import validation.Validation;

public class UserController {
	ContactManagementDAO contactDAO = new ContactManagementDAO();

	public void startProcess() throws ClassNotFoundException, SQLException {
		Scanner scanner = new Scanner(System.in);
		int userChoice;
		System.out.println("\t\t MY CONTACTS \t\t");
		System.out.println("1 : Add Contacts \n2 : Update Contacts \n3 : Delete Contacts \n4 : Search Contacts \n5 : View All Contacts \n6 : Exit");
		System.out.println("Enter Your Choice");
		userChoice = scanner.nextInt();
		switch(userChoice) {
		case 1:{
			addContacts();
			break;
		}
		case 2:{
			updateContacts();
			break;
		}
		case 3:{
			deleteContacts();
			break;
		}
		case 4:{
			searchContacts();
			break;
		}
		case 5:{
			viewAllContacts();
			break;
		}
		case 6:{
			break;
		}
		default :{
			System.out.println("Invalid Choice !.. Try Again");
		}
		}
	}
	
	private void viewAllContacts() throws ClassNotFoundException, SQLException {
		System.out.println();
		ArrayList<UserDetails> userList = contactDAO.viewUserDetails();
		for(int index=0;index<userList.size();index++) {
			System.out.println("  "+userList.get(index).getFirstName()+"  "+userList.get(index).getLastName()
					+"  "+userList.get(index).getMobileNumber()+"  "+userList.get(index).getEmailId());
		}
		System.out.println();
		startProcess();
	}
	

	private void searchContacts() throws ClassNotFoundException, SQLException {
		System.out.println();
		UserDetails user = new UserDetails();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the First Name To Be Search");
		user.setFirstName(scanner.next());
		
		ArrayList<UserDetails> userList = contactDAO.searchUserDetails(user);
		for(int index=0;index<userList.size();index++) {
			System.out.println("  "+userList.get(index).getFirstName()+"  "+userList.get(index).getLastName()
					+"  "+userList.get(index).getMobileNumber()+"  "+userList.get(index).getEmailId());
		}
		System.out.println();
		startProcess();
		
	}

	private void deleteContacts() throws ClassNotFoundException, SQLException {
		System.out.println();
		UserDetails user = new UserDetails();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the First Name To Be Deleted");
		user.setFirstName(scanner.next());

		System.out.println("Enter the Last Name To Be Deleted");
		user.setLastName(scanner.next());
		Integer id =contactDAO.fetchTheUserId(user);
		if(id!=null) {
			int result = contactDAO.deleteUserDetails(id);
			if(result == 1) {
				System.out.println("Contact Deleted Successfully");
				System.out.println();
				startProcess();
			}
		}else {
			System.out.println("No Contacts Found");
			System.out.println();
			deleteContacts();
		}
	}

	private void updateContacts() throws ClassNotFoundException, SQLException {
		System.out.println();
		UserDetails user = new UserDetails();
		Validation valid = new Validation();
		Scanner scanner = new Scanner(System.in);
		boolean boolValue = false;
		boolean boolValue1 = false;
		System.out.println("Enter the First Name To Be Updated");
		user.setFirstName(scanner.next());

		System.out.println("Enter the Last Name To Be Updated");
		user.setLastName(scanner.next());
		Integer id =contactDAO.fetchTheUserId(user);
		if(id!=null) {
			user.setUserId(id);
			System.out.println("Enter the First Name To Update");
			user.setFirstName(scanner.next());
			System.out.println("Enter the Last Name To Update");
			user.setLastName(scanner.next());
			while(!boolValue) {
				System.out.println("Enter the Mobile Number To Update");
				String mobileNumber = scanner.next();
				if(valid.mobileNumberValidation(mobileNumber)) {
					user.setMobileNumber(mobileNumber);
					boolValue=true;
				}else {
					System.out.println("Invalid Mobile Number :( Enter an Correct Mobile Number");
				}
			}
			while(!boolValue1) {
				System.out.println("Enter the Mail ID To Update");
				String mailId = scanner.next();
				if(valid.emailIdValidation(mailId)) {
					user.setEmailId(scanner.next());
					boolValue1 = true;
				}else {
					System.out.println("Invalid Email Address :( Enter an Valid Email Id");
				}
			}
			contactDAO.updateUserDetails(user);
			System.out.println("Contact Updated Successfully");
			System.out.println();
			startProcess();
			}else {
			System.out.println("No Contacts Found");
			System.out.println();
			updateContacts();
		}
	}

	private void addContacts() throws ClassNotFoundException, SQLException {
		System.out.println();
		UserDetails user = new UserDetails();
		Validation valid = new Validation();
		Scanner scanner = new Scanner(System.in);
		boolean boolValue = false;
		boolean boolValue1 = false;
		System.out.println("Enter the First Name");
		user.setFirstName(scanner.next());
		System.out.println("Enter the Last Name");
		user.setLastName(scanner.next());
		
		while(!boolValue) {
		System.out.println("Enter the Mobile Number");
		String mobileNumber = scanner.next();
		if(valid.mobileNumberValidation(mobileNumber)) {
			user.setMobileNumber(mobileNumber);
			boolValue=true;
		}else {
			System.out.println("Invalid Mobile Number :( Enter an Correct Mobile Number");
		    }
		}
		
		while(!boolValue1) {
			System.out.println("Enter the Mail ID");
			String mailId = scanner.next();
			if(valid.emailIdValidation(mailId)) {
				user.setEmailId(scanner.next());
				boolValue1 = true;
			}else {
				System.out.println("Invalid Email Address :( Enter an Valid Email Id");
			}
		}
		contactDAO.addUserDetails(user);
	    user.setUserId(contactDAO.fetchTheUserId(user));
		System.out.println("Contacts Added Successfully");
		System.out.println();
		startProcess();
	}
	
}
