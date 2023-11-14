package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.RestaurantController;

public class Application {
    public static void main(String[] args) {
        try {
            RestaurantController restaurant = new RestaurantController();
            restaurant.eventStart();
        } catch (Exception e) {
            throw e;
        } finally {
            Console.close();
        }
    }
}
