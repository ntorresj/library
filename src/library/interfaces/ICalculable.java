/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.interfaces;

/**
 *
 * @author nicolastorres
 */
public interface ICalculable {
    public final double IVA = 1.19;
    public final double CHILDRENS_BOOK_DISCOUNT = 10;
    public final double TECHNICAL_BOOK_DISCOUNT = 5;
    
    public double getSubtotal(int quantity);
    public double discount();
    public double getTotal();
}
