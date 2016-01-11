package com.bumptech.glide.load;

public enum DecodeFormat
{
  ALWAYS_ARGB_8888,  PREFER_ARGB_8888,  PREFER_RGB_565;
  
  public static final DecodeFormat DEFAULT = PREFER_RGB_565;
  
  private DecodeFormat() {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/bumptech/glide/load/DecodeFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */