import java.util.*;

class Book {
    private String title;
    private String author;
    private String category;
    private boolean isBorrowed;

    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Category: " + category + ", Borrowed: " + isBorrowed;
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}

public class ELibrarySystem {
    private static List<Book> books = new ArrayList<>();
    private static Map<String, User> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Seed data for 20 books
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction"));
        books.add(new Book("A Brief History of Time", "Stephen Hawking", "Science"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction"));
        books.add(new Book("1984", "George Orwell", "Dystopian"));
        books.add(new Book("Pride and Prejudice", "Jane Austen", "Romance"));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction"));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        books.add(new Book("Moby-Dick", "Herman Melville", "Adventure"));
        books.add(new Book("War and Peace", "Leo Tolstoy", "Historical"));
        books.add(new Book("The Odyssey", "Homer", "Epic Poetry"));
        books.add(new Book("Crime and Punishment", "Fyodor Dostoevsky", "Psychological Fiction"));
        books.add(new Book("The Divine Comedy", "Dante Alighieri", "Poetry"));
        books.add(new Book("Frankenstein", "Mary Shelley", "Horror"));
        books.add(new Book("Brave New World", "Aldous Huxley", "Dystopian"));
        books.add(new Book("The Picture of Dorian Gray", "Oscar Wilde", "Philosophical Fiction"));
        books.add(new Book("Dracula", "Bram Stoker", "Horror"));
        books.add(new Book("The Alchemist", "Paulo Coelho", "Philosophical Fiction"));
        books.add(new Book("The Shining", "Stephen King", "Horror"));
        books.add(new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "Non-fiction"));

        users.put("admin", new User("admin", "password"));

        System.out.println("Welcome to the E-Library System!");

        if (authenticateUser()) {
            int choice;
            do {
                System.out.println("\n1. View Books\n2. Borrow a Book\n3. Return a Book\n4. Exit");
                System.out.print("Enter your choice: ");
                
                // Input validation for menu choice
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid choice, please enter a number.");
                    scanner.next(); // Consume the invalid input
                }
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        viewBooks();
                        break;
                    case 2:
                        borrowBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        if (confirmExit()) {
                            System.out.println("Thank you for using the E-Library System!");
                            return;
                        } else {
                            break;
                        }
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (true);
        } else {
            System.out.println("Authentication failed. Exiting system.");
        }
    }

    private static boolean authenticateUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.authenticate(password)) {
            System.out.println("Authentication successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    private static void viewBooks() {
        System.out.println("\nAvailable Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void borrowBook() {
        System.out.print("Enter the title of the book to borrow: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title);

        if (book != null) {
            if (!book.isBorrowed()) {
                book.borrowBook();
                System.out.println("You have successfully borrowed the book: " + title);
            } else {
                System.out.println("Sorry, this book is already borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void returnBook() {
        System.out.print("Enter the title of the book to return: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title);

        if (book != null) {
            if (book.isBorrowed()) {
                book.returnBook();
                System.out.println("You have successfully returned the book: " + title);
            } else {
                System.out.println("This book was not borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private static Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    private static boolean confirmExit() {
        System.out.print("Are you sure you want to exit? (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }
}
