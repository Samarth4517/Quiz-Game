import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Kbc extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JPanel homePanel;
    private JPanel congratsPanel;
    private JLabel questionLabel;
    private JLabel prizeLabel;
    private JButton[] optionButtons;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private List<Question> gameQuestions;
    private Random random;
    private CardLayout cardLayout;
    private boolean messageShown = false;

    private static final String[][] ALL_QUESTIONS = {          
	    {"What is the capital of India?", "New Delhi", "Mumbai", "Kolkata", "Chennai"},
            {"Who is known as the Father of the Nation in India?", "Mahatma Gandhi", "Jawaharlal Nehru", "Subhash Chandra Bose", "Bhagat Singh"},
            {"What is the national animal of India?", "Tiger", "Lion", "Elephant", "Peacock"},
            {"Which Indian city is known as the Pink City?", "Jaipur", "Udaipur", "Jodhpur", "Agra"},
            {"Which river is known as the 'Sorrow of Bihar'?", "Kosi", "Ganga", "Yamuna", "Brahmaputra"},
            {"Which planet is known as the Red Planet?", "Mars", "Earth", "Venus", "Jupiter"},
            {"Who wrote the Indian national anthem?", "Rabindranath Tagore", "Bankim Chandra Chatterjee", "Sarojini Naidu", "Subhash Chandra Bose"},
            {"Who was the first Prime Minister of India?", "Jawaharlal Nehru", "Mahatma Gandhi", "Indira Gandhi", "Rajendra Prasad"},
            {"What is the smallest state in India by area?", "Goa", "Sikkim", "Tripura", "Mizoram"},
            {"Who was the first President of India?", "Rajendra Prasad", "S. Radhakrishnan", "Zakir Husain", "V.V. Giri"},
            {"Which is the largest state in India by area?", "Rajasthan", "Madhya Pradesh", "Maharashtra", "Uttar Pradesh"},
            {"Who invented the computer?", "Charles Babbage", "Alan Turing", "John von Neumann", "Bill Gates"},
            {"Which is the longest river in India?", "Ganga", "Yamuna", "Brahmaputra", "Godavari"},
            {"What is the national flower of India?", "Lotus", "Rose", "Marigold", "Sunflower"},
            {"Who is known as the Missile Man of India?", "A.P.J. Abdul Kalam", "Vikram Sarabhai", "Homi J. Bhabha", "Satish Dhawan"},
            {"Which monument is known as the symbol of love?", "Taj Mahal", "Qutub Minar", "Red Fort", "India Gate"},
            {"Who is the author of 'Harry Potter' series?", "J.K. Rowling", "J.R.R. Tolkien", "C.S. Lewis", "George R.R. Martin"},
            {"Which festival is known as the festival of lights?", "Diwali", "Holi", "Eid", "Christmas"},
            {"Who was the first man to walk on the moon?", "Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "Michael Collins"},
            {"What is the national sport of India?", "Hockey", "Cricket", "Kabaddi", "Football"},
            {"Which element has the chemical symbol 'O'?", "Oxygen", "Gold", "Osmium", "Oganesson"},
            {"Which country is known as the Land of the Rising Sun?", "Japan", "China", "Korea", "Thailand"},
            {"Who wrote the play 'Hamlet'?", "William Shakespeare", "Christopher Marlowe", "Ben Jonson", "John Webster"},
            {"Which ocean is the largest?", "Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"},
            {"Which planet is known as the Morning Star?", "Venus", "Mercury", "Mars", "Jupiter"},
            {"Who painted the Mona Lisa?", "Leonardo da Vinci", "Vincent van Gogh", "Pablo Picasso", "Claude Monet"},
            {"What is the hardest natural substance on Earth?", "Diamond", "Gold", "Iron", "Platinum"},
            {"Which is the largest desert in the world?", "Sahara Desert", "Arabian Desert", "Gobi Desert", "Kalahari Desert"},
            {"What is the tallest mountain in the world?", "Mount Everest", "K2", "Kangchenjunga", "Lhotse"},
            {"Who is known as the Iron Man of India?", "Sardar Vallabhbhai Patel", "Subhash Chandra Bose", "Lal Bahadur Shastri", "Bhagat Singh"},
            {"Which is the smallest planet in our solar system?", "Mercury", "Venus", "Mars", "Neptune"},
            {"What is the square root of 64?", "8", "6", "7", "9"},
            {"Who is known as the Nightingale of India?", "Sarojini Naidu", "Lata Mangeshkar", "Asha Bhosle", "Indira Gandhi"},
            {"Which vitamin is known as ascorbic acid?", "Vitamin C", "Vitamin A", "Vitamin B", "Vitamin D"},
            {"What is the capital of Japan?", "Tokyo", "Kyoto", "Osaka", "Hiroshima"},
            {"Which gas is most abundant in the Earth's atmosphere?", "Nitrogen", "Oxygen", "Carbon Dioxide", "Hydrogen"},
            {"Who is the author of 'Pride and Prejudice'?", "Jane Austen", "Emily Brontë", "Charlotte Brontë", "George Eliot"},
            {"Which planet is known as the Blue Planet?", "Earth", "Neptune", "Uranus", "Saturn"},
            {"Who discovered penicillin?", "Alexander Fleming", "Louis Pasteur", "Robert Koch", "Joseph Lister"},
            {"Which Indian city is known as the City of Joy?", "Kolkata", "Mumbai", "Delhi", "Bangalore"},
            {"What is the main ingredient in traditional Indian 'Dal'?", "Lentils", "Rice", "Wheat", "Maize"},
            {"Who was the first woman Prime Minister of India?", "Indira Gandhi", "Pratibha Patil", "Sushma Swaraj", "Sarojini Naidu"},
            {"Which metal is known as quicksilver?", "Mercury", "Silver", "Platinum", "Gold"},
            {"What is the currency of Japan?", "Yen", "Won", "Dollar", "Euro"},
            {"Which city is known as the Silicon Valley of India?", "Bangalore", "Hyderabad", "Chennai", "Pune"},
            {"What is the boiling point of water?", "100°C", "90°C", "80°C", "70°C"},
            {"Who wrote 'Romeo and Juliet'?", "William Shakespeare", "Jane Austen", "Mark Twain", "Charles Dickens"},
            {"What is the national bird of India?", "Peacock", "Sparrow", "Crow", "Parrot"},
            {"Which element is known as the building block of life?", "Carbon", "Oxygen", "Hydrogen", "Nitrogen"},
            {"Who invented the telephone?", "Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "James Watt"},
            {"Which river is the longest in the world?", "Nile", "Amazon", "Yangtze", "Mississippi"},
            {"Which country is known as the Land of Kangaroos?", "Australia", "New Zealand", "South Africa", "India"},
            {"Who is known as the Light of Asia?", "Gautama Buddha", "Mahavira", "Ashoka", "Chandragupta Maurya"},
            {"What is the chemical formula for water?", "H2O", "CO2", "O2", "NaCl"},
            {"Who was the first person to climb Mount Everest?", "Edmund Hillary", "Tenzing Norgay", "George Mallory", "Reinhold Messner"},
            {"Which is the largest continent?", "Asia", "Africa", "Europe", "North America"},
            {"What is the main ingredient in 'Sushi'?", "Rice", "Wheat", "Barley", "Oats"},
            {"Who is known as the King of Pop?", "Michael Jackson", "Elvis Presley", "Prince", "David Bowie"},
            {"What is the main ingredient in 'Guacamole'?", "Avocado", "Tomato", "Onion", "Lime"},
            {"Who wrote 'To Kill a Mockingbird'?", "Harper Lee", "Mark Twain", "Ernest Hemingway", "F. Scott Fitzgerald"},
            {"What is the smallest bone in the human body?", "Stapes", "Femur", "Tibia", "Humerus"},
            {"Which is the longest bone in the human body?", "Femur", "Humerus", "Tibia", "Fibula"},
            {"What is the currency of the United Kingdom?", "Pound Sterling", "Dollar", "Euro", "Yen"},
            {"Who painted 'Starry Night'?", "Vincent van Gogh", "Claude Monet", "Pablo Picasso", "Leonardo da Vinci"},
            {"Which planet is known as the Gas Giant?", "Jupiter", "Saturn", "Uranus", "Neptune"},
            {"Who is the author of '1984'?", "George Orwell", "Aldous Huxley", "Ray Bradbury", "J.K. Rowling"},
            {"What is the capital of France?", "Paris", "Rome", "Berlin", "Madrid"},
            {"Who is known as the Queen of Pop?", "Madonna", "Lady Gaga", "Beyoncé", "Katy Perry"},
            {"Which ocean is the smallest?", "Arctic Ocean", "Atlantic Ocean", "Indian Ocean", "Southern Ocean"},
            {"Who wrote 'Moby Dick'?", "Herman Melville", "Jules Verne", "Mark Twain", "Ernest Hemingway"},
            {"What is the main ingredient in 'Pasta'?", "Wheat", "Rice", "Barley", "Corn"},
            {"Who invented the light bulb?", "Thomas Edison", "Nikola Tesla", "James Watt", "Alexander Graham Bell"},
            {"Which country is known as the Land of the Midnight Sun?", "Norway", "Sweden", "Finland", "Iceland"},
            {"What is the capital of Italy?", "Rome", "Milan", "Venice", "Florence"},
            {"Who is the author of 'The Great Gatsby'?", "F. Scott Fitzgerald", "Ernest Hemingway", "John Steinbeck", "Mark Twain"},
            {"Which gas is known as laughing gas?", "Nitrous Oxide", "Carbon Dioxide", "Hydrogen", "Oxygen"},
            {"What is the hardest natural substance?", "Diamond", "Gold", "Silver", "Platinum"},
            {"What is the largest organ in the human body?", "Skin", "Heart", "Liver", "Lungs"},
            {"Who discovered America?", "Christopher Columbus", "Leif Erikson", "Vasco da Gama", "Ferdinand Magellan"},
            {"Which element is known as the King of Metals?", "Gold", "Silver", "Platinum", "Iron"},
            {"What is the capital of Canada?", "Ottawa", "Toronto", "Vancouver", "Montreal"},
            {"Who is known as the Bard of Avon?", "William Shakespeare", "John Milton", "Geoffrey Chaucer", "Edmund Spenser"},
            {"What is the smallest planet?", "Mercury", "Venus", "Earth", "Mars"},
            {"Which element is found in all organic compounds?", "Carbon", "Hydrogen", "Oxygen", "Nitrogen"},
            {"What is the capital of Russia?", "Moscow", "Saint Petersburg", "Vladivostok", "Novosibirsk"},
            {"Who wrote 'The Odyssey'?", "Homer", "Virgil", "Ovid", "Sophocles"},
            {"Which is the largest island in the world?", "Greenland", "Australia", "Borneo", "Madagascar"},
            {"What is the capital of Germany?", "Berlin", "Munich", "Frankfurt", "Hamburg"},
            {"Who invented the airplane?", "Wright Brothers", "Thomas Edison", "Alexander Graham Bell", "Nikola Tesla"},
            {"Which planet is known as the Evening Star?", "Venus", "Mercury", "Mars", "Jupiter"},
            {"Who painted 'The Last Supper'?", "Leonardo da Vinci", "Vincent van Gogh", "Pablo Picasso", "Claude Monet"},
            {"What is the capital of Spain?", "Madrid", "Barcelona", "Seville", "Valencia"},
            {"Who is known as the God of Cricket?", "Sachin Tendulkar", "Brian Lara", "Ricky Ponting", "Don Bradman"},
            {"What is the chemical formula for salt?", "NaCl", "H2O", "CO2", "O2"},
            {"What is the largest planet in our solar system?", "Jupiter", "Saturn", "Uranus", "Neptune"},
            {"Who is known as the Queen of Soul?", "Aretha Franklin", "Whitney Houston", "Diana Ross", "Tina Turner"},
            {"What is the capital of Australia?", "Canberra", "Sydney", "Melbourne", "Brisbane"},
            {"Who wrote 'The Catcher in the Rye'?", "J.D. Salinger", "Mark Twain", "Ernest Hemingway", "F. Scott Fitzgerald"},
            {"Which gas is essential for human respiration?", "Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"},
            {"Who painted 'The Scream'?", "Edvard Munch", "Vincent van Gogh", "Pablo Picasso", "Claude Monet"},
            {"What is the currency of China?", "Yuan", "Yen", "Won", "Dollar"},
            {"Who wrote 'The Divine Comedy'?", "Dante Alighieri", "Geoffrey Chaucer", "John Milton", "Homer"},
            {"Which country is known as the Land of the Thunder Dragon?", "Bhutan", "Nepal", "Thailand", "Mongolia"},
            {"Who is known as the father of modern physics?", "Albert Einstein", "Isaac Newton", "Galileo Galilei", "Niels Bohr"},
            {"Which vitamin is known as the sunshine vitamin?", "Vitamin D", "Vitamin A", "Vitamin B", "Vitamin C"},
            {"Who invented the steam engine?", "James Watt", "Thomas Edison", "Nikola Tesla", "Alexander Graham Bell"},
            {"What is the national fruit of India?", "Mango", "Banana", "Apple", "Orange"},
            {"What is the capital of Egypt?", "Cairo", "Alexandria", "Giza", "Luxor"},
            {"Who wrote 'War and Peace'?", "Leo Tolstoy", "Fyodor Dostoevsky", "Anton Chekhov", "Vladimir Nabokov"},
            {"Which element has the chemical symbol 'Au'?", "Gold", "Silver", "Platinum", "Iron"},
            {"What is the largest mammal?", "Blue Whale", "Elephant", "Giraffe", "Hippopotamus"},
            {"Who discovered gravity?", "Isaac Newton", "Albert Einstein", "Galileo Galilei", "Niels Bohr"},
            {"What is the capital of the USA?", "Washington, D.C.", "New York", "Los Angeles", "Chicago"},
            {"Who wrote 'The Iliad'?", "Homer", "Virgil", "Ovid", "Sophocles"},
            {"Which element has the atomic number 1?", "Hydrogen", "Helium", "Oxygen", "Carbon"},
            {"What is the largest organ in the human body?", "Skin", "Heart", "Liver", "Lungs"},
            {"What is the capital of South Africa?", "Pretoria", "Cape Town", "Johannesburg", "Durban"},
            {"Who is known as the father of computer science?", "Alan Turing", "Charles Babbage", "John von Neumann", "Bill Gates"},
            {"What is the national tree of India?", "Banyan", "Peepal", "Neem", "Mango"},
            {"Who wrote 'A Brief History of Time'?", "Stephen Hawking", "Carl Sagan", "Richard Feynman", "Neil deGrasse Tyson"},
            {"Which gas is used in balloons to make them float?", "Helium", "Oxygen", "Nitrogen", "Hydrogen"},
            {"What is the largest internal organ in the human body?", "Liver", "Heart", "Lungs", "Kidneys"},
            {"Who is known as the father of geometry?", "Euclid", "Pythagoras", "Archimedes", "Aristotle"},
            {"What is the main ingredient in 'Paella'?", "Rice", "Wheat", "Barley", "Oats"},
            {"Who discovered penicillin?", "Alexander Fleming", "Louis Pasteur", "Robert Koch", "Joseph Lister"},
            {"Which planet is known as the Morning Star?", "Venus", "Mercury", "Mars", "Jupiter"},
            {"Who wrote 'Pride and Prejudice'?", "Jane Austen", "Emily Brontë", "Charlotte Brontë", "George Eliot"},
            {"Which country is known as the Land of the Rising Sun?", "Japan", "China", "Korea", "Thailand"},
            {"What is the hardest natural substance?", "Diamond", "Gold", "Silver", "Platinum"},
            {"Which river is the longest in the world?", "Nile", "Amazon", "Yangtze", "Mississippi"},
            {"Which country is known as the Land of the Midnight Sun?", "Norway", "Sweden", "Finland", "Iceland"},
            {"Who is known as the Queen of Pop?", "Madonna", "Lady Gaga", "Beyoncé", "Katy Perry"},
            {"Who wrote 'The Great Gatsby'?", "F. Scott Fitzgerald", "Ernest Hemingway", "John Steinbeck", "Mark Twain"},
            {"What is the chemical formula for water?", "H2O", "CO2", "O2", "NaCl"},
            {"Who invented the telephone?", "Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "James Watt"},
            {"Which is the largest desert in the world?", "Sahara Desert", "Arabian Desert", "Gobi Desert", "Kalahari Desert"},
            {"What is the tallest mountain in the world?", "Mount Everest", "K2", "Kangchenjunga", "Lhotse"},
            {"Who is known as the Iron Man of India?", "Sardar Vallabhbhai Patel", "Subhash Chandra Bose", "Lal Bahadur Shastri", "Bhagat Singh"},
            {"Which is the smallest planet in our solar system?", "Mercury", "Venus", "Mars", "Neptune"},
            {"Who was the first President of India?", "Rajendra Prasad", "S. Radhakrishnan", "Zakir Husain", "V.V. Giri"},
            {"What is the national animal of India?", "Tiger", "Lion", "Elephant", "Peacock"},
            {"Who invented the computer?", "Charles Babbage", "Alan Turing", "John von Neumann", "Bill Gates"},
            {"Which is the longest river in India?", "Ganga", "Yamuna", "Brahmaputra", "Godavari"},
            {"What is the national flower of India?", "Lotus", "Rose", "Marigold", "Sunflower"},
            {"Who is known as the Missile Man of India?", "A.P.J. Abdul Kalam", "Vikram Sarabhai", "Homi J. Bhabha", "Satish Dhawan"},
            {"Which monument is known as the symbol of love?", "Taj Mahal", "Qutub Minar", "Red Fort", "India Gate"},
            {"Who is the author of 'Harry Potter' series?", "J.K. Rowling", "J.R.R. Tolkien", "C.S. Lewis", "George R.R. Martin"},
            {"Which festival is known as the festival of lights?", "Diwali", "Holi", "Eid", "Christmas"},
            {"Who was the first man to walk on the moon?", "Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "Michael Collins"},
            {"What is the national sport of India?", "Hockey", "Cricket", "Kabaddi", "Football"},
            {"Which element has the chemical symbol 'O'?", "Oxygen", "Gold", "Osmium", "Oganesson"},
            {"Which country is known as the Land of the Rising Sun?", "Japan", "China", "Korea", "Thailand"},
            {"Who wrote the play 'Hamlet'?", "William Shakespeare", "Christopher Marlowe", "Ben Jonson", "John Webster"},
            {"Which ocean is the largest?", "Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"},
            {"Which planet is known as the Morning Star?", "Venus", "Mercury", "Mars", "Jupiter"},
            {"Who painted the Mona Lisa?", "Leonardo da Vinci", "Vincent van Gogh", "Pablo Picasso", "Claude Monet"},
            {"What is the hardest natural substance on Earth?", "Diamond", "Gold", "Iron", "Platinum"},
            {"Which is the largest desert in the world?", "Sahara Desert", "Arabian Desert", "Gobi Desert", "Kalahari Desert"},
            {"What is the tallest mountain in the world?", "Mount Everest", "K2", "Kangchenjunga", "Lhotse"},
            {"Who is known as the Iron Man of India?", "Sardar Vallabhbhai Patel", "Subhash Chandra Bose", "Lal Bahadur Shastri", "Bhagat Singh"},
            {"Which is the smallest planet in our solar system?", "Mercury", "Venus", "Mars", "Neptune"},
            {"What is the square root of 64?", "8", "6", "7", "9"},
            {"Who is known as the Nightingale of India?", "Sarojini Naidu", "Lata Mangeshkar", "Asha Bhosle", "Indira Gandhi"},
            {"Which vitamin is known as ascorbic acid?", "Vitamin C", "Vitamin A", "Vitamin B", "Vitamin D"},
            {"What is the capital of Japan?", "Tokyo", "Kyoto", "Osaka", "Hiroshima"},
            {"Which gas is most abundant in the Earth's atmosphere?", "Nitrogen", "Oxygen", "Carbon Dioxide", "Hydrogen"},
            {"Who is the author of 'Pride and Prejudice'?", "Jane Austen", "Emily Brontë", "Charlotte Brontë", "George Eliot"},
            {"Which planet is known as the Blue Planet?", "Earth", "Neptune", "Uranus", "Saturn"},
            {"Who discovered penicillin?", "Alexander Fleming", "Louis Pasteur", "Robert Koch", "Joseph Lister"},
            {"Which Indian city is known as the City of Joy?", "Kolkata", "Mumbai", "Delhi", "Bangalore"},
            {"What is the main ingredient in traditional Indian 'Dal'?", "Lentils", "Rice", "Wheat", "Maize"},
            {"Who was the first woman Prime Minister of India?", "Indira Gandhi", "Pratibha Patil", "Sushma Swaraj", "Sarojini Naidu"},
            {"Which metal is known as quicksilver?", "Mercury", "Silver", "Platinum", "Gold"},
            {"What is the currency of Japan?", "Yen", "Won", "Dollar", "Euro"},
            {"Which city is known as the Silicon Valley of India?", "Bangalore", "Hyderabad", "Chennai", "Pune"},
            {"What is the boiling point of water?", "100°C", "90°C", "80°C", "70°C"},
            {"Who wrote 'Romeo and Juliet'?", "William Shakespeare", "Jane Austen", "Mark Twain", "Charles Dickens"},
            {"What is the national bird of India?", "Peacock", "Sparrow", "Crow", "Parrot"},
            {"Which element is known as the building block of life?", "Carbon", "Oxygen", "Hydrogen", "Nitrogen"},
            {"Who invented the telephone?", "Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "James Watt"},
            {"Which river is the longest in the world?", "Nile", "Amazon", "Yangtze", "Mississippi"},
            {"Which country is known as the Land of Kangaroos?", "Australia", "New Zealand", "South Africa", "India"},
            {"Who is known as the Light of Asia?", "Gautama Buddha", "Mahavira", "Ashoka", "Chandragupta Maurya"},
            {"What is the chemical formula for water?", "H2O", "CO2", "O2", "NaCl"},
            {"Who was the first person to climb Mount Everest?", "Edmund Hillary", "Tenzing Norgay", "George Mallory", "Reinhold Messner"},
            {"Which is the largest continent?", "Asia", "Africa", "Europe", "North America"},
            {"What is the main ingredient in 'Sushi'?", "Rice", "Wheat", "Barley", "Oats"},
            {"Who is known as the King of Pop?", "Michael Jackson", "Elvis Presley", "Prince", "David Bowie"},
            {"What is the main ingredient in 'Guacamole'?", "Avocado", "Tomato", "Onion", "Lime"},
            {"Who wrote 'To Kill a Mockingbird'?", "Harper Lee", "Mark Twain", "Ernest Hemingway", "F. Scott Fitzgerald"},
            {"What is the smallest bone in the human body?", "Stapes", "Femur", "Tibia", "Humerus"},
            {"Which is the longest bone in the human body?", "Femur", "Humerus", "Tibia", "Fibula"},
            {"What is the currency of the United Kingdom?", "Pound Sterling", "Dollar", "Euro", "Yen"},
            {"Who painted 'Starry Night'?", "Vincent van Gogh", "Claude Monet", "Pablo Picasso", "Leonardo da Vinci"},
            {"Which planet is known as the Gas Giant?", "Jupiter", "Saturn", "Uranus", "Neptune"},
            {"Who is the author of '1984'?", "George Orwell", "Aldous Huxley", "Ray Bradbury", "J.K. Rowling"},
            {"What is the capital of France?", "Paris", "Rome", "Berlin", "Madrid"},
            {"Who is known as the Queen of Pop?", "Madonna", "Lady Gaga", "Beyoncé", "Katy Perry"},
            {"Which ocean is the smallest?", "Arctic Ocean", "Atlantic Ocean", "Indian Ocean", "Southern Ocean"},
            {"Who wrote 'Moby Dick'?", "Herman Melville", "Jules Verne", "Mark Twain", "Ernest Hemingway"},
            {"What is the main ingredient in 'Pasta'?", "Wheat", "Rice", "Barley", "Corn"},
};

        private static final int[] PRIZE_MONEY = {
            1000, 1000, 3000, 5000, 10000, 10000, 40000, 80000,
            160000, 310000, 640000, 1250000, 2500000, 5000000, 10000000
    };

    private static final Color BACKGROUND_COLOR = new Color(10, 38, 71);
    private static final Color BUTTON_COLOR = new Color(100, 149, 237); // Cornflower Blue
    private static final Color CORRECT_ANSWER_COLOR = new Color(34, 139, 34); // Forest Green
    private static final Color WRONG_ANSWER_COLOR = new Color(220, 20, 60); // Crimson
    private static final Color PRIZE_COLOR = new Color(255, 215, 0); // Gold
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 36);
    private static final Font LOGO_FONT = new Font("Arial", Font.BOLD, 48);
    private static final Font QUESTION_FONT = new Font("Arial", Font.BOLD, 30);
    private static final Font PRIZE_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 20);

    public Kbc() {
        setTitle("Kaun Banega Crorepati");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        initializeHomePanel();
        initializeGamePanel();
        initializeCongratsPanel();

        add(mainPanel);

        random = new Random();
        gameQuestions = getRandomQuestions();
    }

    private void initializeHomePanel() {
        homePanel = new JPanel(new BorderLayout());
        homePanel.setBackground(BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel("Kaun Banega Crorepati", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(100, 10, 50, 10));
        homePanel.add(titleLabel, BorderLayout.NORTH);

        JLabel logoLabel = new JLabel("💰 KBC 💰", SwingConstants.CENTER);
        logoLabel.setFont(LOGO_FONT);
        logoLabel.setForeground(PRIZE_COLOR);
        homePanel.add(logoLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setBackground(BUTTON_COLOR);
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> startGame());
        homePanel.add(startButton, BorderLayout.SOUTH);

        mainPanel.add(homePanel, "Home");
    }

    private void initializeGamePanel() {
        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.setBackground(BACKGROUND_COLOR);

        questionLabel = new JLabel("Question will appear here", SwingConstants.CENTER);
        questionLabel.setFont(QUESTION_FONT);
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        gamePanel.add(questionLabel, BorderLayout.NORTH);

        prizeLabel = new JLabel("Prize: ₹0", SwingConstants.CENTER);
        prizeLabel.setFont(PRIZE_FONT);
        prizeLabel.setForeground(PRIZE_COLOR);
        prizeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gamePanel.add(prizeLabel, BorderLayout.SOUTH);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionsPanel.setBackground(BACKGROUND_COLOR);

        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setFont(BUTTON_FONT);
            optionButtons[i].setForeground(Color.WHITE);
            optionButtons[i].setBackground(BUTTON_COLOR);
            optionButtons[i].setFocusPainted(false);
            optionButtons[i].setActionCommand(String.valueOf(i));
            optionButtons[i].addActionListener(this);
            optionButtons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            optionsPanel.add(optionButtons[i]);
        }

        gamePanel.add(optionsPanel, BorderLayout.CENTER);
        mainPanel.add(gamePanel, "Game");
    }

    private void initializeCongratsPanel() {
        congratsPanel = new JPanel(new BorderLayout());
        congratsPanel.setBackground(BACKGROUND_COLOR);

        JLabel congratsLabel = new JLabel("Congratulations! You won 1 crore!", SwingConstants.CENTER);
        congratsLabel.setFont(TITLE_FONT);
        congratsLabel.setForeground(Color.WHITE);
        congratsLabel.setBorder(BorderFactory.createEmptyBorder(100, 10, 50, 10));
        congratsPanel.add(congratsLabel, BorderLayout.CENTER);

        JLabel footerLabel = new JLabel("Created by harisk.197", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        congratsPanel.add(footerLabel, BorderLayout.SOUTH);

        mainPanel.add(congratsPanel, "Congrats");
    }

    private List<Question> getRandomQuestions() {
        List<Question> questions = new ArrayList<>();
        for (String[] q : ALL_QUESTIONS) {
            questions.add(new Question(q[0], q[1], q[2], q[3], q[4]));
        }
        Collections.shuffle(questions, random);
        return questions.subList(0, Math.min(15, questions.size()));
    }

    private void startGame() {
        score = 0;
        currentQuestionIndex = 0;
        gameQuestions = getRandomQuestions();
        loadNextQuestion();
        cardLayout.show(mainPanel, "Game");
        messageShown = false; // Reset message flag
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < gameQuestions.size()) {
            Question question = gameQuestions.get(currentQuestionIndex);
            questionLabel.setText("<html><div style='text-align: center;'>" + question.getQuestion() + "</div></html>");
            prizeLabel.setText("Prize: ₹" + PRIZE_MONEY[currentQuestionIndex]);

            List<String> options = new ArrayList<>();
            options.add(question.getOptionA());
            options.add(question.getOptionB());
            options.add(question.getOptionC());
            options.add(question.getOptionD());

            // Shuffle options each time a new question is loaded
            Collections.shuffle(options, random);

            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(options.get(i));
                optionButtons[i].setBackground(BUTTON_COLOR); // Reset button color
                optionButtons[i].setEnabled(true); // Enable the buttons
            }
        } else {
            cardLayout.show(mainPanel, "Congrats");
        }
    }

@Override
public void actionPerformed(ActionEvent e) {
    int selectedOption = Integer.parseInt(e.getActionCommand());
    String selectedAnswer = optionButtons[selectedOption].getText();
    String correctAnswer = gameQuestions.get(currentQuestionIndex).getOptionA();

    for (JButton button : optionButtons) {
        button.setEnabled(false); // Disable all buttons after an answer is selected
    }

    if (selectedAnswer.equals(correctAnswer)) {
        optionButtons[selectedOption].setBackground(CORRECT_ANSWER_COLOR);
        score = PRIZE_MONEY[currentQuestionIndex];

        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Timer) e.getSource()).stop();
                currentQuestionIndex++;
                loadNextQuestion();
            }
        }).start();

    } else {
        optionButtons[selectedOption].setBackground(WRONG_ANSWER_COLOR);

        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Timer) e.getSource()).stop();
                if (!messageShown) {
                    messageShown = true;
                    int choice = JOptionPane.showOptionDialog(Kbc.this,
                            "Wrong answer! Game over. Your prize: ₹" + score + "\nDo you want to restart?",
                            "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null,
                            new String[]{"Restart", "Exit"}, "Restart");
                    if (choice == JOptionPane.YES_OPTION) {
                        startGame(); // Restart the game
                    } else {
                        System.exit(0); // Exit the game
                    }
                }
            }
        }).start();
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Kbc().setVisible(true));
    }
}

class Question {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    public Question(String question, String optionA, String optionB, String optionC, String optionD) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }
}
