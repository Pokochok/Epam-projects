package by.epam.dbworker.command.impl;

import by.epam.dbworker.command.ActionCommand;
import by.epam.dbworker.controller.SessionRequestContent;
import by.epam.dbworker.entity.Tour;
import by.epam.dbworker.repository.Repository;
import by.epam.dbworker.repository.impl.TourRepository;
import by.epam.dbworker.resource.ConfigurationManager;
import by.epam.dbworker.specification.Specification;
import by.epam.dbworker.specification.impl.tourspecification.FindAllToursSpecification;

import java.util.Set;

public class ToTours implements ActionCommand {
    private static final String TO_TOURS_PAGE_PATH = "path.page.tours";

    private final int NUMBER_TOURS_PER_PAGE = 8;

    private final String ATTR_NAME_TOURS_PER_PAGE = "toursPerPage";
    private final String ATTR_NAME_INDEX = "index";
    private final String ATTR_NAME_CHANGE_PAGE = "changePage";
    private final String ATTR_NAME_START_INDEX = "startIndexOfTours";
    private final String ATTR_NAME_TOUR_LIST = "tourList";
    private final String ATTR_NAME_NUMBER_OF_TOURS = "numberOfTours";


    @Override
    public String execute(SessionRequestContent request) {
        int toursPerPage = NUMBER_TOURS_PER_PAGE;
        int newIndex;

        if (request.getParameter(ATTR_NAME_INDEX) == null) {
            newIndex = 1;
        } else {
            newIndex = Integer.parseInt(request.getParameter(ATTR_NAME_INDEX)) +
                    Integer.parseInt(request.getParameter(ATTR_NAME_CHANGE_PAGE)); // FIXME: 24/07/2019 
        }

        Specification specification = new FindAllToursSpecification();
        TourRepository repository = TourRepository.getInstance();
        Set<Tour> tourSet = repository.query(specification);
        
        request.setAttribute(ATTR_NAME_TOURS_PER_PAGE, toursPerPage);
        request.setAttribute(ATTR_NAME_START_INDEX, (newIndex - 1) * toursPerPage);
        request.setAttribute(ATTR_NAME_INDEX, newIndex);
        request.setAttribute(ATTR_NAME_TOUR_LIST, tourSet);

        request.setAttribute(ATTR_NAME_NUMBER_OF_TOURS, tourSet.size());
        return ConfigurationManager.getProperty(TO_TOURS_PAGE_PATH);
    }
}
