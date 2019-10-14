package by.epam.touragency.command.impl;

import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.MatchOfUniqueFieldsDetector;
import by.epam.touragency.logic.TourRegistrationLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class TourRegisterCommand {
    @Autowired
    private  TourRegistrationLogic tourRegistrationLogic;

    @Autowired
    private MessageManager messageManager;

    @Autowired
    private Validation validation;

    @Secured("ROLE_ADMIN")
    @PostMapping("/tour_register_command")
    public ModelAndView execute(
            @RequestParam(value = PARAM_NAME_TOUR_NAME) String tourName,
            @RequestParam(value = PARAM_NAME_DEPARTURE_CITY) String departureCity,
            @RequestParam(value = PARAM_NAME_ARRIVAL_CITY) String arrivalCity,
            @RequestParam(value = ATTR_NAME_ARRIVAL_COUNTRY) String arrivalCountry,
            @RequestParam(value = ATTR_NAME_HOTEL) String hotel,
            @RequestParam(value = ATTR_NAME_NUTRITION) String nutrition,
            @RequestParam(value = PARAM_NAME_TOUR_STATUS) String isAvailable,
            @RequestParam(value = ATTR_NAME_LANGUAGE, required = false)String language,
            @RequestParam(value = ATTR_NAME_CHILDREN_NUMBER) String childrenNumber,
            @RequestParam(value = ATTR_NAME_ADULTS_NUMBER) String adultsNumber,
            @RequestParam(value = ATTR_NAME_PRICE) String price,
            @RequestParam(value = PARAM_NAME_DEPARTURE_DATE) String departureDateStr,
            @RequestParam(value = PARAM_NAME_ARRIVAL_DATE) String arrivalDateStr
    ) throws CommandException {
        String page = null;
        boolean isValid = true;
        if (language == null){
            language = EN_LOCALE;
        }

        if (!tourRegistrationLogic.isValidData(tourName, departureCity, arrivalCity, arrivalCountry, hotel, nutrition,
                childrenNumber, adultsNumber, price)) {
            return new ModelAndView(ConfigurationManager.getProperty(TO_TOUR_REGISTRATION_PAGE_PATH));
        }

        ModelAndView modelAndView = new ModelAndView();
        int numberOfAdults = Integer.parseInt(adultsNumber);
        int numberOfChildren = Integer.parseInt(childrenNumber);
        BigDecimal priceBD = new BigDecimal(price);
        long departureDate = validation.validateDate(departureDateStr);
        long arrivalDate = validation.validateDate(arrivalDateStr);
        if (!new Date().before(new Date(departureDate)) || !new Date(departureDate).before(new Date(arrivalDate))) {
            isValid = false;
            LOGGER.info("Invalid date entered");
            modelAndView.addObject(ATTR_NAME_ERROR_DATE,
                    messageManager.getProperty(DATE_ERROR_MSG_KEY, new Locale(language)));
        }

        try {
            if (MatchOfUniqueFieldsDetector.isExistsTourName(tourName)){
                isValid = false;
                LOGGER.info("Invalid data entered. Tour name exists");
                modelAndView.addObject(ATTR_NAME_ERROR_TOUR_NAME_EXISTS,
                        messageManager.getProperty(TOUR_NAME_EXISTS_ERROR_MSG_KEY, new Locale(language)));
            }
            if (isValid) {
                tourRegistrationLogic.addTour(tourName, departureDate, arrivalDate, departureCity, arrivalCity, arrivalCountry, hotel,
                        nutrition, numberOfAdults, numberOfChildren, priceBD, isAvailable);
                modelAndView.addObject(ATTR_NAME_MSG_KEY,  REGISTRATION_SUCCESS_MSG_KEY);
                page = ConfigurationManager.getProperty(INF_PAGE_FLAG);
                modelAndView.setViewName(page);
            } else {
                page = ConfigurationManager.getProperty(TO_TOUR_REGISTRATION_PAGE_PATH);
                modelAndView.setViewName(page);
                return modelAndView;
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return new ToInfCommand().execute(modelAndView);
    }
}
