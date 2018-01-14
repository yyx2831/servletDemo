package cn.yyx.ServletTest.Porxy;

public class Target implements TargetInterface{

	@Override
	public void method1() {
		System.out.println("method1 running...");
	}

	@Override
	public String method2() {
		System.out.println("method2 running...");
		return "method2";
	}

	@Override
	public int method3(int x) {
		return x;
	}

	
	
}
