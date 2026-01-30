const input_form = document.getElementById("input_form");
const shorten_url_div = document.getElementById("shorten_url_div");
const shorten_url_elem = document.getElementById("shorten_url");

const url_api = "http://localhost:8080/shorten_url";

async function encurtar(event) {
    event.preventDefault(); // evita que o form recarregue a página

    const url_longa = input_form.value;

    try {
        const response = await fetch(url_api, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({ url: url_longa })
        });

        if (!response.ok) {
            const err = await response.json();
            alert(err.message || "Erro ao encurtar a URL");
            return;
        }

        const data = await response.json();
        const shortened = data.shortenUrl;

        shorten_url_elem.href = shortened;
        shorten_url_elem.textContent = shortened;

        shorten_url_div.classList.remove("invisible");

    } catch (error) {
        console.error("Erro na requisição:", error);
        alert("Não foi possível conectar à API");
    }
}
