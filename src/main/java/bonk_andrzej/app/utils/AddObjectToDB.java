package bonk_andrzej.app.utils;

import bonk_andrzej.app.db.dao.CrudFacade;
import bonk_andrzej.app.db.modelsDb.Author;
import bonk_andrzej.app.db.modelsDb.Book;
import bonk_andrzej.app.db.modelsDb.Category;
import bonk_andrzej.app.utils.exceptions.ApplicationException;

import java.time.LocalDate;


public class AddObjectToDB {
    public static void addObjectToDB() throws ApplicationException {
        CrudFacade crudFacade = new CrudFacade();


        Category category1 = new Category();
        category1.setName("Dramat");
        Category category2 = new Category();
        category2.setName("Sensacja");
        Category category3 = new Category();
        category3.setName("Reportaż");
        Category category4 = new Category();
        category4.setName("Fantastyka");



        Author author1 = new Author();
        author1.setName("Jakub");
        author1.setSurname("Ćwiek");
        Author author2 = new Author();
        author2.setName("Jarosław");
        author2.setSurname("Grzędowicz");
        Author author3 = new Author();
        author3.setName("Andrzej");
        author3.setSurname("Sapkowski");
        Author author4 = new Author();
        author4.setName("Marcin ");
        author4.setSurname("Przybyłek");



        Book book1 = new Book();
        book1.setCategory(category1);
        book1.setAuthor(author1);
        book1.setTitle("Kłamca");
        book1.setIsbn("2222");
        book1.setRating(4);
        book1.setReleaseDate(LocalDate.of(1500,10,10));
        book1.setAddedDate(LocalDate.now());
        book1.setDescription("Super mega hiper fullwypas ksiazka");
        book1.setAmount("5");

        Book book2 = new Book();
        book2.setCategory(category3);
        book2.setAuthor(author2);
        book2.setTitle("Pan Lodowego Ogrodu");
        book2.setIsbn("5555");
        book2.setRating(5);
        book2.setReleaseDate(LocalDate.of(1500,10,10));
        book2.setAddedDate(LocalDate.now());
        book2.setDescription("Super mega hiper fullwypas ksiazka");
        book2.setAmount("5");

        Book book3 = new Book();
        book3.setCategory(category4);
        book3.setAuthor(author3);
        book3.setTitle("Pani Jeziorna");
        book3.setIsbn("2222");
        book3.setRating(5);
        book3.setReleaseDate(LocalDate.of(1500,10,10));
        book3.setAddedDate(LocalDate.now());
        book3.setDescription("Super mega hiper fullwypas ksiazka");
        book3.setAmount("5");

        Book book4 = new Book();
        book4.setCategory(category4);
        book4.setAuthor(author4);
        book4.setTitle("Gamedec Zabaweczki");
        book4.setIsbn("2222");
        book4.setRating(3);
        book4.setReleaseDate(LocalDate.of(1500,10,10));
        book4.setAddedDate(LocalDate.now());
        book4.setDescription("Super mega hiper fullwypas ksiazka");
        book4.setAmount("5");

        try {
            crudFacade.create(book1);
            crudFacade.create(book2);
            crudFacade.create(book3);
            crudFacade.create(book4);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}