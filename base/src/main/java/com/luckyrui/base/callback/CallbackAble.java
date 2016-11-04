package com.luckyrui.base.callback;

/**
 * 回调接口
 * @param <T>
 * @author chenrui
 * @date 2016年11月3日上午10:59:30
 * @version 201611
 */
public interface CallbackAble<T> {
	/**
	 * 回调方法
	 * @param data
	 * @author chenrui
	 * @date 2016年11月3日 上午10:59:45
	 * @version 201611
	 */
	void call(T data);
}
