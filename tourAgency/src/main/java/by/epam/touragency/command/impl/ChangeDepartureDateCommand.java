package by.epam.touragency.command.impl;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.UpdateTourLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.Validation;

import java.util.Date;
import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.LOGGER;
import static by.epam.touragency.util.PageMsgConstant.DATE_ERROR_MSG_KEY;
import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

public class ChangeDepartureDateCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                : content.getLocalName();

        if (!Validation.validateId(content.getParameter(PARAM_NAME_TOUR_ID))){
            return ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH);
        }
        int tourId = Integer.parseInt(content.getParameter(PARAM_NAME_TOUR_ID));
        long    newDepartureDate = Validation.validateDate(content.getParameter(PARAM_NAME_NEW_DEPARTURE_DATE));
        long    arrivalDate = Validation.validateDate(content.getParameter(PARAM_NAME_ARRIVAL_DATE));
        if (newDepartureDate == -1 || arrivalDate == -1){
            LOGGER.debug("Error in date parsing");
        }

        try {
            if (newDepartureDate < arrivalDate && new Date().before(new Date(newDepartureDate))) {
                UpdateTourLogic.updateDepartureDate(newDepartureDate, tourId);
                content.setAttribute(ATTR_NAME_DEPARTURE_DATE, Validation.dateToFormat(newDepartureDate));
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
