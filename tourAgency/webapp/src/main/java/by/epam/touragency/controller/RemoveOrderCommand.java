package by.epam.touragency.controller;

import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.logic.OrderChangeLogic;
import by.epam.touragency.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RemoveOrderCommand {
    @Autowired
    private OrderChangeLogic orderChangeLogic;

    @Autowired
    private ToOrdersCommand toOrdersCommand;

    @Secured({"ROLE_AGENT", "ROLE_CLIENT"})
    @GetMapping("/remove_order")
    public ModelAndView execute(@RequestParam(value = ParameterConstant.PARAM_NAME_ORDER_ID) String orderId,
                                @SessionAttribute(value = ParameterConstant.ATTR_NAME_USER_ROLE, required = false) String userRole,
                                @SessionAttribute(value = ParameterConstant.ATTR_NAME_USER_ID, required = false) String userId,
                                @RequestParam(value = ParameterConstant.ATTR_NAME_INDEX, required = false) String index,
                                @RequestParam(value = ParameterConstant.ATTR_NAME_CHANGE_PAGE, required = false) String changeToPage
    ) {
        if ((userId == null && userRole == null) && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userRole = userDetails.getUserRole().toString();
            userId = String.valueOf(userDetails.getUserId());
        }
        orderChangeLogic.removeOrder(orderId);
        return toOrdersCommand.execute(userRole, userId, index, changeToPage);
    }
}
