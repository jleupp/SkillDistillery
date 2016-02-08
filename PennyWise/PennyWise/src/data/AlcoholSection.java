package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlcoholSection implements Section {
	private String storeName;
	private final String section = "Alcohol";
	private List<Item> alcoholItemList = new ArrayList<>();
	
	public AlcoholSection(String itemsPackage, String storeName) {
		String[] tokens = itemsPackage.split(",\\^");
		for (String itemPackage : tokens) {
			System.out.println("IN SPLIT ITEMS: ");
			System.out.println(itemPackage);
			alcoholItemList.add(new AlcoholicItem(itemPackage.trim(), section, storeName));
		}
		this.storeName = storeName;
	}
	
	public void removeItem(Item item) {
		alcoholItemList.remove(item);
	}
	
	public void addItem(Item item) {
		alcoholItemList.add(item);
	}
	
	public String getStoreName() {
		return storeName;
	}
	
	public String getSection() {
		return section;
	}
	
	public List<Item> getItemList() {
		Collections.sort(alcoholItemList);
		return alcoholItemList;
	}
	
	public List<Item> findItemType(String type) {
		List<Item> typeItems = new ArrayList<>();
		for (Item item : alcoholItemList) {
			if (item.getType().equalsIgnoreCase(type)) {
				typeItems.add(item);
			}
			else continue;
		}
		Collections.sort(typeItems);
		return typeItems;
	}
	
	public List<Item> findItemBrand(String brand) {
		List<Item> brandItems = new ArrayList<>();
		for (Item item : alcoholItemList) {
			if (item.getBrand().equalsIgnoreCase(brand)) {
				brandItems.add(item);
			}
			else continue;
		}
		Collections.sort(brandItems);
		return brandItems;
	}

	@Override
	public int compareTo(Section n) {
		if (storeName.equalsIgnoreCase(n.getStoreName())) {
			return section.compareTo(n.getSection());
		}
		else return storeName.compareTo(n.getStoreName());
	}
}
