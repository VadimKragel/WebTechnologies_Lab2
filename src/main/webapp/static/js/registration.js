const registerForm = document.querySelector("#register-form");
const passwordField = document.querySelector("#password");
const confirmPasswordField = document.querySelector("#confirmPassword");
const messageBlock = document.querySelector("#validationMessage");

registerForm.addEventListener("submit", confirmPassword);

function confirmPassword(event) {
    const isEqual = passwordField.value === confirmPasswordField.value;
    messageBlock.hidden = isEqual;
    if (!isEqual) {
        event.preventDefault();
    }
}

