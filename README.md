# 🚀 Transaction Service 🚀

👋 Welcome to the Transaction Service repository! 🤗

**Overview**
------------
This is a Java-based application that helps you manage financial transactions with ease. 💸 It's built using Spring Boot, Hibernate, and MySQL. 🚀

## 🎉 Features 🎉

* 💸 Add new transactions
* 📝 Edit existing transactions
* 🚫 Delete transactions 
* 🔍 Search transactions: Look up transactions by ID, type, actor, or transaction details.

## 🤖 Tech Stack 🤖

* ☕ Java 21 
* 🌱 Spring Boot v.3.4.4
* 🐍  Hibernate
* 🛠️ Maven
* 🧪 JUnit 5
* 🐬 MySQL 8

## 📦 Installation 📦

1️⃣ Clone the repository: `git clone https://github.com/KulikZaneta/Transaction-Service.git`

2️⃣ In the `application.properties` file, configure the database connection.

3️⃣ Run the following command to build the project:
`mvn clean install`

4️⃣ After a successful build, run the command to start the application:
`mvn spring-boot:run`

## 🌐 Usage 🌐

The application is available at `http://localhost:8080`. Use the following endpoints to perform various operations on transactions:

## 📚 API Documentation 📚

* **Get all transactions**: `GET /all-transactions` 📝
* **Get a transaction by ID**: `GET /transaction/{id}` 🔍
* **Create a new transaction**: `POST /transaction` 📈
* **Update a transaction by ID**: `PUT /transaction/{id}` 📝
* **Delete a transaction by ID**: `DELETE /transaction/{id}` 🚫
* **Search by Type**: `GET /search/by-type` 🔍
* **Search by Actor**: `GET /search/by-actor` 🧑‍🤝‍🧑
* **Search by Transaction Detail**: `GET /search-by-transaction-detail` 🗝️

