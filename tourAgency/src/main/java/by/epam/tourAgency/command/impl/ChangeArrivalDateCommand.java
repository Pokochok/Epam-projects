package by.epam.tourAgency.command.impl;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.exception.CommandException;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.logic.UpdateTourLogic;
import by.epam.tourAgency.resource.ConfigurationManager;
import by.epam.tourAgency.resource.MessageManager;
import by.epam.tourAgency.util.Validation;

import java.util.Date;
import java.util.Locale;

import static by.epam.tourAgency.util.PageMsgConstant.DATE_ERROR_MSG_KEY;
import static by.epam.tourAgency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.tourAgency.util.PageMsgConstant.LOGGER;
import static by.epam.tourAgency.util.ParameterConstant.*;

public class ChangeArrivalDateCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                : content.getLocalName();

        if (!Validation.validateId(content.getParameter(PARAM_NAME_TOUR_ID))){
            return ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH);
        }
        int tourId = Integer.parseInt(content.getParameter(PARAM_NAME_TOUR_ID));
        long newArrivalDate = Validation.validateDate(content.getParameter(PARAM_NAME_NEW_ARRIVAL_DATE));
        long departureDate = Validation.validateDate(content.getParameter(PARAM_NAME_DEPARTURE_DATE));
        if (newArrivalDate == -1 || departureDate == -1){
            LOGGER.debug("Error in date parsing");
            content.setAttribute(ATTR_NAME_ERROR_DATE,
                    MessageManager.getProperty(DATE_ERROR_MSG_KEY, new Locale(language)));
        }

        try {
            if (departureDate < newArrivalDate) {
                UpdateTourLogic.updateArrivalDate(newArrivalDate, tourId);
                content.setAttribute(ATTR_NAME_ARRIVAL_DATE, Validation.dateToFormat(newArrivalDate));
            } else {
                content.setAttribute(ATTR_NAME_ERROR_DATE,
                        MessageManager.getProperty(DATE_ERROR_MSG_KEY, new Locale(language)));
            }
        }catch (LogicException e){
            throw new CommandException(e);
        }
        return ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH);
    }
}
