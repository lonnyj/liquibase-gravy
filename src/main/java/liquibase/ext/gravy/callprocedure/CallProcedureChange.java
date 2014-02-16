package liquibase.ext.gravy.callprocedure;

import liquibase.change.AbstractChange;
import liquibase.change.ChangeMetaData;
import liquibase.change.DatabaseChange;
import liquibase.change.DatabaseChangeProperty;
import liquibase.database.Database;
import liquibase.statement.SqlStatement;
import liquibase.statement.StoredProcedureStatement;

/**
 * @author Lonny Jacobson
 */
@DatabaseChange(name = "callProcedure", description = "Calls a stored procedure", priority = ChangeMetaData.PRIORITY_DEFAULT, appliesTo = "sequence")
public class CallProcedureChange extends AbstractChange {
   private String procedureName;
   private String[] parameters;

   /**
    * @see liquibase.change.Change#getConfirmationMessage()
    */
   public String getConfirmationMessage() {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @see liquibase.change.Change#generateStatements(liquibase.database.Database)
    */
   public SqlStatement[] generateStatements(Database database) {
      StoredProcedureStatement statement = new StoredProcedureStatement(
            getProcedureName());
      // TODO: Does the type matter?
      int type = 0;
      if (parameters != null) {
         for (String param : parameters) {
            statement.addParameter(param, type);
         }
      }
      return new SqlStatement[] { statement };
   }

   public void setProcedureName(String procedureName) {
      this.procedureName = procedureName;
   }

   /**
    * @return
    */
   @DatabaseChangeProperty(description = "The name of the stored procedure to call", mustEqualExisting = "storedprocedure")
   public String getProcedureName() {
      return procedureName;
   }

   public void setParameters(String[] parameters) {
      this.parameters = parameters;
   }

   /**
    * @return
    */
   @DatabaseChangeProperty(description = "The parameter to the stored procedure")
   public String[] getParameters() {
      return parameters;
   }
}
