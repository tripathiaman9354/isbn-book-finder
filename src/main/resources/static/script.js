// 🔴 ERROR UI (SABSE UPAR RAKHO)
function showError(message) {

    let msg = document.getElementById("errorMsg");

    msg.style.display = "block";
    msg.style.background = "#f8d7da";
    msg.style.color = "#721c24";
    msg.innerText = message;

    document.getElementById("bookCard").style.display = "none";
    document.getElementById("saveBtn").style.display = "none";
}


// 🟢 SUCCESS UI
function showSuccess(message) {

    let msg = document.getElementById("errorMsg");

    msg.style.display = "block";
    msg.style.background = "#d4edda";
    msg.style.color = "#155724";
    msg.innerText = message;
}


// 🔍 SEARCH BOOK
function searchBook() {

    let isbn = document.getElementById("isbn").value.trim();

    if (isbn === "") {
        showError("⚠️ Please enter ISBN number");
        return;
    }

    if (isbn.length < 10) {
        showError("⚠️ Invalid ISBN");
        return;
    }

    // ✅ SHOW LOADER
    document.getElementById("loadingMsg").style.display = "block";
    document.getElementById("bookCard").style.display = "none";
    document.getElementById("errorMsg").style.display = "none";

    fetch("http://localhost:8080/api/books/search/" + isbn)
    .then(response => {
        if (!response.ok) {
            throw new Error("Not Found");
        }
        return response.json();
    })
    .then(data => {

        if (!data.title) {
            throw new Error("No Data");
        }

        window.currentBook = data;

        // ❌ HIDE LOADER
        document.getElementById("loadingMsg").style.display = "none";

        document.getElementById("bookCard").style.display = "flex";

        document.getElementById("title").innerText = data.title;
        document.getElementById("author").innerText = data.authorName || "Unknown";
        document.getElementById("publisher").innerText = data.publisher || "Unknown";
        document.getElementById("publishedDate").innerText = data.publishedDate || "N/A";
        document.getElementById("genre").innerText = data.genreType || "N/A";
        document.getElementById("price").innerText = data.price || "Not Available";

        document.getElementById("coverImage").src =
            data.coverImage || "https://via.placeholder.com/150";

        document.getElementById("saveBtn").style.display = "block";
    })
    .catch(() => {

        // ❌ HIDE LOADER
        document.getElementById("loadingMsg").style.display = "none";

        showError("📚 No book found for this ISBN.");
    });
}


// 💾 SAVE BOOK (ADD THIS BELOW YOUR CODE)
function saveBook() {

    if (!window.currentBook) {
        alert("No book to save!");
        return;
    }

    fetch("http://localhost:8080/api/books/save", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(window.currentBook)
    })
    .then(async response => {

        let text = await response.text();

        if (!response.ok) {
            throw new Error(text); // backend message capture
        }

        return text;
    })
    .then(() => {

        // ✅ UI reset after success
        document.getElementById("isbn").value = "";
        document.getElementById("bookCard").style.display = "none";
        document.getElementById("saveBtn").style.display = "none";
        window.currentBook = null;

        showSuccess("✅ Book saved successfully!");
    })
    .catch(error => {

        let msg = error.message.toLowerCase();

        if (msg.includes("already")) {

            // ✅ reset UI even if duplicate
            document.getElementById("isbn").value = "";
            document.getElementById("bookCard").style.display = "none";
            document.getElementById("saveBtn").style.display = "none";
            window.currentBook = null;

            showError("⚠️ Book is already saved!");
        } else {
            showError("❌ Failed to save book");
        }
    });
}