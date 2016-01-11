package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class MultiTransformation<T>
  implements Transformation<T>
{
  private String id;
  private final Collection<? extends Transformation<T>> transformations;
  
  public MultiTransformation(Collection<? extends Transformation<T>> paramCollection)
  {
    if (paramCollection.size() < 1) {
      throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
    }
    this.transformations = paramCollection;
  }
  
  @SafeVarargs
  public MultiTransformation(Transformation<T>... paramVarArgs)
  {
    if (paramVarArgs.length < 1) {
      throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
    }
    this.transformations = Arrays.asList(paramVarArgs);
  }
  
  public String getId()
  {
    if (this.id == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      Iterator localIterator = this.transformations.iterator();
      while (localIterator.hasNext()) {
        localStringBuilder.append(((Transformation)localIterator.next()).getId());
      }
      this.id = localStringBuilder.toString();
    }
    return this.id;
  }
  
  public Resource<T> transform(Resource<T> paramResource, int paramInt1, int paramInt2)
  {
    Object localObject = paramResource;
    Iterator localIterator = this.transformations.iterator();
    while (localIterator.hasNext())
    {
      Resource localResource = ((Transformation)localIterator.next()).transform((Resource)localObject, paramInt1, paramInt2);
      if ((localObject != null) && (!localObject.equals(paramResource)) && (!localObject.equals(localResource))) {
        ((Resource)localObject).recycle();
      }
      localObject = localResource;
    }
    return (Resource<T>)localObject;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/load/MultiTransformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */