package by.epam.touragency.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Class-wrapper, which contains all data from HttpServletRequest object,
 * that command needs, to perform action
 */
public class SessionRequestContent {
    /**
     * Request attributes from HttpServletRequest
     */
    private HashMap<String, Object> requestAttributes;
    /**
     * Values of parameters in request from HttpServletRequest
     */
    private HashMap<String, String[]> requestParametersValues;
    /**
     * Request parameters from HttpServletRequest
     */
    private HashMap<String, String> requestParameters;
    /**
     * Session attributes from HttpServletRequest
     */
    private HashMap<String, Object> sessionAttributes;
    /**
     * Field for defining locale
     */
    private String localName;
    /**
     * Flag for determine whether to invalidate session
     */
    private boolean invalidateSession;

    /**
     * Constructs a new object that will contain request
     * content
     */
    public SessionRequestContent() {
        requestAttributes = new HashMap<>();
        requestParametersValues = new HashMap<>();
        sessionAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
    }

    /**
     * Receives data from request
     * @param request the object of HttpServletRequest type, from
     *                which data will be obtained
     */
    public void extractValues(HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            requestAttributes.put(attributeName, request.getAttribute(attributeName));
        }

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            requestParametersValues.put(parameterName, request.getParameterValues(parameterName));
        }

        parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            requestParameters.put(parameterName, request.getParameter(parameterName));
        }

        Enumeration<String> sessionAttributeNames = request.getSession().getAttributeNames();
        while (sessionAttributeNames.hasMoreElements()) {
            String sessionAtributeName = sessionAttributeNames.nextElement();
            sessionAttributes.put(sessionAtributeName, request.getSession().getAttribute(sessionAtributeName));
        }

        localName = request.getLocalName();
        invalidateSession = false;
    }

    /**
     * Unload data to request
     * @param request the object of HttpServletRequest type,
     *                to which data will be transferred
     */
    public void insertAttributes(HttpServletRequest request) {
        for (String attributeName : requestAttributes.keySet()) {
            request.setAttribute(attributeName, requestAttributes.get(attributeName));
        }
        if (!invalidateSession) {
            for (String sessionAttributeName : sessionAttributes.keySet()) {
                request.getSession().setAttribute(sessionAttributeName, sessionAttributes.get(sessionAttributeName));
            }
        } else {
            request.getSession().invalidate();
        }
    }

    public Object getAttribute(String attributeName){
        return requestAttributes.get(attributeName);
    }

    public void setAttribute(String attributeName, Object o) {
        requestAttributes.put(attributeName, o);
    }

    public void setSessionAttribute(String sessionAttributeName, Object o) {
        sessionAttributes.put(sessionAttributeName, o);
    }

    public String[] getParameterValues(String paramName) {
        return requestParametersValues.get(paramName);
    }

    public String getParameter(String paramName) {
        return requestParameters.get(paramName);
    }

    public Object getSessionAttribute(String attrName) {
        return sessionAttributes.get(attrName);
    }

    public String getLocalName() {
        return localName;
    }

    public void invalidate() {
        invalidateSession = true;
    }

    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }
}