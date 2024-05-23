package tc;

public class bar implements Holdable {
	
	public bar() {};
	@Override
	public void interact(Holdable u) {
		exampleMethod();
		u.exampleMethod();
	}
	
	@Override
	public void exampleMethod() {
		System.out.println("This is from bar");
	}
	
	@Override
	public String getNama() {
		return("Domba");
	}
}
