package com.annimon.stream;

import com.annimon.stream.function.BiConsumer;
import com.annimon.stream.function.BiFunction;
import com.annimon.stream.function.BiFunction.Util;
import com.annimon.stream.function.Consumer;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Predicate;
import com.annimon.stream.function.Supplier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Stream<T>
{
  private static final int MATCH_ALL = 1;
  private static final int MATCH_ANY = 0;
  private static final int MATCH_NONE = 2;
  private final Iterator<? extends T> iterator;
  
  private Stream(Iterable<? extends T> paramIterable)
  {
    this(paramIterable.iterator());
  }
  
  private Stream(Iterator<? extends T> paramIterator)
  {
    this.iterator = paramIterator;
  }
  
  private boolean match(Predicate<? super T> paramPredicate, int paramInt)
  {
    int i;
    if (paramInt == 0)
    {
      i = 1;
      if (paramInt != 1) {
        break label65;
      }
      paramInt = 1;
      for (;;)
      {
        label13:
        if (this.iterator.hasNext())
        {
          j = paramPredicate.test(this.iterator.next());
          if ((j ^ paramInt) != 0) {
            if ((i == 0) || (j == 0)) {
              break;
            }
          }
        }
      }
    }
    label65:
    while (i == 0)
    {
      int j;
      return true;
      i = 0;
      break;
      paramInt = 0;
      break label13;
      return false;
    }
    return false;
  }
  
  public static <T> Stream<T> of(Iterable<? extends T> paramIterable)
  {
    return new Stream(paramIterable);
  }
  
  public static <T> Stream<T> of(Iterator<? extends T> paramIterator)
  {
    return new Stream(paramIterator);
  }
  
  public static <T> Stream<T> of(List<? extends T> paramList)
  {
    return new Stream(new Stream.1(paramList));
  }
  
  public static <T> Stream<T> of(T... paramVarArgs)
  {
    return new Stream(new Stream.2(paramVarArgs));
  }
  
  public static Stream<Integer> ofRange(int paramInt1, int paramInt2)
  {
    return new Stream(new Stream.3(paramInt1, paramInt2));
  }
  
  public static Stream<Long> ofRange(long paramLong1, long paramLong2)
  {
    return new Stream(new Stream.4(paramLong1, paramLong2));
  }
  
  public boolean allMatch(Predicate<? super T> paramPredicate)
  {
    return match(paramPredicate, 1);
  }
  
  public boolean anyMatch(Predicate<? super T> paramPredicate)
  {
    return match(paramPredicate, 0);
  }
  
  public <R, A> R collect(Collector<? super T, A, R> paramCollector)
  {
    Object localObject2 = paramCollector.supplier().get();
    while (this.iterator.hasNext())
    {
      localObject1 = this.iterator.next();
      paramCollector.accumulator().accept(localObject2, localObject1);
    }
    Object localObject1 = localObject2;
    if (paramCollector.finisher() != null) {
      localObject1 = paramCollector.finisher().apply(localObject2);
    }
    return (R)localObject1;
  }
  
  public <R> R collect(Supplier<R> paramSupplier, BiConsumer<R, ? super T> paramBiConsumer)
  {
    paramSupplier = paramSupplier.get();
    while (this.iterator.hasNext()) {
      paramBiConsumer.accept(paramSupplier, this.iterator.next());
    }
    return paramSupplier;
  }
  
  public long count()
  {
    for (long l = 0L; this.iterator.hasNext(); l += 1L) {
      this.iterator.next();
    }
    return l;
  }
  
  public Stream<T> distinct()
  {
    HashSet localHashSet = new HashSet();
    while (this.iterator.hasNext()) {
      localHashSet.add(this.iterator.next());
    }
    return new Stream(localHashSet);
  }
  
  public Stream<T> filter(Predicate<? super T> paramPredicate)
  {
    return new Stream(new Stream.5(this, paramPredicate));
  }
  
  public Optional<T> findFirst()
  {
    if (this.iterator.hasNext()) {
      return Optional.of(this.iterator.next());
    }
    return Optional.empty();
  }
  
  public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> paramFunction)
  {
    return new Stream(new Stream.7(this, paramFunction));
  }
  
  public void forEach(Consumer<? super T> paramConsumer)
  {
    while (this.iterator.hasNext()) {
      paramConsumer.accept(this.iterator.next());
    }
  }
  
  public Stream<T> limit(long paramLong)
  {
    return new Stream(new Stream.10(this, paramLong));
  }
  
  public <R> Stream<R> map(Function<? super T, ? extends R> paramFunction)
  {
    return new Stream(new Stream.6(this, paramFunction));
  }
  
  public Optional<T> max(Comparator<? super T> paramComparator)
  {
    return reduce(BiFunction.Util.maxBy(paramComparator));
  }
  
  public Optional<T> min(Comparator<? super T> paramComparator)
  {
    return reduce(BiFunction.Util.minBy(paramComparator));
  }
  
  public boolean noneMatch(Predicate<? super T> paramPredicate)
  {
    return match(paramPredicate, 2);
  }
  
  public Stream<T> peek(Consumer<? super T> paramConsumer)
  {
    return new Stream(new Stream.9(this, paramConsumer));
  }
  
  public Optional<T> reduce(BiFunction<T, T, T> paramBiFunction)
  {
    int i = 0;
    Object localObject1 = null;
    while (this.iterator.hasNext())
    {
      Object localObject2 = this.iterator.next();
      if (i == 0)
      {
        i = 1;
        localObject1 = localObject2;
      }
      else
      {
        localObject1 = paramBiFunction.apply(localObject1, localObject2);
      }
    }
    if (i != 0) {
      return Optional.of(localObject1);
    }
    return Optional.empty();
  }
  
  public T reduce(T paramT, BiFunction<T, T, T> paramBiFunction)
  {
    while (this.iterator.hasNext()) {
      paramT = paramBiFunction.apply(paramT, this.iterator.next());
    }
    return paramT;
  }
  
  public Stream<T> skip(long paramLong)
  {
    for (long l = 0L; (l < paramLong) && (this.iterator.hasNext()); l += 1L) {
      this.iterator.next();
    }
    return this;
  }
  
  public Stream<T> sorted()
  {
    return sorted(new Stream.8(this));
  }
  
  public Stream<T> sorted(Comparator<? super T> paramComparator)
  {
    ArrayList localArrayList = new ArrayList();
    while (this.iterator.hasNext()) {
      localArrayList.add(this.iterator.next());
    }
    Collections.sort(localArrayList, paramComparator);
    return new Stream(localArrayList);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/annimon/stream/Stream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */