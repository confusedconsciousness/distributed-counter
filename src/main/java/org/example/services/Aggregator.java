package org.example.services;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

@Component
public class Aggregator {
    private final Map<String, LongAdder> counters = new ConcurrentHashMap<>();

    public long addOne(String contentId) {
        LongAdder adder = counters.computeIfAbsent(contentId, k -> new LongAdder());
        adder.increment();
        return adder.sum();
    }

    public Map<String, Long> drainAll() {
        Map<String, Long> snapshot = new ConcurrentHashMap<>();
        counters.forEach((key, adder) -> {
            long delta = adder.sumThenReset();
            if (delta > 0) {
                snapshot.put(key, delta);
            }
        });
        counters.entrySet().removeIf(e -> e.getValue().sum() == 0L);
        return snapshot;
    }

    public Map<String, Long> drainAboveThreshold(long threshold) {
        Map<String, Long> snapshot = new ConcurrentHashMap<>();
        counters.forEach((key, adder) -> {
            if (adder.sum() >= threshold) {
                long delta = adder.sumThenReset();
                if (delta > 0) snapshot.put(key, delta);
            }
        });
        counters.entrySet().removeIf(e -> e.getValue().sum() > threshold);
        return snapshot;
    }
}
