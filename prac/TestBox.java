public class TestBox {
	Integer i = 2;
	int j = 3;

	public static void main(String[] args) {
		TestBox t = new TestBox();
		t.go();
	}

	public void go() {
		j = i;
		System.out.println(i);
		System.out.println(j);
	}
}
