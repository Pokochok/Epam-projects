package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.entity.Tour;
import by.epam.dbworker.repository.impl.TourRepository;
import by.epam.dbworker.resource.ConfigurationManager;
import by.epam.dbworker.resource.MessageManager;
import by.epam.dbworker.specification.Specification;
import by.epam.dbworker.specification.impl.tourspecification.AddTourSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TourRegisterCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    private final String ATTR_NAME_ERROR_DATE = "errorInvalidDate";
    private final String DATE_ERROR_MSG_KEY = "tourRegistration.message.invalidDate";
    @Override
    public String execute(SessionRequestContent request) {
        String page = null;
        boolean isValid = true;
        String tourName = request.getParameter("tourName");
        String departureCity = request.getParameter("departureCity");
        String arrivalCity = request.getParameter("arrivalCity");
        String arrivalCountry = request.getParameter("arrivalCountry");
        String hotel = request.getParameter("hotel");
        String nutrition = request.getParameter("nutrition");
        int numberOfAdults = Integer.parseInt(request.getParameter("numberOfAdults"));
        int numberOfChildren = Integer.parseInt(request.getParameter("numberOfChildren"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String isAvailable = request.getParameter("isAvailable");
        String language = request.getSessionAttribute("language") != null ?
                request.getSessionAttribute("language").toString() : request.getLocalName();
        long departureDate = 0;
        long arrivalDate = 0;

        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        try {
            departureDate = formatForDateNow.parse(request.getParameter("departureDate")).getTime();
            arrivalDate = formatForDateNow.parse(request.getParameter("arrivalDate")).getTime();
        } catch (ParseException e) {
            LOGGER.debug("Error in date parsing: ", e);
            isValid = false;
            request.setAttribute(ATTR_NAME_ERROR_DATE,
                    MessageManager.getProperty(DATE_ERROR_MSG_KEY, new Locale(language)));
        }

        if(new Date().after(new Date(departureDate)) || new Date().after(new Date(arrivalDate)) ||
                new Date(departureDate).after(new Date(arrivalDate))){
            isValid = false;
            request.setAttribute(ATTR_NAME_ERROR_DATE,
                    MessageManager.getProperty(DATE_ERROR_MSG_KEY, new Locale(language)));
        }

// TODO: 30/07/2019 Добавить валидацию по всем командам из пакеда util

        if (isValid) {
            Tour tour = new Tour(tourName, departureDate, arrivalDate, departureCity, arrivalCity, arrivalCountry, hotel,
                    nutrition, numberOfAdults, numberOfChildren, price, isAvailable);
            Specification specification = new AddTourSpecification(tour);
            TourRepository.getInstance().add(tour, specification);
            page = ConfigurationManager.getProperty("path.page.tours"); // FIXME: 30/07/2019 поменять на уведомление
        } else {
            page = ConfigurationManager.getProperty("path.page.tourRegistration");
        }
        return page;
    }
}
