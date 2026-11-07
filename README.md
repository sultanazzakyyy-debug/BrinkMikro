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
4. [Tech Stack (Bahasa, database, framework, library)](#4-tech-stack-bahasa-database-framework-library)
5. [Perancangan Sistem (Flowchart, Use Case, ERD)](#5-perancangan-sistem-flowchart-use-case-erd)
6. [Struktur Folder/Package](#6-struktur-folderpackage)
7. [Penjelasan Penerapan 4 Pilar OOP di dalam program](#7-penjelasan-penerapan-4-pilar-oop-di-dalam-program)
8. [Cara Penggunaan Program (sertakan screenshot GUI)](#8-cara-penggunaan-program-sertakan-screenshot-gui)
9. [Kontributor (Nama dan NIM)](#9-kontributor-nama-dan-nim)

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

## 4. Tech Stack (Bahasa, database, framework, library)
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

## 5. Perancangan Sistem (Flowchart, Use Case, ERD)
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

<img width="1128" height="1556" alt="Use Case BrinkMikro drawio (3)" src="https://github.com/user-attachments/assets/a141e360-83a9-4094-a02a-9456dfa94af8" />

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

## 7. Penjelasan Penerapan 4 Pilar OOP di dalam program
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

## 8. Cara Penggunaan Program (sertakan screenshot GUI)
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

Halaman Login adalah pintu masuk seluruh pengguna. Dari sini pengguna diarahkan ke halaman sesuai role: Admin ke **Data Pengguna**, Teller ke **Data Nasabah**, dan Manajer ke **Dashboard Kas**.

<p align="center">
  <img src="docs/screenshots/login.png" alt="Halaman Login" width="700">
</p>

**Langkah penggunaan:**

1. Jalankan aplikasi hingga halaman **BrinkMikro** muncul.
2. Isi kolom **Username / ID Teller**.
3. Isi kolom **Password**. Centang **Tampilkan Password** bila ingin melihat karakter yang diketik.
4. Pilih role pada dropdown (**Admin**, **Teller**, atau **Manajer**).
5. Klik **Login**. Jika berhasil, muncul pesan "Login Berhasil" lalu halaman utama role terbuka.
6. Jika username, password, atau role tidak sesuai, muncul pesan peringatan dan Anda tetap berada di halaman login. Jika database tidak dapat dihubungi, muncul pesan "Koneksi ke database terputus".
7. Klik **Reset** untuk mengosongkan seluruh isian.

> 📝 **Catatan:** pada versi saat ini, data pada tabel di setiap form disimpan di memori selama aplikasi berjalan dan integrasinya ke database masih dalam pengerjaan. Logika penyimpanan ke database sudah tersedia di package `dao`.

### 8.6 Role Admin

Admin memiliki tujuh menu yang selalu tersedia pada panel *Menu Navigasi* di sisi kiri. Tombol **Logout** di bagian bawah panel mengembalikan Admin ke halaman login setelah konfirmasi.

#### 8.6.1 Data Pengguna

Halaman pertama yang terbuka setelah login sebagai Admin. Digunakan untuk mengelola akun yang dapat masuk ke sistem beserta hak aksesnya.

<p align="center">
  <img src="docs/screenshots/admin-pengguna.png" alt="Data Pengguna" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Data Pengguna** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **ID User**, **Username**, **Password**, **Nama Lengkap**, **Role / Akses**, **Status Akun**, **Tanggal Dibuat**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari Username/Nama**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Data Pengguna**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Pengguna**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Pengguna**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.6.2 Data Nasabah

Digunakan untuk mengelola data induk nasabah, baik petani maupun pedagang UMKM.

<p align="center">
  <img src="docs/screenshots/admin-nasabah.png" alt="Data Nasabah" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Data Nasabah** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **NIK**, **Nama Nasabah**, **Jenis Nasabah**, **No. Telepon**, **Alamat Domisili**, **Tanggal Daftar**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari NIK/Nama**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Data Nasabah**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Nasabah**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Nasabah**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.6.3 Rekening Tabungan

Digunakan untuk membuka rekening nasabah, mengatur status rekening, dan menutup rekening.

<p align="center">
  <img src="docs/screenshots/admin-rekening.png" alt="Rekening Tabungan" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Rekening Tabungan** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **No. Rekening**, **NIK Nasabah**, **Saldo (Rp)**, **Status Rekening**, **Tgl Pembukaan**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari No. Rek/Nama**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Buka Rekening**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Status Rekening**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Tutup Rekening**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.6.4 Transaksi Tabungan

Digunakan untuk mencatat transaksi setor dan tarik tunai pada rekening nasabah.

<p align="center">
  <img src="docs/screenshots/admin-transaksi.png" alt="Transaksi Tabungan" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Transaksi Tabungan** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **ID Transaksi**, **No. Rekening**, **Jenis Transaksi**, **Jumlah (Rp)**, **Waktu Transaksi**, **ID Teller**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari ID TRX/No. Rek**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Setor / Tarik Tunai**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Transaksi**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Transaksi**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.6.5 Pengajuan Pembiayaan

Digunakan untuk mencatat pengajuan pembiayaan petani dan UMKM serta memverifikasi berkasnya.

<p align="center">
  <img src="docs/screenshots/admin-pengajuan.png" alt="Pengajuan Pembiayaan" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Pengajuan Pembiayaan** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **ID Pembiayaan**, **NIK Nasabah**, **Jumlah Pengajuan**, **Jangka Waktu (Bln)**, **Skema Pembiayaan**, **Status Pembiayaan**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari No. Pengajuan/NIK**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Pengajuan Baru**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Verifikasi Berkas**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Batal Pengajuan**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.6.6 Data Angsuran

Digunakan untuk mencatat tagihan dan pembayaran angsuran pembiayaan.

<p align="center">
  <img src="docs/screenshots/admin-angsuran.png" alt="Data Angsuran" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Data Angsuran** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **ID Angsuran**, **ID Pembiayaan**, **Angsuran Ke**, **Jumlah Bayar (Rp)**, **Denda (Rp)**, **Tanggal Bayar**, **ID Teller**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari ID Angsuran/Pembiayaan**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Bayar Angsuran**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Tagihan**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Entry**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.6.7 Monitoring dan Laporan

Digunakan untuk memantau audit log dan ringkasan operasional sistem.

<p align="center">
  <img src="docs/screenshots/admin-monitoring.png" alt="Monitoring dan Laporan" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Monitoring dan Laporan** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **Timestamp**, **User / Operator**, **Modul**, **Aktivitas System**, **IP Address**, **Status Audit**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari Activity/User**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Log Baru**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Audit Log**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Log**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


### 8.7 Role Teller

Teller memiliki lima menu layanan loket pada panel *Menu Navigasi*. Tombol **Logout** mengembalikan Teller ke halaman login setelah konfirmasi.

#### 8.7.1 Data Nasabah

Halaman pertama yang terbuka setelah login sebagai Teller. Digunakan untuk mendaftarkan dan memperbarui data nasabah di loket.

<p align="center">
  <img src="docs/screenshots/teller-nasabah.png" alt="Data Nasabah" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Data Nasabah** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **NIK**, **Nama Lengkap**, **Kategori Nasabah**, **No. Telepon**, **Alamat Domisili**, **Tanggal Daftar**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari NIK/Nama**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Nasabah Baru**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Data Nasabah**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Nasabah**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.7.2 Rekening Tabungan

Digunakan untuk memeriksa saldo dan mutasi rekening nasabah.

<p align="center">
  <img src="docs/screenshots/teller-rekening.png" alt="Rekening Tabungan" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Rekening Tabungan** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **Waktu Transaksi**, **ID Transaksi**, **No. Rekening**, **Jenis Mutasi**, **Kredit (Rp)**, **Debet (Rp)**, **Saldo Akhir**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari No. Rek/Nama**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Mutasi Baru**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Mutasi**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Record**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.7.3 Setor Tunai

Digunakan untuk melayani setoran tunai nasabah di loket.

<p align="center">
  <img src="docs/screenshots/teller-setor.png" alt="Setor Tunai" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Setor Tunai** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **ID Setor**, **No. Rekening**, **Nama Nasabah**, **Nominal Setoran (Rp)**, **Petugas Teller**, **Tanggal**, **Status**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari Kode/No. Rek**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Setor Tunai Baru**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Setoran**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Setoran**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.7.4 Tarik Tunai

Digunakan untuk melayani penarikan tunai nasabah di loket.

<p align="center">
  <img src="docs/screenshots/teller-tarik.png" alt="Tarik Tunai" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Tarik Tunai** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **ID Tarik**, **No. Rekening**, **Nama Nasabah**, **Nominal Penarikan (Rp)**, **Verifikasi PIN**, **Tanggal**, **Status**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari Kode/No. Rek**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Penarikan Baru**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Penarikan**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Penarikan**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.7.5 Pembayaran Angsuran

Digunakan untuk menerima pembayaran angsuran pembiayaan nasabah.

<p align="center">
  <img src="docs/screenshots/teller-angsuran.png" alt="Pembayaran Angsuran" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Pembayaran Angsuran** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **ID Pembiayaan**, **Nama Nasabah**, **Angsuran Ke**, **Tgl Jatuh Tempo**, **Nominal Tagihan**, **Denda**, **Status Pembayaran**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari No. Pembiayaan**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Bayar Angsuran**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Bukti Bayar**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Pembayaran**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


### 8.8 Role Manajer

Manajer memiliki enam menu pemantauan dan persetujuan pada panel *Menu Navigasi*. Tombol **Logout** mengembalikan Manajer ke halaman login setelah konfirmasi.

#### 8.8.1 Dashboard Kas

Halaman pertama yang terbuka setelah login sebagai Manajer. Menampilkan posisi kas dan likuiditas.

<p align="center">
  <img src="docs/screenshots/manajer-dashboard.png" alt="Dashboard Kas" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Dashboard Kas** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **Unit / Vault Kas**, **Saldo Awal**, **Inflow (Masuk)**, **Outflow (Keluar)**, **Saldo Akhir**, **Status Likuiditas**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari Unit/Vault**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Vault / Kas Baru**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak.
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Position Kas**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Vault**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.8.2 Jatuh Tempo

Digunakan untuk memantau pembiayaan yang jatuh tempo, tunggakan, dan kolektibilitasnya.

<p align="center">
  <img src="docs/screenshots/manajer-jatuhtempo.png" alt="Jatuh Tempo" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Jatuh Tempo** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **ID Pembiayaan**, **Nama Nasabah**, **Sektor Usaha**, **Tgl Jatuh Tempo**, **Tunggakan (Rp)**, **Keterlambatan**, **Kolektibilitas**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari Nasabah/NPL**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Tambah Data NPL**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Status NPL**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Data NPL**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.8.3 Persetujuan Pembiayaan

Digunakan Manajer untuk meninjau pengajuan dan menentukan status persetujuannya.

<p align="center">
  <img src="docs/screenshots/manajer-persetujuan.png" alt="Persetujuan Pembiayaan" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Persetujuan Pembiayaan** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **ID Pembiayaan**, **NIK Nasabah**, **Nama Nasabah**, **Sektor Usaha**, **Plafon Diminta**, **Tenor**, **Status Approval**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari No. Registrasi**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Pengajuan Komite**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak.
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Approval Status**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Pengajuan**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.8.4 Rekap Laporan

Digunakan untuk melihat dan menyusun rekap laporan keuangan dan operasional periodik.

<p align="center">
  <img src="docs/screenshots/manajer-rekap.png" alt="Rekap Laporan" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Rekap Laporan** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **Kode Laporan**, **Nama Laporan**, **Periode Laporan**, **Dibuat Oleh**, **Tanggal Generate**, **Format Output**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari Laporan**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Generate Laporan Baru**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Detail Laporan**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Laporan**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.8.5 Riwayat Transaksi

Digunakan untuk menelusuri audit trail seluruh transaksi sistem.

<p align="center">
  <img src="docs/screenshots/manajer-riwayat.png" alt="Riwayat Transaksi" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Riwayat Transaksi** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **ID Transaksi**, **Waktu**, **Operator / Teller**, **Modul System**, **Deskripsi Transaksi**, **Nominal (Rp)**, **Status**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari ID TRX/User**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Entry Log Manual**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak. Kolom tanggal/waktu wajib memakai format `dd-MM-yyyy` (contoh `10-01-2026`).
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Status Log**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Log Transaksi**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


#### 8.8.6 Master Tabel Angsuran

Digunakan untuk melihat seluruh data angsuran semua nasabah.

<p align="center">
  <img src="docs/screenshots/manajer-angsuran.png" alt="Master Tabel Angsuran" width="850">
</p>

**Langkah penggunaan:**

1. Klik menu **Master Tabel Angsuran** pada panel *Menu Navigasi* di sisi kiri layar.
2. Tabel menampilkan kolom **ID Angsuran**, **ID Pembiayaan**, **Nama Nasabah**, **Angsuran Ke**, **Nominal (Rp)**, **Jatuh Tempo**, **Status Pembayaran**.
3. Untuk mencari data, ketik kata kunci pada kolom **Cari No. Pembiayaan**. Tabel langsung tersaring saat Anda mengetik.
4. Untuk menambah data, klik tombol **+ Schedule Angsuran**. Isi seluruh kolom pada dialog yang muncul, lalu klik **OK**. Kolom yang kosong akan ditolak.
5. Untuk mengubah data, klik satu baris pada tabel, klik **Update Status Angsuran**, ubah isian pada dialog, lalu klik **OK**.
6. Untuk menghapus data, klik satu baris pada tabel, klik **Hapus Schedule**, lalu pilih **Yes** pada dialog konfirmasi.
7. Jika tombol ubah atau hapus ditekan tanpa memilih baris, sistem menampilkan peringatan agar Anda memilih baris terlebih dahulu.


## 9. Kontributor (Nama dan NIM)
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
