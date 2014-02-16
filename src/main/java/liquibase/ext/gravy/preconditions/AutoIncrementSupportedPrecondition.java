package liquibase.ext.gravy.preconditions;

import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.exception.PreconditionErrorException;
import liquibase.exception.PreconditionFailedException;

/**
 * This precondition indicates if a database supports auto-increment columns.
 * 
 * @author Lonny
 */
public class AutoIncrementSupportedPrecondition extends AbstractPrecondition {

   /**
    * Constructs a new <code>AutoIncrementSupportedPrecondition</code>.
    */
   public AutoIncrementSupportedPrecondition() {
      super("autoIncrementSupported");
   }

   /**
    * Determines if the database supports auto-increment columns. If not, throws
    * a <code>PreconditionFailedException</code>.
    * 
    * @see liquibase.precondition.Precondition#check(liquibase.database.Database,
    *      liquibase.changelog.DatabaseChangeLog, liquibase.changelog.ChangeSet)
    */
   public void check(Database database, DatabaseChangeLog changeLog,
         ChangeSet changeSet) throws PreconditionFailedException,
         PreconditionErrorException {
      if (!database.supportsAutoIncrement()) {
         String databaseName = getDatabaseNameAndVersion(database, changeLog);
         throw new PreconditionFailedException(
               "Auto-increment is not supported by " + databaseName, changeLog,
               this);
      }
   }
}
