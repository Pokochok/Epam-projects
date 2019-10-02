package by.epam.touragency.command.impl;

import by.epam.touragency.entity.Order;
import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.ToPageWithListLogic;
import by.epam.touragency.resource.ConfigurationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

import static by.epam.touragency.util.PageMsgConstant.TO_ORDERS_PAGE_PATH;
import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class ToOrdersCommand {
    @GetMapping("/to_orders")
    public ModelAndView execute(
            @SessionAttribute(value = ATTR_NAME_USER_ROLE) String userRole,
            @SessionAttribute(value = ATTR_NAME_USER_ID) String userId,
            @RequestParam(value = ATTR_NAME_INDEX, required = false)String index,
            @RequestParam(value = ATTR_NAME_CHANGE_PAGE, required = false) String changeToPage
    ) throws CommandException {
        int newIndex;

        if (index == null) {
            newIndex = 1;
        } else {
            newIndex = (int) (Double.parseDouble(index) + Integer.parseInt(changeToPage));
        }

        Set<Order> orderSet = null;
        try {
            orderSet = ToPageWithListLogic.getOrderSet(userRole, userId);
        } catch (LogicException e) {
            throw new CommandException(e);
        }

        ModelAndView modelAndView =  new ModelAndView(ConfigurationManager.getProperty(TO_ORDERS_PAGE_PATH));
        modelAndView.addObject(ATTR_NAME_ORDERS_PER_PAGE, NUMBER_ORDERS_PER_PAGE);
        modelAndView.addObject(ATTR_NAME_START_ORDERS_INDEX, (newIndex - 1) * NUMBER_ORDERS_PER_PAGE);
        modelAndView.addObject(ATTR_NAME_INDEX, newIndex);
        modelAndView.addObject(ATTR_NAME_ORDER_LIST, orderSet);
        modelAndView.addObject(ATTR_NAME_NUMBER_OF_ORDERS, orderSet.size());
        return modelAndView;
    }
}
