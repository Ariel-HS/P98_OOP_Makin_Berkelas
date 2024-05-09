package P98.newComponent;

public class foo implements Holdable {

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
