package com.example.practnumber20

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager : SensorManager
    private var sensor : Sensor? = null

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if(p0 != null)
        when(p0.sensor.type){
            Sensor.TYPE_GYROSCOPE -> {
                val axisX = p0!!.values[0]
                val axisY = p0!!.values[1]
                val axisZ = p0!!.values[2]
                var textViewAxisX : TextView = findViewById(R.id.axisX_textView)
                var textViewAxisY : TextView = findViewById(R.id.axisY_textView)
                var textViewAxisZ : TextView = findViewById(R.id.axisZ_textView)
                textViewAxisX.text = axisY.toString()
                textViewAxisY.text = axisY.toString()
                textViewAxisZ.text = axisZ.toString()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*textViewAxisX = findViewById(R.id.axisX_textView)
        textViewAxisY = findViewById(R.id.axisY_textView)
        textViewAxisZ = findViewById(R.id.axisZ_textView)*/
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
            // Успешно! У нас есть гироскоп
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            val toast : Toast = Toast.makeText(applicationContext, "Гироскоп найден!!!", Toast.LENGTH_LONG)
            toast.show()
        } else {
            val toast : Toast = Toast.makeText(applicationContext, "Гироскоп не найден.....", Toast.LENGTH_LONG)
            toast.show()
            val sensorList : List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
            println(sensorList.joinToString("\n"))
            //val sensorStringList : List<String>
            //val SensorList_ListView : ListView = findViewById(R.id.sensorList_ListView)
            //SensorList_ListView.
        }

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}