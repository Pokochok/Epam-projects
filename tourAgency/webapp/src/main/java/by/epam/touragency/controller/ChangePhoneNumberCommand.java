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
public class ChangePhoneNumberCommand {
    @Autowired
    private UpdateUserLogic updateUserLogic;

    @Autowired
    private Validation validation;

    @Autowired
    private MessageManager messageManager;

    @Secured({"ROLE_ADMIN", "ROLE_AGENT", "ROLE_CLIENT"})
    @PostMapping("/change_phone_number")
    public ModelAndView execute(
            @RequestParam(value = ParameterConstant.PARAM_NAME_NEW_PHONE_NUMBER) String newPhoneNumber,
            @SessionAttribute(value = ParameterConstant.ATTR_NAME_LANGUAGE, required = false) Locale language
    ) {
        if (language == null) {
            language = new Locale(ParameterConstant.EN_LOCALE);
        }
        String login = null;
        String role = null;
        User user = null;
        if (updateUserLogic.checkPrincipal()) {
            UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            role = userDetails.getUserRole().toString();
            login = userDetails.getUsername();
            user = userDetails.getUser();
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ConfigurationManager.getProperty(PageMsgConstant.USER_PROFILE_PAGE_PATH));
        if (!validation.validatePhoneNumber(newPhoneNumber)) {
            modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_CHANGE_PN,
                    messageManager.getProperty(PageMsgConstant.CHANGE_PN_ERROR_MSG_KEY, language));
            return modelAndView;
        }
        if (updateUserLogic.updatePhoneNumber(role, newPhoneNumber, login)) {
            modelAndView.addObject(ParameterConstant.ATTR_NAME_USER_PHONE_NUMBER, newPhoneNumber);
            if (user != null) {
                user.setPhoneNumber(newPhoneNumber);
            }
        } else {
            modelAndView.addObject(ParameterConstant.ATTR_NAME_ERROR_PN_EXISTS,
                    messageManager.getProperty(PageMsgConstant.PHONE_NUMBER_EXISTS_MSG_KEY, language));
        }
        return modelAndView;
    }
}
