import controller.Builder;
import controller.MenuController;
import view.menu.Menu;
import view.menu.Navigator;

public class Main {
    public static void main(String[] args) {
        Builder builder = new Builder();
        builder.buildMenu();

        Navigator navigator = new Navigator(builder.getRootMenu());

        MenuController menuController = new MenuController(builder, navigator);

        menuController.run();
    }
}
