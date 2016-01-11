package com.fasterxml.jackson.core;

import java.io.IOException;

public abstract class TreeCodec
{
  public abstract TreeNode createArrayNode();
  
  public abstract TreeNode createObjectNode();
  
  public abstract <T extends TreeNode> T readTree(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException;
  
  public abstract JsonParser treeAsTokens(TreeNode paramTreeNode);
  
  public abstract void writeTree(JsonGenerator paramJsonGenerator, TreeNode paramTreeNode)
    throws IOException, JsonProcessingException;
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/TreeCodec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */