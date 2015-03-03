import java.io.*;
import java.util.*;
public class DataSplitter extends Thread{
	
	
	public static void main(String[]args) throws Exception{
		File file = new File("data.html");
 		ArrayList<String>list = new ArrayList<String>();
        try {
 
            Scanner scanner = new Scanner(file);
 			int k = 0;
            while (scanner.hasNextLine()) {
            	++k;
                String line = scanner.nextLine();
                list.add( line + System.getProperty("line.separator") );
                if ( list.size() % 1000 == 0 ){
                	String filename = "data" + k + ".html";
                	FileWriter fw = new FileWriter(filename);
					fw.write( list.toString()  );
					fw.close();
					break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	
} 	
 	