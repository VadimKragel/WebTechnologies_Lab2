const languageSelect = document.querySelector("#lang-select");

languageSelect.addEventListener("change", setLangParameter)
function setLangParameter(event) {
    const url = new URL(window.location.href);
    url.searchParams.set("lang", event.target.value)
    window.location.href = url.toString();
}