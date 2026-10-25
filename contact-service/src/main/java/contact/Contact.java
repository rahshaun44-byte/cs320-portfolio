package contact;

public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("Invalid contact ID: cannot be null and must be 10 characters or less.");
        }
        validateFirstName(firstName);
        validateLastName(lastName);
        validatePhone(phone);
        validateAddress(address);

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        validateFirstName(firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }

    private void validateFirstName(String name) {
        if (name == null || name.length() > 10) {
            throw new IllegalArgumentException("Invalid first name: cannot be null and must be 10 characters or less.");
        }
    }

    private void validateLastName(String name) {
        if (name == null || name.length() > 10) {
            throw new IllegalArgumentException("Invalid last name: cannot be null and must be 10 characters or less.");
        }
    }

    private void validatePhone(String phoneNum) {
        if (phoneNum == null || phoneNum.length() != 10 || !phoneNum.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid phone number: must be exactly 10 numeric digits.");
        }
    }

    private void validateAddress(String addr) {
        if (addr == null || addr.length() > 30) {
            throw new IllegalArgumentException("Invalid address: cannot be null and must be 30 characters or less.");
        }
    }
}
