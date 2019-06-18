package JavaFinalProject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/*Initial path are:
args[0](String input): "C:\git\input.docx"	<-This is the docx file that this program will read and correct.
args[1](String output):  "C:\git\output.docx" <-This will be the path and docx file that is corrected through this program.
*/

/**
 * The Main method.
 * This program implements an application that simply detects typos in a docx file and 
 * correct them and return a corrected docx file that may not contain typos.
 * 
 * When you write an important document in Microsoft Office Word, Korean people may realize Word doesn't provide Korean language spelling check and correction.
 * This is the why I came up with the program that helps you in writing a document in Korean.
 * The differentiated advantages of the program is it provides Korean typo correction and Korean slang correction.
 * 
 * + Added a few English typos that Microsoft Office Word doesn't correct automatically (It will help you in writing a mixed language(English and Korean) document.
 * 
 * @author Jeamin Rhee
 * @version 1.0
 * @since 2019-June-1st
 */
public class MainRead extends Thread{
	public String[] args;
	
	/*
	public static void main(String args[]) {

	}
	*/

	public MainRead(String args[]) {
		this.args = args;
	}
	
	@Override
	public void run() {
		//super.run();
		ReadFiles reader = new ReadFiles();
		
		try {
			if( (args.length) < 2) {
				throw new NotEnoughArgumentException("No CLI argument Exception! Please put a file path.");
			}
			
		} catch(NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		//Read a docx file that may need correction.
		XWPFDocument document = reader.readDocx(args[0]);			
		
		HashMap<String,String> typoCollection = reader.readTypoCollection();
		
		//Pass an OG docx file and typoCollection and get modified text.
		//All the detecting and modifying processes are dealt in TypoDetector.
		String modifiedText = TypoDetector.run(document, typoCollection);
		
		//Write a corrected docx file.
		ReadFiles.write(args[1], modifiedText);
		//reader.write(args[1], modifiedText);
		
	}		
	
}
