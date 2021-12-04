package menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Customer;
import model.User;
import persistence.RepositoryUser;

public class MenuCustomer {
    RepositoryUser repositoryUser = new RepositoryUser();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: List all customers");
        System.out.println("2: Update account status");
        System.out.println("3: List total active customers");
        System.out.println("4: List total active and not active customers");
        System.out.println("5: Update user's phone number by user ID");
        System.out.println("6: Update user's email by user ID");
        System.out.println("7: Save a new customer");
        System.out.println("100 - Return to Main Menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

    protected void menuChoice(Scanner input) {

        int userChoice;
        do {
            userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
                    menuListAllCustomers(input);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    updatePhoneNumber(input);
                    break;
                case 6:
                    updateEmail(input);
                    break;
                case 7:
                    saveUser(input);
                    break;
                case 100:
                    MainMenu.getMainMenu();
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                    break;
            }// End of switch statement
        } while (userChoice != 100);
    }


    private void menuListAllCustomers(Scanner input) {
        List<Customer> listCustomer = repositoryUser.listAllCustomers();

        if (listCustomer.size() > 0) {
            System.out.println("\nList of Customers:");
            for (Customer cust : listCustomer) {
                System.out.println(cust.toString());
            }
        } else {
            System.out.println("\nNo customers registered\n");
            menuOptions(input);
        }
    }
    private void saveUser(Scanner input){
        User user = new User();

        String firstName;
        boolean validateFirstName = false;
        firstName = enterFirstName(input);

        while (!validateFirstName){
            if ((firstName.length() >= 3 && firstName.length() <= 20) && validateName(firstName)){
                validateFirstName = true;
                user.setFirstName(firstName);
            } else {
                System.out.println("Invalid first name. Enter again.");
                firstName = enterFirstName(input);
            }
        }

        String lastName;
        boolean validateLastName = false;
        lastName = enterLastName(input);

        while (!validateLastName){
            if ((lastName.length() >= 3 && lastName.length() <= 20) && validateName(lastName)){
                validateLastName = true;
                user.setLastName(lastName);
            } else {
                System.out.println("Invalid last name. Enter again");
                lastName = enterLastName(input);
            }
        }

        System.out.println("Mobile: ");
        String mobile = input.next();
        if (validatePhoneNumber(mobile)) {
            user.setMobile(mobile);
        } else {
            System.out.println("Not valid. Type again.");
            mobile = checkPhoneNumber(input);
        }
        user.setMobile(mobile);

        System.out.println("Email: ");
        String email = input.next();
        if (validateEmail(email)){
            user.setEmail(email);
        } else {
            System.out.println("Not valid. Type again.");
            email = checkEmail(input);
        }
        user.setEmail(email);

        user.setDateOfRegister(LocalDate.now());
        repositoryUser.saveUser(user);
    }
    public String checkEmail(Scanner input){
        System.out.println("Email: ");
        String email = input.next();
        while (!validateEmail(email)){
            System.out.println("Insert correct email: ");
             email = input.next();
        }
        return email;
    }
    public String checkPhoneNumber(Scanner input){
        System.out.println("Mobile: ");
        String mobile = input.next();
        while (!validatePhoneNumber(mobile)){
            System.out.println("Insert correct phone number: ");
            mobile = input.next();
        }
        return mobile;
    }
    public boolean validateEmail(String email){
        Pattern p = Pattern.compile("^(.+)@(.+)$");
        Matcher m = p.matcher(email);
        return m.find();
    }
    public String enterFirstName(Scanner scanner){
        System.out.println("First name: ");
        return scanner.next();
    }
    public String enterLastName(Scanner scanner){
        System.out.println("Last name: ");
        return scanner.next();
    }
    public boolean validateName(String name) {
        String regex = "^[a-zA-Z\\s]+$";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
    public boolean validatePhoneNumber(String number){
        Pattern p = Pattern.compile("^(\\d{4}[- .]?)\\d{4}$");
        Matcher m = p.matcher(number);
        return m.find();
    }
    public void updatePhoneNumber(Scanner input){
        System.out.println("Insert user ID: ");
        int userId = input.nextInt();
        User user = repositoryUser.findUserById(userId);
        if (user == null){
            System.out.println("User is not registered");
        } else {
            System.out.println("User's current phone number: " + user.getMobile());
            System.out.println("Insert new phone number: ");
            String phoneNumber = input.next();
            repositoryUser.updatePhoneNumber(userId, phoneNumber);
        }
    }
    public void updateEmail(Scanner input){
        System.out.println("Insert user ID: ");
        int userId = input.nextInt();
        User user = repositoryUser.findUserById(userId);
        if (user == null){
            System.out.println("User is not registered");
        } else {
            System.out.println("User's current email: " + user.getEmail());
            System.out.println("Insert new email: ");
            String email = input.next();
            repositoryUser.updateEmail(userId, email);
        }
    }
    public void listTotalUsers(){
        long result = repositoryUser.listTotalUsers();
        System.out.println(result);
    }
}
