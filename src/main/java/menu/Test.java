package menu;

import model.User;
import persistence.RepositoryUser;

public class Test {
    public static void main(String[] args) {
        MenuCustomer menuCustomer = new MenuCustomer();
        RepositoryUser repo = new RepositoryUser();
        MenuBook bookMenu = new MenuBook();
       boolean value = menuCustomer.validateName("Lyanne");
        System.out.println(value);

        boolean answer = menuCustomer.validatePhoneNumber("233 671");
        System.out.println(answer);

        boolean result = menuCustomer.validateEmail("marimets@gmail.com");
        System.out.println(result);

        User user = repo.findUserById(2);
        System.out.println(user);

        String name = "Neville Goddard";
        boolean result1 = name.matches("(\\b[A-Z]{1}[a-z]+)( )([A-Z]{1}[a-z]+\\b)");
        System.out.println(result1);

        String title = "Power of imagination";
        boolean result2 = title.matches("^[A-Za-z0-9\\s\\-_,\\.;:()]+$");
        System.out.println(result2);
    }
}
