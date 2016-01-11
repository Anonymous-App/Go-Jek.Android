package com.kartuku.digitalwallet.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PredicateBuilder<T>
{
  public static int MATCH_MODE_ANYWHERE = 4;
  public static int MATCH_MODE_END;
  public static int MATCH_MODE_EXACT = 1;
  public static int MATCH_MODE_START = 2;
  private Root ˊ;
  private CriteriaQuery ˋ;
  private CriteriaBuilder ˎ;
  private List<Predicate> ˏ = new ArrayList();
  
  static
  {
    MATCH_MODE_END = 3;
  }
  
  public PredicateBuilder(Root paramRoot, CriteriaQuery paramCriteriaQuery, CriteriaBuilder paramCriteriaBuilder)
  {
    this.ˊ = paramRoot;
    this.ˋ = paramCriteriaQuery;
    this.ˎ = paramCriteriaBuilder;
  }
  
  private static String ˊ(String paramString, int paramInt)
  {
    if (paramInt == MATCH_MODE_EXACT) {}
    do
    {
      return paramString;
      if (paramInt == MATCH_MODE_ANYWHERE) {
        return "%" + paramString + '%';
      }
      if (paramInt == MATCH_MODE_END) {
        return "%" + paramString;
      }
    } while (paramInt != MATCH_MODE_START);
    return paramString + '%';
  }
  
  public void addBetweenTruncDatePredicate(String paramString, Date paramDate1, Date paramDate2)
  {
    int j = 1;
    if (paramDate1 == null)
    {
      i = 1;
      if (i == 0) {
        if (paramDate2 != null) {
          break label84;
        }
      }
    }
    label84:
    for (int i = j;; i = 0)
    {
      if (i == 0) {
        this.ˏ.add(this.ˎ.between(this.ˊ.get(paramString), this.ˎ.literal(paramDate1), this.ˎ.literal(paramDate2)));
      }
      return;
      i = 0;
      break;
    }
  }
  
  public void addComparablePredicate(String paramString1, Comparable paramComparable, String paramString2)
  {
    if (ComparisonOperator.EQUAL.getCode().equals(paramString2)) {
      addEqualPredicate(paramString1, paramComparable);
    }
    do
    {
      return;
      if (ComparisonOperator.NOT_EQUAL.getCode().equals(paramString2))
      {
        addNotEqualPredicate(paramString1, paramComparable);
        return;
      }
      if (ComparisonOperator.LESS_THAN.getCode().equals(paramString2))
      {
        addLessThanPredicate(paramString1, paramComparable);
        return;
      }
      if (ComparisonOperator.LESS_THAN_OR_EQUAL_TO.getCode().equals(paramString2))
      {
        addLessThanOrEqualToPredicate(paramString1, paramComparable);
        return;
      }
      if (ComparisonOperator.GREATER_THAN.getCode().equals(paramString2))
      {
        addGreaterThanPredicate(paramString1, paramComparable);
        return;
      }
    } while (!ComparisonOperator.GREATER_THAN_OR_EQUAL_TO.getCode().equals(paramString2));
    addGreaterThanOrEqualToPredicate(paramString1, paramComparable);
  }
  
  public void addDatePredicate(String paramString, Date paramDate1, Date paramDate2, Date paramDate3)
  {
    if (paramDate1 == null) {}
    for (int i = 1; i == 0; i = 0)
    {
      addEqualTruncDatePredicate(paramString, paramDate1);
      return;
    }
    addBetweenTruncDatePredicate(paramString, paramDate2, paramDate3);
  }
  
  public void addEqualIgnoreCasePredicate(String paramString1, String paramString2)
  {
    boolean bool;
    if (paramString2 == null) {
      bool = true;
    }
    for (;;)
    {
      if (!bool) {
        this.ˏ.add(this.ˎ.equal(this.ˎ.upper(this.ˊ.get(paramString1)), paramString2.toUpperCase()));
      }
      return;
      if ((paramString2 instanceof String)) {
        bool = ((String)paramString2).isEmpty();
      } else {
        bool = false;
      }
    }
  }
  
  public void addEqualPredicate(String paramString, Object paramObject)
  {
    boolean bool;
    if (paramObject == null) {
      bool = true;
    }
    for (;;)
    {
      if (!bool) {
        this.ˏ.add(this.ˎ.equal(this.ˊ.get(paramString), paramObject));
      }
      return;
      if ((paramObject instanceof String)) {
        bool = ((String)paramObject).isEmpty();
      } else {
        bool = false;
      }
    }
  }
  
  public void addEqualPredicate(From paramFrom, String paramString, Object paramObject)
  {
    boolean bool;
    if (paramObject == null) {
      bool = true;
    }
    for (;;)
    {
      if (!bool) {
        this.ˏ.add(this.ˎ.equal(paramFrom.get(paramString), paramObject));
      }
      return;
      if ((paramObject instanceof String)) {
        bool = ((String)paramObject).isEmpty();
      } else {
        bool = false;
      }
    }
  }
  
  public void addEqualTruncDatePredicate(String paramString, Date paramDate)
  {
    if (paramDate == null) {}
    for (int i = 1;; i = 0)
    {
      if (i == 0) {
        this.ˏ.add(this.ˎ.equal(this.ˎ.function("TRUNC", Date.class, new Expression[] { this.ˊ.get(paramString) }), this.ˎ.function("TRUNC", Date.class, new Expression[] { this.ˎ.literal(paramDate) })));
      }
      return;
    }
  }
  
  public void addGreaterThanOrEqualToPredicate(String paramString, Comparable paramComparable)
  {
    boolean bool;
    if (paramComparable == null) {
      bool = true;
    }
    for (;;)
    {
      if (!bool) {
        this.ˏ.add(this.ˎ.greaterThanOrEqualTo(this.ˊ.get(paramString), paramComparable));
      }
      return;
      if ((paramComparable instanceof String)) {
        bool = ((String)paramComparable).isEmpty();
      } else {
        bool = false;
      }
    }
  }
  
  public void addGreaterThanPredicate(String paramString, Comparable paramComparable)
  {
    boolean bool;
    if (paramComparable == null) {
      bool = true;
    }
    for (;;)
    {
      if (!bool) {
        this.ˏ.add(this.ˎ.greaterThan(this.ˊ.get(paramString), paramComparable));
      }
      return;
      if ((paramComparable instanceof String)) {
        bool = ((String)paramComparable).isEmpty();
      } else {
        bool = false;
      }
    }
  }
  
  public void addLessThanOrEqualToPredicate(String paramString, Comparable paramComparable)
  {
    boolean bool;
    if (paramComparable == null) {
      bool = true;
    }
    for (;;)
    {
      if (!bool) {
        this.ˏ.add(this.ˎ.lessThanOrEqualTo(this.ˊ.get(paramString), paramComparable));
      }
      return;
      if ((paramComparable instanceof String)) {
        bool = ((String)paramComparable).isEmpty();
      } else {
        bool = false;
      }
    }
  }
  
  public void addLessThanPredicate(String paramString, Comparable paramComparable)
  {
    boolean bool;
    if (paramComparable == null) {
      bool = true;
    }
    for (;;)
    {
      if (!bool) {
        this.ˏ.add(this.ˎ.lessThan(this.ˊ.get(paramString), paramComparable));
      }
      return;
      if ((paramComparable instanceof String)) {
        bool = ((String)paramComparable).isEmpty();
      } else {
        bool = false;
      }
    }
  }
  
  public void addLikeIgnoreCasePredicate(String paramString1, String paramString2)
  {
    addLikeIgnoreCasePredicate(paramString1, paramString2, MATCH_MODE_EXACT);
  }
  
  public void addLikeIgnoreCasePredicate(String paramString1, String paramString2, int paramInt)
  {
    boolean bool;
    if (paramString2 == null) {
      bool = true;
    }
    for (;;)
    {
      if (!bool)
      {
        paramString2 = ˊ(paramString2, paramInt);
        this.ˏ.add(this.ˎ.like(this.ˎ.upper(this.ˊ.get(paramString1).as(String.class)), paramString2.toUpperCase()));
      }
      return;
      if ((paramString2 instanceof String)) {
        bool = ((String)paramString2).isEmpty();
      } else {
        bool = false;
      }
    }
  }
  
  public void addLikePredicate(String paramString, Integer paramInteger)
  {
    addLikePredicate(paramString, paramInteger, MATCH_MODE_EXACT);
  }
  
  public void addLikePredicate(String paramString, Integer paramInteger, int paramInt)
  {
    if (paramInteger != null) {
      addLikePredicate(paramString, String.valueOf(paramInteger), paramInt);
    }
  }
  
  public void addLikePredicate(String paramString1, String paramString2)
  {
    addLikePredicate(paramString1, paramString2, MATCH_MODE_EXACT);
  }
  
  public void addLikePredicate(String paramString1, String paramString2, int paramInt)
  {
    boolean bool;
    if (paramString2 == null) {
      bool = true;
    }
    for (;;)
    {
      if (!bool)
      {
        paramString2 = ˊ(paramString2, paramInt);
        this.ˏ.add(this.ˎ.like(this.ˊ.get(paramString1).as(String.class), paramString2));
      }
      return;
      if ((paramString2 instanceof String)) {
        bool = ((String)paramString2).isEmpty();
      } else {
        bool = false;
      }
    }
  }
  
  public void addNotEqualIgnoreCasePredicate(String paramString1, String paramString2)
  {
    boolean bool;
    if (paramString2 == null) {
      bool = true;
    }
    for (;;)
    {
      if (!bool) {
        this.ˏ.add(this.ˎ.notEqual(this.ˎ.upper(this.ˊ.get(paramString1)), paramString2.toUpperCase()));
      }
      return;
      if ((paramString2 instanceof String)) {
        bool = ((String)paramString2).isEmpty();
      } else {
        bool = false;
      }
    }
  }
  
  public void addNotEqualPredicate(String paramString, Object paramObject)
  {
    boolean bool;
    if (paramObject == null) {
      bool = true;
    }
    for (;;)
    {
      if (!bool) {
        this.ˏ.add(this.ˎ.notEqual(this.ˊ.get(paramString), paramObject));
      }
      return;
      if ((paramObject instanceof String)) {
        bool = ((String)paramObject).isEmpty();
      } else {
        bool = false;
      }
    }
  }
  
  public Predicate getAndPredicate()
  {
    if (this.ˏ.isEmpty()) {
      return null;
    }
    Predicate[] arrayOfPredicate = new Predicate[this.ˏ.size()];
    arrayOfPredicate = (Predicate[])this.ˏ.toArray(arrayOfPredicate);
    return this.ˎ.and(arrayOfPredicate);
  }
  
  public Predicate[] getPredicates()
  {
    if (this.ˏ.isEmpty()) {
      return null;
    }
    Predicate[] arrayOfPredicate = new Predicate[this.ˏ.size()];
    return (Predicate[])this.ˏ.toArray(arrayOfPredicate);
  }
  
  public boolean isEmpty()
  {
    return this.ˏ.isEmpty();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/PredicateBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */