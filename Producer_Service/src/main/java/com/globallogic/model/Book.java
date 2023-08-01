package com.globallogic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class Book {

     public Book(Integer id, String string, double d) {
		// TODO Auto-generated constructor stub
	}
	private Integer bookId;
     private String bookName;
     private Double bookCost;
}