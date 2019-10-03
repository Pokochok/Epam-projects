package by.epam.touragency.command;

import by.epam.touragency.command.impl.*;

/**
 * Enum for defining command, received from request
 */
public enum CommandEnum {
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

;

    ActionCommand command;

    /**
     * Returns command, which was created
     * @return command
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
