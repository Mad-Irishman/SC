import controller.Builder;
import controller.MenuController;
import view.menu.Navigator;

public class Main {
    public static void main(String[] args) {
        Builder builder = new Builder();
        Navigator navigator = new Navigator();
        MenuController controller = new MenuController(builder, navigator);

        controller.run();
    }
}
