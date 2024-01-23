package ru.hanqnero.uni.lab3.people.interfaces;

public interface CanNod {
    void nod(NodSpeed speed);

    enum NodSpeed {
        SLOW(1000L, "slowly"),
        NORMAL(500L, "\r"),
        FAST(150L, "quickly");
        private final long time;
        private final String adverb;

        NodSpeed(long time, String adverb) {
            this.time = time;
            this.adverb = adverb;
        }

        public long toLong() {
            return time;
        }

        public String toStringAdverb() {
            return adverb;
        }


    }

    class Head {
        State state = State.REGULAR;

        public void tilt(State s) {
            state = s;
        }

        public enum State {TILTED_DOWN, REGULAR, TILTED_UP}
    }
}
