package autoservice.servicesSorting.DataSort;

import java.time.LocalDateTime;

public interface DataSortInterface {

    int getFreePlacesOnDate(LocalDateTime date);

    LocalDateTime getNearestFreeDate();
}
