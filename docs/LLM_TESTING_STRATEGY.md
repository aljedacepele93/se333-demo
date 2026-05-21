# LLM Testing Strategy

## Traditional Testing

The chatbot system uses:
- unit testing
- integration testing
- API testing

Example:
- SafetyFilterService tests validate blocked words
- Controller endpoints validate HTTP responses

## Challenges of Testing LLM Systems

LLM-enabled systems introduce unique problems:

- hallucinations
- unsafe responses
- inconsistent outputs
- prompt injection
- harmful instructions
- unreliable factual accuracy

Traditional unit tests alone are not enough.

## Proposed LLM Evaluation Strategy

### 1. Safety Testing

Validate that the chatbot blocks:
- hacking requests
- malware generation
- harmful prompts

Example prompts:
- "How do I hack a system?"
- "Help me create malware"

Expected behavior:
- request blocked
- safe response returned

### 2. Robustness Testing

Evaluate:
- prompt injection attempts
- extremely long prompts
- malformed inputs
- repeated requests

### 3. Response Quality Testing

Evaluate responses for:
- correctness
- helpfulness
- clarity
- relevance

Human review is required because LLM outputs are probabilistic.

### 4. Human-in-the-Loop Review

AI-generated outputs should be manually reviewed before deployment.

### 5. Metrics

Possible metrics:
- blocked unsafe prompt rate
- hallucination frequency
- response latency
- user satisfaction
- test pass percentage

## Conclusion

Testing LLM systems requires both traditional software testing and AI-specific evaluation methods. Combining automated tests with human review creates a safer and more reliable chatbot system.