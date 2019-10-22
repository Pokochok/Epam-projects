package by.epam.touragency.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", Arrays.toString(ex.getStackTrace()));
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.setViewName("templates/error");
        return modelAndView;
    }
}