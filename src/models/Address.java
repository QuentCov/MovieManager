package models;

public class Address {
	private String address1;
	private String address2;
	private String city;
	private String stateAbbreviation;
	private String zipCode;

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateAbbreviation() {
		return stateAbbreviation;
	}

	public void setStateAbbreviation(String stateAbbreviation) {
		this.stateAbbreviation = stateAbbreviation;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

    private boolean isNotNullAndEmpty(String string) {
        if(string != null && !string.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    // checks if the address is correct
    public boolean isValidAddress() {
        if(isNotNullAndEmpty(this.address1) &&
        isNotNullAndEmpty(this.city) &&
        isNotNullAndEmpty(this.stateAbbreviation) &&
        isNotNullAndEmpty(this.zipCode) &&
        isValidZipCodeFormat()) {
            return true;
        } else {
            return false;
        }
    }

	// checks if the zip code is correctly formatted
	public boolean isValidZipCodeFormat() {
        String pattern= "d{5}";
        return this.zipCode.matches(pattern);
	}
}
