class AllOne {
    Bucket min;
    Bucket max;
    Bucket zero;

    Map<String, Bucket> charMap = new HashMap<>();
    public AllOne() {
        zero = new Bucket(0);
        min = zero;
        max = zero;
    }

    public void inc(String key) {
        moveToNextBucket(key);
    }

    private void moveToNextBucket(String key) {
        Bucket currentBucket = charMap.getOrDefault(key, zero);
        Bucket nextBucket = currentBucket.getOrCreateIncrementalNext();

        removeFrom(currentBucket, key);
        addTo(nextBucket, key);
    }

    private void addTo(Bucket bucket, String key) {
        if(bucket == zero) return;

        charMap.put(key, bucket);

        bucket.add(key);
        if (bucket.prev == max) {
            max = bucket;
        }
    }

    private void removeFrom(Bucket bucket, String key) {
        if(bucket == zero) return;

        charMap.remove(key);

        bucket.remove(key);
        if(bucket.isEmpty()) {
            remove(bucket);
        }
    }

    public void dec(String key) {
        moveToPreviousBucket(key);
    }

    private void moveToPreviousBucket(String key) {
        Bucket currentBucket = charMap.get(key);
        Bucket prevBucket = currentBucket.getOrCreateDecrementalPrevious();

        removeFrom(currentBucket, key);
        addTo(prevBucket, key);
    }

    private void remove(Bucket bucket) {
        if(bucket == zero)
            return;
        bucket.prev.next = bucket.next;
        if(bucket.next != null)
            bucket.next.prev = bucket.prev;

        if(bucket == max)
            max = bucket.prev;

        bucket.next = null;
        bucket.prev = null;
    }

    public String getMaxKey() {
        if(isEmpty()) return "";
        return max.members.iterator().next();
    }

    private boolean isEmpty() {
        return min == max;
    }

    public String getMinKey() {
        if(isEmpty()) return "";
        return min.next.members.iterator().next();
    }
}

class Bucket {
    final int count;
    final Set<String> members;
    Bucket prev;
    Bucket next;

    Bucket(int count) {
        this.count = count;
        this.members = new HashSet<>();
        prev = null;
        next = null;
    }

    public Bucket getOrCreateIncrementalNext() {
        if (!hasIncrementalNext()) {
          Bucket newNext = new Bucket(this.count + 1);
          insertAfter(newNext);
        }
        return this.next;
    }

    private void insertAfter(Bucket bucket) {
        bucket.next = this.next;
        if (this.next != null) {
          this.next.prev = bucket;
        }
        this.next = bucket;
        bucket.prev = this;
    }

    private boolean hasIncrementalNext() {
        return this.next != null && this.next.count == (this.count + 1);
    }

    public void add(String member) {
        this.members.add(member);
    }

    public void remove(String member) {
        this.members.remove(member);
    }

    public boolean isEmpty() {
        return this.members.isEmpty();
    }

    public Bucket getOrCreateDecrementalPrevious() {
        if (!hasDecrementalPrevious()) {
          Bucket newPrev = new Bucket(this.count - 1);
          insertBefore(newPrev);
        }
        return this.prev;
    }

    private void insertBefore(Bucket bucket) {
        bucket.prev = this.prev;
        if (this.prev != null) {
          this.prev.next = bucket;
        }
        this.prev = bucket;
        bucket.next = this;
    }

    private boolean hasDecrementalPrevious() {
        return this.prev != null && this.prev.count == (this.count - 1);
    }
}