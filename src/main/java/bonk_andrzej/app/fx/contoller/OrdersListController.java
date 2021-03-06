package bonk_andrzej.app.fx.contoller;

import bonk_andrzej.app.fx.modelFx.OrdersListModel;
import bonk_andrzej.app.fx.view.*;
import bonk_andrzej.app.utils.DialogsUtils;
import bonk_andrzej.app.utils.FxmlUtils;
import bonk_andrzej.app.utils.exception.ApplicationException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class OrdersListController {

    @FXML
    private ComboBox readerComboBox;
    @FXML
    private ComboBox titleComboBox;
    @FXML
    private TableView<BookOrdersFx> ordersTableView;
    @FXML
    private TableColumn<BookOrdersFx, String> titleColumn;
    @FXML
    private TableColumn<BookOrdersFx, AuthorFx> authorColumn;
    @FXML
    private TableColumn<BookOrdersFx, CategoryFx> categoryColumn;
    @FXML
    private TableColumn<BookOrdersFx, ReaderFx> readerColumn;
    @FXML
    private TableColumn<BookOrdersFx, Number> allReturnedBooksColumn;
    @FXML
    private TableColumn<BookOrdersFx, String> numberBorrowedColumn;
    @FXML
    private TableColumn<BookOrdersFx, Number> leftToReturnColumn;
    @FXML
    private TableColumn<BookOrdersFx, LocalDate> lenderDateColumn;
    @FXML
    private TableColumn<BookOrdersFx, LocalDate> returnDateColumn;
    @FXML
    private TableColumn<BookOrdersFx, LocalDate> actualReturnDateColumn;
    @FXML
    private TableColumn<BookOrdersFx, BookOrdersFx> deleteColumn;
    @FXML
    private TableColumn<BookOrdersFx, BookOrdersFx> editColumn;
    private OrdersListModel ordersListModel;

    @FXML
    public void initialize() {
        ordersListModel = new OrdersListModel();
        try {
            ordersListModel.initAllObservableList();

        } catch (ApplicationException e) {
            DialogsUtils.errorDialogs(e.getMessage());
        }
        bindProperties();
    }

    @FXML
    private void filterOnActionComboBox() {
        ordersListModel.setFilterOrders();
    }

    @FXML
    private void clearSurnameComboBox() {
        readerComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void clearTitleComboBox() {
        titleComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void bindProperties() {
        bindColumns();
        bindComboBoxes();
        setDeleteColumn();
        setUpdateColumn();
    }

    private void setDeleteColumn() {
        deleteColumn.setCellFactory(param -> new TableCell<BookOrdersFx, BookOrdersFx>() {
            Button button = FxmlUtils.createButton(this.getClass(), "/icons/delete.png");

            @Override
            protected void updateItem(BookOrdersFx item, boolean isEmpty) {
                super.updateItem(item, isEmpty);
                button.setAlignment(Pos.CENTER);
                if (isEmpty) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> {
                    try {
                        if (item.getAmountBooksToReturn() <= 0) {
                            ordersListModel.deleteOrders(item);
                        } else {
                            DialogsUtils.specificBooksOrdersError("error.number.books.returned");
                        }
                    } catch (ApplicationException e) {
                        DialogsUtils.errorDialogs(e.getMessage());
                    }
                });
            }
        });
    }

    private void setUpdateColumn() {
        editColumn.setCellFactory(param -> new TableCell<BookOrdersFx, BookOrdersFx>() {
            Button button = FxmlUtils.createButton(this.getClass(), "/icons/edit.png");

            @Override
            protected void updateItem(BookOrdersFx item, boolean isEmpty) {
                super.updateItem(item, isEmpty);

                if (isEmpty) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> {
                    FXMLLoader loader = FxmlUtils.getLoader("/fxml/EditBookOrder.fxml");
                    Scene scene = null;
                    try {
                        scene = new Scene(loader.load());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    EditBooksOrdersController editBookOrderController = loader.getController();
                    editBookOrderController.getOrderModel().setBookOrdersFxObjectProperty(item);
                    editBookOrderController.bindProperties();
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    initialize();
                });
            }
        });
    }



    private void bindComboBoxes() {
        titleComboBox.setItems(ordersListModel.getBookFxObservableList());
        readerComboBox.setItems(ordersListModel.getReaderFxObservableList());
        titleComboBox.valueProperty().bindBidirectional(ordersListModel.bookFxProperty());
        readerComboBox.valueProperty().bindBidirectional(ordersListModel.readerFxProperty());
    }

    private void bindColumns() {
        ordersTableView.setItems(ordersListModel.getBookOrdersFxObservableList());
        titleColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().bookTitleProperty());
        authorColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().authorFxProperty());
        categoryColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().categoryFxProperty());
        readerColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().readerFxProperty());
        numberBorrowedColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().amountAllBorrowedBooksProperty());
        allReturnedBooksColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().amountAllReturnedBooksProperty());
        leftToReturnColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().amountBooksToReturnProperty());
        lenderDateColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().lenderDateProperty());
        returnDateColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().returnDateProperty());
        actualReturnDateColumn.setCellValueFactory(infoInCell -> infoInCell.getValue().actualDateOfReturnProperty());
        deleteColumn.setCellValueFactory(infoInCell -> new SimpleObjectProperty<>(infoInCell.getValue()));
        editColumn.setCellValueFactory(infoInCell -> new SimpleObjectProperty<>(infoInCell.getValue()));
    }
}
