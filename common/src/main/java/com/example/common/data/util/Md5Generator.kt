package com.example.common.data.util

import java.security.MessageDigest

fun generateMd5(vararg values: String): String {

    val combinedValues = values.joinToString("")
    val mdInstance = MessageDigest.getInstance("MD5")

    val digest = mdInstance.digest(combinedValues.toByteArray())

    return digest.joinToString("") { "%02x".format(it) }
}