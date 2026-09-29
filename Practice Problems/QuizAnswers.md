# Week 8 — Quiz Questions (Answers)

1. **C — Polymorphism.** `TicketProcessor` calls the same method on every event
   object without caring whether it's a concert, play, or match; each subtype
   supplies its own behavior behind that common call.

2. **C — Encapsulation.** `dateOfBirth` is a private field that can only be
   changed through `updateProfileInfo`, which validates the new value before
   it's applied.

3. **B — Author 0..\* --- 1 Book.** Every book has exactly one author, but an
   author can have written zero or many books (zero if they haven't published
   yet, or if all their books have been reassigned/unauthored).

4. **D — Composition.** CartItems have no existence or meaning outside their
   ShoppingCart; deleting the cart destroys its items — strong ownership and a
   shared lifecycle.

5. **D — Generalization.** `ProgrammingCourse` is a specialized "is-a" version
   of the general `Course` concept — the classic parent/child inheritance
   relationship in UML.

6. **C — Realization.** `PdfDocument` implements the `Printable` contract
   (an interface) rather than extending a class, so the relationship is
   realization, not generalization.

7. **B — Association.** Customer and Order are related by a transaction with
   no ownership or shared lifecycle — a plain association.

8. **C — State diagram.** The question is entirely about which states a Task
   can be in and which transitions between them are legal — that's exactly
   what a state diagram models.

9. **B — Abstraction.** The module only knows about the `generate()` method
   on the `ReportGenerator` interface, not how any concrete report type
   builds its output — it depends on an abstraction, not implementation
   details.

10. **C — Sequence diagram.** The scenario is an ordered exchange of messages
    between specific objects (user, AuthenticationService, UserRepository)
    over time — the defining use case for a sequence diagram.
