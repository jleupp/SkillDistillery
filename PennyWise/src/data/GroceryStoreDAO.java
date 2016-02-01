package data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class GroceryStoreDAO implements GroceryDAO {
	private static final String FILE_NAME="/WEB-INF/grocery.csv";
	private List<Store> groceryStoreList = new ArrayList<>();
	/*
	 * Use Autowired to have Spring inject an instance
	 * of an ApplicationContext into this object after
	 * creation.  We will use the ApplicationContext to
	 * retrieve an InputStream so we can read from a 
	 * file.
	 */
	@Autowired 
	private ApplicationContext ac;

	/*
	 * The @PostConstruct method is called by Spring after 
	 * object creation and dependency injection
	 */
	@PostConstruct
	public void init() {
		System.out.println("IN GROCERYSTORE INIT");
		// Retrieve an input stream from the application context
		// rather than directly from the file system
		try (
				InputStream is = ac.getResource(FILE_NAME).getInputStream();
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			) {
			//Initializes line and reads in first |Header Line: Comment out to read in first line|
			String line = buf.readLine(); 			
			while ((line = buf.readLine()) != null) {
				System.out.println("IN WHILE LOOP READING IN CSV");
				String[] tokens = line.split("\\+,");
				String grocerName = tokens[0].trim();
				String sectionsPackage = tokens[1].trim();
				groceryStoreList.add(new GroceryStore(grocerName, sectionsPackage));
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public List<Store> getGroceryStoreList() {
		return groceryStoreList;
	}
	
	public void removeItemFromStore(Item item) {
		for (Store store : groceryStoreList) {
			if(item.getStore().equalsIgnoreCase(store.getStoreName())) {
				store.removeItemFromSection(item);
			}
		}
	}
	
	public void addItemToStore(Item item) {
		for (Store store : groceryStoreList) {
			if(item.getStore().equalsIgnoreCase(store.getStoreName())) {
				store.addItemToSection(item);
			}
		}
	}
	
	public List<Item> getItemByType(String type) {
		System.out.println("IN GETITEM BY TYPE");
		
		List<Item> typeList = new ArrayList<>();
		for (Store store : groceryStoreList) {
			typeList.addAll(store.getItemByType(type));
		}
		Collections.sort(typeList);
		System.out.println("BEFORE RETURN");
		return typeList;
	}
	
	public List<Item> getItemByBrand(String brand) {
		List<Item> brandList = new ArrayList<>();
		for (Store store : groceryStoreList) {
			brandList.addAll(store.getItemByBrand(brand));
		}
		Collections.sort(brandList);
		return brandList;
	}
	
	public Store getGroceryStore(String grocerName) {
		for (Store store : groceryStoreList) {
			if (store.getStoreName().equalsIgnoreCase(grocerName)) {
				return store;
			}
			else {continue;}
		}
		return null;
	}
}
