package com.huanhailiuxin.mycustomannotations.compiler;

import com.google.auto.service.AutoService;
import com.huanhailiuxin.customannotation.annotations.BindView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)
public class ButterKnifeProcessor extends AbstractProcessor{

    private Elements elementUtils;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<String>();
        types.add(BindView.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(BindView.class);
        Map<Element,List<Element>> elementMap = new LinkedHashMap<Element,List<Element>>();
        for (Element element:elements){
            Element enclosingElement = element.getEnclosingElement();
            List<Element> bindViewElements = elementMap.get(enclosingElement);
            if(bindViewElements == null){
                bindViewElements = new ArrayList<>();
                elementMap.put(enclosingElement,bindViewElements);
            }
            bindViewElements.add(element);
        }
        for(Map.Entry<Element,List<Element>> entrySet:elementMap.entrySet()){
            Element enclosingElement = entrySet.getKey();
            List<Element> bindViewElements = entrySet.getValue();
            String activityClassNameStr = enclosingElement.getSimpleName().toString();
            ClassName activityClassName = ClassName.bestGuess(activityClassNameStr);
            ClassName unBinderClassName = ClassName.get("com.fastaoe.butterknife","Unbinder");
            ClassName keepClass = ClassName.get("android.support.annotation", "Keep");
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(activityClassNameStr + "_ViewBinding")
                    .addAnnotation(keepClass)
                    .addModifiers(Modifier.PUBLIC,Modifier.FINAL)
                    .addSuperinterface(unBinderClassName)
                    .addField(activityClassName,"target",Modifier.PRIVATE);
            ClassName callSuperClassName = ClassName.get("android.support.annotation","CallSuper");
            MethodSpec.Builder unbindMethodBuilder = MethodSpec.methodBuilder("unbind")
                    .addAnnotation(Override.class)
                    .addAnnotation(callSuperClassName)
                    .addModifiers(Modifier.PUBLIC,Modifier.FINAL);
            MethodSpec.Builder constructorMethodBuilder = MethodSpec.constructorBuilder()
                    .addParameter(activityClassName,"target")
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("this.target = target");
            for(Element bindViewElement:bindViewElements){
                String fieldName = bindViewElement.getSimpleName().toString();
                ClassName utilsClassName = ClassName.get("com.fastaoe.butterknife","Utils");
                int resId = bindViewElement.getAnnotation(BindView.class).value();
                constructorMethodBuilder.addStatement("target.$L = $T.findViewById(target,$L)",fieldName,utilsClassName,resId);
                unbindMethodBuilder.addStatement("target.$L = null",fieldName);
            }
            classBuilder.addMethod(unbindMethodBuilder.build()).addMethod(constructorMethodBuilder.build());
            String packageName = elementUtils.getPackageOf(enclosingElement).getQualifiedName().toString();
            try {
                JavaFile.builder(packageName,classBuilder.build())
                        .build()
                        .writeTo(filer);
            }catch (Exception e){e.printStackTrace();}
        }
        //
        return false;
    }
}