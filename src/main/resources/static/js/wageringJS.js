const form = document.forms.wageringForm;

form.addEventListener('submit', (event) => {
    // предотвращаем дефолтное поведение формы
    event.preventDefault();
    const formData = new FormData(form);
    const jsonObject = {};

    for (const [key, value]  of formData.entries()) {
        jsonObject[key] = value;
    }

    console.log(jsonObject);

    const options = {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonObject),
        mode: 'cors',
        credentials: 'same-origin'
    }

    fetch('/wagering', options)
        .then((response) => console.log(response)); // тут происходит обработка ответа сервера, её может и не быть
})


function takeStatuses() {
    var x = new XMLHttpRequest();
    x.onload = function () {

        // преобразование ответа сервера в массив
        var optionsDTO = JSON.parse(this.responseText);
        var select = document.getElementById('selectForStatus');

    // очистка списка
    while (select.options.length > 0) {
        select.options.remove(0);
    }

    // заполнение списка
    for (var i = 0; i < optionsDTO.length; i++) {
        var option = document.createElement('option');
        option.value = optionsDTO[i].wageringStatus;
        option.textContent = optionsDTO[i].description;
        if (optionsDTO[i].wageringStatus == "DRAFT" ||
            optionsDTO[i].wageringStatus == "OPEN") {
            select.options.add(option);
        }
    }
};
    x.open("GET", "/wagering/takeListOfStatuses", true);
    x.send();
}

function takeTypes() {
    var x = new XMLHttpRequest();
    x.onload = function () {

        // преобразование ответа сервера в массив
        var optionsDTO = JSON.parse(this.responseText);


        var select = document.getElementById('selectForWageringDataType');

        // очистка списка
        while (select.options.length > 0) {
            select.options.remove(0);
        }

        // заполнение списка
        for (var i = 0; i < optionsDTO.length; i++) {
            var option = document.createElement('option');
            option.value = optionsDTO[i].wageringDataType;
            option.textContent = optionsDTO[i].description;
            select.options.add(option);
        }
    };
    x.open("GET", "/wagering/takeListOfTypes", true);
    x.send();
}


function takeWageringTypes() {
    var x = new XMLHttpRequest();
    x.onload = function () {

        // преобразование ответа сервера в массив
        var optionsDTO = JSON.parse(this.responseText);


        var select = document.getElementById('selectForTypeOfWagering');

        // очистка списка
        while (select.options.length > 0) {
            select.options.remove(0);
        }

        // заполнение списка
        for (var i = 0; i < optionsDTO.length; i++) {
            var option = document.createElement('option');
            option.value = optionsDTO[i].id;
            option.textContent = optionsDTO[i].rules;
            select.options.add(option);
        }
    };
    x.open("GET", "/wagering/takeListOfWageringTypes", true);
    x.send();
}

(function () {
    takeStatuses();
    takeTypes();
    takeWageringTypes();
}());

