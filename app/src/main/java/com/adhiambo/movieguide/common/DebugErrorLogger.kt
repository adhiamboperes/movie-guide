package com.adhiambo.movieguide.common

import android.util.Log
import com.adhiambo.movieguide.OpenForTesting

@OpenForTesting
class DebugErrorLogger : ErrorLogger {

    companion object {

        const val TAG = "Movie Guide"
        const val EMPTY_STRING = ""
    }
    override fun logException(
        throwable: Throwable,
        metadata: Map<String, Any>,
        message: String?,
        severity: ErrorLogger.Severity
    ) {
        when (severity) {
            ErrorLogger.Severity.ERROR -> {
                Log.e(TAG, throwable.localizedMessage, throwable)
                Log.e(TAG, combineMessage(message, metadata))
            }
            ErrorLogger.Severity.WARNING -> {
                Log.w(TAG, throwable.localizedMessage, throwable)
                Log.w(TAG, combineMessage(message, metadata))
            }
            ErrorLogger.Severity.INFO -> {
                Log.i(TAG, throwable.localizedMessage, throwable)
                Log.i(TAG, combineMessage(message, metadata))
            }
        }
    }

    override fun logMessage(
        message: String,
        metadata: Map<String, Any>,
        severity: ErrorLogger.Severity
    ) {
        when (severity) {
            ErrorLogger.Severity.ERROR -> {
                Log.e(TAG, combineMessage(message, metadata))
            }
            ErrorLogger.Severity.WARNING -> {
                Log.w(TAG, combineMessage(message, metadata))
            }
            ErrorLogger.Severity.INFO -> {
                Log.i(TAG, combineMessage(message, metadata))
            }
        }
    }

    override fun addExtraInformation(
        message: String,
        extraInformationType: ErrorLogger.ExtraInformationType,
        metadata: Map<String, Any>
    ) {
        Log.d(
            TAG,
            """
                Logged extra information of type $extraInformationType
                ${combineMessage(message, metadata)}
            """.trimIndent()
        )
    }

    private fun combineMessage(message: String?, params: Map<String, Any?>): String {
        return if (params.isEmpty()) {
            message
        } else {
            "$message - $params"
        } ?: EMPTY_STRING
    }
}
