package kr.gdg.sooksook.data

import android.annotation.SuppressLint
import kr.gdg.sooksook.R
import kr.gdg.sooksook.common.Const
import kr.gdg.sooksook.etc.AsyncResult
import kr.gdg.sooksook.etc.DownloadWebpageTask
import org.json.JSONException
import org.json.JSONObject

object RemoteSource {

    interface OCallback {
        fun getDataFromSpeadSheet(isGetData: Boolean)
    }

    private var callback: OCallback? = null

    fun setCallback(callback: OCallback) {
        this.callback = callback
    }

    // region spread sheet read 하기 위한 method
    fun readOnlySpreadSheet() {
        DownloadWebpageTask(object : AsyncResult {
            override fun onResult(jsonObejct: JSONObject) {
                processJson(jsonObejct)
            }
        }).execute("https://spreadsheets.google.com/tq?key=1VT8gM9gYEBgrTznV1rw7rElMUPYUPIlEgKnuhdoVzPI")
    }

    @SuppressLint("ResourceType")
    private fun processJson(jsonObject: JSONObject) {
        try {
            val rows = jsonObject.getJSONArray("rows")
            val arrayListData: ArrayList<SpreadDataResponse> = ArrayList()

            for (r in 0 until rows.length()) {
                val row = rows.getJSONObject(r)
                val columns = row.getJSONArray("c")

                val id = columns.getJSONObject(0).getInt("v")
                val name = if(columns.getJSONObject(1).getString("v") == "null") null else columns.getJSONObject(1).getString("v")
                val category = if(columns.getJSONObject(2).getString("v") == "null") null else columns.getJSONObject(2).getString("v")
                val water = if(columns.getJSONObject(3).getString("v") == "null") null else columns.getJSONObject(3).getString("v")
                val sunlight = if(columns.getJSONObject(4).getString("v") == "null") null else columns.getJSONObject(4).getString("v")
                val wind = if(columns.getJSONObject(5).getString("v") == "null") null else columns.getJSONObject(5).getString("v")
                val soil = if(columns.getJSONObject(6).getString("v") == "null") null else columns.getJSONObject(6).getString("v")
                val temperature = if(columns.getJSONObject(7).getString("v") == "null") null else columns.getJSONObject(7).getString("v")
                val characteristic = if(columns.getJSONObject(8).getString("v") == "null") null else columns.getJSONObject(8).getString("v")
                val tip1 = if(columns.getJSONObject(9).getString("v") == "null") null else columns.getJSONObject(9).getString("v")
                val tip2 = if(columns.getJSONObject(10).getString("v") == "null") null else columns.getJSONObject(10).getString("v")
                val tip3 = if(columns.getJSONObject(11).getString("v") == "null") null else columns.getJSONObject(11).getString("v")
                val tip4 = if(columns.getJSONObject(12).getString("v") == "null") null else columns.getJSONObject(12).getString("v")
                val tip5 = if(columns.getJSONObject(13).getString("v") == "null") null else columns.getJSONObject(13).getString("v")

                val data = when (r) {
                    0 -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5, R.drawable.areca_palm)
                    1 -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5, R.drawable.ficus_benghalensis)
                    2 -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5, R.drawable.weeping_fig)
                    3 -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5, R.drawable.ficus_lyrata)
                    4 -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5, R.drawable.snow_sapphire)
                    5 -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5, R.drawable.scindapsus)
                    6 -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5, R.drawable.daphne_koreana)
                    7 -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5, R.drawable.marimo)
                    8 -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5, R.drawable.ionantha)
                    9 -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5, R.drawable.tillandsia_usneoides)
                    10 -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5, R.drawable.dischidia)
                    11 -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5, R.drawable.basil_tree)
                    12 -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5, R.drawable.radermachera)
                    else -> SpreadDataResponse(id, name, category, water, sunlight, wind, soil, temperature, characteristic, tip1, tip2, tip3, tip4, tip5)
                }


                arrayListData.add(data)
            }

            Const.arrayListData = arrayListData
            callback?.getDataFromSpeadSheet(true)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}