import java.util.*;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

       

        ReadFile r = new ReadFile();
        r.openFile();
        r.readFile();
        r.closeFile();
       
      
        PayoffMatrix m = new PayoffMatrix(Integer.parseInt(r.a.get(0).trim()), Integer.parseInt(r.a.get(1).trim()));
        m.parseMatrix(r.a);
        System.out.println(m.toString());
        // System.out.println(m.matrix.length);
        // System.out.println(m.matrix[0].length);
       

        //let's try to open PDF file
        // file = new File("/Users/pankaj/java.pdf");
        // if(file.exists()) desktop.open(file);
    }
}