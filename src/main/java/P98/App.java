package P98;
import P98.GameController.GameController;

/**
 * Hello world!
 *
 */
class Base {
    public Integer getX() {
        return 5;
    }    

    public Base clone() {
        return new Base();
    }
}

class Derived extends Base {
    private Integer x;

    public Derived() {
        this.x = 10;
    }

    public Derived(Derived d) {
        this.x = d.getX();
    }

    @Override
    public Integer getX() {
        return this.x;
    }

    public Derived clone() {
        return new Derived();
    }

    public Integer test() {
        return 5;
    }

    public Integer test(Boolean b) {
        return 10;
    }
}

public class App 
{

    public static void main( String[] args )
    {
        // Base b = new Derived();
        // Derived d = new Derived(); 

        // System.out.println(b.getX());
        // System.out.println(d.test());
        // System.out.println(d.test(true));
        
        GameController.start();
        GameController.load("./");
    }
}
