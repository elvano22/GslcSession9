
public class Movie extends Cd implements iDiscountable{
	
	private int duration;

	public Movie(String title, String type, int price, int stock, String cdType, String director, int duration) {
		super(title, type, price, stock, cdType, director);
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public int getDiscount() {
		int discounted = (int) (super.price * 0.9);
		return discounted;
	}
	
	
}
