package by.epam.tourAgency.command.impl;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.exception.CommandException;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.logic.UpdateTourLogic;
import by.epam.tourAgency.resource.ConfigurationManager;
import by.epam.tourAgency.util.Validation;

import static by.epam.tourAgency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;
import static by.epam.tourAgency.util.ParameterConstant.*;

public class ChangeHotelCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String newHotel = content.getParameter(PARAM_NAME_NEW_HOTEL);

        if (!Validation.validateTourStringItems(newHotel) || !Validation.validateId(content.getParameter(PARAM_NAME_TOUR_ID))) {
            return ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH);
        }

        int tourId = Integer.parseInt(content.getParameter(PARAM_NAME_TOUR_ID));
        try {
            UpdateTourLogic.updateHotel(newHotel, tourId);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        content.setAttribute(ATTR_NAME_HOTEL, newHotel);
        return ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH);
    }
}
