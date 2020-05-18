package com.xkf.libnetwork

class ApiResponse<T>(
    success: Boolean,
    status: Int,
    message: String,
    body: T
)