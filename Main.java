import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // setting up our initial dataset for the user to query and insert into
        String pathname = "C:\\Users\\wolfg\\IdeaProjects\\Assignment3\\src\\amazon-product-data.csv";
        File products = new File(pathname);
        RBTree<String, String> tree = new RBTree<>();

        // insert all of the items into a RBTree
        Scanner scanner = new Scanner(products);
        while (scanner.hasNextLine()) {
            String job = scanner.nextLine();
            String[] elements = job.split(",", 4);
            String id = elements[0];
            String name = elements[1];
            String category = elements[2];
            String price = elements[3];
            tree.put(id, name, category, price);
        }
        scanner.close();

        // Insertions
        Scanner insertions = new Scanner(System.in);
        System.out.println("We are going to insert two extra items into our RBTree!\nPlease insert your product id (choose random letters and numbers):");
        String id = insertions.nextLine();
        System.out.println("Now insert a product name:");
        String name = insertions.nextLine();
        System.out.println("Now insert a category:");
        String category = insertions.nextLine();
        System.out.println("Now insert a price (only integers and decimals [for formatting] please):");
        String price = "$" + insertions.nextLine();

        tree.put(id, name, category, price);

        System.out.println("Now we are going to try and insert an edge case!\nPlease insert your product id (copy and paste this please [f8c32a45e507a177992973cf0d46d20c]):");
        id = insertions.nextLine();
        System.out.println("Now insert a product name:");
        name = insertions.nextLine();
        System.out.println("Now insert a category:");
        category = insertions.nextLine();
        System.out.println("Now insert a price (only integers and decimals [for formatting] please):");
        price = "$" + insertions.nextLine();

        tree.put(id, name, category, price);

        // Queries
        System.out.println("Now that we're done with insertions, let's get some queries going.");
        System.out.println("Input the ID of the item you'd like to query:");
        System.out.println("Some givens: \nf8c32a45e507a177992973cf0d46d20c \n925a65f62da811353fa2478d58827b48 \n75cf1c44eefc3cb4cf7572db41d57196 \nea09e12629b6b7f3eedca8ff90820ae1 \na392a879beef2986b31245c6204a20c2");

        String userInput = insertions.nextLine();
        tree.query(userInput);
        System.out.println("Well done! Now try one more:");
        userInput = insertions.nextLine();
        tree.query(userInput);
        System.out.println("Last one!");
        userInput = insertions.nextLine();
        tree.query(userInput);
        insertions.close();
    }
}
