import java.util.Scanner;
public class GuestList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] guests = new String[10];
        // Creating a do while loop for app to run all teh time
        do {
            //add empty line to beautify the interface
            System.out.println();
            System.out.println("1 - Add reservation");
            System.out.println("2 - Cancel reservation");
            System.out.println("3 - View Guest List");
            System.out.println("4 - Check available spots");
            System.out.println("5 - Search attendee");
            System.out.println("6 - Exit");
            System.out.println();
            System.out.print("Choose action: ");
            //scan for the input
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            // Create if statements to check the user input and perform appropriate action
            if (choice == 1) {
                String input;
                System.out.print("Enter guest name: ");
                // Scan the whole line because the user might or might not enter surname
                input = scanner.nextLine();
                // Create a string array to store the two parts of the name
                String[] nameParts = input.split(" ");
                // Take the first element of the array to assign it as name
                String name = nameParts[0];
                // Set the surname to empty string
                String surname = "";

                // check if the array has more than one element if it has set the second to surname
                if (nameParts.length > 1) {
                    surname = nameParts[1];
                }

                // Construct hte full name used to store in the guests array
                String fullName = name + (surname.isEmpty() ? "" : " " + surname);

                // Use for loop to iterate through the array
                for (int i = 0; i < guests.length; i++) {
                    // Check if the spot is free to store a guest
                    if(guests[i] == null) {
                        // Store a guest
                        guests[i] = capitalize(fullName);
                        break;
                    }
                }
                // Inform the user of successful operation
                System.out.println("Guest has been registered");

            } else if (choice == 2) {
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
                    // Set the chosen user back to null
                    guests[index - 1] = null;
                    // Inform the user of successful operation
                    System.out.println("Guest number " + index + " has been deleted.");
                } else {
                    // Inform the user of invalid input
                    System.out.println("Invalid guest number.");
                }

            } else if (choice == 3) {
                System.out.println("Guest List:");
                // Iterate through loop and print out the guests
                for(String guest : guests) {
                    if (guest != null) {
                        System.out.println(guest);
                    }
                }
            } else if (choice == 4) {
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
            } else if (choice == 5) {
                // CTA
                System.out.print("Type the name of the atendee you would like to find: ");
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


            if (choice == 6) {
                System.out.println("Exiting...");
                // Escape the while loop
                break;
            }
        } while (true);
    }



    // Helper method for capitalizing and normalizing the guests names
    public static String capitalize(String str) {
        // Make sure the string is not empty
        if (str == null || str.isEmpty()) {
            return str;
        }
        // Capitalize the first letter and lowercase the rest
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
