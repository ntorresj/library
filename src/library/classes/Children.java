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
public class Children extends Book {

    public Children(
            int type, 
            String title, 
            double price, 
            int pages,
            int minAge,
            int maxAge
    ) {
        super(BookType.values()[type].toString(), title, price, pages);
        
        this.setMinAge(minAge);
        this.setMaxAge(maxAge);
    }

    @Override
    public String clasificationBook() {
        return this.getType() + ": " + this.getTitle() + ", " +
                NumberFormat.currency(this.getPrice()) +
                " para menores entre " + this.getMinAge() + " y " + this.getMaxAge();
    }

    @Override
    public String showDetails() {
        return "Título: " + this.getTitle() +
                "\nTipo: " + this.getType() +
                "\nPrecio: " + this.getPrice() +
                "\nPáginas: " + this.getPages() +
                "\nEdad entre: " + this.getMinAge()+ " y " + 
                    this.getMaxAge() + " años\n";
    }
    
}
