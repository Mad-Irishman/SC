package CSVUtils;

import autoservice.models.order.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVImporter {

    public static void importOrdersFromCSV(String filePath, List<Order> orders) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Order importedOrder = Order.fromCSV(line);

                boolean updated = false;
                for (int i = 0; i < orders.size(); i++) {
                    if (orders.get(i).getIdOrder() == importedOrder.getIdOrder()) {
                        orders.set(i, importedOrder);
                        updated = true;
                        break;
                    }
                }

                if (!updated) {
                    orders.add(importedOrder);
                }
            }
        }
    }
}
