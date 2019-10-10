package by.epam.touragency.command.impl;

import by.epam.touragency.exception.CommandException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.OrderChangeLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import static by.epam.touragency.util.ParameterConstant.*;

@Controller
public class RemoveOrderCommand {
    @Autowired
    private OrderChangeLogic orderChangeLogic;

    @Secured({"ROLE_AGENT", "ROLE_CLIENT"})
    @GetMapping("/remove_order")
    public ModelAndView execute(@RequestParam(value = PARAM_NAME_ORDER_ID) String orderId,
                                @SessionAttribute(value = ATTR_NAME_USER_ROLE) String userRole,
                                @SessionAttribute(value = ATTR_NAME_USER_ID) String userId,
                                @RequestParam(value = ATTR_NAME_INDEX, required = false) String index,
                                @RequestParam(value = ATTR_NAME_CHANGE_PAGE, required = false) String changeToPage
    ) throws CommandException {
        try {
            orderChangeLogic.removeOrder(orderId);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return new ToOrdersCommand().execute(userRole, userId, index, changeToPage);
    }
}
