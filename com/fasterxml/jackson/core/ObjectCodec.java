package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.Iterator;

public abstract class ObjectCodec
  extends TreeCodec
  implements Versioned
{
  public abstract TreeNode createArrayNode();
  
  public abstract TreeNode createObjectNode();
  
  public JsonFactory getFactory()
  {
    return getJsonFactory();
  }
  
  @Deprecated
  public JsonFactory getJsonFactory()
  {
    return getFactory();
  }
  
  public abstract <T extends TreeNode> T readTree(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException;
  
  public abstract <T> T readValue(JsonParser paramJsonParser, ResolvedType paramResolvedType)
    throws IOException, JsonProcessingException;
  
  public abstract <T> T readValue(JsonParser paramJsonParser, TypeReference<?> paramTypeReference)
    throws IOException, JsonProcessingException;
  
  public abstract <T> T readValue(JsonParser paramJsonParser, Class<T> paramClass)
    throws IOException, JsonProcessingException;
  
  public abstract <T> Iterator<T> readValues(JsonParser paramJsonParser, ResolvedType paramResolvedType)
    throws IOException, JsonProcessingException;
  
  public abstract <T> Iterator<T> readValues(JsonParser paramJsonParser, TypeReference<?> paramTypeReference)
    throws IOException, JsonProcessingException;
  
  public abstract <T> Iterator<T> readValues(JsonParser paramJsonParser, Class<T> paramClass)
    throws IOException, JsonProcessingException;
  
  public abstract JsonParser treeAsTokens(TreeNode paramTreeNode);
  
  public abstract <T> T treeToValue(TreeNode paramTreeNode, Class<T> paramClass)
    throws JsonProcessingException;
  
  public Version version()
  {
    return Version.unknownVersion();
  }
  
  public abstract void writeTree(JsonGenerator paramJsonGenerator, TreeNode paramTreeNode)
    throws IOException, JsonProcessingException;
  
  public abstract void writeValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException, JsonProcessingException;
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/ObjectCodec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */