import java.util.Scanner;
public class GuestList {
    static Scanner scanner = new Scanner(System.in);
    static String[] guests = new String[10];
    public static void main(String[] args) {

        // Creating a do while loop for app to run all teh time
        do {
            //add empty line to beautify the interface
            System.out.println();
            DisplayGuests(guests);
            displayMenu();
            //scan for the input
            int choice = getOption();

            System.out.println();

            // Create if statements to check the user input and perform appropriate action
            if (choice == 1) {
                addGuest();
            } else if (choice == 2) {
                removeGuest();
            } else if (choice == 4) {
                checkSpot();
            } else if (choice == 5) {
                searchGuest();
            } else if (choice == 6) {
                System.out.println("Exiting...");
                // Escape the while loop
                break;
            }
        } while (true);
    }



    // Helper method for capitalizing and normalizing the guests names
    static String normalize(String str) {
        // Make sure the string is not empty
        if (str == null || str.isEmpty()) {
            return str;
        }
        // Normalize the name
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    static String fullName(String name) {
        String[] nameParts = name.split(" ");
        // Normalize and construct the full name
        String firstName = nameParts.length > 0 ? normalize(nameParts[0]) : "";
        String surname = nameParts.length > 1 ? normalize(nameParts[1]) : "";

        // Construct the full name used to store in the guests array
        return firstName + (surname.isEmpty() ? "" : " " + surname);
    }

    static void DisplayGuests (String[] array) {
        System.out.println("Guest List:");
        // Iterate through loop and print out the guests
        for(int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                System.out.println((i + 1) + " " + array[i]);
            } else {
                System.out.println("Guest list is empty");
                break;
            }
        }
    }

    static void displayMenu () {
        System.out.println("_____________________");
        System.out.println("1 - Add reservation");
        System.out.println("2 - Cancel reservation");
        System.out.println("4 - Check available spots");
        System.out.println("5 - Search attendee");
        System.out.println("6 - Exit");
        System.out.println();
    }

    static int getOption() {
        int choice = -1;

        while (true) {
            System.out.print("Choose action: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 1 && choice <= 6) {
                    break;
                } else {
                    System.out.println("Invalid option. Please enter a number between 1 and 6");
                }
            } else {
                System.out.println("Invalid option. Please enter a number between 1 and 6");
                scanner.nextLine();

            }

        }
        return choice;
    }

    static void addGuest() {
        System.out.print("Enter guest name: ");
        String input = scanner.nextLine();
        String name = fullName(input); // Get the full normalized name

        // Use for loop to iterate through the array
        for (int i = 0; i < guests.length; i++) {
            // Check if the spot is free to store a guest
            if (guests[i] == null) {
                // Store a guest with the normalized name
                guests[i] = name;
                System.out.println("Guest has been registered");
                return; // Exit the method after adding the guest
            }
        }
        System.out.println("Guest list is full. Cannot add more guests.");
    }

    static void removeGuest() {
        System.out.println("Which reservation do you want to delete");
        for (int i = 0; i < guests.length; i++) {
            // Check if the given element is not null
            if(guests[i] != null) {
                // Print out the guest if it is not null
                System.out.println((i + 1) + " - " + guests[i]);
            }
        }
        // CTA
        System.out.print("Enter number of the reservation: ");
        int index = scanner.nextInt();
        // Perform a check of user input
        if (index > 0 && index <= guests.length && guests[index - 1] != null) {
            // Inform the user of successful operation
            System.out.println(guests[index - 1] + " has been deleted.");
            // Set the chosen user back to null
            guests[index - 1] = null;

            for (int i = index - 1; i < guests.length - 1; i++) {
                guests[i] = guests[i + 1];
            }
            // Set the last element to null after shifting
            guests[guests.length - 1] = null;
        } else {
            // Inform the user of invalid input
            System.out.println("Invalid guest number.");
        }
    }

    static void checkSpot() {
        // Declare and initialize variable for counting available spots
        int availableSpots = 0;
        for (String guest : guests) {
            // Check if the guest is null to accurately count available spots
            if (guest == null) {
                // Increment counting variable
                availableSpots++;
            }
        }
        // Present results to user
        System.out.println("There are " + availableSpots + " spots available");
    }

    static void searchGuest() {
        // CTA
        System.out.print("Type the name of the attendee you would like to find: ");
        // Normalize the input to avoid spelling mismatches
        String attendee = scanner.next().toLowerCase();
        // Create a flag to use for if statement
        boolean found = false;

        for (String guest : guests) {
            // Check if the guest exists and equals the search term
            if (guest != null && guest.toLowerCase().equals(attendee)) {
                // Print out the found guest
                System.out.println("Guest " + guest + " is on the list");
                // Change the flag
                found = true;
                // Escape the loop as the guest is found
                break;
            }
        }
        // Inform user of not finding the searched guest
        if (!found) {
            System.out.println("No such attendee");
        }
    }
}
