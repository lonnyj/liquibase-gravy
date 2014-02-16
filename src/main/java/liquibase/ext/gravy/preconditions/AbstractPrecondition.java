package liquibase.ext.gravy.preconditions;

import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.exception.DatabaseException;
import liquibase.exception.PreconditionErrorException;
import liquibase.exception.ValidationErrors;
import liquibase.exception.Warnings;
import liquibase.precondition.Precondition;

/**
 * This class provides a minimal implementation of {@link Precondition}.
 * Sub-classes are encouraged to override {@link #warn(Database)} and
 * {@link #validate(Database)} if applicable.
 * 
 * @author Lonny
 */
public abstract class AbstractPrecondition implements Precondition {
   /** The name of the precondition. */
   private String name;

   /**
    * Constructs a new <code>AbstractPrecondition</code>.
    * 
    * @param name
    *           the name used in XML to refer to the precondition.
    */
   public AbstractPrecondition(String name) {
      if (name == null) {
         throw new IllegalArgumentException("The name cannot be null");
      }
      this.name = name;
   }

   /**
    * Returns the name used in XML to refer to the precondition.
    * 
    * @see Precondition#getName()
    */
   public final String getName() {
      return name;
   }

   /**
    * Default implementation is a no-op.
    * 
    * @see Precondition#warn(Database)
    */
   public Warnings warn(Database database) {
      return null;
   }

   /**
    * Default implementation is a no-op.
    * 
    * @see liquibase.precondition.Precondition#validate(liquibase.database.Database)
    */
   public ValidationErrors validate(Database database) {
      return null;
   }

   /**
    * Generates a string containing the database name and version.
    * 
    * @param database
    *           the database object to interrogate.
    * @param changeLog
    *           the current database change log to use if an exception is
    *           thrown.
    * @return the database name and version string.
    * @throws PreconditionErrorException
    *            if an error occurs while fetching the database version.
    */
   protected String getDatabaseNameAndVersion(Database database,
         DatabaseChangeLog changeLog) throws PreconditionErrorException {
      String databaseName;
      try {
         databaseName = database.getDatabaseProductName() + ' '
               + database.getDatabaseMajorVersion() + '.'
               + database.getDatabaseMinorVersion();
      } catch (DatabaseException e) {
         throw new PreconditionErrorException(e, changeLog, this);
      }
      return databaseName;
   }
}
