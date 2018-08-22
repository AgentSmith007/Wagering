document.getElementById("buttonRed").onclick = function () {
    window.location.href = 'http://localhost:8080/protected/usersBetInsertForm.html?wageringId='
        + wageringIdMy;
};
document.getElementById('buttonEdit').onclick = function () {
    window.location.href = 'http://localhost:8080/protected/updateWagering.html?wageringId='
        + wageringIdMy;
};

const form = document.forms.wageringInfoForm;

function getInfoAboutWagering() {

    var x = new XMLHttpRequest();
    x.onload = function () {

        // преобразование ответа сервера в массив
        var wageringDTO = JSON.parse(this.responseText);

        var table = document.getElementById('tableForUsers');


        var name = document.getElementById('name');
        var description = document.getElementById('description');
        var typeDescription = document.getElementById('typeDescription');
        var status = document.getElementById('status');
        var prizeDescription = document.getElementById('prizeDescription');
        var wageringDataType = document.getElementById('wageringDataType');
        // var wageringResult = document.getElementById('wageringResult');
        var createTime = document.getElementById('createTime');
        var authorLogin = document.getElementById('authorLogin');

        name.value = wageringDTO.name;
        description.value = wageringDTO.description;
        typeDescription.value = wageringDTO.typeDescription;
        console.log(":    :"+wageringDTO.statusDTO.wageringStatus)
        status.value = wageringDTO.statusDTO.description;
        prizeDescription.value = wageringDTO.prizeDescription;
        wageringDataType.value = wageringDTO.wageringDataType.description;
        //wageringResult.value = wageringDTO.wageringResult;

        var time = wageringDTO.createTime;
        var date = new Date(time);
        createTime.value = date;
        authorLogin.value = wageringDTO.authorLogin;

        var buttonRedact=document.getElementById('divEdit');
        var buttonBet=document.getElementById('buttonBet');
        if(wageringDTO.statusDTO.wageringStatus=="FINISHED"||
            wageringDTO.statusDTO.wageringStatus=="CANCELLED"){
            buttonRedact.style.display='none';
            buttonBet.style.display='none';
        }


        var tblBody = document.createElement("tbody");

// creating all cells
        for (var i = 0; i < wageringDTO.usersWithBet.length; i++) {
            // creates a table row
            var row = document.createElement("tr");

            // Create a <td> element and a text node, make the text
            // node the contents of the <td>, and put the <td> at
            // the end of the table row
            var userName = document.createElement("td");
            var cellText = document.createTextNode(wageringDTO.usersWithBet[i].name);
            userName.appendChild(cellText);
            row.appendChild(userName);

            var email = document.createElement("td");
            cellText = document.createTextNode(wageringDTO.usersWithBet[i].email);
            email.appendChild(cellText);
            row.appendChild(email);

            var login = document.createElement("td");
            cellText = document.createTextNode(wageringDTO.usersWithBet[i].login);
            login.appendChild(cellText);
            row.appendChild(login);

            var bet = document.createElement("td");
            cellText = document.createTextNode(wageringDTO.usersWithBet[i].usersBet);
            bet.appendChild(cellText);
            row.appendChild(bet);
            // add the row to the end of the table body
            tblBody.appendChild(row);
        }
// put the <tbody> in the <table>
        table.appendChild(tblBody);
    };

    x.open("GET", "/wagering/getInfoAboutCurrentWagering?wageringId=" + wageringIdMy, true);
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

function showButtons() {
    var x = new XMLHttpRequest();
    var divRoot = document.getElementById("divEdit");
    // var status = document.getElementById('status');
    // var wageringDTO = JSON.parse(this.responseText);

    divRoot.style.visibility = 'hidden';
    x.onload = function () {
        console.log("x.status: ")
        console.log(x.status)
        if (x.status == 200 || x.status == 0) {
            divRoot.style.visibility = 'visible';
        }
        else {
            console.log("we a NOOOT hereerer");
            divRoot.style.visibility = 'hidden';
        }

    };
    x.open("GET", "/wagering/showButtonUpdate?wageringId=" + wageringIdMy, true);
    x.send();
}


(function () {
    getInfoAboutWagering();
    showButtons();
}());











