package autoservice.repository;

import autoservice.models.master.Master;

import java.util.List;

public interface MasterRepository {
    String addMaster(Master master);

    List<Master> allMasters();

    String removeMasterByName(Master master);

    Master getMasterById(String id);

    boolean updateMaster(Master master);

}
