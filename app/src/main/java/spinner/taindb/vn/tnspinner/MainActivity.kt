package spinner.taindb.vn.tnspinner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var spinner = findViewById<TNSpinner>(R.id.spinner)
        spinner.setListItems(listOf(
                "0 - Google Pixel"
                , "1 - SamSung"
                , "2 - Sony"
                , "3 - LG"
                , "4 - Nokia"
                , "5 - Oppo"
                , "6 - Asus"))
    }
}
