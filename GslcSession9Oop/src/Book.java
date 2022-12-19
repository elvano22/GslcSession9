
public class Book extends Product implements iDiscountable{
	
	private String author;

	public Book(String title, String type, int price, int stock, String author) {
		super(title, type, price, stock);
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public int getDiscount() {
		int discounted = (int) (super.price * 0.8);
		return discounted;
	} 
	
	
}
