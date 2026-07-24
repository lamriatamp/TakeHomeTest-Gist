# QA Automation Portfolio Project

## Overview
Ini adalah salah satu proyek automation akhir dari Bootcamp QA Dibimbing.  
Proyek ini dibuat menggunakan **Selenium** untuk Web Automation (CRUD Gist) dan **REST Assured** untuk API Automation (Reqres API).

## Why Selenium?
Kriteria tugas bootcamp meminta penggunaan Selenium.  
Selain itu, pengalaman menunjukkan CI/CD tidak bisa berjalan otomatis di GitHub jika menggunakan Katalon Free, sehingga Selenium dipilih agar pipeline bisa dijalankan dengan GitHub Actions.

## Tech Stack
- Java 17
- Selenium WebDriver
- REST Assured
- TestNG
- Hamcrest
- Maven/Gradle
- GitHub Actions (CI/CD)

## How to Run
1. Buat file `config.properties` di root project berisi:
github.username=xxxxx
github.password=xxxxx
REQRES_API_KEY=reqres_xxxxx

2. Jalankan suite dengan:
- ./gradlew test
- atau mvn test
- atau klik kanan testng.xml → Run
- atau klik kanan salah satu file test case → Run

## Test Execution Order
Urutan eksekusi suite sudah diatur di `testng.xml`:
1. CreateGistTest → membuat gist baru
2. ViewGistTest → memverifikasi gist muncul di halaman user
3. EditGistTest → mengubah gist yang sudah dibuat
4. DeleteGistTest → menghapus gist
5. API Tests (Reqres) → GET, POST, PUT, DELETE user

Dengan urutan ini, setiap test CRUD saling bergantung sehingga hasil lebih realistis.

## Screenshot Result in Selenium
**Dengan ./gradlew test**
<img width="1095" height="68" alt="Screenshot 2026-02-08 022453" src="https://github.com/user-attachments/assets/65ce9400-5ea4-4a3e-8e2d-9c2018357b80" />

**Dengan mvn test**
<img width="759" height="224" alt="Screenshot 2026-02-08 021941" src="https://github.com/user-attachments/assets/1053b005-82a1-4baa-8027-1598a5c49c4a" />

**Dengan Klik Kanan testng.xml**
<img width="369" height="513" alt="Screenshot 2026-02-07 214854" src="https://github.com/user-attachments/assets/33a91744-bb22-4393-8214-0ded11446546" />

**Dengan Klik Kanan testng.xml**
<img width="439" height="357" alt="Screenshot 2026-02-07 214914" src="https://github.com/user-attachments/assets/3cddb940-d9ec-4fcd-977e-79f524ba6342" />

## Findings & Recommendations
**Temuan**
- UI Automation (Gist)
- Locator tombol Edit berbeda antara Firefox dan Chrome headless → masalah responsive layout.
- Chrome headless default viewport kecil, tombol Edit tersembunyi di menu opsi.
- Screenshot artifact sangat membantu debugging di CI/CD.
- API Automation (Reqres)
- Endpoint GET /users dan GET /users/{id} stabil.
- POST /users berhasil, tapi data tidak persist (mock API).
- PUT dan DELETE hanya return status sukses, tidak benar‑benar mengubah data.

**Defect / Problem**
- UI Test
- Timeout di Chrome headless karena tombol Edit tersembunyi.
- Perbedaan perilaku Firefox vs Chrome → test tidak konsisten lintas browser.
- Folder screenshot sempat tidak ada → error di Files.copy.
- API Test
- Data Reqres tidak persist → sulit chaining test.
- Response DELETE hanya 204 tanpa body → assertion terbatas.

**Rekomendasi**
- Tambahkan cross‑browser testing (Chrome + Firefox).
- Gunakan Allure Report atau Extent Report untuk hasil test lebih rapi.
- Tambahkan negative test cases (login gagal, API error handling).
- Untuk API, gunakan environment dummy server sendiri agar data persist.
- Tambahkan pipeline step untuk upload screenshot artifact otomatis saat test gagal.

**Assertion yang Digunakan**
- UI Automation (Selenium + TestNG)
- Assert.assertTrue(element.isDisplayed()) → validasi elemen muncul.
- Assert.assertEquals(actualText, expectedText) → validasi nama file gist.
- Assert.assertFalse(list.isEmpty()) → validasi gist list tidak kosong.
- API Automation (REST Assured + Hamcrest)
- assertThat(response.statusCode(), equalTo(200)) → validasi status code.
- assertThat(response.body().path("id"), notNullValue()) → validasi field response.
- assertThat(response.body().path("name"), equalTo("morpheus")) → validasi data POST.
- assertThat(response.statusCode(), equalTo(204)) → validasi DELETE sukses.

## Important Notes
- Automation di fitur GitHub (CRUD Gist) bisa diblokir oleh pihak GitHub jika terlalu sering dijalankan.  
**Disarankan untuk tidak menjalankan terlalu sering.**
- Credential (`config.properties`) sudah di-ignore dengan `.gitignore` agar tidak ikut ke repository publik.
- Credential pada GitHub disimpan dalam Repository Secrets.
- Untuk CI/CD, gunakan **GitHub Secrets** agar pipeline tetap bisa berjalan tanpa expose credential.
