import java.util.ArrayList;
import java.util.Scanner;

public class Project1 {

}

// Library Management System
class Booksinlib {
    private String title;
    private String Author;
    private String ISBN;
    private boolean isavailable;

    Booksinlib(String Title, String Author, String ISBN) {
        this.title = Title;
        this.Author = Author;
        this.ISBN = ISBN;
        this.isavailable = true;
    }

    public void setTitle(String t) {
        this.title = t;
    }

    public void setAuthor(String a) {
        this.Author = a;
    }

    public void setISBN(String IS) {
        this.ISBN = IS;
    }

    void setAvailablity(boolean availbook) {
        this.isavailable = availbook;
    }

    boolean getAvailablity() {
        return isavailable;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getISBN() {
        return ISBN;
    }

    void dispbookDetails() {
        System.out.println(
                "Book Title: " + getTitle() + " " + "Book Author: " + getAuthor() + " " + "ISBN No:" + " " + getISBN());
    }

    @Override
    public String toString() {
        return title + " " + Author + " " + ISBN;
    }
}

class Persons {
    int pid;
    String pname;

    Persons(int pid, String uname) {
        this.pid = pid;
        this.pname = uname;
    }
}

class Admin extends Persons {
    ArrayList<Booksinlib> adminbook;

    Admin(int aid, String aname) {
        super(aid, aname);
        this.adminbook = new ArrayList<>();
    }

    void addBook(Booksinlib bookadd) {
        adminbook.add(bookadd);
    }

    void removeBook(int index) {
        if (index >= 0 && index < adminbook.size()) {
            adminbook.remove(index);
        } else {
            System.out.println("Enter Valid Index");
        }
    }

    void viewbook() {
        for (Booksinlib booksinlib : adminbook) {
            System.out.print(" " + booksinlib);
        }
    }
}

class Usererss extends Persons {

    Admin adm;

    Usererss(int uid, String uname, Admin ad) {
        super(uid, uname);
        this.adm = ad;
    }

    void Search(String title) {
        boolean found = false;
        for (Booksinlib getbook : adm.adminbook) {
            if (getbook.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Book Details");
                getbook.dispbookDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Not In Library");
        }
    }

    void Borrow(String title) {
        for (Booksinlib avail : adm.adminbook) {
            if (avail.getTitle().equalsIgnoreCase(title)) {
                if (avail.getAvailablity()) {
                    avail.setAvailablity(false);
                    System.out.println("Book Name : " + avail.getTitle());
                    System.out.println("Book Borrowed");
                } else {
                    System.out.println("Book Is Currently Unavailable");
                }
            }
        }
    }

    void Returnbook(String title) {
        for (Booksinlib ReturnBook : adm.adminbook) {
            if (ReturnBook.getTitle().equalsIgnoreCase(title)) {
                if (!ReturnBook.getAvailablity()) {
                    ReturnBook.setAvailablity(true);
                    System.out.println("Thanks For Returning The Book");
                } else {
                    System.out.println("Book Is Already In Library");
                }
            }
        }
    }
}

class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Admin ad1 = new Admin(1, "Girinder");
        ad1.addBook(new Booksinlib("Rich Dad Poor Dad", "Robert Kiyosaki", "12345"));
        ad1.addBook(new Booksinlib("Think and Grow Rich", "Napoleon Hill", "67890"));
        Usererss user1 = new Usererss(1, "Parth", ad1);

        System.out.println("Enter Your Role User Or Admin");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("Admin")) {
            while (true) {
                System.out.println("1 . Add Book");
                System.out.println("2 . Remove Book");
                System.out.println("3 . View Book");
                System.out.println("4 . Exit ");
                int select = sc.nextInt();
                sc.nextLine();

                switch (select) {
                    case 1:
                        System.out.println("Enter Book Title");
                        String bt = sc.nextLine();
                        System.out.println("Enter Book Author");
                        String booka = sc.nextLine();
                        System.out.println("Enter Book ISBN");
                        String isbn = sc.nextLine();
                        System.out.println("Book Added");
                        ad1.addBook(new Booksinlib(bt, booka, isbn));
                        break;
                    case 2:
                        for (Booksinlib string : ad1.adminbook) {
                            string.dispbookDetails();
                        }
                        System.out.println("Enter Book Index");
                        int bookrem = sc.nextInt();
                        sc.nextLine();
                        ad1.removeBook(bookrem);
                        System.out.println("Book Removed");
                        break;
                    case 3:
                        System.out.println("Book Details");
                        for (Booksinlib string : ad1.adminbook) {
                            string.dispbookDetails();
                        }
                        break;
                    case 4:
                        System.out.println("Exiting");
                        return;
                    default:
                        System.out.println("Invalid Option. Try Again.");
                }
            }
        } else if (choice.equalsIgnoreCase("User")) {
            while (true) {
                System.out.println("1 . Search Book");
                System.out.println("2 . Borrow Book");
                System.out.println("3 . Return Book");
                System.out.println("4 . Exit ");
                int select = sc.nextInt();
                sc.nextLine();

                switch (select) {
                    case 1:
                        System.out.println("Book Details");
                        for (Booksinlib string : ad1.adminbook) {
                            string.dispbookDetails();
                        }
                        break;
                    case 2:
                        System.out.println("Enter Book Title");
                        String borrowtit = sc.nextLine();
                        user1.Borrow(borrowtit);
                        break;
                    case 3:
                        System.out.println("Enter book Title");
                        String retbook = sc.nextLine();
                        user1.Returnbook(retbook);
                        break;
                    case 4:
                        System.out.println("Exiting");
                        return;
                    default:
                        System.out.println("Invalid Option. Try Again.");
                }
            }
        } else {
            System.out.println("Only Admin and User Are Allowed");
        }
        sc.close();
    }
}
