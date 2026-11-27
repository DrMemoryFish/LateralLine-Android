# LateralLine Project Brief

## Mission
Build an independent, open-source, self-hostable, offline-first, privacy-focused, secure, durable, extensible location platform for an individual and explicitly trusted circles, with unusually detailed location history and trustworthy behavior through network, server, device-lifecycle, and migration failures.

## What LateralLine Is
- A future multi-user, multi-device platform whose mobile client is the primary product experience.
- A durable local recorder whose accepted original observations survive independently of network delivery state.
- Secure live/current sharing plus resumable historical synchronization that does not make current location wait behind backlog.
- An authoritative self-hosted shared backend for accounts, devices, authorization/circles, server-side history, audit/security events, queries, and cross-device state—without making server availability a prerequisite for local capture.
- An OwnTracks Android fork at inception that deliberately preserves hard-won donor knowledge while progressively establishing independent data, identity, synchronization, backend, and product architecture.
- A system intended to remain operable without mandatory subscriptions or proprietary cloud infrastructure, with optional integrations allowed where they do not become core dependencies.

## What It Is Not
- An OwnTracks theme, prettier client, wrapper around Recorder, Life360 clone, inherited-service dashboard, or temporary travel logger.
- A claim that future backend, identity, sync, circles, sensor, storage-lifecycle, or UI architecture already exists.
- Permission to discard donor Android behavior before equivalent requirements and evidence exist.
- A design where a finite outgoing queue, successful network send, endpoint address, or protocol determines whether a person's canonical history exists.
- A design where local cleanup implicitly deletes server history, or where derived/inferred data silently replaces original observations.

## Current Reality — Observed
- Authoritative checkout path in the owner's development environment is `/home/drmemoryfish/ChatGPT/LateralLine`, a bind mount of `/mnt/E/AI/LateralLine`; this path is environment-specific and may be absent from other agents/runtimes.
- Current branch: `travel-build-v0`.
- OwnTracks donor/inception commit: `3129fc23296c16ec5dd9f46d91d0fd94942b9f49` (`2026-10-05T11:37:16+01:00`). Owner-recorded fork date: 2026-10-10. Governance foundation date: 2026-11-24. Official upstream: `https://github.com/owntracks/android`.
- The implementation remains predominantly OwnTracks Android with bounded Travel Build v0 changes. Its outgoing transport queue is Room-backed with a 5,000,000-message configured capacity. A full normal enqueue or failed-message requeue preserves already-persisted rows and rejects the attempted write instead of evicting an existing row.
- The current GMS debug travel APK installs separately as `org.lateralline.android.travel.debug` with visible name `LateralLine Travel`, while retaining donor Kotlin namespace and OwnTracks HTTP/MQTT/configuration interoperability surfaces.
- Physical S24 testing demonstrated bounded Room persistence/reboot/reconnect behavior and HTTP 2xx delivery, but did not make the queue a lossless archive or prove five-million-row performance.
- Post-completion source audit found important current debt: producer code ignores the asynchronous queue-persistence result; sender retry bookkeeping can cycle/head-starve under persistent failure; dequeue-before-send creates an in-flight loss window; configuration changes can clear the queue; corrupt queued rows and some legacy migration failures are not preservation-safe. These findings qualify the Travel build's reliability boundary without reopening its completed non-eviction milestone.
- The current foreground service is declared as `connectedDevice`, the current debug build can expose detailed private data through debugging surfaces, the donor network config globally permits cleartext, endpoint credentials remain in donor SharedPreferences, lint has a known donor backlog with `abortOnError=false`, and reviewed LateralLine commits have no attached GitHub commit-status checks. These are current-state facts/technical debt, not future LateralLine requirements.
- OwnTracks Recorder exists in the owner's infrastructure, with more than a year of valuable history to preserve. This repository still contains no authoritative LateralLine backend.
- No LateralLine durable-history store, batch-sync protocol, passkey/device-identity implementation, circles system, redesigned mobile shell, storage forecasting/offloading engine, or intelligent trajectory reduction system exists yet.

## Product Direction — Decided
The mobile experience should eventually provide live and historical location, devices, accounts, circles, permissions, places, timelines, precise date/time selection, route exploration, sync/storage health, privacy controls, accessibility, and clear security/fidelity state. It should be modern, minimal, calm, intentional, and functional rather than decorative or derivative.

Accepted original observations are preserved independently from interpretations. Corrections, filtering, map matching, inferred activities, reconstructed journeys, simplified routes, and other processed information remain separate derived data with provenance.

Local acquisition persists canonical history before transport. Realtime/current data does not wait behind historical backlog. Historical transfer becomes batched where useful, resumable, checkpointed, duplicate-safe, and idempotent. Exact wire protocols remain open and are chosen by evidence rather than donor inheritance.

The server preserves full-fidelity originals by default once durably ingested. Successful synchronization does not imply local deletion. Local full-copy offloading, local destructive fidelity reduction, and global deletion are distinct operations with different authorization and propagation semantics.

The backend is authoritative for shared/multi-user state after durable ingestion, while the producing device/local archive is authoritative for an accepted unsynchronized capture. PostgreSQL is the selected backend data-layer direction, with PostGIS where spatial functionality benefits. Exact schema and backend framework remain open.

`docs/PLATFORM_ARCHITECTURE.md` owns the detailed cross-system contracts and storage/synchronization boundaries.

## Identity Boundary — Decided
- **Person**: the human/data subject.
- **Account**: interactive authentication/authorization identity.
- **Device**: a physical/logical producer with an independent eventual credential.
- **Recording lineage / stream epoch**: a future ordering boundary needed to prevent restored/cloned/reinstalled producers from creating ambiguous sequence continuation; final name/representation remains open.
- **Location record**: a stable logical observation produced by a device/lineage, with provenance and independent identity.

Circles and sharing primarily relate persons/accounts; records do not become owned by a circle. One person may have multiple devices. Data custody/copy location does not change ownership.

## Security, Privacy, And Tooling Invariants — Decided Direction
- Location history is highly sensitive; security is foundational.
- Passkeys/WebAuthn are primary interactive authentication where practical. Passwordless-first is preferred. TOTP is optional additional/recovery/step-up authentication; SMS is not preferred MFA.
- Each device eventually owns an independent cryptographic identity; Android private keys use Android Keystore and hardware-backed protection where practical. Devices can be revoked independently.
- Device credentials remain separate from human login credentials. Uploads must not repeatedly transmit a master password.
- Short-lived access credentials, secure session/refresh design, TLS, server-side authorization, rate limiting, multiple authenticators, recovery codes, key/secret rotation, encrypted backups, security-sensitive audit events, and a non-public database are required design concerns.
- Backend authorization is authoritative; hiding UI elements is never authorization.
- The foreseeable private deployment may have one administrator, but high-impact access to other people's location data remains explicit, auditable, and strongly authenticated.
- Secrets (passwords, private keys, auth/session/refresh tokens, API keys, recovery secrets, authorization headers, device credentials) are not ordinary diagnostics and should generally never be duplicated into diagnostic logs.
- Rich private local diagnostics may include precise personal/location/network information when useful to the owner. External AI/development tooling receives tool-safe minimized diagnostics by default through technically enforced boundaries where practical. Instructions/redaction alone are not a sufficient trust boundary when a tool has unrestricted ADB/run-as/debug access.
- Synthetic isolated data/environments are the preferred substrate for scale, failure, and engineering tests that do not require the owner's personal data.

## Deployment And Independence Direction
The backend should move among a desktop PC, Raspberry Pi/ARM64 home or portable server, inexpensive VPS, conventional cloud host, or other supported Linux machine without redesign. Docker/Compose remains the expected portability layer where useful, for portability rather than fashion.

Core operation must not require Cloudflare, public DNS, or another proprietary cloud service when client and server can communicate locally. Cloudflare may provide optional edge/tunnel functions. Caddy or another reverse proxy may be used, but application authentication/authorization remains application-owned.

A deployment's logical identity/trust must be independent of current hardware, IP, domain, reverse proxy, and hosting provider. Migration should preserve users, devices, circles, history, and trust where securely possible. Deployment identity is also distinct from an eternal unrotatable private key.

Portable/off-grid operation (battery/power bank/solar-assisted, intermittent/no internet, abrupt power loss) is a real future scenario. It strengthens requirements for efficient resource usage, transactional durability, backup/restore, local-network operation, and host migration; it does not imply support for arbitrary operating systems or hardware without evidence.

The whole ecosystem is intended to remain open-source and usable without mandatory project/SaaS subscription fees. Ordinary user costs such as hardware, storage, power, domains, and internet still exist. Optional proprietary integrations must not become required core infrastructure.

## Location Quality And Fidelity Boundary
Dense capture improves temporal resolution but does not guarantee pavement side, traffic lane, or centimeter-level spatial accuracy. Preserve accuracy/uncertainty evidence rather than treating sampling frequency as certainty.

Advanced GNSS/inertial/barometric/activity/raw-sensor recording, map matching, sensor fusion, trajectory inference, adaptive sampling, and intelligent trajectory simplification are future research/design areas. Map matching, activity models, heuristics, and ML are evidence/interpretation inputs rather than replacements for original observations.

## Storage Lifecycle Direction
Full-fidelity accepted originals remain local by default within physical storage limits; successful sync does not automatically delete them. Automatic storage management may reclaim nonessential/rebuildable data, use lossless compaction/encoding, offload verified redundant full copies according to user policy, forecast pressure, and prepare proposals. It must not automatically perform destructive simplification of canonical history.

Manual intelligent destructive local reduction is a future explicitly authorized recovery tool, principally for difficult offline storage pressure. It is two-pass, incremental, cumulative-loss-aware, provenance-preserving, and optimized to sacrifice only enough information to reach the user's recovery target. Exact algorithms/thresholds/storage representation remain open.

## Current Milestone State
Travel Build v0 reached its bounded acceptance boundary on 2026-11-27 and is committed/pushed. It established separate installation identity, five-million configured queue capacity, non-eviction on full enqueue/requeue, focused host/device tests, physical S24 coexistence/lifecycle/offline persistence/reconnect evidence, and working HTTP delivery for the exercised path.

The completion must not be overread. Queue-layer rejection is explicit, but the current producer does not propagate that result end-to-end; the queue removes rows before send; persistent-failure retry bookkeeping is defective; and other donor queue-clear/corruption/migration paths remain unsuitable for permanent history. The long movement window also did not prove clean Move-mode endurance. Travel Build is therefore a transitional safety build, not a lossless or production-hardened recorder.

No later implementation milestone is active. The next implementation mission requires a new owner-authorized plan after this architecture/governance consolidation.
