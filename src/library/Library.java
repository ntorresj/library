/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import static java.lang.Math.ceil;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import library.classes.*;
import library.helpers.NumberFormat;
import library.mysql.MySQL;

/**
 *
 * @author nicolastorres
 */
public class Library {

    MySQL mysql;
    HashMap registeredBooks;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Library library = new Library();
        library.mysql = new MySQL();
        library.registeredBooks = library.getBooks();
        library.loadMenu();
    }

    private HashMap getBooks() {
        List records = this.mysql.getValues("SELECT * FROM books");
        this.mysql.close();

        return this.createBooks(records);
    }

    private HashMap createBooks(List records) {
        HashMap<Integer, Book> books = new HashMap<>();

        records.forEach(record -> {
            HashMap book = (HashMap) record;
            switch (Integer.parseInt((String) book.get("type"))) {
                case 0:
                    int hasDisc = book.get("has_disc") == null ? 0
                            : Integer.parseInt((String) book.get("has_disc"));
                    books.put(Integer.parseInt((String) book.get("id")),
                            new Technical(
                                    Integer.parseInt((String) book.get("type")),
                                    (String) book.get("title"),
                                    Double.parseDouble((String) book.get("price")),
                                    Integer.parseInt((String) book.get("pages")),
                                    hasDisc
                            )
                    );
                    break;
                case 1:
                    books.put(Integer.parseInt((String) book.get("id")),
                            new Children(
                                    Integer.parseInt((String) book.get("type")),
                                    (String) book.get("title"),
                                    Double.parseDouble((String) book.get("price")),
                                    Integer.parseInt((String) book.get("pages")),
                                    Integer.parseInt((String) book.get("min_age")),
                                    Integer.parseInt((String) book.get("max_age"))
                            )
                    );
                    break;
            }
        });

        return books;
    }

    private String listTitles() {
        StringBuilder booksAvailables = new StringBuilder();
        this.registeredBooks.forEach((key, book) -> {
            booksAvailables
                    .append(key)
                    .append(" - ")
                    .append(((Book) book).getTitle())
                    .append("\n");
        });

        return booksAvailables.toString().trim();
    }

    private String booksAvailables() {
        StringBuilder booksAvailables = new StringBuilder();

        this.registeredBooks.values().forEach((book) -> {
            booksAvailables.append(((Book) book).showDetails()).append("\n");
        });

        return booksAvailables.toString().trim();
    }

    private String booksAvailables(int minPages) {
        StringBuilder booksAvailables = new StringBuilder();

        this.registeredBooks.values().forEach((book) -> {
            if (minPages < ((Book) book).getPages()) {
                booksAvailables.append(((Book) book).showDetails()).append("\n");
            }
        });

        return booksAvailables.toString().trim();
    }

    private void loadMenu() {
        boolean menu = true;
        do {
            String option = JOptionPane.showInputDialog(null,
                    "La librería de papel\n\n"
                    + " 1) Listar Libros\n"
                    + " 2) Listar Libros > ???\n"
                    + " 3) Comprar Libro\n\n"
                    + " 99) Salir\n"
            );

            if (option == null) {
                option = "99";
            }

            switch (Integer.parseInt(option)) {
                case 1:
                    JOptionPane.showMessageDialog(null,
                            this.booksAvailables());
                    break;
                case 2:
                    String pages = JOptionPane.showInputDialog("Ingrese mínimo de páginas");
                    JOptionPane.showMessageDialog(null,
                            this.booksAvailables(Integer.parseInt(pages)));
                    break;
                case 3:
                    String choice = JOptionPane.showInputDialog(this.listTitles());
                    Book book = ((Book) this.registeredBooks.get(Integer.parseInt(choice)));
                    if (book == null) {
                        JOptionPane.showMessageDialog(null, "Error");
                        break;
                    }
                    this.sellBook(book);
                    break;
                case 99:
                    menu = false;
                    break;
            }
        } while (menu);
    }

    private void sellBook(Book book) {
        boolean menu = true;
        int quantity = 0;

        do {
            String lot = JOptionPane.showInputDialog(null, "¿Cuántos libros?", 1);

            try {
                if (lot == null || Integer.parseInt(lot) < 1) {
                    JOptionPane.showMessageDialog(null, "Opción inválida");
                }

                if (Integer.parseInt(lot) > 0) {
                    quantity = Integer.parseInt(lot);
                    menu = false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        } while (menu);

        Store store = new Store(book);

        JOptionPane.showMessageDialog(null,
                "Subtotal: "
                + NumberFormat.currency(store.getSubtotal(quantity)) + "\n"
                + "Descuento: " + (store.discount()) + " %\n\n"
                + "Total: " + NumberFormat.currency(ceil(store.getTotal())) + "\n"
        );
    }
}
