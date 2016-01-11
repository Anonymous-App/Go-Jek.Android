package com.annimon.stream;

import com.annimon.stream.function.BiConsumer;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Supplier;

public abstract interface Collector<T, A, R>
{
  public abstract BiConsumer<A, T> accumulator();
  
  public abstract Function<A, R> finisher();
  
  public abstract Supplier<A> supplier();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/annimon/stream/Collector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */