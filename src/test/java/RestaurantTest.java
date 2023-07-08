import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void setup() {
        // Initialize a restaurant before each test case
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        restaurant.openingTime = LocalTime.now().minusHours(1);
        restaurant.closingTime = LocalTime.now().plusHours(1);
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        restaurant.openingTime = LocalTime.now().plusHours(1);
        restaurant.closingTime = LocalTime.now().plusHours(2);
        assertFalse(restaurant.isRestaurantOpen());
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws ItemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(ItemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
    }

    //Order Value Test Cases
    @Test
    public void calculate_order_value_for_empty_item_names_should_return_0() {
        int orderTotal = restaurant.calculateOrderValue(Collections.emptyList());
        assertEquals(0, orderTotal);
    }


    @Test
    public void calculate_order_value_for_non_existing_item_names_should_return_0() {
        int orderTotal = restaurant.calculateOrderValue(Arrays.asList("French fries", "Burger"));
        assertEquals(0, orderTotal);
    }


    @Test
    public void calculate_order_value_for_valid_item_names_should_return_correct_total() {
        int orderTotal = restaurant.calculateOrderValue(Arrays.asList("Sweet corn soup", "Vegetable lasagne"));
        assertEquals(388, orderTotal);
    }



    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //Failing Test Case
// We are trying to remove an item from the Menu, however we expect the size of the menu
// to increase by 1 after the removal, hence it will fail.
    @Test
    public void removing_item_from_menu_should_increase_menu_size_by_1() throws ItemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

}
