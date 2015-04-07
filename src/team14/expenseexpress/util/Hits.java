package team14.expenseexpress.util;

import java.util.List;

/**
 * In Lab Code:  https://github.com/joshua2ua/AndroidElasticSearch  Accessed  April. 6, 2015
 * 
 * ElasticSearch Helper class.
 * 
 * @author Team 14
 *
 */

public class Hits<T> {
	private int total;
	private float max_score;
	private List<SearchHit<T>> hits;
	
	public Hits() {}
	/**
	 * gets the total score
	 * @return total score of type int
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * sets instance of total to given total
	 * @param total
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * Gets max score
	 * @return max score of type Float
	 */
	public float getMax_score() {
		return max_score;
	}
	/**
	 * Sets max score to given max score
	 * @param max_score 
	 */
	public void setMax_score(float max_score) {
		this.max_score = max_score;
	}
	/**
	 * Get List of all searched hits
	 * @return hits of type List
	 */
	public List<SearchHit<T>> getHits() {
		return hits;
	}
	/**
	 * Sets list of hits to given hits
	 * @param hits
	 */
	public void setHits(List<SearchHit<T>> hits) {
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "Hits [total=" + total + ", max_score=" + max_score + ", hits="
				+ hits + "]";
	}
}