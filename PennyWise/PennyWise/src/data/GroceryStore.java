package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroceryStore implements Store {
	private List<Section> sectionList = new ArrayList<>();
	private String storeName;
	
	public GroceryStore(String storeName, String sectionPackage) {
		System.out.println("IN GROCERYSTORE CONSTRUCTOR");
		System.out.println(storeName + "    " + sectionPackage);
		this.storeName = storeName;
		String[] sections = sectionPackage.split(",&");
		System.out.println("AFTER SPLIT SECTIONS SECTIONS[0]" + sections[0]);
		System.out.println("AFTER SPLIT SECTIONS SECTIONS[1]" + sections[1]);
		for (String section : sections) {
			String[] splitSection = section.split(",\\*");
			String itemsPackage = splitSection[1].trim();
			System.out.println("");
			System.out.println("SPLIT ALCOHOL FROM ALCOHOL ITEMS");
			System.out.println("Alcohol ==" + splitSection[0]);
			System.out.println("Items" + itemsPackage);
			if (splitSection[0].trim().equalsIgnoreCase("Alcohol")) {
				sectionList.add(new AlcoholSection(itemsPackage, storeName));
			}
			else continue;
		}
	}
	
	public void removeItemFromSection(Item item) {
		for (Section section : sectionList) {
			if (item.getStoreSection().equalsIgnoreCase(section.getSection())) {
				section.removeItem(item);
			}
			else continue;
		}
	}
	
	public void addItemToSection(Item item) {
		for(Section section : sectionList) {
			if (item.getStoreSection().equalsIgnoreCase(section.getSection())) {
				section.addItem(item);
			}
			else continue;	
		}
	}
	
	public List<Item> getItemByType(String type) {
		List<Item> itemList = new ArrayList<>();
		for (Section section : sectionList) {
			itemList.addAll(section.findItemType(type));
		}
		Collections.sort(itemList);
		return itemList;
	}
	public List<Item> getItemByBrand(String brand) {
		List<Item> brandList = new ArrayList<>();
		for (Section section : sectionList) {
			brandList.addAll(section.findItemBrand(brand));
		}
		Collections.sort(brandList);
		return brandList;
	}
	 
	public List<Section> getSections() {
		return sectionList;
	}
	
	public String getStoreName() {
		return storeName;
	}
	
	public int compareTo(Store n) {
		return storeName.compareTo(n.getStoreName());
	}
}
