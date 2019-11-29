/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.classes;

import library.helpers.BookType;
import library.helpers.NumberFormat;

/**
 *
 * @author nicolastorres
 */
public class Technical extends Book {

    public Technical(
            int type,
            String title,
            double price,
            int pages
    ) {
        super(BookType.values()[type].toString(), title, price, pages);
    }

    public Technical(
            int type,
            String title,
            double price,
            int pages,
            int disc
    ) {
        super(BookType.values()[type].toString(), title, price, pages);
        this.setDisc(disc);
    }

    private void setDisc(int disc) {
        switch (disc) {
            case 0:
                this.setHasCD(true);
                break;
            case 1:
                this.setHasDVD(true);
                break;
        }
    }

    @Override
    public String clasificationBook() {
        return this.getType() + ": " + this.getTitle() + ", " +
                NumberFormat.currency(this.getPrice());
    }
    
    @Override
    public String showDetails() {
        String hasDisc = "\nNo contiende disco";
        
        if (this.getHasCD()) {
            hasDisc = "\nContiene CD";
        }
        
        if (this.getHasDVD()) {
            hasDisc = "\nContiene DVD";
        }
        
        return "Título: " + this.getTitle() +
                "\nTipo: " + this.getType() +
                "\nPrecio: " + this.getPrice() +
                "\nPáginas: " + this.getPages() +
                "\nTítulo: " + this.getTitle() +
                hasDisc + "\n";
    }
}
