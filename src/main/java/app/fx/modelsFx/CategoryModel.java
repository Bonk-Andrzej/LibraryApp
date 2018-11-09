package app.fx.modelsFx;

import app.db.dao.CrudFacade;
import app.db.modelsDb.Category;
import app.utils.converter.CategoryConverter;
import app.utils.exceptions.ApplicationException;
import app.fx.view.CategoryFx;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.List;

public class CategoryModel {
//TUTAJ MAM IMPLEMENTACJE METOD CRUDA DO CATEGORY NP


    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();
    private ObjectProperty<CategoryFx> categoryFxObjectProperty = new SimpleObjectProperty<>();
    private TreeItem<String> treeItemRoot = new TreeItem<>();
    private CrudFacade crudFacade = new CrudFacade();

    public void saveCategoryToDB(String categoryName) throws ApplicationException {
        Category categoryToSave = new Category();
        categoryToSave.setName(categoryName);
        crudFacade.create(categoryToSave);
        initializeCategoryFromDB();
    }

    public void deleteCategory() throws ApplicationException {
        Category categoryToDelete = (Category) crudFacade.getById(
                Category.class, getCategoryFxObjectProperty().getId());
        crudFacade.deleteO(categoryToDelete);
        initializeCategoryFromDB();
    }

    public void updateCategoryInDB() throws ApplicationException {
        Category categoryToUpdate = CategoryConverter.convertFromCategoryFxToCategory(
                getCategoryFxObjectProperty());
        crudFacade.update(categoryToUpdate);
        initializeCategoryFromDB();
    }

    public void initializeCategoryFromDB() throws ApplicationException {
        List<Category> categories = crudFacade.getAll(Category.class);
        initializeCategoryFxList(categories);
        initializeItemRoot(categories);
    }

    private void initializeItemRoot(List<Category> categories) {
        treeItemRoot.getChildren().clear();
        categories.forEach(c -> {
            TreeItem<String> categoryItem = new TreeItem<>(c.getName());
            c.getBookListForCategory().forEach(
                    book -> categoryItem.getChildren()
                            .add(new TreeItem<>(book.getTitle())));
            //TODO CO TO JEST! JAK TO DZIALA??
            //TODO UNIKALNE WARTOSCI DLA KATEGORI I RESZTY
            // TODO DLACZEGO BAZA MI SIE WLACZA PRZY KATEGORI I CZY SIE WGL WYLACZA I CZEMU SIE CZASAMI PRZECIAZA
            //TODO CZY HIBERNATE MUSI TAK SIE LADOWAC ZA KAZDYM RAZEM, CZY MOZNA TO JAKOS PRZYSPESZIC - DO SPRAWDZENIA W KATALOGU SQL
            treeItemRoot.getChildren().add(categoryItem);
        });
    }

    private void initializeCategoryFxList(List<Category> categories) {
        categoryFxObservableList.clear();
        categories.forEach(c -> {
            CategoryFx categoryFx = CategoryConverter
                    .convertFromCategoryToCategoryFx(c);
            categoryFxObservableList.add(categoryFx);
        });
    }

    public CrudFacade getCrudFacade() {
        return crudFacade;
    }

    public void setCrudFacade(CrudFacade crudFacade) {
        this.crudFacade = crudFacade;
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }

    public CategoryFx getCategoryFxObjectProperty() {
        return categoryFxObjectProperty.get();
    }

    public ObjectProperty<CategoryFx> categoryFxObjectPropertyProperty() {
        return categoryFxObjectProperty;
    }

    public void setCategoryFxObjectProperty(CategoryFx categoryFxObjectProperty) {
        this.categoryFxObjectProperty.set(categoryFxObjectProperty);
    }

    public TreeItem<String> getTreeItemRoot() {
        return treeItemRoot;
    }

    public void setTreeItemRoot(TreeItem<String> treeItemRoot) {
        this.treeItemRoot = treeItemRoot;
    }

}