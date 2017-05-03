import java.util.List;
//import java.io.IOException;
//import java.io.FileNotFoundException;
import java.io.*;

public class Thread1 extends Thread {
	private String name;
	private String path;

	public Thread1 (String name, String path) {
		this.name = name;
		this.path = path;
	}

	public void run() {
		List<String> list = JsoupBaidu2.nameList(name);
		try {
			JsoupBaidu2.getPictures(list, 2, path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String readContent(String path) {
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
		return content;
	}

	public static void main(String[] args) {
		String path = "/tmp/myseconddir";
		String content = Thread1.readContent("./list");
		List<String> lst = JsoupBaidu2.nameList(content);
		int iter = 0;
		Thread1[] thr_array = new Thread1[100];
		for (String nm: lst) {
			thr_array[iter] = new Thread1(lst.get(iter), path);
			thr_array[iter].start();
			iter++;
		}
		System.out.println("I have to wait for it to complete");
		try {
			for (int i = 0; i < iter; i++) {
				thr_array[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
