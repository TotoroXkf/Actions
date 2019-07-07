package com.example.compiler;

import com.example.annotation.BindView;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {
    private Elements elementUtils;
    private Filer filer;
    private Map<String, List<VariableInfo>> classMap = new HashMap<>();
    private Map<String, TypeElement> classTypeElement = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtils = processingEnvironment.getElementUtils();
        filer = processingEnvironment.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes(){
        Set<String> annotationTypes = new LinkedHashSet<>();
        annotationTypes.add(BindView.class.getCanonicalName());
        return annotationTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion(){
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        collectInfo(roundEnvironment);
        System.out.println("xkf");
        writeToFile();
        return true;
    }

    private void collectInfo(RoundEnvironment roundEnvironment) {
        classMap.clear();
        classTypeElement.clear();
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        for (Element element : elements) {
            int viewId = element.getAnnotation(BindView.class).value();
            VariableElement variableElement = (VariableElement) element;
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
            String className = typeElement.getQualifiedName().toString();
            List<VariableInfo> variableInfoList = classMap.get(className);
            if (variableInfoList == null) {
                variableInfoList = new ArrayList<>();
                classMap.put(className, variableInfoList);
                classTypeElement.put(className, typeElement);
            }
            VariableInfo variableInfo = new VariableInfo();
            variableInfo.setViewId(viewId);
            variableInfo.setVariableElement(variableElement);
            variableInfoList.add(variableInfo);
        }
    }


    private void writeToFile() {
        for (String className : classMap.keySet()) {
            TypeElement typeElement = classTypeElement.get(className);
            MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(ParameterSpec.builder(TypeName.get(typeElement.asType()), "activity").build());
            List<VariableInfo> variableInfoList = classMap.get(className);
            for (VariableInfo variableInfo : variableInfoList) {
                VariableElement variableElement = variableInfo.getVariableElement();
                String variableName = variableElement.getSimpleName().toString();
                String variableFullName = variableElement.asType().toString();
                constructor.addStatement("activity.$L=($L)activity.findViewById($L)", variableName,
                        variableFullName, variableInfo.getViewId());
            }
            TypeSpec typeSpec = TypeSpec.classBuilder(typeElement.getSimpleName() + "$$ViewInjector")
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(constructor.build())
                    .build();
            String packageFullName = elementUtils.getPackageOf(typeElement).getQualifiedName().toString();
            JavaFile javaFile = JavaFile.builder(packageFullName,typeSpec).build();
            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
