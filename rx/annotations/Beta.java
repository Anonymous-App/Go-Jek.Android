package rx.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
@Beta
public @interface Beta {}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/rx/annotations/Beta.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */