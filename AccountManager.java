import java.util.Scanner;

class AccountManager {
    
    private String registeredUsername;
    private String registeredPassword;
    private String registeredPhone;
    private String firstName;
    private String lastName;
    
    // Username validation method
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }
    
    // Password validation method
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) hasCapital = true;
            else if (Character.isDigit(c)) hasNumber = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        
        return hasCapital && hasNumber && hasSpecial;
    }
    
    // Phone validation method
    public boolean checkCellPhoneNumber(String phone) {
        if (phone == null) return false;
        return phone.matches("\\+27[0-9]{9}");
    }
    
    // Registration methods
    public String registerUsername(String username) {
        if (checkUserName(username)) {
            registeredUsername = username;
            return "Username successfully captured.";
        } else {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
    }
    
    public String registerPassword(String password) {
        if (checkPasswordComplexity(password)) {
            registeredPassword = password;
            return "Password successfully captured.";
        } else {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
    }
    
    public String registerCellPhoneNumber(String phone) {
        if (checkCellPhoneNumber(phone)) {
            registeredPhone = phone;
            return "Cell phone number successfully added.";
        } else {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
    }
    
    public void setUserDetails(String first, String last) {
        this.firstName = first;
        this.lastName = last;
    }
    
    // Login methods
    public boolean loginUser(String username, String password) {
        return registeredUsername != null && 
               registeredUsername.equals(username) &&
               registeredPassword != null &&
               registeredPassword.equals(password);
    }
    
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        
        System.out.println("=== Registration ===\n");
        
        // Get username
        System.out.print("Enter username (must contain _ and be ≤5 chars): ");
        String username = scanner.nextLine();
        System.out.println(accountManager.registerUsername(username));
        
        // Get password
        System.out.print("\nEnter password (8+ chars, capital, number, special): ");
        String password = scanner.nextLine();
        System.out.println(accountManager.registerPassword(password));
        
        // Get phone number
        System.out.print("\nEnter SA cell number (+27XXXXXXXXX): ");
        String phone = scanner.nextLine();
        System.out.println(accountManager.registerCellPhoneNumber(phone));
        
        // Get personal details
        System.out.print("\nEnter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        accountManager.setUserDetails(firstName, lastName);
        
        System.out.println("\n=== Registration Complete ===\n");
        
        // Login
        System.out.println("=== Login ===\n");
        System.out.print("Enter username: ");
        String loginUser = scanner.nextLine();
        System.out.print("Enter password: ");
        String loginPass = scanner.nextLine();
        
        System.out.println(accountManager.returnLoginStatus(loginUser, loginPass));
        
        scanner.close();
    }
}