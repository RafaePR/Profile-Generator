import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Application extends JFrame implements ActionListener{
	
	String[] letterList = {"0","1","2","3","4","5","6","7","8","9","!","@","#","$","&","*","a","b",
			"c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x",
			"y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T",
			"U","V","W","X","Y","Z"};
	
	private String firstName;
	private String lastName;
	private String Department;
	private String emailAddress;
	private String Username;
	private String emailPassword;
	private String userPassword;

	JTextField text1, text2;
	JComboBox comboBox;
	JButton submit;

	public Application() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Profile Generator v1.0");
		this.setLayout(new BorderLayout());
		this.setLocation(320, 150);
		this.setResizable(false);
		this.setSize(700, 300);
		
		JLabel label1 = new JLabel("Profile");
		JLabel label2 = new JLabel("Generator");
		JLabel label3 = new JLabel("v1.0");
		
		JLabel label4 = new JLabel("First Name:");
		label4.setFont(new Font("Consolas", Font.BOLD, 25));
		
		JLabel label5 = new JLabel("Last Name:");
		label5.setFont(new Font("Consolas", Font.BOLD, 25));
		
		JLabel label6 = new JLabel("Department:");
		label6.setFont(new Font("Consolas", Font.BOLD, 25));
		
		label1.setFont(new Font("Consolas", Font.ITALIC, 25));
		label2.setFont(new Font("Consolas", Font.ITALIC, 25));
		label3.setFont(new Font("Consolas", Font.ITALIC, 25));
		
		text1 = new JTextField();
		text1.setPreferredSize(new Dimension(450, 25));
		text1.setFont(new Font("Consolas", Font.ITALIC, 18));
		
		text2 = new JTextField();
		text2.setPreferredSize(new Dimension(450, 25));
		text2.setFont(new Font("Consolas", Font.ITALIC, 18));
		
		String[] departments = {"Select Department", "Human Resources", "IT", 
				"Accounting and Finance", "Marketing", "Research and Development", "Production"};
		
		comboBox = new JComboBox(departments);
		
		submit = new JButton("Submit");
		submit.setFocusable(false);
		submit.addActionListener(this);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.LIGHT_GRAY);
		
		panel1.add(label1);
		panel1.add(label2);
		panel1.add(label3);
		
		panel2.add(label4);
		panel2.add(text1);
		panel2.add(label5);
		panel2.add(text2);
		panel2.add(label6);
		panel2.add(comboBox);
		panel2.add(submit);
		
		this.add(panel1, BorderLayout.WEST);
		this.add(panel2, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public String generatePassword() {
		
		String password;
		
		int len = letterList.length;
		
		int random1 = (int) (Math.random() * len);
		int random2 = (int) (Math.random() * len);
		int random3 = (int) (Math.random() * len);
		int random4 = (int) (Math.random() * len);
		int random5 = (int) (Math.random() * len);
		int random6 = (int) (Math.random() * len);
		int random7 = (int) (Math.random() * len);
		int random8 = (int) (Math.random() * len);

		password = letterList[random1]+letterList[random2]+letterList[random3]+letterList[random4]+
				letterList[random5]+letterList[random6]+letterList[random7]+letterList[random8];

		return password;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == submit) {
			
			this.firstName = text1.getText();
			this.lastName = text2.getText();
			this.Department = (String)comboBox.getSelectedItem();
			this.Username = this.firstName.toLowerCase()+"."+this.lastName.toLowerCase();
			this.emailPassword = generatePassword();
			this.userPassword = generatePassword();
			
			String dep = null;
			
			if (comboBox.getSelectedIndex() == 1)
				dep = "@hr.enterprise.com";
			else if (comboBox.getSelectedIndex() == 2)
				dep = "@it.enterprise.com";
			else if (comboBox.getSelectedIndex() == 3)
				dep = "@af.enterprise.com";
			else if (comboBox.getSelectedIndex() == 4)
				dep = "@mk.enterprise.com";
			else if (comboBox.getSelectedIndex() == 5)
				dep = "@rd.enterprise.com";
			else if (comboBox.getSelectedIndex() == 6)
				dep = "@pd.enterprise.com";
			
			this.emailAddress = this.firstName.toLowerCase()+"."+this.lastName.toLowerCase()+dep;
			
			if (comboBox.getSelectedIndex() == 0 || this.firstName.isBlank() || this.lastName.isBlank()) {
				
				JOptionPane.showMessageDialog(null, "All of the three must be filled.", "Error", 
						JOptionPane.WARNING_MESSAGE);
			}
			else {
				
				File file = new File("users/"+this.firstName+this.lastName+".txt");
				
				try {
					FileWriter fwriter = new FileWriter(file);
					fwriter.write("\nName..............: "+this.firstName+" "+this.lastName+
							"\nDepartment........: "+this.Department+
							"\n\nEmail Address.....: "+this.emailAddress+
							"\nEmail Password....: "+this.emailPassword+
							"\n\nUsername..........: "+this.Username+
							"\nUsername Password.: "+this.userPassword);
					fwriter.flush();
					fwriter.close();
					
					JOptionPane.showMessageDialog(null, "New profile created.", 
							"Success", JOptionPane.INFORMATION_MESSAGE);
					
					text1.setText("");
					text2.setText("");
					
					comboBox.setSelectedIndex(0);
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}