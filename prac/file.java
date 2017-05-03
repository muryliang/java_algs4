import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class file {
	public static void main(String[] args) {
		File f = new File("./list");
		long filelength = f.length();
		byte[] filecontent = new byte[(int)(filelength)];
		try {
			FileInputStream in = new FileInputStream("./list");
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException notFoundE) {
			notFoundE.printStackTrace();
		} catch (IOException ioE) {
			ioE.printStackTrace();
		}

		String content = new String(filecontent);
		System.out.println(content);
	}
}

