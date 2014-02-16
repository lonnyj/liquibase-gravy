package liquibase.ext.gravy.preconditions;

import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.exception.PreconditionErrorException;
import liquibase.exception.PreconditionFailedException;

/**
 * This precondition indicates if a database supports tablespaces.
 * 
 * @author Lonny
 */
public class TablespacesSupportedPrecondition extends AbstractPrecondition {

   public TablespacesSupportedPrecondition() {
      super("tablespacesSupported");
   }

   /**
    * Determines if the database supports tablespaces. If not, throws a
    * <code>PreconditionFailedException</code>.
    * 
    * @see liquibase.precondition.Precondition#check(liquibase.database.Database,
    *      liquibase.changelog.DatabaseChangeLog, liquibase.changelog.ChangeSet)
    */
   public void check(Database database, DatabaseChangeLog changeLog,
         ChangeSet changeSet) throws PreconditionFailedException,
         PreconditionErrorException {
      if (!database.supportsTablespaces()) {
         String databaseName = getDatabaseNameAndVersion(database, changeLog);
         throw new PreconditionFailedException(
               "Tablespaces are not supported by " + databaseName, changeLog,
               this);
      }
   }
}
