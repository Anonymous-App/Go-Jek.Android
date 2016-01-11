package io.realm;

import io.realm.annotations.RealmClass;
import io.realm.internal.Row;
import io.realm.internal.Table;

@RealmClass
public abstract class RealmObject
{
  protected Realm realm;
  protected Row row;
  
  public boolean isValid()
  {
    return (this.row != null) && (this.row.isAttached());
  }
  
  public void removeFromRealm()
  {
    if (this.row == null) {
      throw new IllegalStateException("Object malformed: missing object in Realm. Make sure to instantiate RealmObjects with Realm.createObject()");
    }
    if (this.realm == null) {
      throw new IllegalStateException("Object malformed: missing Realm. Make sure to instantiate RealmObjects with Realm.createObject()");
    }
    this.row.getTable().moveLastOver(this.row.getIndex());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/realm/RealmObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */