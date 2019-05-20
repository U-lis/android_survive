package party.pythonstudy.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var isRunning = false
    private var time = 0
    private var timerTask: Timer ?= null
    private var lap = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            isRunning = !isRunning
            if (isRunning) {
                start()
            } else {
                pause()
            }
        }

        lapButton.setOnClickListener {
            if (isRunning) {
                lap()
            }
        }

        resetFab.setOnClickListener {
            reset()
        }
    }


    private fun start() {
        fab.setImageResource(R.drawable.ic_pause_black_24dp)

        timerTask = timer(period=1) {
            this@MainActivity.time++

            val sec = this@MainActivity.time / 1000
            val mil = this@MainActivity.time % 1000

            runOnUiThread {
                secondText.text = "$sec"
                milisecondText.text = String.format("%03d", mil)
            }
        }
    }

    private fun pause() {
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        timerTask?.cancel()
    }

    private fun lap() {
        val time = this.time
        val txt = TextView(this)
        txt.text = "Lap ${this.lap} : ${time/1000}.${time%1000}"
        lapLayout.addView(txt, 0)
        lap++
    }

    private fun reset() {
        this.time = 0
        this.isRunning = false
        this.lap = 1
        this.timerTask?.cancel()
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        secondText.text = getText(R.string.zero)
        milisecondText.text = getText(R.string.zero)
        lapLayout.removeAllViews()
    }
}
