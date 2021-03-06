package bonk_andrzej.app.utils.converter;

import bonk_andrzej.app.db.modelDb.Author;
import bonk_andrzej.app.fx.view.AuthorFx;

public class AuthorConverter {

    public AuthorFx convertAuthorToAuthorFx(Author author) {
        AuthorFx authorFx = new AuthorFx();
        authorFx.setId(author.getId());
        authorFx.setName(author.getName());
        authorFx.setSurname(author.getSurname());
        return authorFx;
    }

    public Author convertAuthorFxToAuthor(AuthorFx authorFx) {
        Author author = new Author();
        author.setId(authorFx.getId());
        author.setName(authorFx.getName());
        author.setSurname(authorFx.getSurname());
        return author;
    }

}
