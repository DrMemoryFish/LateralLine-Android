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

## Owner-Authorized Commit And Push
When the owner instructs Codex to commit a completed or reviewed unit, Codex owns the full governed operation unless the owner explicitly narrows it:
1. inspect status, staged and unstaged diffs, intended scope, branch, and remotes
2. reconcile the active plan, checkpoint, and affected durable records
3. confirm required validation and stop conditions
4. stage only intended files and verify the staged diff
5. create the commit with an accurate message
6. obtain the actual hash, author/committer metadata, and timestamps from Git
7. reconcile commit/push bookkeeping from that evidence
8. push when the owner's instruction authorizes it
9. fetch or otherwise refresh the remote-tracking ref, then verify local HEAD, remote equality, and final dirty state
10. report every created commit and any intentionally uncommitted work

Commit authorization does not imply push authorization unless the owner authorizes both. Codex must not hand shell commands back to the owner as a substitute for an authorized operation it can safely complete.

## Non-Recursive Commit Bookkeeping
A commit cannot contain its own final hash. Before the relevant commit exists, hash fields remain null or explicitly pending. After Git creates the commit, record its real hash and timestamps in a later bookkeeping change when durable continuity requires it. One small follow-up bookkeeping commit may record the preceding substantive commit and push; it does not require another bookkeeping-only commit to record itself. Verify the follow-up's own hash and push directly from Git and report them at handoff or record them at the next natural checkpoint. Never create an infinite chain of commits whose only purpose is to name the immediately preceding bookkeeping commit.

## Stop Conditions
Stop and request direction when the next action is irreversible/destructive, externally consequential, security-sensitive beyond authorization, blocked by unresolvable authority conflict, or requires an owner-reserved product decision. Missing internal planning/evidence surfaces are normally self-unblocking work, not a stop condition.

## Handoff
Update the plan and sidecar, reconcile owner documents, append relevant durable records, separate direct from deferred validation, report expected/unexpected dirty state, and name the exact next step.
