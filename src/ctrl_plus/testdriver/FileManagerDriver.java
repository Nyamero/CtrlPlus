package ctrl_plus.testdriver;

import java.io.IOException;

import ctrl_plus.general.manager.FileManager;

public class FileManagerDriver {

	public static void main(String[] args) {

		FileManager fm = new FileManager();
		
		try {
			fm.createFile("example.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
