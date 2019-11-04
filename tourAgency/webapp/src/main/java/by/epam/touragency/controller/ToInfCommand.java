package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import by.epam.touragency.resource.MessageManager;
import by.epam.touragency.util.PageMsgConstant;
import by.epam.touragency.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
@PreAuthorize("permitAll()")
public class ToInfCommand {
    @Autowired
    private MessageManager messageManager;

    @RequestMapping("/to_inf")
    public ModelAndView execute(ModelAndView modelAndView, @RequestParam(ParameterConstant.PARAM_NAME_MSG_KEY) String msgKey) {
        String language = modelAndView.getModel().get(ParameterConstant.ATTR_NAME_LANGUAGE) != null ?
                modelAndView.getModel().get(ParameterConstant.ATTR_NAME_LANGUAGE).toString() : ParameterConstant.EN_LOCALE;
        modelAndView.clear();
        modelAndView.addObject(ParameterConstant.ATTR_NAME_RESULT_INF, messageManager.getProperty(msgKey, new Locale(language)));
        modelAndView.setViewName(ConfigurationManager.getProperty(PageMsgConstant.TO_INF_PAGE_PATH));
        return modelAndView;
    }
}
