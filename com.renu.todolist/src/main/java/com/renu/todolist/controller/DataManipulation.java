package com.renu.todolist.controller;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class DataManipulation {

private Integer todolistid;
private String description;
private String status;
private List<SecondTable> secondTables;


}
 