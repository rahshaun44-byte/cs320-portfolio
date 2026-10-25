# CS 320 Software Test Automation & QA Portfolio

**Architect:** Rahshaun (Rocky) Chambers  
**Course:** SNHU CS 320: Software Test Automation & Quality Assurance  
**Instructor:** Prof. Antoun  
**Engineering Discipline:** QuantumFlex Defensive Software Architecture  

---

## 1. Executive Portfolio Overview

This repository houses verified engineering artifacts from **CS 320: Software Test Automation and Quality Assurance**. The core objective of this work is establishing an uncompromising, fail-closed software verification standard. 

By grounding application logic in strict mathematical boundary conditions, domain-driven isolation, and automated JUnit 5 test suites, every module demonstrates how proactive test engineering eliminates defect escapement and guarantees software reliability.

### Core Repository Artifacts
1. **Contact Service Module (`contact-service/`)**: Production-grade domain entity and service layer implementing fail-closed input verification, immutable entity keys, and constant-time $O(1)$ hash-indexed storage with 37 passing JUnit 5 tests.
2. **Task Service Module (`task-service/`)**: Invariant-enforced task domain entity and service layer supporting updates to name and description while strictly protecting immutable task IDs with 23 passing JUnit 5 tests.
3. **Appointment Service Module (`appointment-service/`)**: Calendar-aware appointment domain entity and service layer enforcing non-past timestamp validation and immutable appointment keys with 19 passing JUnit 5 tests.
4. **Project Two Engineering Report (`Project_Two_Summary_and_Reflections_Report.md`)**: Comprehensive technical audit evaluating automated testing strategies, boundary-value analysis, equivalence partitioning, test coverage defense, and cognitive bias mitigation across all 79 unit tests.

---

## 2. Architectural Reflections

### How can I ensure that my code, program, or software is functional and secure?

In the QuantumFlex architecture, functionality and security are treated as two sides of the same coin: **software must fail closed**. Any entity state that cannot be mathematically proven valid must be rejected before entering system memory.

To achieve this standard:
* **Defensive Domain Encapsulation**: In `Contact.java`, `Task.java`, and `Appointment.java`, entity state is kept private and unique identifiers are made strictly immutable (`final`). Validation gates run directly inside constructors and mutators. If an input is null, exceeds character thresholds, or violates format specifications, execution halts immediately with an exception. Invalid state is mathematically impossible to instantiate.
* **Exhaustive Boundary Verification**: Code is never trusted simply because it passes a happy-path scenario. Using JUnit 5, test suites systematically target precise boundary conditions: upper limits, boundary-plus-one overflow, null references, empty strings, date-in-the-past checks, and regex violations (such as non-numeric phone characters).
* **Deterministic State Invariants**: At the service layer (`ContactService.java`, `TaskService.java`, `AppointmentService.java`), in-memory hash-mapped storage provides constant-time $O(1)$ CRUD lookups and enforces strict duplicate-key rejection and missing-key fault injection. State transitions remain completely deterministic.

### How do I interpret user needs and incorporate them into a program?

Interpreting user needs requires converting abstract business expectations into unambiguous, verifiable engineering specifications:
* **Requirement Decomposition**: Every natural-language requirement—such as "contact ID must not be longer than 10 characters" or "appointment date cannot be in the past"—is decomposed into an explicit technical invariant with a corresponding automated unit test.
* **Domain-Driven Modeling**: System entities are modeled around real operational workflows. Distinguishing between immutable identity keys (which guarantee referential integrity) and mutable attributes (such as contact phone numbers or task descriptions that legitimate users update) aligns system mechanics directly with business needs.
* **Informative, Fail-Closed Error Feedback**: When invalid data is submitted, the system refuses the mutation with precise, structured feedback rather than silent corruption or unhandled runtime crashes, giving downstream interfaces clear, actionable recovery paths.

### How do I approach designing software?

My approach to software design is rooted in modularity, separation of concerns, and engineering for testability:
* **Separation of Concerns**: Domain models are responsible strictly for data integrity and invariant self-validation, while service components manage orchestration, persistence, and indexing. Decoupling the domain entity from the service registry ensures that each component has one single, well-defined responsibility.
* **Algorithmic Efficiency**: Scalability is baked into the design from day one. Utilizing in-memory hash-indexed mappings (`Map<String, T> = new HashMap<>()`) provides constant-time $O(1)$ lookups, insertions, and deletions, eliminating linear scanning bottlenecks.
* **Designing for Verifiability**: Code that is difficult to test indicates architectural debt. By keeping methods pure, reducing hidden dependencies, and ensuring predictable state transformations, testing becomes seamless, robust, and permanent.

---

## 3. Repository Architecture

```text
cs320-portfolio/
├── README.md                                  # Architectural Reflections & Portfolio Guide
├── Project_Two_Summary_and_Reflections_Report.md  # Formal QA Analysis & Engineering Report
├── .gitignore                                 # Build artifact exclusions
├── contact-service/                           # Contact Microservice (Project One Milestone)
│   ├── pom.xml                                # Apache Maven build descriptor
│   └── src/
│       ├── main/java/contact/
│       │   ├── Contact.java                   # Invariant-enforced domain entity
│       │   └── ContactService.java            # Constant-time in-memory registry
│       └── test/java/contact/
│           ├── ContactTest.java               # 25 Boundary & input validation tests
│           └── ContactServiceTest.java        # 12 Service lifecycle & CRUD tests
├── task-service/                              # Task Microservice (Project One Milestone)
│   ├── pom.xml                                # Apache Maven build descriptor
│   └── src/
│       ├── main/java/task/
│       │   ├── Task.java                      # Invariant-enforced task entity
│       │   └── TaskService.java               # Constant-time in-memory registry
│       └── test/java/task/
│           ├── TaskTest.java                  # 13 Boundary & input validation tests
│           └── TaskServiceTest.java           # 10 Service lifecycle & CRUD tests
└── appointment-service/                       # Appointment Microservice (Project One Milestone)
    ├── pom.xml                                # Apache Maven build descriptor
    └── src/
        ├── main/java/appointment/
        │   ├── Appointment.java               # Invariant-enforced appointment entity
        │   └── AppointmentService.java        # Constant-time in-memory registry
        └── test/java/appointment/
            ├── AppointmentTest.java           # 13 Boundary & date-validation tests
            └── AppointmentServiceTest.java    # 6 Service lifecycle & CRUD tests
```

---

## 4. Verification & Provenance Watermark

* **Chief Architect**: Rahshaun (Rocky) Chambers  
* **System**: QuantumFlex Sovereign Architecture & SNHU Academic Coursework  
* **Integrity Standard**: Fail-Closed Validation | Deterministic State | 100% Boundary Rigor  
* **Test Verification**: 79 / 79 Tests Passing across 3 modules (Apache Maven, JUnit 5)  
* **Status**: Complete, Verified & Submissible  

---

### Academic Integrity & GenAI Usage Acknowledgment
Local language models (cs320-tutor on loopback silicon) were utilized strictly as Socratic reviewers and structural drafting aids. All design architectures, mathematical invariants, domain implementations, and analytical reflections are my own original work in accordance with SNHU academic policies and Shapiro Library citation guidelines.
