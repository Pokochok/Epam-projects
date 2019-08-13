package by.epam.tourAgency.command.impl;

import by.epam.tourAgency.command.ActionCommand;
import by.epam.tourAgency.controller.SessionRequestContent;
import by.epam.tourAgency.entity.Tour;
import by.epam.tourAgency.exception.CommandException;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.logic.ToPageWithListLogic;
import by.epam.tourAgency.resource.ConfigurationManager;


import java.util.ArrayList;
import java.util.List;

import static by.epam.tourAgency.util.PageMsgConstant.TO_TOURS_PAGE_PATH;
import static by.epam.tourAgency.util.ParameterConstant.*;

public class ToToursCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        int newIndex;
        String userRole = String.valueOf(content.getSessionAttribute(ATTR_NAME_USER_ROLE));

        if (content.getParameter(ATTR_NAME_INDEX) == null) {
            newIndex = 1;
        } else {
            newIndex = (int) (Double.parseDouble(content.getParameter(ATTR_NAME_INDEX)) +
                                Integer.parseInt(content.getParameter(ATTR_NAME_CHANGE_PAGE)));
        }

        List<Tour> tourSet = null;
        try {
            tourSet = new ArrayList<>(ToPageWithListLogic.getTourSet(userRole));
        } catch (LogicException e) {
            throw new CommandException(e);
        }

        content.setAttribute(ATTR_NAME_TOURS_PER_PAGE, NUMBER_TOURS_PER_PAGE);
        content.setAttribute(ATTR_NAME_START_TOURS_INDEX, (newIndex - 1) * NUMBER_TOURS_PER_PAGE);
        content.setAttribute(ATTR_NAME_INDEX, newIndex);
        content.setAttribute(ATTR_NAME_TOUR_LIST, tourSet);

        content.setAttribute(ATTR_NAME_NUMBER_OF_TOURS, tourSet.size());
        return ConfigurationManager.getProperty(TO_TOURS_PAGE_PATH);
    }
}
