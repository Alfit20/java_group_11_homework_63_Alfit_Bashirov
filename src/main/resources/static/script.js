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








