//const form = document.forms.wageringGetForm;

function getAllWagering() {
    var x = new XMLHttpRequest();
    x.onload = function () {

        // преобразование ответа сервера в массив
        var optionsDTO = JSON.parse(this.responseText);

        //заполнение списка
        var table = [];
        var links = [];
        for (var i = 0; i < optionsDTO.length; i++) {
            table[i] = document.createElement('table');
            links[i] = document.createElement("a");
            links[i].setAttribute("href", "http://localhost:8080/protected/wageringFullDescription.html?wageringId=" + optionsDTO[i].id);
            var linkText = document.createTextNode("Full information");
            links[i].appendChild(linkText);

            var td = document.createElement('td');

            var tr1 = document.createElement('tr');
            var tr2 = document.createElement('tr');
            var tr3 = document.createElement('tr');
            var tr4 = document.createElement('tr');
            var tr5 = document.createElement('tr');
            var linkTr = document.createElement('href');

            var text1 = document.createTextNode("Wagering id: " + optionsDTO[i].id);
            var text2 = document.createTextNode("Name of wagering: " + optionsDTO[i].name);
            var text3 = document.createTextNode("Status: " + optionsDTO[i].statusDTO.description);
            var text4 = document.createTextNode("Description: " + optionsDTO[i].description);
            var text5 = document.createTextNode("Creator login: " + optionsDTO[i].authorLogin);
            linkTr.value = ('http://localhost:8080/protected/wageringFullDescription.html?wageringId=' + optionsDTO[i].id);

            tr1.appendChild(text1);
            tr2.appendChild(text2);
            tr3.appendChild(text3);
            tr4.appendChild(text4);
            tr5.appendChild(text5);

            td.appendChild(tr1);
            td.appendChild(tr2);
            td.appendChild(tr3);
            td.appendChild(tr4);
            td.appendChild(tr5);
            td.appendChild(links[i]);

            table[i].appendChild(td);
            document.body.appendChild(table[i]);
        }

    };
    x.open("GET", "/wagering/getAllWagerings", true);
    x.send();
}

(function () {
    getAllWagering();
}());