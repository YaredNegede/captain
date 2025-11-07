# captain

## Design summary

**Runtime target:** Local machine (the MCP binary / service runs locally and invokes real kubectl).

**Tool exposed:** kubectl (core Kubernetes commands only — get, describe, logs, exec, apply, delete, port-forward, scale, patch, rollout, top).

**Execution style:** LLM uses structured MCP "tool calls" (JSON) to request an action; MCP service runs kubectl as a subprocess or uses client-go, returns structured JSON results (prefer -o json when available).

**Outputs:** Always return structured output where possible (JSON). For commands that are inherently textual (e.g., logs, exec), return stdout/stderr fields and optionally a summarized text.

**Security:** Non-mutating commands allowed by default. Mutating/privileged commands require an explicit escalation approval event from a human operator. The MCP service must implement an approval mechanism (prompt, system dialog, or CLI confirm) and record audit logs.

**Timeouts / limits:** Default 30s; configurable per-command. Limit returned object size and truncate long logs with truncated: true flag.

**Context management:** Accept kubeconfig, context, and namespace as part of each call. Default uses local KUBECONFIG or ~/.kube/config.

**Safety:** Provide --dry-run support for mutating actions and confirm=true only after approval.