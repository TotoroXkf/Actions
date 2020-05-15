package com.xkf.ppjoke.base

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import com.xkf.ppjoke.mode.Destination
import java.io.BufferedReader
import java.io.InputStreamReader

object AppConfig {
    private val destinationMap = hashMapOf<String, Destination>()
    
    fun getDestConfig(): HashMap<String, Destination> {
        if (destinationMap.isEmpty()) {
            val content = parseFile("destination.json")
            destinationMap.putAll(
                JSON.parseObject(
                    content,
                    object : TypeReference<HashMap<String, Destination>>() {}.type
                )
            )
        }
        return destinationMap
    }
    
    private fun parseFile(fileName: String): String {
        val assets = AppGlobal.getApplication().assets
        val stream = assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(stream))
        val stringBuilder = StringBuilder()
        var line = reader.readLine()
        while (line != null) {
            stringBuilder.append(line)
            line = reader.readLine()
        }
        stream.close()
        reader.close()
        return stringBuilder.toString()
    }
}