package com.annimon.stream;

import com.annimon.stream.function.Consumer;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Predicate;
import com.annimon.stream.function.Supplier;
import java.util.NoSuchElementException;

public class Optional<T>
{
  private static final Optional<?> EMPTY = new Optional();
  private final T value;
  
  private Optional()
  {
    this.value = null;
  }
  
  private Optional(T paramT)
  {
    this.value = Objects.requireNonNull(paramT);
  }
  
  public static <T> Optional<T> empty()
  {
    return EMPTY;
  }
  
  public static <T> Optional<T> of(T paramT)
  {
    return new Optional(paramT);
  }
  
  public static <T> Optional<T> ofNullable(T paramT)
  {
    if (paramT == null) {
      return EMPTY;
    }
    return of(paramT);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof Optional)) {
      return false;
    }
    paramObject = (Optional)paramObject;
    return Objects.equals(this.value, ((Optional)paramObject).value);
  }
  
  public Optional<T> filter(Predicate<? super T> paramPredicate)
  {
    if (!isPresent()) {}
    while (paramPredicate.test(this.value)) {
      return this;
    }
    return EMPTY;
  }
  
  public <U> Optional<U> flatMap(Function<? super T, Optional<U>> paramFunction)
  {
    if (!isPresent()) {
      return empty();
    }
    return (Optional)Objects.requireNonNull(paramFunction.apply(this.value));
  }
  
  public T get()
  {
    if (this.value == null) {
      throw new NoSuchElementException("No value present");
    }
    return (T)this.value;
  }
  
  public int hashCode()
  {
    return Objects.hashCode(this.value);
  }
  
  public void ifPresent(Consumer<? super T> paramConsumer)
  {
    if (this.value != null) {
      paramConsumer.accept(this.value);
    }
  }
  
  public boolean isPresent()
  {
    return this.value != null;
  }
  
  public <U> Optional<U> map(Function<? super T, ? extends U> paramFunction)
  {
    if (!isPresent()) {
      return empty();
    }
    return ofNullable(paramFunction.apply(this.value));
  }
  
  public T orElse(T paramT)
  {
    if (this.value != null) {
      paramT = this.value;
    }
    return paramT;
  }
  
  public T orElseGet(Supplier<? extends T> paramSupplier)
  {
    if (this.value != null) {
      return (T)this.value;
    }
    return (T)paramSupplier.get();
  }
  
  public <X extends Throwable> T orElseThrow(Supplier<? extends X> paramSupplier)
    throws Throwable
  {
    if (this.value != null) {
      return (T)this.value;
    }
    throw ((Throwable)paramSupplier.get());
  }
  
  public String toString()
  {
    if (this.value != null) {
      return String.format("Optional[%s]", new Object[] { this.value });
    }
    return "Optional.empty";
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/annimon/stream/Optional.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */