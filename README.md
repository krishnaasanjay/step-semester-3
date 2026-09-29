# Week 8 — Assignment (OOP System Design, Category B)

5 problems solved in Java, each as a single self-contained `.java` file under `Assignments/`.

| # | Title | Key Concepts |
|---|-------|---------------|
| 1 | The Hostel Laundry Queue | `WashType` interface, machine guards its own busy/free state, new wash types add without touching booking logic |
| 2 | The Assignment Submission Portal | Abstract `Assignment` with its own late-penalty rule, submission status locked after grading (no resubmission) |
| 3 | The Campus Premiere Ticket Counter | Abstract `Seat` pricing per category, per-show seat availability tracking, cancellation releases all seats |
| 4 | The FitZone Membership Desk | `MembershipPlan` interface kept separate from status rules, guarded status transitions (e.g. can't freeze an Expired membership) |
| 5 | The Campus Notice Broadcaster | `NotificationChannel` interface, `NoticeBoard` depends on the abstraction rather than concrete channels, new channels add with no change to posting/delivery logic |
