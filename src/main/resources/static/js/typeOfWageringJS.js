// Для отправки данных на сервер будем использовать HTML5 Forms, подробности по ссылке
// https://developer.mozilla.org/en-US/docs/Web/API/Document/forms

const form = document.forms.typeOfWageringForm;

// Для того чтобы отправить данные на сервер асинхронно, то есть без перехода по ссылке, что является дефолтным поведением, нам необходимо написать собственный обработчик для события submit
form.addEventListener('submit', (event) => {
    // предотвращаем дефолтное поведение формы
    event.preventDefault();
// получаем объект с данными из формы, его правда придётся спарсить где-то
// подробнее тут - https://developer.mozilla.org/en-US/docs/Web/API/FormData
    const formData = new FormData(form);
    const jsonObject = {};

    for (const [key, value]  of formData.entries()) {
        jsonObject[key] = value;
    }

// Для отправки данных на форму будем использовать Fetch API, это будет самым простым вариантом из имеющихся
// подробнее тут - https://learn.javascript.ru/fetch
// Задаём настройки для запроса

    console.log(jsonObject);

    const options = {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonObject),
        mode: 'cors'
    }

// Отправляем запрос, урл указываем вашего сервера и передаём настройки вторым аргументом
// fetch построен на промисах, поэтому эту тему тоже следует изучить - https://learn.javascript.ru/promise
    fetch('http://localhost:8080/user/userRegistration', options)
        .then((response) => console.log(response)); // тут происходит обработка ответа сервера, её может и не быть
})