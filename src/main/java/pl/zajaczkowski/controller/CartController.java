package pl.zajaczkowski.controller;


public class CartController {

//	@PutMapping("/addTomato")
//	public void addTomato()
/*	 @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	    @ResponseStatus(value = HttpStatus.NO_CONTENT)
	    public void addItem (@PathVariable(value = "productId") int productId, @AuthenticationPrincipal User activeUser){
	        Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
	        Cart cart = customer.getCart();
	        Product product = productService.getProductById(productId);
	        List<CartItem> cartItems = cart.getCartItems();

	        for (int i=0; i < cartItems.size(); i++){
	            if(product.getProductId() == cartItems.get(i).getProduct().getProductId()){
	                CartItem cartItem = cartItems.get(i);
	                cartItem.setQuantity(cartItem.getQuantity() + 1);
	                cartItem.setTotalPrice(product.getProductPrice()*cartItem.getQuantity());
	                cartItemService.addCartItem(cartItem);

	                return;
	            }
	        }

	        CartItem cartItem = new CartItem();
	        cartItem.setProduct(product);
	        cartItem.setQuantity(1);
	        cartItem.setTotalPrice(product.getProductPrice()*cartItem.getQuantity());
	        cartItem.setCart(cart);
	        cartItemService.addCartItem(cartItem);
	    }*/
}
