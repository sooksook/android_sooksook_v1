package kr.gdg.sooksook.common

import android.util.Log
import kr.gdg.sooksook.GlobalApplication

object Dlog {
    private val TAG = GlobalApplication::class.java.simpleName

    private fun buildLogMsg(message: String): String {
        val ste = Thread.currentThread().stackTrace[4]
        val sb = StringBuilder()
        sb.append("[")
        sb.append(ste.fileName.replace(".java", ""))
        sb.append("::")
        sb.append(ste.methodName)
        sb.append("]")
        sb.append(message)

        return sb.toString()
    }

    // Log Level Error
    fun e(message: String) {
        if (GlobalApplication.getInstance().DEBUG) Log.e(TAG, buildLogMsg(message))
    }

    // Log Level Warning
    fun w(message: String) {
        if (GlobalApplication.getInstance().DEBUG) Log.w(TAG, buildLogMsg(message))
    }

    // Log Level Information
    fun i(message: String) {
        if (GlobalApplication.getInstance().DEBUG) Log.i(TAG, buildLogMsg(message))
    }

    // Log Level Debug
    fun d(message: String) {
        if (GlobalApplication.getInstance().DEBUG) Log.d(TAG, buildLogMsg(message))
    }

    // Log Level Verbose
    fun v(message: String) {
        if (GlobalApplication.getInstance().DEBUG) Log.v(TAG, buildLogMsg(message))
    }
}