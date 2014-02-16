package liquibase.ext.gravy.preconditions;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.exception.PreconditionFailedException;

import org.testng.annotations.Test;

/**
 * Tests {@link AutoIncrementSupportedPrecondition}.
 * 
 * @author Lonny Jacobson
 */
public class AutoIncrementSupportedPreconditionIT {
   /**
    * Tests the successful invocation of
    * {@link AutoIncrementSupportedPrecondition#check(Database, DatabaseChangeLog, ChangeSet)}
    * 
    * @throws Exception
    *            if the test fails.
    */
   @Test
   public void testCheckPass() throws Exception {
      AutoIncrementSupportedPrecondition precondition = new AutoIncrementSupportedPrecondition();

      Database database = mock(Database.class);
      when(database.supportsAutoIncrement()).thenReturn(true);
      DatabaseChangeLog changeLog = null;
      ChangeSet changeSet = null;
      precondition.check(database, changeLog, changeSet);
      verify(database).supportsAutoIncrement();
   }

   /**
    * Tests the successful invocation of
    * {@link AutoIncrementSupportedPrecondition#check(Database, DatabaseChangeLog, ChangeSet)}
    * 
    * @throws Exception
    *            if the test fails.
    */
   @Test(expectedExceptions = PreconditionFailedException.class)
   public void testCheckFail() throws Exception {
      AutoIncrementSupportedPrecondition precondition = new AutoIncrementSupportedPrecondition();

      Database database = mock(Database.class);
      when(database.supportsAutoIncrement()).thenReturn(false);
      DatabaseChangeLog changeLog = null;
      ChangeSet changeSet = null;
      try {
         precondition.check(database, changeLog, changeSet);
         fail("The call to check should have thrown an exception");
      } finally {
         verify(database).supportsAutoIncrement();
      }
   }
}
