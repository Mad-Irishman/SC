package view.menu;

public class Navigator {
    private Menu currentMenu;

    public void printMenu() {
        // Отобразить текущее меню и опции
    }

    public void navigate(int index) {
        MenuItem selectedItem = currentMenu.getMenuItems()[index];
        selectedItem.doAction();
    }
}