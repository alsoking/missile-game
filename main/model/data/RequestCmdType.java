package yw29_zl58.main.model.data;

import common.datatype.*;


/**
 * RequestCmdType
 * @author admin
 *
 */
public class RequestCmdType implements IRequestCmdType {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	Class<?> id;

	/**
	 * constructor
	 * @param id the class
	 */
	public RequestCmdType(Class<?> id) {
		this.id = id;
	}

	/**
	 * get cmd id
	 * @return id
	 */
	@Override
	public Class<?> getCmdId() {
		// TODO Auto-generated method stub
		return id;
	}

}
