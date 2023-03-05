package entities;

//public enum Genre {
//    French,
//    Italian,
//    Japanese,
//    Chinese,
//    Indian
//}

public enum Genre {

    /**
     * City list
     */
    All(0),
    French(1),
    Italian(2),
    Japanese(3),
    Chinese(4),
    Indian(5),
    ;

    private final Integer genreId;

    Genre(Integer genreId) {
        this.genreId = genreId;
    }

    public Integer getgenreId() {
        return genreId;
    }
}
