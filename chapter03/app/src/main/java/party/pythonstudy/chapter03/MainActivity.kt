package party.pythonstudy.chapter03

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickButton.setOnClickListener {
            if (textView.text == getString(R.string.clicked)) {
                textView.text = getString(R.string.again)
            } else {
                textView.text = getString(R.string.clicked)
            }
        }
    }
}
