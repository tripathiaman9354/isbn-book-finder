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

### Home Page
<img width="1360" height="720" alt="1" src="https://github.com/user-attachments/assets/effa9980-549c-44cd-baf7-4388e95af33f" />

### Handling empty ISBN input
<img width="1360" height="720" alt="2" src="https://github.com/user-attachments/assets/bb5c266a-117c-48de-ac9b-e212cb8c7df2" />

### Error Handling: Invalid ISBN
<img width="1360" height="720" alt="3" src="https://github.com/user-attachments/assets/a4248eb5-019f-40bc-811f-42ec9661a6de" />

### No Book Found for Given ISBN
<img width="1360" height="720" alt="4" src="https://github.com/user-attachments/assets/e804fcb8-5bf6-46a1-b92d-2ef155d7b429" />

### Search Result (Book Found Successfully)
<img width="1360" height="720" alt="5" src="https://github.com/user-attachments/assets/d2036b38-b2f6-4026-8369-29dfaf85dbb7" />

### Book Saved Successfully (Data Stored)
<img width="1360" height="720" alt="6" src="https://github.com/user-attachments/assets/a8838104-dd0b-4a8a-85e2-1d2238236031" />

### Database 
<img width="1631" height="886" alt="7" src="https://github.com/user-attachments/assets/f5952ae9-ecbc-4903-b365-49de54d3bb84" />






---


## 👨‍💻 Author
Aman Tripathi  
GitHub: https://github.com/tripathiaman9354


