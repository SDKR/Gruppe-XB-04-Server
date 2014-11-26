package keyKeeper;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class KeyGetter {
	
	//Arraylist to hold the entire encryption file
	private ArrayList<String> completeFile = new ArrayList<String>();

	public void getConfigInfo()
	{
		//Insert the filepath here // skal rettes til !
		// "C:\\Users\\Emil Laptop\\Documents\\GitHub\\GitHub 2\\Gruppe-XB-04-Server\\KeyKeeper.txt"

		String filePath = "KeyKeeperAsger.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath),
                    Charset.defaultCharset());
            	
            for (String line : lines) {
            	if (line.startsWith("//comment//") || line.isEmpty() )
            	{
            	//ignore line 
            	}
            	else if(line.startsWith("[NP]"))
            	{
            	completeFile.add("");
            	}
            	else {
            	//Writes to arraylist
            	completeFile.add(line);
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //printer m�ngden af linjer i txt filen og i arrayet, kan udkommenteres senere
//        int size = completeFile.size();
//        System.out.println("\n"+"Antal linjer sendt til array: "+size );
//        System.out.println("Antal linjer i txt filen: "+linecounter);
//        
        //Prints entire arraylist - Not nedded later
//        System.out.println("\n"+"Printer indholdet i arrayet:"+"\n");
//        Iterator printlist = completeFile.iterator();
//        while (printlist.hasNext()) {
//        System.out.println(printlist.next());
//        }
	}

	public ArrayList<String> getCompleteFile() {
		return completeFile;
	}
	
}