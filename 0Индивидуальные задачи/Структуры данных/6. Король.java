import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("in.txt"));
        PrintWriter writer = new PrintWriter("out.txt");
        int n = Integer.parseInt(reader.readLine());
        String[] str = reader.readLine().split(" ");
        Set<Position> mov = new HashSet<>();
        int x = 0;
        int y = 0;

        mov.add(new Position(0, 0));

        for (int i = 0; i < n; ++i) {
            //position.change(Integer.parseInt(str[i]));
            switch (Integer.parseInt(str[i])) {
                case 0: {
                    x--;
                    break;
                }
                case 1: {
                    x--;
                    y++;
                    break;
                }
                case 2: {
                    y++;
                    break;
                }
                case 3: {
                    x++;
                    y++;
                    break;
                }
                case 4: {
                    x++;
                    break;
                }
                case 5: {
                    x++;
                    y--;
                    break;
                }
                case 6: {
                    y--;
                    break;
                }
                case 7: {
                    x--;
                    y--;
                    break;
                }
            }

            if (!mov.add(new Position(x, y))) {
                writer.write("Yes");
                writer.close();
            }
        }
        writer.write("No");
        writer.close();
    }


    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object elem) {
            Position el = (Position) elem;
            if (elem == null || elem.getClass() != this.getClass()) {
                return false;
            } else if (elem == this) {
                return true;
            }
            return x == el.x && y == el.y;
        }
    }
}