package com.adhiambo.movieguide.common


interface ErrorLogger {

    fun logException(
        throwable: Throwable,
        metadata: Map<String, Any> = hashMapOf(),
        message: String? = null,
        severity: Severity = Severity.ERROR
    )

    fun logMessage(
        message: String,
        metadata: Map<String, Any> = hashMapOf(),
        severity: Severity = Severity.ERROR
    )

    fun addExtraInformation(
        message: String,
        extraInformationType: ExtraInformationType,
        metadata: Map<String, Any> = emptyMap()
    )

    enum class Severity {
        ERROR,
        WARNING,
        INFO
    }

    enum class ExtraInformationType {
        NAVIGATION,
        REQUEST,
        STATE
    }
}
