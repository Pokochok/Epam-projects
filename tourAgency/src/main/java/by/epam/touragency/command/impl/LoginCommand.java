package by.epam.touragency.command.impl;

import by.epam.touragency.entity.User;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.LoginLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class LoginCommand {

    @Autowired
    private LoginLogic loginLogic;

    @PostMapping(value = "/login")
    public ModelAndView execute(){
        return new ModelAndView(ConfigurationManager.getProperty(HOME_PAGE_PATH));
    }
//    public ModelAndView execute(
//            @RequestParam(value = PARAM_NAME_LOGIN) String login,
//            @RequestParam(value = PARAM_NAME_PASSWORD) String password,
//            @RequestParam(value = ATTR_NAME_LANGUAGE, required = false) String language
//    ) throws CommandException {
//        if (language == null){
//            language = EN_LOCALE;
//        }
//        ModelAndView modelAndView = new ModelAndView();
//        String page = null;
//        User user = null;
//        try {
//            user = loginLogic.checkedUser(login, password);
//        } catch (LogicException e) {
//            throw new CommandException(e);
//        }
//        if (user != null && user.getId() != 0) {
//            modelAndView.addObject(ATTR_NAME_USER_ID, user.getId());
//            modelAndView.addObject(ATTR_NAME_USER_NAME, user.getName());
//            modelAndView.addObject(ATTR_NAME_USER_SURNAME, user.getSurname());
//            modelAndView.addObject(ATTR_NAME_USER_EMAIL, user.getEmail());
//            modelAndView.addObject(ATTR_NAME_USER_PHONE_NUMBER, user.getPhoneNumber());
//            modelAndView.addObject(ATTR_NAME_USER_LOGIN, user.getLogin());
//            modelAndView.addObject(ATTR_NAME_USER_STATUS, user.getStatus());
//            modelAndView.addObject(ATTR_NAME_USER_ROLE, user.getRole().toString());
//            page = ConfigurationManager.getProperty(HOME_PAGE_PATH);
//        } else {
//            LOGGER.debug("Incorrect login or password");
//            modelAndView.addObject(ATTR_NAME_ERROR_LOGIN,
//                    MessageManager.getProperty(LOGIN_ERROR_MSG_KEY, new Locale(language)));
//            page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
//        }
//        modelAndView.setViewName(page);
//        return modelAndView;
//    }


}
