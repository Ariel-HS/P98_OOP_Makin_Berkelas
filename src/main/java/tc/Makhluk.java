package tc;

public class Makhluk implements Holdable{
	@Override
	public void interact(Holdable u) {
		exampleMethod();
		u.exampleMethod();
	}
	
	@Override
	public void exampleMethod() {
		System.out.println("This is from foo");
	}
	
	@Override
	public String getNama() {
		return("Jagung");
	}
}
