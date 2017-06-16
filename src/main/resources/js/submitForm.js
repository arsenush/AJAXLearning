(function(){
    "use strict";

    window.onload = function () {
        AJAXSubmitForm();
    };

    function AJAXSubmitForm() {
        var mainForm = $("#mainForm");

        mainForm.submit(function (event) {
            event.preventDefault();

            // var request = new XMLHttpRequest();
            var data = mainForm.serialize();

            $.ajax({
                data: data,
                url: "submit",
                type: "POST",
                beforeSend: function () {
                    $("#result p:first-child").html("Waiting...");
                },
                success: function (result, textStatus, xhr) {
                    processJSONResult(result, xhr.status);
                }

            });

            // request.onreadystatechange = function () {
            //     if (request.readyState == 4 && request.status >= 200) {
            //         var jsonResult = JSON.parse(request.responseText);
            //         document.getElementById("result").querySelector("p").innerHTML = "<strong>Results:</strong>";
            //         processJSONResult(jsonResult, request.status);
            //     } else {
            //         document.getElementById("result").querySelector("p").innerHTML = "Waiting...";
            //     }
            // };
            //
            // request.open("POST", "submit", true);
            // request.setRequestHeader("Accept", "application/json");
            // request.setRequestHeader("Content-Type", "application/json");
            //
            // request.send(data);
        });

    }

    function processJSONResult(jsonResult, responseStatus) {
        var userName = $("#userName");
        var userAge = $("#userAge");
        var userStreet = $("#userStreet");
        var responseStatusTag = $("#responseStatus");

        $("#result p:first-child").html("<strong>Results:</strong>");

        userName.html("Name: " + jsonResult["name"]);
        userAge.html("Age: " + jsonResult["age"]);
        userStreet.html("Street: " + jsonResult["address"]["street"]);
        responseStatusTag.html(responseStatus);
    }

    $.fn.serializeObject = function(){

        var self = this,
            json = {},
            push_counters = {},
            patterns = {
                "validate": /^[a-zA-Z][a-zA-Z0-9_]*(?:\[(?:\d*|[a-zA-Z0-9_]+)\])*$/,
                "key":      /[a-zA-Z0-9_]+|(?=\[\])/g,
                "push":     /^$/,
                "fixed":    /^\d+$/,
                "named":    /^[a-zA-Z0-9_]+$/
            };


        this.build = function(base, key, value){
            base[key] = value;
            return base;
        };

        this.push_counter = function(key){
            if(push_counters[key] === undefined){
                push_counters[key] = 0;
            }
            return push_counters[key]++;
        };

        $.each($(this).serializeArray(), function(){

            // skip invalid keys
            if(!patterns.validate.test(this.name)){
                return;
            }

            var k,
                keys = this.name.match(patterns.key),
                merge = this.value,
                reverse_key = this.name;

            while((k = keys.pop()) !== undefined){

                // adjust reverse_key
                reverse_key = reverse_key.replace(new RegExp("\\[" + k + "\\]$"), '');

                // push
                if(k.match(patterns.push)){
                    merge = self.build([], self.push_counter(reverse_key), merge);
                }

                // fixed
                else if(k.match(patterns.fixed)){
                    merge = self.build([], k, merge);
                }

                // named
                else if(k.match(patterns.named)){
                    merge = self.build({}, k, merge);
                }
            }

            json = $.extend(true, json, merge);
        });

        return json;
    };
})();