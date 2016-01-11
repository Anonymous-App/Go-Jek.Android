package org.codehaus.jackson.schema;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

public class JsonSchema
{
  private final ObjectNode schema;
  
  @JsonCreator
  public JsonSchema(ObjectNode paramObjectNode)
  {
    this.schema = paramObjectNode;
  }
  
  public static JsonNode getDefaultSchemaNode()
  {
    ObjectNode localObjectNode = JsonNodeFactory.instance.objectNode();
    localObjectNode.put("type", "any");
    return localObjectNode;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (!(paramObject instanceof JsonSchema)) {
        return false;
      }
      paramObject = (JsonSchema)paramObject;
      if (this.schema != null) {
        break;
      }
    } while (((JsonSchema)paramObject).schema == null);
    return false;
    return this.schema.equals(((JsonSchema)paramObject).schema);
  }
  
  @JsonValue
  public ObjectNode getSchemaNode()
  {
    return this.schema;
  }
  
  public String toString()
  {
    return this.schema.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/schema/JsonSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */