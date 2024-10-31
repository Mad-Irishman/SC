package autoservice.dataBase.repository.impl;

import autoservice.config.database.connection.DatabaseConnection;
import autoservice.dataBase.repository.GaragePlaceRepository;
import autoservice.models.garagePlace.GaragePlace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GaragePlaceRepositoryImpl implements GaragePlaceRepository {
    private static final Logger logger = LoggerFactory.getLogger(GaragePlaceRepositoryImpl.class);
    private static final String ADD_GARAGE_PLACE = "INSERT INTO garage_places (place_number, is_occupied) VALUES (?, ?)";
    private static final String ALL_GARAGE_PLACE = "SELECT * FROM garage_places";
    private static final String REMOVE_GARAGE_PLACE = "DELETE FROM garage_places WHERE place_number = ?";

    @Override
    public boolean addGaragePlace(GaragePlace garagePlace) {
        logger.info("Attempting to add garage_place: {}", garagePlace);

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_GARAGE_PLACE)) {

            preparedStatement.setInt(1, garagePlace.getPlaceNumber());
            preparedStatement.setBoolean(2, garagePlace.isOccupied());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Garage place added successfully: {}", garagePlace);
                return true;
            } else {
                logger.info("Failed to add garage place (no rows affected): {}", garagePlace);
                return false;
            }

        } catch (SQLException e) {
            logger.info("Error while adding garage place: {}", garagePlace, e);
            return false;
        }
    }


    @Override
    public List<GaragePlace> getAllGaragePlaces() {
        logger.info("Attempting to get all garage places");
        List<GaragePlace> garagePlaces = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ALL_GARAGE_PLACE);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                GaragePlace garagePlace = new GaragePlace(resultSet.getInt("place_number"));
                garagePlaces.add(garagePlace);
            }

            return garagePlaces;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeGaragePlace(GaragePlace garagePlace) {
        logger.info("Attempting to remove garage place: {}", garagePlace);
        boolean isDeleted = false;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_GARAGE_PLACE)) {

            preparedStatement.setInt(1, garagePlace.getPlaceNumber());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                isDeleted = true;
                logger.info("Garage place removed successfully: {}", garagePlace);
            } else {
                logger.info("Failed to remove garage place: {}", garagePlace);
            }

        } catch (SQLException e) {
            logger.error("Error while trying to delete garage place with name '{}'", garagePlace, e);
        }
        return isDeleted;
    }

}
