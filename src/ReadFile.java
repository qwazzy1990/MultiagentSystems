
import java.io.*;
import java.util.*;

public class ReadFile {
    private BufferedReader x;
    public ArrayList<String> a = new ArrayList<String>();

    public void openFile(File inFile) {

        try {
            x = new BufferedReader(new FileReader(inFile));
        } catch (Exception e) {
            System.out.println("Could not find the file");
            return;
        }

    }

    public void readFile() {
        try {
            String aa = x.readLine();
            while (aa != null) {
                int index = aa.indexOf(" ");
                aa = aa.substring(index + 1, aa.length());
                a.add(aa);

                aa = x.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFile() {
        try {
            x.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}