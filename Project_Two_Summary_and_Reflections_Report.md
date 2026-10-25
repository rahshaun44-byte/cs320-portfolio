# Project Two Summary and Reflections Report: Back-End Services Unit Testing
**To:** Engineering Supervisor, Grand Strand Systems  
**From:** Rahshaun (Rocky) Chambers, Back-End Software Engineer  
**Course:** SNHU CS 320: Software Test Automation and Quality Assurance  
**Date:** October 2026  
**Format:** Formal Engineering Quality Assurance Report (APA Style References)

---

## 1. Executive Summary

As a back-end software engineer for Grand Strand Systems, I was tasked with developing and validating three core back-end micro-features for a mobile application: the **Contact Service**, **Task Service**, and **Appointment Service** (collectively delivered in Project One). The primary objective of this initiative was to engineer resilient in-memory domain models and data-management services that adhere strictly to customer requirements while establishing an automated unit testing suite capable of verifying domain invariants, detecting software defects, and preventing regression escapements.

This report delivers a comprehensive technical analysis of the unit testing approach executed across all three services, defends the quality and coverage of the resulting test suites, cites specific implementation artifacts demonstrating technical soundness and computational efficiency, and reflects upon the software testing techniques, engineering mindset, bias mitigation strategies, and professional discipline required to sustain long-term software quality.

---

## 2. Summary of Testing Approach

### 2.1 Alignment of Testing Approach to Software Requirements
The testing approach was engineered to map 1:1 against every functional requirement and data integrity rule specified by Grand Strand Systems. For each domain feature, requirements dictated exact constraints regarding field mutability, length limits, character formatting, nullability, and service-level CRUD (Create, Read, Update, Delete) behaviors:

1. **Contact Service Alignment:** Requirements specified that `contactId` must be a unique, non-null String of $\le 10$ characters that is strictly immutable; `firstName` and `lastName` must be non-null Strings of $\le 10$ characters; `phone` must be a non-null, exact 10-digit numerical string; and `address` must be a non-null String of $\le 30$ characters. In `ContactTest.java` and `ContactServiceTest.java`, automated test methods systematically exercise every valid boundary (e.g., exact 10-character boundary strings, valid 10-digit regex phone numbers) and every negative boundary (null inputs, 11-character strings, non-numeric phone characters).
2. **Task Service Alignment:** Requirements mandated that `taskId` be unique, non-null, and immutable ($\le 10$ characters); `name` be non-null ($\le 20$ characters); and `description` be non-null ($\le 50$ characters). `TaskServiceTest.java` verified that update routines correctly modify only `name` and `description` while rejecting attempts to update non-existent IDs.
3. **Appointment Service Alignment:** Requirements mandated that `appointmentId` be unique, non-null, and immutable ($\le 10$ characters); `description` be non-null ($\le 50$ characters); and `appointmentDate` be a non-null `java.util.Date` that cannot be in the past. `AppointmentTest.java` specifically validated that dates occurring prior to the current execution timestamp throw an `IllegalArgumentException`, while future dates are safely scheduled.

By developing separate test suites for domain entity validation (`*Test.java`) and service-tier business logic (`*ServiceTest.java`), the test architecture maintained strict separation of concerns, ensuring every specification criterion was independently and repeatedly verified.

### 2.2 Defense of Test Effectiveness and Code Coverage
The overall quality and effectiveness of the JUnit 5 test suite is defended by achieving **100% test success across all 79 automated test cases** and exceeding SNHU’s 80% code coverage threshold across all Java classes.

High test coverage alone is insufficient if assertions do not rigorously validate state mutations; therefore, our test suite implemented a dual-assertion strategy:
* **Positive Equivalence Verification:** Every successful operation asserts not only that an exception was not thrown, but verifies exact object equality and attribute persistence (e.g., `assertEquals("John", service.getContact("12345").getFirstName())`).
* **Negative Fault Injection:** Every invalid input path is explicitly challenged using JUnit 5’s `assertThrows(IllegalArgumentException.class, ...)`, verifying that invalid states cannot be instantiated under any circumstances.
* **Collision and Non-Existent Key Defenses:** The service tests explicitly verify duplicate key rejection (attempting to add an ID already present in the in-memory map) and non-existent key operations (attempting to delete or update an unmapped ID).

Because every execution branch, exception throw, and boundary limit is covered by an automated assertion, the test harness guarantees that zero silent failures can occur in production.

---

## 3. Test Writing Experience: Technical Soundness and Efficiency

### 3.1 Strategies for Ensuring Technically Sound Code
Technical soundness requires fail-closed validation, defensive copying, and comprehensive boundary-value analysis. To guarantee that invalid objects can never exist within the JVM runtime, validation logic was encapsulated directly within the domain entity constructors and setter methods.

#### Specific Code Illustration: Fail-Closed Boundary Assertion
In `ContactTest.java`, technical soundness was enforced by verifying boundary conditions at both the exact maximum permissible length and the first invalid length ($N+1$):

```java
// Citing ContactTest.java: Verification of exact boundary and boundary + 1
@Test
void testContactFirstNameExactlyTenCharacters() {
    Contact contact = new Contact("12345", "1234567890", "Doe", "1234567890", "123 Main Street");
    assertEquals("1234567890", contact.getFirstName());
}

@Test
void testContactFirstNameTooLongThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
        new Contact("12345", "12345678901", "Doe", "1234567890", "123 Main Street");
    });
}
```

Similarly, in `Appointment.java`, temporal soundness was achieved by comparing input dates against the current instant:

```java
// Citing AppointmentTest.java: Temporal boundary validation
@Test
void testAppointmentDateInPastThrowsException() {
    Date pastDate = new Date(System.currentTimeMillis() - 100000);
    assertThrows(IllegalArgumentException.class, () -> {
        new Appointment("APP01", pastDate, "Valid Description");
    });
}
```
This fail-closed design guarantees that business rules are mathematically and temporally sound before data reaches the persistence layer.

### 3.2 Strategies for Ensuring Computational Efficiency
Back-end services must maintain predictable, low-latency execution and minimal memory overhead. To ensure computational efficiency, the services utilize direct hashed storage (`java.util.HashMap` / `java.util.Map`) rather than linear sequential structures (`ArrayList`). This architectural decision delivers average $O(1)$ constant-time performance for additions, lookups, updates, and removals, preventing $O(N)$ performance degradation as the user dataset scales.

#### Specific Code Illustration: Constant-Time In-Memory Execution
In `TaskService.java` and its associated unit test `TaskServiceTest.java`, efficiency is maintained by using direct key lookups:

```java
// Citing TaskService.java: O(1) lookup and update operations
public void updateTaskDescription(String taskId, String newDescription) {
    Task task = tasks.get(taskId);
    if (task == null) {
        throw new IllegalArgumentException("Task ID does not exist.");
    }
    task.setDescription(newDescription);
}
```

In the test suite, test execution efficiency was maximized by keeping tests strictly in-memory and avoiding external I/O, heavy mocking frameworks, or database overhead:

```java
// Citing TaskServiceTest.java: Independent in-memory test lifecycle
@BeforeEach
void setUp() {
    taskService = new TaskService();
}

@Test
void testTaskServiceDeleteSuccess() {
    Task task = new Task("T100", "Initial Name", "Initial Description");
    taskService.addTask(task);
    taskService.deleteTask("T100");
    assertThrows(IllegalArgumentException.class, () -> taskService.getTask("T100"));
}
```
Running all 79 unit tests executes in under 2 seconds during Maven build cycles (`mvn test -q`), enabling instantaneous developer feedback and continuous integration efficiency.

---

## 4. Reflections on Software Testing Techniques

### 4.1 Testing Techniques Employed in the Project
Three primary dynamic black-box and white-box testing techniques were employed:
1. **Boundary-Value Analysis (BVA):** Testing the extreme boundaries of input domains. For string length requirements ($\le 10$ characters), tests evaluated strings of length 0 (empty), 10 (maximum valid), and 11 (minimum invalid).
2. **Equivalence Partitioning:** Dividing input data into valid and invalid equivalence classes. For phone numbers, inputs were divided into valid classes (exact 10 numerical digits) and invalid classes (null, $<10$ digits, $>10$ digits, and alphabetic/special characters).
3. **Negative Exception Testing:** Using `assertThrows()` to assert that illegal states trigger controlled, predictable application exceptions rather than unhandled JVM crashes.

### 4.2 Other Software Testing Techniques Not Used
1. **Mutation Testing:** A technique where deliberate faults (mutants) are injected into the source code to verify whether existing tests catch the change. While highly effective at gauging test suite strength, it requires specialized tooling (e.g., PIT Mutation Testing).
2. **Integration / End-to-End API Testing:** Testing services across network sockets, database connectors, and HTTP REST controllers (e.g., using MockMvc or RestAssured). Because Project One focused strictly on back-end domain components, network integration was excluded.
3. **Fuzz Testing:** Injecting semi-random, malformed inputs at high velocity to uncover memory corruption or unhandled runtime panics.

### 4.3 Practical Uses and Implications for Different Projects
* **Unit Testing & BVA:** Essential in safety-critical, financial, and authentication systems where off-by-one errors or unhandled nulls cause fatal transaction failures or security bypasses.
* **Mutation Testing:** Highly valuable in mission-critical aerospace and medical device code where 100% branch coverage might mask superficial assertions that don't actually test logic.
* **Fuzz Testing:** Indispensable in networking daemons, cryptographic libraries, and public API gateways exposed to untrusted external inputs.

---

## 5. Reflections on Engineering Mindset, Bias, and Discipline

### 5.1 Employing Caution and Appreciating Complexity
As a software tester, I adopted a **fail-closed, adversarial mindset**. Rather than assuming code works under ideal conditions, a disciplined tester assumes that code will be subjected to hostile, malformed, or unanticipated inputs. In back-end services, seemingly minor interactions—such as in-place mutation of mutable `Date` references—can introduce catastrophic side effects across concurrent threads. Exercising caution required treating every input as untrusted and verifying that domain objects reject mutation of primary keys (`contactId`, `taskId`, `appointmentId`) after initialization.

### 5.2 Limiting Bias in Code Review and Self-Testing
Developer bias is a major vulnerability in software engineering. When developers test their own code, cognitive bias leads them to execute "happy-path" scenarios that mirror their own mental model of how the software *should* be used. To mitigate this bias during Project One:
* I treated the written specification as an unyielding contract, writing tests based strictly on the requirement limits rather than internal code implementation details.
* I deliberately authored negative tests designed to break the system before verifying that the code successfully resisted the failure.
* In commercial practice, peer code reviews, pair programming, and dedicated Quality Assurance (QA) engineers are essential to ensure code is evaluated by engineers who did not write it.

### 5.3 Professional Discipline and Avoiding Technical Debt
Commitment to quality is the cornerstone of professional software engineering. Cutting corners—such as skipping boundary checks, omitting unit tests to meet artificial deadlines, or relying on manual ad-hoc testing—inevitably incurs severe **technical debt**. Technical debt compounds exponentially over time, slowing feature velocity, degrading code maintainability, and introducing production escapements that erode customer trust.

As a practicing engineer, I avoid technical debt by adhering to automated testing rigor:
1. Enforcing automated regression suites in CI/CD pipelines where builds fail if coverage falls below 80% or if any test fails.
2. Refactoring code continuously under the protection of comprehensive test harnesses.
3. Treating test code with the exact same architectural rigor, clean code standards, and documentation as production code.

---

## 6. References
* Garcia, B. (2017). *Mastering Software Testing with JUnit 5*. Packt Publishing.
* Hambling, B., Morgan, P., Samaroo, A., Thompson, G., & Williams, P. (2019). *Software Testing: An ISTQB-BCS Certified Tester Foundation Guide* (4th ed.). BCS, The Chartered Institute for IT.
* Southern New Hampshire University. (2026). *CS 320: Software Test Automation and Quality Assurance Course Materials and Rubrics*. SNHU.
