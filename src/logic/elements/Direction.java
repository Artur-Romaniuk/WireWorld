package logic.elements;

public enum Direction {
    UP {
        @Override
        public String toString() {
            return "UP";
        }
    },
    DOWN {
        @Override
        public String toString() {
            return "DOWN";
        }
    },
    LEFT {
        @Override
        public String toString() {
            return "LEFT";
        }
    },
    RIGHT {
        @Override
        public String toString() {
            return "RIGHT";
        }
    }
}
