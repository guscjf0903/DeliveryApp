document.addEventListener("DOMContentLoaded", function () {
    var loginId = sessionStorage.getItem("loginId");

    document.getElementById("loginId").value = loginId;
});