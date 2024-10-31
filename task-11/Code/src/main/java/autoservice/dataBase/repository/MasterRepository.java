package autoservice.dataBase.repository;

import autoservice.models.master.Master;

import java.util.List;

public interface MasterRepository {
    boolean addMaster(Master master);

    public List<Master> allMasters();

    public boolean deleteMasterByName(Master master);

    public Master getMasterById(String id);
}
