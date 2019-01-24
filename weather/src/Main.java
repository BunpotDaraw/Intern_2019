
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Main {

    public static int PRETTY_PRINT_INDENT_FACTOR = 4;
    
    static String jsonPrettyPrintString;

    public static void main(String[] args) {
    	
    	 Scanner sc = new Scanner(System.in);
         Scanner rd = null;
        
         String XML_STRING = "";
    	
         
    	System.out.println("Enter a XML file directory to convert that file to JSON (EX:xml_in\\test.xml)");
    	String fd = null;
    	fd = sc.next();//xml_in\test.xml
    	File f = new File(fd);
    	// read file
    	try {
			rd = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	while(rd.hasNextLine())
    	{
    		XML_STRING += rd.nextLine();
    	}
    	
    	rd.close();
    	// convert
       try {
            JSONObject xmlJSONObj = XML.toJSONObject(XML_STRING);
             jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            System.out.println(jsonPrettyPrintString);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
     
  //write file
       System.out.println("Enter Y if you want to create a JSON file");
        char ans = sc.next().charAt(0);
		ans = Character.toLowerCase(ans);
		
       if(ans == 'y') 
       {
    	   System.out.println("Enter a file name and directory (EX:json_out\\test.json)");
    	   fd = sc.next();//json_out\test.json
    	   File outFile = new File(fd);
    	   FileOutputStream outFileStream = null;
		try {
			outFileStream = new FileOutputStream(outFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   PrintWriter outStream = new PrintWriter(outFileStream);
    	
    	   outStream.println(jsonPrettyPrintString);
    	   outStream.close();
    	   System.out.println("File already create");
       }
       
       
       
    }
    
}