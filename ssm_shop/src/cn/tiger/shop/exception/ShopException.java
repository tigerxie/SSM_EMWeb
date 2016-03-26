package cn.tiger.shop.exception;

/**
 * 系统自定义异常类，针对预期的异常，需要在程序中抛出此类的异常
 * @author tiger
 *
 */
public class ShopException extends Exception {

	//异常信息
	public String message;

	public ShopException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
