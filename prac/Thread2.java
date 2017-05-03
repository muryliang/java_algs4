public class Thread2 extends Thread{
	private String name;
	private Thread3 tr3;

	public Thread2(String name) {
		this.name = name;
		Thread3 th3 = new Thread3();
	}

}
