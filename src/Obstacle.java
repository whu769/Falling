
public class Obstacle extends MovingImage {
	
	public Obstacle(String name, int x, int y, int w, int h){
		super(name,x,y,w,h);
	}
	
	public void moveByAmount(double x, double y){
		this.y += y;
	}
}
