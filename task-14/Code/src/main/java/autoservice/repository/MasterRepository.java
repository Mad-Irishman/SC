package autoservice.repository;

import autoservice.models.master.Master;

import java.util.List;

public interface MasterRepository {
    boolean addMaster(Master master);

    List<Master> allMasters();

    boolean deleteMasterByName(Master master);

    Master getMasterById(String id);

    boolean updateMaster(Master master);

}
