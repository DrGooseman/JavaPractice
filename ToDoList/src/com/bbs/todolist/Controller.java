package com.bbs.todolist;

import com.bbs.todolist.datamodel.ToDoItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<ToDoItem> todoItems;

    @FXML
    private ListView<ToDoItem> todoListView;

    @FXML
    private TextArea itemDetailTextArea;



    public void initialize(){
        ToDoItem item1 = new ToDoItem("Buy milk", "Buy whole milk from Perekrestok.", LocalDate.of(2020, Month.APRIL, 21));
        ToDoItem item2 = new ToDoItem("Make pirozhki", "Make delicious pirozhki s myasom.", LocalDate.of(2020, Month.APRIL, 23));
        ToDoItem item3 = new ToDoItem("Buy turtles", "The wife wants turtles, go buy some.", LocalDate.of(2020, Month.APRIL, 27));
        ToDoItem item4 = new ToDoItem("Water the cat", "The cat is thirsty, water her.", LocalDate.of(2020, Month.MAY, 2));
        ToDoItem item5 = new ToDoItem("Defeat the baby", "The baby needs to be defeated.", LocalDate.of(2020, Month.MAY, 6));

        todoItems = new ArrayList<>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void handleClickListView(){
        ToDoItem item = todoListView.getSelectionModel().getSelectedItem();
        StringBuilder sb = new StringBuilder(item.getDetails());
        sb.append("\n\n\n\n");
        sb.append("Due: ");
        sb.append(item.getDeadline().toString());
        itemDetailTextArea.setText(sb.toString());
    }
}
