package JavaFinalProject;

/**
 * It is the main method of this program and it runs one thread.
 * There's only one thread 't1' because this program doesn't need many thread.
 * Passes String args[] that user put as addresses of input and output docx file.
 * As soon as the program reaches to "t1.start();", t1 goes to MainRead and be executed in run method which over-riden udner thread class.
 * 
 * @author 21600
 *
 */
public class ThreadExam {
	public static void main(String args[]) {
		MainRead t1 = new MainRead(args);
		
		t1.start();
		
		//System.out.println("Main End");
	}
}
