package org.parceler;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import org.parceler.converter.ArrayListParcelConverter;
import org.parceler.converter.BooleanArrayParcelConverter;
import org.parceler.converter.CharArrayParcelConverter;
import org.parceler.converter.CollectionParcelConverter;
import org.parceler.converter.HashMapParcelConverter;
import org.parceler.converter.HashSetParcelConverter;
import org.parceler.converter.LinkedHashMapParcelConverter;
import org.parceler.converter.LinkedHashSetParcelConverter;
import org.parceler.converter.LinkedListParcelConverter;
import org.parceler.converter.NullableParcelConverter;
import org.parceler.converter.SparseArrayParcelConverter;
import org.parceler.converter.TreeMapParcelConverter;
import org.parceler.converter.TreeSetParcelConverter;

final class NonParcelRepository
  implements Repository<Parcels.ParcelableFactory>
{
  private static final NonParcelRepository INSTANCE = new NonParcelRepository();
  private final Map<Class, Parcels.ParcelableFactory> parcelableCollectionFactories = new HashMap();
  
  private NonParcelRepository()
  {
    this.parcelableCollectionFactories.put(Collection.class, new CollectionParcelableFactory(null));
    this.parcelableCollectionFactories.put(List.class, new ListParcelableFactory(null));
    this.parcelableCollectionFactories.put(ArrayList.class, new ListParcelableFactory(null));
    this.parcelableCollectionFactories.put(Set.class, new SetParcelableFactory(null));
    this.parcelableCollectionFactories.put(HashSet.class, new SetParcelableFactory(null));
    this.parcelableCollectionFactories.put(TreeSet.class, new TreeSetParcelableFactory(null));
    this.parcelableCollectionFactories.put(SparseArray.class, new SparseArrayParcelableFactory(null));
    this.parcelableCollectionFactories.put(Map.class, new MapParcelableFactory(null));
    this.parcelableCollectionFactories.put(HashMap.class, new MapParcelableFactory(null));
    this.parcelableCollectionFactories.put(TreeMap.class, new TreeMapParcelableFactory(null));
    this.parcelableCollectionFactories.put(Integer.class, new IntegerParcelableFactory(null));
    this.parcelableCollectionFactories.put(Long.class, new LongParcelableFactory(null));
    this.parcelableCollectionFactories.put(Double.class, new DoubleParcelableFactory(null));
    this.parcelableCollectionFactories.put(Float.class, new FloatParcelableFactory(null));
    this.parcelableCollectionFactories.put(Byte.class, new ByteParcelableFactory(null));
    this.parcelableCollectionFactories.put(String.class, new StringParcelableFactory(null));
    this.parcelableCollectionFactories.put(Character.class, new CharacterParcelableFactory(null));
    this.parcelableCollectionFactories.put(Boolean.class, new BooleanParcelableFactory(null));
    this.parcelableCollectionFactories.put(byte[].class, new ByteArrayParcelableFactory(null));
    this.parcelableCollectionFactories.put(char[].class, new CharArrayParcelableFactory(null));
    this.parcelableCollectionFactories.put(boolean[].class, new BooleanArrayParcelableFactory(null));
    this.parcelableCollectionFactories.put(IBinder.class, new IBinderParcelableFactory(null));
    this.parcelableCollectionFactories.put(Bundle.class, new BundleParcelableFactory(null));
    this.parcelableCollectionFactories.put(SparseBooleanArray.class, new SparseBooleanArrayParcelableFactory(null));
    this.parcelableCollectionFactories.put(LinkedList.class, new LinkedListParcelableFactory(null));
    this.parcelableCollectionFactories.put(LinkedHashMap.class, new LinkedHashMapParcelableFactory(null));
    this.parcelableCollectionFactories.put(SortedMap.class, new TreeMapParcelableFactory(null));
    this.parcelableCollectionFactories.put(SortedSet.class, new TreeSetParcelableFactory(null));
    this.parcelableCollectionFactories.put(LinkedHashSet.class, new LinkedHashSetParcelableFactory(null));
  }
  
  public static NonParcelRepository getInstance()
  {
    return INSTANCE;
  }
  
  public Map<Class, Parcels.ParcelableFactory> get()
  {
    return this.parcelableCollectionFactories;
  }
  
  public static final class BooleanArrayParcelable
    extends NonParcelRepository.ConverterParcelable<boolean[]>
  {
    private static final BooleanArrayParcelConverter CONVERTER = new BooleanArrayParcelConverter();
    public static final BooleanArrayParcelableCreator CREATOR = new BooleanArrayParcelableCreator(null);
    
    public BooleanArrayParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public BooleanArrayParcelable(boolean[] paramArrayOfBoolean)
    {
      super(CONVERTER, null);
    }
    
    private static final class BooleanArrayParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.BooleanArrayParcelable>
    {
      public NonParcelRepository.BooleanArrayParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.BooleanArrayParcelable(paramParcel);
      }
      
      public NonParcelRepository.BooleanArrayParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.BooleanArrayParcelable[paramInt];
      }
    }
  }
  
  private static class BooleanArrayParcelableFactory
    implements Parcels.ParcelableFactory<boolean[]>
  {
    public Parcelable buildParcelable(boolean[] paramArrayOfBoolean)
    {
      return new NonParcelRepository.BooleanArrayParcelable(paramArrayOfBoolean);
    }
  }
  
  public static final class BooleanParcelable
    extends NonParcelRepository.ConverterParcelable<Boolean>
  {
    private static final NullableParcelConverter<Boolean> CONVERTER = new NullableParcelConverter()
    {
      public Boolean nullSafeFromParcel(Parcel paramAnonymousParcel)
      {
        return Boolean.valueOf(paramAnonymousParcel.createBooleanArray()[0]);
      }
      
      public void nullSafeToParcel(Boolean paramAnonymousBoolean, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeBooleanArray(new boolean[] { paramAnonymousBoolean.booleanValue() });
      }
    };
    public static final BooleanParcelableCreator CREATOR = new BooleanParcelableCreator(null);
    
    public BooleanParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public BooleanParcelable(boolean paramBoolean)
    {
      super(CONVERTER, null);
    }
    
    private static final class BooleanParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.BooleanParcelable>
    {
      public NonParcelRepository.BooleanParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.BooleanParcelable(paramParcel);
      }
      
      public NonParcelRepository.BooleanParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.BooleanParcelable[paramInt];
      }
    }
  }
  
  private static class BooleanParcelableFactory
    implements Parcels.ParcelableFactory<Boolean>
  {
    public Parcelable buildParcelable(Boolean paramBoolean)
    {
      return new NonParcelRepository.BooleanParcelable(paramBoolean.booleanValue());
    }
  }
  
  private static class BundleParcelableFactory
    implements Parcels.ParcelableFactory<Bundle>
  {
    public Parcelable buildParcelable(Bundle paramBundle)
    {
      return paramBundle;
    }
  }
  
  public static final class ByteArrayParcelable
    extends NonParcelRepository.ConverterParcelable<byte[]>
  {
    private static final NullableParcelConverter<byte[]> CONVERTER = new NullableParcelConverter()
    {
      public byte[] nullSafeFromParcel(Parcel paramAnonymousParcel)
      {
        return paramAnonymousParcel.createByteArray();
      }
      
      public void nullSafeToParcel(byte[] paramAnonymousArrayOfByte, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeByteArray(paramAnonymousArrayOfByte);
      }
    };
    public static final ByteArrayParcelableCreator CREATOR = new ByteArrayParcelableCreator(null);
    
    public ByteArrayParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public ByteArrayParcelable(byte[] paramArrayOfByte)
    {
      super(CONVERTER, null);
    }
    
    private static final class ByteArrayParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.ByteArrayParcelable>
    {
      public NonParcelRepository.ByteArrayParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.ByteArrayParcelable(paramParcel);
      }
      
      public NonParcelRepository.ByteArrayParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.ByteArrayParcelable[paramInt];
      }
    }
  }
  
  private static class ByteArrayParcelableFactory
    implements Parcels.ParcelableFactory<byte[]>
  {
    public Parcelable buildParcelable(byte[] paramArrayOfByte)
    {
      return new NonParcelRepository.ByteArrayParcelable(paramArrayOfByte);
    }
  }
  
  public static final class ByteParcelable
    extends NonParcelRepository.ConverterParcelable<Byte>
  {
    private static final NullableParcelConverter<Byte> CONVERTER = new NullableParcelConverter()
    {
      public Byte nullSafeFromParcel(Parcel paramAnonymousParcel)
      {
        return Byte.valueOf(paramAnonymousParcel.readByte());
      }
      
      public void nullSafeToParcel(Byte paramAnonymousByte, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeByte(paramAnonymousByte.byteValue());
      }
    };
    public static final ByteParcelableCreator CREATOR = new ByteParcelableCreator(null);
    
    public ByteParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public ByteParcelable(Byte paramByte)
    {
      super(CONVERTER, null);
    }
    
    private static final class ByteParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.ByteParcelable>
    {
      public NonParcelRepository.ByteParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.ByteParcelable(paramParcel);
      }
      
      public NonParcelRepository.ByteParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.ByteParcelable[paramInt];
      }
    }
  }
  
  private static class ByteParcelableFactory
    implements Parcels.ParcelableFactory<Byte>
  {
    public Parcelable buildParcelable(Byte paramByte)
    {
      return new NonParcelRepository.ByteParcelable(paramByte);
    }
  }
  
  public static final class CharArrayParcelable
    extends NonParcelRepository.ConverterParcelable<char[]>
  {
    private static final CharArrayParcelConverter CONVERTER = new CharArrayParcelConverter();
    public static final CharArrayParcelableCreator CREATOR = new CharArrayParcelableCreator(null);
    
    public CharArrayParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public CharArrayParcelable(char[] paramArrayOfChar)
    {
      super(CONVERTER, null);
    }
    
    private static final class CharArrayParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.CharArrayParcelable>
    {
      public NonParcelRepository.CharArrayParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.CharArrayParcelable(paramParcel);
      }
      
      public NonParcelRepository.CharArrayParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.CharArrayParcelable[paramInt];
      }
    }
  }
  
  private static class CharArrayParcelableFactory
    implements Parcels.ParcelableFactory<char[]>
  {
    public Parcelable buildParcelable(char[] paramArrayOfChar)
    {
      return new NonParcelRepository.CharArrayParcelable(paramArrayOfChar);
    }
  }
  
  public static final class CharacterParcelable
    extends NonParcelRepository.ConverterParcelable<Character>
  {
    private static final NullableParcelConverter<Character> CONVERTER = new NullableParcelConverter()
    {
      public Character nullSafeFromParcel(Parcel paramAnonymousParcel)
      {
        return Character.valueOf(paramAnonymousParcel.createCharArray()[0]);
      }
      
      public void nullSafeToParcel(Character paramAnonymousCharacter, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeCharArray(new char[] { paramAnonymousCharacter.charValue() });
      }
    };
    public static final CharacterParcelableCreator CREATOR = new CharacterParcelableCreator(null);
    
    public CharacterParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public CharacterParcelable(Character paramCharacter)
    {
      super(CONVERTER, null);
    }
    
    private static final class CharacterParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.CharacterParcelable>
    {
      public NonParcelRepository.CharacterParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.CharacterParcelable(paramParcel);
      }
      
      public NonParcelRepository.CharacterParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.CharacterParcelable[paramInt];
      }
    }
  }
  
  private static class CharacterParcelableFactory
    implements Parcels.ParcelableFactory<Character>
  {
    public Parcelable buildParcelable(Character paramCharacter)
    {
      return new NonParcelRepository.CharacterParcelable(paramCharacter);
    }
  }
  
  public static final class CollectionParcelable
    extends NonParcelRepository.ConverterParcelable<Collection>
  {
    private static final CollectionParcelConverter CONVERTER = new ArrayListParcelConverter()
    {
      public Object itemFromParcel(Parcel paramAnonymousParcel)
      {
        return Parcels.unwrap(paramAnonymousParcel.readParcelable(NonParcelRepository.CollectionParcelable.class.getClassLoader()));
      }
      
      public void itemToParcel(Object paramAnonymousObject, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeParcelable(Parcels.wrap(paramAnonymousObject), 0);
      }
    };
    public static final CollectionParcelableCreator CREATOR = new CollectionParcelableCreator(null);
    
    public CollectionParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public CollectionParcelable(Collection paramCollection)
    {
      super(CONVERTER, null);
    }
    
    private static final class CollectionParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.CollectionParcelable>
    {
      public NonParcelRepository.CollectionParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.CollectionParcelable(paramParcel);
      }
      
      public NonParcelRepository.CollectionParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.CollectionParcelable[paramInt];
      }
    }
  }
  
  private static class CollectionParcelableFactory
    implements Parcels.ParcelableFactory<Collection>
  {
    public Parcelable buildParcelable(Collection paramCollection)
    {
      return new NonParcelRepository.CollectionParcelable(paramCollection);
    }
  }
  
  private static class ConverterParcelable<T>
    implements Parcelable, ParcelWrapper<T>
  {
    private final TypeRangeParcelConverter<T, T> converter;
    private final T value;
    
    private ConverterParcelable(Parcel paramParcel, TypeRangeParcelConverter<T, T> paramTypeRangeParcelConverter)
    {
      this(paramTypeRangeParcelConverter.fromParcel(paramParcel), paramTypeRangeParcelConverter);
    }
    
    private ConverterParcelable(T paramT, TypeRangeParcelConverter<T, T> paramTypeRangeParcelConverter)
    {
      this.converter = paramTypeRangeParcelConverter;
      this.value = paramT;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public T getParcel()
    {
      return (T)this.value;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      this.converter.toParcel(this.value, paramParcel);
    }
  }
  
  public static final class DoubleParcelable
    extends NonParcelRepository.ConverterParcelable<Double>
  {
    private static final NullableParcelConverter<Double> CONVERTER = new NullableParcelConverter()
    {
      public Double nullSafeFromParcel(Parcel paramAnonymousParcel)
      {
        return Double.valueOf(paramAnonymousParcel.readDouble());
      }
      
      public void nullSafeToParcel(Double paramAnonymousDouble, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeDouble(paramAnonymousDouble.doubleValue());
      }
    };
    public static final DoubleParcelableCreator CREATOR = new DoubleParcelableCreator(null);
    
    public DoubleParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public DoubleParcelable(Double paramDouble)
    {
      super(CONVERTER, null);
    }
    
    private static final class DoubleParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.DoubleParcelable>
    {
      public NonParcelRepository.DoubleParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.DoubleParcelable(paramParcel);
      }
      
      public NonParcelRepository.DoubleParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.DoubleParcelable[paramInt];
      }
    }
  }
  
  private static class DoubleParcelableFactory
    implements Parcels.ParcelableFactory<Double>
  {
    public Parcelable buildParcelable(Double paramDouble)
    {
      return new NonParcelRepository.DoubleParcelable(paramDouble);
    }
  }
  
  public static final class FloatParcelable
    extends NonParcelRepository.ConverterParcelable<Float>
  {
    private static final NullableParcelConverter<Float> CONVERTER = new NullableParcelConverter()
    {
      public Float nullSafeFromParcel(Parcel paramAnonymousParcel)
      {
        return Float.valueOf(paramAnonymousParcel.readFloat());
      }
      
      public void nullSafeToParcel(Float paramAnonymousFloat, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeFloat(paramAnonymousFloat.floatValue());
      }
    };
    public static final FloatParcelableCreator CREATOR = new FloatParcelableCreator(null);
    
    public FloatParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public FloatParcelable(Float paramFloat)
    {
      super(CONVERTER, null);
    }
    
    private static final class FloatParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.FloatParcelable>
    {
      public NonParcelRepository.FloatParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.FloatParcelable(paramParcel);
      }
      
      public NonParcelRepository.FloatParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.FloatParcelable[paramInt];
      }
    }
  }
  
  private static class FloatParcelableFactory
    implements Parcels.ParcelableFactory<Float>
  {
    public Parcelable buildParcelable(Float paramFloat)
    {
      return new NonParcelRepository.FloatParcelable(paramFloat);
    }
  }
  
  public static final class IBinderParcelable
    extends NonParcelRepository.ConverterParcelable<IBinder>
  {
    private static final NullableParcelConverter<IBinder> CONVERTER = new NullableParcelConverter()
    {
      public IBinder nullSafeFromParcel(Parcel paramAnonymousParcel)
      {
        return paramAnonymousParcel.readStrongBinder();
      }
      
      public void nullSafeToParcel(IBinder paramAnonymousIBinder, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeStrongBinder(paramAnonymousIBinder);
      }
    };
    public static final IBinderParcelableCreator CREATOR = new IBinderParcelableCreator(null);
    
    public IBinderParcelable(IBinder paramIBinder)
    {
      super(CONVERTER, null);
    }
    
    public IBinderParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    private static final class IBinderParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.IBinderParcelable>
    {
      public NonParcelRepository.IBinderParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.IBinderParcelable(paramParcel);
      }
      
      public NonParcelRepository.IBinderParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.IBinderParcelable[paramInt];
      }
    }
  }
  
  private static class IBinderParcelableFactory
    implements Parcels.ParcelableFactory<IBinder>
  {
    public Parcelable buildParcelable(IBinder paramIBinder)
    {
      return new NonParcelRepository.IBinderParcelable(paramIBinder);
    }
  }
  
  public static final class IntegerParcelable
    extends NonParcelRepository.ConverterParcelable<Integer>
  {
    private static final NullableParcelConverter<Integer> CONVERTER = new NullableParcelConverter()
    {
      public Integer nullSafeFromParcel(Parcel paramAnonymousParcel)
      {
        return Integer.valueOf(paramAnonymousParcel.readInt());
      }
      
      public void nullSafeToParcel(Integer paramAnonymousInteger, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeInt(paramAnonymousInteger.intValue());
      }
    };
    public static final IntegerParcelableCreator CREATOR = new IntegerParcelableCreator(null);
    
    public IntegerParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public IntegerParcelable(Integer paramInteger)
    {
      super(CONVERTER, null);
    }
    
    private static final class IntegerParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.IntegerParcelable>
    {
      public NonParcelRepository.IntegerParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.IntegerParcelable(paramParcel);
      }
      
      public NonParcelRepository.IntegerParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.IntegerParcelable[paramInt];
      }
    }
  }
  
  private static class IntegerParcelableFactory
    implements Parcels.ParcelableFactory<Integer>
  {
    public Parcelable buildParcelable(Integer paramInteger)
    {
      return new NonParcelRepository.IntegerParcelable(paramInteger);
    }
  }
  
  public static final class LinkedHashMapParcelable
    extends NonParcelRepository.ConverterParcelable<LinkedHashMap>
  {
    private static final LinkedHashMapParcelConverter CONVERTER = new LinkedHashMapParcelConverter()
    {
      public Object mapKeyFromParcel(Parcel paramAnonymousParcel)
      {
        return Parcels.unwrap(paramAnonymousParcel.readParcelable(NonParcelRepository.MapParcelable.class.getClassLoader()));
      }
      
      public void mapKeyToParcel(Object paramAnonymousObject, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeParcelable(Parcels.wrap(paramAnonymousObject), 0);
      }
      
      public Object mapValueFromParcel(Parcel paramAnonymousParcel)
      {
        return Parcels.unwrap(paramAnonymousParcel.readParcelable(NonParcelRepository.MapParcelable.class.getClassLoader()));
      }
      
      public void mapValueToParcel(Object paramAnonymousObject, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeParcelable(Parcels.wrap(paramAnonymousObject), 0);
      }
    };
    public static final LinkedHashMapParcelableCreator CREATOR = new LinkedHashMapParcelableCreator(null);
    
    public LinkedHashMapParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public LinkedHashMapParcelable(LinkedHashMap paramLinkedHashMap)
    {
      super(CONVERTER, null);
    }
    
    private static final class LinkedHashMapParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.LinkedHashMapParcelable>
    {
      public NonParcelRepository.LinkedHashMapParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.LinkedHashMapParcelable(paramParcel);
      }
      
      public NonParcelRepository.LinkedHashMapParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.LinkedHashMapParcelable[paramInt];
      }
    }
  }
  
  private static class LinkedHashMapParcelableFactory
    implements Parcels.ParcelableFactory<LinkedHashMap>
  {
    public Parcelable buildParcelable(LinkedHashMap paramLinkedHashMap)
    {
      return new NonParcelRepository.LinkedHashMapParcelable(paramLinkedHashMap);
    }
  }
  
  public static final class LinkedHashSetParcelable
    extends NonParcelRepository.ConverterParcelable<LinkedHashSet>
  {
    private static final LinkedHashSetParcelConverter CONVERTER = new LinkedHashSetParcelConverter()
    {
      public Object itemFromParcel(Parcel paramAnonymousParcel)
      {
        return Parcels.unwrap(paramAnonymousParcel.readParcelable(NonParcelRepository.LinkedHashSetParcelable.class.getClassLoader()));
      }
      
      public void itemToParcel(Object paramAnonymousObject, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeParcelable(Parcels.wrap(paramAnonymousObject), 0);
      }
    };
    public static final LinkedHashSetParcelableCreator CREATOR = new LinkedHashSetParcelableCreator(null);
    
    public LinkedHashSetParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public LinkedHashSetParcelable(LinkedHashSet paramLinkedHashSet)
    {
      super(CONVERTER, null);
    }
    
    private static final class LinkedHashSetParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.LinkedHashSetParcelable>
    {
      public NonParcelRepository.LinkedHashSetParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.LinkedHashSetParcelable(paramParcel);
      }
      
      public NonParcelRepository.LinkedHashSetParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.LinkedHashSetParcelable[paramInt];
      }
    }
  }
  
  private static class LinkedHashSetParcelableFactory
    implements Parcels.ParcelableFactory<LinkedHashSet>
  {
    public Parcelable buildParcelable(LinkedHashSet paramLinkedHashSet)
    {
      return new NonParcelRepository.LinkedHashSetParcelable(paramLinkedHashSet);
    }
  }
  
  public static final class LinkedListParcelable
    extends NonParcelRepository.ConverterParcelable<LinkedList>
  {
    private static final LinkedListParcelConverter CONVERTER = new LinkedListParcelConverter()
    {
      public Object itemFromParcel(Parcel paramAnonymousParcel)
      {
        return Parcels.unwrap(paramAnonymousParcel.readParcelable(NonParcelRepository.LinkedListParcelable.class.getClassLoader()));
      }
      
      public void itemToParcel(Object paramAnonymousObject, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeParcelable(Parcels.wrap(paramAnonymousObject), 0);
      }
    };
    public static final LinkedListParcelableCreator CREATOR = new LinkedListParcelableCreator(null);
    
    public LinkedListParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public LinkedListParcelable(LinkedList paramLinkedList)
    {
      super(CONVERTER, null);
    }
    
    private static final class LinkedListParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.LinkedListParcelable>
    {
      public NonParcelRepository.LinkedListParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.LinkedListParcelable(paramParcel);
      }
      
      public NonParcelRepository.LinkedListParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.LinkedListParcelable[paramInt];
      }
    }
  }
  
  private static class LinkedListParcelableFactory
    implements Parcels.ParcelableFactory<LinkedList>
  {
    public Parcelable buildParcelable(LinkedList paramLinkedList)
    {
      return new NonParcelRepository.LinkedListParcelable(paramLinkedList);
    }
  }
  
  public static final class ListParcelable
    extends NonParcelRepository.ConverterParcelable<List>
  {
    private static final ArrayListParcelConverter CONVERTER = new ArrayListParcelConverter()
    {
      public Object itemFromParcel(Parcel paramAnonymousParcel)
      {
        return Parcels.unwrap(paramAnonymousParcel.readParcelable(NonParcelRepository.ListParcelable.class.getClassLoader()));
      }
      
      public void itemToParcel(Object paramAnonymousObject, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeParcelable(Parcels.wrap(paramAnonymousObject), 0);
      }
    };
    public static final ListParcelableCreator CREATOR = new ListParcelableCreator(null);
    
    public ListParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public ListParcelable(List paramList)
    {
      super(CONVERTER, null);
    }
    
    private static final class ListParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.ListParcelable>
    {
      public NonParcelRepository.ListParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.ListParcelable(paramParcel);
      }
      
      public NonParcelRepository.ListParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.ListParcelable[paramInt];
      }
    }
  }
  
  private static class ListParcelableFactory
    implements Parcels.ParcelableFactory<List>
  {
    public Parcelable buildParcelable(List paramList)
    {
      return new NonParcelRepository.ListParcelable(paramList);
    }
  }
  
  public static final class LongParcelable
    extends NonParcelRepository.ConverterParcelable<Long>
  {
    private static final NullableParcelConverter<Long> CONVERTER = new NullableParcelConverter()
    {
      public Long nullSafeFromParcel(Parcel paramAnonymousParcel)
      {
        return Long.valueOf(paramAnonymousParcel.readLong());
      }
      
      public void nullSafeToParcel(Long paramAnonymousLong, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeLong(paramAnonymousLong.longValue());
      }
    };
    public static final LongParcelableCreator CREATOR = new LongParcelableCreator(null);
    
    public LongParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public LongParcelable(Long paramLong)
    {
      super(CONVERTER, null);
    }
    
    private static final class LongParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.LongParcelable>
    {
      public NonParcelRepository.LongParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.LongParcelable(paramParcel);
      }
      
      public NonParcelRepository.LongParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.LongParcelable[paramInt];
      }
    }
  }
  
  private static class LongParcelableFactory
    implements Parcels.ParcelableFactory<Long>
  {
    public Parcelable buildParcelable(Long paramLong)
    {
      return new NonParcelRepository.LongParcelable(paramLong);
    }
  }
  
  public static final class MapParcelable
    extends NonParcelRepository.ConverterParcelable<Map>
  {
    private static final HashMapParcelConverter CONVERTER = new HashMapParcelConverter()
    {
      public Object mapKeyFromParcel(Parcel paramAnonymousParcel)
      {
        return Parcels.unwrap(paramAnonymousParcel.readParcelable(NonParcelRepository.MapParcelable.class.getClassLoader()));
      }
      
      public void mapKeyToParcel(Object paramAnonymousObject, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeParcelable(Parcels.wrap(paramAnonymousObject), 0);
      }
      
      public Object mapValueFromParcel(Parcel paramAnonymousParcel)
      {
        return Parcels.unwrap(paramAnonymousParcel.readParcelable(NonParcelRepository.MapParcelable.class.getClassLoader()));
      }
      
      public void mapValueToParcel(Object paramAnonymousObject, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeParcelable(Parcels.wrap(paramAnonymousObject), 0);
      }
    };
    public static final MapParcelableCreator CREATOR = new MapParcelableCreator(null);
    
    public MapParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public MapParcelable(Map paramMap)
    {
      super(CONVERTER, null);
    }
    
    private static final class MapParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.MapParcelable>
    {
      public NonParcelRepository.MapParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.MapParcelable(paramParcel);
      }
      
      public NonParcelRepository.MapParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.MapParcelable[paramInt];
      }
    }
  }
  
  private static class MapParcelableFactory
    implements Parcels.ParcelableFactory<Map>
  {
    public Parcelable buildParcelable(Map paramMap)
    {
      return new NonParcelRepository.MapParcelable(paramMap);
    }
  }
  
  public static final class SetParcelable
    extends NonParcelRepository.ConverterParcelable<Set>
  {
    private static final HashSetParcelConverter CONVERTER = new HashSetParcelConverter()
    {
      public Object itemFromParcel(Parcel paramAnonymousParcel)
      {
        return Parcels.unwrap(paramAnonymousParcel.readParcelable(NonParcelRepository.SetParcelable.class.getClassLoader()));
      }
      
      public void itemToParcel(Object paramAnonymousObject, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeParcelable(Parcels.wrap(paramAnonymousObject), 0);
      }
    };
    public static final SetParcelableCreator CREATOR = new SetParcelableCreator(null);
    
    public SetParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public SetParcelable(Set paramSet)
    {
      super(CONVERTER, null);
    }
    
    private static final class SetParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.SetParcelable>
    {
      public NonParcelRepository.SetParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.SetParcelable(paramParcel);
      }
      
      public NonParcelRepository.SetParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.SetParcelable[paramInt];
      }
    }
  }
  
  private static class SetParcelableFactory
    implements Parcels.ParcelableFactory<Set>
  {
    public Parcelable buildParcelable(Set paramSet)
    {
      return new NonParcelRepository.SetParcelable(paramSet);
    }
  }
  
  public static final class SparseArrayParcelable
    extends NonParcelRepository.ConverterParcelable<SparseArray>
  {
    private static final SparseArrayParcelConverter CONVERTER = new SparseArrayParcelConverter()
    {
      public Object itemFromParcel(Parcel paramAnonymousParcel)
      {
        return Parcels.unwrap(paramAnonymousParcel.readParcelable(NonParcelRepository.SparseArrayParcelable.class.getClassLoader()));
      }
      
      public void itemToParcel(Object paramAnonymousObject, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeParcelable(Parcels.wrap(paramAnonymousObject), 0);
      }
    };
    public static final SparseArrayCreator CREATOR = new SparseArrayCreator(null);
    
    public SparseArrayParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public SparseArrayParcelable(SparseArray paramSparseArray)
    {
      super(CONVERTER, null);
    }
    
    private static final class SparseArrayCreator
      implements Parcelable.Creator<NonParcelRepository.SparseArrayParcelable>
    {
      public NonParcelRepository.SparseArrayParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.SparseArrayParcelable(paramParcel);
      }
      
      public NonParcelRepository.SparseArrayParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.SparseArrayParcelable[paramInt];
      }
    }
  }
  
  private static class SparseArrayParcelableFactory
    implements Parcels.ParcelableFactory<SparseArray>
  {
    public Parcelable buildParcelable(SparseArray paramSparseArray)
    {
      return new NonParcelRepository.SparseArrayParcelable(paramSparseArray);
    }
  }
  
  public static final class SparseBooleanArrayParcelable
    extends NonParcelRepository.ConverterParcelable<SparseBooleanArray>
  {
    private static final NullableParcelConverter<SparseBooleanArray> CONVERTER = new NullableParcelConverter()
    {
      public SparseBooleanArray nullSafeFromParcel(Parcel paramAnonymousParcel)
      {
        return paramAnonymousParcel.readSparseBooleanArray();
      }
      
      public void nullSafeToParcel(SparseBooleanArray paramAnonymousSparseBooleanArray, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeSparseBooleanArray(paramAnonymousSparseBooleanArray);
      }
    };
    public static final SparseBooleanArrayCreator CREATOR = new SparseBooleanArrayCreator(null);
    
    public SparseBooleanArrayParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public SparseBooleanArrayParcelable(SparseBooleanArray paramSparseBooleanArray)
    {
      super(CONVERTER, null);
    }
    
    private static final class SparseBooleanArrayCreator
      implements Parcelable.Creator<NonParcelRepository.SparseBooleanArrayParcelable>
    {
      public NonParcelRepository.SparseBooleanArrayParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.SparseBooleanArrayParcelable(paramParcel);
      }
      
      public NonParcelRepository.SparseBooleanArrayParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.SparseBooleanArrayParcelable[paramInt];
      }
    }
  }
  
  private static class SparseBooleanArrayParcelableFactory
    implements Parcels.ParcelableFactory<SparseBooleanArray>
  {
    public Parcelable buildParcelable(SparseBooleanArray paramSparseBooleanArray)
    {
      return new NonParcelRepository.SparseBooleanArrayParcelable(paramSparseBooleanArray);
    }
  }
  
  public static final class StringParcelable
    implements Parcelable, ParcelWrapper<String>
  {
    public static final StringParcelableCreator CREATOR = new StringParcelableCreator(null);
    private String contents;
    
    private StringParcelable(Parcel paramParcel)
    {
      this.contents = paramParcel.readString();
    }
    
    private StringParcelable(String paramString)
    {
      this.contents = paramString;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public String getParcel()
    {
      return this.contents;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeString(this.contents);
    }
    
    private static final class StringParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.StringParcelable>
    {
      public NonParcelRepository.StringParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.StringParcelable(paramParcel, null);
      }
      
      public NonParcelRepository.StringParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.StringParcelable[paramInt];
      }
    }
  }
  
  private static class StringParcelableFactory
    implements Parcels.ParcelableFactory<String>
  {
    public Parcelable buildParcelable(String paramString)
    {
      return new NonParcelRepository.StringParcelable(paramString, null);
    }
  }
  
  public static final class TreeMapParcelable
    extends NonParcelRepository.ConverterParcelable<Map>
  {
    private static final TreeMapParcelConverter CONVERTER = new TreeMapParcelConverter()
    {
      public Object mapKeyFromParcel(Parcel paramAnonymousParcel)
      {
        return Parcels.unwrap(paramAnonymousParcel.readParcelable(NonParcelRepository.MapParcelable.class.getClassLoader()));
      }
      
      public void mapKeyToParcel(Object paramAnonymousObject, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeParcelable(Parcels.wrap(paramAnonymousObject), 0);
      }
      
      public Object mapValueFromParcel(Parcel paramAnonymousParcel)
      {
        return Parcels.unwrap(paramAnonymousParcel.readParcelable(NonParcelRepository.MapParcelable.class.getClassLoader()));
      }
      
      public void mapValueToParcel(Object paramAnonymousObject, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeParcelable(Parcels.wrap(paramAnonymousObject), 0);
      }
    };
    public static final TreeMapParcelableCreator CREATOR = new TreeMapParcelableCreator(null);
    
    public TreeMapParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public TreeMapParcelable(Map paramMap)
    {
      super(CONVERTER, null);
    }
    
    private static final class TreeMapParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.TreeMapParcelable>
    {
      public NonParcelRepository.TreeMapParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.TreeMapParcelable(paramParcel);
      }
      
      public NonParcelRepository.TreeMapParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.TreeMapParcelable[paramInt];
      }
    }
  }
  
  private static class TreeMapParcelableFactory
    implements Parcels.ParcelableFactory<Map>
  {
    public Parcelable buildParcelable(Map paramMap)
    {
      return new NonParcelRepository.TreeMapParcelable(paramMap);
    }
  }
  
  public static final class TreeSetParcelable
    extends NonParcelRepository.ConverterParcelable<Set>
  {
    private static final TreeSetParcelConverter CONVERTER = new TreeSetParcelConverter()
    {
      public Object itemFromParcel(Parcel paramAnonymousParcel)
      {
        return Parcels.unwrap(paramAnonymousParcel.readParcelable(NonParcelRepository.TreeSetParcelable.class.getClassLoader()));
      }
      
      public void itemToParcel(Object paramAnonymousObject, Parcel paramAnonymousParcel)
      {
        paramAnonymousParcel.writeParcelable(Parcels.wrap(paramAnonymousObject), 0);
      }
    };
    public static final TreeSetParcelableCreator CREATOR = new TreeSetParcelableCreator(null);
    
    public TreeSetParcelable(Parcel paramParcel)
    {
      super(CONVERTER, null);
    }
    
    public TreeSetParcelable(Set paramSet)
    {
      super(CONVERTER, null);
    }
    
    private static final class TreeSetParcelableCreator
      implements Parcelable.Creator<NonParcelRepository.TreeSetParcelable>
    {
      public NonParcelRepository.TreeSetParcelable createFromParcel(Parcel paramParcel)
      {
        return new NonParcelRepository.TreeSetParcelable(paramParcel);
      }
      
      public NonParcelRepository.TreeSetParcelable[] newArray(int paramInt)
      {
        return new NonParcelRepository.TreeSetParcelable[paramInt];
      }
    }
  }
  
  private static class TreeSetParcelableFactory
    implements Parcels.ParcelableFactory<Set>
  {
    public Parcelable buildParcelable(Set paramSet)
    {
      return new NonParcelRepository.TreeSetParcelable(paramSet);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/NonParcelRepository.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */