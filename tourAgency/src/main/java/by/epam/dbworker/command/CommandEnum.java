package by.epam.dbworker.command;

import by.epam.dbworker.command.impl.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTRATION{
        {
            this.command = new RegisterCommand();
        }
    },
    BACK_TO_MAIN{
        {
            this.command = new BackToMainCommand();
        }
    },
    TO_ABOUT_COMPANY{
        {
            this.command = new ToAboutCompany();
        }
    },
    TO_TOUR_SEARCH{
        {
            this.command = new ToTourSearch();
        }
    },
    TO_TOURS{
        {
            this.command = new ToTours();
        }
    },
    TO_USER_PROFILE{
        {
            this.command = new ToUserProfile();
        }
    },
    TO_REGISTRATION{
        {
            this.command = new ToRegistration();
        }
    },
    TO_LOGIN{
        {
            this.command = new ToLogin();
        }
    },
    CHANGE_PHONE_NUMBER{
        {
            this.command = new ChangePhoneNumber();
        }
    },
    CHANGE_PASSWORD{
        {
            this.command = new ChangePassword();
        }
    },
    TO_TOUR_OVERVIEW{
        {
            this.command = new ToTourOverview();
        }
    },
    TO_TOUR_REGISTRATION{
        {
            this.command = new ToTourRegistration();
        }
    },
    TOUR_REGISTER_COMMAND{
        {
            this.command = new TourRegisterCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
