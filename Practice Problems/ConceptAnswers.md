# Week 8 — Concept Questions (Answers)

## Question 1 — Encapsulation in a User object

`User` controls its own `email` state by keeping the field `private` and
exposing only a `changeEmail(String newEmail)` method as the way in. The
object — not the caller — decides what counts as a valid value.

Unrestricted access (a public field, or a plain setter with no checks) would
let any part of the program assign an empty string, a malformed address, or
an address already used by another account. Once that happens, every other
part of the system that trusts `email` to be a valid, unique address (login,
notifications, password resets) can silently break.

Routing every change through `changeEmail` means the format check and the
uniqueness check happen exactly once, in one place, every time — so a `User`
object can never be observed in an invalid state from outside the class.

## Question 2 — Inheritance vs. composition for report capabilities

Inheritance fits when every subtype genuinely **is a** more specific version
of the base type and shares its entire behavior — e.g. `SalesReport extends
Report` if all reports really do share the same core generation logic.

Composition fits here because `DataExport` and `DataVisualization` are
**optional, independent capabilities**, not "is-a" relationships — a report
*has* an exporter or a visualizer, it isn't one. Different reports need
different combinations (some need both, some need neither), which composition
handles naturally by holding references to small, focused
`DataExporter`/`DataVisualizer` objects.

Forcing this into a single inheritance hierarchy forces every combination
into its own subclass (`ExportableReport`, `VisualizableReport`,
`ExportableVisualizableReport`, ...) — the classic combinatorial explosion.
Adding one new optional capability multiplies the number of subclasses needed
for every existing report type, making the hierarchy rigid and hard to
extend.

## Question 3 — Abstraction, overriding, and polymorphism together

An abstract class or interface (`Notification`) declares a `send()` method
without saying how it's done. `EmailNotification`, `SMSNotification`, and
`PushNotification` each **override** `send()` with their own delivery logic.
`NotificationService` holds a reference typed as `Notification` and calls
`send()` on it — at runtime, the JVM dispatches to whichever concrete
override matches the actual object. That runtime dispatch is polymorphism,
and it's what lets `NotificationService` trigger a notification with no
`if/else` chain checking the concrete type.

Adding `InAppNotification` means writing one new class that implements
`send()`. `NotificationService` never changes: it already knows how to call
`send()` on anything that satisfies the `Notification` abstraction.

## Question 4 — Composition vs. aggregation

**Composition** fits Organization–Department: a Department has no existence
independent of its Organization, and when the Organization ceases to exist,
its Departments are dissolved with it. That's ownership plus a shared
lifecycle — the definition of composition.

**Aggregation** fits Department–Employee: an Employee belongs to a
Department but can survive it — when a Department is dissolved, its
Employees carry on and can be reassigned elsewhere. The "part" outlives the
"whole," which is exactly what aggregation (a weaker, shared-ownership
relationship) is for.

## Question 5 — Multiplicity vs. business rules

The relationship is **Student 0..\* --- 0..\* Course** (many-to-many): a
Student can enroll in many Courses, and a Course can have many Students, and
either side can currently have zero.

Multiplicity only says *how many* objects can be linked on each side — it
doesn't say anything about whether a *specific pair* can repeat. "No
duplicate enrollment for the same student-course pair" is a uniqueness
constraint on individual links, not a count of how many links can exist in
general. UML multiplicity can't express that rule on its own; it has to be
enforced elsewhere — e.g. a uniqueness check in the enrollment logic, or a
composite key/unique index in the underlying data store.
