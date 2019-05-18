package party.pythonstudy.bmicalculator

//import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Remove saved data
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.remove("KEY_HEIGHT").remove("KEY_WEIGHT").apply()

        calculateButton.setOnClickListener {
            val height = heightEditText.text.toString()
            val weight = weightEditText.text.toString()

            if (weight == "" || height == "") {
                toast(getText(R.string.no_input_warning))
            } else {
//            val intent = Intent(this, ResultActivity::class.java)
//            intent.putExtra("weight", weightEditText.text.toString())
//            intent.putExtra("height", heightEditText.text.toString())
//            startActivity(intent)

                saveData(height, weight)

                startActivity<ResultActivity>(
                    "weight" to weight.toDouble(),
                    "height" to height.toDouble()
                ) // Anko
            }
        }
    }

    override fun onResume() {
        super.onResume()

        loadData()
    }

    private fun saveData(height: String, weight: String) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putString("KEY_HEIGHT", height).putString("KEY_WEIGHT", weight).apply()
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getString("KEY_HEIGHT", "")
        val weight = pref.getString("KEY_WEIGHT", "")

        heightEditText.setText(height)
        weightEditText.setText(weight)
    }
}
