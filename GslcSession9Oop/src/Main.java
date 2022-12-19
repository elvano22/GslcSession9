import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	Scanner scan = new Scanner(System.in);
	ArrayList<Member> memberList = new ArrayList<>();
	ArrayList<Product> productList = new ArrayList<>();
	
	public void showAll() {
		if(productList.size()==0) {
			System.out.println("No Data");
			return;
		}
		System.out.printf("|%-3s|%-15s|%-7s|%-15s|%-15s|%-15s|%-15s|%-10s|%-15s|%-15s|%-7s|", "No", "Title","Season", "Type", "CD Type", "Author", "Director", "Duration", "Total Episodes","Price", "Stock");
		for(int i=0; i<productList.size(); i++) {
			if(productList.get(i).getType()=="Book"){
				Book books = (Book) productList.get(i);
				System.out.printf("|%-3d|%-15s|%-7d|%-15s|%-15s|%-15s|%-15s|%-10d|%-15d|%-15d|%-7d|", i+1, books.getTitle() ,"-", books.getType(), "-",books.getAuthor(), "-", "-", "-",books.getPrice(), books.getStock());
			}
			else if(productList.get(i).getType()=="CD" && ((Cd)productList.get(i)).getCdType()=="Movie"){
				Movie movies = (Movie)productList.get(i);
				System.out.printf("|%-3d|%-15s|%-7d|%-15s|%-15s|%-15s|%-15s|%-10d|%-15d|%-15d|%-7d|", i+1, movies.getTitle() ,"-", movies.getType(), movies.getCdType(),"-", movies.getDirector(), movies.getDuration(), "-",movies.getPrice(), movies.getStock());
			}
			else if(productList.get(i).getType()=="CD" && ((Cd)productList.get(i)).getCdType()=="Series"){
				Series series = (Series)productList.get(i);
				System.out.printf("|%-3d|%-15s|%-7d|%-15s|%-15s|%-15s|%-15s|%-10d|%-15d|%-15d|%-7d|", i+1, series.getTitle() ,series.getSeason(), series.getType(), series.getCdType(),"-", series.getDirector(), "-", series.getTotalEpisodes(),series.getPrice(), series.getStock());
			}
		}
		System.out.println("Press ENTER to Continue");
		scan.nextLine();
	}
	
	public void buy() {
		String areMember, findId;
		int check=-1, chooseProduct, howMany, total;
		
		do {
			System.out.print("Are you a member [y | n]: ");
			areMember = scan.nextLine();
			
			if(areMember == "y") {
				System.out.print("Input id: ");
				findId = scan.nextLine();
				
				check =-1;
				for(int i=0; i<memberList.size(); i++) {
					if(memberList.get(i).getId()==findId) {
						check = i;
					}
				}
				if (check ==-1) System.out.println("There is no member with that id");
			}
		} while((areMember!="y" || check == -1) && areMember!="n");
		
		System.out.printf("|%-3s|%-15s|%-7s|%-15s|%-15s|%-15s|%-15s|%-10s|%-15s|%-15s|%-7s|", "No", "Title","Season", "Type", "CD Type", "Author", "Director", "Duration", "Total Episodes","Price", "Stock");
		for(int i=0; i<productList.size(); i++) {
			if(productList.get(i).getType()=="Book"){
				Book books = (Book) productList.get(i);
				System.out.printf("|%-3d|%-15s|%-7d|%-15s|%-15s|%-15s|%-15s|%-10d|%-15d|%-15d|%-7d|", i+1, books.getTitle() ,"-", books.getType(), "-",books.getAuthor(), "-", "-", "-",books.getPrice(), books.getStock());
			}
			else if(productList.get(i).getType()=="CD" && ((Cd)productList.get(i)).getCdType()=="Movie"){
				Movie movies = (Movie)productList.get(i);
				System.out.printf("|%-3d|%-15s|%-7d|%-15s|%-15s|%-15s|%-15s|%-10d|%-15d|%-15d|%-7d|", i+1, movies.getTitle() ,"-", movies.getType(), movies.getCdType(),"-", movies.getDirector(), movies.getDuration(), "-",movies.getPrice(), movies.getStock());
			}
			else if(productList.get(i).getType()=="CD" && ((Cd)productList.get(i)).getCdType()=="Series"){
				Series series = (Series)productList.get(i);
				System.out.printf("|%-3d|%-15s|%-7d|%-15s|%-15s|%-15s|%-15s|%-10d|%-15d|%-15d|%-7d|", i+1, series.getTitle() ,series.getSeason(), series.getType(), series.getCdType(),"-", series.getDirector(), "-", series.getTotalEpisodes(),series.getPrice(), series.getStock());
			}
		}
		do {
			System.out.println("What product number do you want to buy?");
			System.out.print(" >> ");
			chooseProduct = scan.nextInt(); scan.nextLine();
		}while(chooseProduct<1 || chooseProduct>productList.size());
		do {
			System.out.println("How many?");
			System.out.print(" >> ");
			howMany = scan.nextInt(); scan.nextLine();
		}while(howMany<1 || howMany>productList.get(chooseProduct-1).getStock());
		
		if(areMember == "y") {
			if(productList.get(chooseProduct-1).getType() == "Book") {
				total = ((Book)productList.get(chooseProduct-1)).getDiscount() * howMany;
			}
			else if(productList.get(chooseProduct-1).getType() == "Cd" && ((Cd)productList.get(chooseProduct-1)).getCdType() == "Movie") {
				total = ((Movie)productList.get(chooseProduct-1)).getDiscount() * howMany;
			}
			else {
				total = productList.get(chooseProduct-1).getPrice() * howMany;
				
			}
		}
		else total = productList.get(chooseProduct-1).getPrice() * howMany;
		
		int temp = productList.get(chooseProduct-1).getStock() - howMany;
		if(temp == 0) productList.remove(chooseProduct-1);
		else productList.get(chooseProduct-1).setStock(temp);
		
		System.out.println("Thank you for purchasing. Your total price is "+ total);
		
		System.out.println("Press ENTER to Continue");
		scan.nextLine();
	}
	
	public void addProduct() {
		String title, type, author, cdType, director;
		int price, stock, duration, season, totalEpisodes;
		
		System.out.println("ADD PRODUCT");
		System.out.println("=====================");
		do {
			System.out.print("Title: ");
			title = scan.nextLine();
		} while(title.isEmpty());
		do {
			System.out.print("Stock: ");
			stock = scan.nextInt(); scan.nextLine();
		} while(stock<1);
		do {
			System.out.print("Price: ");
			price = scan.nextInt(); scan.nextLine();
		} while(price<1);
		do {
			System.out.print("Product type [Book | CD]: ");
			type = scan.nextLine();
		} while(!type.equals("Book") && !type.equals("CD"));
		
		if (type.equals("Book")) {
			do {
				System.out.print("Author: ");
				author = scan.nextLine();
			} while(author.isEmpty());
			productList.add(new Book(title, type, price, stock, author));
		}
		else if (type.equals("CD")) {
			do {
				System.out.print("Director: ");
				director = scan.nextLine();
			} while(director.isEmpty());
			do {
				System.out.print("CD type [Movie | Series]: ");
				cdType = scan.nextLine();
			} while(!cdType.equals("Movie") && !cdType.equals("Series"));
			
			if(cdType.equals("Movie")) {
				do {
					System.out.print("Movie duration: ");
					duration = scan.nextInt(); scan.nextLine();
				} while(duration<0);
				productList.add(new Movie(title, type, price, stock, cdType, director, duration));
			}
			else if(cdType.equals("Series")) {
				do {
					System.out.print("Season: ");
					season = scan.nextInt(); scan.nextLine();
				} while(season<1);
				do {
					System.out.print("Total episodes: ");
					totalEpisodes = scan.nextInt(); scan.nextLine();
				} while(totalEpisodes<1);
				productList.add(new Series(title, type, price, stock, cdType, director, season, totalEpisodes));
			}
		}
		
		System.out.println("Press ENTER to Continue");
		scan.nextLine();
	}
	
	public void deleteProduct() {
		int delete;
		if(productList.size()==0) {
			System.out.println("No Data");
			return;
		}
		System.out.printf("|%-3s|%-15s|%-7s|%-15s|%-15s|%-15s|%-15s|%-10s|%-15s|%-15s|%-7s|", "No", "Title","Season", "Type", "CD Type", "Author", "Director", "Duration", "Total Episodes","Price", "Stock");
		for(int i=0; i<productList.size(); i++) {
			if(productList.get(i).getType()=="Book"){
				Book books = (Book) productList.get(i);
				System.out.printf("|%-3d|%-15s|%-7d|%-15s|%-15s|%-15s|%-15s|%-10d|%-15d|%-15d|%-7d|", i+1, books.getTitle() ,"-", books.getType(), "-",books.getAuthor(), "-", "-", "-",books.getPrice(), books.getStock());
			}
			else if(productList.get(i).getType()=="CD" && ((Cd)productList.get(i)).getCdType()=="Movie"){
				Movie movies = (Movie)productList.get(i);
				System.out.printf("|%-3d|%-15s|%-7d|%-15s|%-15s|%-15s|%-15s|%-10d|%-15d|%-15d|%-7d|", i+1, movies.getTitle() ,"-", movies.getType(), movies.getCdType(),"-", movies.getDirector(), movies.getDuration(), "-",movies.getPrice(), movies.getStock());
			}
			else if(productList.get(i).getType()=="CD" && ((Cd)productList.get(i)).getCdType()=="Series"){
				Series series = (Series)productList.get(i);
				System.out.printf("|%-3d|%-15s|%-7d|%-15s|%-15s|%-15s|%-15s|%-10d|%-15d|%-15d|%-7d|", i+1, series.getTitle() ,series.getSeason(), series.getType(), series.getCdType(),"-", series.getDirector(), "-", series.getTotalEpisodes(),series.getPrice(), series.getStock());
			}
		}
		
		System.out.print("Choose index: ");
		delete = scan.nextInt(); scan.nextLine();
		productList.remove(delete-1);
		System.out.println("That product is successfully removed");
		System.out.println("Press ENTER to Continue");
		scan.nextLine();
	}
	
	public void addMember() {
		String name, phoneNumber, email, id;
		boolean check;
		
		System.out.println("ADD MEMBER");
		System.out.println("=====================");
		do {
			System.out.print("Name: ");
			name = scan.nextLine();
		} while(name.isEmpty());
		do {
			System.out.print("Phone Number [08.........]: ");
			phoneNumber = scan.nextLine();
			check = true;
			for(int i=0; i<phoneNumber.length(); i++) {
				if (!Character.isDigit(phoneNumber.charAt(i))) {
					check = false;
					break;
				}
			}
			System.out.println(phoneNumber.startsWith("08"));
			System.out.println(check);
		} while(!phoneNumber.startsWith("08") || !check);
		do {
			System.out.print("Email [..........@gmail.com]: ");
			email = scan.nextLine();
		} while(!email.endsWith("@gmail.com"));
		
		//generate id with JJ + 4 last digit of phone number + 1 random number
		id = "JJ";
		for(int i=phoneNumber.length()-5; i<phoneNumber.length(); i++) {
			id += phoneNumber.charAt(i);
		}
		id += (int)Math.random()*9;
		System.out.println("You are now a member. Your id is: " + id);
		
		memberList.add(new Member(name, phoneNumber, email, id));
		
		System.out.println("Press ENTER to Continue");
		scan.nextLine();
	}
	
	public void deleteMember() {
		String findId;
		int check = -1;
		do {
			System.out.print("Input id: ");
			findId = scan.nextLine();
			
			check =-1;
			for(int i=0; i<memberList.size(); i++) {
				if(memberList.get(i).getId()==findId) {
					check = i;
				}
			}
			if (check == -1) System.out.println("There is no member with that id");
		} while(check == -1);
		
		System.out.println("Press ENTER to Continue");
		scan.nextLine();
	}
	
	public Main() {
		int choose = 0;
		// Menu
		do {
			System.out.println("=========================");
			System.out.println(" JAYA JAYA BOOKS AND CDS");
			System.out.println("=========================");
			System.out.println("1. Show all product");
			System.out.println("2. Buy a product");
			System.out.println("3. Add product");
			System.out.println("4. Delete product");
			System.out.println("5. Add member");
			System.out.println("6. Delete member");
			System.out.println("7. Exit");
			System.out.print(" >> ");
			choose = scan.nextInt(); scan.nextLine();
			
			switch (choose) {
			case 1:
				showAll();
				break;
			case 2:
				buy();
				break;
			case 3:
				addProduct();
				break;
			case 4:
				deleteProduct();
				break;
			case 5:
				addMember();
				break;
			case 6:
				deleteMember();
				break;
			default:
				break;
			}
			
		} while(choose != 7); //Menu will be repeated as long as choose != 7
	}

	public static void main(String[] args) {
		new Main();
	}
}
