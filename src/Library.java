import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Library {
    private String libraryName;
    private List<Book> bookList;
    private static int numberOfBooks;
    public static final int MAX_NUMBER_OF_BOOKS = 100;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.bookList = new ArrayList<Book>();
    }
    public void showLibraryInfo() {
        System.out.println("Library name :" + this.libraryName);
        System.out.println("Library numbers of book:" + this.numberOfBooks);
        System.out.println("Library max number of books:" + this.MAX_NUMBER_OF_BOOKS);
    }
    public void addNewBook(){
        if(this.numberOfBooks == MAX_NUMBER_OF_BOOKS) {
            System.out.println("No slot for new book!");
            return;
        }
        System.out.println("2. Add new book:");
        System.out.println("--------------------");
        System.out.print("Enter book 's information: ");
        Scanner sc = new Scanner(System.in);
        System.out.print("(1) Book ID: ");
        int bookId = sc.nextInt();
        sc.nextLine();
        for (Book eachBook : this.bookList) {
            if(eachBook.getBookID() == bookId) {
                System.out.println("Invalid book ID!");
                return;
            }
        }
        System.out.println("(2) Book title:");
        String bookTitle = sc.nextLine();
        System.out.println("Amount :");
        int amountOfBook = sc.nextInt();
        sc.nextLine();
        Book newBook = new Book(bookId,bookTitle,amountOfBook);
        if (this.numberOfBooks + amountOfBook > this.MAX_NUMBER_OF_BOOKS) {
            System.out.println("Books exceeded max number!");
        } else {
            this.numberOfBooks += amountOfBook;
            this.bookList.add(newBook);
        }
    }

    public boolean findBook(int bookID) {
        for (Book eachBook : this.bookList) {
            if (bookID == eachBook.getBookID()) {
                eachBook.showBookInfo();
                return true;
            }
        }
        return false;
    }

    public void borrowBook(int bookID){
        for (Book eachBook : this.bookList) {
            if(bookID == eachBook.getBookID()) {
                eachBook.showBookInfo();
                if (eachBook.getAvailable() == 0) {
                    System.out.println("There is no book available of this title!");
                    return;
                }
                else  {
                    eachBook.setAvailable(eachBook.getAvailable()-1);
                    System.out.println("The book is borrowed successfully!");
                    eachBook.showBookInfo();
                    return;
                }
            }
        }
        System.out.println("Book not found!");
        return;
    }
    public void findBook(String bookTitle) {
        List<Book> foundBook = new ArrayList<Book>();
        for (Book eachBook : this.bookList) {
            String eachTitle = eachBook.getBookTitle().toLowerCase();
            bookTitle = bookTitle.toLowerCase();
            if(eachTitle.contains(bookTitle)) {
                foundBook.add(eachBook);
            }
        }
        int foundNumber = foundBook.size() ;
        System.out.println("Found "+foundNumber+" book(s):");
        for(int i = 0; i< foundNumber; i++) {
            System.out.println(i+1+". "+foundBook.get(i).getBookTitle());
        }
    }
    public Book getALuckyBook() {
        int ran = getRanIndex();
        while (this.bookList.get(ran).getAvailable() > 0) {
            ran = getRanIndex();
        }
        return this.bookList.get(ran);
    }
    public int getRanIndex() {
        return (int) Math.random()*Library.numberOfBooks;
    }
    public void returnBook(int bookID){
        for (Book eachBook : this.bookList) {
            if(bookID == eachBook.getBookID()) {
                eachBook.setAvailable(eachBook.getAvailable()+1);
                eachBook.showBookInfo();
                System.out.println("The book is returned successfully!");
                return;
                }
        }
        System.out.println("Book not found!");
        return;
    }
}
