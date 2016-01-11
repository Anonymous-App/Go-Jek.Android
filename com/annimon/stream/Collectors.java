package com.annimon.stream;

import com.annimon.stream.function.Function;
import java.util.List;
import java.util.Set;

public final class Collectors
{
  public static <T> Collector<T, ?, Double> averaging(Function<? super T, Double> paramFunction)
  {
    return new Collectors.4(paramFunction);
  }
  
  public static Collector<CharSequence, ?, String> joining()
  {
    return new Collectors.3();
  }
  
  public static <T> Collector<T, ?, List<T>> toList()
  {
    return new Collectors.1();
  }
  
  public static <T> Collector<T, ?, Set<T>> toSet()
  {
    return new Collectors.2();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/annimon/stream/Collectors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */