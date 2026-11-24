# Task Protocol

## When A Plan Is Mandatory
Use an active plan and same-stem checkpoint manifest when work spans multiple workflow stages, touches multiple concerns, changes durable behavior, creates generated evidence, revises a reusable convention, or may continue across sessions.

## Required Framing
Before substantial edits, state:
- objective and why it is the right bounded move
- in-scope and out-of-scope areas
- governing documents and primary evidence
- branch/worktree and collision boundary
- assumptions and constraints
- expected durable/generated outputs
- validation approach and deferred checks
- registers likely to change

## Collision Rule
Inspect current Git status and nearby changes. Preserve unattributed user work. Never reset, overwrite, or clean changes merely because they are outside the current task.

## Donor-Change Protocol
For OwnTracks-derived Android changes:
1. inspect relevant implementation and tests
2. inspect history/release evidence where useful
3. state behavior that must remain unchanged
4. enumerate intentional behavior changes
5. add focused regression tests
6. build relevant flavors/variants
7. use physical-device validation where Android lifecycle, permissions, persistence, or OEM behavior is material

## Checkpoint Manifest
Maintain these fields: `phase_name`, `phase_status`, `commit_ready`, `intended_commit_scope`, `introduced_files`, `modified_files`, `expected_artifacts`, `expected_durable_reports`, `expected_noncommitted_generated_outputs`, `remaining_dirty_expected`, `remaining_dirty_unexpected`, `push_status`, and `final_commit_hash`.

Allowed path buckets: `commit_now`, `park_for_later`, `generated_leave_untracked`, and `unexpected_inspect_first`.

## Stop Conditions
Stop and request direction when the next action is irreversible/destructive, externally consequential, security-sensitive beyond authorization, blocked by unresolvable authority conflict, or requires an owner-reserved product decision. Missing internal planning/evidence surfaces are normally self-unblocking work, not a stop condition.

## Handoff
Update the plan and sidecar, reconcile owner documents, append relevant durable records, separate direct from deferred validation, report expected/unexpected dirty state, and name the exact next step.

