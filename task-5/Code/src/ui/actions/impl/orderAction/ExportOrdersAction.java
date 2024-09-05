package ui.actions.impl.orderAction;

import CSVUtils.CSVExporter;
import autoservice.manager.impl.ServiceManager;
import ui.actions.IAction;

import java.io.IOException;
import java.util.Scanner;

public class ExportOrdersAction implements IAction {
    private final ServiceManager serviceManager;

    public ExportOrdersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу для экспорта заказов: ");
        String filePath = scanner.nextLine();

        try {
            CSVExporter.exportOrdersToCSV(filePath, serviceManager.getOrders());
            System.out.println("Данные успешно экспортированы в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при экспорте данных: " + e.getMessage());
        }
    }
}


