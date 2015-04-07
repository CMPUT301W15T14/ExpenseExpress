package team14.expenseexpress.util;

public class SimpleSearchCommand {
	private SimpleSearchQuery query;
		/**
		 * constructor
		 * @param query item searched for
		 */
	public SimpleSearchCommand(String query) {
		super();
		this.query = new SimpleSearchQuery(query);
	}
	/**
	 * Searches feilds if they exist
	 * @param query item being searched
	 * @param fields place being searched
	 */
	public SimpleSearchCommand(String query, String[] fields) {
		super();
		throw new UnsupportedOperationException("Fields not yet implemented.");
	}
	/**
	 * 
	 * @return current query
	 */
	public SimpleSearchQuery getQuery() {
		return query;
	}
/**
 * sets current query to given query
 * @param query
 */
	public void setQuery(SimpleSearchQuery query) {
		this.query = query;
	}

	static class SimpleSearchQuery {
		private SimpleSearchQueryString query_string;
		/**
		 * contructor
		 * @param query
		 */
		public SimpleSearchQuery(String query) {
			super();
			this.query_string = new SimpleSearchQueryString(query);
		}
/**
 * not be called before setQueryString
 * @return the query string
 */
		public SimpleSearchQueryString getQueryString() {
			return query_string;
		}
/**
 * sets query_string to given parameter
 * @param queryString 
 */
		public void setQueryString(SimpleSearchQueryString queryString) {
			this.query_string = queryString;
		}

		static class SimpleSearchQueryString {
			private String query;
			/**
			 * Constructor
			 * current query is set to given query
			 * @param query
			 */
			public SimpleSearchQueryString(String query) {
				super();
				this.query = query;
			}
			/**
			 * 
			 * @return current query
			 */
			public String getQuery() {
				return query;
			}
			/**
			 * sets current Query
			 * @param query
			 */
			public void setQuery(String query) {
				this.query = query;
			}
		}
	}
}