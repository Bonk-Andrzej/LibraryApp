package app.fx.view;

import javafx.beans.property.*;

import java.time.LocalDate;

public class LenderBooksFx {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private ObjectProperty<LocalDate> lenderDate = new SimpleObjectProperty(LocalDate.now());
    private  ObjectProperty<LocalDate> returnDate = new SimpleObjectProperty(LocalDate.of(2020,10,10));
    private ObjectProperty<LocalDate> actualDateOfReturn = new SimpleObjectProperty(LocalDate.of(2020,10,10));

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public LocalDate getLenderDate() {
        return lenderDate.get();
    }

    public ObjectProperty<LocalDate> lenderDateProperty() {
        return lenderDate;
    }

    public void setLenderDate(LocalDate lenderDate) {
        this.lenderDate.set(lenderDate);
    }

    public LocalDate getReturnDate() {
        return returnDate.get();
    }

    public ObjectProperty<LocalDate> returnDateProperty() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate.set(returnDate);
    }

    public LocalDate getActualDateOfReturn() {
        return actualDateOfReturn.get();
    }

    public ObjectProperty<LocalDate> actualDateOfReturnProperty() {
        return actualDateOfReturn;
    }

    public void setActualDateOfReturn(LocalDate actualDateOfReturn) {
        this.actualDateOfReturn.set(actualDateOfReturn);
    }
}