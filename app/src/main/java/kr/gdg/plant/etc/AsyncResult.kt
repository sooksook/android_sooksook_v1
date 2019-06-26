package kr.gdg.plant.etc

import org.json.JSONObject

interface AsyncResult {
    fun onResult(jsonObejct: JSONObject)
}