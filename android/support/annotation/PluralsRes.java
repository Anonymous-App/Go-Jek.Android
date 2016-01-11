package android.support.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.LOCAL_VARIABLE})
public @interface PluralsRes {}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/annotation/PluralsRes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */