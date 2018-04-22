$(document).ready(function() {

    $(document).ajaxSend(function(e, xhr, options) {
        var _csrfElement = $("input[name='_csrf']");
        if (_csrfElement == undefined || _csrfElement == null) {
            console.error("There is no input with name '_csrf' element in your DOM.");
            return;
        }
        var token = _csrfElement.val();
        console.log(token);
        var header = "X-CSRF-Token";
        xhr.setRequestHeader(header, token);
    });

});