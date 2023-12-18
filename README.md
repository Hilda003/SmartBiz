# SmartBiz API

A brief description of what this project does and who it's for





### Sign Up
#### Method: POST
#### URL: /signup
#### Body:
```json
{
  "username": "nama_pengguna",
  "email": "email@contoh.com",
  "password": "kata_sandi"
}
```
#### Response:

##### Success (HTTP 201)
```json
{
  "status": "success",
  "message": "Pendaftaran berhasil"
}
```
##### Error (HTTP 400)                                       
```json
{
  "status": "error",
  "message": "Username atau email sudah terdaftar"
}
```
##### Error (HTTP 500)                                       
```json
{
  "status": "error",
  "message": "Terjadi kesalahan internal"
}
```

### Forgot Password
#### Method: POST
#### URL: `/forgot-password
#### Body:
```json
{
  "email": "contoh@email.com"
}

```
#### Response:

##### Success (HTTP 200)                                        
```json
{
  "status": "success",
  "message": "Reset token sent successfully"
}

```
##### Error (HTTP 500)                                      
```json
{
  "status": "error",
  "message": "Internal Server Error"
}
```

### Reset Password
#### Method: POST
#### URL: `/reset-password
#### Body:
```json
{
  "email": "contoh@email.com",
  "token": "token_yang_anda_terima_melalui_email",
  "newPassword": "password_baru"
}

```
#### Response:

##### Success (HTTP 200)                                        
```json
{
  "status": "success",
  "message": "Password reset successfully"
}

```
##### Error (HTTP 500)                                      
```json
{
  "status": "error",
  "message": "Internal Server Error"
}
```
##### Error (HTTP 400)                                      
```json
{
  "status": "error",
  "message": "Invalid token"
}
```

### Login
#### Method: POST
#### URL: `/login
#### Body:
```json
{
  "username": "nama_pengguna",
  "password": "kata_sandi"
}

```
#### Response:

##### Success (HTTP 200)                                        
```json
{
  "status": "success",
  "message": "Login berhasil"
}

```
##### Error (HTTP 401)                                      
```json
{
  "status": "error",
  "message": "Login gagal"
}
```

### Input Barang
#### Method: POST
#### URL: `/input_barang
#### Body:
```json
{
  "userId": 1,
  "namaBarang": "Nama Barang",
  "hargaBarang": 100,
  "jumlahBarang": 10
}

```
#### Response:

##### Success (HTTP 201)                                         
```json
{
  "status": "success",
  "message": "Input barang berhasil"
}

```
##### Error (HTTP 404)                                     
```json
{
  "status": "error",
  "message": "Pengguna tidak ditemukan"
}
```
##### Error (HTTP 500)                                     
```json
{
  "status": "error",
  "message": "Terjadi kesalahan internal"
}
```

### Create Income
#### Method: POST
#### URL: `/create_income
#### Body:
```json
{
  "userId": 1,
  "tanggal": "2023-01-01",
  "jam": "12:00",
  "barangId": 1,
  "jumlahBarang": 5
}

```
#### Response:

##### Success (HTTP 201)                                         
```json
{
  "status": "success",
  "message": "Create Income berhasil"
}

```
##### Error (HTTP 404)                                     
```json
{
  "status": "error",
  "message": "Barang tidak ditemukan"
}
```
##### Error (HTTP 400)                                     
```json
{
  "status": "error",
  "message": "Stok barang tidak cukup untuk penjualan"
}
```
##### Error (HTTP 500)                                     
```json
{
  "status": "error",
  "message": "Terjadi kesalahan internal"
}
```

### Create Expense
#### Method: POST
#### URL: `/create_expense
#### Body:
```json
{
  "userId": 1,
  "tanggal": "2023-01-01",
  "jam": "12:00",
  "barangId": 1,
  "jumlahBarang": 3,
  "hargaBarang": 50
}

```
#### Response:

##### Success (HTTP 201)                                         
```json
{
  "status": "success",
  "message": "Create Expense berhasil"
}

```
##### Error (HTTP 500)                                     
```json
{
  "status": "error",
  "message": "Terjadi kesalahan internal"
}
```

### Total Profit
#### Method: GET
#### URL: `/total_profit/{userId}

#### Response:

##### Success (HTTP 200)                                         
```json
{
  "status": "success",
  "data": {
    "user_id": 1,
    "total_income": 1000,
    "total_expense": 500,
    "total_profit": 500
  }
}

```
##### Error (HTTP 404)                                     
```json
{
  "status": "error",
  "message": "Profit information not found"
}
```
##### Error (HTTP 500)                                     
```json
{
  "status": "error",
  "message": "Internal Server Error"
}
```

### Edit Barang
#### Method: PUT
#### URL: `/edit_barang/{barangId}
#### Body:
```json
{
  "namaBarang": "string",
  "hargaBarang": 150
}

```
#### Response:

##### Success (HTTP 200)                                         
```json
{
  "status": "success",
  "message": "Edit barang berhasil"
}


```
##### Error (HTTP 404)                                     
```json
{
  "status": "error",
  "message": "Barang tidak ditemukan"
}
```
##### Error (HTTP 500)                                     
```json
{
  "status": "error",
  "message": "Internal Server Error"
}
```

### Delete Barang
#### Method: DELETE
#### URL: `/delete_barang/{barangId}

#### Response:

##### Success (HTTP 200)                                         
```json
{
  "status": "success",
  "message": "Hapus barang berhasil"
}


```
##### Error (HTTP 404)                                     
```json
{
  "status": "error",
  "message": "Barang tidak ditemukan"
}
```
##### Error (HTTP 500)                                     
```json
{
  "status": "error",
  "message": "Internal Server Error"
}
```

### Add Barang
#### Method: POST
#### URL: `/add_barang
#### Body:
```json
{
  "userId": 1,
  "namaBarang": "string",
  "hargaBarang": 120,
  "jumlahBarang": 15
}

```
#### Response:

##### Success (HTTP 201)                                         
```json
{
  "status": "success",
  "message": "Tambah barang berhasil"
}


```
##### Error (HTTP 404)                                     
```json
{
  "status": "error",
  "message": "Pengguna tidak ditemukan"
}
```
##### Error (HTTP 500)                                     
```json
{
  "status": "error",
  "message": "Internal Server Error"
}
```





