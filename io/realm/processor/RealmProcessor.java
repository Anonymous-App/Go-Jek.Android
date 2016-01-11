package io.realm.processor;

import io.realm.annotations.RealmClass;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes({"io.realm.annotations.RealmClass", "io.realm.annotations.Ignore", "io.realm.annotations.Index", "io.realm.annotations.PrimaryKey"})
public class RealmProcessor
  extends AbstractProcessor
{
  Set<ClassMetaData> classesToValidate = new HashSet();
  boolean done = false;
  
  public SourceVersion getSupportedSourceVersion()
  {
    return SourceVersion.latestSupported();
  }
  
  public boolean process(Set<? extends TypeElement> paramSet, RoundEnvironment paramRoundEnvironment)
  {
    RealmVersionChecker.getInstance(this.processingEnv).executeRealmVersionUpdate();
    Utils.initialize(this.processingEnv);
    paramSet = paramRoundEnvironment.getElementsAnnotatedWith(RealmClass.class).iterator();
    for (;;)
    {
      if (paramSet.hasNext())
      {
        paramRoundEnvironment = (Element)paramSet.next();
        if (!paramRoundEnvironment.getKind().equals(ElementKind.CLASS)) {
          Utils.error("The RealmClass annotation can only be applied to classes", paramRoundEnvironment);
        }
        localObject = new ClassMetaData(this.processingEnv, (TypeElement)paramRoundEnvironment);
        if (((ClassMetaData)localObject).isModelClass())
        {
          Utils.note("Processing class " + ((ClassMetaData)localObject).getSimpleClassName());
          if (!((ClassMetaData)localObject).generateMetaData(this.processingEnv.getMessager())) {
            this.done = true;
          }
        }
      }
    }
    while (this.done)
    {
      return true;
      this.classesToValidate.add(localObject);
      Object localObject = new RealmProxyClassGenerator(this.processingEnv, (ClassMetaData)localObject);
      try
      {
        ((RealmProxyClassGenerator)localObject).generate();
      }
      catch (IOException localIOException)
      {
        Utils.error(localIOException.getMessage(), paramRoundEnvironment);
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        Utils.error(localUnsupportedOperationException.getMessage(), paramRoundEnvironment);
      }
      break;
    }
    paramSet = new RealmValidationListGenerator(this.processingEnv, this.classesToValidate);
    paramRoundEnvironment = new RealmJSonImplGenerator(this.processingEnv, this.classesToValidate);
    try
    {
      paramSet.generate();
      paramRoundEnvironment.generate();
      this.done = true;
      return true;
    }
    catch (IOException paramSet)
    {
      Utils.error(paramSet.getMessage());
    }
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/realm/processor/RealmProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */