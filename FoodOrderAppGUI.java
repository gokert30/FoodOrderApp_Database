import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;


public class mustigui {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JComboBox<String> tableComboBox;

    public mustigui() {
        frame = new JFrame("Database Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        model = new DefaultTableModel();
        table = new JTable(model);

        JButton viewButton = new JButton("View");
        viewButton.addActionListener((ActionEvent e) -> viewData());

        JButton addButton = new JButton("Add");
        addButton.addActionListener((ActionEvent e) -> addData());

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener((ActionEvent e) -> updateData());

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener((ActionEvent e) -> deleteData());
        
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener((ActionEvent e) -> searchData());
        
        

        tableComboBox = new JComboBox<>(new String[]{"restaurants", "orders", "customers", "carriers", "districts"});

        JPanel panel = new JPanel();
        panel.add(viewButton);
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(searchButton);
        panel.add(tableComboBox);

        frame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addData() {
        String selectedTable = (String) tableComboBox.getSelectedItem();
        if (selectedTable == null) return;

        switch (selectedTable) {
            case "restaurants":
                addRestaurant();
                break;
            case "orders":
                addOrder();
                break;
            case "customers":
                addCustomer();
                break;
            case "carriers":
                addCarrier();
                break;
            case "districts":
                addDistrict();
                break;
        }
    }

    private void addRestaurant() {
        String id = JOptionPane.showInputDialog("Enter Restaurant ID:");
        String name = JOptionPane.showInputDialog("Enter Restaurant Name:");
        String manager = JOptionPane.showInputDialog("Enter Manager Name:");
        String location = JOptionPane.showInputDialog("Enter Location:");
        String district_no = JOptionPane.showInputDialog("Enter District Number:");

        try (Connection connection = DriverManager.getConnection(MySQLUser.URL, MySQLUser.USER, MySQLUser.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO restaurants (r_id, R_name, Manager, location, Districts_district_no) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, manager);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, district_no);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Restaurant added successfully!");
                updateTable("SELECT * FROM restaurants", "Restaurants");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to add restaurant.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addOrder() {
        String orderId = JOptionPane.showInputDialog("Enter Order ID:");
        String address = JOptionPane.showInputDialog("Enter Address:");
        String restaurantId = JOptionPane.showInputDialog("Enter Restaurant ID:");
        String telephone = JOptionPane.showInputDialog("Enter Telephone Number:");
        

        try (Connection connection = DriverManager.getConnection(MySQLUser.URL, MySQLUser.USER, MySQLUser.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders (order_id, address, restaurant_id, customer_id) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setInt(1, Integer.parseInt(orderId));
            preparedStatement.setString(2, address);
            preparedStatement.setInt(3, Integer.parseInt(restaurantId));
            preparedStatement.setString(4, telephone);
          

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Order added successfully!");
                updateTable("SELECT * FROM orders", "Orders");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to add order.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addCustomer() {
        String number = JOptionPane.showInputDialog("Enter Telephone Number:");
        String name = JOptionPane.showInputDialog("Enter Customer Name:");
        String address = JOptionPane.showInputDialog("Enter Customer Address:");

        try (Connection connection = DriverManager.getConnection(MySQLUser.URL, MySQLUser.USER, MySQLUser.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers (T_number, Cst_name, CustomerAddress) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, number);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Customer added successfully!");
                updateTable("SELECT * FROM customers", "Customers");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to add customer.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addCarrier() {
        String carrierId = JOptionPane.showInputDialog("Enter Carrier SSN:");
        String salary = JOptionPane.showInputDialog("Enter Carrier Salary:");
        String name = JOptionPane.showInputDialog("Enter Carrier Name:");
        String sex = JOptionPane.showInputDialog("Enter Carrier Sex:");
        String rId = JOptionPane.showInputDialog("Enter Carrier Restaurant ID:");
        String rNo = JOptionPane.showInputDialog("Enter Carrier Restaurant District No");

        try (Connection connection = DriverManager.getConnection(MySQLUser.URL, MySQLUser.USER, MySQLUser.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO carriers (SSN, salary, c_name, sex, Restaurants_R_id, Restaurants_Districts_district_no) VALUES (?, ?, ?,?,?,?)")) {

            preparedStatement.setInt(1, Integer.parseInt(carrierId));
            preparedStatement.setString(2, salary);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, sex);
            preparedStatement.setString(5, rId);
            preparedStatement.setString(6, rNo);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Carrier added successfully!");
                updateTable("SELECT * FROM carriers", "Carriers");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to add carrier.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addDistrict() {
        String districtId = JOptionPane.showInputDialog("Enter District ID:");
        String name = JOptionPane.showInputDialog("Enter District Name:");
        String restaurantNumber = JOptionPane.showInputDialog("Enter Number of Restaurants:");

        try (Connection connection = DriverManager.getConnection(MySQLUser.URL, MySQLUser.USER, MySQLUser.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO districts (district_id, name,restaurants_number) VALUES (?, ?,?)")) {

            preparedStatement.setInt(1, Integer.parseInt(districtId));
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, Integer.parseInt(restaurantNumber));
            

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "District added successfully!");
                updateTable("SELECT * FROM districts", "Districts");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to add district.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable(String query, String tableName) {
        try (Connection connection = DriverManager.getConnection(MySQLUser.URL, MySQLUser.USER, MySQLUser.PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            model.setRowCount(0);
            model.setColumnCount(0);
            for (int column = 1; column <= columnCount; column++) {
                model.addColumn(metaData.getColumnName(column));
            }

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    row[column - 1] = resultSet.getObject(column);
                }
                model.addRow(row);
            }

            frame.setTitle("Data from " + tableName);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteData() {
        String selectedTable = (String) tableComboBox.getSelectedItem();
        if (selectedTable == null) return;

        String id = JOptionPane.showInputDialog("Enter ID of the record to delete:");

        String query = "";
        switch (selectedTable) {
            case "restaurants":
                query = "DELETE FROM restaurants WHERE R_id = ?";
                break;
            case "orders":
                query = "DELETE FROM orders WHERE order_id = ?";
                break;
            case "customers":
                query = "DELETE FROM customers WHERE T_number = ?";
                break;
            case "carriers":
                query = "DELETE FROM carriers WHERE SSN = ?";
                break;
            case "districts":
                query = "DELETE FROM districts WHERE district_id = ?";
                break;
        }

        try (Connection connection = DriverManager.getConnection(MySQLUser.URL, MySQLUser.USER, MySQLUser.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Use setString for T_number since it's not an integer
            if ("customers".equals(selectedTable)) {
                preparedStatement.setString(1, id);
            } else {
                preparedStatement.setInt(1, Integer.parseInt(id));
            }

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, selectedTable + " record deleted successfully!");
                updateTable("SELECT * FROM " + selectedTable, selectedTable.substring(0, 1).toUpperCase() + selectedTable.substring(1));
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to delete record.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Invalid ID format: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void updateData() {
        String selectedTable = (String) tableComboBox.getSelectedItem();
        if (selectedTable == null) return;

        String id = JOptionPane.showInputDialog("Enter ID of the record to update:");

        String query = "";
        String[] inputs;
        switch (selectedTable) {
            case "restaurants":
                inputs = new String[]{"Enter new Restaurant Name:", "Enter new Location:"};
                query = "UPDATE restaurants SET R_name = ?, location = ? WHERE r_id = ?";
                break;
            case "orders":
                inputs = new String[]{"Enter new Customer ID:", "Enter new Restaurant ID:", "Enter new Carrier ID:", "Enter new Order Address:"};
                query = "UPDATE orders SET customer_id = ?, r_id = ?, carrier_id = ?, address = ? WHERE order_id = ?";
                break;
            case "customers":
                inputs = new String[]{"Enter new Customer Name:"};
                query = "UPDATE Customers SET Cst_name = ? WHERE T_number = ?";
                break;
            case "carriers":
                inputs = new String[]{"Enter new Carrier's Restaurant:", "Enter new Carrier's District:", "Enter new Carrier Salary:"};
                query = "UPDATE Carriers SET Restaurants_r_id = ?, Restaurants_Districts_district_no ,salary = ? WHERE SSN = ?";
                break;
            case "districts":
                inputs = new String[]{"Enter new District Name:","Enter the number of Restaurants:"};
                query = "UPDATE Districts SET name = ?, restaurants_number = ? WHERE district_id = ?";
                break;
            default:
                inputs = new String[]{};
        }

        Object[] inputValues = new Object[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            inputValues[i] = JOptionPane.showInputDialog(inputs[i]);
        }

        try (Connection connection = DriverManager.getConnection(MySQLUser.URL, MySQLUser.USER, MySQLUser.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < inputValues.length; i++) {
                preparedStatement.setObject(i + 1, inputValues[i]);
            }
            preparedStatement.setInt(inputValues.length + 1, Integer.parseInt(id));

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, selectedTable + " record updated successfully!");
                updateTable("SELECT * FROM " + selectedTable, selectedTable.substring(0, 1).toUpperCase() + selectedTable.substring(1));
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to update record.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewData() {
        String selectedTable = (String) tableComboBox.getSelectedItem();
        if (selectedTable != null) {
            updateTable("SELECT * FROM " + selectedTable, selectedTable.substring(0, 1).toUpperCase() + selectedTable.substring(1));
        }
    }
    
    private void searchData() {
        String selectedTable = (String) tableComboBox.getSelectedItem();
        if (selectedTable == null) return;

        String id = JOptionPane.showInputDialog("Enter ID of the record to search:");
        

        String query = "";
        switch (selectedTable) {
            case "restaurants":
                query = "SELECT * FROM restaurants WHERE r_id = ?";
                break;
            case "orders":
                query = "SELECT * FROM orders WHERE order_id = ?";
                break;
            case "customers":
                query = "SELECT * FROM customers WHERE T_number = ?";
                break;
            case "carriers":
                query = "SELECT * FROM carriers WHERE SSN = ?";
                break;
            case "districts":
                query = "SELECT * FROM districts WHERE district_id = ?";
                break;
        }

        try (Connection connection = DriverManager.getConnection(MySQLUser.URL, MySQLUser.USER, MySQLUser.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, Integer.parseInt(id));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                model.setRowCount(0);
                model.setColumnCount(0);
                for (int column = 1; column <= columnCount; column++) {
                    model.addColumn(metaData.getColumnName(column));
                }

                while (resultSet.next()) {
                    Object[] row = new Object[columnCount];
                    for (int column = 1; column <= columnCount; column++) {
                        row[column - 1] = resultSet.getObject(column);
                    }
                    model.addRow(row);
                }

                frame.setTitle("Search result for " + selectedTable);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
       
        SwingUtilities.invokeLater(() -> new LoginPage());
    }
}

class LoginPage extends JDialog {
    private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        setTitle("Login");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);

      
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> checkLogin());

       
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.EAST;
        contentPane.add(usernameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        contentPane.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.EAST;
        contentPane.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        contentPane.add(passwordField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        contentPane.add(loginButton, constraints);

        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void checkLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals(MySQLUser.USER) && password.equals(MySQLUser.PASSWORD)) {
           
            SwingUtilities.invokeLater(mustigui::new);
            dispose(); 
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class MySQLUser {
    static final String URL = "jdbc:mysql://localhost:3306/Online_Food_Order";
    static final String USER = "root";
    static final String PASSWORD = "12345678";
}
       
