package JavaFinalProject;

public class ThreadExam {
	public static void main(String args[]) {
		MainRead t1 = new MainRead(args);
		
		t1.start();
		
		//System.out.println("Main End");
	}
}
