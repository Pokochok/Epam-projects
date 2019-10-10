package by.epam.touragency.command.impl;

import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.OrderChangeLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.ParameterConstant.*;
import static by.epam.touragency.util.ParameterConstant.ATTR_NAME_CHANGE_PAGE;

@Controller
public class PayOrderCommand {
    @Autowired
    OrderChangeLogic orderChangeLogic;

    @Secured("ROLE_CLIENT")
    @PostMapping("/pay_order")
    public ModelAndView execute(@RequestParam(value = PARAM_NAME_ORDER_ID, required = false) String orderId,
                                @SessionAttribute(value = ATTR_NAME_USER_ROLE) String userRole,
                                @SessionAttribute(value = ATTR_NAME_USER_ID) String userId,
                                @RequestParam(value = ATTR_NAME_INDEX, required = false) String index,
                                @RequestParam(value = ATTR_NAME_CHANGE_PAGE, required = false) String changeToPage
    ) throws CommandException {
        try {
            boolean isSuccess = orderChangeLogic.payOrder(orderId);
            if (isSuccess) {
                return new ToOrdersCommand().execute(userRole, userId, index, changeToPage).addObject(ATTR_NAME_PAYMENT_STATE, Boolean.TRUE);
            } else {
                return new ToOrdersCommand().execute(userRole, userId, index, changeToPage);
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
    }
}
