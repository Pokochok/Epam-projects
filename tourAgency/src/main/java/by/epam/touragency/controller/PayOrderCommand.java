package by.epam.touragency.controller;

import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.exception.ControllerException;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.logic.OrderChangeLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
                                @SessionAttribute(value = ATTR_NAME_USER_ROLE,required = false) String userRole,
                                @SessionAttribute(value = ATTR_NAME_USER_ID, required = false) String userId,
                                @RequestParam(value = ATTR_NAME_INDEX, required = false) String index,
                                @RequestParam(value = ATTR_NAME_CHANGE_PAGE, required = false) String changeToPage
    ) throws ControllerException {
        if((userId == null && userRole == null) && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userRole = userDetails.getUserRole().toString();
            userId = String.valueOf(userDetails.getUserId());
        }
        try {
            orderChangeLogic.payOrder(orderId);
            return new ToOrdersCommand().execute(userRole, userId, index, changeToPage);
        } catch (LogicException e) {
            throw new ControllerException(e);
        }
    }
}
