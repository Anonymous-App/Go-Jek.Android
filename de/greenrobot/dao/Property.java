package de.greenrobot.dao;

import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.query.WhereCondition;
import de.greenrobot.dao.query.WhereCondition.PropertyCondition;
import java.util.Collection;

public class Property
{
  public final String columnName;
  public final String name;
  public final int ordinal;
  public final boolean primaryKey;
  public final Class<?> type;
  
  public Property(int paramInt, Class<?> paramClass, String paramString1, boolean paramBoolean, String paramString2)
  {
    this.ordinal = paramInt;
    this.type = paramClass;
    this.name = paramString1;
    this.primaryKey = paramBoolean;
    this.columnName = paramString2;
  }
  
  public WhereCondition between(Object paramObject1, Object paramObject2)
  {
    return new WhereCondition.PropertyCondition(this, " BETWEEN ? AND ?", new Object[] { paramObject1, paramObject2 });
  }
  
  public WhereCondition eq(Object paramObject)
  {
    return new WhereCondition.PropertyCondition(this, "=?", paramObject);
  }
  
  public WhereCondition ge(Object paramObject)
  {
    return new WhereCondition.PropertyCondition(this, ">=?", paramObject);
  }
  
  public WhereCondition gt(Object paramObject)
  {
    return new WhereCondition.PropertyCondition(this, ">?", paramObject);
  }
  
  public WhereCondition in(Collection<?> paramCollection)
  {
    return in(paramCollection.toArray());
  }
  
  public WhereCondition in(Object... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder(" IN (");
    SqlUtils.appendPlaceholders(localStringBuilder, paramVarArgs.length).append(')');
    return new WhereCondition.PropertyCondition(this, localStringBuilder.toString(), paramVarArgs);
  }
  
  public WhereCondition isNotNull()
  {
    return new WhereCondition.PropertyCondition(this, " IS NOT NULL");
  }
  
  public WhereCondition isNull()
  {
    return new WhereCondition.PropertyCondition(this, " IS NULL");
  }
  
  public WhereCondition le(Object paramObject)
  {
    return new WhereCondition.PropertyCondition(this, "<=?", paramObject);
  }
  
  public WhereCondition like(String paramString)
  {
    return new WhereCondition.PropertyCondition(this, " LIKE ?", paramString);
  }
  
  public WhereCondition lt(Object paramObject)
  {
    return new WhereCondition.PropertyCondition(this, "<?", paramObject);
  }
  
  public WhereCondition notEq(Object paramObject)
  {
    return new WhereCondition.PropertyCondition(this, "<>?", paramObject);
  }
  
  public WhereCondition notIn(Collection<?> paramCollection)
  {
    return notIn(paramCollection.toArray());
  }
  
  public WhereCondition notIn(Object... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder(" NOT IN (");
    SqlUtils.appendPlaceholders(localStringBuilder, paramVarArgs.length).append(')');
    return new WhereCondition.PropertyCondition(this, localStringBuilder.toString(), paramVarArgs);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/de/greenrobot/dao/Property.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */