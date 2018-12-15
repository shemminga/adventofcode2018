package day13;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class TrackLayoutA {
    private static final List<Character> CART_CHARS = List.of('^', '<', '>', 'v');

    private final char[][] grid;
    private final List<Cart> carts = new ArrayList<>();
    private final List<Cart> collidedCarts = new ArrayList<>();

    private TrackLayoutA(final char[][] grid) {
        this.grid = grid;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                final Direction cartDirection = Direction.of(grid[i][j]);
                if (cartDirection != null) {
                    grid[i][j] = cartDirection.getTrack();

                    carts.add(new Cart(j, i, cartDirection));
                }
            }
        }

        sortCarts();
    }

    void doTick() {
        carts.stream()
                .filter(cart -> !cart.isCollided())
                .forEach(cart -> {
                    cart.moveStep();
                    cart.updateDirection(grid[cart.getY()][cart.getX()]);

                    final List<Cart> collisionCarts = carts.stream()
                            .filter(cart1 -> cart.getY() == cart1.getY() && cart.getX() == cart1.getX())
                            .filter(cart1 -> !cart.equals(cart1))
                            .collect(Collectors.toList());

                    if (!collisionCarts.isEmpty()) {
                        cart.setCollided();
                        collisionCarts.forEach(Cart::setCollided);

                        collidedCarts.add(cart);
                        collidedCarts.addAll(collisionCarts);
                    }
                });

        sortCarts();
    }

    private void sortCarts() {
        carts.sort((c1, c2) -> (c1.getY() == c2.getY()) ? (c1.getX() - c2.getX()) : (c1.getY() - c2.getY()));
    }

    List<Cart> getCollidedCarts() {
        return collidedCarts;
    }

    List<Cart> getCarts() {
        return carts;
    }

    char[][] getGrid() {
        return grid;
    }

    static TrackLayoutA of(final List<String> lines) {
        final char[][] grid = lines.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        return new TrackLayoutA(grid);
    }
}
