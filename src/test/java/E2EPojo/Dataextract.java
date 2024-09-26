package E2EPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dataextract {

	
	
	private String _id;                 // Matches "_id" in JSON
	private String orderById;           // Matches "orderById" in JSON
    private String orderBy;             // Matches "orderBy" in JSON
    private String productOrderedId;    // Matches "productOrderedId" in JSON
    private String productName;         // Matches "productName" in JSON
    private String country;             // Matches "country" in JSON
    private String productDescription;   // Matches "productDescription" in JSON
    private String productImage;        // Matches "productImage" in JSON
    private String orderPrice;          // Matches "orderPrice" in JSON
    
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getOrderById() {
		return orderById;
	}
	public void setOrderById(String orderById) {
		this.orderById = orderById;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getProductOrderedId() {
		return productOrderedId;
	}
	public void setProductOrderedId(String productOrderedId) {
		this.productOrderedId = productOrderedId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	

	
	
	
}
