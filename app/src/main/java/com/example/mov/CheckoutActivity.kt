package com.example.mov

import android.app.Notification
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import kotlin.collections.ArrayList
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.app.PendingIntent
import android.app.NotificationChannel
import android.graphics.Color
import android.view.View

import androidx.core.app.NotificationCompat

import com.example.mov.dashboard.CheckoutAdapter
import com.example.mov.databinding.ActivityCheckoutBinding
import com.example.mov.model.Checkout
import com.example.mov.model.Film
import com.example.mov.util.Preferences
import java.text.NumberFormat
import java.util.*

class CheckoutActivity : AppCompatActivity() {

    lateinit var binding: ActivityCheckoutBinding
    private var datalist = ArrayList<Checkout>()

    private var total: Int = 0
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        preferences = Preferences(this)
        datalist = intent.getSerializableExtra("data") as ArrayList<Checkout>
        val data = intent.getParcelableExtra<Film>("datas")

        for (a in datalist.indices){
            total += datalist[a].harga!!.toInt()
        }

        datalist.add(Checkout("Total Harus Dibayar", total.toString()))

        binding.btnTiket.setOnClickListener {
            val intent = Intent(this@CheckoutActivity,
                CheckouSuccessActivity::class.java)
            startActivity(intent)

            showNotif(data!!)
        }

        binding.btnHome.setOnClickListener {
            finish()
        }

        binding.rcCheckout.layoutManager = LinearLayoutManager(this)
        binding.rcCheckout.adapter = CheckoutAdapter(datalist) {
        }

        if(preferences.getValues("saldo")!!.isNotEmpty()) {
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            binding.tvSaldo.setText(formatRupiah.format(preferences.getValues("saldo")!!.toDouble()))
            binding.btnTiket.visibility = View.VISIBLE
            binding.textView3.visibility = View.INVISIBLE

        } else {
            binding.tvSaldo.setText("Rp 0")
            binding.btnTiket.visibility = View.INVISIBLE
            binding.textView3.visibility = View.VISIBLE
            binding.textView3.text = "Saldo pada e-wallet kamu tidak mencukupi\n" +
                    "untuk melakukan transaksi"
        }
    }

    private fun showNotif(datas: Film) {
        val NOTIFICATION_CHANNEL_ID = "channel_bwa_notif"
        val context = this.applicationContext
        var notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channelName = "BWAMOV Notif Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(mChannel)
        }

//        val mIntent = Intent(this, CheckoutSuccessActivity::class.java)
//        val bundle = Bundle()
//        bundle.putString("id", "id_film")
//        mIntent.putExtras(bundle)

        val mIntent = Intent(this, TiketActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("data", datas)
        mIntent.putExtras(bundle)

        val pendingIntent =
            PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        builder.setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.logo_mov)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources,
                    R.drawable.logo_notification
                )
            )
            .setTicker("notif bwa starting")
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.RED, 3000, 3000)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentTitle("Sukses Terbeli")
            .setContentText("Tiket "+datas.judul+" berhasil kamu dapatkan. Enjoy the movie!")

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115, builder.build())
    }
}
