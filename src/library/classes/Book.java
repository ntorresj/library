/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.classes;

/**
 *
 * @author nicolastorres
 */
public abstract class Book {

    private final String type;
    private final String title;
    private final double price;
    private final int pages;
    private int minAge;
    private int maxAge;
    private boolean hasCD = false;
    private boolean hasDVD = false;

    public Book(String type, String title, double price, int pages) {
        this.type = type;
        this.title = title;
        this.price = price;
        this.pages = pages;
    }

    public abstract String clasificationBook();
    
    public abstract String showDetails();

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public double getPrice() {
        return this.price;
    }

    public int getPages() {
        return this.pages;
    }

    public int getMinAge() {
        return this.minAge;
    }

    public int getMaxAge() {
        return this.maxAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
    
    public boolean getHasCD() {
        return this.hasCD;
    }

    public boolean getHasDVD() {
        return this.hasDVD;
    }

    public void setHasCD(boolean hasCD) {
        this.hasCD = hasCD;
    }

    public void setHasDVD(boolean hasDVD) {
        this.hasDVD = hasDVD;
    }
}
