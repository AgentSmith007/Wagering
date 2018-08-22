const form = document.forms.updateWageringForm;
var globalReason = "not reason";
document.getElementById('buttonCancel').onclick = function () {
    window.location.href = 'http://localhost:8080/protected/wageringFullDescription.html?wageringId='
        + wageringIdMy;
}

document.getElementById('makeCanceled').onclick = function () {
    showModalWin();
    document.getElementById('submitReason').onclick = function () {
        globalReason = document.getElementById('textReason').value;
        console.log(globalReason);

        const options = {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            credentials: 'same-origin'
        }
        fetch('/wagering/toCancelStatus/' + wageringIdMy + '?reason=' + globalReason, options)
            .then((response) => console.log(response));
        window.location.href = 'http://localhost:8080/protected/wageringFullDescription.html?wageringId='
            + wageringIdMy;
    }
    console.log(globalReason);
}

document.getElementById('makeFinished').onclick = function () {
    showModalWin();
    document.getElementById('submitReason').onclick = function () {
        globalReason = document.getElementById('textReason').value;
        console.log(globalReason);

        const options = {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            credentials: 'same-origin'
        }
        fetch('/wagering/toFinishStatus/' + wageringIdMy + '?reason=' + globalReason, options)
            .then((response) => console.log(response));
        window.location.href = 'http://localhost:8080/protected/wageringFullDescription.html?wageringId='
            + wageringIdMy;
    }
    console.log(globalReason);
}

document.getElementById('makeOpen').onclick = function (event) {
    event.preventDefault();
    const options = {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        // body: JSON.stringify(jsonObject),
        mode: 'cors',
        credentials: 'same-origin'
    }
    fetch('/wagering/updateStatus/' + wageringIdMy, options)
        .then((response) => console.log(response));
}
document.getElementById('makeDone').onclick = function (event) {
    event.preventDefault();
    const options = {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        // body: JSON.stringify(jsonObject),
        mode: 'cors',
        credentials: 'same-origin'
    }
    fetch('/wagering/updateStatus/' + wageringIdMy, options)
        .then((response) => console.log(response));
}


function showModalWin() {

    var darkLayer = document.createElement('div'); // слой затемнения
    darkLayer.id = 'shadow'; // id чтобы подхватить стиль
    document.body.appendChild(darkLayer); // включаем затемнение

    var modalWin = document.getElementById('popupWin'); // находим наше "окно"
    modalWin.style.display = 'block'; // "включаем" его

    darkLayer.onclick = function () {  // при клике на слой затемнения все исчезнет
        darkLayer.parentNode.removeChild(darkLayer); // удаляем затемнение
        modalWin.style.display = 'none'; // делаем окно невидимым
        return false;
    };
}

function chooseStatus() {
    var x = new XMLHttpRequest();
    x.onload = function () {

        var wageringDTO = JSON.parse(this.responseText);
        // var wageringStatus=wageringDTO.wageringStatusDTO.wageringStatus;
        var makeOpen = document.getElementById('makeOpen');
        var makeDone = document.getElementById('makeDone');
        var makeFinished = document.getElementById('makeFinished');
        var makeCanceled = document.getElementById('makeCanceled');

        makeOpen.style.display = 'none';
        makeDone.style.display = 'none';
        makeFinished.style.display = 'none';
        makeCanceled.style.display = 'none';

        if (wageringDTO.open) {
            makeOpen.style.display = 'block';
            //makeCanceled.style.display = 'block';
        }
        if (wageringDTO.done) {
            makeDone.style.display = 'block';
            makeCanceled.style.display = 'block';
        }
        if (wageringDTO.finished) {
            makeFinished.style.display = 'block';
            makeCanceled.style.display = 'block'
        }
        //if(wageringDTO.cancelled){makeCanceled.style.display = 'none'; }

    };
    x.open("GET", "/wagering/chooseStatus?wageringId=" + wageringIdMy, true);
    x.send();
}

form.addEventListener('submit', (event) => {
    console.log('11111');
    // предотвращаем дефолтное поведение формы
    event.preventDefault();
    const formData = new FormData(form);
    const jsonObject = {};

    for (const [key, value]  of formData.entries()) {

        jsonObject[key] = value;
    }

    console.log(jsonObject);
    console.log('22222');

    const options = {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonObject),
        mode: 'cors',
        credentials: 'same-origin'
    }
    console.log('33333');
    fetch('/wagering/' + wageringIdMy, options)
        .then((response) => console.log(response)); // тут происходит обработка ответа сервера, её может и не быть
    console.log('4444');
})

function takeStatuses() {
    var x = new XMLHttpRequest();
    x.onload = function () {

        // преобразование ответа сервера в массив
        var optionsDTO = JSON.parse(this.responseText);

        var select = document.getElementById('status');

        // очистка списка
        while (select.options.length > 0) {
            select.options.remove(0);
        }
        // заполнение списка
        for (var i = 0; i < optionsDTO.length; i++) {
            var option = document.createElement('option');
            option.value = optionsDTO[i].wageringStatus;
            option.textContent = optionsDTO[i].description;
            select.options.add(option);
            // if (optionsDTO[i].wageringStatus == "DRAFT" ||
            //     optionsDTO[i].wageringStatus == "OPEN")
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
        var select = document.getElementById('wageringDataType');
        // очистка списка
        if (select.options) {
            while (select.options.length > 0) {
                select.options.remove(0);
            }
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

        var select = document.getElementById('idOfTypeOfWagering');

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

var wageringIdMy = getUrlVars()["wageringId"];
console.log(wageringIdMy);


function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}

function getInfoAboutWagering() {

    var x = new XMLHttpRequest();
    x.onload = function () {

        // преобразование ответа сервера в массив
        var wageringDTO = JSON.parse(this.responseText);

        var name = document.getElementById('name');
        var description = document.getElementById('description');
        var idOfTypeOfWagering = document.getElementById('idOfTypeOfWagering');
        var status = document.getElementById('status');
        var prizeDescription = document.getElementById('prizeDescription');
        var wageringDataType = document.getElementById('wageringDataType');
        // var authorLogin = document.getElementById('authorLogin');

        name.value = wageringDTO.name;
        description.value = wageringDTO.description;
        idOfTypeOfWagering.value = wageringDTO.typeDescription;
        status.value = wageringDTO.statusDTO.wageringStatus;
        prizeDescription.value = wageringDTO.prizeDescription;
        wageringDataType.value = wageringDTO.wageringDataType.wageringDataType;

    };
    x.open("GET", "/wagering/getInfoAboutCurrentWagering?wageringId=" + wageringIdMy, true);
    x.send();
}

(function () {
    chooseStatus();
    getInfoAboutWagering();
    //takeStatuses();
    takeTypes();
    takeWageringTypes();
}());