package christmas;

import christmas.controller.RestaurantController;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        RestaurantController restaurant = new RestaurantController();
        restaurant.eventStart();
    }
}
