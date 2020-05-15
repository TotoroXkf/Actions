package com.xkf.libcompiler

import com.alibaba.fastjson.JSONObject
import com.google.auto.service.AutoService
import com.xkf.libannotation.ActivityDestination
import com.xkf.libannotation.FragmentDestination
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic
import kotlin.math.abs

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(value = ["com.xkf.libannotation.FragmentDestination", "com.xkf.libannotation.ActivityDestination"])
class NavProcessor : AbstractProcessor() {
    private lateinit var messager: Messager
    private lateinit var filer: Filer
    
    override fun init(processingEnvironment: ProcessingEnvironment) {
        super.init(processingEnvironment)
        
        messager = processingEnvironment.messager
        filer = processingEnvironment.filer
    }
    
    override fun process(
        set: MutableSet<out TypeElement>,
        roundEnvironment: RoundEnvironment
    ): Boolean {
        val fragmentElements =
            roundEnvironment.getElementsAnnotatedWith(FragmentDestination::class.java)
        val activityElements =
            roundEnvironment.getElementsAnnotatedWith(ActivityDestination::class.java)
        if (fragmentElements.isNotEmpty() || activityElements.isNotEmpty()) {
            val hashMap = hashMapOf<String, JSONObject>()
            handleProcessor(fragmentElements, FragmentDestination::class.java, hashMap)
            handleProcessor(activityElements, ActivityDestination::class.java, hashMap)
            filer.createClassFile()
        }
        return true
    }
    
    private fun <T : Annotation> handleProcessor(
        fragmentElements: MutableSet<out Element>,
        clazz: Class<T>,
        hashMap: HashMap<String, JSONObject>
    ) {
        for (element in fragmentElements) {
            val typeElement = element as TypeElement
            val className = typeElement.qualifiedName.toString()
            val id = abs(className.hashCode())
            val pageUrl: String
            val needLogin: Boolean
            val asStarter: Boolean
            val isFragment: Boolean
            when (val annotation = typeElement.getAnnotation(clazz)) {
                is FragmentDestination -> {
                    pageUrl = annotation.pageUrl
                    needLogin = annotation.needLogin
                    asStarter = annotation.asStarter
                    isFragment = true
                }
                is ActivityDestination -> {
                    pageUrl = annotation.pageUrl
                    needLogin = annotation.needLogin
                    asStarter = annotation.asStarter
                    isFragment = false
                }
                else -> {
                    pageUrl = ""
                    needLogin = false
                    asStarter = false
                    isFragment = false
                }
            }
            if (hashMap.containsKey(pageUrl)) {
                messager.printMessage(Diagnostic.Kind.ERROR, "不同的页面不允许使用相同的URL")
                return
            }
            val jsonObject = JSONObject()
            jsonObject["id"] = id
            jsonObject["pageUrl"] = pageUrl
            jsonObject["needLogin"] = needLogin
            jsonObject["asStarter"] = asStarter
            jsonObject["isFragment"] = isFragment
            hashMap[pageUrl] = jsonObject
        }
    }
}