package contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
    }

    @Test
    public void testAddContactSuccessful() {
        Contact contact = new Contact("C001", "John", "Doe", "1234567890", "123 Elm St");
        service.addContact(contact);
        assertEquals(contact, service.getContact("C001"));
    }

    @Test
    public void testAddDuplicateContactIdThrowsException() {
        Contact contact1 = new Contact("C001", "John", "Doe", "1234567890", "123 Elm St");
        Contact contact2 = new Contact("C001", "Jane", "Smith", "0987654321", "456 Oak St");
        service.addContact(contact1);

        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(contact2);
        });
    }

    @Test
    public void testAddNullContactThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(null);
        });
    }

    @Test
    public void testDeleteContactSuccessful() {
        Contact contact = new Contact("C002", "Jane", "Doe", "1234567890", "123 Elm St");
        service.addContact(contact);
        assertNotNull(service.getContact("C002"));

        service.deleteContact("C002");
        assertNull(service.getContact("C002"));
    }

    @Test
    public void testDeleteNonExistentContactThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("NONEXISTENT");
        });
    }

    @Test
    public void testDeleteNullContactIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact(null);
        });
    }

    @Test
    public void testUpdateFirstNameSuccessful() {
        Contact contact = new Contact("C003", "John", "Doe", "1234567890", "123 Elm St");
        service.addContact(contact);
        service.updateFirstName("C003", "Johnny");
        assertEquals("Johnny", service.getContact("C003").getFirstName());
    }

    @Test
    public void testUpdateLastNameSuccessful() {
        Contact contact = new Contact("C003", "John", "Doe", "1234567890", "123 Elm St");
        service.addContact(contact);
        service.updateLastName("C003", "Smith");
        assertEquals("Smith", service.getContact("C003").getLastName());
    }

    @Test
    public void testUpdatePhoneSuccessful() {
        Contact contact = new Contact("C003", "John", "Doe", "1234567890", "123 Elm St");
        service.addContact(contact);
        service.updatePhone("C003", "9876543210");
        assertEquals("9876543210", service.getContact("C003").getPhone());
    }

    @Test
    public void testUpdateAddressSuccessful() {
        Contact contact = new Contact("C003", "John", "Doe", "1234567890", "123 Elm St");
        service.addContact(contact);
        service.updateAddress("C003", "789 Pine Rd");
        assertEquals("789 Pine Rd", service.getContact("C003").getAddress());
    }

    @Test
    public void testUpdateNonExistentContactThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName("NONEXISTENT", "Test");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateLastName("NONEXISTENT", "Test");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone("NONEXISTENT", "1234567890");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateAddress("NONEXISTENT", "Test");
        });
    }

    @Test
    public void testUpdateWithNullIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName(null, "Test");
        });
    }
}
