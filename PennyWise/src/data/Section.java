package data;

import java.util.List;

public interface Section extends Comparable<Section> {
	public List<Item> getItemList();
	public List<Item> findItemType(String type);
	public List<Item> findItemBrand(String brand);
	public String getStoreName();
	public String getSection();
	public void addItem(Item item);
	public void removeItem(Item item);
}