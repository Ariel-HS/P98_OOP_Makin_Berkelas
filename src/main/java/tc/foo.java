package tc;

public class foo implements Holdable {

	public foo() {};
	
	@Override
	public void interact(Holdable u) {
		exampleMethod();
		u.exampleMethod();
	}
	
	@Override
	public void exampleMethod() {
		System.out.println("This is from foo");
	}

}
