package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.BitSet;

public final class CharsToNameCanonicalizer
{
  protected static final int DEFAULT_T_SIZE = 64;
  public static final int HASH_MULT = 33;
  static final int MAX_COLL_CHAIN_LENGTH = 100;
  static final int MAX_ENTRIES_FOR_REUSE = 12000;
  protected static final int MAX_T_SIZE = 65536;
  static final CharsToNameCanonicalizer sBootstrapSymbolTable = new CharsToNameCanonicalizer();
  protected Bucket[] _buckets;
  protected boolean _canonicalize;
  protected boolean _dirty;
  protected final int _flags;
  private final int _hashSeed;
  protected int _indexMask;
  protected int _longestCollisionList;
  protected BitSet _overflows;
  protected CharsToNameCanonicalizer _parent;
  protected int _size;
  protected int _sizeThreshold;
  protected String[] _symbols;
  
  private CharsToNameCanonicalizer()
  {
    this._canonicalize = true;
    this._flags = -1;
    this._dirty = true;
    this._hashSeed = 0;
    this._longestCollisionList = 0;
    initTables(64);
  }
  
  private CharsToNameCanonicalizer(CharsToNameCanonicalizer paramCharsToNameCanonicalizer, int paramInt1, String[] paramArrayOfString, Bucket[] paramArrayOfBucket, int paramInt2, int paramInt3, int paramInt4)
  {
    this._parent = paramCharsToNameCanonicalizer;
    this._flags = paramInt1;
    this._canonicalize = JsonFactory.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(paramInt1);
    this._symbols = paramArrayOfString;
    this._buckets = paramArrayOfBucket;
    this._size = paramInt2;
    this._hashSeed = paramInt3;
    paramInt1 = paramArrayOfString.length;
    this._sizeThreshold = _thresholdSize(paramInt1);
    this._indexMask = (paramInt1 - 1);
    this._longestCollisionList = paramInt4;
    this._dirty = false;
  }
  
  private String _addSymbol(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!this._dirty)
    {
      copyArrays();
      this._dirty = true;
    }
    for (;;)
    {
      localObject = new String(paramArrayOfChar, paramInt1, paramInt2);
      paramArrayOfChar = (char[])localObject;
      if (JsonFactory.Feature.INTERN_FIELD_NAMES.enabledIn(this._flags)) {
        paramArrayOfChar = InternCache.instance.intern((String)localObject);
      }
      this._size += 1;
      if (this._symbols[paramInt4] != null) {
        break;
      }
      this._symbols[paramInt4] = paramArrayOfChar;
      return paramArrayOfChar;
      if (this._size >= this._sizeThreshold)
      {
        rehash();
        paramInt4 = _hashToIndex(calcHash(paramArrayOfChar, paramInt1, paramInt2));
      }
    }
    paramInt1 = paramInt4 >> 1;
    Object localObject = new Bucket(paramArrayOfChar, this._buckets[paramInt1]);
    paramInt2 = ((Bucket)localObject).length;
    if (paramInt2 > 100)
    {
      _handleSpillOverflow(paramInt1, (Bucket)localObject);
      return paramArrayOfChar;
    }
    this._buckets[paramInt1] = localObject;
    this._longestCollisionList = Math.max(paramInt2, this._longestCollisionList);
    return paramArrayOfChar;
  }
  
  private String _findSymbol2(char[] paramArrayOfChar, int paramInt1, int paramInt2, Bucket paramBucket)
  {
    while (paramBucket != null)
    {
      String str = paramBucket.has(paramArrayOfChar, paramInt1, paramInt2);
      if (str != null) {
        return str;
      }
      paramBucket = paramBucket.next;
    }
    return null;
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
      this._symbols[(paramInt + paramInt)] = paramBucket.symbol;
      this._buckets[paramInt] = null;
      this._size -= paramBucket.length;
      this._longestCollisionList = -1;
      return;
      if (this._overflows.get(paramInt))
      {
        if (JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(this._flags)) {
          reportTooManyCollisions(100);
        }
        this._canonicalize = false;
      }
      else
      {
        this._overflows.set(paramInt);
      }
    }
  }
  
  private static int _thresholdSize(int paramInt)
  {
    return paramInt - (paramInt >> 2);
  }
  
  private void copyArrays()
  {
    Object localObject = this._symbols;
    this._symbols = ((String[])Arrays.copyOf((Object[])localObject, localObject.length));
    localObject = this._buckets;
    this._buckets = ((Bucket[])Arrays.copyOf((Object[])localObject, localObject.length));
  }
  
  public static CharsToNameCanonicalizer createRoot()
  {
    long l = System.currentTimeMillis();
    return createRoot((int)l + (int)(l >>> 32) | 0x1);
  }
  
  protected static CharsToNameCanonicalizer createRoot(int paramInt)
  {
    return sBootstrapSymbolTable.makeOrphan(paramInt);
  }
  
  private void initTables(int paramInt)
  {
    this._symbols = new String[paramInt];
    this._buckets = new Bucket[paramInt >> 1];
    this._indexMask = (paramInt - 1);
    this._size = 0;
    this._longestCollisionList = 0;
    this._sizeThreshold = _thresholdSize(paramInt);
  }
  
  private CharsToNameCanonicalizer makeOrphan(int paramInt)
  {
    return new CharsToNameCanonicalizer(null, -1, this._symbols, this._buckets, this._size, paramInt, this._longestCollisionList);
  }
  
  private void mergeChild(CharsToNameCanonicalizer paramCharsToNameCanonicalizer)
  {
    if (paramCharsToNameCanonicalizer.size() > 12000) {
      try
      {
        initTables(256);
        this._dirty = false;
        return;
      }
      finally {}
    }
    if (paramCharsToNameCanonicalizer.size() > size()) {
      try
      {
        this._symbols = paramCharsToNameCanonicalizer._symbols;
        this._buckets = paramCharsToNameCanonicalizer._buckets;
        this._size = paramCharsToNameCanonicalizer._size;
        this._sizeThreshold = paramCharsToNameCanonicalizer._sizeThreshold;
        this._indexMask = paramCharsToNameCanonicalizer._indexMask;
        this._longestCollisionList = paramCharsToNameCanonicalizer._longestCollisionList;
        this._dirty = false;
        return;
      }
      finally {}
    }
  }
  
  private void rehash()
  {
    int i1 = this._symbols.length;
    int i = i1 + i1;
    if (i > 65536)
    {
      this._size = 0;
      this._canonicalize = false;
      this._symbols = new String[64];
      this._buckets = new Bucket[32];
      this._indexMask = 63;
      this._dirty = true;
    }
    int k;
    label182:
    do
    {
      return;
      Object localObject1 = this._symbols;
      Bucket[] arrayOfBucket = this._buckets;
      this._symbols = new String[i];
      this._buckets = new Bucket[i >> 1];
      this._indexMask = (i - 1);
      this._sizeThreshold = _thresholdSize(i);
      int j = 0;
      i = 0;
      k = 0;
      Object localObject2;
      if (k < i1)
      {
        localObject2 = localObject1[k];
        m = j;
        int n = i;
        if (localObject2 != null)
        {
          m = j + 1;
          j = _hashToIndex(calcHash((String)localObject2));
          if (this._symbols[j] != null) {
            break label182;
          }
          this._symbols[j] = localObject2;
        }
        for (n = i;; n = Math.max(i, ((Bucket)localObject2).length))
        {
          k += 1;
          j = m;
          i = n;
          break;
          j >>= 1;
          localObject2 = new Bucket((String)localObject2, this._buckets[j]);
          this._buckets[j] = localObject2;
        }
      }
      k = 0;
      int m = i;
      i = k;
      k = j;
      while (i < i1 >> 1)
      {
        localObject1 = arrayOfBucket[i];
        j = m;
        if (localObject1 != null)
        {
          k += 1;
          localObject2 = ((Bucket)localObject1).symbol;
          m = _hashToIndex(calcHash((String)localObject2));
          if (this._symbols[m] == null) {
            this._symbols[m] = localObject2;
          }
          for (;;)
          {
            localObject1 = ((Bucket)localObject1).next;
            break;
            m >>= 1;
            localObject2 = new Bucket((String)localObject2, this._buckets[m]);
            this._buckets[m] = localObject2;
            j = Math.max(j, ((Bucket)localObject2).length);
          }
        }
        i += 1;
        m = j;
      }
      this._longestCollisionList = m;
      this._overflows = null;
    } while (k == this._size);
    throw new Error("Internal error on SymbolTable.rehash(): had " + this._size + " entries; now have " + k + ".");
  }
  
  public int _hashToIndex(int paramInt)
  {
    paramInt += (paramInt >>> 15);
    paramInt ^= paramInt << 7;
    return this._indexMask & paramInt + (paramInt >>> 3);
  }
  
  public int bucketCount()
  {
    return this._symbols.length;
  }
  
  public int calcHash(String paramString)
  {
    int k = paramString.length();
    int i = this._hashSeed;
    int j = 0;
    while (j < k)
    {
      i = i * 33 + paramString.charAt(j);
      j += 1;
    }
    j = i;
    if (i == 0) {
      j = 1;
    }
    return j;
  }
  
  public int calcHash(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i = this._hashSeed;
    int j = paramInt1;
    while (j < paramInt1 + paramInt2)
    {
      i = i * 33 + paramArrayOfChar[j];
      j += 1;
    }
    paramInt1 = i;
    if (i == 0) {
      paramInt1 = 1;
    }
    return paramInt1;
  }
  
  public int collisionCount()
  {
    int j = 0;
    Bucket[] arrayOfBucket = this._buckets;
    int m = arrayOfBucket.length;
    int i = 0;
    while (i < m)
    {
      Bucket localBucket = arrayOfBucket[i];
      int k = j;
      if (localBucket != null) {
        k = j + localBucket.length;
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  public String findSymbol(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject;
    if (paramInt2 < 1) {
      localObject = "";
    }
    int k;
    String str;
    do
    {
      Bucket localBucket;
      do
      {
        return (String)localObject;
        if (!this._canonicalize) {
          return new String(paramArrayOfChar, paramInt1, paramInt2);
        }
        k = _hashToIndex(paramInt3);
        localObject = this._symbols[k];
        if (localObject == null) {
          break;
        }
        if (((String)localObject).length() == paramInt2)
        {
          int i = 0;
          while (((String)localObject).charAt(i) == paramArrayOfChar[(paramInt1 + i)])
          {
            int j = i + 1;
            i = j;
            if (j == paramInt2) {
              return (String)localObject;
            }
          }
        }
        localBucket = this._buckets[(k >> 1)];
        if (localBucket == null) {
          break;
        }
        str = localBucket.has(paramArrayOfChar, paramInt1, paramInt2);
        localObject = str;
      } while (str != null);
      str = _findSymbol2(paramArrayOfChar, paramInt1, paramInt2, localBucket.next);
      localObject = str;
    } while (str != null);
    return _addSymbol(paramArrayOfChar, paramInt1, paramInt2, paramInt3, k);
  }
  
  public int hashSeed()
  {
    return this._hashSeed;
  }
  
  public CharsToNameCanonicalizer makeChild(int paramInt)
  {
    try
    {
      String[] arrayOfString = this._symbols;
      Bucket[] arrayOfBucket = this._buckets;
      int i = this._size;
      int j = this._hashSeed;
      int k = this._longestCollisionList;
      return new CharsToNameCanonicalizer(this, paramInt, arrayOfString, arrayOfBucket, i, j, k);
    }
    finally {}
  }
  
  public int maxCollisionLength()
  {
    return this._longestCollisionList;
  }
  
  public boolean maybeDirty()
  {
    return this._dirty;
  }
  
  public void release()
  {
    if (!maybeDirty()) {}
    while ((this._parent == null) || (!this._canonicalize)) {
      return;
    }
    this._parent.mergeChild(this);
    this._dirty = false;
  }
  
  protected void reportTooManyCollisions(int paramInt)
  {
    throw new IllegalStateException("Longest collision chain in symbol table (of size " + this._size + ") now exceeds maximum, " + paramInt + " -- suspect a DoS attack based on hash collisions");
  }
  
  public int size()
  {
    return this._size;
  }
  
  static final class Bucket
  {
    public final int length;
    public final Bucket next;
    public final String symbol;
    
    public Bucket(String paramString, Bucket paramBucket)
    {
      this.symbol = paramString;
      this.next = paramBucket;
      if (paramBucket == null) {}
      for (int i = 1;; i = paramBucket.length + 1)
      {
        this.length = i;
        return;
      }
    }
    
    public String has(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      if (this.symbol.length() != paramInt2) {}
      int j;
      do
      {
        while (this.symbol.charAt(i) != paramArrayOfChar[(paramInt1 + i)])
        {
          return null;
          i = 0;
        }
        j = i + 1;
        int i = j;
      } while (j < paramInt2);
      return this.symbol;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/sym/CharsToNameCanonicalizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */