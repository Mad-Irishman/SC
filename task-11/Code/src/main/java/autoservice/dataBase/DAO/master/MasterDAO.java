package autoservice.dataBase.DAO.master;

import autoservice.models.master.Master;

import java.util.List;

public interface MasterDAO {
    boolean addMaster(Master master);

    public List<Master> allMasters();

    public boolean deleteMasterByName(Master master);

    public Master getMasterById(String id);
}
