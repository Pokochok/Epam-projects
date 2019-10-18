package by.epam.touragency.command.impl;

import by.epam.touragency.entity.User;
import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.UpdateUserLogic;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
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

import static by.epam.touragency.util.PageMsgConstant.CHANGE_USER_NAME_ERROR_MSG_KEY;
import static by.epam.touragency.util.PageMsgConstant.USER_PROFILE_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ChangeNameCommand {
    @Autowired
    private UpdateUserLogic updateUserLogic;

    @Autowired
    private Validation validation;

    @Autowired
    private MessageManager messageManager;

    @Secured({"ROLE_ADMIN", "ROLE_AGENT", "ROLE_CLIENT"})
    @PostMapping("/change_user_name")
    public ModelAndView execute(
            @RequestParam(value = PARAM_NAME_NEW_NAME) String newName,
            @SessionAttribute(value = ATTR_NAME_LANGUAGE, required = false) Locale language
    ) throws CommandException {
        if (language == null){
            language = new Locale(EN_LOCALE);
        }
        String login = null;
        String role = null;
        User user = null;
        if(updateUserLogic.checkPrincipal()) {
            UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            role = userDetails.getUserRole().toString();
            login = userDetails.getUsername();
            user = userDetails.getUser();
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ConfigurationManager.getProperty(USER_PROFILE_PAGE_PATH));
        if (!validation.validateName(newName)) {
            modelAndView.addObject(ATTR_NAME_ERROR_CHANGE_USER_NAME,
                    messageManager.getProperty(CHANGE_USER_NAME_ERROR_MSG_KEY, language));
            return modelAndView;
        }
        try {
            updateUserLogic.updateName(role, login, newName);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        if (user != null) {
            user.setName(newName);
        }
        modelAndView.addObject(ATTR_NAME_USER_NAME, newName);
        return modelAndView;
    }
}
