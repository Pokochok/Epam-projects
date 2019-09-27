package by.epam.touragency.command.impl;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.MatchOfUniqueFieldsDetector;
import by.epam.touragency.logic.TourRegistrationLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.Validation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;

public class TourRegisterCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String page = null;
        boolean isValid = true;
        String tourName = content.getParameter(PARAM_NAME_TOUR_NAME);
        String departureCity = content.getParameter(PARAM_NAME_DEPARTURE_CITY);
        String arrivalCity = content.getParameter(PARAM_NAME_ARRIVAL_CITY);
        String arrivalCountry = content.getParameter(ATTR_NAME_ARRIVAL_COUNTRY);
        String hotel = content.getParameter(ATTR_NAME_HOTEL);
        String nutrition = content.getParameter(ATTR_NAME_NUTRITION);
        String isAvailable = content.getParameter(PARAM_NAME_TOUR_STATUS);
        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString() : content.getLocalName();

        if (!Validation.validateName(tourName) || !Validation.validateTourStringItems(departureCity) ||
                !Validation.validateTourStringItems(arrivalCity) || !Validation.validateTourStringItems(arrivalCountry) ||
                !Validation.validateTourStringItems(hotel) || !Validation.validateNutrition(nutrition) ||
                !Validation.validateNumberOfPeople(content.getParameter(ATTR_NAME_CHILDREN_NUMBER)) ||
                !Validation.validateNumberOfPeople(content.getParameter(ATTR_NAME_ADULTS_NUMBER)) ||
                !Validation.validatePrice(content.getParameter(ATTR_NAME_PRICE))) {
            return ConfigurationManager.getProperty(TO_TOUR_REGISTRATION_PAGE_PATH);
        }

        int numberOfAdults = Integer.parseInt(content.getParameter(ATTR_NAME_ADULTS_NUMBER));
        int numberOfChildren = Integer.parseInt(content.getParameter(ATTR_NAME_CHILDREN_NUMBER));
        BigDecimal price = new BigDecimal(content.getParameter(ATTR_NAME_PRICE));
        long departureDate = Validation.validateDate(content.getParameter(PARAM_NAME_DEPARTURE_DATE));
        long arrivalDate = Validation.validateDate(content.getParameter(PARAM_NAME_ARRIVAL_DATE));
        if (!new Date().before(new Date(departureDate)) || !new Date(departureDate).before(new Date(arrivalDate))) {
            isValid = false;
            LOGGER.info("Invalid date entered");
            content.setAttribute(ATTR_NAME_ERROR_DATE,
                    MessageManager.getProperty(DATE_ERROR_MSG_KEY, new Locale(language)));
        }

        try {
            if (!(isValid = !MatchOfUniqueFieldsDetector.isExistsTourName(tourName))){
                LOGGER.info("Invalid date entered");
                content.setAttribute(ATTR_NAME_ERROR_TOUR_NAME_EXISTS,
                        MessageManager.getProperty(TOUR_NAME_EXISTS_ERROR_MSG_KEY, new Locale(language)));
            }
            if (isValid) {
                TourRegistrationLogic.addTour(tourName, departureDate, arrivalDate, departureCity, arrivalCity, arrivalCountry, hotel,
                        nutrition, numberOfAdults, numberOfChildren, price, isAvailable);
                content.setAttribute(ATTR_NAME_MSG_KEY,  REGISTRATION_SUCCESS_MSG_KEY);
                page = ConfigurationManager.getProperty(INF_PAGE_FLAG);
            } else {
                page = ConfigurationManager.getProperty(TO_TOUR_REGISTRATION_PAGE_PATH);
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
