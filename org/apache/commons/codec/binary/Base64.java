package org.apache.commons.codec.binary;

import java.math.BigInteger;

public class Base64
  extends BaseNCodec
{
  private static final int BITS_PER_ENCODED_BYTE = 6;
  private static final int BYTES_PER_ENCODED_BLOCK = 4;
  private static final int BYTES_PER_UNENCODED_BLOCK = 3;
  static final byte[] CHUNK_SEPARATOR = { 13, 10 };
  private static final byte[] DECODE_TABLE = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };
  private static final int MASK_6BITS = 63;
  private static final byte[] STANDARD_ENCODE_TABLE = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  private static final byte[] URL_SAFE_ENCODE_TABLE = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
  private final int decodeSize;
  private final byte[] decodeTable;
  private final int encodeSize;
  private final byte[] encodeTable;
  private final byte[] lineSeparator;
  
  public Base64()
  {
    this(0);
  }
  
  public Base64(int paramInt)
  {
    this(paramInt, CHUNK_SEPARATOR);
  }
  
  public Base64(int paramInt, byte[] paramArrayOfByte)
  {
    this(paramInt, paramArrayOfByte, false);
  }
  
  public Base64(int paramInt, byte[] paramArrayOfByte, boolean paramBoolean) {}
  
  public Base64(boolean paramBoolean)
  {
    this(76, CHUNK_SEPARATOR, paramBoolean);
  }
  
  public static byte[] decodeBase64(String paramString)
  {
    return new Base64().decode(paramString);
  }
  
  public static byte[] decodeBase64(byte[] paramArrayOfByte)
  {
    return new Base64().decode(paramArrayOfByte);
  }
  
  public static BigInteger decodeInteger(byte[] paramArrayOfByte)
  {
    return new BigInteger(1, decodeBase64(paramArrayOfByte));
  }
  
  public static byte[] encodeBase64(byte[] paramArrayOfByte)
  {
    return encodeBase64(paramArrayOfByte, false);
  }
  
  public static byte[] encodeBase64(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    return encodeBase64(paramArrayOfByte, paramBoolean, false);
  }
  
  public static byte[] encodeBase64(byte[] paramArrayOfByte, boolean paramBoolean1, boolean paramBoolean2)
  {
    return encodeBase64(paramArrayOfByte, paramBoolean1, paramBoolean2, Integer.MAX_VALUE);
  }
  
  public static byte[] encodeBase64(byte[] paramArrayOfByte, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
      return paramArrayOfByte;
    }
    if (paramBoolean1) {}
    for (Base64 localBase64 = new Base64(paramBoolean2);; localBase64 = new Base64(0, CHUNK_SEPARATOR, paramBoolean2))
    {
      long l = localBase64.getEncodedLength(paramArrayOfByte);
      if (l <= paramInt) {
        break;
      }
      throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + l + ") than the specified maximum size of " + paramInt);
    }
    return localBase64.encode(paramArrayOfByte);
  }
  
  public static byte[] encodeBase64Chunked(byte[] paramArrayOfByte)
  {
    return encodeBase64(paramArrayOfByte, true);
  }
  
  public static String encodeBase64String(byte[] paramArrayOfByte)
  {
    return StringUtils.newStringUtf8(encodeBase64(paramArrayOfByte, false));
  }
  
  public static byte[] encodeBase64URLSafe(byte[] paramArrayOfByte)
  {
    return encodeBase64(paramArrayOfByte, false, true);
  }
  
  public static String encodeBase64URLSafeString(byte[] paramArrayOfByte)
  {
    return StringUtils.newStringUtf8(encodeBase64(paramArrayOfByte, false, true));
  }
  
  public static byte[] encodeInteger(BigInteger paramBigInteger)
  {
    if (paramBigInteger == null) {
      throw new NullPointerException("encodeInteger called with null parameter");
    }
    return encodeBase64(toIntegerBytes(paramBigInteger), false);
  }
  
  @Deprecated
  public static boolean isArrayByteBase64(byte[] paramArrayOfByte)
  {
    return isBase64(paramArrayOfByte);
  }
  
  public static boolean isBase64(byte paramByte)
  {
    return (paramByte == 61) || ((paramByte >= 0) && (paramByte < DECODE_TABLE.length) && (DECODE_TABLE[paramByte] != -1));
  }
  
  public static boolean isBase64(String paramString)
  {
    return isBase64(StringUtils.getBytesUtf8(paramString));
  }
  
  public static boolean isBase64(byte[] paramArrayOfByte)
  {
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      if ((!isBase64(paramArrayOfByte[i])) && (!isWhiteSpace(paramArrayOfByte[i]))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  static byte[] toIntegerBytes(BigInteger paramBigInteger)
  {
    int m = paramBigInteger.bitLength() + 7 >> 3 << 3;
    byte[] arrayOfByte = paramBigInteger.toByteArray();
    if ((paramBigInteger.bitLength() % 8 != 0) && (paramBigInteger.bitLength() / 8 + 1 == m / 8)) {
      return arrayOfByte;
    }
    int j = 0;
    int k = arrayOfByte.length;
    int i = k;
    if (paramBigInteger.bitLength() % 8 == 0)
    {
      j = 1;
      i = k - 1;
    }
    k = m / 8;
    paramBigInteger = new byte[m / 8];
    System.arraycopy(arrayOfByte, j, paramBigInteger, k - i, i);
    return paramBigInteger;
  }
  
  void decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BaseNCodec.Context paramContext)
  {
    if (paramContext.eof) {}
    label436:
    for (;;)
    {
      return;
      if (paramInt2 < 0) {
        paramContext.eof = true;
      }
      int i = 0;
      byte[] arrayOfByte;
      int j;
      if (i < paramInt2)
      {
        arrayOfByte = ensureBufferSize(this.decodeSize, paramContext);
        j = paramArrayOfByte[paramInt1];
        if (j == 61) {
          paramContext.eof = true;
        }
      }
      for (;;)
      {
        if ((!paramContext.eof) || (paramContext.modulus == 0)) {
          break label436;
        }
        paramArrayOfByte = ensureBufferSize(this.decodeSize, paramContext);
        switch (paramContext.modulus)
        {
        case 1: 
        default: 
          throw new IllegalStateException("Impossible modulus " + paramContext.modulus);
          if ((j >= 0) && (j < DECODE_TABLE.length))
          {
            j = DECODE_TABLE[j];
            if (j >= 0)
            {
              paramContext.modulus = ((paramContext.modulus + 1) % 4);
              paramContext.ibitWorkArea = ((paramContext.ibitWorkArea << 6) + j);
              if (paramContext.modulus == 0)
              {
                j = paramContext.pos;
                paramContext.pos = (j + 1);
                arrayOfByte[j] = ((byte)(paramContext.ibitWorkArea >> 16 & 0xFF));
                j = paramContext.pos;
                paramContext.pos = (j + 1);
                arrayOfByte[j] = ((byte)(paramContext.ibitWorkArea >> 8 & 0xFF));
                j = paramContext.pos;
                paramContext.pos = (j + 1);
                arrayOfByte[j] = ((byte)(paramContext.ibitWorkArea & 0xFF));
              }
            }
          }
          i += 1;
          paramInt1 += 1;
          break;
        case 2: 
          paramContext.ibitWorkArea >>= 4;
          paramInt1 = paramContext.pos;
          paramContext.pos = (paramInt1 + 1);
          paramArrayOfByte[paramInt1] = ((byte)(paramContext.ibitWorkArea & 0xFF));
          return;
        case 3: 
          paramContext.ibitWorkArea >>= 2;
          paramInt1 = paramContext.pos;
          paramContext.pos = (paramInt1 + 1);
          paramArrayOfByte[paramInt1] = ((byte)(paramContext.ibitWorkArea >> 8 & 0xFF));
          paramInt1 = paramContext.pos;
          paramContext.pos = (paramInt1 + 1);
          paramArrayOfByte[paramInt1] = ((byte)(paramContext.ibitWorkArea & 0xFF));
          return;
        }
      }
    }
  }
  
  void encode(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BaseNCodec.Context paramContext)
  {
    if (paramContext.eof) {}
    do
    {
      return;
      if (paramInt2 >= 0) {
        break;
      }
      paramContext.eof = true;
    } while ((paramContext.modulus == 0) && (this.lineLength == 0));
    paramArrayOfByte = ensureBufferSize(this.encodeSize, paramContext);
    paramInt1 = paramContext.pos;
    switch (paramContext.modulus)
    {
    default: 
      throw new IllegalStateException("Impossible modulus " + paramContext.modulus);
    case 1: 
      paramInt2 = paramContext.pos;
      paramContext.pos = (paramInt2 + 1);
      paramArrayOfByte[paramInt2] = this.encodeTable[(paramContext.ibitWorkArea >> 2 & 0x3F)];
      paramInt2 = paramContext.pos;
      paramContext.pos = (paramInt2 + 1);
      paramArrayOfByte[paramInt2] = this.encodeTable[(paramContext.ibitWorkArea << 4 & 0x3F)];
      if (this.encodeTable == STANDARD_ENCODE_TABLE)
      {
        paramInt2 = paramContext.pos;
        paramContext.pos = (paramInt2 + 1);
        paramArrayOfByte[paramInt2] = 61;
        paramInt2 = paramContext.pos;
        paramContext.pos = (paramInt2 + 1);
        paramArrayOfByte[paramInt2] = 61;
      }
      break;
    }
    for (;;)
    {
      paramContext.currentLinePos += paramContext.pos - paramInt1;
      if ((this.lineLength <= 0) || (paramContext.currentLinePos <= 0)) {
        break;
      }
      System.arraycopy(this.lineSeparator, 0, paramArrayOfByte, paramContext.pos, this.lineSeparator.length);
      paramContext.pos += this.lineSeparator.length;
      return;
      paramInt2 = paramContext.pos;
      paramContext.pos = (paramInt2 + 1);
      paramArrayOfByte[paramInt2] = this.encodeTable[(paramContext.ibitWorkArea >> 10 & 0x3F)];
      paramInt2 = paramContext.pos;
      paramContext.pos = (paramInt2 + 1);
      paramArrayOfByte[paramInt2] = this.encodeTable[(paramContext.ibitWorkArea >> 4 & 0x3F)];
      paramInt2 = paramContext.pos;
      paramContext.pos = (paramInt2 + 1);
      paramArrayOfByte[paramInt2] = this.encodeTable[(paramContext.ibitWorkArea << 2 & 0x3F)];
      if (this.encodeTable == STANDARD_ENCODE_TABLE)
      {
        paramInt2 = paramContext.pos;
        paramContext.pos = (paramInt2 + 1);
        paramArrayOfByte[paramInt2] = 61;
      }
    }
    int i = 0;
    while (i < paramInt2)
    {
      byte[] arrayOfByte = ensureBufferSize(this.encodeSize, paramContext);
      paramContext.modulus = ((paramContext.modulus + 1) % 3);
      int k = paramArrayOfByte[paramInt1];
      int j = k;
      if (k < 0) {
        j = k + 256;
      }
      paramContext.ibitWorkArea = ((paramContext.ibitWorkArea << 8) + j);
      if (paramContext.modulus == 0)
      {
        j = paramContext.pos;
        paramContext.pos = (j + 1);
        arrayOfByte[j] = this.encodeTable[(paramContext.ibitWorkArea >> 18 & 0x3F)];
        j = paramContext.pos;
        paramContext.pos = (j + 1);
        arrayOfByte[j] = this.encodeTable[(paramContext.ibitWorkArea >> 12 & 0x3F)];
        j = paramContext.pos;
        paramContext.pos = (j + 1);
        arrayOfByte[j] = this.encodeTable[(paramContext.ibitWorkArea >> 6 & 0x3F)];
        j = paramContext.pos;
        paramContext.pos = (j + 1);
        arrayOfByte[j] = this.encodeTable[(paramContext.ibitWorkArea & 0x3F)];
        paramContext.currentLinePos += 4;
        if ((this.lineLength > 0) && (this.lineLength <= paramContext.currentLinePos))
        {
          System.arraycopy(this.lineSeparator, 0, arrayOfByte, paramContext.pos, this.lineSeparator.length);
          paramContext.pos += this.lineSeparator.length;
          paramContext.currentLinePos = 0;
        }
      }
      i += 1;
      paramInt1 += 1;
    }
  }
  
  protected boolean isInAlphabet(byte paramByte)
  {
    return (paramByte >= 0) && (paramByte < this.decodeTable.length) && (this.decodeTable[paramByte] != -1);
  }
  
  public boolean isUrlSafe()
  {
    return this.encodeTable == URL_SAFE_ENCODE_TABLE;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/apache/commons/codec/binary/Base64.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */