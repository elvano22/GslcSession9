
public abstract class Cd extends Product{
	
	protected String director, cdType;

	public Cd(String title, String type, int price, int stock, String cdType, String director) {
		super(title, type, price, stock);
		this.cdType = cdType;
		this.director = director;
	}

	public String getCdType() {
		return cdType;
	}

	public void setCdType(String cdType) {
		this.cdType = cdType;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	
}
