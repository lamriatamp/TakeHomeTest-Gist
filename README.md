# QA Automation Portfolio Project

## 📖 Overview
Ini adalah proyek automasi pengujian akhir (*Final Project*) dari Bootcamp QA Dibimbing. Proyek ini mendemonstrasikan implementasi *End-to-End Testing* menggunakan dua pendekatan:
*   **Web Automation (UI):** Menggunakan **Selenium** untuk melakukan skenario CRUD (Create, Read, Update, Delete) pada fitur GitHub Gist.
*   **API Automation:** Menggunakan **REST Assured** untuk melakukan pengujian integrasi pada Reqres API.

## 🛠️ Tech Stack
*   **Language:** Java 17
*   **UI Automation:** Selenium WebDriver
*   **API Automation:** REST Assured
*   **Testing Framework:** TestNG
*   **Assertion Library:** Hamcrest
*   **Build Tool:** Gradle / Maven
*   **CI/CD:** GitHub Actions

## 💡 Why Selenium?
Pemilihan Selenium disesuaikan dengan kriteria teknis dari *bootcamp*. Selain itu, integrasi *Continuous Integration/Continuous Deployment* (CI/CD) melalui GitHub Actions dapat dikonfigurasi dan berjalan secara otomatis (gratis) dengan mulus menggunakan ekosistem Selenium, dibandingkan limitasi yang ada pada alat berbasis GUI versi gratis.

## 🚀 How to Run (Local Execution)

### 1. Konfigurasi Kredensial
Buat sebuah file bernama `config.properties` di *root directory* proyek ini dan isi dengan format berikut:

```properties
github.username=USERNAME_GITHUB_KAMU
github.password=PASSWORD_GITHUB_KAMU
REQRES_API_KEY=reqres_xxxxx
