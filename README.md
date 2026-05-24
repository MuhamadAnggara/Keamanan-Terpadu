# Sistem Keamanan Terpadu (Java Swing)

## Deskripsi

Sistem Keamanan Terpadu adalah aplikasi desktop berbasis Java Swing yang digunakan untuk membantu pengelolaan data keamanan seperti:

* Data petugas keamanan
* Laporan kejadian
* Area patroli
* Sinkronisasi data menggunakan Supabase API

Aplikasi ini memiliki antarmuka modern dengan sidebar navigasi dan sistem login sederhana.

---

# Tampilan Fitur

## 1. Login System

Pengguna harus login sebelum masuk ke dashboard utama.

### Default Login

```txt
Username : admin
Password : admin123
```

---

## 2. Dashboard Utama

Dashboard terdiri dari beberapa menu:

* Data Petugas
* Laporan Kejadian
* Area Patroli
* Sinkronisasi Data

Menggunakan:

* Java Swing
* JTable
* CardLayout
* BorderLayout
* GridLayout

---

## 3. Integrasi Supabase

Aplikasi menggunakan REST API dari Supabase untuk mengambil data.

Teknologi yang digunakan:

* HttpURLConnection
* JSON Parsing Manual
* REST API

---

# Struktur Project

```txt
Keamanan.Java/
│
├── src/
│   ├── LoginForm.java
│   └── KeamananApp.java
│
├── dist/
│   └── Uts_Anggara.jar
│
├── build/
├── nbproject/
├── lib/
├── build.xml
└── manifest.mf
```

---

# Teknologi yang Digunakan

| Teknologi  | Keterangan       |
| ---------- | ---------------- |
| Java       | Bahasa utama     |
| Java Swing | GUI Desktop      |
| Supabase   | Database Backend |
| REST API   | Komunikasi Data  |
| NetBeans   | IDE Project      |

---

# Cara Menjalankan Project

## Menggunakan NetBeans

1. Buka NetBeans
2. Pilih:

```txt
File -> Open Project
```

3. Pilih folder:

```txt
Keamanan.Java
```

4. Jalankan project:

```txt
Run Project (F6)
```

---

## Menggunakan Command Line

Compile:

```bash
javac src/*.java
```

Jalankan:

```bash
java LoginForm
```

---

# Build JAR

File JAR sudah tersedia di:

```txt
dist/Uts_Anggara.jar
```

Menjalankan JAR:

```bash
java -jar Uts_Anggara.jar
```

