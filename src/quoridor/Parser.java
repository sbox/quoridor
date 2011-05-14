package quoridor;


/**
 * 
 * A framework for scanning and tokenising user input
 * 
 * @author steve
 *
 */
public interface Parser {
	
	/**
	 * Checks if there is a command in the buffer
	 * @return true iff there is a command in the buffer
	 */
	public boolean hasNextToken();
	
	/**
	 * Returns the token of the current command
	 * @return the token of the current command
	 */
	public short currentToken();
	
	/**
	 * Returns the current command
	 * @return the current command
	 */
	public String currentCommand();
	
	/**
	 * Takes the current command off the buffer to expose the next
	 * command.
	 */
	public void progressToken();
	
	/**
	 * Checks if the current command has remaining arguments
	 * @return true iff the current command has remaining arguments
	 */
	public boolean hasNextArg();
	
	/**
	 * Returns the next argument from the list of given arguments
	 * and removes it from the list to expose the next argument
	 * @return the next argument from the list
	 */
	public String nextArg();
	
	/**
	 * Scans a line of input, and in the event that it is a valid 
	 * command, adds it to the command buffer and returns true
	 * @return true iff the input being scanned is a valid command
	 */
	public boolean scanLine();
	
	/**
	 * Returns the token of the next command on the buffer or scans
	 * for new input.
	 * @return the token of a command entered by the user
	 */
	public short ensureCommand();
	
	/**
	 * Checks if the current command has been given its required
	 * number of arguments
	 * @return true iff the current command has been given its
	 * required number of arguments
	 */
	public boolean hasRequiredArgs();
	
	/**
	 * Checks if the current command has been given a non-zero number
	 * of arguments that is not its required number of arguments
	 * @return true if the current command has been given a non-zero 
	 * number of arguments that is not its required number of 
	 * arguments
	 */
	public boolean hasErroneousArgs();
	
}
