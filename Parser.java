import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/*
    const STORY = 0;
    const HASPIC = 1;
    const PIC = 2;
    const END = 3
    const NUMOPT = 4;
    const OPT1 = 5;
    const OPT2 = 6;
    const OPT3 = 7; 
 */

public class Parser {
	static String fileName = "C:\\Users\\User\\eclipse-workspace\\Adventure Parser\\src\\rawStory.txt";
	static File file;
	static BufferedReader reader;
	static Page[] book = new Page[118];
	static String[] formattedBook = new String[118];
	
	public static void main(String[] args) { 
		String story = read(fileName);
		String[] parts = story.split("[#]");
		parseParts(parts);
		
		for(int i=1; i<parts.length; i++) {
			formattedBook[i] = book[i].toString();
		}
		
		try {
			writeToFile(formattedBook);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/*
	 * All pages are broken into arrays of lengths 2-8. Page objects are
	 * created and stored in the Book array. 
	 */
	private static void parseParts(String[] parts) {
		String[] p;
		
		for(int i=1; i<parts.length; i++) {
			p = parts[i].split("[@]");
			
			//(int pageNum, String pic, String story, int end, String op1, 
			//     int op1GoTo, String op2, int op2GoTo, String op3, int op3GoTo)
			//
			switch(p.length) {
				case 2: // page, pic
					book[i] = new Page(p[0], p[1], null, 0, 1, null, null, null, null, null, null);
					break;
				case 3: // page, story, goto/end
					if(p[2].contains("The End")) 
						book[i] = new Page(p[0], null, p[1], 1, 0, null, null, null, null, null, null);
					
					else 
						book[i] = new Page(p[0], null, p[1], 0, 1, null, p[2], null, null, null, null);
					break;
					
				case 4: // page, pic, story, goto/end
					if(p[3].contains("The End")) 
						book[i] = new Page(p[0], p[1], p[2], 1, 0, null, null, null, null, null, null);
					else
						book[i] = new Page(p[0], p[1], p[2], 0, 1, null, p[3], null, null, null, null);
					break;
					
				case 5: // page, story, end, don't like, goto 
					book[i] = new Page(p[0], null, p[1], 2, 1, null, null, p[3], p[4], null, null);
					break;					
					
				case 6: // page, story, opt1, goto1, opt2, goto2
					book[i] = new Page(p[0], null, p[1], 0, 2, p[2], p[3], p[4], p[5], null, null);
					break;
					
				case 7: // page, pic, story, opt1, goto1, opt2, goto2
					book[i] = new Page(p[0], p[1], p[2], 0, 2, p[3], p[4], p[5], p[6], null, null);
					break;
					
				case 8: //page, story, opt1, goto1, opt2, goto2, opt3, goto3
					book[i] = new Page(p[0], null, p[1], 0, 3, p[2], p[3], p[4], p[5], p[6], p[7]);
					break;
			}
		}
	} 

	private static String read(String filePath){
	    StringBuilder contentBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        String sCurrentLine;
	        while ((sCurrentLine = br.readLine()) != null) {
	            contentBuilder.append(sCurrentLine).append("\n");
	        }
	    } 
	    catch (IOException e){
	        e.printStackTrace();
	    }
	    return contentBuilder.toString();
	}
	
	private static void writeToFile(String[] output) throws IOException {
		FileWriter fileWriter = new FileWriter("C:\\Users\\User\\Desktop\\PORTFOLIO\\chooseadventure\\src\\output.txt");
		
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print("export const pages = \r\n" + 
				"[");
		printWriter.print(finalFormat(output));
		
		printWriter.print("]");
		
		printWriter.close();
	}
		
	
	private static String finalFormat(String[] output) {
		String s = "";
		for(int i=0; i<output.length; i++) {
			s += output[i] + ",\n";
		}
		return s;
	}
}
