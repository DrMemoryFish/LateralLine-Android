# Workflow

## Default Stage Order
1. Orient on authority, current plan, and Git state.
2. Inspect direct evidence.
3. Define scope, exclusions, and acceptance.
4. Create or update an active plan and checkpoint sidecar for substantial work.
5. Implement or produce only the scoped change.
6. Verify with the strongest practical evidence.
7. Review correctness, safety, duplication, and maintainability.
8. Reconcile durable documents and registers.
9. Checkpoint and hand off with an exact next action.

## Non-Skippable Principle
A later stage does not begin by default until the prior stage has produced its required outputs or an explicit, logged exception explains why the skip is safe.

## LateralLine Safeguards
- Preserve donor behavior unless the task explicitly changes it and evidence/tests justify the change.
- Review relevant source, tests, Git history, release notes, issues, pull requests, and documentation in proportion to risk.
- Classify upstream changes as ADOPT, ADAPT, SUPERSEDED, IRRELEVANT, or REVIEW; do not blindly merge upstream.
- Keep large missions bounded by architectural verification points, not arbitrary code-size limits.
- Separate direct verification from later physical-device, infrastructure, security, or scale validation.
- Do not let a documentation, travel-safety, or compatibility mission expand into unrelated product redesign.

