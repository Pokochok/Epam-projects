package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.TO_INF_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ToInfCommand {
    @Autowired
    private MessageManager messageManager;

    @RequestMapping("/to_inf")
    public ModelAndView execute(ModelAndView modelAndView, @RequestParam(PARAM_NAME_MSG_KEY) String msgKey) {
        String language = modelAndView.getModel().get(ATTR_NAME_LANGUAGE) != null ?
                modelAndView.getModel().get(ATTR_NAME_LANGUAGE).toString() : EN_LOCALE;
        modelAndView.clear();
        modelAndView.addObject(ATTR_NAME_RESULT_INF, messageManager.getProperty(msgKey, new Locale(language)));
        modelAndView.setViewName(ConfigurationManager.getProperty(TO_INF_PAGE_PATH));
        return modelAndView;
    }
}
