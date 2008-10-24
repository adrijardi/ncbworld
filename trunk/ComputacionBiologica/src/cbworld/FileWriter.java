package cbworld;

import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {
	private PrintWriter pw;
	
	public FileWriter(String file) {
		try {
			pw = new PrintWriter(new java.io.FileWriter(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void println(String txt){
		pw.println(txt);
	}
	
	public void println(Object otxt){
		println(otxt.toString());
	}
	
	public void close(){
		pw.close();
	}

}
