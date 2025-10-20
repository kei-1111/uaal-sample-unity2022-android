package io.github.kei_1111.uaal_sample_unity2022

import java.lang.ref.WeakReference

object UnityMessageReceiver {
    interface Listener {
        fun onMessageReceived(message: String)
    }

    var listener: WeakReference<Listener>? = null

    fun sendMessage(message: String) {
        listener?.get()?.onMessageReceived(message)
    }
}
