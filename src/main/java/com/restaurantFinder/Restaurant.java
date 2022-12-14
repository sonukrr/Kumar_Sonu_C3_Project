package com.restaurantFinder;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        
        menu.add(new Item("Noodles", 100));
        menu.add(new Item("Chicken Biryani", 200));
        menu.add(new Item("Chicken Fried Rice", 300));
        menu.add(new Item("Mutton Biryani", 400));
     
    }

    public boolean isRestaurantOpen() {
        boolean isOpen = false;
        
        LocalTime currTime = LocalTime.now();        
        isOpen = currTime.compareTo(openingTime) >= 0 && currTime.compareTo(closingTime) < 0;
        return isOpen;
        
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {       
        return this.menu;        
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    
    public LocalTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

	public String getName() {
        return name;
    }

    public Integer getOrderValue(List<Item> selectedItems) {
        int total = 0;
        for (Item item : selectedItems) {
            total += item.getPrice();
        } 
        return total;
    }


}
