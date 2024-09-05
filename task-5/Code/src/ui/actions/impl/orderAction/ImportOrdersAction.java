package ui.actions.impl.orderAction;

import CSVUtils.CSVImporter;
import autoservice.manager.impl.ServiceManager;
import ui.actions.IAction;

import java.io.IOException;
import java.util.Scanner;

public class ImportOrdersAction implements IAction {
    private final ServiceManager serviceManager;

    public ImportOrdersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу для импорта заказов: ");
        String filePath = scanner.nextLine();

        try {
            CSVImporter.importOrdersFromCSV(filePath, serviceManager.getOrders());
            System.out.println("Данные успешно импортированы из файла: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при импорте данных: " + e.getMessage());
        }
    }
}