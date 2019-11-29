/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.classes;

import library.interfaces.ICalculable;

/**
 *
 * @author nicolastorres
 */
public class Store implements ICalculable{
    private final Book book;
    private double subtotal;
    private double discount;
    
    public Store(Book book){
        this.book = book;
    }

    @Override
    public double getSubtotal(int quantity) {
        this.subtotal = this.book.getPrice() * quantity;
        
        return this.subtotal;
    }

    @Override
    public double discount() {
        switch (book.getType()) {
            case "TECHNICAL":
                this.discount = Store.TECHNICAL_BOOK_DISCOUNT;
                break;
            case "CHILDREN":
                this.discount = Store.CHILDRENS_BOOK_DISCOUNT;
                break;
        }
        
        return this.discount;
    }

    @Override
    public double getTotal() {
        return this.subtotal * ((100 - this.discount) / 100) * Store.IVA;
    }
    
}
