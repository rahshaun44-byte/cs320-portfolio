package contact;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    public void testValidContactCreation() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St, Anytown USA");
        assertEquals("1234567890", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main St, Anytown USA", contact.getAddress());
    }

    @Test
    public void testContactIdNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testContactIdTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testFirstNameNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", null, "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testFirstNameTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "MoreThanTenChars", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testLastNameNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", null, "1234567890", "123 Main St");
        });
    }

    @Test
    public void testLastNameTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "MoreThanTenChars", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testPhoneNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", null, "123 Main St");
        });
    }

    @Test
    public void testPhoneTooShortThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "123456789", "123 Main St");
        });
    }

    @Test
    public void testPhoneTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "12345678901", "123 Main St");
        });
    }

    @Test
    public void testPhoneNonDigitsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "12345abc90", "123 Main St");
        });
    }

    @Test
    public void testAddressNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "1234567890", null);
        });
    }

    @Test
    public void testAddressTooLongThrowsException() {
        String longAddress = "This address is definitely longer than thirty characters!";
        assertTrue(longAddress.length() > 30);
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Doe", "1234567890", longAddress);
        });
    }

    @Test
    public void testSetFirstNameValid() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contact.setFirstName("Jane");
        assertEquals("Jane", contact.getFirstName());
    }

    @Test
    public void testSetFirstNameNullThrowsException() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName(null);
        });
    }

    @Test
    public void testSetFirstNameTooLongThrowsException() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName("MoreThanTenChars");
        });
    }

    @Test
    public void testSetLastNameValid() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contact.setLastName("Smith");
        assertEquals("Smith", contact.getLastName());
    }

    @Test
    public void testSetLastNameNullThrowsException() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName(null);
        });
    }

    @Test
    public void testSetLastNameTooLongThrowsException() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName("MoreThanTenChars");
        });
    }

    @Test
    public void testSetPhoneValid() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contact.setPhone("0987654321");
        assertEquals("0987654321", contact.getPhone());
    }

    @Test
    public void testSetPhoneNullThrowsException() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone(null);
        });
    }

    @Test
    public void testSetPhoneInvalidThrowsException() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("12345");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("abcdefghij");
        });
    }

    @Test
    public void testSetAddressValid() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contact.setAddress("456 Oak Ave");
        assertEquals("456 Oak Ave", contact.getAddress());
    }

    @Test
    public void testSetAddressNullThrowsException() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress(null);
        });
    }

    @Test
    public void testSetAddressTooLongThrowsException() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        String longAddress = "This address is definitely longer than thirty characters!";
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress(longAddress);
        });
    }
}
