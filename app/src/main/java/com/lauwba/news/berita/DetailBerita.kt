package com.lauwba.news.berita

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.lauwba.news.R
import com.lauwba.news.RequestHandler
import com.lauwba.news.config.Config
import kotlinx.android.synthetic.main.activity_detail_berita.*
import org.json.JSONObject

class DetailBerita : AppCompatActivity() {

    private var id:String?=null
    private var pd: ProgressDialog?=null
    private var from :String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)

        id=intent.getStringExtra(Config.id)
        from=intent.getStringExtra("from")
        Toast.makeText(this@DetailBerita, from, Toast.LENGTH_SHORT).show()
        if (from.equals("berita",true)){
            getdetailberita().execute()
        }else{
            getdetailberita().execute()
        }
    }

    inner class getdetailberita : AsyncTask<String, Void, String>(){

        override fun doInBackground(vararg params: String?): String {
            val request= RequestHandler()
            return request.sendGetRequest(Config.url_detail_berita+id)

        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailBerita,"","Wait...",false,true)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            if (objek.getInt("status")==1){
                Toast.makeText(this@DetailBerita, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            }
            else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    judul.text = data.getString("judul")
                    isi.text = data.getString("isi")
                    tanggal.text = data.getString("tanggal")
                    Glide.with(this@DetailBerita)
                        .load(Config.url_gambar+ data.getString("gambar")).into(gambarberita)
                }
            }
        }

    }


}
