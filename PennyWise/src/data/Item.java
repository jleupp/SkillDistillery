package data;

public interface Item extends Comparable<Item> {
	public String getBrand();
	public void setBrand(String brandName);
	public String getType();
	public void setType(String type);
	public double getPrice();
	public void setPrice(double price);
	public void setUnitMeasurement(Enum unit);
	public String getStoreSection();
	public void setStoreSection(String section);
	public String getSize();
	public String getStore();
	public void setStore(String store);
//	public int compareTo(Item n);
	
}
