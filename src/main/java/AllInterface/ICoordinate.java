package AllInterface;

public interface ICoordinate {
    int row();
    int col();
    boolean valid();
    void set(int row, int col, boolean valid);
    void setValid(boolean valid);
}
