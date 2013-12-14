package com.bearprogrammer.blog.springdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bearprogrammer.blog.springdata.model.Person;
import com.bearprogrammer.blog.springdata.model.PersonRepository;


public class Main {
	
	public static void main(String[] args) {
		Main main = new Main();
		
		try {
			main.loop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		main.exit();
	}
	
	public ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/config.xml");
	
	private final BufferedReader userIn;

	public Main() {
		userIn = new BufferedReader(new InputStreamReader(System.in));
	}
	
	private void addPerson() throws IOException {
		editPerson(new Person());
	}
	
	private void editPerson (Person person) throws IOException {
		clearScreen();
		
		if (person.getId() == null) {
			println("--- Add Person ---");
		} else {
			println("--- Edit Person ---");
		}
		
		do {
			print("First name > ");
			person.setFirstName(userIn.readLine());
		} while (person.getFirstName() == null || person.getFirstName().isEmpty());
		
		do {
			print("Last name > ");
			person.setLastName(userIn.readLine());
		} while (person.getLastName() == null || person.getLastName().isEmpty());
		
		context.getBean(PersonRepository.class).save(person);
		
		println("Person saved.");
		waitToContinue();
	}
	
	private void clearScreen() {
		for (int i = 0; i < 50; i++) System.out.println();
	}
	
	private void exit() {
		context.destroy();
	}
	
	private void loop() throws Exception {
		LOOP : while (true) {
			clearScreen();
			
			println("What do you want to do?");
			println("1 - Add Person");
			println("2 - Edit Person");
			println("3 - Find Person");
			println("4 - Delete Person");
			println();
			println("0 - Exit");
			
			int command = readInteger("Type command number");
			
			switch(command) {
			case 1:
				addPerson();
				break;
			case 2:
				editPerson();
				break;
			case 3:
				findPerson();
				break;
			case 4:
				deletePerson();
				break;
			case 0:
				println();
				println("Bye!");
				println();
				break LOOP;
			}
		}
		
	}

	private void deletePerson() throws IOException {
		Person p = selectPerson();
		if (p != null) {
			context.getBean(PersonRepository.class).delete(p);
			println("Person deleted.");
			waitToContinue();
		}
	}

	private void findPerson() throws IOException {
		clearScreen();
		println("--- Find Person ---");
		print("Type first or last name > ");
		String name = userIn.readLine();
		if (name != null && !name.isEmpty()) {
			List<Person> result = context.getBean(PersonRepository.class).findByName(name.toLowerCase());
			
			int size = result.size(); 
			if (size == 1) println("1 person found.");
			else println(size + " people found:");
			
			for (Person p : result) {
				println(p.getId() + " - " + p.getFirstName() + " " + p.getLastName());
			}
		}
		waitToContinue();
	}

	private int readInteger(String message) throws IOException {
		Integer command = null;
		while (command == null) {
			try {
				print(message);
				print(" > ");
				command = Integer.parseInt(userIn.readLine());
			} catch (NumberFormatException nfe) {
				println("Invalid number, please try again.");
			}
		}
		return command;
	}
	
	private void editPerson() throws IOException {
		Person p = selectPerson();
		if (p != null) {
			editPerson(p);
		}
	}

	private Person selectPerson() throws IOException {
		PersonRepository repository = context.getBean(PersonRepository.class);
		Iterable<Person> people = repository.findAll();
		
		clearScreen();
		println("--- Select Person ---");
		for (Person p : people) {
			println(p.getId() + " - " + p.getFirstName() + " " + p.getLastName());
		}
		
		int selectedId = readInteger("Select person by ID or Zero to exit");
		
		if (selectedId == 0) return null;
		return repository.findOne(selectedId);
	}

	private void println() {
		System.out.println();
	}
	
	private void print(String text) {
		System.out.print(text);
	}
	
	private void println(String text) {
		System.out.println(text);
	}
	
	private void waitToContinue() throws IOException {
		waitToContinue(null);
	}
	
	private void waitToContinue(String text) throws IOException {
		if (text == null) text = "Press enter to continue...";
		print(text);
		userIn.readLine();
	}

}
