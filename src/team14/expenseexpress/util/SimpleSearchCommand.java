package team14.expenseexpress.util;

public class SimpleSearchCommand {
	private SimpleSearchQuery query;
		
	public SimpleSearchCommand(String query) {
		super();
		this.query = new SimpleSearchQuery(query);
	}

	public SimpleSearchCommand(String query, String[] fields) {
		super();
		throw new UnsupportedOperationException("Fields not yet implemented.");
	}

	public SimpleSearchQuery getQuery() {
		return query;
	}

	public void setQuery(SimpleSearchQuery query) {
		this.query = query;
	}

	static class SimpleSearchQuery {
		private SimpleSearchQueryString query_string;
		
		public SimpleSearchQuery(String query) {
			super();
			this.query_string = new SimpleSearchQueryString(query);
		}

		public SimpleSearchQueryString getQueryString() {
			return query_string;
		}

		public void setQueryString(SimpleSearchQueryString queryString) {
			this.query_string = queryString;
		}

		static class SimpleSearchQueryString {
			private String query;
			
			public SimpleSearchQueryString(String query) {
				super();
				this.query = query;
			}

			public String getQuery() {
				return query;
			}

			public void setQuery(String query) {
				this.query = query;
			}
		}
	}
}