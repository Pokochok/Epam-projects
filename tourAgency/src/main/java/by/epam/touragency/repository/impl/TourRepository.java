package by.epam.touragency.repository.impl;

import by.epam.touragency.connectionpool.ProxyConnectionPool;
import by.epam.touragency.entity.Tour;
import by.epam.touragency.exception.ConnectionPoolException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static by.epam.touragency.util.PageMsgConstant.LOGGER;

public class TourRepository implements Repository<Tour> {

    private TourRepository() {
    }

    private static class RepositoryHolder {
        private static final TourRepository REPOSITORY = new TourRepository();
    }

    public static TourRepository getInstance() {
        return RepositoryHolder.REPOSITORY;
    }

    @Override
    public void add(Tour item, Specification specification) throws RepositoryException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("New tour was added");
        } catch (SQLException e) {
            LOGGER.error("Error in adding tour: ");
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool");
            throw new RepositoryException(e);
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void update(Tour entity, Specification specification) throws RepositoryException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ProxyConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(specification.sqlQuery());
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
            LOGGER.info("Tour was updated");
        } catch (SQLException e) {
            LOGGER.error("Error in updating tour: ");
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool");
            throw new RepositoryException(e);
        } finally {
            if (preparedStatement != null) {
                closePreparedStatement(preparedStatement);
            }
            ProxyConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public void remove(Tour entity, Specification specification) throws RepositoryException {
        try (PreparedStatement preparedStatement = ProxyConnectionPool.getInstance().takeConnection()
                .prepareStatement(specification.sqlQuery())) {
            setPreparedStatementValues(preparedStatement, specification);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error in removing: ");
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool");
            throw new RepositoryException(e);
        }
    }

    @Override
    public Set<Tour> query(Specification specification) throws RepositoryException {
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
                Tour tour = new Tour.TourBuilder()
                .setId(resultSet.getInt("id"))
                .setTourName(resultSet.getString("tour_name"))
                .setDepartureDate(resultSet.getLong("departure_date"))
                .setArrivalDate(resultSet.getLong("arrival_date"))
                .setDepartureCity(resultSet.getString("departure_city"))
                .setArrivalCity(resultSet.getString("arrival_city"))
                .setArrivalCountry(resultSet.getString("arrival_country"))
                .setHotel(resultSet.getString("hotel"))
                .setNutrition(resultSet.getString("nutrition"))
                .setAdultsNumber(resultSet.getInt("adults_number"))
                .setChildrenNumber(resultSet.getInt("children_number"))
                .setPrice(resultSet.getBigDecimal("price"))
                .setStatus(resultSet.getString("status")).build();
                tourSet.add(tour);
            }
        } catch (SQLException e) {
            LOGGER.error("Error in query: ");
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Error in connection pool");
            throw new RepositoryException(e);
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
