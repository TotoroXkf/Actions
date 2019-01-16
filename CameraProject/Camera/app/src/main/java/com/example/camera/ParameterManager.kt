package com.example.camera

import android.hardware.camera2.CaptureRequest

//https://blog.csdn.net/qq_29333911/article/details/79400617

/*
black_level_lock
黑电平补偿是否锁定当前值，或者可以自由更改。
color_correction_aberration_mode
色差校正算法的运算方式。
color_correction_gains
适用于拜尔原料色通道的白平衡。
color_correction_mode
模式控制选择图像数据从传感器的原始颜色为线性sRGB色彩转换。
color_correction_transform
颜色的变换矩阵，利用从传感器RGB颜色空间到输出线性sRGB色彩空间变换。
control_ae_antibanding_mode
所需的摄像装置的自动曝光算法的antibanding补偿设置。
control_ae_exposure_compensation
自动曝光（AE）目标图像亮度的调整。
control_ae_lock
是否自动曝光（AE）当前锁定到其最新计算值。
control_ae_mode
照相机设备的自动曝光程序所需的模式。
control_ae_precapture_trigger
不论相机设备将触发Precapture计量序列时，处理这个请求。
control_ae_regions
用于自动曝光调整的计量区域列表。
control_ae_target_fps_range
自动曝光程序可以调整捕获帧速以保持良好曝光范围。
control_af_mode
当前是否启用自动对焦（AF），以及它设置为什么模式。
control_af_regions
用于自动对焦的计量区域列表。
control_af_trigger
照相机设备是否会触发这个请求的自动对焦。
control_awb_lock
无论是自动白平衡（AWB）是目前最新的计算值锁定。
control_awb_mode
无论是自动白平衡（AWB）是目前设置的颜色变换领域，其照明的目标是什么。
control_awb_regions
用于自动白平衡光源估计的测光区域列表。
control_capture_intent
信息到相机设备3A（自动曝光，自动对焦，自动白平衡）例程，目的是为了捕捉，帮助相机设备决定最佳3A策略。
control_effect_mode
特殊色彩效果。
control_enable_zsl
允许摄像装置使零快门延迟模式的要求与android.control.captureintent = = still_capture。
control_mode
3A模式（自动曝光、自动白平衡、自动对焦）控制例程。
control_post_raw_sensitivity_boost
捕获原始传感器数据后输出图像的附加灵敏度提升量。
control_scene_mode
当前活动场景模式的控件。
control_video_stabilization_mode
视频稳定是否有效。
造物主edge_mode
边缘增强操作模式。
flash_mode
照相机设备闪光灯控制所需的模式。
hot_pixel_mode
热像素校正的操作模式。
jpeg_gps_location
生成映像GPS元数据时要使用的位置对象。
jpeg_orientation
jpeg图像的定位。
jpeg_quality
最后jpeg图像的压缩质量。
jpeg_thumbnail_quality
jpeg缩略图压缩质量。
jpeg_thumbnail_size
嵌入式jpeg缩略图解析。
lens_aperture
理想透镜孔径，作为透镜焦距与有效孔径的比值。
lens_filter_density
透镜中性密度滤光器所需的设置。
lens_focal_length
所需的透镜焦距；用于光学变焦。
lens_focus_distance
所需的距离对焦点平面，从镜头前的表面测量。
lens_optical_stabilization_mode
设置照相机设备在拍摄图像时是否使用光学图像稳定（稳定）。
noise_reduction_mode
噪声抑制算法的运算方式。
reprocess_effective_exposure_factor
在发送处理前，应用程序处理对原始输出帧施加曝光时间增加因子的数量。
scaler_crop_region
要捕获的传感器读出的所需区域。
sensor_exposure_time
每个像素的曝光时间。
sensor_frame_duration
从帧曝光开始到下一帧曝光开始的持续时间。
sensor_sensitivity
在处理之前应用于传感器数据的增益量。
sensor_test_pattern_data
一个像素[ R，g_even，g_odd，b ]提供测试模式android.sensor.testpatternmode是当solid_color。
sensor_test_pattern_mode
当启用时，传感器发送一个测试模式，而不是从相机进行真正的曝光。
shading_mode
应用于图像数据的镜头阴影校正质量。
statistics_face_detect_mode
 */

private val currentParameterMap = HashMap<CaptureRequest.Key<Int>, Int>()

fun setDefaultParameter() {
	currentParameterMap.clear()
	currentParameterMap[CaptureRequest.CONTROL_AF_MODE] =
			CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE
	currentParameterMap[CaptureRequest.CONTROL_AE_MODE] =
			CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH
}

fun setLocalParameter() {

}

fun getParameterMap(): HashMap<CaptureRequest.Key<Int>, Int> {
	return currentParameterMap
}