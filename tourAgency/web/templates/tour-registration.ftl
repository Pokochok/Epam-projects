<#import "components/main-panel.ftl" as mainPanel/>
<#import "/spring.ftl" as spring/>
<#import "components/security.ftl" as secutity/>
<!DOCTYPE html>
<html>
<head>
    <@mainPanel.page>
        <#include "../css/registration-style.css"/>
    </@mainPanel.page>
    <title><@spring.message "message.title"/></title>
</head>
<body>

<form class="registrationForm" method="POST" action="tour_register_command">


    <div class="tourContent">
        <h2><@spring.message "message.registration"/></h2>
        <hr/>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.tourName"/>
            </div>

            <label>
                <input type="text" name="tourName" required maxlength="40" minlength="1"
                       pattern="^[\sa-zA-Z.,_%+-]{1,40}$"/>
            </label>
        </div>
    ${errorTourNameExists!}

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.departureDate"/>
            </div>

            <label>
                <input type="date" name="departureDate" required/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.arrivalDate"/>
            </div>

            <label>
                <input type="date" name="arrivalDate" required/>
            </label>
        </div>
        ${errorInvalidDate!}

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.departureCity"/>
            </div>

            <label>
                <input type="text" name="departureCity" required pattern="^[\sa-zA-Z.,_%+-]{1,40}$""/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.arrivalCity"/>
            </div>

            <label>
                <input type="text" name="arrivalCity" required pattern="^[\sa-zA-Z.,_%+-]{1,40}$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.arrivalCountry"/>
            </div>

            <label>
                <input type="text" name="arrivalCountry" required pattern="^[\sa-zA-Z.,_%+-]{1,40}$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.hotel"/>
            </div>

            <label>
                <input type="text" name="hotel" required pattern="^[\sa-zA-Z.,_%+-]{1,40}$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.nutrition"/>
            </div>

            <label>
                <input type="text" name="nutrition" required pattern="^[A-Z]{2,3}[+]?$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.numberOfAdults"/>
            </div>

            <label>
                <input type="text" name="adultsNumber" required pattern="^([0-4]?\d|50)$" min="0" max="50"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.numberOfChildren"/>
            </div>

            <label>
                <input type="text" name="childrenNumber" required pattern="^([0-4]?\d|50)$" min="0" max="50"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.price"/>
            </div>

            <label>
                <input type="text" name="price" required pattern="^([1]?\d?\d?\d?\d?\d([.]\d\d)?|200000([.]\d\d)?)$" min="1" max="200000"/>
            </label>
        </div>

        <div class="contentItem">
            <label class="radio-button">
                <input type="radio" name="isAvailable" value="AVAILABLE" checked><@spring.message
                "message.radio.available"/> <br>
                <input type="radio" name="isAvailable" value="NOT_AVAILABLE"><@spring.message
                "message.radio.notAvailable"/> <br>
            </label>
        </div>

        <br/>
        <label>
            <input type="submit" name="register" value="<@spring.message "message.submit.register"/>"/>
        </label>
    </div>
</form>
</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>
