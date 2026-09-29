# Week 8 — Practice Problems (OOP System Design, Category B)

5 problems solved in Java, each as a single self-contained `.java` file under `Practice Problems/`, plus the quiz and concept-question answers as markdown.

| # | Title | Key Concepts |
|---|-------|---------------|
| 1 | Vehicle Rental System | Abstract class for per-category pricing rules, encapsulated availability status, open for new vehicle categories |
| 2 | Employee Leave Request Workflow | Abstract class per employee type with its own leave-limit rule, state-guarded status transitions (Pending → Approved/Rejected only) |
| 3 | Online Examination System | Abstract `Question` type with self-evaluation, submitted-state lockout, polymorphic scoring across question types |
| 4 | Hotel Booking System | Abstract `Room` pricing rule, overlap-checked reservations, cancellation-deadline enforcement |
| 5 | Payment Processing for a Shopping System | `PaymentMethod` interface, order status changes only on successful payment, new payment methods add without touching order logic |

## Quiz & Concept Questions

- `QuizAnswers.md` — 10 multiple-choice questions on OOP/UML concepts (polymorphism, encapsulation, multiplicity, composition vs. aggregation, generalization, realization, state/sequence diagrams, abstraction), with justification for each answer.
- `ConceptAnswers.md` — 5 short-answer questions on encapsulation, inheritance vs. composition, abstraction + polymorphism, composition vs. aggregation, and UML multiplicity vs. business rules.
