public class GalleryDescriptionCards {

    static abstract class ArtPiece {
        private static int counter = 0;
        private final String pieceId;

        public ArtPiece() {
            counter++;
            this.pieceId = "ART-" + counter;
        }

        public abstract String describe();

        String getPieceId() {
            return pieceId;
        }
    }

    static class Painting extends ArtPiece {
        String title;

        public Painting(String title) {
            super();
            this.title = title;
        }

        @Override
        public String describe() {
            return "Painting: " + title + ", framed on canvas";
        }
    }

    static class Sculpture extends ArtPiece {
        String title;

        public Sculpture(String title) {
            super();
            this.title = title;
        }

        @Override
        public String describe() {
            return "Sculpture: " + title + ", carved from stone";
        }
    }

    public static void main(String[] args) {
        Painting p = new Painting("Sunset Fields");
        System.out.println(p.describe());

        Sculpture s = new Sculpture("The Thinker II");
        System.out.println(s.describe());
    }
}
