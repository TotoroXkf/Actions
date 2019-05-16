const val SOCKET_PORT = 12306

const val PARTNER_SELF = "self"
const val PARTNER_ALL = "all"

const val OK = "ok"

/**
 * 查看当前连接的设备
 */
const val ACTION_DEVICE_COUNT = "count"

/**
 * 拍摄
 */
const val ACTION_CAPTURE = "capture"

/**
 * 断开连接
 */
const val ACTION_FINISH = "finish"

/**
 * 回文测试
 */
const val ACTION_ECHO = "echo"

/**
 * 测量命令从发出到接收的延迟
 */
const val ACTION_DELAY_TEST = "rtt"

/**
 * 服务端删除指定的注册的手机
 */
const val ACTION_REMOVE = "remove"

/**
 * 传输拍摄的照片到服务端
 */
const val ACTION_GET = "get"

/**
 * 参数设置，控制焦距
 */
const val ACTION_ZOOM = "zoom"

/**
 * 参数设置，控制hdr
 */
const val ACTION_HDR = "hdr"

/**
 * 参数设置，控制flash
 */
const val ACTION_FLASH = "flash"

/**
 * 参数设置，控制白平衡
 */
const val ACTION_WHITE_BALANCE = "white_balance"

/**
 * 参数设置，控制对焦区域
 */
const val ACTION_FOCUS_AREA = "focus"

/**
 * 参数设置，曝光补偿
 */
const val ACTION_EXPOSURE_CORRECTION= "exposure"