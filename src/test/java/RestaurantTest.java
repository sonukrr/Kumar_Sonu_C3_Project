
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.restaurantFinder.Item;
import com.restaurantFinder.Restaurant;
import com.restaurantFinder.itemNotFoundException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    List<Item> selectedItems = new ArrayList<Item>();
    
    public void restaurantCreation() {
    	LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
    	restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    	restaurant.addToMenu("Prawns Biryani", 400);
    	restaurant.addToMenu("Mutton Biryani", 200);
    }
    

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
    	
        restaurantCreation();
        restaurant.setClosingTime(LocalTime.now().plusMinutes(40));                
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
    	restaurantCreation();
    	restaurant.setClosingTime(LocalTime.now().minusMinutes(30));
    	assertFalse(restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>ORDER TOTAL<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void order_value_equal_to_cumulative_total_on_adding_all_items() {
        restaurantCreation();
        selectedItems = restaurant.getMenu();    	
        
        assertEquals(1600, restaurant.getOrderValue(selectedItems));
    }

    @Test
    public void removing_item_from_order_should_update_total_cost() {
        restaurantCreation();
        selectedItems = restaurant.getMenu();
        int total = restaurant.getOrderValue(selectedItems);
        int removedPrice = selectedItems.get(0).getPrice();    	
        selectedItems.remove(0);    	    	
        
        assertEquals(total - removedPrice, restaurant.getOrderValue(selectedItems));
    }    
//<<<<<<<<<<<<<<<<<<<<<<<<<<<ORDER TOTAL>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
    	restaurantCreation();
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
    	restaurantCreation();
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
    	restaurantCreation();
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class, ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}