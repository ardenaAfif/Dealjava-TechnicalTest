<h1 align="center">
  Dealjava Technical Test - Cooking Game
</h1>

## Overview

Aplikasi Android yang dirancang dengan fitur gamifikasi interaktif. Aplikasi ini menerapkan sistem pengumpulan kartu bahan dan penggabungan kartu untuk membuka resep masakan.  Animasi Lottie digunakan untuk meningkatkan pengalaman visual saat membuka kartu, menggabungkan bahan, dan menampilkan hasil kombinasi (berhasil/gagal).

## Key Features

1. **Card Pack System:**
    * Pengguna dapat membuka paket berisi 5 Kartu Bahan (Ingredient Cards).  Terdapat beberapa paket bahan yang bisa dibuka.

2. **Combination System:**
    * Pengguna dapat menggabungkan dua Kartu Bahan untuk mencoba membuka Resep.
    * Kombinasi yang gagal mengakibatkan hilangnya kartu bahan yang digunakan.
    * Kombinasi yang berhasil membuka Resep dan memberikan Kartu Makanan (Food Card).
    * Resep yang telah dibuka akan tersimpan permanen di Recipe Library.

3. **Recipe Library:**
    * Menyimpan semua resep yang berhasil dibuka.
    * Berfungsi sebagai shortcut untuk menggabungkan Kartu Bahan yang sudah diketahui kombinasinya.

4. **Lottie Animations:**
    * Terintegrasi animasi Lottie untuk membuka kartu, efek penggabungan, dan hasil berhasil/gagal.


## Application Screenshots

Berikut adalah beberapa screenshot dari aplikasi:

| Screenshot | Keterangan |
|------------|------------|
| <img src="https://github.com/ardenaAfif/Dealjava-TechnicalTest/blob/master/assets/home.jpg" width="200"> | Tampilan halaman utama aplikasi, menampilkan menu *Ingredients*, *Combination Recipe*, dan *Recipe Library*. |
| <img src="https://github.com/ardenaAfif/Dealjava-TechnicalTest/blob/master/assets/success.jpg" width="200"> | Tampilan halaman setelah resep berhasil dibuat, menampilkan nama resep (Roti Bakar Keju), gambar makanan, deskripsi singkat, dan tombol *OK*. |
| <img src="https://github.com/ardenaAfif/Dealjava-TechnicalTest/blob/master/assets/combine.jpg" width="200"> | Tampilan halaman *Combination Recipe*, menampilkan kartu-kartu bahan yang bisa dikombinasikan. Terlihat dua kartu, Nasi dan Bawang Putih, sedang dalam proses kombinasi. |
| <img src="https://github.com/ardenaAfif/Dealjava-TechnicalTest/blob/master/assets/ingredient.jpg" width="200"> | Tampilan halaman *Ingredients*, menampilkan beberapa *Box Ingredient* yang bisa dibuka untuk mendapatkan kartu bahan. |
| <img src="https://github.com/ardenaAfif/Dealjava-TechnicalTest/blob/master/assets/open%20box.jpg" width="200"> | Tampilan isi dari *Box Ingredient 2*, menampilkan kartu-kartu bahan yang terdapat di dalamnya, seperti Tomat, Garam, Keju, Susu, dan Telur. Terdapat juga tombol *Claim All*. |


## Libraries

1. **LottieFiles** - [Documentation](https://developers.lottiefiles.com/docs/dotlottie-player/dotlottie-android/)
   - A library for integrating LottieFiles animations into Android applications, allowing for lightweight and efficient animation playback.

2. **Glide** - [Documentation](https://github.com/bumptech/glide)
   - A library for displaying and manipulating images easily and efficiently in Android applications. Supports caching, resizing, and loading from various image sources.

3. **Shimmer Effect** - [Documentation](https://github.com/facebook/shimmer-android)
   - Provides a shimmer effect that animates UI loading by providing a sparkling animation effect on UI elements.

4. **Dagger Hilt** - [Documentation](https://developer.android.com/training/dependency-injection/hilt-android)
   - A dependency injection framework that simplifies integration with Android applications, replacing Dagger 2 with easier-to-use features.

5. **Kotlin Coroutines with Firebase** - [Documentation](https://github.com/Kotlin/kotlinx.coroutines)
   - Kotlin Coroutines is a framework for asynchronous and concurrency management in Kotlin. This feature allows using Firebase with coroutines.
   - Implementation:
     - `implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.5.2")`

6. **AndroidX Activity** - [Documentation](https://developer.android.com/jetpack/androidx/releases/activity)
   - The Activity component from AndroidX, which provides the latest features and bug fixes related to Activity on Android.

7. **AndroidX Core KTX** - [Documentation](https://developer.android.com/kotlin/ktx)
   - Kotlin Extensions for AndroidX Core, provides Kotlin features that are more expressive and easy to use in Android application development.

8. **AndroidX AppCompat** - [Documentation](https://developer.android.com/jetpack/androidx/releases/appcompat)
   - The AndroidX library provides compatibility with previous versions of Android, making it easier to develop applications with the latest features.

9. **Material Components for Android** - [Documentation](https://github.com/material-components/material-components-android)
   - A library for UI design that follows Google's Material Design guidelines, providing consistent and interactive UI components.

10. **AndroidX Constraint Layout** - [Documentation](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout)
    - Flexible and powerful layout for building complex UI layouts by providing constraint-based features to determine the position and size of UI elements.
