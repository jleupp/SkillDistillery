package data;

import java.util.List;

public interface Store extends Comparable<Store> {

	public List<Section> getSections();
	public String getStoreName();
	public List<Item> getItemByType(String type);
	public List<Item> getItemByBrand(String brand);
	public void addItemToSection(Item item);
}
