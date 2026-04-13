package com.example.interaccionconimagenes

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes

/**
 * Descripcion: Controla la reproduccion secuencial de dos audios locales en res/raw.
 * Autor: Alex Rhoddo Pacheco
 * Fecha de creacion: 2026-04-12
 * Fecha de ultima modificacion: 2026-04-12
 */
class ReproductorDeMusicaController(
    private val context: Context,
    @param:RawRes private val playlist: List<Int>,
    private val onTrackChanged: ((Int) -> Unit)? = null
) {
    private var mediaPlayer: MediaPlayer? = null
    private var currentTrackIndex = 0

    init {
        require(playlist.isNotEmpty()) { "La lista de canciones no puede estar vacia." }
    }

    fun play(): Boolean {
        return runCatching {
            val player = mediaPlayer ?: createPlayer(currentTrackIndex).also { mediaPlayer = it }
            if (!player.isPlaying) {
                player.start()
                onTrackChanged?.invoke(currentTrackIndex)
            }
            true
        }.getOrDefault(false)
    }

    fun pause() {
        val player = mediaPlayer ?: return
        if (player.isPlaying) {
            player.pause()
        }
    }

    fun stop() {
        val player = mediaPlayer ?: return
        if (player.isPlaying) {
            player.pause()
        }
        player.seekTo(0)
    }

    fun skip(): Boolean {
        return runCatching {
            val nextTrackIndex = (currentTrackIndex + 1) % playlist.size
            startTrack(nextTrackIndex)
            true
        }.getOrDefault(false)
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
        currentTrackIndex = 0
    }

    private fun createPlayer(trackIndex: Int): MediaPlayer {
        return MediaPlayer.create(context, playlist[trackIndex]).apply {
            setOnCompletionListener {
                playNextTrack()
            }
        }
    }

    private fun startTrack(trackIndex: Int) {
        mediaPlayer?.release()
        currentTrackIndex = trackIndex
        mediaPlayer = createPlayer(trackIndex)
        onTrackChanged?.invoke(currentTrackIndex)
        mediaPlayer?.start()
    }

    private fun playNextTrack() {
        val nextTrackIndex = (currentTrackIndex + 1) % playlist.size
        startTrack(nextTrackIndex)
    }
}


