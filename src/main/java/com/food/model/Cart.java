package com.food.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	Map<Integer, CartItem> items;
	
	public Cart() {
		items = new HashMap<Integer, CartItem>();
	}
	
	public void addItem(CartItem newItem) {
		if(items.containsKey(newItem.getItemId())) {
			newItem.setQuantity(items.get(newItem.getItemId()).getQuantity() + newItem.getQuantity());
			items.put(newItem.getItemId(), newItem);
			
		}else {
			items.put(newItem.getItemId(), newItem);
		}
	}
	
	public void updateItem(int itemId, int quantity) {
		if(items.containsKey(itemId)) {
			if(quantity <= 0) {
				items.remove(itemId);
			}else {
				items.get(itemId).setQuantity(quantity);
			}
		}
	}
	
	public void removeItem(int itemId) {
		items.remove(itemId);
	}
	
	public Map<Integer, CartItem> getItems(){
		return items;
	}
	
	public void clear() {
		items.clear();;
	}
}
