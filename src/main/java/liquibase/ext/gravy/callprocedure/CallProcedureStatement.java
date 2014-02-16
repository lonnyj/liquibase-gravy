package liquibase.ext.gravy.callprocedure;

import liquibase.statement.AbstractSqlStatement;
import liquibase.statement.CallableSqlStatement;

/**
 * @author Lonny Jacobson
 */
public class CallProcedureStatement extends AbstractSqlStatement implements
      CallableSqlStatement {

   /**
    * @see liquibase.statement.SqlStatement#skipOnUnsupported()
    */
   public boolean skipOnUnsupported() {
      // TODO Auto-generated method stub
      return false;
   }

}
