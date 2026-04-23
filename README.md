# 📚 ISBN Book Finder

A web-based application that allows users to search for book details using an ISBN number.  
The app fetches data from external APIs and displays relevant book information.

---

## 🚀 Features
- 🔍 Search book using ISBN
- 📖 Fetch book details (title, author, publisher)
- 🔄 Fallback to secondary API if data is missing
- ⚠️ Handles invalid or missing ISBN gracefully

---

## 🛠 Tech Stack
- Java / Spring Boot (Backend)
- REST APIs (Google Books API, Open Library API)
- HTML, CSS, JavaScript (Frontend)

---

## ⚙️ How It Works
1. User enters ISBN  
2. App calls Google Books API  
3. If data not found → calls Open Library API  
4. Displays result to user  

---

## 💡 Learnings
- Working with real-world APIs  
- Handling incomplete/missing data  
- Backend development with Spring Boot  
- API integration & JSON parsing  

---

## 🌐 API Endpoint

### Get Book by ISBN
```http
GET /api/books/{isbn}
```
### Example
```http
GET /api/books/9780140449136
```
---

## ⚠️ Error Handling
- Invalid ISBN handled
- Book not found → fallback API used
- API failure handled gracefully


## 📸 Demo
<img width="1360" height="720" alt="1" src="https://github.com/user-attachments/assets/e8507c4a-56e5-4e01-9c70-f3593e98cab8" />
<img width="1631" height="886" alt="7" src="https://github.com/user-attachments/assets/59eae9cd-e8fa-4ca4-b7a4-3e8824720f11" />
<img width="1360" height="720" alt="6" src="https://github.com/user-attachments/assets/80b023c4-5757-46b4-8e99-6bbb832eba53" />
<img width="1360" height="720" alt="5" src="https://github.com/user-attachments/assets/b12ad452-4f68-498d-8586-9c8a6a59a586" />
<img width="1360" height="720" alt="4" src="https://github.com/user-attachments/assets/49b37e04-764c-4d9b-bfa6-bbdb2f65c5e1" />
<img width="1360" height="720" alt="3" src="https://github.com/user-attachments/assets/39eeeb55-7a18-49da-a070-528e4b7268ea" />
<img width="1360" height="720" alt="2" src="https://github.com/user-attachments/assets/a559223a-ca0d-4c58-8e26-3b352ccd8260" />

---


## 👨‍💻 Author
Aman Tripathi  
GitHub: https://github.com/tripathiaman9354


