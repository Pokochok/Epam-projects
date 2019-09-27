package by.epam.touragency.command.impl;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.UpdateTourLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.Validation;

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.TOUR_NAME_EXISTS_MSG_KEY;
import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

public class ChangeTourNameCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String newTourName = content.getParameter(PARAM_NAME_NEW_TOUR_NAME);
        String tourName = content.getParameter(PARAM_NAME_TOUR_NAME);
        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString()
                : content.getLocalName();

        if (!Validation.validateTourStringItems(newTourName) || !Validation.validateTourStringItems(tourName) ||
                !Validation.validateId(content.getParameter(PARAM_NAME_TOUR_ID))) {
            return ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH);
        }

        int tourId = Integer.parseInt(content.getParameter(PARAM_NAME_TOUR_ID));
        try {
            if (UpdateTourLogic.updateTourName(newTourName, tourId)) {
                content.setAttribute(ATTR_NAME_TOUR_NAME, newTourName);
            } else {
                content.setAttribute(ATTR_NAME_TOUR_NAME, tourName);
                content.setAttribute(ATTR_NAME_ERROR_TOUR_NAME_EXISTS_MSG,
                        MessageManager.getProperty(TOUR_NAME_EXISTS_MSG_KEY, new Locale(language)));
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH);
    }
}
