import java.util.*;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

       
        File inFile = null;
        if (0 < args.length) {
           inFile = new File(args[0]);
           System.out.println(args[0]);
        } else {
           System.err.println("Invalid arguments count:" + args.length);
           return;
        }
        ReadFile r = new ReadFile();
        r.openFile(inFile);
        r.readFile();
        r.closeFile();
       
      
        PayoffMatrix m = new PayoffMatrix(Integer.parseInt(r.a.get(0).trim()), Integer.parseInt(r.a.get(1).trim()));
       
        m.parseMatrix(r.a);

        Agent a = new Agent(m.matrix);
        System.out.println(a.toString());

        a.runOptions();
        
        // int arr[] = a.findNash();
        // System.out.printf("%d %d\n", arr[0], arr[1]);
       
    }
}