package com.bigosvaap.colaboradores

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bigosvaap.colaboradores.model.JsonData
import com.bigosvaap.colaboradores.persistance.EmpleadoDao
import com.bigosvaap.colaboradores.service.ApiService
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.File
import javax.inject.Inject



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    @Inject
    lateinit var apiService: ApiService
    @Inject
    lateinit var empleadoDao: EmpleadoDao
    @Inject
    lateinit var moshi: Moshi
    @Inject
    lateinit var  fireStore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        prepopulateDatabase()

    }

    private fun prepopulateDatabase(){
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                try {

                    //Getting file Url
                    val response = apiService.getData()

                    if (response.success){

                        val fileUrl = response.data.path

                        //Downloading and Saving file
                        File(filesDir.toString() + File.separator + "Employees.zip").outputStream().use {
                                fileOutputStream -> apiService.downloadFile(fileUrl).byteStream().copyTo(fileOutputStream)
                        }

                        //Unzipping file
                        Utils.unZipFile(filesDir.toString(), "Employees.zip")

                        //Reading json file
                        val jsonFile = File(filesDir.toString()).walkBottomUp().find {it.name.endsWith("json")}

                        if (jsonFile != null){
                            jsonFile.readText()
                            val jsonContent = moshi.adapter(JsonData::class.java).fromJson(jsonFile.readText())

                            //Saving Json Content in Room Database
                            if (jsonContent != null) {
                                empleadoDao.insertAll(jsonContent.data.employess)

                                //Saving data to firestore
                                jsonContent.data.employess.forEach { empleado ->
                                    fireStore.collection("empleados")
                                        .add(empleado.toHashMapOf())
                                        .addOnSuccessListener { documentReference ->
                                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                                        }
                                        .addOnFailureListener { e ->
                                            Log.w(TAG, "Error adding document", e)
                                        }
                                }

                            }

                        }

                    }
                    else
                        Toast.makeText(applicationContext, response.code, Toast.LENGTH_LONG).show()
                }catch (e: HttpException){
                    Toast.makeText(applicationContext, e.message(), Toast.LENGTH_LONG).show()
                }catch (e: Exception){
                    Toast.makeText(applicationContext, "Error :c", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}