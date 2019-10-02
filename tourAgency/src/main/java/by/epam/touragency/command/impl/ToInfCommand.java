package by.epam.touragency.command.impl;

import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.TO_INF_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ToInfCommand {
    @PostMapping("/to_inf")
    public ModelAndView execute(ModelAndView modelAndView) {
        String msgKey = modelAndView.getModel().get(PARAM_NAME_MSG_KEY).toString();
        String language = modelAndView.getModel().get(ATTR_NAME_LANGUAGE) != null ?
                modelAndView.getModel().get(ATTR_NAME_LANGUAGE).toString() : EN_LOCALE;
        modelAndView.clear();
        modelAndView.addObject(ATTR_NAME_RESULT_INF, MessageManager.getProperty(msgKey, new Locale(language)));
        modelAndView.setViewName(ConfigurationManager.getProperty(TO_INF_PAGE_PATH));
        return modelAndView;
    }
}
