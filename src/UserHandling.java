import java.io.Serializable;
import java.sql.ResultSet;


public class UserHandling implements Serializable{
    private boolean select, execute = false;
    private static final long serialVersionUID = 1L;
    private String sqlStatement;
    private ResultSet resultSet;

    public UserHandling(){

    }

    /**
     * Set SQL statement string
     * @param sqlStatement
     */
    public void setSqlStatement(String sqlStatement){
        this.sqlStatement = sqlStatement;
    }

    /**
     * Returns an SQL statement
     * @return
     */
    public String getSqlStatement(){
        return this.sqlStatement;
    }

    /**
     * Set Select boolean
     * @param select
     */
    public void setSelect(boolean select){
        this.select = select;
    }

    /**
     * Get Select boolean
     * @return
     */
    public boolean getSelect(){
        return this.select;
    }

    /**
     * Set Execute boolean
     * @param execute
     */
    public void setExecute(boolean execute){
        this.execute = execute;
    }

    /**
     * Get Execute boolean
     * @return
     */
    public boolean getExecute(){
        return this.execute;
    }

    /**
     * Set resultset
     * @return
     */
    public void setResultSet(ResultSet resultSet){
        this.resultSet = resultSet;
    }

    /**
     * Returns a resultset
     * @return
     */
    public ResultSet getResultSet(){
        return this.resultSet;
    }
}
