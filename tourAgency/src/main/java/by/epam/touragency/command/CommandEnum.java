package by.epam.touragency.command;

import by.epam.touragency.command.impl.*;

/**
 * Enum for defining command, received from request
 */
public enum CommandEnum {
//    LOGIN {
//        {
//            this.command = new LoginCommand();
//        }
//    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegisterCommand();
        }
    },
    BACK_TO_MAIN {
        {
            this.command = new BackToMainCommand();
        }
    },
    TO_ABOUT_COMPANY {
        {
            this.command = new ToAboutCompanyCommand();
        }
    },
    TO_TOURS {
        {
            this.command = new ToToursCommand();
        }
    },
    TO_USER_PROFILE {
        {
            this.command = new ToUserProfileCommand();
        }
    },
    TO_REGISTRATION {
        {
            this.command = new ToRegistrationCommand();
        }
    },
    TO_LOGIN {
        {
            this.command = new ToLoginCommand();
        }
    },
    CHANGE_PHONE_NUMBER {
        {
            this.command = new ChangePhoneNumberCommand();
        }
    },
    CHANGE_PASSWORD {
        {
            this.command = new ChangePasswordCommand();
        }
    },
    CHANGE_EMAIL {
        {
            this.command = new ChangeEmailCommand();
        }
    },
    CHANGE_LOGIN {
        {
            this.command = new ChangeLoginCommand();
        }
    },
    CHANGE_USER_NAME {
        {
            this.command = new ChangeNameCommand();
        }
    },
    CHANGE_USER_SURNAME {
        {
            this.command = new ChangeSurnameCommand();
        }
    },
    TO_TOUR_OVERVIEW {
        {
            this.command = new ToTourOverviewCommand();
        }
    },
    TO_TOUR_REGISTRATION {
        {
            this.command = new ToTourRegistrationCommand();
        }
    },
    CHANGE_TOUR_NAME {
        {
            this.command = new ChangeTourNameCommand();
        }
    },
    CHANGE_ARRIVAL_COUNTRY {
        {
            this.command = new ChangeArrivalCountryCommand();
        }
    },
    CHANGE_ARRIVAL_CITY {
        {
            this.command = new ChangeArrivalCityCommand();
        }
    },
    CHANGE_DEPARTURE_CITY {
        {
            this.command = new ChangeDepartureCityCommand();
        }
    },
    CHANGE_DEPARTURE_DATE {
        {
            this.command = new ChangeDepartureDateCommand();
        }
    },
    CHANGE_ARRIVAL_DATE {
        {
            this.command = new ChangeArrivalDateCommand();
        }
    },
    CHANGE_HOTEL{
        {
            this.command = new ChangeHotelCommand();
        }
    },
    CHANGE_NUTRITION{
        {
            this.command = new ChangeNutritionCommand();
        }
    },
    CHANGE_ADULTS_NUMBER{
        {
            this.command = new ChangeAdultsNumberCommand();
        }
    },
    CHANGE_CHILDREN_NUMBER{
        {
            this.command = new ChangeChildrenNumberCommand();
        }
    },
    CHANGE_PRICE{
        {
            this.command = new ChangePriceCommand();
        }
    },
    CHANGE_STATUS{
        {
            this.command = new ChangeStatusCommand();
        }
    },
    TO_TICKET_REGISTRATION {
        {
            this.command = new ToTicketRegistrationCommand();
        }
    },
    TOUR_REGISTER_COMMAND {
        {
            this.command = new TourRegisterCommand();
        }
    },
    TICKET_REGISTER_COMMAND {
        {
            this.command = new TicketRegisterCommand();
        }
    },
    TO_TICKETS {
        {
            this.command = new ToTicketsCommand();
        }
    },
    TO_BOOKING {
        {
            this.command = new ToBookingCommand();
        }
    },
    TO_ORDERS{
        {
            this.command = new ToOrdersCommand();
        }
    },
    PAY_ORDER{
        {
            this.command = new PayOrderCommand();
        }
    },
    REMOVE_ORDER{
        {
            this.command = new RemoveOrderCommand();
        }
    },
    BOOKING {
        {
            this.command = new BookingCommand();
        }
    },
    TO_INF{
        {
            this.command = new ToInfCommand();
        }
    };

    ActionCommand command;

    /**
     * Returns command, which was created
     * @return command
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
