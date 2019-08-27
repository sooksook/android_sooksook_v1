package kr.gdg.sooksook.etc

import org.json.JSONObject

interface AsyncResult {
    fun onResult(jsonObejct: JSONObject)
}