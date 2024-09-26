package E2EPojo;

public class ViewOrderDetail {
	
	
	
	private Dataextract data; // This should map to the "data" field in the JSON response
    private String message;    // This should map to the "message" field in the JSON response

    // Getters and setters
    public Dataextract getData() {
        return data;
    }

    public void setData(Dataextract data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	

}
