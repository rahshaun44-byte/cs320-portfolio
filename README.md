# CS 320 Module Eight Journal & Portfolio Reflection
**Author:** Rocky (Rahshaun Chambers)  
**Course:** SNHU CS 320: Software Test Automation & QA  
**Instructor:** Prof. Antoun  
**Repository Artifacts:** Contact Service Source & Tests, Project Two Summary and Reflections Report

---

## 1. Professional Portfolio README & Self-Assessment

### Introduction
This repository showcases core artifacts from **CS 320: Software Test Automation and Quality Assurance**, demonstrating competencies in automated unit testing, test-driven validation, defensive software engineering, and formal quality analysis. 

The portfolio includes:
1. **Contact Service Module:** Full implementation and test suite demonstrating fail-closed boundary validation, input sanitization, and $O(1)$ CRUD service management ([`Contact.java`](file:///home/quant/Documents/CS320/contact-service/src/main/java/contact/Contact.java), [`ContactService.java`](file:///home/quant/Documents/CS320/contact-service/src/main/java/contact/ContactService.java), [`ContactTest.java`](file:///home/quant/Documents/CS320/contact-service/src/test/java/contact/ContactTest.java), [`ContactServiceTest.java`](file:///home/quant/Documents/CS320/contact-service/src/test/java/contact/ContactServiceTest.java)).
2. **Project Two Quality Assurance Report:** Comprehensive engineering report evaluating testing strategies, boundary-value analysis, test coverage, and bias mitigation ([`Project_Two_Summary_and_Reflections_Report.md`](file:///home/quant/Documents/CS320/Project_Two_Summary_and_Reflections_Report.md)).

---

## 2. Core Reflection Prompts

### Question 1: How can I ensure that my code, program, or software is functional and secure?
Ensuring software functionality and security begins with the premise that software must **fail closed**—meaning invalid, malformed, or unauthenticated state must be rejected before it can persist into the runtime memory or database. 

In my CS 320 work, I achieved this through three interconnected practices:
1. **Defensive Domain Encapsulation:** In [`Contact.java`](file:///home/quant/Documents/CS320/contact-service/src/main/java/contact/Contact.java), entity fields are declared `private` and unique identifiers are declared `final` (immutable). Null checks and boundary length validations are enforced directly inside the constructor and setters, preventing invalid entities from ever existing.
2. **Automated Boundary-Value Testing:** Functional correctness is verified through automated unit tests using JUnit 5. Rather than testing only happy-path scenarios, tests deliberately target boundaries ($N$ and $N+1$), null inputs, empty strings, and regex mismatches (such as testing that non-numeric characters in phone numbers throw `IllegalArgumentException`).
3. **Deterministic State Verification:** In the service layer ([`ContactService.java`](file:///home/quant/Documents/CS320/contact-service/src/main/java/contact/ContactService.java)), thread-safe memory management and strict key verification prevent race conditions, duplicate ID collisions, and silent data overwrites.

### Question 2: How do I interpret user needs and incorporate them into a program?
Interpreting user needs requires translating high-level business requirements and user stories into unambiguous technical contracts. Requirements documents often define constraints in plain English—such as *"the contact ID shall not be longer than 10 characters and shall not be null."*

To incorporate these requirements reliably:
1. **Requirement Decomposition & Traceability:** I decompose each user requirement into discrete acceptance criteria and map them 1:1 to automated unit tests before or alongside writing implementation code.
2. **Domain-Driven Modeling:** I model entities around user workflows. For instance, distinguishing between immutable attributes (a generated ID that represents system identity) and mutable attributes (first name, phone number, address that users legitimately need to update) ensures the software honors real-world operational workflows.
3. **User-Centric Error Handling:** When invalid input is supplied, throwing clear, descriptive runtime exceptions ensures that upper layers (such as UI forms or REST controllers) can present informative, actionable feedback to end users rather than crashing unexpectedly.

### Question 3: How do I approach designing software?
My approach to software design is grounded in modularity, separation of concerns, and testability:
1. **Separation of Entity and Service Concerns:** Entities should be responsible solely for domain validation and self-consistency, while service classes orchestrate storage, retrieval, and business logic. Keeping `Contact` decoupled from `ContactService` ensures each class has a single, well-defined responsibility (SRP).
2. **Algorithmic and Data Structure Efficiency:** Selecting appropriate data structures is fundamental to scalable design. In `ContactService`, utilizing an in-memory `Map<String, Contact>` provides $O(1)$ constant-time lookups, insertions, and deletions, avoiding the performance degradation of linear searches across large datasets.
3. **Designing for Verifiability:** Code that is difficult to test is usually code that is poorly designed. By structuring methods to be deterministic and self-contained with minimal hidden state, unit testing becomes straightforward, reliable, and maintainable over the long term.

---

## 3. Summary of Portfolio Repository Structure

```
├── contact-service/
│   ├── pom.xml
│   └── src/
│       ├── main/java/contact/
│       │   ├── Contact.java
│       │   └── ContactService.java
│       └── test/java/contact/
│           ├── ContactTest.java
│           └── ContactServiceTest.java
├── Project_Two_Summary_and_Reflections_Report.md
└── README.md (This Document)
```

---

### SNHU GenAI Usage Acknowledgment
I acknowledge that local Ollama (`cs320-tutor:latest` / Qwen 2.5 Coder 7B running privately on localhost) was used as an academic proofreading and structural drafting aid, while all analysis, core ideas, and coding perspectives are my own in accordance with SNHU Shapiro Library guidelines.
