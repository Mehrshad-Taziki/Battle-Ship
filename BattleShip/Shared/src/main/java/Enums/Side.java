package Enums;

public enum Side {
    PLAYER_ONE{
        @Override
        public Side getOther() {
            return PLAYER_TWO;
        }

        @Override
        public int getInt() {
            return 0;
        }

    },PLAYER_TWO{
        @Override
        public Side getOther() {
            return PLAYER_ONE;
        }

        @Override
        public int getInt() {
            return 1;
        }
    };

    public abstract Side getOther();

    public abstract int getInt();
}
