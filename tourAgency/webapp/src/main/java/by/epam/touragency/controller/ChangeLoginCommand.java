package by.epam.touragency.controller;

import by.epam.touragency.entity.User;
import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.logic.UpdateUserLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.PageMsgConstant;
import by.epam.touragency.util.ParameterConstant;
import by.epam.touragency.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class ChangeLoginCommand {
    @Autowired
    private UpdateUserLogic updateUserLogic;

    @Autowired
    private MessageManager messageManager;

    @Autowired
    private Validation validation;

    @Secured({"ROLE_ADMIN", "ROLE_AGENT", "ROLE_CLIENT"})
    @PostMapping("/change_login")
    public ModelAndView execute(
            @RequestParam(value = ParameterConstant.PARAM_NAME_NEW_LOGIN) String login,
            @SessionAttribute(value = ParameterConstant.ATTR_NAME_LANGUAGE, required = false) Locale language
    ) {
        if (language == null) {
            language = new Locale(ParameterConstant.EN_LOCALE);
        }
        ModelAndView modelAndView = new ModelAndView(ConfigurationManager.getProperty(PageMsgConstant.USER_PROFILE_PAGE_PATH));
        if (!updateUserLogic.checkPrincipal()) {
            return modelAndView;
        }
        UserPrincipal userDetails = updateUserLogic.getUserPrincipal();
        User user = userDetails.getUser();
        String email = userDetails.getUserEmail();
        if (!validation.validateLogin(login)) {
            modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_CHANGE_LOGIN,
                    messageManager.getProperty(PageMsgConstant.CHANGE_LOGIN_ERROR_MSG_KEY, language));
            return modelAndView;
        }

        if (updateUserLogic.updateLogin(user, login, email)) {
            modelAndView.addObject(ParameterConstant.ATTR_NAME_USER_LOGIN, login);
        } else {
            modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_LOGIN_EXISTS,
                    messageManager.getProperty(PageMsgConstant.LOGIN_EXISTS_MSG_KEY, language));
        }
        return modelAndView;
    }
}
