package autoservice.repository.impl;

import autoservice.config.database.connection.DatabaseConnection;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.repository.MasterRepository;
import autoservice.models.master.Master;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterRepositoryImpl implements MasterRepository {
    private static final Logger logger = LoggerFactory.getLogger(MasterRepositoryImpl.class);
    private static final String ADD_MASTER_QUERY = "INSERT INTO masters (id, name) VALUES (?, ?)";
    private static final String ALL_MASTER_QUERY = "SELECT * FROM masters";
    private static final String REMOVE_MASTER = "DELETE FROM masters WHERE name = ?";
    private static final String GET_MASTER_BY_ID = "SELECT * FROM masters WHERE id = ?";
    private static final String UPDATE_MASTER = "UPDATE masters SET is_available = ? WHERE name = ?";

    @Override
    public boolean addMaster(Master master) {
        logger.info("Attempting to add master: {}", master);

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_MASTER_QUERY)) {

            preparedStatement.setString(1, master.getId());
            preparedStatement.setString(2, master.getName());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Master added successfully: {}", master);
                return true;
            } else {
                logger.info("Failed to add master (no rows affected): {}", master);
                return false;
            }

        } catch (SQLException e) {
            logger.error("Error while adding master: {}", master, e);
            return false;
        }
    }

    @Override
    public List<Master> allMasters() {
        logger.info("Attempting to all masters");
        List<Master> allMasters = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ALL_MASTER_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                Master master = new Master(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        MasterStatus.valueOf(resultSet.getString("is_available"))
                );

                allMasters.add(master);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allMasters;
    }

    @Override
    public boolean deleteMasterByName(Master master) {
        logger.info("Attempting to remove master: {}", master);
        boolean isDeleted = false;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_MASTER)) {

            preparedStatement.setString(1, master.getName());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isDeleted = true;
                logger.info("Master with name '{}' was deleted successfully.", master);
            } else {
                logger.info("No master found with name '{}'.", master);
            }

        } catch (SQLException e) {
            logger.error("Error while trying to delete master with name '{}'", master, e);
        }

        return isDeleted;
    }

    @Override
    public Master getMasterById(String id) {
        Master master = null;
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MASTER_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                master = new Master(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        MasterStatus.valueOf(resultSet.getString("is_available"))
                );
            }
        } catch (SQLException e) {
            logger.error("Error while trying to retrieve master with ID '{}'", id, e);
        }
        return master;
    }


    @Override
    public boolean updateMaster(Master master) {
        logger.info("Attempting to update master: {}", master);
        boolean isUpdated = false;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MASTER)) {
            preparedStatement.setObject(1, master.isAvailable().name(), Types.OTHER);
            preparedStatement.setString(2, master.getName());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true;
                logger.info("Master updated successfully: {}", master);
            } else {
                logger.info("Failed to update master: {}", master);
            }
        } catch (SQLException e) {
            logger.error("Error updating master: {}", master, e);
            throw new RuntimeException(e);
        }
        return isUpdated;
    }

}
