package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.query.internal.ComparisonFilter;
import com.google.android.gms.drive.query.internal.FieldOnlyFilter;
import com.google.android.gms.drive.query.internal.InFilter;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.google.android.gms.drive.query.internal.NotFilter;
import com.google.android.gms.drive.query.internal.Operator;

public class Filters
{
  public static Filter and(Filter paramFilter, Filter... paramVarArgs)
  {
    return new LogicalFilter(Operator.Re, paramFilter, paramVarArgs);
  }
  
  public static Filter and(Iterable<Filter> paramIterable)
  {
    return new LogicalFilter(Operator.Re, paramIterable);
  }
  
  public static Filter contains(SearchableMetadataField<String> paramSearchableMetadataField, String paramString)
  {
    return new ComparisonFilter(Operator.Rh, paramSearchableMetadataField, paramString);
  }
  
  public static <T> Filter eq(SearchableMetadataField<T> paramSearchableMetadataField, T paramT)
  {
    return new ComparisonFilter(Operator.QZ, paramSearchableMetadataField, paramT);
  }
  
  public static <T extends Comparable<T>> Filter greaterThan(SearchableOrderedMetadataField<T> paramSearchableOrderedMetadataField, T paramT)
  {
    return new ComparisonFilter(Operator.Rc, paramSearchableOrderedMetadataField, paramT);
  }
  
  public static <T extends Comparable<T>> Filter greaterThanEquals(SearchableOrderedMetadataField<T> paramSearchableOrderedMetadataField, T paramT)
  {
    return new ComparisonFilter(Operator.Rd, paramSearchableOrderedMetadataField, paramT);
  }
  
  public static <T> Filter in(SearchableCollectionMetadataField<T> paramSearchableCollectionMetadataField, T paramT)
  {
    return new InFilter(paramSearchableCollectionMetadataField, paramT);
  }
  
  public static <T extends Comparable<T>> Filter lessThan(SearchableOrderedMetadataField<T> paramSearchableOrderedMetadataField, T paramT)
  {
    return new ComparisonFilter(Operator.Ra, paramSearchableOrderedMetadataField, paramT);
  }
  
  public static <T extends Comparable<T>> Filter lessThanEquals(SearchableOrderedMetadataField<T> paramSearchableOrderedMetadataField, T paramT)
  {
    return new ComparisonFilter(Operator.Rb, paramSearchableOrderedMetadataField, paramT);
  }
  
  public static Filter not(Filter paramFilter)
  {
    return new NotFilter(paramFilter);
  }
  
  public static Filter openedByMe()
  {
    return new FieldOnlyFilter(SearchableField.LAST_VIEWED_BY_ME);
  }
  
  public static Filter or(Filter paramFilter, Filter... paramVarArgs)
  {
    return new LogicalFilter(Operator.Rf, paramFilter, paramVarArgs);
  }
  
  public static Filter or(Iterable<Filter> paramIterable)
  {
    return new LogicalFilter(Operator.Rf, paramIterable);
  }
  
  public static Filter sharedWithMe()
  {
    return new FieldOnlyFilter(SearchableField.QG);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/Filters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */