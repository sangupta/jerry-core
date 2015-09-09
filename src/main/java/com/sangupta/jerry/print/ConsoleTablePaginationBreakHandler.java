package com.sangupta.jerry.print;

/**
 * A handler that gets called when we pause for pagination
 * to let developers decide if we want to move ahead or not.
 * 
 * @author sangupta
 *
 */
public interface ConsoleTablePaginationBreakHandler {

	public boolean continuePagination(ConsoleTable table);
	
}
