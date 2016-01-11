package org.apache.commons.codec.digest;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;

public class DigestUtils
{
  private static final int STREAM_BUFFER_LENGTH = 1024;
  
  private static byte[] digest(MessageDigest paramMessageDigest, InputStream paramInputStream)
    throws IOException
  {
    return updateDigest(paramMessageDigest, paramInputStream).digest();
  }
  
  public static MessageDigest getDigest(String paramString)
  {
    try
    {
      paramString = MessageDigest.getInstance(paramString);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public static MessageDigest getMd2Digest()
  {
    return getDigest("MD2");
  }
  
  public static MessageDigest getMd5Digest()
  {
    return getDigest("MD5");
  }
  
  public static MessageDigest getSha1Digest()
  {
    return getDigest("SHA-1");
  }
  
  public static MessageDigest getSha256Digest()
  {
    return getDigest("SHA-256");
  }
  
  public static MessageDigest getSha384Digest()
  {
    return getDigest("SHA-384");
  }
  
  public static MessageDigest getSha512Digest()
  {
    return getDigest("SHA-512");
  }
  
  @Deprecated
  public static MessageDigest getShaDigest()
  {
    return getSha1Digest();
  }
  
  public static byte[] md2(InputStream paramInputStream)
    throws IOException
  {
    return digest(getMd2Digest(), paramInputStream);
  }
  
  public static byte[] md2(String paramString)
  {
    return md2(StringUtils.getBytesUtf8(paramString));
  }
  
  public static byte[] md2(byte[] paramArrayOfByte)
  {
    return getMd2Digest().digest(paramArrayOfByte);
  }
  
  public static String md2Hex(InputStream paramInputStream)
    throws IOException
  {
    return Hex.encodeHexString(md2(paramInputStream));
  }
  
  public static String md2Hex(String paramString)
  {
    return Hex.encodeHexString(md2(paramString));
  }
  
  public static String md2Hex(byte[] paramArrayOfByte)
  {
    return Hex.encodeHexString(md2(paramArrayOfByte));
  }
  
  public static byte[] md5(InputStream paramInputStream)
    throws IOException
  {
    return digest(getMd5Digest(), paramInputStream);
  }
  
  public static byte[] md5(String paramString)
  {
    return md5(StringUtils.getBytesUtf8(paramString));
  }
  
  public static byte[] md5(byte[] paramArrayOfByte)
  {
    return getMd5Digest().digest(paramArrayOfByte);
  }
  
  public static String md5Hex(InputStream paramInputStream)
    throws IOException
  {
    return Hex.encodeHexString(md5(paramInputStream));
  }
  
  public static String md5Hex(String paramString)
  {
    return Hex.encodeHexString(md5(paramString));
  }
  
  public static String md5Hex(byte[] paramArrayOfByte)
  {
    return Hex.encodeHexString(md5(paramArrayOfByte));
  }
  
  @Deprecated
  public static byte[] sha(InputStream paramInputStream)
    throws IOException
  {
    return sha1(paramInputStream);
  }
  
  @Deprecated
  public static byte[] sha(String paramString)
  {
    return sha1(paramString);
  }
  
  @Deprecated
  public static byte[] sha(byte[] paramArrayOfByte)
  {
    return sha1(paramArrayOfByte);
  }
  
  public static byte[] sha1(InputStream paramInputStream)
    throws IOException
  {
    return digest(getSha1Digest(), paramInputStream);
  }
  
  public static byte[] sha1(String paramString)
  {
    return sha1(StringUtils.getBytesUtf8(paramString));
  }
  
  public static byte[] sha1(byte[] paramArrayOfByte)
  {
    return getSha1Digest().digest(paramArrayOfByte);
  }
  
  public static String sha1Hex(InputStream paramInputStream)
    throws IOException
  {
    return Hex.encodeHexString(sha1(paramInputStream));
  }
  
  public static String sha1Hex(String paramString)
  {
    return Hex.encodeHexString(sha1(paramString));
  }
  
  public static String sha1Hex(byte[] paramArrayOfByte)
  {
    return Hex.encodeHexString(sha1(paramArrayOfByte));
  }
  
  public static byte[] sha256(InputStream paramInputStream)
    throws IOException
  {
    return digest(getSha256Digest(), paramInputStream);
  }
  
  public static byte[] sha256(String paramString)
  {
    return sha256(StringUtils.getBytesUtf8(paramString));
  }
  
  public static byte[] sha256(byte[] paramArrayOfByte)
  {
    return getSha256Digest().digest(paramArrayOfByte);
  }
  
  public static String sha256Hex(InputStream paramInputStream)
    throws IOException
  {
    return Hex.encodeHexString(sha256(paramInputStream));
  }
  
  public static String sha256Hex(String paramString)
  {
    return Hex.encodeHexString(sha256(paramString));
  }
  
  public static String sha256Hex(byte[] paramArrayOfByte)
  {
    return Hex.encodeHexString(sha256(paramArrayOfByte));
  }
  
  public static byte[] sha384(InputStream paramInputStream)
    throws IOException
  {
    return digest(getSha384Digest(), paramInputStream);
  }
  
  public static byte[] sha384(String paramString)
  {
    return sha384(StringUtils.getBytesUtf8(paramString));
  }
  
  public static byte[] sha384(byte[] paramArrayOfByte)
  {
    return getSha384Digest().digest(paramArrayOfByte);
  }
  
  public static String sha384Hex(InputStream paramInputStream)
    throws IOException
  {
    return Hex.encodeHexString(sha384(paramInputStream));
  }
  
  public static String sha384Hex(String paramString)
  {
    return Hex.encodeHexString(sha384(paramString));
  }
  
  public static String sha384Hex(byte[] paramArrayOfByte)
  {
    return Hex.encodeHexString(sha384(paramArrayOfByte));
  }
  
  public static byte[] sha512(InputStream paramInputStream)
    throws IOException
  {
    return digest(getSha512Digest(), paramInputStream);
  }
  
  public static byte[] sha512(String paramString)
  {
    return sha512(StringUtils.getBytesUtf8(paramString));
  }
  
  public static byte[] sha512(byte[] paramArrayOfByte)
  {
    return getSha512Digest().digest(paramArrayOfByte);
  }
  
  public static String sha512Hex(InputStream paramInputStream)
    throws IOException
  {
    return Hex.encodeHexString(sha512(paramInputStream));
  }
  
  public static String sha512Hex(String paramString)
  {
    return Hex.encodeHexString(sha512(paramString));
  }
  
  public static String sha512Hex(byte[] paramArrayOfByte)
  {
    return Hex.encodeHexString(sha512(paramArrayOfByte));
  }
  
  @Deprecated
  public static String shaHex(InputStream paramInputStream)
    throws IOException
  {
    return sha1Hex(paramInputStream);
  }
  
  @Deprecated
  public static String shaHex(String paramString)
  {
    return sha1Hex(paramString);
  }
  
  @Deprecated
  public static String shaHex(byte[] paramArrayOfByte)
  {
    return sha1Hex(paramArrayOfByte);
  }
  
  public static MessageDigest updateDigest(MessageDigest paramMessageDigest, InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    for (int i = paramInputStream.read(arrayOfByte, 0, 1024); i > -1; i = paramInputStream.read(arrayOfByte, 0, 1024)) {
      paramMessageDigest.update(arrayOfByte, 0, i);
    }
    return paramMessageDigest;
  }
  
  public static MessageDigest updateDigest(MessageDigest paramMessageDigest, String paramString)
  {
    paramMessageDigest.update(StringUtils.getBytesUtf8(paramString));
    return paramMessageDigest;
  }
  
  public static MessageDigest updateDigest(MessageDigest paramMessageDigest, byte[] paramArrayOfByte)
  {
    paramMessageDigest.update(paramArrayOfByte);
    return paramMessageDigest;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/apache/commons/codec/digest/DigestUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */