package com.example.dp24

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.AudioFormat
import android.media.AudioTrack
import android.media.PlaybackParams
import android.media.audiofx.Equalizer
import androidx.media.AudioAttributesCompat
import androidx.media.AudioAttributesCompat.CONTENT_TYPE_MUSIC
import androidx.media.AudioAttributesCompat.USAGE_MEDIA
import java.io.FileInputStream
import java.nio.channels.FileChannel
import java.util.concurrent.Executors

class AudioPlayer(context: Context, fileDescriptor: AssetFileDescriptor) {
    private val audioTrack: AudioTrack
    private val audioAttributes: AudioAttributesCompat
    private val equalizer: Equalizer?
    private val executor = Executors.newSingleThreadExecutor()

    init {
        audioAttributes = AudioAttributesCompat.Builder()
            .setContentType(CONTENT_TYPE_MUSIC)
            .setUsage(USAGE_MEDIA)
            .build()

        val audioFormat = AudioFormat.Builder()
            .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setSampleRate(48000)
            .build()

        audioTrack = AudioTrack.Builder()
//            .setAudioAttributes(audioAttributes)
            .setAudioFormat(audioFormat)
            .setBufferSizeInBytes(2048)
            .setTransferMode(AudioTrack.MODE_STREAM)
            .build()

        // create an equalizer object
        equalizer = Equalizer(0, audioTrack.audioSessionId).apply {
            enabled = true // enable equalizer
            for (i in 0 until numberOfBands) {
                // set the gain of all bands to 0dB
                setBandLevel(i.toShort(), 0)
            }
        }

        // play audio in a separate thread
        executor.execute {
            // read audio data from fileDescriptor
            val channel = FileInputStream(fileDescriptor?.fileDescriptor).channel
            val fileBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size())
            val audioBuffer = fileBuffer.asShortBuffer()

            // set speed to 1.0 (normal playback rate)
            val params = PlaybackParams().setSpeed(1.0f)

            // play audio track
            audioTrack.play()

            // write audio data to audioTrack
            audioTrack.write(audioBuffer.array(), 0, audioBuffer.limit())

            // set playback speed to 0.5x (half speed)
            params.setSpeed(0.5f)
            audioTrack.playbackParams = params

            // disable equalizer and stop audio
            equalizer?.enabled = false
            audioTrack.stop()
            audioTrack.release()
        }
    }

    // release resources
    fun release() {
        executor.shutdown()
        equalizer?.release()
    }
}
