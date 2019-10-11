package com.example.formylove.picturewall


import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.formylove.R
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.Executors


class PictureWallFragment : Fragment() {
//    private var totalPicturesCount = 0
//    private var urls = HashMap<Int, String>()
//    private lateinit var pictureList: RecyclerView
//    private lateinit var refreshLayout: SwipeRefreshLayout
//
//    private val cacheThreadLocal = Executors.newFixedThreadPool(16)
//    private val client = OkHttpClient()
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.fragment_picture_wall, container, false)
//
//        refreshLayout = view.findViewById(R.id.refreshLayout)
//
//        pictureList = view.findViewById(R.id.pictureList)
//        pictureList.adapter = PictureWallAdapter()
//        pictureList.layoutManager = GridLayoutManager(context!!, 2)
//
//        cacheInit(context!!)
//        loaderInit(context!!)
//
//        refreshLayout.setOnRefreshListener {
//            refresh()
//        }
//
//        refresh()
//
//        return view
//    }
//
//    /*
//     * 查询云端数据库，记录整体的图片数量，uls，同时将本地数据库提取到内存
//     */
//    private fun refresh() {
//        refreshLayout.isRefreshing = true
//
//        //一个随机函数，用来随机打乱从云端拉回的数据
//        fun randomList(result: MutableList<Picture>?) {
//            if (result != null) {
//                val size = result.size
//                for (i in result.indices) {
//                    val j = ((Math.random() * 1000).toInt()) % size
//                    val temp = result[i]
//                    result[i] = result[j]
//                    result[j] = temp
//                }
//            }
//        }
//
//        val query = BmobQuery<Picture>()
//        query.addQueryKeys("imageUrl")
//        query.setLimit(1000)
//        query.findObjects(object : FindListener<Picture>() {
//            override fun done(result: MutableList<Picture>?, e: BmobException?) {
//                if (e == null && result != null) {
//                    //随机化列表
//                    randomList(result)
//                    //清空缓存，不然随机列表不生效
//                    clearMemoryCache(result.size)
//
//                    for (i in result.indices) {
//                        urls[i] = result[i].imageUrl!!
//                    }
//                    totalPicturesCount = result.size
//
//                    pictureList.adapter!!.notifyDataSetChanged()
//                    refreshLayout.isRefreshing = false
//                } else {
//                    Log.e("xkf123456789", e?.message)
//                }
//            }
//        })
//    }
//
//    /*
//     * 根据Id获取一张照片
//     * 先查内存缓存，其次是本地缓存，再其次是云端数据库
//     */
//    private fun setPicture(itemId: Int, set: (Bitmap) -> Unit) {
//        cacheThreadLocal.execute {
//            //最先从内存缓存读取
//            var bitmap: Bitmap? = getBitmapFromMemCache(itemId)
//
//            //内存缓存没有，从本地读取,同时写入内存缓存
//            if (bitmap == null) {
//                val bytes = readFromDisk(urls[itemId]!!)
//                if (bytes != null) {
//                    bitmap = decodeBitmapFromBytes(bytes)
//                    addBitmapToMemoryCache(itemId, bitmap)
//                }
//            }
//
//            //本地也没有，从云端拉取，拉取结束写入内存缓存和本地
//            if (bitmap == null) {
//                val response = request(urls[itemId]!!)
//                val bytes = response!!.body()!!.bytes()
//                bitmap = decodeBitmapFromBytes(bytes)
//                addBitmapToMemoryCache(itemId, bitmap)
//                writeToDisk(urls[itemId]!!, bytes)
//            }
//
//            pictureList.post {
//                set(bitmap)
//            }
//        }
//    }
//
//
//    /*
//     * 网络请求
//     */
//    private fun request(url: String, callback: Callback? = null, inMainThread: Boolean = false): Response? {
//        val request = Request.Builder().url(url).build()
//        return if (!inMainThread) {
//            client.newCall(request).execute()
//        } else {
//            if (callback != null) {
//                client.newCall(request).enqueue(callback)
//            }
//            null
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//        flushDiskLruCache()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        closeDiskLruCache()
//    }
//
//    inner class PictureWallAdapter : RecyclerView.Adapter<ViewHolder>() {
//        override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): ViewHolder {
//            return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.picture_wall_item, viewGroup, false))
//        }
//
//        override fun getItemCount(): Int {
//            return totalPicturesCount
//        }
//
//        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//            if (itemCount > 0 && urls.containsKey(position)) {
//                val picture = viewHolder.picture
//                picture.setImageBitmap(null)
//                picture.tag = position
//                setPicture(position) { bitmap ->
//                    val tag = viewHolder.picture.tag as Int
//                    if (tag == position) {
//                        viewHolder.picture.setImageBitmap(bitmap)
//                    }
//                }
//            }
//        }
//    }
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var picture: ImageView = itemView.findViewById(R.id.picture)
//    }
}
