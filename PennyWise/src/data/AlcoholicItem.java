package data;

public class AlcoholicItem implements Item, Liquid {
	private String brand;
	private String type;
	private String vintage;
	private Double price;
	private Integer volume;
	private String size;
	private Liquid.LiquidVolume unitMeasurement = null;
	private String storeSection;
	private String store;
	
	public AlcoholicItem(String storeName, String storeSection, String brand, String type, Double price, Integer volume, Enum unit) {
		this.brand = brand;
		this.type = type;
		this.price = price;
		this.volume = volume;
		this.unitMeasurement = ((Liquid.LiquidVolume)unit);
		this.storeSection = storeSection;
		this.store = storeName;
		this.size = volume + " " + unitMeasurement.toString();
		
	}
	public AlcoholicItem(String itemPackage, String storeSection, String storeName) {
		System.out.println("IN ALCOHOL ITEM CONSTRUCTOR" + "    " + itemPackage);
		String[] itemVariable = itemPackage.split(",");
		this.brand = itemVariable[0];
		this.type = itemVariable[1];
		this.vintage = itemVariable[2].trim();
		this.price = Double.parseDouble(itemVariable[3].trim());
		this.volume = Integer.parseInt(itemVariable[4].trim());
		setUnitMeasurement(Liquid.LiquidVolume.ML);
		this.size = volume + " " + unitMeasurement.toString();
		this.storeSection = storeSection;
		this.store = storeName;
	}
	
	public String getStore() {
		return store;
	}
	
	public void setStore(String store) {
		this.store = store;
	}
	
	public String getStoreSection() {
		return storeSection;
	}
	
	public void setStoreSection(String section) {
		this.storeSection = section;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String volume) {
		this.size = volume + unitMeasurement.toString();
	}
	
	public Integer getVolume() {
		return volume;
	}
	
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brandName) {
		this.brand = brandName;
	}
	
	public String getType() {
		return type;
	}			
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setUnitMeasurement(Enum unit) {
		this.unitMeasurement = ((Liquid.LiquidVolume)unit);
	}

	@Override
	public int compareTo(Item n) {
		if (!store.equalsIgnoreCase(n.getStore())) {
			return store.compareTo(n.getStore());
		}
		else if (!storeSection.equalsIgnoreCase(n.getStoreSection())) {
			return storeSection.compareToIgnoreCase(n.getStoreSection());
		}
		else return price.compareTo(n.getPrice());
	}

}
