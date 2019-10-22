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
                <input class="uui-form-element" type="text" name="tourName" required maxlength="40" minlength="1"
                       pattern="^[\sa-zA-Z.,_%+-]{1,40}$"/>
            </label>
        </div>
    ${errorTourNameExists!}

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.departureDate"/>
            </div>
            <div id="datepicker" class="date uui-datepicker date-button">
                <input type="text" class="uui-form-element" name="departureDate" placeholder="mm/dd/yyyy" required/>
                <span class="input-group-addon uui-button">
                    <i class="fa fa-calendar white"></i>
            </span>
            </div>

            <script>
            $('#datepicker').uui_datepicker({ todayHighlight: true });
            </script>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.arrivalDate"/>
            </div>
            <div id="datepicker" class="date uui-datepicker date-button">
                            <input type="text" class="uui-form-element" name="arrivalDate" placeholder="mm/dd/yyyy" required/>
                            <span class="input-group-addon uui-button">
                                <i class="fa fa-calendar white"></i>
            </span>
            </div>

            <script>
            $('#datepicker').uui_datepicker({ todayHighlight: true });
            </script>
        </div>
        ${errorInvalidDate!}

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.departureCity"/>
            </div>

            <label>
                <input class="uui-form-element" type="text" name="departureCity" required pattern="^[\sa-zA-Z.,_%+-]{1,40}$""/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.arrivalCity"/>
            </div>

            <label>
                <input class="uui-form-element" type="text" name="arrivalCity" required pattern="^[\sa-zA-Z.,_%+-]{1,40}$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.arrivalCountry"/>
            </div>

            <label>
                <input class="uui-form-element" type="text" name="arrivalCountry" required pattern="^[\sa-zA-Z.,_%+-]{1,40}$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.hotel"/>
            </div>

            <label>
                <input class="uui-form-element" type="text" name="hotel" required pattern="^[\sa-zA-Z.,_%+-]{1,40}$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.nutrition"/>
            </div>

            <label>
                <input class="uui-form-element" type="text" name="nutrition" required pattern="^[A-Z]{2,3}[+]?$"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.numberOfAdults"/>
            </div>

            <label>
                <input class="uui-form-element" type="text" name="adultsNumber" required pattern="^([0-4]?\d|50)$" min="0" max="50"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.numberOfChildren"/>
            </div>

            <label>
                <input class="uui-form-element" type="text" name="childrenNumber" required pattern="^([0-4]?\d|50)$" min="0" max="50"/>
            </label>
        </div>

        <div class="contentItem">
            <div class="contentItemMsg">
                <@spring.message "message.price"/>
            </div>

            <label>
                <input class="uui-form-element" type="text" name="price" required pattern="^([1]?\d?\d?\d?\d?\d([.]\d\d)?|200000([.]\d\d)?)$" min="1" max="200000"/>
            </label>
        </div>
        <div class="contentItem">
            <label class="uui-input-group vertical radio-button">
                <p class="uui-radio">
                    <input type="radio" name="isAvailable" value="AVAILABLE" id="a1" checked />
                    <label for="a1"><@spring.message "message.radio.available"/></label>
                </p>
                <p class="uui-radio">
                    <input type="radio" name="isAvailable" value="NOT_AVAILABLE" id="a2" />
                    <label for="a2"><@spring.message "message.radio.notAvailable"/></label>
                </p>
            </label>
        </div>
        <br/>
        <label>
            <input class="transparent uui-button lime-green" type="submit" name="register" value="<@spring.message "message.submit.register"/>"/>
        </label>
    </div>
</form>
</body>
<#import "components/timestamp.ftl" as timestamp/>
<@timestamp.page/>
</html>
