package com.example.interaccionconimagenes

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Descripcion: Implementa la interaccion con imagenes y un reproductor de musica basico.
 * Autor: Alex Rhoddo Pacheco
 * Fecha de creacion: 2026-04-12
 * Fecha de ultima modificacion: 2026-04-12
 */
class MainActivity : AppCompatActivity() {
    private data class CancionUi(
        val audioResId: Int,
        val titleResId: Int,
        val imageResId: Int
    )
    private var currentTrackIndex = 0

    private lateinit var canciones: List<CancionUi>
    private lateinit var textSongName: TextView
    private lateinit var imageInteractive: ImageView
    private var isPlaying = false
    private lateinit var textStatus: TextView
    private lateinit var reproductorDeMusicaController: ReproductorDeMusicaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        canciones = listOf(
            CancionUi(R.raw.justin_bieber_confident, R.string.song_confident, R.drawable.confident),
            CancionUi(R.raw.the_weeknd_ariana_grande_die_for_you, R.string.song_die_for_you, R.drawable.die_for_you)
        )

        reproductorDeMusicaController = ReproductorDeMusicaController(
            this,
            canciones.map { it.audioResId }
        ) { trackIndex ->
            currentTrackIndex = trackIndex
            updateSongInfo(trackIndex)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imageInteractive = findViewById(R.id.imageInteractive)

        textSongName = findViewById(R.id.textSongName)
        updateSongInfo(currentTrackIndex)
        setupImageInteraction()
        setupMusicControls()


    }

    override fun onDestroy() {
        super.onDestroy()
        reproductorDeMusicaController.release()
    }

    private fun setupImageInteraction() {
        val imageInteractive = findViewById<ImageView>(R.id.imageInteractive)
        val interaccionConImagenManager = InteraccionConImagenManager()
        interaccionConImagenManager.attachClickAction(imageInteractive) {
            Toast.makeText(this, getString(R.string.image_toast_message), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupMusicControls() {
        textStatus = findViewById(R.id.textStatus)
        val btnPlayPause = findViewById<Button>(R.id.btnPlayPause)
        val btnSkip = findViewById<Button>(R.id.btnSkip)
        val btnStop = findViewById<Button>(R.id.btnStop)

        btnPlayPause.setOnClickListener {
            if (isPlaying) {
                reproductorDeMusicaController.pause()
                btnPlayPause.text = "▶"
                textStatus.setText(R.string.music_status_paused)
                isPlaying = false
            } else {
                val started = reproductorDeMusicaController.play()
                if (started) {
                    btnPlayPause.text = "⏸"
                    textStatus.setText(R.string.music_status_playing)
                    isPlaying = true
                } else {
                    textStatus.setText(R.string.music_status_stopped)
                    Toast.makeText(this, getString(R.string.audio_error), Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnSkip.setOnClickListener {
            val skipped = reproductorDeMusicaController.skip()
            if (skipped) {
                textStatus.setText(R.string.music_status_playing)
                btnPlayPause.text = "⏸"
                isPlaying = true
            }
        }

        btnStop.setOnClickListener {
            reproductorDeMusicaController.stop()
            textStatus.setText(R.string.music_status_stopped)
            btnPlayPause.text = "▶"
            isPlaying = false
        }

    }
    private fun updateSongInfo(trackIndex: Int) {
        val cancion = canciones[trackIndex]
        textSongName.setText(cancion.titleResId)
        imageInteractive.setImageResource(cancion.imageResId)
    }

}