package by.epam.tourAgency.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParametersValues;
    private HashMap<String, String> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    private String localName;
    private boolean invalidateSession;

    public SessionRequestContent() {
        requestAttributes = new HashMap<>();
        requestParametersValues = new HashMap<>();
        sessionAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
    }

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
}