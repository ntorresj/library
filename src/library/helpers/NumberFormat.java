/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.helpers;

import java.text.DecimalFormat;

/**
 *
 * @author nicolastorres
 */
public class NumberFormat {

    public static String currency(double number) {
        String pattern = "$###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(number);
    }
}
