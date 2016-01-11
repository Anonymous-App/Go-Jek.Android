package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ObjectNode
  extends ContainerNode<ObjectNode>
{
  protected final Map<String, JsonNode> _children;
  
  public ObjectNode(JsonNodeFactory paramJsonNodeFactory)
  {
    super(paramJsonNodeFactory);
    this._children = new LinkedHashMap();
  }
  
  public ObjectNode(JsonNodeFactory paramJsonNodeFactory, Map<String, JsonNode> paramMap)
  {
    super(paramJsonNodeFactory);
    this._children = paramMap;
  }
  
  protected JsonNode _at(JsonPointer paramJsonPointer)
  {
    return get(paramJsonPointer.getMatchingProperty());
  }
  
  protected boolean _childrenEqual(ObjectNode paramObjectNode)
  {
    return this._children.equals(paramObjectNode._children);
  }
  
  protected ObjectNode _put(String paramString, JsonNode paramJsonNode)
  {
    this._children.put(paramString, paramJsonNode);
    return this;
  }
  
  public JsonToken asToken()
  {
    return JsonToken.START_OBJECT;
  }
  
  public ObjectNode deepCopy()
  {
    ObjectNode localObjectNode = new ObjectNode(this._nodeFactory);
    Iterator localIterator = this._children.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localObjectNode._children.put(localEntry.getKey(), ((JsonNode)localEntry.getValue()).deepCopy());
    }
    return localObjectNode;
  }
  
  public Iterator<JsonNode> elements()
  {
    return this._children.values().iterator();
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == null);
      bool1 = bool2;
    } while (!(paramObject instanceof ObjectNode));
    return _childrenEqual((ObjectNode)paramObject);
  }
  
  public boolean equals(Comparator<JsonNode> paramComparator, JsonNode paramJsonNode)
  {
    if (!(paramJsonNode instanceof ObjectNode)) {}
    int i;
    do
    {
      return false;
      paramJsonNode = (ObjectNode)paramJsonNode;
      localObject = this._children;
      paramJsonNode = paramJsonNode._children;
      i = ((Map)localObject).size();
    } while (paramJsonNode.size() != i);
    Object localObject = ((Map)localObject).entrySet().iterator();
    for (;;)
    {
      if (((Iterator)localObject).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
        JsonNode localJsonNode = (JsonNode)paramJsonNode.get(localEntry.getKey());
        if (localJsonNode == null) {
          break;
        }
        if (paramComparator.compare(localEntry.getValue(), localJsonNode) != 0) {
          return false;
        }
      }
    }
    return true;
  }
  
  public Iterator<String> fieldNames()
  {
    return this._children.keySet().iterator();
  }
  
  public Iterator<Map.Entry<String, JsonNode>> fields()
  {
    return this._children.entrySet().iterator();
  }
  
  public ObjectNode findParent(String paramString)
  {
    Iterator localIterator = this._children.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Map.Entry)localIterator.next();
      if (paramString.equals(((Map.Entry)localObject).getKey())) {
        return this;
      }
      localObject = ((JsonNode)((Map.Entry)localObject).getValue()).findParent(paramString);
      if (localObject != null) {
        return (ObjectNode)localObject;
      }
    }
    return null;
  }
  
  public List<JsonNode> findParents(String paramString, List<JsonNode> paramList)
  {
    Iterator localIterator = this._children.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Map.Entry)localIterator.next();
      if (paramString.equals(((Map.Entry)localObject).getKey()))
      {
        localObject = paramList;
        if (paramList == null) {
          localObject = new ArrayList();
        }
        ((List)localObject).add(this);
        paramList = (List<JsonNode>)localObject;
      }
      else
      {
        paramList = ((JsonNode)((Map.Entry)localObject).getValue()).findParents(paramString, paramList);
      }
    }
    return paramList;
  }
  
  public JsonNode findValue(String paramString)
  {
    Iterator localIterator = this._children.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Map.Entry)localIterator.next();
      if (paramString.equals(((Map.Entry)localObject).getKey())) {
        return (JsonNode)((Map.Entry)localObject).getValue();
      }
      localObject = ((JsonNode)((Map.Entry)localObject).getValue()).findValue(paramString);
      if (localObject != null) {
        return (JsonNode)localObject;
      }
    }
    return null;
  }
  
  public List<JsonNode> findValues(String paramString, List<JsonNode> paramList)
  {
    Iterator localIterator = this._children.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (paramString.equals(localEntry.getKey()))
      {
        Object localObject = paramList;
        if (paramList == null) {
          localObject = new ArrayList();
        }
        ((List)localObject).add(localEntry.getValue());
        paramList = (List<JsonNode>)localObject;
      }
      else
      {
        paramList = ((JsonNode)localEntry.getValue()).findValues(paramString, paramList);
      }
    }
    return paramList;
  }
  
  public List<String> findValuesAsText(String paramString, List<String> paramList)
  {
    Iterator localIterator = this._children.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (paramString.equals(localEntry.getKey()))
      {
        Object localObject = paramList;
        if (paramList == null) {
          localObject = new ArrayList();
        }
        ((List)localObject).add(((JsonNode)localEntry.getValue()).asText());
        paramList = (List<String>)localObject;
      }
      else
      {
        paramList = ((JsonNode)localEntry.getValue()).findValuesAsText(paramString, paramList);
      }
    }
    return paramList;
  }
  
  public JsonNode get(int paramInt)
  {
    return null;
  }
  
  public JsonNode get(String paramString)
  {
    return (JsonNode)this._children.get(paramString);
  }
  
  public JsonNodeType getNodeType()
  {
    return JsonNodeType.OBJECT;
  }
  
  public int hashCode()
  {
    return this._children.hashCode();
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider)
  {
    return this._children.isEmpty();
  }
  
  public JsonNode path(int paramInt)
  {
    return MissingNode.getInstance();
  }
  
  public JsonNode path(String paramString)
  {
    paramString = (JsonNode)this._children.get(paramString);
    if (paramString != null) {
      return paramString;
    }
    return MissingNode.getInstance();
  }
  
  @Deprecated
  public JsonNode put(String paramString, JsonNode paramJsonNode)
  {
    Object localObject = paramJsonNode;
    if (paramJsonNode == null) {
      localObject = nullNode();
    }
    return (JsonNode)this._children.put(paramString, localObject);
  }
  
  public ObjectNode put(String paramString, double paramDouble)
  {
    return _put(paramString, numberNode(paramDouble));
  }
  
  public ObjectNode put(String paramString, float paramFloat)
  {
    return _put(paramString, numberNode(paramFloat));
  }
  
  public ObjectNode put(String paramString, int paramInt)
  {
    return _put(paramString, numberNode(paramInt));
  }
  
  public ObjectNode put(String paramString, long paramLong)
  {
    return _put(paramString, numberNode(paramLong));
  }
  
  public ObjectNode put(String paramString, Boolean paramBoolean)
  {
    if (paramBoolean == null) {}
    for (paramBoolean = nullNode();; paramBoolean = booleanNode(paramBoolean.booleanValue())) {
      return _put(paramString, paramBoolean);
    }
  }
  
  public ObjectNode put(String paramString, Double paramDouble)
  {
    if (paramDouble == null) {}
    for (paramDouble = nullNode();; paramDouble = numberNode(paramDouble.doubleValue())) {
      return _put(paramString, paramDouble);
    }
  }
  
  public ObjectNode put(String paramString, Float paramFloat)
  {
    if (paramFloat == null) {}
    for (paramFloat = nullNode();; paramFloat = numberNode(paramFloat.floatValue())) {
      return _put(paramString, paramFloat);
    }
  }
  
  public ObjectNode put(String paramString, Integer paramInteger)
  {
    if (paramInteger == null) {}
    for (paramInteger = nullNode();; paramInteger = numberNode(paramInteger.intValue())) {
      return _put(paramString, paramInteger);
    }
  }
  
  public ObjectNode put(String paramString, Long paramLong)
  {
    if (paramLong == null) {}
    for (paramLong = nullNode();; paramLong = numberNode(paramLong.longValue())) {
      return _put(paramString, paramLong);
    }
  }
  
  public ObjectNode put(String paramString, Short paramShort)
  {
    if (paramShort == null) {}
    for (paramShort = nullNode();; paramShort = numberNode(paramShort.shortValue())) {
      return _put(paramString, paramShort);
    }
  }
  
  public ObjectNode put(String paramString1, String paramString2)
  {
    if (paramString2 == null) {}
    for (paramString2 = nullNode();; paramString2 = textNode(paramString2)) {
      return _put(paramString1, paramString2);
    }
  }
  
  public ObjectNode put(String paramString, BigDecimal paramBigDecimal)
  {
    if (paramBigDecimal == null) {}
    for (paramBigDecimal = nullNode();; paramBigDecimal = numberNode(paramBigDecimal)) {
      return _put(paramString, paramBigDecimal);
    }
  }
  
  public ObjectNode put(String paramString, short paramShort)
  {
    return _put(paramString, numberNode(paramShort));
  }
  
  public ObjectNode put(String paramString, boolean paramBoolean)
  {
    return _put(paramString, booleanNode(paramBoolean));
  }
  
  public ObjectNode put(String paramString, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {}
    for (paramArrayOfByte = nullNode();; paramArrayOfByte = binaryNode(paramArrayOfByte)) {
      return _put(paramString, paramArrayOfByte);
    }
  }
  
  @Deprecated
  public JsonNode putAll(ObjectNode paramObjectNode)
  {
    return setAll(paramObjectNode);
  }
  
  @Deprecated
  public JsonNode putAll(Map<String, ? extends JsonNode> paramMap)
  {
    return setAll(paramMap);
  }
  
  public ArrayNode putArray(String paramString)
  {
    ArrayNode localArrayNode = arrayNode();
    _put(paramString, localArrayNode);
    return localArrayNode;
  }
  
  public ObjectNode putNull(String paramString)
  {
    this._children.put(paramString, nullNode());
    return this;
  }
  
  public ObjectNode putObject(String paramString)
  {
    ObjectNode localObjectNode = objectNode();
    _put(paramString, localObjectNode);
    return localObjectNode;
  }
  
  public ObjectNode putPOJO(String paramString, Object paramObject)
  {
    return _put(paramString, pojoNode(paramObject));
  }
  
  public ObjectNode putRawValue(String paramString, RawValue paramRawValue)
  {
    return _put(paramString, rawValueNode(paramRawValue));
  }
  
  public JsonNode remove(String paramString)
  {
    return (JsonNode)this._children.remove(paramString);
  }
  
  public ObjectNode remove(Collection<String> paramCollection)
  {
    this._children.keySet().removeAll(paramCollection);
    return this;
  }
  
  public ObjectNode removeAll()
  {
    this._children.clear();
    return this;
  }
  
  public JsonNode replace(String paramString, JsonNode paramJsonNode)
  {
    Object localObject = paramJsonNode;
    if (paramJsonNode == null) {
      localObject = nullNode();
    }
    return (JsonNode)this._children.put(paramString, localObject);
  }
  
  public ObjectNode retain(Collection<String> paramCollection)
  {
    this._children.keySet().retainAll(paramCollection);
    return this;
  }
  
  public ObjectNode retain(String... paramVarArgs)
  {
    return retain(Arrays.asList(paramVarArgs));
  }
  
  public void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException
  {
    paramJsonGenerator.writeStartObject();
    Iterator localIterator = this._children.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      paramJsonGenerator.writeFieldName((String)localEntry.getKey());
      ((BaseJsonNode)localEntry.getValue()).serialize(paramJsonGenerator, paramSerializerProvider);
    }
    paramJsonGenerator.writeEndObject();
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonProcessingException
  {
    paramTypeSerializer.writeTypePrefixForObject(this, paramJsonGenerator);
    Iterator localIterator = this._children.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      paramJsonGenerator.writeFieldName((String)localEntry.getKey());
      ((BaseJsonNode)localEntry.getValue()).serialize(paramJsonGenerator, paramSerializerProvider);
    }
    paramTypeSerializer.writeTypeSuffixForObject(this, paramJsonGenerator);
  }
  
  public JsonNode set(String paramString, JsonNode paramJsonNode)
  {
    Object localObject = paramJsonNode;
    if (paramJsonNode == null) {
      localObject = nullNode();
    }
    this._children.put(paramString, localObject);
    return this;
  }
  
  public JsonNode setAll(ObjectNode paramObjectNode)
  {
    this._children.putAll(paramObjectNode._children);
    return this;
  }
  
  public JsonNode setAll(Map<String, ? extends JsonNode> paramMap)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      JsonNode localJsonNode = (JsonNode)localEntry.getValue();
      paramMap = localJsonNode;
      if (localJsonNode == null) {
        paramMap = nullNode();
      }
      this._children.put(localEntry.getKey(), paramMap);
    }
    return this;
  }
  
  public int size()
  {
    return this._children.size();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder((size() << 4) + 32);
    localStringBuilder.append("{");
    int i = 0;
    Iterator localIterator = this._children.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (i > 0) {
        localStringBuilder.append(",");
      }
      i += 1;
      TextNode.appendQuoted(localStringBuilder, (String)localEntry.getKey());
      localStringBuilder.append(':');
      localStringBuilder.append(((JsonNode)localEntry.getValue()).toString());
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public ObjectNode with(String paramString)
  {
    Object localObject = (JsonNode)this._children.get(paramString);
    if (localObject != null)
    {
      if ((localObject instanceof ObjectNode)) {
        return (ObjectNode)localObject;
      }
      throw new UnsupportedOperationException("Property '" + paramString + "' has value that is not of type ObjectNode (but " + localObject.getClass().getName() + ")");
    }
    localObject = objectNode();
    this._children.put(paramString, localObject);
    return (ObjectNode)localObject;
  }
  
  public ArrayNode withArray(String paramString)
  {
    Object localObject = (JsonNode)this._children.get(paramString);
    if (localObject != null)
    {
      if ((localObject instanceof ArrayNode)) {
        return (ArrayNode)localObject;
      }
      throw new UnsupportedOperationException("Property '" + paramString + "' has value that is not of type ArrayNode (but " + localObject.getClass().getName() + ")");
    }
    localObject = arrayNode();
    this._children.put(paramString, localObject);
    return (ArrayNode)localObject;
  }
  
  public JsonNode without(String paramString)
  {
    this._children.remove(paramString);
    return this;
  }
  
  public ObjectNode without(Collection<String> paramCollection)
  {
    this._children.keySet().removeAll(paramCollection);
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/node/ObjectNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */