/*****************************************************************************
      List of Classes:
        Query - parent of XMlQuery and SoapQuery
*****************************************************************************/

import java.lang.*;
import java.util.*;

public abstract class Query {
	protected final int MAX_ITEMS_PER_PAGE = 25;

	protected String serverURL, associatesID, token, searchType, type, page, offer;
	protected Vector searchValues;

/*****************************************************************************
      Method name: generateMultipleSearchString
      Function: used in AsinSearch only (since AsinSearch allows multiple searches)
                add ',' in between each Asin number
      Input Parameters: search type (String)
                        asin numbers (Vector)
      Return Values: one string containing all asin numbers (String)
*****************************************************************************/
	protected String generateMultipleSearchString(String sesarchType, Vector _searchValues) {
		StringBuffer resultString = new StringBuffer();
		if (searchType.equals("AsinSearch") && !_searchValues.isEmpty()) {
			resultString.append(_searchValues.elementAt(0));
			for(int i = 1; i < _searchValues.size(); i++) {
				resultString.append(",");
				resultString.append(_searchValues.elementAt(i));
			}
		}
		// other search methods don't allow multiple entries
		else {
			resultString.append(_searchValues.elementAt(0));
		}
		return new String(resultString);
	}

	public void setServerURL(String _serverURL) {
		serverURL = _serverURL;
	}
	public void setAssociatesID(String _associatesID) {
		associatesID = _associatesID;
	}
	public void setToken(String _token) {
		token = _token;
	}
	public void setSearchType(String _searchType) {
		searchType = _searchType;
	}
	public void setSearchValues(Vector _searchValues) {
		searchValues = _searchValues;
	}
	public void setType(String _type) {
		type = _type;
	}
	public void setPage(String _page) {
		page = _page;
	}
	public void setOffer(String _offer) {
		offer = _offer;
	}
}