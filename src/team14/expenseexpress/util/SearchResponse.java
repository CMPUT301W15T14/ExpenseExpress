package team14.expenseexpress.util;

/**
 * In Lab Code:  https://github.com/joshua2ua/AndroidElasticSearch  Accessed  April. 6, 2015
 * 
 * ElasticSearch Helper class.
 * 
 * @author Team 14
 *
 */

public class SearchResponse<T> {

	private int took;
	private boolean timed_out;
	private Shard _shards;
	private Hits<T> hits;
	
	public SearchResponse() {}
/**
 * 
 * @return took of type int
 */
	public int getTook() {
		return took;
	}
/**
 * sets took to given took
 * @param took
 */
	public void setTook(int took) {
		this.took = took;
	}
/**
 * Returns True if timed out, False otherwise
 * @return timed_out of type Boolean
 */
	public boolean isTimed_out() {
		return timed_out;
	}
/**
 * 
 * set timed_out to True or False
 * @param timed_out
 */
	public void setTimed_out(boolean timed_out) {
		this.timed_out = timed_out;
	}
/**
 * 
 * @return _shards of type Shard
 */
	public Shard get_shards() {
		return _shards;
	}
/**
 * sets _shards to parameter
 * @param _shards
 */
	public void set_shards(Shard _shards) {
		this._shards = _shards;
	}
/**
 * 
 * @return hits of type Hits, containing T
 */
	public Hits<T> getHits() {
		return hits;
	}
/**
 * sets hits to given parameter
 * @param hits
 */
	public void setHits(Hits<T> hits) {
		this.hits = hits;
	}	   
}

	

class Shard {
	private int total;
	private int successful;
	private int failed;
	
	public Shard() {}
	/**
	 * 
	 * @return total Shard
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * sets total for shard
	 * @param total
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * gets value of successful
	 * @return
	 */
	public int getSuccessful() {
		return successful;
	}
	/**
	 * sets value of successful for shard
	 * @param successful
	 */
	public void setSuccessful(int successful) {
		this.successful = successful;
	}
	/**
	 * get value of failed for shard
	 * @return
	 */
	public int getFailed() {
		return failed;
	}
	/**
	 * sets value of failed for shard
	 * @param failed
	 */
	public void setFailed(int failed) {
		this.failed = failed;
	}
}

