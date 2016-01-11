package nucleus.factory;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import nucleus.presenter.Presenter;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPresenter
{
  Class<? extends Presenter> value();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/factory/RequiresPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */