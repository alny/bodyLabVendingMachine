package Model;

public class CityZip {

	private int id;
	private String zipCode;
	private String city;

	public CityZip(int id, String zipCode, String city) {
		this.id = id;
		this.zipCode = zipCode;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
