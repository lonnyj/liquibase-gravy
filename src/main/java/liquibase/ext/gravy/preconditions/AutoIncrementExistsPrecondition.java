package liquibase.ext.gravy.preconditions;

import liquibase.CatalogAndSchema;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.exception.PreconditionErrorException;
import liquibase.exception.PreconditionFailedException;
import liquibase.exception.ValidationErrors;
import liquibase.precondition.Precondition;
import liquibase.structure.core.Table;

/**
 * @author Lonny Jacobson
 */
public class AutoIncrementExistsPrecondition extends AbstractPrecondition {
   /** The optional catalog name. */
   private String catalog;

   /** The optional schema name. */
   private String schema;

   /** The table name. */
   private String tableName;

   /**
    * @param name
    */
   public AutoIncrementExistsPrecondition() {
      super("autoIncrementExists");
   }

   public String getCatalog() {
      return catalog;
   }

   public void setCatalog(String catalog) {
      this.catalog = catalog;
   }

   public String getSchema() {
      return schema;
   }

   public void setSchema(String schema) {
      this.schema = schema;
   }

   public String getTableName() {
      return tableName;
   }

   public void setTableName(String tableName) {
      this.tableName = tableName;
   }

   @Override
   public ValidationErrors validate(Database database) {
      ValidationErrors validationErrors = new ValidationErrors();
      validationErrors.checkRequiredField("tableName", tableName);
      return validationErrors;
   }

   /**
    * @see Precondition#check(Database, DatabaseChangeLog, ChangeSet)
    */
   public void check(Database database, DatabaseChangeLog changeLog,
         ChangeSet changeSet) throws PreconditionFailedException,
         PreconditionErrorException {
      CatalogAndSchema defaultSchema = database.getDefaultSchema();
      Table table = new Table().setName(tableName);
      table.setSchema(catalog, schema);
      // Table table = database.getDefaultSchema().
   }
}
