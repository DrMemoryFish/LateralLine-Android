# LateralLine Project Brief

## Mission
Build an independent, self-hostable, offline-first, privacy-focused, secure, durable, extensible location platform for an individual and explicitly trusted circles, with unusually detailed location history and trustworthy behavior through network or server outages.

## What LateralLine Is
- A future multi-user, multi-device platform whose mobile client is the primary product experience.
- A durable local recorder plus secure realtime and historical synchronization.
- An authoritative self-hosted backend, rich history experience, device management, and fine-grained trusted sharing.
- An OwnTracks Android fork at inception that deliberately preserves hard-won donor knowledge while progressively establishing independent architecture.

## What It Is Not
- An OwnTracks theme, prettier client, wrapper around Recorder, Life360 clone, inherited-service dashboard, or temporary travel logger.
- A claim that future backend, identity, sync, circles, sensor, or UI architecture already exists.
- Permission to discard donor Android behavior before equivalent requirements and evidence exist.

## Current Reality — Observed
- Authoritative checkout: `/home/drmemoryfish/ChatGPT/LateralLine`, a writable bind mount of `/mnt/E/AI/LateralLine`.
- Current branch: `travel-build-v0`.
- OwnTracks donor/inception commit and HEAD before governance work: `3129fc23296c16ec5dd9f46d91d0fd94942b9f49`; Git commit date: `2026-10-05T11:37:16+01:00`.
- Owner-recorded LateralLine fork date: 2026-10-10. The local clone and `travel-build-v0` branch creation are independently evidenced by the reflog on that date. Governance foundation instantiation date: 2026-11-24. Official upstream: `https://github.com/owntracks/android`.
- The implementation remains the OwnTracks Android donor code with bounded Travel Build v0 changes. Its outgoing queue is Room-backed, injected with a 5,000,000-message capacity, and full normal enqueue or failed-message requeue rejects the unpersisted message without evicting an already-persisted row.
- The GMS debug travel APK now installs as `org.lateralline.android.travel.debug` with the visible name `LateralLine Travel`, while retaining the donor Kotlin namespace and OwnTracks HTTP/MQTT/configuration interoperability surfaces.
- OwnTracks Recorder exists in the owner's infrastructure, but this repository does not yet contain a LateralLine authoritative backend.
- No LateralLine passkey/device-identity system, circles, redesigned mobile shell, durable-history model, or batch-sync protocol is implemented yet.

## Product Direction — Decided
The mobile experience should eventually provide live and historical location, devices, accounts, circles, permissions, places, timelines, precise date/time selection, route exploration, sync health, privacy controls, accessibility, and clear security state. It should be modern, minimal, calm, intentional, and functional rather than decorative or derivative.

Local acquisition must eventually persist durable history independently of network delivery state. Realtime/current data must not wait behind historical backlog. Historical transfer must become batched, compressed where appropriate, resumable, idempotent, duplicate-safe, and checkpointed.

The backend will become authoritative and is expected to provide APIs, authentication, authorization, users/persons/devices/circles, permissions, location ingestion, realtime communication, batch sync, checkpoints, audit/security events, retention, and history queries. PostgreSQL is the selected data-layer direction, with PostGIS where spatial functionality benefits. Exact schema and backend framework remain open.

## Identity Boundary — Decided
- **Person**: the human.
- **Account**: the person's authentication and authorization identity.
- **Device**: a physical or logical producer of location records with its own eventual credential.
- **Location record**: produced by a device.

Circles and sharing primarily belong to person/account relationships. Person is not device; one person may have multiple devices.

## Security And Privacy Invariants — Decided Direction
- Location history is highly sensitive; security is foundational.
- Passkeys/WebAuthn are primary interactive authentication where practical. Passwordless-first is preferred.
- TOTP is optional additional, recovery, or step-up authentication; SMS is not preferred MFA.
- Each device eventually owns an independent cryptographic identity; Android private keys use Android Keystore and hardware-backed protection where practical.
- Device credentials remain separate from human login credentials. Uploads must not repeatedly transmit a master password.
- Devices can be independently revoked.
- Short-lived access credentials, secure session/refresh design, TLS, server-side authorization, rate limiting, multiple authenticators, recovery codes, key/secret rotation, encrypted backups, security-sensitive audit events, and a non-public database are required design concerns.
- Backend authorization is authoritative; hiding UI elements is never authorization.
- The foreseeable private deployment has one administrator, but high-impact access to other people's location data remains explicit, auditable, and strongly authenticated.

## Deployment And Edge Direction
The backend should move among a desktop PC, Raspberry Pi/home server, inexpensive VPS, conventional cloud host, or other Linux machine without redesign. Docker/Compose is the expected portability layer where useful, for portability rather than fashion.

Cloudflare may provide edge connectivity/security/tunneling. Application authentication and authorization remain application concerns. Caddy may be an optional origin router, not foundational security architecture; other reverse proxies remain valid.

## Location Quality Boundary
Dense capture improves temporal resolution but does not guarantee pavement side, traffic lane, or centimeter-level spatial accuracy. GNSS accuracy metadata, altitude/vertical accuracy, barometer, inertial sensors, activity recognition, raw GNSS, map matching, sensor fusion, and pedestrian dead reckoning are future research areas—not Travel Build v0 scope.

## Current Milestone
Travel Build v0 is an emergency-safe transitional donor build whose bounded acceptance boundary was reached on 2026-11-27. Host and Samsung Galaxy S24 evidence covers the 5,000,000 capacity, non-destructive full-queue behavior, explicit persistence failure, separate installation identity, coexistence with official OwnTracks, physical Room instrumentation, permissions, all tracking-mode selections, HTTP publishing, launcher shortcuts, Samsung battery exemption, background/reboot survival, and offline queue persistence plus reconnect/drain. The plan precisely records two limitations: the owner-reported 5+ hour movement window returned in Significant rather than its Move baseline and therefore is not claimed as a clean long-window Move/dense-capture pass; actual configuration import and authenticated external control remain deferred because their security gates are disabled and no safe owner-provided fixture was available. This build still uses donor Room/acquisition/modes/MQTT/HTTP behavior and is not the permanent storage architecture. See `plans/ACTIVE/2026-11-24_travel-build-v0.md`.
