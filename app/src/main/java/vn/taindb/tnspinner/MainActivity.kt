package  vn.taindb.tnspinner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity(), SpinnerAdapter.OnClickItemListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var spinner = findViewById<SpinnerTextView>(R.id.spinner)
        spinner.setOnItemSeletedListener(this@MainActivity)
        spinner.setListItems(listOf(
                "0 - Google Pixel"
                , "1 - SamSung"
                , "2 - Sony"
                , "3 - LG"
                , "4 - Nokia"
                , "5 - Oppo"
                , "6 - Asus"))
    }

    override fun onItemClick(text: String, position: Int) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }
}
