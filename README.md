# Splash Screen Lottie Compose Animation

Proyek ini menunjukkan cara membuat splash screen menggunakan animasi Lottie di aplikasi Android dengan Kotlin dan Jetpack Compose.

## Prasyarat

Pastikan Anda sudah menginstal:

- Android Studio
- Plugin Kotlin
- Plugin Compose

## Langkah-langkah Implementasi

### 1. Tambahkan Dependency

Tambahkan dependensi Lottie dan Jetpack Compose di `build.gradle` aplikasi Anda:

```kotlin
dependencies {
    implementation ("com.airbnb.android:lottie-compose:4.0.0")
}
```
### 2. Siapkan File Animasi Lottie

1. Buat folder raw di dalam direktori res.
2. Tempatkan file Lottie JSON (lottie_animation.json) di dalam folder res/raw.

### 3. Edit File MainActivity.kt
Ubah file `MainActivity.kt` dengan konten berikut:

```kotlin
package com.bagusrizki.splashscreenlottieanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bagusrizki.splashscreenlottieanimation.ui.theme.SplashScreenLottieAnimationTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val showSplash = remember { mutableStateOf(true) }

            MyApp {
                if (showSplash.value) {
                    MySplashScreen {
                        showSplash.value = false // Ubah state untuk menyembunyikan splash screen
                    }
                } else {
                    MainScreen() // Layar utama aplikasi
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    // Implementasikan layar utama aplikasi Anda di sini
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Composable
fun MySplashScreen(navigateToMain: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))

        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever
        )

        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            scope.launch {
                delay(3000) // Delay selama 3 detik
                navigateToMain() // Panggil fungsi untuk navigasi ke layar utama
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SplashScreenLottieAnimationTheme {
        MySplashScreen {}
    }
}

```

### 4. Jalankan Aplikasi
Buka Android Studio dan jalankan aplikasi Anda. Anda akan melihat splash screen dengan animasi Lottie diikuti oleh layar utama aplikasi Anda.
