package com.fasterxml.jackson.core.util;

public class BufferRecycler
{
  public static final int BYTE_BASE64_CODEC_BUFFER = 3;
  private static final int[] BYTE_BUFFER_LENGTHS = { 8000, 8000, 2000, 2000 };
  public static final int BYTE_READ_IO_BUFFER = 0;
  public static final int BYTE_WRITE_CONCAT_BUFFER = 2;
  public static final int BYTE_WRITE_ENCODING_BUFFER = 1;
  private static final int[] CHAR_BUFFER_LENGTHS = { 4000, 4000, 200, 200 };
  public static final int CHAR_CONCAT_BUFFER = 1;
  public static final int CHAR_NAME_COPY_BUFFER = 3;
  public static final int CHAR_TEXT_BUFFER = 2;
  public static final int CHAR_TOKEN_BUFFER = 0;
  protected final byte[][] _byteBuffers;
  protected final char[][] _charBuffers;
  
  public BufferRecycler()
  {
    this(4, 4);
  }
  
  protected BufferRecycler(int paramInt1, int paramInt2)
  {
    this._byteBuffers = new byte[paramInt1][];
    this._charBuffers = new char[paramInt2][];
  }
  
  public final byte[] allocByteBuffer(int paramInt)
  {
    return allocByteBuffer(paramInt, 0);
  }
  
  public byte[] allocByteBuffer(int paramInt1, int paramInt2)
  {
    int j = byteBufferLength(paramInt1);
    int i = paramInt2;
    if (paramInt2 < j) {
      i = j;
    }
    byte[] arrayOfByte = this._byteBuffers[paramInt1];
    if ((arrayOfByte == null) || (arrayOfByte.length < i)) {
      return balloc(i);
    }
    this._byteBuffers[paramInt1] = null;
    return arrayOfByte;
  }
  
  public final char[] allocCharBuffer(int paramInt)
  {
    return allocCharBuffer(paramInt, 0);
  }
  
  public char[] allocCharBuffer(int paramInt1, int paramInt2)
  {
    int j = charBufferLength(paramInt1);
    int i = paramInt2;
    if (paramInt2 < j) {
      i = j;
    }
    char[] arrayOfChar = this._charBuffers[paramInt1];
    if ((arrayOfChar == null) || (arrayOfChar.length < i)) {
      return calloc(i);
    }
    this._charBuffers[paramInt1] = null;
    return arrayOfChar;
  }
  
  protected byte[] balloc(int paramInt)
  {
    return new byte[paramInt];
  }
  
  protected int byteBufferLength(int paramInt)
  {
    return BYTE_BUFFER_LENGTHS[paramInt];
  }
  
  protected char[] calloc(int paramInt)
  {
    return new char[paramInt];
  }
  
  protected int charBufferLength(int paramInt)
  {
    return CHAR_BUFFER_LENGTHS[paramInt];
  }
  
  public final void releaseByteBuffer(int paramInt, byte[] paramArrayOfByte)
  {
    this._byteBuffers[paramInt] = paramArrayOfByte;
  }
  
  public void releaseCharBuffer(int paramInt, char[] paramArrayOfChar)
  {
    this._charBuffers[paramInt] = paramArrayOfChar;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/util/BufferRecycler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */