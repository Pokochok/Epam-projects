package by.epam.tourAgency.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TimeTag extends BodyTagSupport {
    @Override
    public int doStartTag() throws JspException {
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        String time = "<b> " + formatForDateNow.format(gc.getTime()) + " </b>";
        try {
            JspWriter out = pageContext.getOut();
            out.write(time );
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
