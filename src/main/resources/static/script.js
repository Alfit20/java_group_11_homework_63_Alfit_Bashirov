const mainBlock = document.getElementsByClassName("main-block")[0]
const image = document.createElement("img");
image.src = "images/the-mandalorian-star-wars-baby-yoda-armor-helmet-hd-wallpaper-preview.jpg";
image.classList.add("card-img-top", "image-like");
mainBlock.prepend(image)

const block = document.getElementsByClassName("block")[0]
const like = `<span class="mx-2 heart muted"><i class="far fa-heart"></i></span>`
const comment = `<i class="bi bi-chat comment"></i>`
const bookmark = `<i class="bi bi-bookmark bookmark"></i>`
block.insertAdjacentHTML('beforeEnd', like)
block.insertAdjacentHTML('beforeEnd', comment)
block.insertAdjacentHTML('beforeEnd', bookmark)

// Регистрация
const registerForm = document.getElementById("register-form")
registerForm.addEventListener("submit", registerHandler)

function registerHandler(e) {
    e.preventDefault()
    const form = e.target
    const data = new FormData(form)
    createUser(data)
}

const baseUrl = 'http://localhost:8080';

async function createUser(userFormData) {
    const userJSON = JSON.stringify(Object.fromEntries(userFormData));
    const settings = {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-type': 'application/json'
        },
        body: userJSON
    };
    const response = await fetch(baseUrl + '/registration', settings);
    const responseData = await response.text();
    console.log(responseData);
}

// Авторизация по микрограмму запустите программу
const authUrl = "/user/login"
const loginForm = document.getElementById('login-form');
loginForm.addEventListener('submit', onLoginHandler);

function onLoginHandler(e) {
    e.preventDefault();
    let form = e.target;
    let userFormData = new FormData(form);
    let user = {
        username: userFormData.get("email"),
        password: userFormData.get("password")
    }
    localStorage.setItem('user', JSON.stringify(user));
    let auth = authentication()
}


let getAuth = function () {
    let userFromStorage = localStorage.getItem("user")
    let userJson = JSON.parse(userFromStorage)
    return userJson
}

async function authentication() {
    let userAuth = getAuth()
    // console.log(userAuth.username + ":" + userAuth.password)
    let settings = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Basic " + btoa(userAuth.username + ":" + userAuth.password),
            // "Access-Control-Allow-Origin": "*",
            // "Access-Control-Allow-Methods": "*",
            // "Access-Control-Allow-Headers": "*"

        },
        mode: "cors"
    }
    try {
        let searchedUser = await fetch(baseUrl + authUrl, settings).then(response => {
            if (!response.ok) {
                throw new Error(response.status);
            }
            hideSplashScreen()
            let email = document.createElement("span")
            email.id = "name"
            email.setAttribute("style", "color: #fff; text-align: right;")
            let button = document.getElementById("closeButton")
            button.before(email)
            email.append(userAuth.username)
            return response.text();
        })
        console.log(searchedUser)
    } catch (error) {
        alert("Неверный логин или пароль")
    }

}

function hideSplashScreen() {
    let myElement = document.getElementById("splash")
    myElement.hidden = true
}

const closeButton = document.getElementById("closeButton")
closeButton.addEventListener("click", function () {
    let myElement = document.getElementById("splash")
    myElement.hidden = false
    let email = document.getElementById("name").remove()
    localStorage.clear()
})








