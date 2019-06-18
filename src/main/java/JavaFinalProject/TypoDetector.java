package JavaFinalProject;
import java.util.HashMap;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class TypoDetector {

	
	public static String fix(String data, HashMap<String, String> typoCollection) {
		for(String key: typoCollection.keySet()) {
			if(data.contains(key)) {
				data = data.replaceAll(key, typoCollection.get(key));
				ReadFiles.correction.put(key, typoCollection.get(key));
				System.out.println("Detected Typo: "+key+"\t\tCorrected: "+typoCollection.get(key)); //It prints on the console.
			}
		}
		
		
		return data;
	}
	
	
	
	
	
	/**
	 * If the document contains any single typo whether in Korean or English, it automatically detects typos and replace it to correct ones.
	 *
	 * @param document
	 * @param typoCollection
	 * @return text
	 */
	/*
	 public static String run(XWPFDocument document, HashMap<String, String> typoCollection) {
	 
		
		XWPFWordExtractor extractor = new XWPFWordExtractor(document);
		String text = extractor.getText();

		for(String key: typoCollection.keySet()) {
			if(text.contains(key)) {
				text = text.replaceAll(key, typoCollection.get(key));
				ReadFiles.correction.put(key, typoCollection.get(key));
				System.out.println("Detected Typo: "+key+"\t\tCorrected: "+typoCollection.get(key)); //It prints on the console.
			}
		}
		
		return text; //return a corrected text in a docx file.
	}
	*/
}
