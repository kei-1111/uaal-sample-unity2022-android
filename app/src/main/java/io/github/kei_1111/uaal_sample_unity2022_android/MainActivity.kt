package io.github.kei_1111.uaal_sample_unity2022_android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.unity3d.player.UnityPlayer
import com.unity3d.player.UnityPlayer.UnitySendMessage
import io.github.kei_1111.uaal_sample_unity2022_android.ui.theme.UaalsampleTheme
import java.lang.ref.WeakReference

class MainActivity : ComponentActivity(), UnityMessageReceiver.Listener {

    private var unityPlayer: UnityPlayer? = null

    init {
        UnityMessageReceiver.listener = WeakReference(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        unityPlayer = UnityPlayer(this)

        setContent {
            UaalsampleTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AndroidView(
                        factory = { unityPlayer!! },
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeGesturesPadding()
                    ) {
                        Button(
                            onClick = {
                                UnitySendMessage(UnityObject.SAMPLE_MANAGER.objectName, UnityMethod.SPAWN_OBJECT.methodName, "")
                            },
                            modifier = Modifier.align(Alignment.BottomCenter)
                        ) {
                            Text(
                                text = "Send Message to Unity"
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onMessageReceived(message: String) {
        Toast.makeText(this, message + "個落としました", Toast.LENGTH_SHORT).show()
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
