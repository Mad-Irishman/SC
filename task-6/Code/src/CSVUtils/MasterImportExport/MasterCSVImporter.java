package CSVUtils.MasterImportExport;

import autoservice.models.master.Master;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MasterCSVImporter {
    public static void importMastersFromCSV(List<Master> masters, String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Пропускаем заголовок

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // Разделение по запятой
                String id = data[0];
                String name = data[1];

                Master master = findMasterById(masters, id);
                if (master != null) {
                    // Обновляем существующего мастера
                    master.setName(name);
                } else {
                    // Добавляем нового мастера
                    masters.add(new Master(name));
                }
            }
        }
    }

    private static Master findMasterById(List<Master> masters, String id) {
        for (Master master : masters) {
            if (Objects.equals(master.getId(), id)
            ) {
                return master;
            }
        }
        return null;
    }
}
