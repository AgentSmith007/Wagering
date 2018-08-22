(function () {
    var x = new XMLHttpRequest();
    x.onload = function () {

        // преобразование ответа сервера в массив
        var userInfo = JSON.parse(this.responseText);


        var login = document.getElementById('login');
        var name = document.getElementById('name');
        var mail = document.getElementById('mail');


        name.value = userInfo.name;
        login.value = userInfo.login;
        mail.value = userInfo.email;
    };
    x.open("GET", "/user/getUserInfo", true);
    x.send();
}());