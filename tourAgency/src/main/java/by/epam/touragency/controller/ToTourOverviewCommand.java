package by.epam.touragency.controller;

import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.PageMsgConstant.TOUR_OVERVIEW_PAGE_PATH;

@Controller
public class ToTourOverviewCommand {
    @Secured({"ROLE_ADMIN", "ROLE_AGENT", "ROLE_CLIENT", "ROLE_ANONYMOUS"})
    @PostMapping("/to_tour_overview")
    public ModelAndView execute() {
        return new ModelAndView(ConfigurationManager.getProperty(TOUR_OVERVIEW_PAGE_PATH));
    }
}
