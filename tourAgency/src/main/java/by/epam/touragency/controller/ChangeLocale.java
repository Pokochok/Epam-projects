package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static by.epam.touragency.util.PageMsgConstant.MAIN_PAGE_PATH;

@Controller
@PreAuthorize("permitAll()")
public class ChangeLocale {
    @Autowired
    private LocaleResolver localeResolver;

    @RequestMapping("/lang")
    public ModelAndView lang(@RequestParam("language") String language,
                             HttpServletRequest request, HttpServletResponse response,
                             ModelAndView modelAndView){
        request.getSession().setAttribute("language", language);
        localeResolver.setLocale(request, response, new Locale(language));
//        modelAndView.setViewName(request.getHeader("Referer"));
        modelAndView.setViewName(ConfigurationManager.getProperty(MAIN_PAGE_PATH));
        return modelAndView;
    }
}
