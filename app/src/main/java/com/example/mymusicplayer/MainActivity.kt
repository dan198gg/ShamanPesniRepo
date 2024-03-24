package com.example.mymusicplayer

import android.content.Intent
import android.media.Image
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet.Layout

class MainActivity : AppCompatActivity() {
    var songLst= mutableListOf<Int>(R.raw.russia, R.raw.ya_russki, R.raw.ogon)
    var songImgLst= mutableListOf<Int>(R.drawable.russia_song_img,R.drawable.ya_russki_song_img,R.drawable.ogon_song_ing)
    var soundNameLst= mutableListOf<Int>(R.string.russia,R.string.ya_russki,R.string.ogon)
    var soundBackgroundLst= mutableListOf<Int>(R.drawable.russia_background,R.drawable.ya_russki_background,R.drawable.ogon_background)
    var soundIndex=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mySp=getSharedPreferences("colorsbg", MODE_PRIVATE)
        if (mySp.contains("color")){
            var id = resources.getIdentifier(mySp.getString("color","white"),"color",packageName)
            findViewById<TextView>(R.id.textSong).setTextColor(resources.getColor(id))
        }
        var currentTrack: MediaPlayer = MediaPlayer.create(this, songLst[soundIndex])
        findViewById<ImageButton>(R.id.btnStpPly).setOnClickListener {
            if (currentTrack.isPlaying) {
                currentTrack.pause()
            } else {
                currentTrack.start()
            }


        }


        findViewById<ImageButton>(R.id.btnNext).setOnClickListener {
            currentTrack.stop()
            if (soundIndex<songLst.size-1){
            soundIndex++
            }else{
                soundIndex=0
            }
            currentTrack = MediaPlayer.create(this, songLst[soundIndex])
            currentTrack.start()
            findViewById<ImageView>(R.id.imageSong).setImageResource(songImgLst[soundIndex])
            findViewById<TextView>(R.id.textSong).setText(soundNameLst[soundIndex])
            findViewById<FrameLayout>(R.id.backgroundSong).setBackgroundResource(soundBackgroundLst[soundIndex])

        }


        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            currentTrack.stop()
            if (soundIndex>0) {
                soundIndex--

            }else{
                soundIndex=songLst.size-1

            }
            currentTrack = MediaPlayer.create(this, songLst[soundIndex])
            currentTrack.start()
            findViewById<ImageView>(R.id.imageSong).setImageResource(songImgLst[soundIndex])
            findViewById<TextView>(R.id.textSong).setText(soundNameLst[soundIndex])
            findViewById<FrameLayout>(R.id.backgroundSong).setBackgroundResource(soundBackgroundLst[soundIndex])
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.optionsAction->{
                startActivity(Intent(this,SettingsActivity::class.java))
            finish()
            }
            R.id.nextSoundAction->{}
            R.id.testAction->{Toast.makeText(this,"404",Toast.LENGTH_LONG).show()}

        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}