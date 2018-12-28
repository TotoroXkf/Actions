package com.example.formylove.picturewall

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

var imageWidth = 0
var imageHeight = 0

/*
 * 初始化，计算图片的实际大小
 * 屏幕一行显示两张图片，每张图片左右各4dp，所以宽度=屏幕的宽度减去16dp的像素值/2
 * 图片的高度为固定值150dp
 */
fun loaderInit(context: Context) {
    val display = context.resources.displayMetrics
    imageHeight = dp2px(context, 150f)
    imageWidth = (display.widthPixels - dp2px(context, 16f)) / 2
}

/*
 * 计算采样率
 */
private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    // 源图片的高度和宽度
    val height = options.outHeight
    val width = options.outWidth
    var inSampleSize = 1
    if (height > reqHeight || width > reqWidth) {
        // 计算出实际宽高和目标宽高的比率
        val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
        val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
        // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
        // 一定都会大于等于目标的宽和高。
        inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
    }
    return inSampleSize
}

/*
 * 压缩图片到合适的大小
 */
fun decodeBitmapFromBytes(bytes: ByteArray): Bitmap {
    val options = BitmapFactory.Options()
    // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
    options.inJustDecodeBounds = true
    BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)
    // 使用获取到的inSampleSize值再次解析图片
    options.inSampleSize = calculateInSampleSize(options, imageWidth, imageHeight)
    //options.inSampleSize = 4
    options.inJustDecodeBounds = false
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)
}

/*
 * 将px转换为与之相等的dp
 */
fun px2dp(context: Context, pxValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}


/*
 * 将dp转换为与之相等的px
 */
fun dp2px(context: Context, dipValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dipValue * scale + 0.5f).toInt()
}