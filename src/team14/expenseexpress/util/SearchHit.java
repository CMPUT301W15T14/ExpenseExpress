package team14.expenseexpress.util;

/**
 * In Lab Code:  https://github.com/joshua2ua/AndroidElasticSearch  Accessed  April. 6, 2015
 * 
 * ElasticSearch Helper class.
 * 
 * @author Team 14
 *
 */

public class SearchHit<T> {
	private String _index;
	private String _type;
	private String _id;
	private String _version;
	private boolean found;
	private T _source;
	/**
	 * empty constructor
	 */
	public SearchHit() {

	}
	/**
	 * gets value of index
	 * @return index of type String
	 */
	public String get_index() {
		return _index;
	}
	/**
	 * Makes current index = given index
	 * @param _index
	 */
	public void set_index(String _index) {
		this._index = _index;
	}
	/**
	 * 
	 * @return _type of type String
	 */
	public String get_type() {
		return _type;
	}
	/**
	 * Sets current typ to given type
	 * @param _type
	 */
	public void set_type(String _type) {
		this._type = _type;
	}
	/**
	 * 
	 * @return _id of type String
	 */
	public String get_id() {
		return _id;
	}
/**
 * sets current _id to given _id
 * @param _id
 */
	public void set_id(String _id) {
		this._id = _id;
	}
/**
 * 
 * @return _version of type String
 */
	public String get_version() {
		return _version;
	}
/**
 * sets current _version to given _version
 * @param _version
 */
	public void set_version(String _version) {
		this._version = _version;
	}
/**
 * 
 * @return True if found
 */
	public boolean isFound() {
		return found;
	}
/**
 * set found to True or False
 * @param found Boolean
 */
	public void setFound(boolean found) {
		this.found = found;
	}
/**
 * 
 * @return _source of type T
 */
	public T getSource() {
		return _source;
	}
/**
 * sets _source to given source
 * @param source
 */
	public void setSource(T source) {
		this._source = source;
	}

	@Override
	public String toString() {
		return "SimpleElasticSearchResponse [_index=" + _index + ", _type="
				+ _type + ", _id=" + _id + ", _version=" + _version
				+ ", found=" + found + ", _source=" + _source + "]";
	}
	
	
}
