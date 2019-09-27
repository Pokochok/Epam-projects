package by.epam.touragency.command.impl;

import by.epam.touragency.command.ActionCommand;
import by.epam.touragency.controller.SessionRequestContent;
import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.TO_INF_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

public class ToInfCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        String msgKey = content.getParameter(PARAM_NAME_MSG_KEY);
        String language = content.getSessionAttribute(ATTR_NAME_LANGUAGE) != null ?
                content.getSessionAttribute(ATTR_NAME_LANGUAGE).toString() : content.getLocalName();

        content.setAttribute(ATTR_NAME_RESULT_INF, MessageManager.getProperty(msgKey, new Locale(language)));
        return ConfigurationManager.getProperty(TO_INF_PAGE_PATH);
    }
}
