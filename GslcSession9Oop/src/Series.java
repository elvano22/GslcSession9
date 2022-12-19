
public class Series extends Cd{
	
	int season, totalEpisodes;

	public Series(String title, String type, int price, int stock, String cdType, String director, int season,
			int totalEpisodes) {
		super(title, type, price, stock, cdType, director);
		this.season = season;
		this.totalEpisodes = totalEpisodes;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getTotalEpisodes() {
		return totalEpisodes;
	}

	public void setTotalEpisodes(int totalEpisodes) {
		this.totalEpisodes = totalEpisodes;
	}
	
	
}
