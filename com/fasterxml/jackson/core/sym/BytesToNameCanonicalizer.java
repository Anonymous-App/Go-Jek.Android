package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicReference;

@Deprecated
public final class BytesToNameCanonicalizer
{
  private static final int DEFAULT_T_SIZE = 64;
  static final int INITIAL_COLLISION_LEN = 32;
  static final int LAST_VALID_BUCKET = 254;
  private static final int MAX_COLL_CHAIN_LENGTH = 200;
  static final int MAX_ENTRIES_FOR_REUSE = 6000;
  private static final int MAX_T_SIZE = 65536;
  static final int MIN_HASH_SIZE = 16;
  private static final int MULT = 33;
  private static final int MULT2 = 65599;
  private static final int MULT3 = 31;
  protected int _collCount;
  protected int _collEnd;
  protected Bucket[] _collList;
  private boolean _collListShared;
  protected int _count;
  protected final boolean _failOnDoS;
  protected int[] _hash;
  protected int _hashMask;
  private boolean _hashShared;
  protected boolean _intern;
  protected int _longestCollisionList;
  protected Name[] _mainNames;
  private boolean _namesShared;
  private transient boolean _needRehash;
  protected BitSet _overflows;
  protected final BytesToNameCanonicalizer _parent;
  private final int _seed;
  protected final AtomicReference<TableInfo> _tableInfo;
  
  private BytesToNameCanonicalizer(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
  {
    this._parent = null;
    this._seed = paramInt2;
    this._intern = paramBoolean1;
    this._failOnDoS = paramBoolean2;
    if (paramInt1 < 16) {
      paramInt2 = 16;
    }
    for (;;)
    {
      this._tableInfo = new AtomicReference(initTableInfo(paramInt2));
      return;
      paramInt2 = paramInt1;
      if ((paramInt1 - 1 & paramInt1) != 0)
      {
        paramInt2 = 16;
        while (paramInt2 < paramInt1) {
          paramInt2 += paramInt2;
        }
      }
    }
  }
  
  private BytesToNameCanonicalizer(BytesToNameCanonicalizer paramBytesToNameCanonicalizer, boolean paramBoolean1, int paramInt, boolean paramBoolean2, TableInfo paramTableInfo)
  {
    this._parent = paramBytesToNameCanonicalizer;
    this._seed = paramInt;
    this._intern = paramBoolean1;
    this._failOnDoS = paramBoolean2;
    this._tableInfo = null;
    this._count = paramTableInfo.count;
    this._hashMask = paramTableInfo.mainHashMask;
    this._hash = paramTableInfo.mainHash;
    this._mainNames = paramTableInfo.mainNames;
    this._collList = paramTableInfo.collList;
    this._collCount = paramTableInfo.collCount;
    this._collEnd = paramTableInfo.collEnd;
    this._longestCollisionList = paramTableInfo.longestCollisionList;
    this._needRehash = false;
    this._hashShared = true;
    this._namesShared = true;
    this._collListShared = true;
  }
  
  private void _addSymbol(int paramInt, Name paramName)
  {
    if (this._hashShared) {
      unshareMain();
    }
    if (this._needRehash) {
      rehash();
    }
    this._count += 1;
    int j = paramInt & this._hashMask;
    int i;
    if (this._mainNames[j] == null)
    {
      this._hash[j] = (paramInt << 8);
      if (this._namesShared) {
        unshareNames();
      }
      this._mainNames[j] = paramName;
      paramInt = this._hash.length;
      if (this._count > paramInt >> 1)
      {
        i = paramInt >> 2;
        if (this._count <= paramInt - i) {
          break label290;
        }
        this._needRehash = true;
      }
    }
    label199:
    label265:
    label290:
    while (this._collCount < i)
    {
      return;
      if (this._collListShared) {
        unshareCollision();
      }
      this._collCount += 1;
      int k = this._hash[j];
      paramInt = k & 0xFF;
      if (paramInt == 0) {
        if (this._collEnd <= 254)
        {
          i = this._collEnd;
          this._collEnd += 1;
          paramInt = i;
          if (i >= this._collList.length)
          {
            expandCollision();
            paramInt = i;
          }
          this._hash[j] = (k & 0xFF00 | paramInt + 1);
        }
      }
      for (;;)
      {
        paramName = new Bucket(paramName, this._collList[paramInt]);
        if (paramName.length <= 200) {
          break label265;
        }
        _handleSpillOverflow(paramInt, paramName);
        break;
        paramInt = findBestBucket();
        break label199;
        paramInt -= 1;
      }
      this._collList[paramInt] = paramName;
      this._longestCollisionList = Math.max(paramName.length, this._longestCollisionList);
      break;
    }
    this._needRehash = true;
  }
  
  private void _handleSpillOverflow(int paramInt, Bucket paramBucket)
  {
    if (this._overflows == null)
    {
      this._overflows = new BitSet();
      this._overflows.set(paramInt);
    }
    for (;;)
    {
      this._collList[paramInt] = null;
      this._count -= paramBucket.length;
      this._longestCollisionList = -1;
      return;
      if (this._overflows.get(paramInt))
      {
        if (this._failOnDoS) {
          reportTooManyCollisions(200);
        }
        this._intern = false;
      }
      else
      {
        this._overflows.set(paramInt);
      }
    }
  }
  
  protected static int[] calcQuads(byte[] paramArrayOfByte)
  {
    int n = paramArrayOfByte.length;
    int[] arrayOfInt = new int[(n + 3) / 4];
    int j;
    for (int i = 0; i < n; i = j + 1)
    {
      int k = paramArrayOfByte[i] & 0xFF;
      int m = i + 1;
      j = m;
      i = k;
      if (m < n)
      {
        k = k << 8 | paramArrayOfByte[m] & 0xFF;
        m += 1;
        j = m;
        i = k;
        if (m < n)
        {
          k = k << 8 | paramArrayOfByte[m] & 0xFF;
          m += 1;
          j = m;
          i = k;
          if (m < n)
          {
            i = k << 8 | paramArrayOfByte[m] & 0xFF;
            j = m;
          }
        }
      }
      arrayOfInt[(j >> 2)] = i;
    }
    return arrayOfInt;
  }
  
  private static Name constructName(int paramInt1, String paramString, int paramInt2, int paramInt3)
  {
    if (paramInt3 == 0) {
      return new Name1(paramString, paramInt1, paramInt2);
    }
    return new Name2(paramString, paramInt1, paramInt2, paramInt3);
  }
  
  private static Name constructName(int paramInt1, String paramString, int[] paramArrayOfInt, int paramInt2)
  {
    if (paramInt2 < 4)
    {
      switch (paramInt2)
      {
      default: 
        return new Name3(paramString, paramInt1, paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
      case 1: 
        return new Name1(paramString, paramInt1, paramArrayOfInt[0]);
      }
      return new Name2(paramString, paramInt1, paramArrayOfInt[0], paramArrayOfInt[1]);
    }
    return NameN.construct(paramString, paramInt1, paramArrayOfInt, paramInt2);
  }
  
  public static BytesToNameCanonicalizer createRoot()
  {
    long l = System.currentTimeMillis();
    return createRoot((int)l + (int)(l >>> 32) | 0x1);
  }
  
  protected static BytesToNameCanonicalizer createRoot(int paramInt)
  {
    return new BytesToNameCanonicalizer(64, true, paramInt, true);
  }
  
  private void expandCollision()
  {
    Bucket[] arrayOfBucket = this._collList;
    this._collList = ((Bucket[])Arrays.copyOf(arrayOfBucket, arrayOfBucket.length * 2));
  }
  
  private int findBestBucket()
  {
    Bucket[] arrayOfBucket = this._collList;
    int j = Integer.MAX_VALUE;
    int k = -1;
    int i = 0;
    int i1 = this._collEnd;
    while (i < i1)
    {
      Bucket localBucket = arrayOfBucket[i];
      if (localBucket == null) {}
      int n;
      do
      {
        return i;
        n = localBucket.length;
        m = j;
        if (n >= j) {
          break;
        }
      } while (n == 1);
      int m = n;
      k = i;
      i += 1;
      j = m;
    }
    return k;
  }
  
  public static Name getEmptyName()
  {
    return Name1.getEmptyName();
  }
  
  private TableInfo initTableInfo(int paramInt)
  {
    return new TableInfo(0, paramInt - 1, new int[paramInt], new Name[paramInt], null, 0, 0, 0);
  }
  
  private void mergeChild(TableInfo paramTableInfo)
  {
    int i = paramTableInfo.count;
    TableInfo localTableInfo = (TableInfo)this._tableInfo.get();
    if (i == localTableInfo.count) {
      return;
    }
    if (i > 6000) {
      paramTableInfo = initTableInfo(64);
    }
    this._tableInfo.compareAndSet(localTableInfo, paramTableInfo);
  }
  
  private void nukeSymbols()
  {
    this._count = 0;
    this._longestCollisionList = 0;
    Arrays.fill(this._hash, 0);
    Arrays.fill(this._mainNames, null);
    Arrays.fill(this._collList, null);
    this._collCount = 0;
    this._collEnd = 0;
  }
  
  private void rehash()
  {
    this._needRehash = false;
    this._namesShared = false;
    int m = this._hash.length;
    int i = m + m;
    if (i > 65536) {
      nukeSymbols();
    }
    label346:
    do
    {
      return;
      this._hash = new int[i];
      this._hashMask = (i - 1);
      Object localObject1 = this._mainNames;
      this._mainNames = new Name[i];
      i = 0;
      int j = 0;
      int n;
      while (j < m)
      {
        arrayOfBucket = localObject1[j];
        k = i;
        if (arrayOfBucket != null)
        {
          k = i + 1;
          i = arrayOfBucket.hashCode();
          n = i & this._hashMask;
          this._mainNames[n] = arrayOfBucket;
          this._hash[n] = (i << 8);
        }
        j += 1;
        i = k;
      }
      int i1 = this._collEnd;
      if (i1 == 0)
      {
        this._longestCollisionList = 0;
        return;
      }
      this._collCount = 0;
      this._collEnd = 0;
      this._collListShared = false;
      int k = 0;
      Bucket[] arrayOfBucket = this._collList;
      this._collList = new Bucket[arrayOfBucket.length];
      j = 0;
      while (j < i1)
      {
        localObject1 = arrayOfBucket[j];
        while (localObject1 != null)
        {
          m = i + 1;
          Object localObject2 = ((Bucket)localObject1).name;
          i = ((Name)localObject2).hashCode();
          int i2 = i & this._hashMask;
          int i3 = this._hash[i2];
          if (this._mainNames[i2] == null)
          {
            this._hash[i2] = (i << 8);
            this._mainNames[i2] = localObject2;
            localObject1 = ((Bucket)localObject1).next;
            i = m;
          }
          else
          {
            this._collCount += 1;
            i = i3 & 0xFF;
            if (i == 0) {
              if (this._collEnd <= 254)
              {
                n = this._collEnd;
                this._collEnd += 1;
                i = n;
                if (n >= this._collList.length)
                {
                  expandCollision();
                  i = n;
                }
                this._hash[i2] = (i3 & 0xFF00 | i + 1);
              }
            }
            for (;;)
            {
              localObject2 = new Bucket((Name)localObject2, this._collList[i]);
              this._collList[i] = localObject2;
              k = Math.max(k, ((Bucket)localObject2).length);
              break;
              i = findBestBucket();
              break label346;
              i -= 1;
            }
          }
        }
        j += 1;
      }
      this._longestCollisionList = k;
    } while (i == this._count);
    throw new RuntimeException("Internal error: count after rehash " + i + "; should be " + this._count);
  }
  
  private void unshareCollision()
  {
    Bucket[] arrayOfBucket = this._collList;
    if (arrayOfBucket == null) {}
    for (this._collList = new Bucket[32];; this._collList = ((Bucket[])Arrays.copyOf(arrayOfBucket, arrayOfBucket.length)))
    {
      this._collListShared = false;
      return;
    }
  }
  
  private void unshareMain()
  {
    int[] arrayOfInt = this._hash;
    this._hash = Arrays.copyOf(arrayOfInt, arrayOfInt.length);
    this._hashShared = false;
  }
  
  private void unshareNames()
  {
    Name[] arrayOfName = this._mainNames;
    this._mainNames = ((Name[])Arrays.copyOf(arrayOfName, arrayOfName.length));
    this._namesShared = false;
  }
  
  public Name addName(String paramString, int paramInt1, int paramInt2)
  {
    String str = paramString;
    if (this._intern) {
      str = InternCache.instance.intern(paramString);
    }
    if (paramInt2 == 0) {}
    for (int i = calcHash(paramInt1);; i = calcHash(paramInt1, paramInt2))
    {
      paramString = constructName(i, str, paramInt1, paramInt2);
      _addSymbol(i, paramString);
      return paramString;
    }
  }
  
  public Name addName(String paramString, int[] paramArrayOfInt, int paramInt)
  {
    String str = paramString;
    if (this._intern) {
      str = InternCache.instance.intern(paramString);
    }
    int i;
    if (paramInt < 4) {
      if (paramInt == 1) {
        i = calcHash(paramArrayOfInt[0]);
      }
    }
    for (;;)
    {
      paramString = constructName(i, str, paramArrayOfInt, paramInt);
      _addSymbol(i, paramString);
      return paramString;
      if (paramInt == 2)
      {
        i = calcHash(paramArrayOfInt[0], paramArrayOfInt[1]);
      }
      else
      {
        i = calcHash(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
        continue;
        i = calcHash(paramArrayOfInt, paramInt);
      }
    }
  }
  
  public int bucketCount()
  {
    return this._hash.length;
  }
  
  public int calcHash(int paramInt)
  {
    paramInt ^= this._seed;
    paramInt += (paramInt >>> 15);
    return paramInt ^ paramInt >>> 9;
  }
  
  public int calcHash(int paramInt1, int paramInt2)
  {
    paramInt1 = (paramInt1 ^ paramInt1 >>> 15) + paramInt2 * 33 ^ this._seed;
    paramInt1 += (paramInt1 >>> 7);
    return paramInt1 ^ paramInt1 >>> 4;
  }
  
  public int calcHash(int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt1 ^= this._seed;
    paramInt1 = ((paramInt1 + (paramInt1 >>> 9)) * 33 + paramInt2) * 65599;
    paramInt1 = paramInt1 + (paramInt1 >>> 15) ^ paramInt3;
    paramInt1 += (paramInt1 >>> 17);
    paramInt1 += (paramInt1 >>> 15);
    return paramInt1 ^ paramInt1 << 9;
  }
  
  public int calcHash(int[] paramArrayOfInt, int paramInt)
  {
    if (paramInt < 4) {
      throw new IllegalArgumentException();
    }
    int i = paramArrayOfInt[0] ^ this._seed;
    i = ((i + (i >>> 9)) * 33 + paramArrayOfInt[1]) * 65599;
    i = i + (i >>> 15) ^ paramArrayOfInt[2];
    int j = i + (i >>> 17);
    i = 3;
    while (i < paramInt)
    {
      j = j * 31 ^ paramArrayOfInt[i];
      j += (j >>> 3);
      j ^= j << 7;
      i += 1;
    }
    paramInt = j + (j >>> 15);
    return paramInt ^ paramInt << 9;
  }
  
  public int collisionCount()
  {
    return this._collCount;
  }
  
  public Name findName(int paramInt)
  {
    int i = calcHash(paramInt);
    int j = i & this._hashMask;
    int k = this._hash[j];
    if ((k >> 8 ^ i) << 8 == 0)
    {
      localName = this._mainNames[j];
      if (localName == null) {
        localObject = null;
      }
      do
      {
        return (Name)localObject;
        localObject = localName;
      } while (localName.equals(paramInt));
    }
    while (k != 0)
    {
      Name localName;
      j = k & 0xFF;
      if (j <= 0) {
        break;
      }
      Object localObject = this._collList[(j - 1)];
      if (localObject == null) {
        break;
      }
      return ((Bucket)localObject).find(i, paramInt, 0);
    }
    return null;
    return null;
  }
  
  public Name findName(int paramInt1, int paramInt2)
  {
    int i;
    int j;
    int k;
    if (paramInt2 == 0)
    {
      i = calcHash(paramInt1);
      j = i & this._hashMask;
      k = this._hash[j];
      if ((k >> 8 ^ i) << 8 != 0) {
        break label122;
      }
      localName = this._mainNames[j];
      if (localName != null) {
        break label70;
      }
      localObject = null;
    }
    label70:
    do
    {
      return (Name)localObject;
      i = calcHash(paramInt1, paramInt2);
      break;
      localObject = localName;
    } while (localName.equals(paramInt1, paramInt2));
    label122:
    while (k != 0)
    {
      Name localName;
      j = k & 0xFF;
      if (j <= 0) {
        break;
      }
      Object localObject = this._collList[(j - 1)];
      if (localObject == null) {
        break;
      }
      return ((Bucket)localObject).find(i, paramInt1, paramInt2);
    }
    return null;
    return null;
  }
  
  public Name findName(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = calcHash(paramInt1, paramInt2, paramInt3);
    int j = i & this._hashMask;
    int k = this._hash[j];
    if ((k >> 8 ^ i) << 8 == 0)
    {
      localName = this._mainNames[j];
      if (localName == null) {
        localObject = null;
      }
      do
      {
        return (Name)localObject;
        localObject = localName;
      } while (localName.equals(paramInt1, paramInt2, paramInt3));
    }
    while (k != 0)
    {
      Name localName;
      j = k & 0xFF;
      if (j <= 0) {
        break;
      }
      Object localObject = this._collList[(j - 1)];
      if (localObject == null) {
        break;
      }
      return ((Bucket)localObject).find(i, paramInt1, paramInt2, paramInt3);
    }
    return null;
    return null;
  }
  
  public Name findName(int[] paramArrayOfInt, int paramInt)
  {
    int i = 0;
    Object localObject;
    if (paramInt < 4) {
      if (paramInt == 3) {
        localObject = findName(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
      }
    }
    int j;
    int k;
    do
    {
      do
      {
        return (Name)localObject;
        j = paramArrayOfInt[0];
        if (paramInt < 2) {}
        for (paramInt = i;; paramInt = paramArrayOfInt[1]) {
          return findName(j, paramInt);
        }
        i = calcHash(paramArrayOfInt, paramInt);
        j = i & this._hashMask;
        k = this._hash[j];
        if ((k >> 8 ^ i) << 8 != 0) {
          break;
        }
        localName = this._mainNames[j];
        localObject = localName;
      } while (localName == null);
      localObject = localName;
    } while (localName.equals(paramArrayOfInt, paramInt));
    while (k != 0)
    {
      Name localName;
      j = k & 0xFF;
      if (j <= 0) {
        break;
      }
      localObject = this._collList[(j - 1)];
      if (localObject == null) {
        break;
      }
      return ((Bucket)localObject).find(i, paramArrayOfInt, paramInt);
    }
    return null;
    return null;
  }
  
  public int hashSeed()
  {
    return this._seed;
  }
  
  public BytesToNameCanonicalizer makeChild(int paramInt)
  {
    return new BytesToNameCanonicalizer(this, JsonFactory.Feature.INTERN_FIELD_NAMES.enabledIn(paramInt), this._seed, JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(paramInt), (TableInfo)this._tableInfo.get());
  }
  
  @Deprecated
  public BytesToNameCanonicalizer makeChild(boolean paramBoolean1, boolean paramBoolean2)
  {
    return new BytesToNameCanonicalizer(this, paramBoolean2, this._seed, true, (TableInfo)this._tableInfo.get());
  }
  
  public int maxCollisionLength()
  {
    return this._longestCollisionList;
  }
  
  public boolean maybeDirty()
  {
    return !this._hashShared;
  }
  
  public void release()
  {
    if ((this._parent != null) && (maybeDirty()))
    {
      this._parent.mergeChild(new TableInfo(this));
      this._hashShared = true;
      this._namesShared = true;
      this._collListShared = true;
    }
  }
  
  protected void reportTooManyCollisions(int paramInt)
  {
    throw new IllegalStateException("Longest collision chain in symbol table (of size " + this._count + ") now exceeds maximum, " + paramInt + " -- suspect a DoS attack based on hash collisions");
  }
  
  public int size()
  {
    if (this._tableInfo != null) {
      return ((TableInfo)this._tableInfo.get()).count;
    }
    return this._count;
  }
  
  private static final class Bucket
  {
    public final int hash;
    public final int length;
    public final Name name;
    public final Bucket next;
    
    Bucket(Name paramName, Bucket paramBucket)
    {
      this.name = paramName;
      this.next = paramBucket;
      if (paramBucket == null) {}
      for (int i = 1;; i = paramBucket.length + 1)
      {
        this.length = i;
        this.hash = paramName.hashCode();
        return;
      }
    }
    
    public Name find(int paramInt1, int paramInt2, int paramInt3)
    {
      Object localObject;
      if ((this.hash == paramInt1) && (this.name.equals(paramInt2, paramInt3)))
      {
        localObject = this.name;
        return (Name)localObject;
      }
      for (Bucket localBucket = this.next;; localBucket = localBucket.next)
      {
        if (localBucket == null) {
          break label80;
        }
        if (localBucket.hash == paramInt1)
        {
          Name localName = localBucket.name;
          localObject = localName;
          if (localName.equals(paramInt2, paramInt3)) {
            break;
          }
        }
      }
      label80:
      return null;
    }
    
    public Name find(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      Object localObject;
      if ((this.hash == paramInt1) && (this.name.equals(paramInt2, paramInt3, paramInt4)))
      {
        localObject = this.name;
        return (Name)localObject;
      }
      for (Bucket localBucket = this.next;; localBucket = localBucket.next)
      {
        if (localBucket == null) {
          break label84;
        }
        if (localBucket.hash == paramInt1)
        {
          Name localName = localBucket.name;
          localObject = localName;
          if (localName.equals(paramInt2, paramInt3, paramInt4)) {
            break;
          }
        }
      }
      label84:
      return null;
    }
    
    public Name find(int paramInt1, int[] paramArrayOfInt, int paramInt2)
    {
      Object localObject;
      if ((this.hash == paramInt1) && (this.name.equals(paramArrayOfInt, paramInt2)))
      {
        localObject = this.name;
        return (Name)localObject;
      }
      for (Bucket localBucket = this.next;; localBucket = localBucket.next)
      {
        if (localBucket == null) {
          break label80;
        }
        if (localBucket.hash == paramInt1)
        {
          Name localName = localBucket.name;
          localObject = localName;
          if (localName.equals(paramArrayOfInt, paramInt2)) {
            break;
          }
        }
      }
      label80:
      return null;
    }
  }
  
  private static final class TableInfo
  {
    public final int collCount;
    public final int collEnd;
    public final BytesToNameCanonicalizer.Bucket[] collList;
    public final int count;
    public final int longestCollisionList;
    public final int[] mainHash;
    public final int mainHashMask;
    public final Name[] mainNames;
    
    public TableInfo(int paramInt1, int paramInt2, int[] paramArrayOfInt, Name[] paramArrayOfName, BytesToNameCanonicalizer.Bucket[] paramArrayOfBucket, int paramInt3, int paramInt4, int paramInt5)
    {
      this.count = paramInt1;
      this.mainHashMask = paramInt2;
      this.mainHash = paramArrayOfInt;
      this.mainNames = paramArrayOfName;
      this.collList = paramArrayOfBucket;
      this.collCount = paramInt3;
      this.collEnd = paramInt4;
      this.longestCollisionList = paramInt5;
    }
    
    public TableInfo(BytesToNameCanonicalizer paramBytesToNameCanonicalizer)
    {
      this.count = paramBytesToNameCanonicalizer._count;
      this.mainHashMask = paramBytesToNameCanonicalizer._hashMask;
      this.mainHash = paramBytesToNameCanonicalizer._hash;
      this.mainNames = paramBytesToNameCanonicalizer._mainNames;
      this.collList = paramBytesToNameCanonicalizer._collList;
      this.collCount = paramBytesToNameCanonicalizer._collCount;
      this.collEnd = paramBytesToNameCanonicalizer._collEnd;
      this.longestCollisionList = paramBytesToNameCanonicalizer._longestCollisionList;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/sym/BytesToNameCanonicalizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */