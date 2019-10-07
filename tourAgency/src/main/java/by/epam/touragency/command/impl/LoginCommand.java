package by.epam.touragency.command.impl;

import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.logic.LoginLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.*;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class LoginCommand {

    @Autowired
    private LoginLogic loginLogic;

    @RequestMapping(value = "/login_setter")
    public ModelAndView execute(@RequestParam(value = "language", required = false) Locale language
                                ) throws CommandException {
        if (language == null){
            language = new Locale("en");
        }
        ModelAndView modelAndView = new ModelAndView();
        String page = null;
        UserPrincipal userPrincipal = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (!(securityContext.getAuthentication() instanceof AnonymousAuthenticationToken)) {
              userPrincipal = (UserPrincipal)securityContext.getAuthentication().getPrincipal();
        }

        if (userPrincipal != null && userPrincipal.getUserId() != 0) {
            modelAndView.addObject(ATTR_NAME_USER_ID, userPrincipal.getUserId());
            modelAndView.addObject(ATTR_NAME_USER_NAME, userPrincipal.getRealUserName());
            modelAndView.addObject(ATTR_NAME_USER_SURNAME, userPrincipal.getUserSurname());
            modelAndView.addObject(ATTR_NAME_USER_EMAIL, userPrincipal.getUserEmail());
            modelAndView.addObject(ATTR_NAME_USER_PHONE_NUMBER, userPrincipal.getUserPhoneNumber());
            modelAndView.addObject(ATTR_NAME_USER_LOGIN, userPrincipal.getUsername());
            modelAndView.addObject(ATTR_NAME_USER_STATUS, userPrincipal.getUserStatus());
            modelAndView.addObject(ATTR_NAME_USER_ROLE, userPrincipal.getUserRole().toString());
            page = ConfigurationManager.getProperty(HOME_PAGE_PATH);
        } else {
            LOGGER.debug("Incorrect login or password");
            modelAndView.addObject(ATTR_NAME_ERROR_LOGIN,
                    MessageManager.getProperty(LOGIN_ERROR_MSG_KEY, language));
            page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
        }
        modelAndView.setViewName(page);
        return modelAndView;
    }


}
