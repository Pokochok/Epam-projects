<#import "components/main-panel.ftl" as mainPanel/>
<#import "/spring.ftl" as spring/>
<#import "components/security.ftl" as secutity/>
<!DOCTYPE html>
<html>
<head>
    <@mainPanel.page>
        <#include "../css/registration-style.css">
    </@mainPanel.page>
    <title><@spring.message "message.title.ticketRegistration"/></title>
</head>
<body>

<form class="registrationForm" method="POST" action="ticket_register_command">

    <div class="ticketContent">
        <h2><@spring.message "message.title.ticketRegistration"/></h2>
        <hr/>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.flightNumber"/>
            </div>

            <label>
                <input class="uui-form-element" type="text" name="flightNumber" required pattern="^([1]?\d{1,9}|2000000000)$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.ticketNumber"/>
            </div>

            <label>
                <input class="uui-form-element" type="text" name="ticketNumber" required pattern="^([1]?\d{1,9}|2000000000)$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.departureCity"/>
            </div>

            <label>
                <input class="uui-form-element" type="text" name="departureCity"  pattern="^[a-zA-Z.,_%+-\s]{1,40}$" required/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.arrivalCity"/>
            </div>

            <label>
                <input class="uui-form-element" type="text" name="arrivalCity"  pattern="^[a-zA-Z.,_%+-\s]{1,40}$" required/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.departureDate"/>
            </div>

            <label>
                <input class="uui-form-element" type="date" name="departureDate" required/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.arrivalDate"/>
            </div>

            <label>
                <input class="uui-form-element" type="date" name="arrivalDate" required/>
            </label>
        </div>
        ${errorInvalidDate!}

        <br/>
        <label>
            <input class="transparent uui-button lime-green" type="submit" name="register" value="<@spring.message "message.submit.registerTicket"/>"/>
        </label>
    </div>
</form>
</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>

