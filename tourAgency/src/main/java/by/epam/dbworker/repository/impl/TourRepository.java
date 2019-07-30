package by.epam.dbworker.repository.impl;

import by.epam.dbworker.connectionpool.ProxyConnectionPool;
import by.epam.dbworker.entity.Tour;
import by.epam.dbworker.repository.Repository;
import by.epam.dbworker.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class TourRepository implements Repository<Tour> {
    private static final Logger LOGGER = LogManager.getLogger();

    private TourRepository() {
    }

    private static class RepositoryHolder{
        private static final TourRepository REPOSITORY = new TourRepository();
    }

    public static TourRepository getInstance(){
        return RepositoryHolder.REPOSITORY;
    }

    @Override
    public void add(Tour item, Specification specification) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("New tour was added");
        } catch (SQLException e) {
            LOGGER.error("Error in adding tour: ", e);
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void update(Tour entity, Specification specification) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("Tour was updated");
        } catch (SQLException e) {
            LOGGER.error("Error in updating tour: ", e);
            // TODO: 24/06/2019 Exception
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void remove(Tour entity, Specification specification) {
        try (PreparedStatement preparedStatement = ProxyConnectionPool.getInstance().takeConnection()
                .prepareStatement(specification.sqlQuery())) {
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error in removing: ", e);
        }
    }

    @Override
    public Set<Tour> query(Specification specification) {
        Set<Tour> tourSet = new HashSet<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tour tour = new Tour();
                tour.setId(resultSet.getInt("id"));
                tour.setTourName(resultSet.getString("tour_name"));
                tour.setDepartureDate(resultSet.getLong("departure_date"));
                tour.setArrivalDate(resultSet.getLong("arrival_date"));
                tour.setDepartureCity(resultSet.getString("departure_city"));
                tour.setArrivalCity(resultSet.getString("arrival_city"));
                tour.setArrivalCountry(resultSet.getString("arrival_country"));
                tour.setHotel(resultSet.getString("hotel"));
                tour.setNutrition(resultSet.getString("nutrition"));
                tour.setAdultsNumber(resultSet.getInt("adults_number"));
                tour.setChildrenNumber(resultSet.getInt("children_number"));
                tour.setPrice(resultSet.getBigDecimal("price"));
                tour.setStatus(resultSet.getString("status"));
                tourSet.add(tour);
            }
        } catch (SQLException e) {
            LOGGER.error("Error in query: ", e);
            // TODO: 24/06/2019 Exception
        } finally {
            if (resultSet != null) {
                closeResultSet(resultSet);
            }
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
        return tourSet;
    }
}
