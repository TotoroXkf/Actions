package com.xkf.multitype

import java.util.concurrent.atomic.AtomicInteger

class TypePool {
    companion object {
        const val MAX_TYPE = 1000
    }

    private val typeGenerator = AtomicInteger(0)
    private val typeMap = hashMapOf<Int, ItemAdapter<*>>()

    public fun register(itemAdapter: ItemAdapter<*>) {
        val type = typeGenerator.getAndIncrement()
        if (type > MAX_TYPE) {
            throw Exception("超出type的限制")
        }
        typeMap[type] = itemAdapter
        itemAdapter.type = type
    }

    public fun findAdapterByType(type: Int): ItemAdapter<*>? {
        return typeMap[type]
    }

    public fun clear() {
        typeMap.clear()
    }
}