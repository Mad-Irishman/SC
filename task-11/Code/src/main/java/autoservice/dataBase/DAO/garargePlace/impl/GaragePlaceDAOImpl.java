package autoservice.dataBase.DAO.garargePlace.impl;

import autoservice.dataBase.DAO.garargePlace.GaragePlaceDAO;
import autoservice.dataBase.connection.DatabaseConnection;
import autoservice.models.garagePlace.GaragePlace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class GaragePlaceDAOImpl implements GaragePlaceDAO {
    private static final Logger logger = LoggerFactory.getLogger(GaragePlaceDAOImpl.class);

    @Override
    public void addGaragePlace(GaragePlace garagePlace) {
        String query = "INSERT INTO garage_places (place_number, is_occupied) VALUES (?, ?)";
        logger.info("Adding garage_place: {}", garagePlace);

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, garagePlace.getPlaceNumber());
            preparedStatement.setBoolean(2, garagePlace.isOccupied());
            preparedStatement.executeUpdate();
            logger.info("Place add successful: {}", garagePlace);

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
