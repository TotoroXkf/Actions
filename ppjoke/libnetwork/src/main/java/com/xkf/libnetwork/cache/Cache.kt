package com.xkf.libnetwork.cache

import java.io.Serializable


class Cache : Serializable {
    var key: String = ""
    var data: ByteArray = byteArrayOf()
}