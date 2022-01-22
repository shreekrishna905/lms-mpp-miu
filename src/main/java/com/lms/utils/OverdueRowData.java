package com.lms.utils;

public class OverdueRowData {
        String isbn;
        String title;
        String copyNo;
        String libraryMember;
        String dueDate;
        String fine;

        public OverdueRowData(String libraryMember, String isbn, String title, String copyNo, String dueDate, String fine){
            this.isbn = isbn;
            this.title = title;
            this.copyNo =copyNo;
            this.dueDate = dueDate;
            this.libraryMember = libraryMember;
            this.fine = fine;
        }

        public String getIsbn() {
            return isbn;
        }



        public String getTitle() {
            return title;
        }



        public String getCopyNo() {
            return copyNo;
        }



        public String getLibraryMember() {
            return libraryMember;
        }


        public String getDueDate() {
            return dueDate;
        }

        public String getFine() {
            return fine;
        }
    }