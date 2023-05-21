package UserApp_controller;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import UserApp.UserConfig;
import UserApp_dao.UserDao;
import UserApp_dto.User;

public class User_Controller_CRUD {

	static Scanner sc = new Scanner(System.in);
	static UserDao dao;

	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);
		dao = context.getBean(UserDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1. Save User");
		System.out.println("2. Update User");
		System.out.println("3. verify user by phone and password");
		System.out.println("4. verify user by Email and password");
		System.out.println("5. verify user by id and password");
		System.out.println("6. Delete User");
		System.out.println("7. Print details by Id");

		int choice = sc.nextInt();
		switch (choice) {
		case 1: {

			save();
			break;
		}
		case 2: {
			Update();
			break;
		}
		case 3: {
			phone_password();
			break;
		}
		case 4: {
			email_password();
			break;
		}
		case 5: {
			id_password();
			break;
		}
		case 6: {
			Delete();
			break;
		}
		case 7: {
			Fetch_by_id();
			break;
		}
		}

	}

	public static void save() {
		System.out.println("Enter name, phone, email and password");
		String name = sc.next();
		long phone = sc.nextLong();
		String email = sc.next();
		String password = sc.next();
		User u = new User();
		u.setName(name);
		u.setEmali(email);
		u.setPhone(phone);
		u.setPassword(password);
		u = dao.saveUser(u);
		System.out.println("User saved with ID: " + u.getId());

	}

	public static void Update() {
		System.out.println("Enter the id to update your details");
		int id = sc.nextInt();
		System.out.println("Enter name, phone, email and password");
		String name = sc.next();
		long phone = sc.nextLong();
		String email = sc.next();
		String password = sc.next();
		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setEmali(email);
		u.setPhone(phone);
		u.setPassword(password);
		u = dao.UpdateUser(u);
		System.out.println("User Details Updated with ID: " + u.getId());
	}

	public static void phone_password() {
		System.out.println("Enter your phone and passwoed");
		long phone = sc.nextLong();
		String password = sc.next();
		User u = dao.verifyUser(phone, password);
		if (u != null) {
			System.out.println("Login Successfully");
			System.out.println("Name " + u.getName());
			System.out.println("Email " + u.getEmali());
			System.out.println("Phone " + u.getPhone());
			System.out.println("Password " + u.getPassword());

		} else {
			System.err.println("Invalid phone number or pasworrd");
		}

	}

	public static void email_password() {
		System.out.println("Enter your email and password");
		String email = sc.next();
		String password = sc.next();
		User u = dao.verifyUseremail(email, password);
		if (u != null) {
			System.out.println("Login Successfully");
			System.out.println("Name " + u.getName());
			System.out.println("Email " + u.getEmali());
			System.out.println("Phone " + u.getPhone());
			System.out.println("Password " + u.getPassword());

		} else {
			System.err.println("Invalid phone number or pasworrd");
		}

	}

	public static void id_password() {
		System.out.println("Enter your id and password");
		int id = sc.nextInt();
		String password = sc.next();
		User u = dao.verifyUserid(id, password);
		if (u != null) {
			System.out.println("Login Successfully");
			System.out.println("Name " + u.getName());
			System.out.println("Email " + u.getEmali());
			System.out.println("Phone " + u.getPhone());
			System.out.println("Password " + u.getPassword());

		} else {
			System.err.println("Invalid phone number or pasworrd");
		}

	}

	public static void Delete() {
		System.out.println("Enter the id to update your details");
		int id = sc.nextInt();
		boolean u = dao.deleteUser(id);
		if (u) {
			System.out.println("Deleted record Successfully");

		} else {
			System.out.println("Invalid ID");
		}

	}
	public static void Fetch_by_id() {
		System.out.println("Enter your id to print the details");
		int id = sc.nextInt();
	
		User u = dao.finduserbyID(id);
		if (u != null) {
			System.out.println("Details of id "+u.getId());
			System.out.println("Name " + u.getName());
			System.out.println("Email " + u.getEmali());
			System.out.println("Phone " + u.getPhone());
			System.out.println("Password " + u.getPassword());

		} else {
			System.err.println("Invalid ID");
		}

	}
}
