# LateralLine Platform Architecture

## Purpose And Authority
This document owns LateralLine's durable cross-system architecture contracts: canonical location observations, local durability, identity/provenance boundaries, synchronization semantics, storage lifecycle and fidelity, migration, deployment identity/portability, communication boundaries, and diagnostic trust classes. It constrains later implementation plans without pretending unsettled mechanisms are already chosen.

`docs/PROJECT_BRIEF.md` remains the owner for mission, product direction, security/privacy invariants, and current reality. `docs/ROADMAP.md` owns sequencing. `docs/OPEN_QUESTIONS.md` owns unresolved consequential choices. The retained `docs/DATA_FLOW_DESIGN.md` describes donor OwnTracks internals and is evidence only; it is not the LateralLine architecture contract.

## Claim Boundary
The contracts below are **DECIDED direction** unless a section explicitly says `PROPOSED` or `OPEN`. They are not claims that the current Travel Build implements them. The current Android implementation remains predominantly OwnTracks donor architecture with bounded Travel Build v0 changes.

# 1. Core Data Model Principles

## 1.1 Original observations and interpretations are different kinds of data
A canonical location observation records what a device actually observed, together with the measurement context and uncertainty available at capture time. It is not a claim that the person was definitely at a mathematically perfect coordinate.

Once an observation crosses the future LateralLine acquisition-validation boundary and is accepted as a canonical record:
- preserve the original observation at full fidelity by default;
- preserve relevant available measurement metadata, including accuracy/uncertainty and source/provider context;
- do not discard an observation merely because it is inaccurate, implausible-looking, or inconvenient for display;
- do not overwrite the original with a corrected, smoothed, map-matched, reconstructed, inferred, or otherwise processed value.

Malformed input that never satisfies the acquisition-validation contract does not need to become a canonical observation. The exact acceptance rules remain an implementation/design question for the local-history milestone.

Derived information is separate. Examples include:
- outlier/quality assessments;
- filtered or smoothed positions;
- map-matched positions;
- inferred activity or transport mode;
- reconstructed journeys, stops, places, or routes;
- trajectory simplifications;
- user corrections or manually entered annotations.

Derived objects must retain provenance to the source records/ranges and processing method as appropriate. Reprocessing may replace a derived interpretation, but must not silently rewrite the original observation.

## 1.2 Durable capture boundary
A location record is not successfully captured merely because Android delivered a fix, an asynchronous write was launched, a transport queue accepted work in memory, or a server returned a response.

The future LateralLine capture contract is:

1. acquire and validate/normalize an observation;
2. create its stable canonical identity and provenance;
3. durably commit it to the local history store under the configured durability guarantees;
4. only after that commit succeeds may the application report the record as durably captured;
5. network delivery and server availability are independent follow-on concerns.

If local persistence fails, the application must not report durable capture success. Storage-full, I/O, corruption, migration, and other persistence failures must become explicit health/error states rather than silent loss.

No software can guarantee survival against physically failed storage. Correctness is demonstrated against the software/storage contract using process-death, reboot, abrupt-power-loss where practical, fault-injection, migration, and low/full-disk tests.

## 1.3 Record identity, ordering, and recording lineage
A capture timestamp is not a sufficient record identity or ordering key. Clock corrections, duplicate timestamps, restore/cloning, reinstall, device replacement, imports, and delayed arrival must not create identity collisions.

Each canonical record must eventually have:
- a stable unique logical record identity independent of wall-clock time;
- origin-device identity/provenance;
- per-recording-stream ordering information;
- capture timestamp(s) needed for historical interpretation.

The architecture must distinguish **device identity** from a **recording lineage / stream epoch** (final terminology open). A restored backup, cloned state, reinstall, replacement device, or import must not accidentally create two producers that continue the same ordering sequence ambiguously.

A useful conceptual model is: stable record UUID + origin device + lineage/epoch + monotonic sequence within that lineage + capture/provider/wall/elapsed time where justified. This is not a schema decision; exact ID algorithms and timestamp fields remain open.

Imported/restored original records retain their original logical identities wherever possible. Newly captured records after restore/reinstall must not impersonate an ambiguous pre-restore stream.

## 1.4 Provenance is first-class
LateralLine must be able to distinguish where data came from and what has happened to it. Provenance may include, as applicable:
- native capture vs import vs manual entry vs derived/processed data;
- source device and recording lineage;
- capture method/provider and available device metadata;
- source system/archive and import operation;
- processor/model/algorithm/version/parameters for derived data;
- source record IDs/ranges for transformations.

Migration should preserve immutable/checksummed source archives and an import/reconciliation manifest rather than requiring every normalized row to permanently duplicate the full source representation.

# 2. Local History, Transport, And Synchronization

## 2.1 Canonical history is independent of transport state
The permanent dependency direction is:

`acquisition -> durable local history -> synchronization/transport`

not:

`acquisition -> transport queue -> server`

A transport queue/outbox is delivery state, not the master archive. Endpoint changes, protocol changes, queue clearing/reconstruction, connectivity loss, server outage, synchronization bugs, application crashes, or transport replacement must not delete canonical local history.

The Travel Build v0 five-million-row Room queue is transitional. At capacity it preserves existing rows and rejects new queue entries; therefore it still cannot guarantee continued durable recording when the queue is full. That limitation is one reason the durable-history architecture exists.

## 2.2 Live delivery and historical backfill are separate scheduling semantics
Current/realtime information must not wait behind a large historical backlog. Future synchronization therefore separates:
- a high-priority live/current lane or equivalent scheduling semantics; and
- lower-priority historical backfill that can batch, compress where useful, resume, checkpoint, and run for long periods.

They may ultimately share a transport connection or implementation component, but their priority and progress semantics remain independent.

## 2.3 Delivery correctness: at-least-once transport, idempotent logical storage
LateralLine should not depend on fragile network-level “exactly once” delivery. Ambiguous failure is unavoidable when a server commits a record but the acknowledgement is lost.

The decided correctness direction is:
- stable record identities;
- at-least-once transmission/retry;
- idempotent server ingestion so resending the same record does not create another logical record;
- server acknowledgement only after the record/checkpoint has been durably committed under the server transaction contract;
- durable client-side acknowledgement/checkpoint state;
- reconciliation tooling/tests capable of detecting omissions, duplicates, ordering/checkpoint corruption, and premature acknowledgement.

An HTTP status alone is not inherently enough to prove a record is offload-safe; the future protocol must define what application-level durable acknowledgement means.

## 2.4 Ordering is not timestamp-only
Historical display can use capture time, but synchronization/order identity must survive clock changes, duplicate timestamps, delayed records, imports, and restored streams. Per-lineage sequence/order state must remain distinct from wall-clock timestamps.

## 2.5 Communication protocols are adapters, not the domain model
OwnTracks HTTP/MQTT compatibility remains useful but does not choose LateralLine's permanent communication architecture.

Candidate roles to evaluate later include:
- HTTPS for reliable ingestion, historical batches, queries, accounts, devices, circles, administration, and ordinary APIs;
- WebSockets or another push channel for interactive live updates/viewers where measurement justifies a persistent connection;
- MQTT for OwnTracks compatibility, dedicated GPS trackers, wearables, IoT devices, and external hardware where it provides genuine value.

The exact role split is **OPEN**. Evaluate reliability, throughput, mobile energy, reconnect behavior, privacy/security, operational complexity, maintainability, infrastructure requirements, and extensibility. A protocol adapter must translate into/out of canonical LateralLine records rather than make the wire format the internal source of truth.

# 3. Ownership, Identity, Authorization, And Copies

## 3.1 Person, account, device, record
The existing identity boundary remains:
- **Person**: the human/data subject.
- **Account**: interactive authentication/authorization identity.
- **Device**: physical/logical producer with an independent credential/cryptographic identity.
- **Location record**: logical observation produced by a device/lineage and associated with its owner.

One person may have multiple accounts/authenticators/devices according to later design; person is not device.

## 3.2 Ownership is not custody
A record may be copied to a phone, server, backup, another authorized client, or export without changing who owns the logical data. Storage location/custody and ownership are separate concepts.

Circles are authorization/grouping relationships; they do not own a person's records. Overlapping circles are allowed. Fine-grained access to live location, historical data, and other shared information is required, while exact grant/deny/precedence composition remains OPEN.

## 3.3 Backend authority is scoped, not server-required capture
“Authoritative backend” does not mean a location only exists after server delivery.
- The producing device/local archive is authoritative for an accepted unsynchronized capture and its original provenance.
- After durable ingestion, server and device may hold the same logical record identity.
- The server is authoritative for shared multi-user state: accounts, device enrollment/revocation state, authorization/circles, server-side history, audit/security events, and cross-device/shared views according to later contracts.

This distributed-data boundary must be preserved in backend and synchronization design.

## 3.4 Identity/backend/sync are co-designed at contract boundaries
Milestones may remain separate, but deep implementation must not independently invent incompatible answers to:
- who/what authenticated an upload;
- who owns the record;
- how device revocation affects ingestion;
- how provenance is verified/retained;
- what a durable server acknowledgement means;
- who may read/share/delete a record.

Consequential changes to these contracts require owner review rather than silent implementation choices.

# 4. Local Storage Lifecycle And Fidelity

## 4.1 Default retention
Full-fidelity accepted original history is preserved locally indefinitely by default, subject to physical storage limits. Successful synchronization does not automatically delete the local original.

Destructive retention, downsampling, or removal of canonical local originals requires an explicit user-controlled policy/action. LateralLine must never silently destroy canonical history merely to admit newer history.

## 4.2 Three different deletion/removal operations
These are fundamentally different and must not share accidental semantics:

1. **Lossless local offloading / redundant-copy eviction**: remove a full local copy only when the logical original is verified offload-safe elsewhere according to user policy. This is information-preserving ecosystem-wide but reduces offline local availability. It must never generate a server/global deletion instruction.
2. **Local destructive reduction**: intentionally remove some local canonical originals to recover device storage. This is lossy and requires explicit user authorization. It never instructs the server to delete its full originals.
3. **Global deletion**: intentionally delete logical history from the ecosystem. This is a separate domain/security action with explicit authorization/audit semantics and is never inferred from local cleanup.

Local presence, logical record existence, synchronization status, server archive verification, backup/offload safety, and fidelity must not be collapsed into one `synced` or `exists` boolean.

## 4.3 Synced is not necessarily offload-safe
A server may have durably committed a record while the user's durability policy still requires another verified backup before the phone may evict its full copy. Exact offload-safety classes/policies remain OPEN.

Offloaded ranges may retain lightweight derived journey geometry, summaries, indexes, or availability metadata for offline UX. Those artifacts are caches/derived data, not replacements pretending to be the original. Full detail may later be rehydrated from a trusted archive.

Pinned/protected journeys/date ranges are a supported future policy direction; exact UX/policy is open.

## 4.4 Fidelity state must remain visible
Once originals are intentionally reduced locally, the remaining representation must not be described as untouched full-fidelity history. The data model must be extensible enough to represent full original availability, reduced-local fidelity, remote-full/local-offloaded state, damaged/unknown integrity, and other justified states without fixing a final enum prematurely.

# 5. Storage Pressure, Forecasting, And Intelligent Emergency Reduction

## 5.1 Automatic management remains lossless with respect to canonical information
Automatic storage management may eventually:
- reclaim caches, temporary/rebuildable data, stale transient sync files, and bounded diagnostics according to policy;
- compact/re-encode history losslessly;
- offload full local copies that are verified offload-safe according to user preferences;
- forecast storage pressure and warn early;
- analyze history and prepare a proposed lossy reduction without executing it.

Automatic management must **not** destructively simplify canonical history.

Adaptive future sampling changes observations that are never collected, so it is a separate explicit fidelity policy rather than a loophole around the destructive-reduction rule.

## 5.2 Proactive storage forecasting
LateralLine should forecast remaining recording capacity from actual recent storage consumption and recording behavior rather than waiting for imminent failure. Forecasts must:
- express uncertainty/ranges rather than fake precision;
- account for recording mode, sampling pattern, sensor use, movement/context, and changing record size where material;
- distinguish physical free space from categories of potentially recoverable space;
- reserve operational headroom for database/WAL/transactions, compaction, index rebuild, analysis, reduction, recovery, and OS needs;
- report expected net recovery and peak temporary storage/memory/compute requirements for recovery options;
- support estimated additional recording duration as an explicitly approximate prediction.

## 5.3 Manual intelligent destructive reduction
The owner may manually invoke intelligent local reduction regardless of current free space. Its primary emergency value is when full originals exist only on the recording device and storage pressure would otherwise stop recording.

The operation follows a two-pass model:

**Pass 1 — analysis only**
- read existing history without canonical mutation;
- identify potentially redundant/low-information records/ranges;
- evaluate collective trajectory impact and dependencies;
- produce removal-safety/information-preservation scores and multidimensional error/importance information;
- estimate bytes actually recoverable, affected journeys/ranges, expected information loss, archive status, and operational resource cost;
- produce a stable proposal tied to the analyzed source state.

**Pass 2 — explicit execution**
- requires user authorization;
- revalidates that the proposal/source state is still applicable;
- applies only the authorized subset;
- verifies actual storage recovery;
- records durable reduction provenance and resulting fidelity state.

The user controls both an eligibility/safety threshold and a maximum sacrifice or recovery objective. The optimizer should **minimize cumulative information loss subject to recovering at least the requested storage**, not delete all eligible points.

If a score is not a calibrated probability, do not call it probabilistic “confidence.” Internally retain the relevant dimensions (for example spatial/temporal deviation, speed/heading/acceleration effects, stop/turn/transition importance, accuracy/uncertainty, activity/map ambiguity) rather than hiding all reasoning behind one unexplained scalar.

## 5.4 Context and uncertainty
Trajectory reduction may eventually consider geometry, elapsed time, speed, acceleration/deceleration, heading, stops/dwell, transport/activity transitions, measurement accuracy, geographic/map context, and optional sensor information.

Important constraints:
- a straight line can still contain meaningful traffic/stopping/speed information;
- low-accuracy observations can preserve evidence of uncertainty, tunnels, indoor periods, urban canyons, or sensor degradation and are not automatically junk;
- map matching, activity recognition, sensor fusion, heuristics, or ML are evidence inputs, not replacements for original truth;
- uncertainty favors preservation;
- ML is used only if benchmarked benefit justifies it; destructive decisions should remain reproducible/explainable, with model/version/parameters in provenance and deterministic safety bounds where practical.

Exact algorithms remain OPEN. Classical geometric simplification, spatiotemporal error-bounded methods, SED/BQS-family ideas, map-aware methods, activity/sensor context, hierarchies/progressive representations, and local ML are research candidates rather than commitments.

## 5.5 Repeated reductions and cumulative loss
Candidate importance depends on which neighboring records remain. Scores are not permanent independent facts. Each destructive reduction must evaluate the collective retained trajectory and cumulative fidelity state.

After an earlier reduction, later analysis must reassess the current retained set. If deleted originals are unavailable locally, cumulative-error reasoning may require remote originals or retained hierarchical/error summaries. The exact representation is OPEN; designs that can only compare against the already-degraded local set must not silently claim fidelity relative to the original.

Active/open journeys should normally be protected from destructive retrospective simplification because later observations can change the significance of recent points; exact journey-closure/emergency behavior remains OPEN.

## 5.6 Reduction provenance
A destructive reduction operation must leave durable metadata sufficient to tell the truth about what happened without retaining the full deleted payload and defeating the storage goal. Relevant information can include operation ID/time, authorization/policy, algorithm/model/version/parameters, source state, affected ranges/journeys, original/retained counts, archive-copy state, estimated fidelity bounds, and outcome.

## 5.7 Real filesystem reclamation matters
“Rows deleted” is not equivalent to “bytes returned to the device.” Storage design must support predictable filesystem-level reclamation under pressure and account for the working space needed to do it. SQLite freelist/VACUUM/auto-vacuum behavior, segmented/chunked storage, immutable blocks, or other representations must be evaluated rather than assumed. Emergency operations must function with bounded additional disk/RAM; an operation that needs more temporary storage than remains cannot be the recovery mechanism.

# 6. Migration And OwnTracks Independence

## 6.1 Incremental migration
The owner already has more than a year of OwnTracks/Recorder history. Migration is high-value user data work, not a cosmetic import.

Migration must support incremental coexistence rather than requiring a big-bang replacement. Expected properties include:
- ongoing recording continues during migration/cutover;
- immutable/checksummed source archives are preserved;
- import operations are rerunnable/recoverable after interruption;
- counts/time ranges and other reconciliation evidence can be compared;
- duplicates are handled deterministically;
- provenance links imported records to source system/archive/import operation;
- failed/unrecognized records remain visible rather than silently discarded;
- a catch-up/delta/dual-run strategy is available where needed before final cutover.

Do not destructively transform or delete the only source copy merely because normalized LateralLine records were generated.

## 6.2 Compatibility is an adapter boundary
OwnTracks protocol, Recorder, MQTT, and current Android donor behavior remain compatibility/reference assets while useful. They do not define permanent LateralLine record identity, retention, synchronization, backend schema, or security architecture.

# 7. Deployment, Offline Operation, And Server Identity

## 7.1 Open/self-hosted independence
The long-term ecosystem is intended to remain open-source and operable without mandatory LateralLine subscriptions or proprietary cloud services. Users still bear ordinary costs such as hardware, power, storage, domains, and connectivity. Optional proprietary integrations may exist, but core recording/storage/synchronization and self-hosted operation must have an independent path.

The OSS/non-Google client path must not be allowed to become architecturally irrelevant if it is required to meet this independence goal. Exact licensing/component policy remains OPEN and needs explicit review before publication beyond the current derivative Android repository.

## 7.2 Local/offline operation
When a recording client and LateralLine server can reach each other on a local network without public internet, core local functions should not require Cloudflare, public DNS, or a proprietary hosted service. Long-term goals include local recording, local-history access, local synchronization, and applicable local administration. Remote sharing naturally requires a route/connectivity between participants and the server.

The client must be able to access its own locally stored history even when every server is unreachable.

## 7.3 Portable hosts
The backend should move among desktop Linux, Raspberry Pi/ARM64-class home/portable servers, VPS/cloud Linux, and other supported Linux hosts without application redesign. Do not promise arbitrary OS/hardware neutrality without evidence.

Portable/off-grid operation is a real future scenario: battery/power-bank/solar-assisted server, intermittent or absent internet, abrupt power loss, constrained CPU/RAM/storage, and host migration. This strengthens requirements for transactional durability, backup/restore, power-loss tests, efficient resource usage, and avoiding single-copy assumptions.

PostgreSQL/PostGIS remains the selected backend data-layer direction, but “can run on Pi” is not proof of acceptable ARM/power/storage performance; representative measurements are required later.

## 7.4 Deployment identity is independent of address and hardware
A LateralLine deployment's logical identity/trust must remain independent of current hardware, IP address, LAN hostname, public domain, reverse proxy, or hosting provider. Moving the deployment must preserve accounts, device relationships, circles, history, and trust wherever securely possible.

Deployment identity must also be distinct from an eternal single private key. Secure key rotation/recovery/lineage must be possible. Exact local discovery, TLS, passkey relying-party, endpoint mobility, and trust-transition mechanisms remain OPEN and must be co-designed with identity/deployment architecture.

# 8. Diagnostics, Privacy, Secrets, And Development Tooling

## 8.1 Data classes
Do not solve privacy by making the owner's diagnostics useless. Distinguish at least:

**Secrets** — passwords, private keys, session/refresh/access tokens, authorization headers, recovery codes, API keys, device credentials and comparable authentication material. Secrets should generally never be duplicated into diagnostic logs, including private logs. Diagnostics should expose presence/source/result/error state instead of secret value.

**Private diagnostics/personal data** — precise coordinates, addresses, SSID/BSSID, raw location-bearing payload context, exact endpoint information where sensitive, and other detailed owner-visible troubleshooting data. Rich local diagnostics are allowed when useful.

**Tool-safe diagnostics** — minimized/sanitized booleans, counts, timings, queue/storage states, component names, error classes, status code classes, pseudonymous record IDs, and similar evidence intended for external development tooling.

## 8.2 Technical boundary, not instruction-only redaction
The long-term boundary between private diagnostics and AI/external tooling must be technically enforced where practical. If an agent has arbitrary ADB, debuggable `run-as`, raw logcat, filesystem and UI-dump capabilities, application-level instructions are not a real confidentiality boundary.

Preferred direction:
- rich private diagnostics remain available to the owner locally;
- external tooling gets a constrained diagnostic interface/capability producing tool-safe output;
- broad development/performance/failure testing uses synthetic isolated data/environments by default;
- personal-device raw access is exceptional, explicit, minimum-scope, and owner-authorized;
- secrets remain outside both ordinary diagnostic classes.

The FAIL-0002 command-boundary allowlisting procedure is interim containment, not the final trust architecture.

# 9. Current Travel Build Technical Debt And Characterization Boundary

These are observed implementation facts, not definitions of future architecture:
- queue persistence returns an asynchronous Boolean, but current `LocationProcessor` does not await it and can report pipeline success/set the published location after a failed persistence attempt;
- sender retry state does not feed the decremented retry count back into the live counter, while retryable messages are requeued to the queue head, creating a persistent-failure/head-of-line-starvation risk;
- dequeue removes a row before transport send, creating an in-flight crash/power-loss window;
- connection preference changes can clear the outgoing transport queue;
- queue deserialization failure substitutes `MessageUnknown` and deletes the original row rather than quarantining it;
- legacy Tape2 migration can delete source files after partial migration success and may delete a corrupted source file after I/O failure;
- five-million-row operational performance is unbenchmarked;
- the Android foreground service is currently declared as `connectedDevice` despite its core location-sharing role; modern Android location foreground-service requirements must be reconciled;
- current debug logging can expose detailed personal/configuration values to ADB/logcat, ordinary preferences store endpoint credentials under the donor SharedPreferences model, cleartext traffic is globally permitted by donor network config, and external content-URI configuration gating is inconsistent with the explicit external-configuration switch;
- the current APK is a debug artifact, lint is not clean (`abortOnError=false` with donor backlog), and the reviewed LateralLine commits had no attached GitHub commit-status checks.

Travel Build v0 remains complete for its bounded non-eviction/coexistence/device-validation contract. These findings prevent interpreting it as a lossless, production-hardened location recorder.

## 9.1 Current Travel queue scale characterization
Before relying on the five-million number as a travel safety envelope, use realistic synthetic datasets at multiple scales (exact matrix plan-defined; representative checkpoints include tens/hundreds of thousands, one million, intermediate multi-million states, five million, and the capacity+1 boundary).

Measure at least: database/WAL/storage growth, insert and dequeue latency including tails, memory, initialization/startup/reboot, drain throughput, persistent-failure/retry behavior, network flapping, process death, low/full disk, ordering, and battery/thermal behavior where material. Synthetic trajectories should resemble walking/driving/stationary/bursty/offline patterns rather than identical toy rows.

Do not spend disproportionate effort optimizing the transitional queue if evidence shows the durable-history architecture is the better fix. Synthetic scale tests do not replace representative S24/OEM/background endurance tests.

# 10. Durable Local History v1 Boundary

The next durable-history milestone must establish the foundation, not implement every future feature.

## Must establish
- protocol-independent canonical original observations;
- stable record identity/order/lineage/provenance contracts;
- durable-capture success semantics;
- isolated local-history ownership independent of transport queue/configuration;
- schema migration/integrity behavior;
- efficient append and time/range access sufficient for later timeline and sync scans;
- storage accounting and a representation that does not make future real reclamation/fidelity state impossible;
- failure tests proportionate to data-integrity risk.

## Explicitly not required in v1
- final historical sync protocol/backend;
- live WebSocket/MQTT/HTTPS role decision;
- server/offload engine;
- trajectory simplification or ML;
- proactive forecasting UI;
- adaptive sampling;
- circles/passkeys/backend implementation;
- final retention/reduction algorithms;
- Recorder migration execution.

The milestone may include a small pre-history hardening/characterization gate for current-runtime defects that directly threaten development/travel safety, but that gate must not become a second transport architecture project.
