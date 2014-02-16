package liquibase.ext.gravy.preconditions;

import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.exception.PreconditionErrorException;
import liquibase.exception.PreconditionFailedException;

/**
 * This precondition indicates if a database supports sequences.
 * 
 * @author Lonny
 */
public class SequencesSupportedPrecondition extends AbstractPrecondition {

   public SequencesSupportedPrecondition() {
      super("sequencesSupported");
   }

   /**
    * Determines if the database supports sequences. If not, throws a
    * <code>PreconditionFailedException</code>.
    * 
    * @see liquibase.precondition.Precondition#check(liquibase.database.Database,
    *      liquibase.changelog.DatabaseChangeLog, liquibase.changelog.ChangeSet)
    */
   public void check(Database database, DatabaseChangeLog changeLog,
         ChangeSet changeSet) throws PreconditionFailedException,
         PreconditionErrorException {
      if (!database.supportsSequences()) {
         String databaseName = getDatabaseNameAndVersion(database, changeLog);
         throw new PreconditionFailedException(
               "Sequences are not supported by " + databaseName, changeLog,
               this);
      }
   }
}
