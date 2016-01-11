package bolts;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AggregateException
  extends Exception
{
  private static final String DEFAULT_MESSAGE = "There were multiple errors.";
  private static final long serialVersionUID = 1L;
  private List<Throwable> innerThrowables;
  
  public AggregateException(String paramString, List<? extends Throwable> paramList) {}
  
  public AggregateException(String paramString, Throwable[] paramArrayOfThrowable)
  {
    this(paramString, Arrays.asList(paramArrayOfThrowable));
  }
  
  public AggregateException(List<? extends Throwable> paramList)
  {
    this("There were multiple errors.", paramList);
  }
  
  @Deprecated
  public Throwable[] getCauses()
  {
    return (Throwable[])this.innerThrowables.toArray(new Throwable[this.innerThrowables.size()]);
  }
  
  @Deprecated
  public List<Exception> getErrors()
  {
    ArrayList localArrayList = new ArrayList();
    if (this.innerThrowables == null) {}
    for (;;)
    {
      return localArrayList;
      Iterator localIterator = this.innerThrowables.iterator();
      while (localIterator.hasNext())
      {
        Throwable localThrowable = (Throwable)localIterator.next();
        if ((localThrowable instanceof Exception)) {
          localArrayList.add((Exception)localThrowable);
        } else {
          localArrayList.add(new Exception(localThrowable));
        }
      }
    }
  }
  
  public List<Throwable> getInnerThrowables()
  {
    return this.innerThrowables;
  }
  
  public void printStackTrace(PrintStream paramPrintStream)
  {
    super.printStackTrace(paramPrintStream);
    int i = -1;
    Iterator localIterator = this.innerThrowables.iterator();
    while (localIterator.hasNext())
    {
      Throwable localThrowable = (Throwable)localIterator.next();
      paramPrintStream.append("\n");
      paramPrintStream.append("  Inner throwable #");
      i += 1;
      paramPrintStream.append(Integer.toString(i));
      paramPrintStream.append(": ");
      localThrowable.printStackTrace(paramPrintStream);
      paramPrintStream.append("\n");
    }
  }
  
  public void printStackTrace(PrintWriter paramPrintWriter)
  {
    super.printStackTrace(paramPrintWriter);
    int i = -1;
    Iterator localIterator = this.innerThrowables.iterator();
    while (localIterator.hasNext())
    {
      Throwable localThrowable = (Throwable)localIterator.next();
      paramPrintWriter.append("\n");
      paramPrintWriter.append("  Inner throwable #");
      i += 1;
      paramPrintWriter.append(Integer.toString(i));
      paramPrintWriter.append(": ");
      localThrowable.printStackTrace(paramPrintWriter);
      paramPrintWriter.append("\n");
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/bolts/AggregateException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */