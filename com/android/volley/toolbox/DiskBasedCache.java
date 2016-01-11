package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.VolleyLog;
import java.io.EOFException;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DiskBasedCache
  implements Cache
{
  private static final int CACHE_MAGIC = 538247942;
  private static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
  private static final float HYSTERESIS_FACTOR = 0.9F;
  private final Map<String, CacheHeader> mEntries = new LinkedHashMap(16, 0.75F, true);
  private final int mMaxCacheSizeInBytes;
  private final File mRootDirectory;
  private long mTotalSize = 0L;
  
  public DiskBasedCache(File paramFile)
  {
    this(paramFile, 5242880);
  }
  
  public DiskBasedCache(File paramFile, int paramInt)
  {
    this.mRootDirectory = paramFile;
    this.mMaxCacheSizeInBytes = paramInt;
  }
  
  private String getFilenameForKey(String paramString)
  {
    int i = paramString.length() / 2;
    int j = paramString.substring(0, i).hashCode();
    return String.valueOf(j) + String.valueOf(paramString.substring(i).hashCode());
  }
  
  private void pruneIfNeeded(int paramInt)
  {
    if (this.mTotalSize + paramInt < this.mMaxCacheSizeInBytes) {
      return;
    }
    if (VolleyLog.DEBUG) {
      VolleyLog.v("Pruning old cache entries.", new Object[0]);
    }
    long l1 = this.mTotalSize;
    int i = 0;
    long l2 = SystemClock.elapsedRealtime();
    Iterator localIterator = this.mEntries.entrySet().iterator();
    label61:
    int j = i;
    CacheHeader localCacheHeader;
    if (localIterator.hasNext())
    {
      localCacheHeader = (CacheHeader)((Map.Entry)localIterator.next()).getValue();
      if (!getFileForKey(localCacheHeader.key).delete()) {
        break label203;
      }
      this.mTotalSize -= localCacheHeader.size;
    }
    for (;;)
    {
      localIterator.remove();
      j = i + 1;
      i = j;
      if ((float)(this.mTotalSize + paramInt) >= this.mMaxCacheSizeInBytes * 0.9F) {
        break label61;
      }
      if (!VolleyLog.DEBUG) {
        break;
      }
      VolleyLog.v("pruned %d files, %d bytes, %d ms", new Object[] { Integer.valueOf(j), Long.valueOf(this.mTotalSize - l1), Long.valueOf(SystemClock.elapsedRealtime() - l2) });
      return;
      label203:
      VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", new Object[] { localCacheHeader.key, getFilenameForKey(localCacheHeader.key) });
    }
  }
  
  private void putEntry(String paramString, CacheHeader paramCacheHeader)
  {
    if (!this.mEntries.containsKey(paramString)) {}
    CacheHeader localCacheHeader;
    for (this.mTotalSize += paramCacheHeader.size;; this.mTotalSize += paramCacheHeader.size - localCacheHeader.size)
    {
      this.mEntries.put(paramString, paramCacheHeader);
      return;
      localCacheHeader = (CacheHeader)this.mEntries.get(paramString);
    }
  }
  
  private static int read(InputStream paramInputStream)
    throws IOException
  {
    int i = paramInputStream.read();
    if (i == -1) {
      throw new EOFException();
    }
    return i;
  }
  
  static int readInt(InputStream paramInputStream)
    throws IOException
  {
    return 0x0 | read(paramInputStream) << 0 | read(paramInputStream) << 8 | read(paramInputStream) << 16 | read(paramInputStream) << 24;
  }
  
  static long readLong(InputStream paramInputStream)
    throws IOException
  {
    return 0L | (read(paramInputStream) & 0xFF) << 0 | (read(paramInputStream) & 0xFF) << 8 | (read(paramInputStream) & 0xFF) << 16 | (read(paramInputStream) & 0xFF) << 24 | (read(paramInputStream) & 0xFF) << 32 | (read(paramInputStream) & 0xFF) << 40 | (read(paramInputStream) & 0xFF) << 48 | (read(paramInputStream) & 0xFF) << 56;
  }
  
  static String readString(InputStream paramInputStream)
    throws IOException
  {
    return new String(streamToBytes(paramInputStream, (int)readLong(paramInputStream)), "UTF-8");
  }
  
  static Map<String, String> readStringStringMap(InputStream paramInputStream)
    throws IOException
  {
    int j = readInt(paramInputStream);
    if (j == 0) {}
    for (Object localObject = Collections.emptyMap();; localObject = new HashMap(j))
    {
      int i = 0;
      while (i < j)
      {
        ((Map)localObject).put(readString(paramInputStream).intern(), readString(paramInputStream).intern());
        i += 1;
      }
    }
    return (Map<String, String>)localObject;
  }
  
  private void removeEntry(String paramString)
  {
    CacheHeader localCacheHeader = (CacheHeader)this.mEntries.get(paramString);
    if (localCacheHeader != null)
    {
      this.mTotalSize -= localCacheHeader.size;
      this.mEntries.remove(paramString);
    }
  }
  
  private static byte[] streamToBytes(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    while (i < paramInt)
    {
      int j = paramInputStream.read(arrayOfByte, i, paramInt - i);
      if (j == -1) {
        break;
      }
      i += j;
    }
    if (i != paramInt) {
      throw new IOException("Expected " + paramInt + " bytes, read " + i + " bytes");
    }
    return arrayOfByte;
  }
  
  static void writeInt(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    paramOutputStream.write(paramInt >> 0 & 0xFF);
    paramOutputStream.write(paramInt >> 8 & 0xFF);
    paramOutputStream.write(paramInt >> 16 & 0xFF);
    paramOutputStream.write(paramInt >> 24 & 0xFF);
  }
  
  static void writeLong(OutputStream paramOutputStream, long paramLong)
    throws IOException
  {
    paramOutputStream.write((byte)(int)(paramLong >>> 0));
    paramOutputStream.write((byte)(int)(paramLong >>> 8));
    paramOutputStream.write((byte)(int)(paramLong >>> 16));
    paramOutputStream.write((byte)(int)(paramLong >>> 24));
    paramOutputStream.write((byte)(int)(paramLong >>> 32));
    paramOutputStream.write((byte)(int)(paramLong >>> 40));
    paramOutputStream.write((byte)(int)(paramLong >>> 48));
    paramOutputStream.write((byte)(int)(paramLong >>> 56));
  }
  
  static void writeString(OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    paramString = paramString.getBytes("UTF-8");
    writeLong(paramOutputStream, paramString.length);
    paramOutputStream.write(paramString, 0, paramString.length);
  }
  
  static void writeStringStringMap(Map<String, String> paramMap, OutputStream paramOutputStream)
    throws IOException
  {
    if (paramMap != null)
    {
      writeInt(paramOutputStream, paramMap.size());
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        writeString(paramOutputStream, (String)localEntry.getKey());
        writeString(paramOutputStream, (String)localEntry.getValue());
      }
    }
    writeInt(paramOutputStream, 0);
  }
  
  public void clear()
  {
    try
    {
      File[] arrayOfFile = this.mRootDirectory.listFiles();
      if (arrayOfFile != null)
      {
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          arrayOfFile[i].delete();
          i += 1;
        }
      }
      this.mEntries.clear();
      this.mTotalSize = 0L;
      VolleyLog.d("Cache cleared.", new Object[0]);
      return;
    }
    finally {}
  }
  
  /* Error */
  public Cache.Entry get(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: getfield 47	com/android/volley/toolbox/DiskBasedCache:mEntries	Ljava/util/Map;
    //   9: aload_1
    //   10: invokeinterface 184 2 0
    //   15: checkcast 10	com/android/volley/toolbox/DiskBasedCache$CacheHeader
    //   18: astore 4
    //   20: aload 4
    //   22: ifnonnull +10 -> 32
    //   25: aload 5
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: areturn
    //   32: aload_0
    //   33: aload_1
    //   34: invokevirtual 140	com/android/volley/toolbox/DiskBasedCache:getFileForKey	(Ljava/lang/String;)Ljava/io/File;
    //   37: astore 8
    //   39: aconst_null
    //   40: astore 7
    //   42: aconst_null
    //   43: astore_2
    //   44: aconst_null
    //   45: astore 6
    //   47: new 13	com/android/volley/toolbox/DiskBasedCache$CountingInputStream
    //   50: dup
    //   51: new 301	java/io/BufferedInputStream
    //   54: dup
    //   55: new 303	java/io/FileInputStream
    //   58: dup
    //   59: aload 8
    //   61: invokespecial 305	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   64: invokespecial 308	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   67: aconst_null
    //   68: invokespecial 311	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:<init>	(Ljava/io/InputStream;Lcom/android/volley/toolbox/DiskBasedCache$1;)V
    //   71: astore_3
    //   72: aload_3
    //   73: invokestatic 315	com/android/volley/toolbox/DiskBasedCache$CacheHeader:readHeader	(Ljava/io/InputStream;)Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
    //   76: pop
    //   77: aload 4
    //   79: aload_3
    //   80: aload 8
    //   82: invokevirtual 317	java/io/File:length	()J
    //   85: aload_3
    //   86: invokestatic 321	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:access$100	(Lcom/android/volley/toolbox/DiskBasedCache$CountingInputStream;)I
    //   89: i2l
    //   90: lsub
    //   91: l2i
    //   92: invokestatic 211	com/android/volley/toolbox/DiskBasedCache:streamToBytes	(Ljava/io/InputStream;I)[B
    //   95: invokevirtual 325	com/android/volley/toolbox/DiskBasedCache$CacheHeader:toCacheEntry	([B)Lcom/android/volley/Cache$Entry;
    //   98: astore_2
    //   99: aload_3
    //   100: ifnull +7 -> 107
    //   103: aload_3
    //   104: invokevirtual 328	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:close	()V
    //   107: aload_2
    //   108: astore_1
    //   109: goto -81 -> 28
    //   112: astore_1
    //   113: aload 5
    //   115: astore_1
    //   116: goto -88 -> 28
    //   119: astore 4
    //   121: aload 6
    //   123: astore_3
    //   124: aload_3
    //   125: astore_2
    //   126: ldc_w 330
    //   129: iconst_2
    //   130: anewarray 4	java/lang/Object
    //   133: dup
    //   134: iconst_0
    //   135: aload 8
    //   137: invokevirtual 333	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   140: aastore
    //   141: dup
    //   142: iconst_1
    //   143: aload 4
    //   145: invokevirtual 334	java/io/IOException:toString	()Ljava/lang/String;
    //   148: aastore
    //   149: invokestatic 170	com/android/volley/VolleyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   152: aload_3
    //   153: astore_2
    //   154: aload_0
    //   155: aload_1
    //   156: invokevirtual 336	com/android/volley/toolbox/DiskBasedCache:remove	(Ljava/lang/String;)V
    //   159: aload 5
    //   161: astore_1
    //   162: aload_3
    //   163: ifnull -135 -> 28
    //   166: aload_3
    //   167: invokevirtual 328	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:close	()V
    //   170: aload 5
    //   172: astore_1
    //   173: goto -145 -> 28
    //   176: astore_1
    //   177: aload 5
    //   179: astore_1
    //   180: goto -152 -> 28
    //   183: astore 4
    //   185: aload 7
    //   187: astore_3
    //   188: aload_3
    //   189: astore_2
    //   190: ldc_w 330
    //   193: iconst_2
    //   194: anewarray 4	java/lang/Object
    //   197: dup
    //   198: iconst_0
    //   199: aload 8
    //   201: invokevirtual 333	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   204: aastore
    //   205: dup
    //   206: iconst_1
    //   207: aload 4
    //   209: invokevirtual 337	java/lang/NegativeArraySizeException:toString	()Ljava/lang/String;
    //   212: aastore
    //   213: invokestatic 170	com/android/volley/VolleyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   216: aload_3
    //   217: astore_2
    //   218: aload_0
    //   219: aload_1
    //   220: invokevirtual 336	com/android/volley/toolbox/DiskBasedCache:remove	(Ljava/lang/String;)V
    //   223: aload 5
    //   225: astore_1
    //   226: aload_3
    //   227: ifnull -199 -> 28
    //   230: aload_3
    //   231: invokevirtual 328	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:close	()V
    //   234: aload 5
    //   236: astore_1
    //   237: goto -209 -> 28
    //   240: astore_1
    //   241: aload 5
    //   243: astore_1
    //   244: goto -216 -> 28
    //   247: astore_1
    //   248: aload_2
    //   249: ifnull +7 -> 256
    //   252: aload_2
    //   253: invokevirtual 328	com/android/volley/toolbox/DiskBasedCache$CountingInputStream:close	()V
    //   256: aload_1
    //   257: athrow
    //   258: astore_1
    //   259: aload_0
    //   260: monitorexit
    //   261: aload_1
    //   262: athrow
    //   263: astore_1
    //   264: aload 5
    //   266: astore_1
    //   267: goto -239 -> 28
    //   270: astore_1
    //   271: aload_3
    //   272: astore_2
    //   273: goto -25 -> 248
    //   276: astore 4
    //   278: goto -90 -> 188
    //   281: astore 4
    //   283: goto -159 -> 124
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	286	0	this	DiskBasedCache
    //   0	286	1	paramString	String
    //   43	230	2	localObject1	Object
    //   71	201	3	localObject2	Object
    //   18	60	4	localCacheHeader	CacheHeader
    //   119	25	4	localIOException1	IOException
    //   183	25	4	localNegativeArraySizeException1	NegativeArraySizeException
    //   276	1	4	localNegativeArraySizeException2	NegativeArraySizeException
    //   281	1	4	localIOException2	IOException
    //   1	264	5	localObject3	Object
    //   45	77	6	localObject4	Object
    //   40	146	7	localObject5	Object
    //   37	163	8	localFile	File
    // Exception table:
    //   from	to	target	type
    //   103	107	112	java/io/IOException
    //   47	72	119	java/io/IOException
    //   166	170	176	java/io/IOException
    //   47	72	183	java/lang/NegativeArraySizeException
    //   230	234	240	java/io/IOException
    //   47	72	247	finally
    //   126	152	247	finally
    //   154	159	247	finally
    //   190	216	247	finally
    //   218	223	247	finally
    //   5	20	258	finally
    //   32	39	258	finally
    //   103	107	258	finally
    //   166	170	258	finally
    //   230	234	258	finally
    //   252	256	258	finally
    //   256	258	258	finally
    //   252	256	263	java/io/IOException
    //   72	99	270	finally
    //   72	99	276	java/lang/NegativeArraySizeException
    //   72	99	281	java/io/IOException
  }
  
  public File getFileForKey(String paramString)
  {
    return new File(this.mRootDirectory, getFilenameForKey(paramString));
  }
  
  /* Error */
  public void initialize()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 51	com/android/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   6: invokevirtual 344	java/io/File:exists	()Z
    //   9: ifne +36 -> 45
    //   12: aload_0
    //   13: getfield 51	com/android/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   16: invokevirtual 347	java/io/File:mkdirs	()Z
    //   19: ifne +23 -> 42
    //   22: ldc_w 349
    //   25: iconst_1
    //   26: anewarray 4	java/lang/Object
    //   29: dup
    //   30: iconst_0
    //   31: aload_0
    //   32: getfield 51	com/android/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   35: invokevirtual 333	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   38: aastore
    //   39: invokestatic 352	com/android/volley/VolleyLog:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: aload_0
    //   46: getfield 51	com/android/volley/toolbox/DiskBasedCache:mRootDirectory	Ljava/io/File;
    //   49: invokevirtual 292	java/io/File:listFiles	()[Ljava/io/File;
    //   52: astore 6
    //   54: aload 6
    //   56: ifnull -14 -> 42
    //   59: aload 6
    //   61: arraylength
    //   62: istore_2
    //   63: iconst_0
    //   64: istore_1
    //   65: iload_1
    //   66: iload_2
    //   67: if_icmpge -25 -> 42
    //   70: aload 6
    //   72: iload_1
    //   73: aaload
    //   74: astore 7
    //   76: aconst_null
    //   77: astore_3
    //   78: aconst_null
    //   79: astore 5
    //   81: new 301	java/io/BufferedInputStream
    //   84: dup
    //   85: new 303	java/io/FileInputStream
    //   88: dup
    //   89: aload 7
    //   91: invokespecial 305	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   94: invokespecial 308	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   97: astore 4
    //   99: aload 4
    //   101: invokestatic 315	com/android/volley/toolbox/DiskBasedCache$CacheHeader:readHeader	(Ljava/io/InputStream;)Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
    //   104: astore_3
    //   105: aload_3
    //   106: aload 7
    //   108: invokevirtual 317	java/io/File:length	()J
    //   111: putfield 148	com/android/volley/toolbox/DiskBasedCache$CacheHeader:size	J
    //   114: aload_0
    //   115: aload_3
    //   116: getfield 136	com/android/volley/toolbox/DiskBasedCache$CacheHeader:key	Ljava/lang/String;
    //   119: aload_3
    //   120: invokespecial 354	com/android/volley/toolbox/DiskBasedCache:putEntry	(Ljava/lang/String;Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;)V
    //   123: aload 4
    //   125: ifnull +8 -> 133
    //   128: aload 4
    //   130: invokevirtual 355	java/io/BufferedInputStream:close	()V
    //   133: iload_1
    //   134: iconst_1
    //   135: iadd
    //   136: istore_1
    //   137: goto -72 -> 65
    //   140: astore_3
    //   141: goto -8 -> 133
    //   144: astore_3
    //   145: aload 5
    //   147: astore 4
    //   149: aload 7
    //   151: ifnull +12 -> 163
    //   154: aload 4
    //   156: astore_3
    //   157: aload 7
    //   159: invokevirtual 145	java/io/File:delete	()Z
    //   162: pop
    //   163: aload 4
    //   165: ifnull -32 -> 133
    //   168: aload 4
    //   170: invokevirtual 355	java/io/BufferedInputStream:close	()V
    //   173: goto -40 -> 133
    //   176: astore_3
    //   177: goto -44 -> 133
    //   180: astore 4
    //   182: aload_3
    //   183: ifnull +7 -> 190
    //   186: aload_3
    //   187: invokevirtual 355	java/io/BufferedInputStream:close	()V
    //   190: aload 4
    //   192: athrow
    //   193: astore_3
    //   194: aload_0
    //   195: monitorexit
    //   196: aload_3
    //   197: athrow
    //   198: astore_3
    //   199: goto -9 -> 190
    //   202: astore 5
    //   204: aload 4
    //   206: astore_3
    //   207: aload 5
    //   209: astore 4
    //   211: goto -29 -> 182
    //   214: astore_3
    //   215: goto -66 -> 149
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	218	0	this	DiskBasedCache
    //   64	73	1	i	int
    //   62	6	2	j	int
    //   77	43	3	localCacheHeader	CacheHeader
    //   140	1	3	localIOException1	IOException
    //   144	1	3	localIOException2	IOException
    //   156	1	3	localObject1	Object
    //   176	11	3	localIOException3	IOException
    //   193	4	3	localObject2	Object
    //   198	1	3	localIOException4	IOException
    //   206	1	3	localObject3	Object
    //   214	1	3	localIOException5	IOException
    //   97	72	4	localObject4	Object
    //   180	25	4	localObject5	Object
    //   209	1	4	localObject6	Object
    //   79	67	5	localObject7	Object
    //   202	6	5	localObject8	Object
    //   52	19	6	arrayOfFile	File[]
    //   74	84	7	localFile	File
    // Exception table:
    //   from	to	target	type
    //   128	133	140	java/io/IOException
    //   81	99	144	java/io/IOException
    //   168	173	176	java/io/IOException
    //   81	99	180	finally
    //   157	163	180	finally
    //   2	42	193	finally
    //   45	54	193	finally
    //   59	63	193	finally
    //   128	133	193	finally
    //   168	173	193	finally
    //   186	190	193	finally
    //   190	193	193	finally
    //   186	190	198	java/io/IOException
    //   99	123	202	finally
    //   99	123	214	java/io/IOException
  }
  
  public void invalidate(String paramString, boolean paramBoolean)
  {
    try
    {
      Cache.Entry localEntry = get(paramString);
      if (localEntry != null)
      {
        localEntry.softTtl = 0L;
        if (paramBoolean) {
          localEntry.ttl = 0L;
        }
        put(paramString, localEntry);
      }
      return;
    }
    finally {}
  }
  
  /* Error */
  public void put(String paramString, Cache.Entry paramEntry)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_2
    //   4: getfield 374	com/android/volley/Cache$Entry:data	[B
    //   7: arraylength
    //   8: invokespecial 376	com/android/volley/toolbox/DiskBasedCache:pruneIfNeeded	(I)V
    //   11: aload_0
    //   12: aload_1
    //   13: invokevirtual 140	com/android/volley/toolbox/DiskBasedCache:getFileForKey	(Ljava/lang/String;)Ljava/io/File;
    //   16: astore_3
    //   17: new 378	java/io/BufferedOutputStream
    //   20: dup
    //   21: new 380	java/io/FileOutputStream
    //   24: dup
    //   25: aload_3
    //   26: invokespecial 381	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   29: invokespecial 384	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   32: astore 4
    //   34: new 10	com/android/volley/toolbox/DiskBasedCache$CacheHeader
    //   37: dup
    //   38: aload_1
    //   39: aload_2
    //   40: invokespecial 386	com/android/volley/toolbox/DiskBasedCache$CacheHeader:<init>	(Ljava/lang/String;Lcom/android/volley/Cache$Entry;)V
    //   43: astore 5
    //   45: aload 5
    //   47: aload 4
    //   49: invokevirtual 390	com/android/volley/toolbox/DiskBasedCache$CacheHeader:writeHeader	(Ljava/io/OutputStream;)Z
    //   52: ifne +61 -> 113
    //   55: aload 4
    //   57: invokevirtual 391	java/io/BufferedOutputStream:close	()V
    //   60: ldc_w 393
    //   63: iconst_1
    //   64: anewarray 4	java/lang/Object
    //   67: dup
    //   68: iconst_0
    //   69: aload_3
    //   70: invokevirtual 333	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   73: aastore
    //   74: invokestatic 170	com/android/volley/VolleyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   77: new 188	java/io/IOException
    //   80: dup
    //   81: invokespecial 394	java/io/IOException:<init>	()V
    //   84: athrow
    //   85: astore_1
    //   86: aload_3
    //   87: invokevirtual 145	java/io/File:delete	()Z
    //   90: ifne +20 -> 110
    //   93: ldc_w 396
    //   96: iconst_1
    //   97: anewarray 4	java/lang/Object
    //   100: dup
    //   101: iconst_0
    //   102: aload_3
    //   103: invokevirtual 333	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   106: aastore
    //   107: invokestatic 170	com/android/volley/VolleyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   110: aload_0
    //   111: monitorexit
    //   112: return
    //   113: aload 4
    //   115: aload_2
    //   116: getfield 374	com/android/volley/Cache$Entry:data	[B
    //   119: invokevirtual 399	java/io/BufferedOutputStream:write	([B)V
    //   122: aload 4
    //   124: invokevirtual 391	java/io/BufferedOutputStream:close	()V
    //   127: aload_0
    //   128: aload_1
    //   129: aload 5
    //   131: invokespecial 354	com/android/volley/toolbox/DiskBasedCache:putEntry	(Ljava/lang/String;Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;)V
    //   134: goto -24 -> 110
    //   137: astore_1
    //   138: aload_0
    //   139: monitorexit
    //   140: aload_1
    //   141: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	142	0	this	DiskBasedCache
    //   0	142	1	paramString	String
    //   0	142	2	paramEntry	Cache.Entry
    //   16	87	3	localFile	File
    //   32	91	4	localBufferedOutputStream	java.io.BufferedOutputStream
    //   43	87	5	localCacheHeader	CacheHeader
    // Exception table:
    //   from	to	target	type
    //   17	85	85	java/io/IOException
    //   113	134	85	java/io/IOException
    //   2	17	137	finally
    //   17	85	137	finally
    //   86	110	137	finally
    //   113	134	137	finally
  }
  
  public void remove(String paramString)
  {
    try
    {
      boolean bool = getFileForKey(paramString).delete();
      removeEntry(paramString);
      if (!bool) {
        VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", new Object[] { paramString, getFilenameForKey(paramString) });
      }
      return;
    }
    finally {}
  }
  
  static class CacheHeader
  {
    public String etag;
    public String key;
    public long lastModified;
    public Map<String, String> responseHeaders;
    public long serverDate;
    public long size;
    public long softTtl;
    public long ttl;
    
    private CacheHeader() {}
    
    public CacheHeader(String paramString, Cache.Entry paramEntry)
    {
      this.key = paramString;
      this.size = paramEntry.data.length;
      this.etag = paramEntry.etag;
      this.serverDate = paramEntry.serverDate;
      this.lastModified = paramEntry.lastModified;
      this.ttl = paramEntry.ttl;
      this.softTtl = paramEntry.softTtl;
      this.responseHeaders = paramEntry.responseHeaders;
    }
    
    public static CacheHeader readHeader(InputStream paramInputStream)
      throws IOException
    {
      CacheHeader localCacheHeader = new CacheHeader();
      if (DiskBasedCache.readInt(paramInputStream) != 538247942) {
        throw new IOException();
      }
      localCacheHeader.key = DiskBasedCache.readString(paramInputStream);
      localCacheHeader.etag = DiskBasedCache.readString(paramInputStream);
      if (localCacheHeader.etag.equals("")) {
        localCacheHeader.etag = null;
      }
      localCacheHeader.serverDate = DiskBasedCache.readLong(paramInputStream);
      localCacheHeader.lastModified = DiskBasedCache.readLong(paramInputStream);
      localCacheHeader.ttl = DiskBasedCache.readLong(paramInputStream);
      localCacheHeader.softTtl = DiskBasedCache.readLong(paramInputStream);
      localCacheHeader.responseHeaders = DiskBasedCache.readStringStringMap(paramInputStream);
      return localCacheHeader;
    }
    
    public Cache.Entry toCacheEntry(byte[] paramArrayOfByte)
    {
      Cache.Entry localEntry = new Cache.Entry();
      localEntry.data = paramArrayOfByte;
      localEntry.etag = this.etag;
      localEntry.serverDate = this.serverDate;
      localEntry.lastModified = this.lastModified;
      localEntry.ttl = this.ttl;
      localEntry.softTtl = this.softTtl;
      localEntry.responseHeaders = this.responseHeaders;
      return localEntry;
    }
    
    public boolean writeHeader(OutputStream paramOutputStream)
    {
      try
      {
        DiskBasedCache.writeInt(paramOutputStream, 538247942);
        DiskBasedCache.writeString(paramOutputStream, this.key);
        if (this.etag == null) {}
        for (String str = "";; str = this.etag)
        {
          DiskBasedCache.writeString(paramOutputStream, str);
          DiskBasedCache.writeLong(paramOutputStream, this.serverDate);
          DiskBasedCache.writeLong(paramOutputStream, this.lastModified);
          DiskBasedCache.writeLong(paramOutputStream, this.ttl);
          DiskBasedCache.writeLong(paramOutputStream, this.softTtl);
          DiskBasedCache.writeStringStringMap(this.responseHeaders, paramOutputStream);
          paramOutputStream.flush();
          return true;
        }
        return false;
      }
      catch (IOException paramOutputStream)
      {
        VolleyLog.d("%s", new Object[] { paramOutputStream.toString() });
      }
    }
  }
  
  private static class CountingInputStream
    extends FilterInputStream
  {
    private int bytesRead = 0;
    
    private CountingInputStream(InputStream paramInputStream)
    {
      super();
    }
    
    public int read()
      throws IOException
    {
      int i = super.read();
      if (i != -1) {
        this.bytesRead += 1;
      }
      return i;
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      paramInt1 = super.read(paramArrayOfByte, paramInt1, paramInt2);
      if (paramInt1 != -1) {
        this.bytesRead += paramInt1;
      }
      return paramInt1;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/android/volley/toolbox/DiskBasedCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */