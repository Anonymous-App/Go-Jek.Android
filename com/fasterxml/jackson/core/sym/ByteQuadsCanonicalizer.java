package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public final class ByteQuadsCanonicalizer
{
  private static final int DEFAULT_T_SIZE = 64;
  static final int MAX_ENTRIES_FOR_REUSE = 6000;
  private static final int MAX_T_SIZE = 65536;
  static final int MIN_HASH_SIZE = 16;
  private static final int MULT = 33;
  private static final int MULT2 = 65599;
  private static final int MULT3 = 31;
  protected int _count;
  protected final boolean _failOnDoS;
  protected int[] _hashArea;
  private boolean _hashShared;
  protected int _hashSize;
  protected boolean _intern;
  protected int _longNameOffset;
  protected String[] _names;
  private transient boolean _needRehash;
  protected final ByteQuadsCanonicalizer _parent;
  protected int _secondaryStart;
  private final int _seed;
  protected int _spilloverEnd;
  protected final AtomicReference<TableInfo> _tableInfo;
  protected int _tertiaryShift;
  protected int _tertiaryStart;
  
  private ByteQuadsCanonicalizer(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
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
      this._tableInfo = new AtomicReference(TableInfo.createInitial(paramInt2));
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
  
  private ByteQuadsCanonicalizer(ByteQuadsCanonicalizer paramByteQuadsCanonicalizer, boolean paramBoolean1, int paramInt, boolean paramBoolean2, TableInfo paramTableInfo)
  {
    this._parent = paramByteQuadsCanonicalizer;
    this._seed = paramInt;
    this._intern = paramBoolean1;
    this._failOnDoS = paramBoolean2;
    this._tableInfo = null;
    this._count = paramTableInfo.count;
    this._hashSize = paramTableInfo.size;
    this._secondaryStart = (this._hashSize << 2);
    this._tertiaryStart = (this._secondaryStart + (this._secondaryStart >> 1));
    this._tertiaryShift = paramTableInfo.tertiaryShift;
    this._hashArea = paramTableInfo.mainHash;
    this._names = paramTableInfo.names;
    this._spilloverEnd = paramTableInfo.spilloverEnd;
    this._longNameOffset = paramTableInfo.longNameOffset;
    this._needRehash = false;
    this._hashShared = true;
  }
  
  private int _appendLongName(int[] paramArrayOfInt, int paramInt)
  {
    int i = this._longNameOffset;
    if (i + paramInt > this._hashArea.length)
    {
      int k = this._hashArea.length;
      int m = Math.min(4096, this._hashSize);
      int j = this._hashArea.length;
      k = Math.max(i + paramInt - k, m);
      this._hashArea = Arrays.copyOf(this._hashArea, j + k);
    }
    System.arraycopy(paramArrayOfInt, 0, this._hashArea, i, paramInt);
    this._longNameOffset += paramInt;
    return i;
  }
  
  private final int _calcOffset(int paramInt)
  {
    return (paramInt & this._hashSize - 1) << 2;
  }
  
  static int _calcTertiaryShift(int paramInt)
  {
    paramInt >>= 2;
    if (paramInt < 64) {
      return 4;
    }
    if (paramInt <= 256) {
      return 5;
    }
    if (paramInt <= 1024) {
      return 6;
    }
    return 7;
  }
  
  private int _findOffsetForAdd(int paramInt)
  {
    int i = _calcOffset(paramInt);
    int[] arrayOfInt = this._hashArea;
    if (arrayOfInt[(i + 3)] == 0) {
      paramInt = i;
    }
    do
    {
      return paramInt;
      j = this._secondaryStart + (i >> 3 << 2);
      paramInt = j;
    } while (arrayOfInt[(j + 3)] == 0);
    int j = this._tertiaryStart + (i >> this._tertiaryShift + 2 << this._tertiaryShift);
    int k = this._tertiaryShift;
    for (paramInt = j;; paramInt = i + 4)
    {
      i = paramInt;
      if (i >= j + (1 << k)) {
        break label104;
      }
      paramInt = i;
      if (arrayOfInt[(i + 3)] == 0) {
        break;
      }
    }
    label104:
    paramInt = this._spilloverEnd;
    this._spilloverEnd += 4;
    i = this._hashSize;
    if (this._spilloverEnd >= i << 3)
    {
      if (this._failOnDoS) {
        _reportTooManyCollisions();
      }
      this._needRehash = true;
    }
    return paramInt;
  }
  
  private String _findSecondary(int paramInt1, int paramInt2)
  {
    Object localObject2 = null;
    int i = this._tertiaryStart + (paramInt1 >> this._tertiaryShift + 2 << this._tertiaryShift);
    int[] arrayOfInt = this._hashArea;
    int j = this._tertiaryShift;
    paramInt1 = i;
    Object localObject1;
    while (paramInt1 < i + (1 << j))
    {
      int k = arrayOfInt[(paramInt1 + 3)];
      if ((paramInt2 == arrayOfInt[paramInt1]) && (1 == k)) {
        localObject1 = this._names[(paramInt1 >> 2)];
      }
      do
      {
        return (String)localObject1;
        localObject1 = localObject2;
      } while (k == 0);
      paramInt1 += 4;
    }
    paramInt1 = _spilloverStart();
    for (;;)
    {
      localObject1 = localObject2;
      if (paramInt1 >= this._spilloverEnd) {
        break;
      }
      if ((paramInt2 == arrayOfInt[paramInt1]) && (1 == arrayOfInt[(paramInt1 + 3)])) {
        return this._names[(paramInt1 >> 2)];
      }
      paramInt1 += 4;
    }
  }
  
  private String _findSecondary(int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject2 = null;
    int i = this._tertiaryStart + (paramInt1 >> this._tertiaryShift + 2 << this._tertiaryShift);
    int[] arrayOfInt = this._hashArea;
    int j = this._tertiaryShift;
    paramInt1 = i;
    Object localObject1;
    while (paramInt1 < i + (1 << j))
    {
      int k = arrayOfInt[(paramInt1 + 3)];
      if ((paramInt2 == arrayOfInt[paramInt1]) && (paramInt3 == arrayOfInt[(paramInt1 + 1)]) && (2 == k)) {
        localObject1 = this._names[(paramInt1 >> 2)];
      }
      do
      {
        return (String)localObject1;
        localObject1 = localObject2;
      } while (k == 0);
      paramInt1 += 4;
    }
    paramInt1 = _spilloverStart();
    for (;;)
    {
      localObject1 = localObject2;
      if (paramInt1 >= this._spilloverEnd) {
        break;
      }
      if ((paramInt2 == arrayOfInt[paramInt1]) && (paramInt3 == arrayOfInt[(paramInt1 + 1)]) && (2 == arrayOfInt[(paramInt1 + 3)])) {
        return this._names[(paramInt1 >> 2)];
      }
      paramInt1 += 4;
    }
  }
  
  private String _findSecondary(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Object localObject2 = null;
    int i = this._tertiaryStart + (paramInt1 >> this._tertiaryShift + 2 << this._tertiaryShift);
    int[] arrayOfInt = this._hashArea;
    int j = this._tertiaryShift;
    paramInt1 = i;
    Object localObject1;
    while (paramInt1 < i + (1 << j))
    {
      int k = arrayOfInt[(paramInt1 + 3)];
      if ((paramInt2 == arrayOfInt[paramInt1]) && (paramInt3 == arrayOfInt[(paramInt1 + 1)]) && (paramInt4 == arrayOfInt[(paramInt1 + 2)]) && (3 == k)) {
        localObject1 = this._names[(paramInt1 >> 2)];
      }
      do
      {
        return (String)localObject1;
        localObject1 = localObject2;
      } while (k == 0);
      paramInt1 += 4;
    }
    paramInt1 = _spilloverStart();
    for (;;)
    {
      localObject1 = localObject2;
      if (paramInt1 >= this._spilloverEnd) {
        break;
      }
      if ((paramInt2 == arrayOfInt[paramInt1]) && (paramInt3 == arrayOfInt[(paramInt1 + 1)]) && (paramInt4 == arrayOfInt[(paramInt1 + 2)]) && (3 == arrayOfInt[(paramInt1 + 3)])) {
        return this._names[(paramInt1 >> 2)];
      }
      paramInt1 += 4;
    }
  }
  
  private String _findSecondary(int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3)
  {
    Object localObject2 = null;
    int i = this._tertiaryStart + (paramInt1 >> this._tertiaryShift + 2 << this._tertiaryShift);
    int[] arrayOfInt = this._hashArea;
    int j = this._tertiaryShift;
    paramInt1 = i;
    Object localObject1;
    while (paramInt1 < i + (1 << j))
    {
      int k = arrayOfInt[(paramInt1 + 3)];
      if ((paramInt2 == arrayOfInt[paramInt1]) && (paramInt3 == k) && (_verifyLongName(paramArrayOfInt, paramInt3, arrayOfInt[(paramInt1 + 1)]))) {
        localObject1 = this._names[(paramInt1 >> 2)];
      }
      do
      {
        return (String)localObject1;
        localObject1 = localObject2;
      } while (k == 0);
      paramInt1 += 4;
    }
    paramInt1 = _spilloverStart();
    for (;;)
    {
      localObject1 = localObject2;
      if (paramInt1 >= this._spilloverEnd) {
        break;
      }
      if ((paramInt2 == arrayOfInt[paramInt1]) && (paramInt3 == arrayOfInt[(paramInt1 + 3)]) && (_verifyLongName(paramArrayOfInt, paramInt3, arrayOfInt[(paramInt1 + 1)]))) {
        return this._names[(paramInt1 >> 2)];
      }
      paramInt1 += 4;
    }
  }
  
  private final int _spilloverStart()
  {
    int i = this._hashSize;
    return (i << 3) - i;
  }
  
  private boolean _verifyLongName(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    boolean bool2 = false;
    int[] arrayOfInt = this._hashArea;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int m = 0;
    int i = paramInt2;
    int j = paramInt2;
    int n = paramInt2;
    int k = paramInt2;
    boolean bool1;
    switch (paramInt1)
    {
    default: 
      bool1 = _verifyLongName2(paramArrayOfInt, paramInt1, paramInt2);
    }
    do
    {
      do
      {
        return bool1;
        if (paramArrayOfInt[0] != arrayOfInt[paramInt2]) {
          return false;
        }
        m = 0 + 1;
        i = paramInt2 + 1;
        if (paramArrayOfInt[m] != arrayOfInt[i]) {
          return false;
        }
        i1 = m + 1;
        j = i + 1;
        if (paramArrayOfInt[i1] != arrayOfInt[j]) {
          return false;
        }
        i2 = i1 + 1;
        n = j + 1;
        if (paramArrayOfInt[i2] != arrayOfInt[n]) {
          return false;
        }
        i3 = i2 + 1;
        k = n + 1;
        paramInt2 = i3 + 1;
        i = paramArrayOfInt[i3];
        paramInt1 = k + 1;
        if (i != arrayOfInt[k]) {
          return false;
        }
        i = paramInt2 + 1;
        j = paramArrayOfInt[paramInt2];
        paramInt2 = paramInt1 + 1;
        bool1 = bool2;
      } while (j != arrayOfInt[paramInt1]);
      paramInt1 = i + 1;
      i = paramArrayOfInt[i];
      j = paramInt2 + 1;
      if (i != arrayOfInt[paramInt2]) {
        return false;
      }
      bool1 = bool2;
    } while (paramArrayOfInt[paramInt1] != arrayOfInt[j]);
    return true;
  }
  
  private boolean _verifyLongName2(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int i = 0;
    for (;;)
    {
      int j = i + 1;
      if (paramArrayOfInt[i] != this._hashArea[paramInt2]) {
        return false;
      }
      if (j >= paramInt1) {
        return true;
      }
      i = j;
      paramInt2 += 1;
    }
  }
  
  private void _verifyNeedForRehash()
  {
    if ((this._count > this._hashSize >> 1) && ((this._spilloverEnd - _spilloverStart() >> 2 > this._count + 1 >> 7) || (this._count > this._hashSize * 0.8D))) {
      this._needRehash = true;
    }
  }
  
  private void _verifySharing()
  {
    if (this._hashShared)
    {
      this._hashArea = Arrays.copyOf(this._hashArea, this._hashArea.length);
      this._names = ((String[])Arrays.copyOf(this._names, this._names.length));
      this._hashShared = false;
      _verifyNeedForRehash();
    }
    if (this._needRehash) {
      rehash();
    }
  }
  
  public static ByteQuadsCanonicalizer createRoot()
  {
    long l = System.currentTimeMillis();
    return createRoot((int)l + (int)(l >>> 32) | 0x1);
  }
  
  protected static ByteQuadsCanonicalizer createRoot(int paramInt)
  {
    return new ByteQuadsCanonicalizer(64, true, paramInt, true);
  }
  
  private void mergeChild(TableInfo paramTableInfo)
  {
    int i = paramTableInfo.count;
    TableInfo localTableInfo = (TableInfo)this._tableInfo.get();
    if (i == localTableInfo.count) {
      return;
    }
    if (i > 6000) {
      paramTableInfo = TableInfo.createInitial(64);
    }
    this._tableInfo.compareAndSet(localTableInfo, paramTableInfo);
  }
  
  private void nukeSymbols(boolean paramBoolean)
  {
    this._count = 0;
    this._spilloverEnd = _spilloverStart();
    this._longNameOffset = (this._hashSize << 3);
    if (paramBoolean)
    {
      Arrays.fill(this._hashArea, 0);
      Arrays.fill(this._names, null);
    }
  }
  
  private void rehash()
  {
    this._needRehash = false;
    this._hashShared = false;
    int[] arrayOfInt = this._hashArea;
    String[] arrayOfString = this._names;
    int i = this._hashSize;
    int k = this._count;
    int j = i + i;
    int m = this._spilloverEnd;
    if (j > 65536) {
      nukeSymbols(true);
    }
    do
    {
      return;
      this._hashArea = new int[arrayOfInt.length + (i << 3)];
      this._hashSize = j;
      this._secondaryStart = (j << 2);
      this._tertiaryStart = (this._secondaryStart + (this._secondaryStart >> 1));
      this._tertiaryShift = _calcTertiaryShift(j);
      this._names = new String[arrayOfString.length << 1];
      nukeSymbols(false);
      j = 0;
      Object localObject1 = new int[16];
      i = 0;
      if (i < m)
      {
        int n = arrayOfInt[(i + 3)];
        if (n == 0) {}
        for (;;)
        {
          i += 4;
          break;
          j += 1;
          String str = arrayOfString[(i >> 2)];
          switch (n)
          {
          default: 
            Object localObject2 = localObject1;
            if (n > localObject1.length) {
              localObject2 = new int[n];
            }
            System.arraycopy(arrayOfInt, arrayOfInt[(i + 1)], localObject2, 0, n);
            addName(str, (int[])localObject2, n);
            localObject1 = localObject2;
            break;
          case 1: 
            localObject1[0] = arrayOfInt[i];
            addName(str, (int[])localObject1, 1);
            break;
          case 2: 
            localObject1[0] = arrayOfInt[i];
            localObject1[1] = arrayOfInt[(i + 1)];
            addName(str, (int[])localObject1, 2);
            break;
          case 3: 
            localObject1[0] = arrayOfInt[i];
            localObject1[1] = arrayOfInt[(i + 1)];
            localObject1[2] = arrayOfInt[(i + 2)];
            addName(str, (int[])localObject1, 3);
          }
        }
      }
    } while (j == k);
    throw new IllegalStateException("Failed rehash(): old count=" + k + ", copyCount=" + j);
  }
  
  protected void _reportTooManyCollisions()
  {
    if (this._hashSize <= 1024) {
      return;
    }
    throw new IllegalStateException("Spill-over slots in symbol table with " + this._count + " entries, hash area of " + this._hashSize + " slots is now full (all " + (this._hashSize >> 3) + " slots -- suspect a DoS attack based on hash collisions." + " You can disable the check via `JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW`");
  }
  
  public String addName(String paramString, int paramInt)
  {
    _verifySharing();
    String str = paramString;
    if (this._intern) {
      str = InternCache.instance.intern(paramString);
    }
    int i = _findOffsetForAdd(calcHash(paramInt));
    this._hashArea[i] = paramInt;
    this._hashArea[(i + 3)] = 1;
    this._names[(i >> 2)] = str;
    this._count += 1;
    _verifyNeedForRehash();
    return str;
  }
  
  public String addName(String paramString, int paramInt1, int paramInt2)
  {
    _verifySharing();
    String str = paramString;
    if (this._intern) {
      str = InternCache.instance.intern(paramString);
    }
    if (paramInt2 == 0) {}
    for (int i = calcHash(paramInt1);; i = calcHash(paramInt1, paramInt2))
    {
      i = _findOffsetForAdd(i);
      this._hashArea[i] = paramInt1;
      this._hashArea[(i + 1)] = paramInt2;
      this._hashArea[(i + 3)] = 2;
      this._names[(i >> 2)] = str;
      this._count += 1;
      _verifyNeedForRehash();
      return str;
    }
  }
  
  public String addName(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    _verifySharing();
    String str = paramString;
    if (this._intern) {
      str = InternCache.instance.intern(paramString);
    }
    int i = _findOffsetForAdd(calcHash(paramInt1, paramInt2, paramInt3));
    this._hashArea[i] = paramInt1;
    this._hashArea[(i + 1)] = paramInt2;
    this._hashArea[(i + 2)] = paramInt3;
    this._hashArea[(i + 3)] = 3;
    this._names[(i >> 2)] = str;
    this._count += 1;
    _verifyNeedForRehash();
    return str;
  }
  
  public String addName(String paramString, int[] paramArrayOfInt, int paramInt)
  {
    _verifySharing();
    String str = paramString;
    if (this._intern) {
      str = InternCache.instance.intern(paramString);
    }
    switch (paramInt)
    {
    default: 
      int j = calcHash(paramArrayOfInt, paramInt);
      int i = _findOffsetForAdd(j);
      this._hashArea[i] = j;
      j = _appendLongName(paramArrayOfInt, paramInt);
      this._hashArea[(i + 1)] = j;
      this._hashArea[(i + 3)] = paramInt;
      paramInt = i;
    }
    for (;;)
    {
      this._names[(paramInt >> 2)] = str;
      this._count += 1;
      _verifyNeedForRehash();
      return str;
      paramInt = _findOffsetForAdd(calcHash(paramArrayOfInt[0]));
      this._hashArea[paramInt] = paramArrayOfInt[0];
      this._hashArea[(paramInt + 3)] = 1;
      continue;
      paramInt = _findOffsetForAdd(calcHash(paramArrayOfInt[0], paramArrayOfInt[1]));
      this._hashArea[paramInt] = paramArrayOfInt[0];
      this._hashArea[(paramInt + 1)] = paramArrayOfInt[1];
      this._hashArea[(paramInt + 3)] = 2;
      continue;
      paramInt = _findOffsetForAdd(calcHash(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]));
      this._hashArea[paramInt] = paramArrayOfInt[0];
      this._hashArea[(paramInt + 1)] = paramArrayOfInt[1];
      this._hashArea[(paramInt + 2)] = paramArrayOfInt[2];
      this._hashArea[(paramInt + 3)] = 3;
    }
  }
  
  public int bucketCount()
  {
    return this._hashSize;
  }
  
  public int calcHash(int paramInt)
  {
    paramInt ^= this._seed;
    paramInt += (paramInt >>> 16);
    paramInt ^= paramInt << 3;
    return paramInt + (paramInt >>> 12);
  }
  
  public int calcHash(int paramInt1, int paramInt2)
  {
    paramInt1 += (paramInt1 >>> 15);
    paramInt1 = (paramInt1 ^ paramInt1 >>> 9) + paramInt2 * 33 ^ this._seed;
    paramInt1 += (paramInt1 >>> 16);
    paramInt1 ^= paramInt1 >>> 4;
    return paramInt1 + (paramInt1 << 3);
  }
  
  public int calcHash(int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt1 ^= this._seed;
    paramInt1 = ((paramInt1 + (paramInt1 >>> 9)) * 31 + paramInt2) * 33;
    paramInt1 = paramInt1 + (paramInt1 >>> 15) ^ paramInt3;
    paramInt1 += (paramInt1 >>> 4);
    paramInt1 += (paramInt1 >>> 15);
    return paramInt1 ^ paramInt1 << 9;
  }
  
  public int calcHash(int[] paramArrayOfInt, int paramInt)
  {
    if (paramInt < 4) {
      throw new IllegalArgumentException();
    }
    int i = paramArrayOfInt[0] ^ this._seed;
    i = i + (i >>> 9) + paramArrayOfInt[1];
    i = (i + (i >>> 15)) * 33 ^ paramArrayOfInt[2];
    int j = i + (i >>> 4);
    i = 3;
    while (i < paramInt)
    {
      int k = paramArrayOfInt[i];
      j += (k ^ k >> 21);
      i += 1;
    }
    paramInt = j * 65599;
    paramInt += (paramInt >>> 19);
    return paramInt ^ paramInt << 5;
  }
  
  public String findName(int paramInt)
  {
    Object localObject2 = null;
    int i = _calcOffset(calcHash(paramInt));
    int[] arrayOfInt = this._hashArea;
    int j = arrayOfInt[(i + 3)];
    Object localObject1;
    if (j == 1)
    {
      if (arrayOfInt[i] != paramInt) {
        break label60;
      }
      localObject1 = this._names[(i >> 2)];
    }
    label60:
    int k;
    do
    {
      do
      {
        return (String)localObject1;
        localObject1 = localObject2;
      } while (j == 0);
      j = this._secondaryStart + (i >> 3 << 2);
      k = arrayOfInt[(j + 3)];
      if (k == 1)
      {
        if (arrayOfInt[j] != paramInt) {
          break;
        }
        return this._names[(j >> 2)];
      }
      localObject1 = localObject2;
    } while (k == 0);
    return _findSecondary(i, paramInt);
  }
  
  public String findName(int paramInt1, int paramInt2)
  {
    Object localObject2 = null;
    int i = _calcOffset(calcHash(paramInt1, paramInt2));
    int[] arrayOfInt = this._hashArea;
    int j = arrayOfInt[(i + 3)];
    Object localObject1;
    if (j == 2)
    {
      if ((paramInt1 != arrayOfInt[i]) || (paramInt2 != arrayOfInt[(i + 1)])) {
        break label74;
      }
      localObject1 = this._names[(i >> 2)];
    }
    label74:
    int k;
    do
    {
      do
      {
        return (String)localObject1;
        localObject1 = localObject2;
      } while (j == 0);
      j = this._secondaryStart + (i >> 3 << 2);
      k = arrayOfInt[(j + 3)];
      if (k == 2)
      {
        if ((paramInt1 != arrayOfInt[j]) || (paramInt2 != arrayOfInt[(j + 1)])) {
          break;
        }
        return this._names[(j >> 2)];
      }
      localObject1 = localObject2;
    } while (k == 0);
    return _findSecondary(i, paramInt1, paramInt2);
  }
  
  public String findName(int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject2 = null;
    int i = _calcOffset(calcHash(paramInt1, paramInt2, paramInt3));
    int[] arrayOfInt = this._hashArea;
    int j = arrayOfInt[(i + 3)];
    Object localObject1;
    if (j == 3)
    {
      if ((paramInt1 != arrayOfInt[i]) || (arrayOfInt[(i + 1)] != paramInt2) || (arrayOfInt[(i + 2)] != paramInt3)) {
        break label91;
      }
      localObject1 = this._names[(i >> 2)];
    }
    label91:
    int k;
    do
    {
      do
      {
        return (String)localObject1;
        localObject1 = localObject2;
      } while (j == 0);
      j = this._secondaryStart + (i >> 3 << 2);
      k = arrayOfInt[(j + 3)];
      if (k == 3)
      {
        if ((paramInt1 != arrayOfInt[j]) || (arrayOfInt[(j + 1)] != paramInt2) || (arrayOfInt[(j + 2)] != paramInt3)) {
          break;
        }
        return this._names[(j >> 2)];
      }
      localObject1 = localObject2;
    } while (k == 0);
    return _findSecondary(i, paramInt1, paramInt2, paramInt3);
  }
  
  public String findName(int[] paramArrayOfInt, int paramInt)
  {
    Object localObject2 = null;
    Object localObject1;
    if (paramInt < 4) {
      if (paramInt == 3) {
        localObject1 = findName(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
      }
    }
    int i;
    int j;
    int k;
    do
    {
      int[] arrayOfInt;
      do
      {
        return (String)localObject1;
        if (paramInt == 2) {
          return findName(paramArrayOfInt[0], paramArrayOfInt[1]);
        }
        return findName(paramArrayOfInt[0]);
        i = calcHash(paramArrayOfInt, paramInt);
        j = _calcOffset(i);
        arrayOfInt = this._hashArea;
        k = arrayOfInt[(j + 3)];
        if ((i == arrayOfInt[j]) && (k == paramInt) && (_verifyLongName(paramArrayOfInt, paramInt, arrayOfInt[(j + 1)]))) {
          return this._names[(j >> 2)];
        }
        localObject1 = localObject2;
      } while (k == 0);
      int m = this._secondaryStart + (j >> 3 << 2);
      int n = arrayOfInt[(m + 3)];
      if ((i == arrayOfInt[m]) && (n == paramInt) && (_verifyLongName(paramArrayOfInt, paramInt, arrayOfInt[(m + 1)]))) {
        return this._names[(m >> 2)];
      }
      localObject1 = localObject2;
    } while (k == 0);
    return _findSecondary(j, i, paramArrayOfInt, paramInt);
  }
  
  public int hashSeed()
  {
    return this._seed;
  }
  
  public ByteQuadsCanonicalizer makeChild(int paramInt)
  {
    return new ByteQuadsCanonicalizer(this, JsonFactory.Feature.INTERN_FIELD_NAMES.enabledIn(paramInt), this._seed, JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(paramInt), (TableInfo)this._tableInfo.get());
  }
  
  public boolean maybeDirty()
  {
    return !this._hashShared;
  }
  
  public int primaryCount()
  {
    int j = 0;
    int i = 3;
    int m = this._secondaryStart;
    while (i < m)
    {
      int k = j;
      if (this._hashArea[i] != 0) {
        k = j + 1;
      }
      i += 4;
      j = k;
    }
    return j;
  }
  
  public void release()
  {
    if ((this._parent != null) && (maybeDirty()))
    {
      this._parent.mergeChild(new TableInfo(this));
      this._hashShared = true;
    }
  }
  
  public int secondaryCount()
  {
    int j = 0;
    int i = this._secondaryStart + 3;
    int m = this._tertiaryStart;
    while (i < m)
    {
      int k = j;
      if (this._hashArea[i] != 0) {
        k = j + 1;
      }
      i += 4;
      j = k;
    }
    return j;
  }
  
  public int size()
  {
    if (this._tableInfo != null) {
      return ((TableInfo)this._tableInfo.get()).count;
    }
    return this._count;
  }
  
  public int spilloverCount()
  {
    return this._spilloverEnd - _spilloverStart() >> 2;
  }
  
  public int tertiaryCount()
  {
    int j = 0;
    int k = this._tertiaryStart + 3;
    int n = this._hashSize;
    int m;
    for (int i = k;; i = m)
    {
      m = i;
      if (m >= k + n) {
        break;
      }
      i = j;
      if (this._hashArea[m] != 0) {
        i = j + 1;
      }
      m += 4;
      j = i;
    }
    return j;
  }
  
  public String toString()
  {
    int i = primaryCount();
    int j = secondaryCount();
    int k = tertiaryCount();
    int m = spilloverCount();
    int n = totalCount();
    return String.format("[%s: size=%d, hashSize=%d, %d/%d/%d/%d pri/sec/ter/spill (=%s), total:%d]", new Object[] { getClass().getName(), Integer.valueOf(this._count), Integer.valueOf(this._hashSize), Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(m), Integer.valueOf(n), Integer.valueOf(i + j + k + m), Integer.valueOf(n) });
  }
  
  public int totalCount()
  {
    int j = 0;
    int i = 3;
    int m = this._hashSize;
    while (i < m << 3)
    {
      int k = j;
      if (this._hashArea[i] != 0) {
        k = j + 1;
      }
      i += 4;
      j = k;
    }
    return j;
  }
  
  private static final class TableInfo
  {
    public final int count;
    public final int longNameOffset;
    public final int[] mainHash;
    public final String[] names;
    public final int size;
    public final int spilloverEnd;
    public final int tertiaryShift;
    
    public TableInfo(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, String[] paramArrayOfString, int paramInt4, int paramInt5)
    {
      this.size = paramInt1;
      this.count = paramInt2;
      this.tertiaryShift = paramInt3;
      this.mainHash = paramArrayOfInt;
      this.names = paramArrayOfString;
      this.spilloverEnd = paramInt4;
      this.longNameOffset = paramInt5;
    }
    
    public TableInfo(ByteQuadsCanonicalizer paramByteQuadsCanonicalizer)
    {
      this.size = paramByteQuadsCanonicalizer._hashSize;
      this.count = paramByteQuadsCanonicalizer._count;
      this.tertiaryShift = paramByteQuadsCanonicalizer._tertiaryShift;
      this.mainHash = paramByteQuadsCanonicalizer._hashArea;
      this.names = paramByteQuadsCanonicalizer._names;
      this.spilloverEnd = paramByteQuadsCanonicalizer._spilloverEnd;
      this.longNameOffset = paramByteQuadsCanonicalizer._longNameOffset;
    }
    
    public static TableInfo createInitial(int paramInt)
    {
      int i = paramInt << 3;
      return new TableInfo(paramInt, 0, ByteQuadsCanonicalizer._calcTertiaryShift(paramInt), new int[i], new String[paramInt << 1], i - paramInt, i);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/sym/ByteQuadsCanonicalizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */