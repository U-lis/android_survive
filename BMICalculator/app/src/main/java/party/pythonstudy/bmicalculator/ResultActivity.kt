package party.pythonstudy.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.toast

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val height = intent.getDoubleExtra("height", 0.0)
        val weight = intent.getDoubleExtra("weight", 0.0)

        val bmi = weight / Math.pow(height/100, 2.0)

        when {
            bmi >= 35 -> resultTextView.text = getString(R.string.hyper_fat)
            bmi >= 30 -> resultTextView.text = getString(R.string.ultra_fat)
            bmi >= 25 -> resultTextView.text = getString(R.string.super_fat)
            bmi >= 23 -> resultTextView.text = getString(R.string.fat)
            bmi >= 18.5 -> resultTextView.text = getString(R.string.normal)
            else -> resultTextView.text = getString(R.string.thin)
        }

        when {
            bmi >= 23 -> imageView.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            bmi >= 18.5 -> imageView.setImageResource(R.drawable.ic_tag_faces_black_24dp)
            else -> imageView.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
        }

//        Toast.makeText(this, "$bmi", Toast.LENGTH_SHORT).show()
        toast("$bmi")   // Anko
    }
}
