
public class Person extends MovingImage {
    protected int health;

    
	public Person(String name, int x, int y, int w, int h){
		super(name,x,y,w,h);
	}
	
	public void moveByAmount(double x, double y){
		this.x += x;
		if (getX() < 0 ){
			this.x = 0 + 20;
		}
		else if (getX() >= 470){
			this.x = 460;
		}
		
		this.y += y;
	}
}
