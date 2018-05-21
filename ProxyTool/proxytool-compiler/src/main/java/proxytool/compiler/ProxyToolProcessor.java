package proxytool.compiler;

import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import proxytool.OnClick;
import proxytool.ViewById;

/**
 * 自定义注解处理器
 */
@AutoService(Processor.class)
public class ProxyToolProcessor extends AbstractProcessor{
    //文件相关的辅助类
    private Filer mFiler;
    //元素相关的辅助类
    private Elements mElementUtils;
    //日志相关的辅助类
    private Messager mMessager;

    /**
     * 处理器的初始化方法，可以获取相关的工具类
     * @param processingEnv
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
    }
    /**
     * 指定那些自定义注解可以被注解处理器注册
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<String>();
        types.add(ViewById.class.getName());
        types.add(OnClick.class.getName());
        return types;
    }
    /**
     * 用来指定你使用的Java版本
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {

        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //处理被ViewById注解的元素
        for(Element element:roundEnv.getElementsAnnotatedWith(ViewById.class)){
            if(!isValid(ViewById.class,"fields",element)){
                return true;
            }
        }
        //处理被OnClick注解的元素
        for(Element element:roundEnv.getElementsAnnotatedWith(OnClick.class)){
            if(isValid(OnClick.class,"methods",element)){
                return true;
            }
        }
        return true;
    }

    private boolean isValid(Class<? extends Annotation> annotationClass,String targetThing,Element element){
        boolean isValid = true;
        //获取元素所在的父元素,肯定是类,接口,枚举
        TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
        //父元素的全限定名
        String qualifiedName = enclosingElement.getQualifiedName().toString();
        //被ViewById和OnClick注解的元素,不能是private或static修饰
        Set<Modifier> modifiers = element.getModifiers();
        if(modifiers.contains(Modifier.PRIVATE) || modifiers.contains(Modifier.STATIC)){
            error(element,"@%s %s must not be private or static. (%s.%s)",annotationClass.getSimpleName(),targetThing,qualifiedName,element.getSimpleName());
            isValid = false;
        }
        //父元素必须是类,而不能是接口或者枚举
        if(enclosingElement.getKind() != ElementKind.CLASS){
            error(enclosingElement,"@%s %s may only be contained in classes. (%s.%s)",annotationClass.getSimpleName(),targetThing,qualifiedName,element.getSimpleName());
            isValid = false;
        }
        //ViewById和OnClick不能在android框架层注解
        if(qualifiedName.startsWith("android.")){
            error(element,"@%s-annotated class incorrectly in Android framework package. (%s)",annotationClass.getSimpleName(),qualifiedName);
            isValid = false;
        }
        //ViewById和OnClick不能在Java框架层注解
        if(qualifiedName.startsWith("java.")){
            error(element,"@%s-annotated class incorrectly in Java framework package. (%s)",annotationClass.getSimpleName(),qualifiedName);
            isValid = false;
        }
        return isValid;
    }

    private void error(Element element,String msg,Object ... args){
        mMessager.printMessage(Diagnostic.Kind.ERROR,String.format(msg,args),element);
    }
}
