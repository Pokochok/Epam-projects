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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class ChangeEmailCommand {
    @Autowired
    private UpdateUserLogic updateUserLogic;

    @Autowired
    private Validation validation;

    @Autowired
    private MessageManager messageManager;

    @Secured({"ROLE_ADMIN", "ROLE_AGENT", "ROLE_CLIENT"})
    @PostMapping("/change_email")
    public ModelAndView execute(
            @RequestParam(value = ParameterConstant.PARAM_NAME_NEW_EMAIL) String email,
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
        String login = userDetails.getUsername();
        User user = userDetails.getUser();
        if (!validation.validateEmail(email) || !validation.validateLogin(login)) {
            return modelAndView;
        }
        if (updateUserLogic.updateEmail(user, email, login)) {
            modelAndView.addObject(ParameterConstant.ATTR_NAME_USER_EMAIL, email);
        } else {
            modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_EMAIL_EXISTS,
                    messageManager.getProperty(PageMsgConstant.EMAIL_EXISTS_MSG_KEY, language));
        }
        return modelAndView;
    }
}
