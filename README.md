<h1 align="center">🏦 BrinkMikro</h1>

<p align="center">
  <b>Sistem Informasi Tabungan dan Pembiayaan bagi UMKM dan Petani</b><br>
  Aplikasi desktop Java Swing + MySQL · Project Akhir Capstone &amp; Tugas Akhir PBO
</p>

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-17-orange?logo=openjdk&logoColor=white">
  <img alt="MySQL" src="https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql&logoColor=white">
  <img alt="Maven" src="https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven&logoColor=white">
  <img alt="GUI" src="https://img.shields.io/badge/GUI-Java%20Swing-blue">
</p>

---

## 📑 Daftar Isi

1. [Nama Program](#1-nama-program)
2. [Deskripsi Singkat](#2-deskripsi-singkat)
3. [Fitur Utama Program](#3-fitur-utama-program)
4. [Tech Stack](#4-tech-stack)
5. [Perancangan Sistem](#5-perancangan-sistem)
6. [Struktur Folder/Package](#6-struktur-folderpackage)
7. [Penerapan 4 Pilar OOP](#7-penerapan-4-pilar-oop)
8. [Cara Penggunaan Program](#8-cara-penggunaan-program)
9. [Kontributor](#9-kontributor)

---

## 1. Nama Program

**BrinkMikro**: Sistem Informasi Tabungan dan Pembiayaan bagi UMKM.

Kata "Mikro" merujuk pada skala usaha dan layanan keuangan yang dituju, yaitu pedagang UMKM dan petani.

## 2. Deskripsi Singkat

Petani dan pedagang UMKM sering kesulitan mengelola keuangan karena pendapatan yang tidak menentu (pedagang mengikuti penjualan harian, petani mengikuti musim tanam dan panen) serta terbatasnya akses ke layanan keuangan yang sesuai. Pencatatan manual membuat riwayat transaksi sulit ditelusuri dan angsuran sulit dipantau.

BrinkMikro dirancang sebagai sistem informasi yang menyatukan data **pengguna, nasabah, rekening tabungan, transaksi tabungan, pengajuan pembiayaan, dan angsuran** dalam satu basis data terstruktur. Setiap petugas memakai aplikasi sesuai perannya (**Admin, Teller, Manajer**), sedangkan keputusan pembiayaan tetap berada di tangan Manajer sesuai batas kewenangannya.

Proyek ini berkaitan dengan tiga tujuan Sustainable Development Goals (SDGs):

| SDG | Keterkaitan |
|---|---|
| **SDG 1 – Tanpa Kemiskinan** | Rekam jejak tabungan dan transaksi membantu nasabah mengakses pembiayaan formal sebagai alternatif pinjaman informal berbunga tinggi. |
| **SDG 8 – Pekerjaan Layak dan Pertumbuhan Ekonomi** | Akses modal yang sesuai kemampuan mendukung keberlangsungan dan perkembangan usaha. |
| **SDG 9 – Industri, Inovasi, dan Infrastruktur** | Teknologi informasi dipakai untuk mengintegrasikan layanan keuangan mikro agar lebih tertib dan terlacak. |

### 📌 Status Pengembangan

| Lapisan | Status |
|---|---|
| Basis data (11 tabel, relasi, data contoh) | ✅ Selesai |
| Backend: `model`, `dao`, `service` | ✅ Selesai dan teruji lewat `BrinkmikroApp` |
| Antarmuka GUI (19 form, 3 role) | ✅ Tampilan dan navigasi selesai |
| Integrasi GUI ↔ DAO | 🔧 Sedang berjalan. Tabel pada form saat ini masih memakai `DefaultTableModel` di memori, sedangkan koneksi ke database baru aktif pada proses login |

## 3. Fitur Utama Program

### 3.1 Fitur per Role

| Role | Menu | Kegunaan |
|---|---|---|
| **Admin** | Data Pengguna | Kelola akun dan hak akses pengguna |
| | Data Nasabah | Kelola data induk nasabah |
| | Rekening Tabungan | Buka, ubah status, dan tutup rekening |
| | Transaksi Tabungan | Pencatatan setor dan tarik tunai |
| | Pengajuan Pembiayaan | Kelola dan verifikasi berkas pengajuan |
| | Data Angsuran | Pencatatan tagihan dan pembayaran angsuran |
| | Monitoring dan Laporan | Audit log dan ringkasan operasional |
| **Teller** | Data Nasabah | Daftarkan dan kelola nasabah |
| | Rekening Tabungan | Cek saldo dan mutasi rekening |
| | Setor Tunai | Layanan setoran di loket |
| | Tarik Tunai | Layanan penarikan di loket |
| | Pembayaran Angsuran | Terima pembayaran angsuran nasabah |
| **Manajer** | Dashboard Kas | Posisi kas dan likuiditas |
| | Jatuh Tempo | Pantau pembiayaan jatuh tempo dan tunggakan |
| | Persetujuan Pembiayaan | Setujui atau tolak pengajuan |
| | Rekap Laporan | Rekap keuangan dan operasional |
| | Riwayat Transaksi | Audit trail seluruh transaksi |
| | Master Tabel Angsuran | Seluruh data angsuran nasabah |

### 3.2 Fitur Umum pada Setiap Form

- **Pencarian real-time** dengan `TableRowSorter` dan `RowFilter` regex (tidak membedakan huruf besar/kecil).
- **Operasi CRUD** lewat tombol tambah, ubah, dan hapus, dengan dialog input dan konfirmasi.
- **Validasi input**: kolom wajib diisi dan format tanggal wajib `dd-MM-yyyy`.
- **Navigasi menu samping** antarform sesuai role, serta tombol **Logout**.

### 3.3 Aturan Bisnis pada Lapisan DAO

| Aturan | Penjelasan |
|---|---|
| **Keamanan login** | Percobaan login yang gagal lebih dari 3 kali mengunci akun (`status_aktif = 'Terkunci'`). Akun `Nonaktif` ditolak. |
| **Transaksi atomik** | Setor, tarik, bayar angsuran, tambah nasabah, dan tambah pengguna dibungkus transaksi database (`setAutoCommit(false)`, `commit`, `rollback`). |
| **Penguncian baris** | Setor, tarik, dan bayar angsuran memakai `SELECT ... FOR UPDATE` agar saldo dan status tidak berubah ganda saat diproses. |
| **Validasi rekening** | Setor dan tarik hanya untuk rekening berstatus `Aktif`. Nominal harus lebih dari 0. Tarik tunai ditolak bila saldo tidak cukup. |
| **Perhitungan kewajiban** | `total_kewajiban = pokok + (pokok × bunga% × jangka_bulan / 12)`, dengan bunga bawaan 6%. |
| **Batas persetujuan** | Manajer tidak dapat menyetujui pengajuan yang melebihi `batas_persetujuan` miliknya. |
| **Pelunasan otomatis** | Hanya pembiayaan berstatus `Disetujui` yang dapat dibayar. Status berubah menjadi `Lunas` saat total angsuran mencapai total kewajiban. |
| **Pencatatan lengkap** | Nasabah baru disimpan bersama detail Petani atau UMKM. Pengguna baru disimpan bersama detail Admin, Teller, atau Manajer. |
| **Monitoring** | Dashboard kas (saldo tabungan, pembiayaan cair, angsuran masuk), daftar jatuh tempo dengan sisa kewajiban, dan rekap pembiayaan per jenis nasabah dan status. |

## 4. Tech Stack

| Komponen | Teknologi | Kegunaan |
|---|---|---|
| Bahasa pemrograman | **Java 17** | Logika aplikasi dan penerapan OOP |
| GUI | **Java Swing** (NetBeans GUI Builder) | Antarmuka aplikasi desktop |
| Layout | **NetBeans AbsoluteLayout** `RELEASE120` | Penataan komponen form |
| Database | **MySQL 8** (dijalankan lewat Laragon) | Penyimpanan data relasional |
| Akses data | **JDBC** + `PreparedStatement` | Query terparameter, aman dari SQL injection |
| Database driver | **MySQL Connector/J 8.4.0** | Koneksi Java ke MySQL |
| Build tool | **Apache Maven** | Pengelolaan dependency dan build |
| Pola arsitektur | **Model, DAO, Service, View** | Pemisahan data, akses database, logika, dan tampilan |
| IDE | **Apache NetBeans** | Pengembangan dan GUI Builder |
| Version control | **Git dan GitHub** | Kolaborasi tim |

## 5. Perancangan Sistem

> Diagram diekspor dari diagrams.net. Letakkan file gambar pada folder `docs/diagram/` agar tampil di GitHub.

### 5.1 Flowchart

Alur sistem dimulai dari **login**, lalu pengguna diarahkan ke submenu sesuai peran.

| Flowchart | Ringkasan Alur |
|---|---|
| **Login** | Pengguna mengisi username dan password. Jika kredensial tidak valid, sistem menghitung percobaan. Setelah lebih dari 3 kali gagal, akun terkunci. Jika valid, pengguna diarahkan sesuai peran. |
| **Admin** | Kelola pengguna, nasabah, dan rekening (tambah, ubah, hapus dengan validasi). Proses transaksi setor dan tarik, kelola pengajuan dan angsuran, lalu pantau lewat menu Monitoring. |
| **Teller** | Tambah nasabah dan rekening, layani setor dan tarik tunai (validasi data dan saldo), catat pengajuan dan angsuran. |
| **Manajer** | Lihat dashboard kas, jatuh tempo, rekap laporan, dan transaksi. Tinjau pengajuan, lalu simpan keputusan Disetujui atau Tidak Disetujui. |

![Flowchart Login](docs/diagram/flowchart-login.png)
![Flowchart Admin](docs/diagram/flowchart-admin.png)
![Flowchart Teller](docs/diagram/flowchart-teller.png)
![Flowchart Manajer](docs/diagram/flowchart-manajer.png)

🔗 [Sumber flowchart (diagrams.net)](https://app.diagrams.net/#G17ZA8wX00RmHssDlPSr7OFqnqkN4jBmiW#%7B%22pageId%22%3A%22BnzTX9ZRe5hTWhAEgd1e%22%7D)

### 5.2 Use Case Diagram

Empat aktor berinteraksi dengan sistem:

| Aktor | Peran dalam Sistem |
|---|---|
| **Admin** | Mengelola pengguna, nasabah, rekening, transaksi tunai, pengajuan, angsuran, serta melihat dashboard kas dan rekap laporan |
| **Teller** | Melayani setor dan tarik tunai, mengelola nasabah dan rekening, mencatat pengajuan dan angsuran |
| **Manajer** | Meninjau data, memutuskan pembiayaan, memantau angsuran dan jatuh tempo, melihat dashboard dan rekap laporan |
| **Nasabah** | Mengajukan pembiayaan, melihat informasi pengajuan dan angsuran, menerima keputusan pembiayaan |

<img width="1128" height="1556" alt="Use Case BrinkMikro drawio (3)" src="https://github.com/user-attachments/assets/d9a9df10-7f6f-41ec-83b8-6fc0ba6eb09a" />

### 5.3 Entity Relationship Diagram (ERD)

Basis data `brinkmikro` terdiri dari **11 tabel**:

| Kelompok | Tabel |
|---|---|
| Pengguna (superclass dan subclass) | `pengguna`, `admin`, `teller`, `manajer` |
| Nasabah (superclass dan subclass) | `nasabah`, `petani`, `pedagang_umkm` |
| Layanan keuangan | `rekening_tabungan`, `transaksi_tabungan`, `pembiayaan`, `angsuran` |

**Relasi dan kardinalitas utama**

| Dari | Ke | Kardinalitas | Keterangan |
|---|---|---|---|
| `pengguna` | `admin` / `teller` / `manajer` | 1 : 1 | Spesialisasi akun berdasarkan peran |
| `nasabah` | `petani` / `pedagang_umkm` | 1 : 1 | Spesialisasi berdasarkan jenis nasabah |
| `nasabah` | `rekening_tabungan` | 1 : N | Satu nasabah memiliki rekening tabungan |
| `nasabah` | `pembiayaan` | 1 : N | Satu nasabah dapat mengajukan banyak pembiayaan |
| `manajer` | `pembiayaan` | 1 : N | Manajer memutuskan pembiayaan |
| `pembiayaan` | `angsuran` | 1 : N | Satu pembiayaan memiliki banyak angsuran |
| `rekening_tabungan` | `transaksi_tabungan` | 1 : N | Satu rekening memiliki banyak transaksi |
| `teller` | `transaksi_tabungan` / `angsuran` | 1 : N | Teller memproses transaksi dan angsuran |

![ERD Logical](docs/diagram/erd-logical.png)
![ERD Relational](docs/diagram/erd-relational.png)

## 6. Struktur Folder/Package

> ⚠️ Struktur berikut adalah kondisi project **saat ini** dan dapat berubah seiring penyelesaian integrasi GUI.

### 6.1 Tampilan Struktur

```text
BrinkMikro
├── pom.xml
├── .gitignore
└── src/main/java/com/mycompany/brinkmikro/app
    ├── BrinkmikroApp.java            # Uji koneksi dan backend (CLI)
    │
    ├── config
    │   └── DatabaseConnection.java   # Koneksi JDBC ke MySQL
    │
    ├── model                         # Entity / data class
    │   ├── Pengguna.java             # Superclass akun
    │   ├── Admin.java                # extends Pengguna
    │   ├── Teller.java               # extends Pengguna
    │   ├── Manajer.java              # extends Pengguna
    │   ├── Nasabah.java              # Superclass nasabah
    │   ├── Petani.java               # extends Nasabah
    │   └── PedagangUmkm.java         # extends Nasabah
    │
    ├── dao                           # Data Access Object (query SQL)
    │   ├── PenggunaDAO.java
    │   ├── NasabahDAO.java
    │   ├── RekeningTransaksiDAO.java
    │   ├── PembiayaanDAO.java
    │   ├── AngsuranDAO.java
    │   └── MonitoringDAO.java
    │
    ├── service
    │   └── AuthService.java          # Autentikasi dan penguncian akun
    │
    └── view                          # GUI Java Swing (.java + .form)
        ├── FormLogin
        ├── Admin*     (7 form)
        ├── Teller*    (5 form)
        └── Manajer*   (6 form)
```

### 6.2 Penjelasan Package

| Package | Isi | Peran |
|---|---|---|
| `app` | `BrinkmikroApp` | Program uji yang mencoba login dan memanggil DAO untuk membuktikan backend berjalan |
| `app.config` | `DatabaseConnection` | Titik tunggal pembuatan koneksi database |
| `app.model` | 7 class | Representasi data dengan hierarki pewarisan `Pengguna` dan `Nasabah` |
| `app.dao` | 6 class | Seluruh query SQL (CRUD, join, agregasi, transaksi) terpusat di sini |
| `app.service` | `AuthService` | Logika login: validasi kredensial, hitung percobaan gagal, kunci akun |
| `app.view` | 19 form | Antarmuka Swing. Setiap form memiliki pasangan file `.form` dari NetBeans GUI Builder |

## 7. Penerapan 4 Pilar OOP

### 7.1 Encapsulation (Enkapsulasi)

Atribut dibuat `protected` atau `private` dan hanya dapat dibaca atau diubah lewat *getter* dan *setter*. Pada `AuthService`, hasil login dibungkus class `LoginResult` yang seluruh atributnya `private final` sehingga **tidak dapat diubah** setelah dibuat.

<details>
<summary><b>💡 Lihat kode</b></summary>

```java
// model/Pengguna.java
public class Pengguna {
    protected int idPengguna;
    protected String namaLengkap;
    protected String username;
    protected String password;
    protected String peran;       // 'Admin', 'Teller', 'Manajer'
    protected String statusAktif; // 'Aktif', 'Terkunci', 'Nonaktif'

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    // ... getter dan setter lainnya
}
```

```java
// service/AuthService.java
public static class LoginResult {
    private final boolean success;
    private final boolean locked;
    private final String message;
    private final Pengguna pengguna;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    // ... hanya getter, tanpa setter
}
```

</details>

### 7.2 Inheritance (Pewarisan)

Dua hierarki pewarisan merepresentasikan spesialisasi pada ERD:

- `Admin`, `Teller`, dan `Manajer` mewarisi **`Pengguna`**.
- `Petani` dan `PedagangUmkm` mewarisi **`Nasabah`**.

Subclass memanggil `super(...)` dengan nilai `peran` atau `jenisNasabah` yang sudah ditetapkan, sehingga data induk dan data khusus tidak mungkin tertukar.

<details>
<summary><b>💡 Lihat kode</b></summary>

```java
// model/Teller.java
public class Teller extends Pengguna {
    private String loket;
    private String shiftTeller; // 'Pagi', 'Siang'

    public Teller(int idPengguna, String namaLengkap, String username,
                  String password, String statusAktif,
                  String loket, String shiftTeller) {
        super(idPengguna, namaLengkap, username, password, "Teller", statusAktif);
        this.loket = loket;
        this.shiftTeller = shiftTeller;
    }
}
```

```java
// model/Petani.java
public class Petani extends Nasabah {
    private BigDecimal luasLahan;
    private String komoditasUtama;
    private BigDecimal pendapatanTahunan;

    public Petani(String nik, String namaNasabah, String alamat, String noTelepon,
                  Date tanggalDaftar, BigDecimal luasLahan,
                  String komoditasUtama, BigDecimal pendapatanTahunan) {
        super(nik, namaNasabah, alamat, noTelepon, "Petani", tanggalDaftar);
        // ...
    }
}
```

Seluruh form pada package `view` juga mewarisi `javax.swing.JFrame`.

</details>

### 7.3 Polymorphism (Polimorfisme)

Polimorfisme muncul dalam tiga bentuk:

1. **Overloading konstruktor.** Setiap model memiliki konstruktor kosong dan konstruktor berparameter.
2. **Overriding.** Event handler Swing mengimplementasikan ulang method dari interface `DocumentListener` (`insertUpdate`, `removeUpdate`, `changedUpdate`) lewat anonymous class. Method yang dijalankan ditentukan saat runtime oleh objek listener-nya.
3. **Substitusi objek.** `Admin`, `Teller`, dan `Manajer` adalah `Pengguna`, sehingga `AuthService.login()` cukup mengembalikan satu tipe `Pengguna` untuk semua peran.

<details>
<summary><b>💡 Lihat kode</b></summary>

```java
// Overloading konstruktor (model/Admin.java)
public Admin() {}

public Admin(int idPengguna, String namaLengkap, String username, String password,
             String statusAktif, String cakupanTugas, String keteranganDetail) {
    super(idPengguna, namaLengkap, username, password, "Admin", statusAktif);
    // ...
}
```

```java
// Overriding method interface (view/TellerTarikTunai.java)
txtSearch.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) { filterSearch(); }
    @Override
    public void removeUpdate(DocumentEvent e) { filterSearch(); }
    @Override
    public void changedUpdate(DocumentEvent e) { filterSearch(); }
});
```

</details>

### 7.4 Abstraction (Abstraksi)

Detail teknis disembunyikan di balik antarmuka yang sederhana:

- **Lapisan DAO** menyembunyikan SQL, `PreparedStatement`, dan pengelolaan transaksi. Pemanggil cukup memakai method seperti `tarikTunai(...)`, `tambahAngsuran(...)`, atau `putuskanPembiayaan(...)`.
- **`AuthService`** menyembunyikan proses autentikasi dan penguncian akun dan hanya mengembalikan `LoginResult`.
- **`DatabaseConnection`** menyembunyikan konfigurasi driver dan URL, sehingga class lain cukup memanggil `getConnection()`.
- **Nested class `Record`** pada DAO (`AngsuranRecord`, `PembiayaanRecord`, dst.) mengemas hasil query join menjadi objek siap pakai.

<details>
<summary><b>💡 Lihat kode</b></summary>

```java
// Pemanggil tidak perlu tahu SQL, penguncian baris, maupun commit/rollback
RekeningTransaksiDAO dao = new RekeningTransaksiDAO();
dao.tarikTunai("10012026011", new BigDecimal("1850000"), 5);
```

```java
// dao/RekeningTransaksiDAO.java (ringkasan alur tarikTunai)
conn.setAutoCommit(false);
try {
    // 1. SELECT saldo, status ... FOR UPDATE
    // 2. validasi: rekening Aktif dan saldo cukup
    // 3. UPDATE saldo, INSERT transaksi_tabungan
    conn.commit();
} catch (Exception ex) {
    conn.rollback();
    throw ex;
}
```

</details>

## 8. Cara Penggunaan Program

### 8.1 Prasyarat

- JDK 17 atau lebih baru
- Apache Maven (atau Apache NetBeans yang sudah menyertakan Maven)
- MySQL 8 (misalnya melalui [Laragon](https://laragon.org/))

### 8.2 Menyiapkan Database

1. Jalankan MySQL, lalu buat database:
   ```sql
   CREATE DATABASE brinkmikro;
   ```
2. Buat tabel dan isi data contoh dengan menjalankan skrip SQL project (DDL dan DML sesuai Bab III laporan).
3. Samakan konfigurasi koneksi pada `config/DatabaseConnection.java` dengan pengaturan MySQL Anda:
   ```java
   private static final String DB_URL = "jdbc:mysql://localhost:3306/brinkmikro?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
   private static final String USER = "root";
   private static final String PASSWORD = "";
   ```

### 8.3 Menjalankan Aplikasi

**Melalui NetBeans:** buka project sebagai Maven Project, lalu jalankan class `view.FormLogin`.

**Melalui terminal:**

```bash
git clone https://github.com/Mikhelfebian/BrinkMikro.git
cd BrinkMikro
mvn clean compile
mvn exec:java
```

Untuk menguji koneksi dan backend tanpa GUI, jalankan class `BrinkmikroApp`. Program akan mencetak jumlah nasabah, rekening, pengajuan, dan ringkasan kas ke konsol.

### 8.4 Akun Demo

Gunakan akun berikut (data contoh):

| Role | Username | Password |
|---|---|---|
| Admin | `admin_mikhel` | `admin123` |
| Teller | `teller_siti` | `teller123` |
| Manajer | `manajer_bambang` | `manajer123` |

### 8.5 Halaman Login

Pilih **role**, isi **username** dan **password**, lalu klik **Login**. Centang *Show Password* untuk menampilkan kata sandi, dan klik **Reset** untuk mengosongkan isian. Jika kredensial atau role tidak sesuai, sistem menampilkan pesan peringatan.

![Halaman Login](docs/screenshots/login.png)

### 8.6 Role Admin

Setelah login, Admin masuk ke **Data Pengguna** dan dapat berpindah menu lewat panel navigasi di sisi kiri (tujuh menu).

| Menu | Cara Pakai | Screenshot |
|---|---|---|
| **Data Pengguna** | Cari username/nama, lalu tambah, ubah, atau hapus pengguna | ![](docs/screenshots/admin-pengguna.png) |
| **Data Nasabah** | Kelola data induk nasabah (NIK, nama, jenis, telepon, alamat) | ![](docs/screenshots/admin-nasabah.png) |
| **Rekening Tabungan** | Buka rekening, ubah status, atau tutup rekening | ![](docs/screenshots/admin-rekening.png) |
| **Transaksi Tabungan** | Catat setor atau tarik tunai | ![](docs/screenshots/admin-transaksi.png) |
| **Pengajuan Pembiayaan** | Input pengajuan baru dan verifikasi berkas | ![](docs/screenshots/admin-pengajuan.png) |
| **Data Angsuran** | Catat tagihan dan pembayaran angsuran | ![](docs/screenshots/admin-angsuran.png) |
| **Monitoring dan Laporan** | Lihat audit log dan ringkasan operasional | ![](docs/screenshots/admin-monitoring.png) |

### 8.7 Role Teller

Teller langsung menuju **Data Nasabah** dan memiliki lima menu layanan loket.

| Menu | Cara Pakai | Screenshot |
|---|---|---|
| **Data Nasabah** | Daftarkan nasabah baru dan perbarui datanya | ![](docs/screenshots/teller-nasabah.png) |
| **Rekening Tabungan** | Cek saldo dan mutasi rekening | ![](docs/screenshots/teller-rekening.png) |
| **Setor Tunai** | Input nomor rekening dan nominal setoran | ![](docs/screenshots/teller-setor.png) |
| **Tarik Tunai** | Input nomor rekening dan nominal penarikan | ![](docs/screenshots/teller-tarik.png) |
| **Pembayaran Angsuran** | Terima pembayaran angsuran berdasarkan nomor pembiayaan | ![](docs/screenshots/teller-angsuran.png) |

### 8.8 Role Manajer

Manajer langsung menuju **Dashboard Kas** dan memiliki enam menu pemantauan.

| Menu | Cara Pakai | Screenshot |
|---|---|---|
| **Dashboard Kas** | Pantau posisi kas dan likuiditas | ![](docs/screenshots/manajer-dashboard.png) |
| **Jatuh Tempo** | Pantau pembiayaan jatuh tempo dan tunggakan | ![](docs/screenshots/manajer-jatuhtempo.png) |
| **Persetujuan Pembiayaan** | Pilih pengajuan, lalu setujui atau tolak | ![](docs/screenshots/manajer-persetujuan.png) |
| **Rekap Laporan** | Lihat rekap laporan keuangan dan operasional | ![](docs/screenshots/manajer-rekap.png) |
| **Riwayat Transaksi** | Telusuri seluruh riwayat transaksi sistem | ![](docs/screenshots/manajer-riwayat.png) |
| **Master Tabel Angsuran** | Lihat seluruh angsuran nasabah | ![](docs/screenshots/manajer-angsuran.png) |

### 8.9 Fungsi Tombol Umum

| Komponen | Fungsi |
|---|---|
| **Tombol tambah** (`+ ...`) | Membuka dialog input data baru. Semua kolom wajib diisi |
| **Tombol ubah** (`Update ...`) | Mengubah baris yang dipilih pada tabel |
| **Tombol hapus** (`Hapus ...`) | Menghapus baris yang dipilih setelah konfirmasi |
| **Kolom Cari** | Menyaring tabel secara langsung saat mengetik |
| **Menu Navigasi** | Berpindah antarform sesuai hak akses role |
| **Logout** | Kembali ke halaman login |

## 9. Kontributor

| Nama | NIM |
|---|---|
| **Kenny Giovanni Gavra** | 2509116003 |
| **Mikhel Febian** | 2509116056 |
| **Muhammad Nadhir Sultan Azzaky** | 2509116080 |
| **Adelia Githa Naveezha Hermawan** | 2509116110 |

<p align="center">
  Program Studi Sistem Informasi · Fakultas Teknik · Universitas Mulawarman · 2026
</p>

<hr>

<p align="center"><b>BrinkMikro</b> · Java Swing · MySQL · JDBC · Object-Oriented Programming</p>
