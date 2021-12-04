package menu;

import model.*;
import persistence.RepositoryAuthor;
import persistence.RepositoryBook;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuBook {
    RepositoryBook repoBook = new RepositoryBook();
    RepositoryAuthor repoAuthor = new RepositoryAuthor();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: List all books available");
        System.out.println("2: Show all book loans");
        System.out.println("3: List books by category");
        System.out.println("4: List books by author ID");
        //System.out.println("5: Add a book");
        //System.out.println("6: Add an author");
        //System.out.println("7: Add a category");
        System.out.println("8: Add a book loan");
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
                    listAllBooks();
                    break;
                case 2:
                    showBookLoans();
                    break;
                case 3:
                    listBooksByCategory(input);
                    break;
                case 4:
                    listBooksByAuthorId(input);
                    break;
                case 5:
                    //addBook(input);
                    break;
                case 6:
                    //addAuthor(input);
                    break;
                case 7:
                   // addCategory(input);
                    break;
                case 100:
                    MainMenu.getMainMenu();
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                    break;
            }
        } while (userChoice != 100);
    }

   /* private void addBook(Scanner input) {
        Book book = new Book();
        System.out.println("Author ID: ");
        int authorId = input.nextInt();
        Author author = repoAuthor.findAuthorById(authorId);
        if (author == null){
            System.out.println("Author not registered");
        } else {
            book.setAuthor(author);
        }
        System.out.println("Title: ");
        String title = input.next();
        if (validateTitle(title)){
            book.setName(title) ;
            while (!validateTitle(title)){
            System.out.println("Insert correct title.");
            title = input.next();
            validateTitle(title);
            }
        }
        repoBook.addABook(book);
    }
    */
   /* private void addCategory(Scanner input){
        Category category = new Category();
        System.out.println("Category: ");
        String name = input.next();
        Category category1 = repoBook.findCategoryByName(name);
        if (category1 == null){
            category.setName(name);
        } else {
            System.out.println("Category already exists");
        }
        repoBook.addACategory(category);
        }
        */
  /*  private void addAuthor(Scanner input) {
        Author author = new Author();
        System.out.println("First name: ");
        String firstName = input.next();
        if (validateName(firstName)) {
            author.setFirstName(firstName);
            while (!validateName(firstName)) {
                System.out.println("Insert correct name: ");
                firstName = input.next();
                validateName(firstName);
            }
        }
        System.out.println("Last name: ");
        String lastName = input.next();
        if (validateName(lastName)) {
            author.setLastName(lastName);
            while (!validateName(lastName)) {
                System.out.println("Insert correct name: ");
                lastName = input.next();
                validateName(lastName);
            }
        }
        repoAuthor.addAuthor(author);
    }
    */
    public void listAllBooks(){
        List<Book> allBooks = repoBook.listAllBooks();
        for (Book b : allBooks) {
            System.out.println(b.toString());
        }
    }
    public void showBookLoans(){
        List<BookLoan> allBookLoans = repoBook.listBookLoans();
        for (BookLoan b : allBookLoans){
            System.out.println(b.toString());
        }
    }
    public void listBooksByAuthorId(Scanner input){
        System.out.println("Author ID: ");
        int authorId = input.nextInt();
        List<Book> resultList = repoBook.listBooksByAuthorId(authorId);
        for (Book b : resultList){
            System.out.println(b.toString());
        }
    }
    public void listBooksByCategory(Scanner input){
        System.out.println("Category: ");
        String category = input.next();
        List<Book> resultList = repoBook.listBooksByCategory(category);
        for (Book b : resultList){
            System.out.println(b.toString());
        }
    }
    public boolean validateName(String name) {
        String regex = "^[a-zA-Z\\s]+$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
    public boolean validateTitle(String title){
        String regex = "^[A-Za-z0-9\\s\\-_,\\.;:()]+$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(title);
        return matcher.find();
    }
}