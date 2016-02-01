package data;

import java.util.List;

public interface GroceryDAO {

	public List<Store> getGroceryStoreList();
	public Store getGroceryStore(String grocerName);
	public List<Item> getItemByType(String type);
	public List<Item> getItemByBrand(String brand);
	public void addItemToStore(Item item);
	public void removeItemFromStore(Item item);
}
