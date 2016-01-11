package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.UUID;

public class UUIDSerializer
  extends StdScalarSerializer<UUID>
{
  static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
  
  public UUIDSerializer()
  {
    super(UUID.class);
  }
  
  private static final void _appendInt(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 >> 24));
    paramInt2 += 1;
    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 >> 16));
    paramInt2 += 1;
    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 >> 8));
    paramArrayOfByte[(paramInt2 + 1)] = ((byte)paramInt1);
  }
  
  private static void _appendInt(int paramInt1, char[] paramArrayOfChar, int paramInt2)
  {
    _appendShort(paramInt1 >> 16, paramArrayOfChar, paramInt2);
    _appendShort(paramInt1, paramArrayOfChar, paramInt2 + 4);
  }
  
  private static void _appendShort(int paramInt1, char[] paramArrayOfChar, int paramInt2)
  {
    paramArrayOfChar[paramInt2] = HEX_CHARS[(paramInt1 >> 12 & 0xF)];
    paramInt2 += 1;
    paramArrayOfChar[paramInt2] = HEX_CHARS[(paramInt1 >> 8 & 0xF)];
    paramInt2 += 1;
    paramArrayOfChar[paramInt2] = HEX_CHARS[(paramInt1 >> 4 & 0xF)];
    paramArrayOfChar[(paramInt2 + 1)] = HEX_CHARS[(paramInt1 & 0xF)];
  }
  
  private static final byte[] _asBytes(UUID paramUUID)
  {
    byte[] arrayOfByte = new byte[16];
    long l1 = paramUUID.getMostSignificantBits();
    long l2 = paramUUID.getLeastSignificantBits();
    _appendInt((int)(l1 >> 32), arrayOfByte, 0);
    _appendInt((int)l1, arrayOfByte, 4);
    _appendInt((int)(l2 >> 32), arrayOfByte, 8);
    _appendInt((int)l2, arrayOfByte, 12);
    return arrayOfByte;
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, UUID paramUUID)
  {
    if (paramUUID == null) {}
    while ((paramUUID.getLeastSignificantBits() == 0L) && (paramUUID.getMostSignificantBits() == 0L)) {
      return true;
    }
    return false;
  }
  
  @Deprecated
  public boolean isEmpty(UUID paramUUID)
  {
    return isEmpty(null, paramUUID);
  }
  
  public void serialize(UUID paramUUID, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if ((paramJsonGenerator.canWriteBinaryNatively()) && (!(paramJsonGenerator instanceof TokenBuffer)))
    {
      paramJsonGenerator.writeBinary(_asBytes(paramUUID));
      return;
    }
    paramSerializerProvider = new char[36];
    long l = paramUUID.getMostSignificantBits();
    _appendInt((int)(l >> 32), paramSerializerProvider, 0);
    paramSerializerProvider[8] = 45;
    int i = (int)l;
    _appendShort(i >>> 16, paramSerializerProvider, 9);
    paramSerializerProvider[13] = 45;
    _appendShort(i, paramSerializerProvider, 14);
    paramSerializerProvider[18] = 45;
    l = paramUUID.getLeastSignificantBits();
    _appendShort((int)(l >>> 48), paramSerializerProvider, 19);
    paramSerializerProvider[23] = 45;
    _appendShort((int)(l >>> 32), paramSerializerProvider, 24);
    _appendInt((int)l, paramSerializerProvider, 28);
    paramJsonGenerator.writeString(paramSerializerProvider, 0, 36);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/ser/std/UUIDSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */