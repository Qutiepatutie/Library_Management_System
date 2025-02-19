package com.mycompany.java_ui.Student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mycompany.java_library.library_function.libraryFunctions;
import com.mycompany.java_ui.MainPage.ComponentStyles;

public class BorrowBooks implements MouseListener{

    JFrame borrowBooksFrame = new JFrame();

    JTextField studNameField = new JTextField();
    JTextField studIDField = new JTextField();

    JLabel emptyStudNameMessage = new JLabel();
    JLabel emptyStudIDMessage = new JLabel();

    JComboBox<String> choices = new JComboBox<>(); 

    ComponentStyles.CustomRoundedButton2 confirmButton = new ComponentStyles.CustomRoundedButton2("Confirm");
    ComponentStyles.CustomRoundedButton2 resetButton = new ComponentStyles.CustomRoundedButton2("Reset");

    JPanel mainPanel = new JPanel();
    ArrayList<JPanel> bookPanelsList = new ArrayList<>();
    ArrayList<String> borrowBookList = new ArrayList<>();
    ArrayList<String> borrowedBooksList = new ArrayList<>();

    HashMap<JPanel, JLabel> bookLabelsList = new HashMap<>();
    JPanel borrowedBooksPanel = new JPanel();

    ComponentStyles.RoundedPanel homeButtonPanel = new ComponentStyles.RoundedPanel(20);

    libraryFunctions libFuncs = new libraryFunctions();

    public BorrowBooks(){

        borrowBooksFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        borrowBooksFrame.setSize(950,600);
        borrowBooksFrame.setResizable(false);
        borrowBooksFrame.setTitle("Borrow Books");
        borrowBooksFrame.setLayout(new BorderLayout());
        borrowBooksFrame.setLocationRelativeTo(null);

        ComponentStyles.BackgroundPanel bgPanel = new ComponentStyles.BackgroundPanel("logInPageBG.jpg");
        bgPanel.setPreferredSize(new Dimension(950,600));
        bgPanel.setLayout(null);

        //--------------------------------INPUT PANEL------------------------------//
        ComponentStyles.RoundedPanel inputPanel = new ComponentStyles.RoundedPanel(20);
        inputPanel.setBounds(110,350,725,200);
        inputPanel.setLayout(null);
        inputPanel.setBackground(new Color(103,120,97,160));

        JLabel studNameLabel = new JLabel("Enter Student Name:");
        studNameLabel.setBounds(30,20,200,25);
        studNameLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        studNameLabel.setForeground(Color.decode("#fbf8ef"));

        studNameField.setBounds(30,50,200,35);
        studNameField.setBackground(Color.decode("#e5efda"));
        studNameField.setBorder(new EmptyBorder(5,5,0,0));
        studNameField.setFont(new Font("Calibri", Font.PLAIN, 17));

        emptyStudNameMessage.setBounds(30,85,135,15);
        emptyStudNameMessage.setForeground(Color.decode("#BE3144"));
        emptyStudNameMessage.setFont(new Font(null, Font.ITALIC,11));
        //emptyStudNameMessage.setVisible(false);

        JLabel studIDLabel = new JLabel("Enter Student ID:");
        studIDLabel.setBounds(30,100,165,25);
        studIDLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        studIDLabel.setForeground(Color.decode("#fbf8ef"));

        studIDField.setBounds(30,130,200,35);
        studIDField.setBackground(Color.decode("#e5efda"));
        studIDField.setBorder(new EmptyBorder(5,5,0,0));
        studIDField.setFont(new Font("Calibri", Font.PLAIN, 17));

        emptyStudIDMessage.setBounds(30,165,135,15);
        emptyStudIDMessage.setForeground(Color.decode("#BE3144"));
        emptyStudIDMessage.setFont(new Font(null, Font.ITALIC,11));
        //emptyStudIDMessage.setVisible(false);

        JLabel borrowBookLabel = new JLabel("Borrowed Book/s:");
        borrowBookLabel.setBounds(300,20,200,25);
        borrowBookLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        borrowBookLabel.setForeground(Color.decode("#fbf8ef"));

        borrowedBooksPanel.setLayout(new GridLayout(0,1));
        borrowedBooksPanel.setBackground(Color.decode("#e5efda"));

        
        JScrollPane borrowedBookScrollPane = new JScrollPane(borrowedBooksPanel);
        borrowedBookScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        borrowedBookScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        borrowedBookScrollPane.setBounds(300,50,250,50);
        borrowedBookScrollPane.setOpaque(false);
        borrowedBookScrollPane.getViewport().setOpaque(false);
        borrowedBookScrollPane.setBorder(null);

        confirmButton.setBounds(300,130,120,40);
        confirmButton.addMouseListener(this);

        resetButton.setBounds(430,130,120,40);
        resetButton.addMouseListener(this);
        //-------------------------------------------------------------------------//

        //-------------------------------MAIN PANEL--------------------------------//
        ComponentStyles.RoundedPanel upperLine = new ComponentStyles.RoundedPanel(10);
        upperLine.setBounds(10,10,910,2);

        ComponentStyles.RoundedPanel bottomLine = new ComponentStyles.RoundedPanel(10);
        bottomLine.setBounds(10,330,910,2);

        mainPanel.setLayout(new GridLayout(0,4,10,10));
        mainPanel.setBorder(new EmptyBorder(10,10,15,10));
        mainPanel.setOpaque(false);

        addPanel();

        JScrollPane mainScrollPane = new JScrollPane(mainPanel);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        mainScrollPane.setBounds(20,20,890,300);
        mainScrollPane.setOpaque(false);
        mainScrollPane.getViewport().setOpaque(false);
        mainScrollPane.setBorder(null);
        //--------------------------------------------------------------------------//

        //--------------------------HOME BUTTON-------------------------------------//
        ImageIcon origIcon = new ImageIcon("home.png");
        Image scaledIcon = origIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon homeIcon = new ImageIcon(scaledIcon);

        homeButtonPanel.setBounds(10,510,40,40);
        homeButtonPanel.setBackground(new Color(252,255,242,120));
        homeButtonPanel.addMouseListener(this);

        JLabel homeIconLabel = new JLabel(homeIcon);
        homeButtonPanel.add(homeIconLabel);

        //---------------------------------------------------------------------------//

        inputPanel.add(studNameLabel);
        inputPanel.add(studNameField);
        inputPanel.add(emptyStudNameMessage);
        inputPanel.add(studIDField);
        inputPanel.add(studIDLabel);
        inputPanel.add(emptyStudIDMessage);
        inputPanel.add(borrowBookLabel);
        inputPanel.add(borrowedBookScrollPane);
        inputPanel.add(confirmButton);
        inputPanel.add(resetButton);

        bgPanel.add(inputPanel);
        bgPanel.add(mainScrollPane);
        bgPanel.add(upperLine);
        bgPanel.add(bottomLine);
        bgPanel.add(homeButtonPanel);

        borrowBooksFrame.add(bgPanel);
        borrowBooksFrame.setVisible(true);
    }

    public void addPanel(){

        File bookCoversFolder = new File("BookCovers");

        File[] bookCoversFolderFiles = bookCoversFolder.listFiles(File::isFile);
        if(bookCoversFolderFiles != null){
            for(File cover : bookCoversFolderFiles){

                ImageIcon origIcon = new ImageIcon(cover.getPath());
                Image scaledIcon = origIcon.getImage().getScaledInstance(160, 230, Image.SCALE_SMOOTH);
                ImageIcon bookCover = new ImageIcon(scaledIcon);
                
                JPanel books = new JPanel();
                books.setPreferredSize(new Dimension(200,290));
                books.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
                books.setOpaque(true);
                books.setBackground(new Color(255,255,255,0));
                books.addMouseListener(this);
                
                String[] bookDescription = cover.getName().split("[_.]");

                String title = bookDescription[0];
                String author = bookDescription[1];

                if(libFuncs.checkIfBorrowed(title, author)){
                    borrowedBooksList.add(title + " - " + author);
                    continue;
                }

                JLabel booksLabel = new JLabel();
                booksLabel.setIcon(bookCover);
                booksLabel.setText("<html><div style='text-align: center;'>" + title + "<br><i>" + author + "</i></div></html>");
                booksLabel.setFont(new Font("Serif", Font.BOLD,15));
                booksLabel.setHorizontalTextPosition(JLabel.CENTER);
                booksLabel.setVerticalTextPosition(JLabel.BOTTOM);
                booksLabel.setHorizontalAlignment(JLabel.CENTER);
                booksLabel.setIconTextGap(5);

                books.add(booksLabel);
                mainPanel.add(books);
                
                bookPanelsList.add(books);
                bookLabelsList.put(books,booksLabel);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource()==homeButtonPanel){
            borrowBooksFrame.dispose();
            StudentPage.studentFrame.setVisible(true);
        }

        if(e.getSource()==resetButton){

            studNameField.setText(null);
            studNameField.requestFocusInWindow();
            studIDField.setText(null);
            emptyStudNameMessage.setText(null);
            emptyStudIDMessage.setText(null);
            borrowedBooksPanel.removeAll();
            borrowBookList.clear();
            borrowedBooksPanel.revalidate();
            borrowedBooksPanel.repaint();
            mainPanel.revalidate();
            mainPanel.repaint();
        }

        if(e.getSource()==confirmButton){
            
            String studName = studNameField.getText();
            String studID = studIDField.getText();

            emptyStudNameMessage.setText(null);
            emptyStudIDMessage.setText(null);

            if(studName.isEmpty()){
                emptyStudNameMessage.setText("Please enter student name");
                return;
            }

            if(studID.isEmpty()){
                emptyStudIDMessage.setText("Please enter student ID");
                return;
            }

            if(borrowBookList.size() == 0){
                JOptionPane.showMessageDialog(borrowBooksFrame, "Your list is empty!");
                return;
            }
            
            for(String elements : borrowBookList){
                String book = elements.replaceAll("<[^>]*>", "").trim();

                String[] bookInfo = book.split("&emsp;-&emsp;");
                String title = bookInfo[0];
                String author = bookInfo[1];

                if(!studName.isEmpty() && !studID.isEmpty()){
                    if(libFuncs.checkIfBorrowed(title, author)){
                        borrowedBooksList.add(title + " - " + author);
                        continue;
                    }

                    libFuncs.borrowBooks(title, author, studName, studID);
                }
            }

            JPanel borrowedListPanel = new JPanel();
            borrowedListPanel.setLayout(new GridLayout(0,1));
            borrowedListPanel.add(new JLabel("<html>The book/s is/are already borrowed<br><br></html>"));

            int borrowedBooksListSize = borrowBookList.size();
            if(borrowedBooksList.size() != 0){

                for(String books : borrowedBooksList){
                    JLabel borrowedBooks = new JLabel();
                    borrowedBooks.setText("<html>" + books + "<br></html>");

                    borrowedListPanel.add(borrowedBooks);
                    borrowedBooksListSize--;
                }

                JOptionPane.showMessageDialog(borrowBooksFrame, borrowedListPanel);
                borrowedBooksList.clear();

            } else if(borrowedBooksList.size() == 0 && borrowedBooksListSize != 0) {
                JOptionPane.showMessageDialog(borrowBooksFrame, "Book/s Borrowed Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                studNameField.setText(null);
                studNameField.requestFocusInWindow();
                studIDField.setText(null);
                emptyStudNameMessage.setText(null);
                emptyStudIDMessage.setText(null);
                borrowedBooksPanel.removeAll();
                borrowBookList.clear();
                borrowedBooksPanel.revalidate();
                borrowedBooksPanel.repaint();

            }
        }

        for(JPanel book : bookPanelsList){
            if(e.getSource()==book){
                JLabel bookLabel = bookLabelsList.get(book);
                
                String text = bookLabel.getText().replaceAll("<[^>]*>", "_").trim();

                String[] info = text.split("__");

                JLabel infoLabel = new JLabel("<html>" + info[1] + "&emsp;-&emsp;" + info[2] + "</html>");
                infoLabel.setFont(new Font("Serif", Font.BOLD,12));
                infoLabel.setHorizontalTextPosition(JLabel.LEFT);

                if(bookLabel != null){
                    if(borrowBookList.contains(infoLabel.getText())){
                        JOptionPane.showMessageDialog(borrowBooksFrame, "This Book is Already in your List");
                        return;
                    }

                    borrowedBooksPanel.add(infoLabel);
                    borrowBookList.add(infoLabel.getText());

                    borrowedBooksPanel.revalidate();
                    borrowedBooksPanel.repaint();
                }
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==homeButtonPanel){
            homeButtonPanel.setBackground(new Color(252,255,242,60));
        }

        for(JPanel panels : bookPanelsList){
            if(e.getSource()==panels){
                panels.setOpaque(true);
                panels.setBackground(new Color(255,255,255,80));

                panels.getParent().repaint();
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==homeButtonPanel){
            homeButtonPanel.setBackground(new Color(252,255,242,120));
        }

        for(JPanel panels : bookPanelsList){
            if(e.getSource()==panels){
                panels.setBackground(new Color(255,255,255,0));
                panels.setOpaque(false);
                
                panels.getParent().repaint();
            }
        }
    }
    public static void main(String[] args){
        new BorrowBooks();
    }
}
