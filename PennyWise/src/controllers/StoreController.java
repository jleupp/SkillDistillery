package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import data.*;





@Controller
@SessionAttributes({"shoppingList", "requestedItemsList"})
public class StoreController {
	@Autowired
	GroceryDAO groceryDAO;
	
	@ModelAttribute("shoppingList")
	public List<Item> setShoppingList() {
		List<Item> itemList = new ArrayList<>();
		return itemList;
	}

	@ModelAttribute("requestedItemsList")
	public List<Item> setRemoveItemList() {
		List<Item> itemList = new ArrayList<>();
		return itemList;
	}
//	If List of Stores is searched for a specific item TYPE (e.g. "Apple") this Method is called
//	A list of all Items from All stores that have matches for this type is added to a session list
//	that can be used later to add selected items to a Separate Shopping List
	
	@RequestMapping(path="request.do", params="type", method=RequestMethod.GET)
	public ModelAndView getItemByType(String type, @ModelAttribute("requestedItemsList") List<Item> returnList) {
		returnList.clear();
		ModelAndView mv = new ModelAndView("display.jsp");
		List<Item> itemList = groceryDAO.getItemByType(type);
		returnList.addAll(itemList);
		Collections.sort(itemList);
		mv.addObject("itemList", itemList);
		mv.addObject("method", "getItemByType");
		for (Item item : returnList) {
			System.out.println(item.getStoreSection() + " " + item.getBrand() + " " + item.getType() + " " + item.getPrice());
			
		}
		System.out.println(returnList.size());
		return mv;
	}

//	If List of Stores is searched for a specific item BRAND (e.g. "KRAFT") this Method is called
//	A list of all Items from All stores that have matches for this type is added to a session list
//	that can be used later to add selected items to a Separate Shopping List
	
	
	@RequestMapping(path="request.do", params="brand", method=RequestMethod.GET)
	public ModelAndView getItemByBrand(String brand, @ModelAttribute("requestedItemsList") List<Item> returnList) {
		returnList.clear();
		ModelAndView mv = new ModelAndView("display.jsp");
		returnList.addAll(groceryDAO.getItemByBrand(brand));
		Collections.sort(returnList);
		mv.addObject("itemList", returnList);
		mv.addObject("method", "getItemByBrand");
		for (Item item : groceryDAO.getItemByBrand(brand)) {
			System.out.println(item.getStoreSection() + " " + item.getBrand() + " " + item.getType() + " " + item.getSize() + " " + item.getPrice());
		}
		System.out.println(returnList.size());
		return mv;
	}
	
//After an Item(s) has been selected by a user to add to their Shopping List This method is Called from the JSP
//The JSP returns a list of each Item's location in memory that the user selected, these are then compared to the list created in the search 
//for item calls. Any items that are matched are subsequently added to the User's Shopping list.
	
	@RequestMapping(path="∆List.do", name="add" , params="add", method=RequestMethod.GET)
	public ModelAndView addItemsToList(@ModelAttribute("requestedItemsList") List<Item> returnList, @ModelAttribute("shoppingList") List<Item> itemList, @RequestParam("modifyList") String...stringArray) {
		System.out.println(returnList.size());
		ModelAndView mv = new ModelAndView("display.jsp");
		for (String st : stringArray) {
			System.out.println("String returned from JSP: " + st);
			for(Item item : returnList) {
				System.out.println("Item from Session List: " + item);
				if (item.toString().equals(st)) {
					itemList.add(item);
				} else continue;
			}
		}
		mv.addObject("itemList", itemList);
		return mv;
	}
	
	@RequestMapping(path="∆List.do", name="remove", params="remove", method=RequestMethod.GET)
	public ModelAndView removeItemsFromList(@ModelAttribute("shoppingList") List<Item> shoppingList, @RequestParam("modifyList") String...stringArray) {
		ModelAndView mv = new ModelAndView("display.jsp");
		List<Item> removalItems = new ArrayList<>();
		for(String objectLocation : stringArray) {
			for (Item item : shoppingList) {
				if (objectLocation.equals(item.toString())) {
					removalItems.add(item);
				} else continue;
			}
		}
		for (Item removalItem : removalItems) {			
			shoppingList.remove(removalItem);
		}
		Collections.sort(shoppingList);
		mv.addObject("itemList", shoppingList);
		return mv;
				
	}

	
	
	
	@RequestMapping(path="∆List.do", name="update", method=RequestMethod.GET)
	public ModelAndView modifyStoreItems(@ModelAttribute("shoppingList") List<Item> shoppingList, @ModelAttribute("requestedItemsList") List<Item> returnList, @RequestParam("update") String string) {
		System.out.println(string);
		ModelAndView mv = new ModelAndView("updateItem.jsp");
			for (Item item : returnList) {
				if (item.toString().equalsIgnoreCase(string)) {
					System.out.println(string + " equals " + item.toString());
					mv.addObject("itemUpdate", item);
					return mv;
				} else continue;
			}
			for (Item item : shoppingList) {
				if (item.toString().equalsIgnoreCase(string)) {
					System.out.println("MATCHED UPDATE ITEM TO ITEM IN SHOPPING LIST");
					mv.addObject("itemUpdate", item);
					return mv;
				}else continue;
			}
		return mv;
	}
	
	@RequestMapping(path="∆Item.do", name="update", method=RequestMethod.GET)
	public ModelAndView updateItem(
			@ModelAttribute("shoppingList") List<Item> shoppingList, 
			@ModelAttribute("requestedItemsList") List<Item> returnList, 
									@RequestParam("itemAddress") String itemAddress, @RequestParam("updatePrice") Double price, 
									@RequestParam("removeItem") String removeItem, @RequestParam("store_name") String storeName) {
		
			Item updatedItem = null;
			for (Item item : returnList) {
				if (item.toString().equalsIgnoreCase(itemAddress)) {
					updatedItem = item;
				} else continue;
			}
			if (updatedItem == null) {
				for (Item item : shoppingList) {
					if (item.toString().equalsIgnoreCase(itemAddress)) {
						updatedItem = item;
						System.out.println("UPDATING ITEM" + updatedItem.getSize());
					} else continue;
				}
			}
		if (removeItem.equals("false,true")) {
			returnList.remove(updatedItem);
			shoppingList.remove(updatedItem);
			groceryDAO.removeItemFromStore(updatedItem);
		}
		else {
			if (!storeName.trim().equals("Dont")) {
				String[] tokens = updatedItem.getSize().split(" ");
				if (updatedItem.getStoreSection().equalsIgnoreCase("Alcohol")) {
					updatedItem = new AlcoholicItem(storeName.trim(), updatedItem.getStoreSection(), updatedItem.getBrand(), updatedItem.getType(), updatedItem.getPrice(), Integer.parseInt(tokens[0]), Liquid.LiquidVolume.ML);
				}
				groceryDAO.addItemToStore(updatedItem);
				shoppingList.add(updatedItem);
			}
			if (updatedItem.getPrice() != price) {
		
				System.out.println("IN UPDATE PRICE");
				System.out.println("Current Price: " + updatedItem.getPrice());
				updatedItem.setPrice(price);
				System.out.println("Updated Price: " + updatedItem.getPrice());
			}
		}
			
		System.out.println("ItemAddress " + itemAddress);
		System.out.println("Item Price " + price);
		System.out.println("Remove Item " + removeItem);
		System.out.println("Store Name" + storeName);
		ModelAndView mv = new ModelAndView("index.jsp");
		return mv;
		
		
	}
	
	@RequestMapping(path="addItem.do", method=RequestMethod.GET)
	public ModelAndView addItemToStore(@ModelAttribute("shoppingList") List<Item> shoppingList,
										@RequestParam("store_name") String storeName, @RequestParam("store_section") String storeSection,
										@RequestParam("brand") String brand, @RequestParam("type") String type, @RequestParam("price") Double price, @RequestParam("size") String size) {
		ModelAndView mv = new ModelAndView("display.jsp");
		System.out.println(storeSection);
		if (storeSection.equalsIgnoreCase("Alcohol")) {
			String[] tokens = size.split(" ");
			Item n = new AlcoholicItem(storeName, storeSection, brand, type, price, Integer.parseInt(tokens[0]), Liquid.LiquidVolume.ML);
			groceryDAO.addItemToStore(n);
			shoppingList.add(n);
		}
		Collections.sort(shoppingList);
		mv.addObject("itemList", shoppingList);
		return mv;
		
	}
	
	
	
	
	
}
