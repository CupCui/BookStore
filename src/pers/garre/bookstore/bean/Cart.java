package pers.garre.bookstore.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sun.javafx.sg.prism.web.NGWebView;

/**
 *  保存购物项的Map，key是bookId，value是CartItem  使用LinkedHashMap是为了保证购物车中添加图书的顺序 
 * 购物车中商品的总数量，通过计算得到  购物车中商品的总金额，通过计算得到
 */
// Collection to list ??
public class Cart {
	private Map<Integer, CartItem> map = new LinkedHashMap<>();
	private int totalCount;
	private double totalAmount;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(Map<Integer, CartItem> map) {
		super();
		this.map = map;
	}

	public Map<Integer, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<Integer, CartItem> map) {
		this.map = map;
	}
	
	/**
	 * 将图书添加到购物车 
	 * @param book
	 * @return last add cart book
	 */
	public void addCart(Book book) {
		CartItem cartItem = map.get(book.getId());
		// 图书已经存在列表中
		if (cartItem != null) {
			cartItem.setCount(cartItem.getCount() + 1);
		} else { 		// 图书不在列表中
			CartItem item = new CartItem();
			item.setBook(book);
			item.setCount(1);
			map.put(book.getId(), item);
		}
	}
	
	public int getCountById(String bookId) {
		CartItem cartItem = map.get(Integer.parseInt(bookId));
		if (cartItem == null) {
			return 0;
		}
		return cartItem.getCount();
	}
	
	public void deleteCartItem(int bookId) {
		map.remove(bookId);
	}
	
	public void updateCartItemCount(int bookId, int count) {
		CartItem cartItem = map.get(bookId);
		cartItem.setCount(count);
	}
	
	public void clearCart() {
		map.clear();
	}
	
	// Get cart items list ??? Question
	public List<CartItem> getCartItems() {
		Collection<CartItem> cartItems = map.values();
		List<CartItem> list = new ArrayList<>(cartItems);
		return list;
	}

	public int getTotalCount() {
		int totalCount = 0;
		for (CartItem cartItem : getCartItems()) {
			totalCount += cartItem.getCount();
		}
		return totalCount;
	}

	public double getTotalAmount() {
		BigDecimal totalAmount = new BigDecimal(0);
		for (CartItem cartItem : getCartItems()) {
			BigDecimal amount = new BigDecimal(cartItem.getAmount() + "");
			totalAmount = totalAmount.add(amount);
		}
		return totalAmount.doubleValue();
	}

	@Override
	public String toString() {
		return "Cart [map=" + map + ", totalCount=" + totalCount + ", totalAmount=" + totalAmount + "]";
	}

}
