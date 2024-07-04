package com.bagusrizki.splashscreenlottieanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            // State untuk mengontrol apakah splash screen masih ditampilkan
            val showSplash = remember { mutableStateOf(true) }

            // Memanggil fungsi MyApp dan menampilkan layar yang sesuai berdasarkan state showSplash
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Main Screen")
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    // Menggunakan tema MaterialTheme dari Material3
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
        modifier = Modifier.fillMaxSize().padding(84.dp),
        contentAlignment = Alignment.Center
    ) {
        // Mengambil komposisi Lottie dari asset yang terletak di folder res/raw
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_animation))

        // Menampilkan animasi Lottie dengan pengulangan terus-menerus
        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever
        )

        // Coroutine scope untuk mengontrol waktu delay sebelum navigasi
        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            scope.launch {
                delay(2500) // Delay selama 2.5 detik
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
