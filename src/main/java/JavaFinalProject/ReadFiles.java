package JavaFinalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.nio.file.*;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


/**
 * This ReadFiles class contains every method that reads various formatted files.
 * TypoCollection (CSV formatted files)
 * readDocx (docx formatted file)
 */
public class ReadFiles {
	static HashMap<String,String> correction = new HashMap<String,String>();
	
	
	public static String readFileAsString(String fileName) throws Exception 
	  { 
		boolean check = true;
	    String data = ""; 
		File file = new File(fileName);
		   
		BufferedReader br  =  new BufferedReader(new InputStreamReader(new FileInputStream(file),"euc-kr"));

		String line = null;

		while((line=br.readLine()) != null){
			if(check == true) {
				data = data + line;
				check = false;
			}else {
				data = data + "\r\n" + line;
			}
			
		}
	    //data = new String(Files.readAllBytes(Paths.get(fileName))); 
	    
	    return data; 
	  } 
	
	
	
	
	
	/**
	 * User inputs a path where a docx file exists with arguments and passes it to readDocx as a parameter.
	 * And readDocx method reads the docx file.
	 * 
	 * @param String input(args[0])
	 * @return a docx formatted file
	 */
	/*
	public XWPFDocument readDocx(String input) {
			XWPFDocument document = null;
			FileInputStream fileInputStream = null;
			
			try
			{
				File fileToBeRead = new File(input);
				fileInputStream = new FileInputStream(fileToBeRead);
				document = new XWPFDocument(fileInputStream);
				
				XWPFWordExtractor extractor = new XWPFWordExtractor(document);

			}
			catch (Exception e)
			{
				System.out.println("We had an error while reading the Word Doc");
				System.out.println(e.getMessage());
			}
			finally
			{
				try
				{
					if (document != null)
					{
						document.close();
					}
					if (fileInputStream != null)
					{
						fileInputStream.close();
					}
				}
				catch (Exception ex)
				{
					System.out.println(ex.getMessage());
				}
			}
			return document.getXWPFDocument();
		}
	*/

	
	
	
	/**
	 * readTypoCollection reads two CSV formatted files; EngTypoCollection, KorTypoCollection.
	 * These files are filled with a bunch of sets of 'Typo and Correction'.
	 * @return HashMap TypoCollection
	 */
	public HashMap<String,String> readTypoCollection() {
		String engTypoFile = "EngTypoCollection.csv", korTypoFile = "KorTypoCollection.csv";
		String line ="";
		boolean removeHead1=true, removeHead2=true;
		HashMap<String,String> typoCollection = new HashMap<String,String>();
		//ArrayList<String> stringCol = new ArrayList<String>();
		
		//read a EngTypoCollection and put the sets(typo: correct) into the hashmap
		BufferedReader csvReader1 = null;
		try
		{
			csvReader1 = new BufferedReader(new FileReader(engTypoFile)); //putting eng typo list into the hashmap
			while((line = csvReader1.readLine()) != null)
			{
				if(removeHead1)
				{
					line = csvReader1.readLine();
					removeHead1=false;
				}
				else
				{
					String[] singleLine = line.split(",");
					typoCollection.put(singleLine[0].trim(), singleLine[1].trim());
					//System.out.println(singleLine[0]);
				}
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(csvReader1!=null )
			{
				try
				{
					csvReader1.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
				
		//read a KorTypoCollection and put it into the hashmap
		BufferedReader csvReader2 = null;
		try
		{
			csvReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(korTypoFile), "euc-kr"));
			//csvReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(korTypoFile), "UTF-8"));
			
			while ((line = csvReader2.readLine()) != null)
			{
				if(removeHead2) {
					String[] singleLine = line.split(",");
				    removeHead2 = false;
				}else {
					String[] singleLine = line.split(",");
					typoCollection.put(singleLine[0].trim(), singleLine[1].trim());
				    typoCollection.put(singleLine[0], singleLine[1]);	
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
			return typoCollection;
	}

	
	public static void textWrite(String address, String fixedText) {

		//Creating a correction text file so users can be aware of what mistakes they made.
		try {
			PrintWriter writer = new PrintWriter("CorrectionResult.txt", "UTF-8");
			writer.println("   CORRECTION  (TYPO)");
			
			int i = 1 ;
			for(String key: correction.keySet()) {
				if(correction.get(key).length()>7) {
					writer.println(i+"."+correction.get(key)+"  ("+key+")");
				}else {
					writer.println(i+"."+correction.get(key)+"       ("+key+")");
				}
				i++;
			}
			writer.close();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			PrintWriter author = new PrintWriter(address, "UTF-8");
			author.println(fixedText);
			
			author.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("modified text file created"); //final line message
		
	}
	
	
	
	
	/**
	 * The path where a corrected docx file will be stored is output parameter;args[1].
	 * It writes a corrected docx file in the path that a user set at initial.
	 * @param String output(args[1])
	 * @param String modifiedText
	 */
	/*
	public static void write(String output, String modifiedText) {
		//creating a modified document (hopefully doesn't contain any typo)
		try
		{
			//creating an empty docx file
			XWPFDocument document = new XWPFDocument();
			
			//Write the Document in file system
			FileOutputStream out = new FileOutputStream(new File(output));
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText(modifiedText); //writing a corrected docx file
			
			document.write(out);
			out.close();
			
			//Creating a correction text file so users can be aware of what mistakes they made.
			PrintWriter writer = new PrintWriter("CorrectionResult.txt", "UTF-8");
			writer.println("   CORRECTION  (TYPO)");
			
			int i = 1 ;
			for(String key: correction.keySet()) {
				if(correction.get(key).length()>7) {
					writer.println(i+"."+correction.get(key)+"  ("+key+")");
				}else {
					writer.println(i+"."+correction.get(key)+"       ("+key+")");
				}
				i++;
			}
			document.close();
			writer.close();
		} 
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}
		
		System.out.println("modified docx created"); //final line message
	}
	*/
}

