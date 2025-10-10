package io.github.kei_1111.uaal_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.unity3d.player.UnityPlayer
import io.github.kei_1111.uaal_sample.ui.theme.UaalsampleTheme

class MainActivity : ComponentActivity() {

    private var unityPlayer: UnityPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        unityPlayer = UnityPlayer(this)

        setContent {
            UaalsampleTheme {
                AndroidView(
                    factory = { unityPlayer!! },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        unityPlayer?.windowFocusChanged(hasFocus)
    }

    override fun onResume() {
        super.onResume()
        unityPlayer?.resume()
    }

    override fun onPause() {
        super.onPause()
        unityPlayer?.pause()
    }
}
