import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Converter extends JFrame implements ActionListener {
	JComboBox<String> unitComboBox;
	private JLabel temperatureLabel, result1, result2;
	private JTextField temptEntry;
	private JButton btnConvert;
	private final String[] units = { "Celsius", "Kelvin", "Fahrenheit" };

	Converter() {
		this.setTitle("Temperature converter");
		this.setSize(900, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon image = new ImageIcon("icon.png");
		this.setIconImage(image.getImage());
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// Background Image
		ImageIcon backgroundImage = new ImageIcon("background.jpg");
		Background background = new Background(backgroundImage.getImage());
		background.setLayout(new GridBagLayout());
		this.setContentPane(background);

		// Font for larger size
		Font font = new Font("Arial", Font.PLAIN, 20);

		// Temperature Label
		temperatureLabel = new JLabel("Enter temperature:");
		temperatureLabel.setFont(font);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		background.add(temperatureLabel, gbc);

		// Temperature Entry
		temptEntry = new JTextField(10);
		temptEntry.setFont(font);
		gbc.gridx = 1;
		background.add(temptEntry, gbc);

		// Unit ComboBox
		unitComboBox = new JComboBox<>(units);
		unitComboBox.setFont(font);
		unitComboBox.setBackground(new Color(0xE2DFD0));

		gbc.gridx = 0;
		gbc.gridy = 1;
		background.add(unitComboBox, gbc);

		// Convert Button
		btnConvert = new JButton("Convert");
		btnConvert.setFont(font);
		btnConvert.setFocusable(false);

		btnConvert.setBackground(new Color(0xE2DFD0));
		gbc.gridx = 1;
		background.add(btnConvert, gbc);
		btnConvert.addActionListener(this);

		// Results Labels
		result1 = new JLabel("");
		result1.setFont(font);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		background.add(result1, gbc);

		result2 = new JLabel("");
		result2.setFont(font);
		gbc.gridy = 3;
		background.add(result2, gbc);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConvert) {
			try {
				double temperature = Double.parseDouble(temptEntry.getText());
				String originalUnit = (String) unitComboBox.getSelectedItem();

				double celsius = 0, fahrenheit = 0, kelvin = 0;

				switch (originalUnit) {
				case "Celsius":
					fahrenheit = celToFah(temperature);
					kelvin = celToKel(temperature);
					showResult("Fahrenheit", "Kelvin", fahrenheit, kelvin);
					break;
				case "Fahrenheit":
					celsius = fahToCel(temperature);
					kelvin = fahToKel(temperature);
					showResult("Celsius", "Kelvin", celsius, kelvin);
					break;
				case "Kelvin":
					celsius = kelToCel(temperature);
					fahrenheit = kelToFah(temperature);
					showResult("Celsius", "Fahrenheit", celsius, fahrenheit);
					break;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Please enter a valid temperature value.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	double celToFah(double celsius) {
		return (celsius * 9 / 5) + 32;
	}

	double celToKel(double celsius) {
		return celsius + 273.15;
	}

	double fahToCel(double fahrenheit) {
		return (fahrenheit - 32) * 5 / 9;
	}

	double fahToKel(double fahrenheit) {
		return (fahrenheit - 32) / 1.8 + 273.15;
	}

	double kelToCel(double kelvin) {
		return kelvin - 273.15;
	}

	double kelToFah(double kelvin) {
		return (kelvin - 273.15) * 1.8 + 32;
	}

	void showResult(String firstText, String secondText, double firstResult, double secondResult) {
		result1.setText(String.format("%s: %.2f°", firstText, firstResult));
		result2.setText(String.format("%s: %.2f°", secondText, secondResult));
	}
}
