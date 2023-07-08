import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
public class MockUI {
    private RestaurantService restaurantService;

    public MockUI(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public void displayRestaurantDetails(String restaurantName) {
        try {
            Restaurant restaurant = restaurantService.findRestaurantByName(restaurantName);
            restaurant.displayDetails();
        } catch (RestaurantNotFoundException e) {
            System.out.println("Restaurant not found: " + e.getMessage());
        }
    }


}
