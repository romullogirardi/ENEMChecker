package com.romullogirardi.checker.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.romullogirardi.checker.model.AnswersCardReader;
import com.romullogirardi.checker.model.Enums.Discipline;
import com.romullogirardi.checker.model.Enums.ForeignLanguage;
import com.romullogirardi.checker.model.Enums.KnowledgeArea;
import com.romullogirardi.checker.model.Enums.QuestionOptionLetters;
import com.romullogirardi.checker.model.Question;
import com.romullogirardi.checker.model.QuestionsChecker;

public class MainWindow {

	private JFrame frame;
	private String answersCardAbsolutePath1 = null;
	private String answersCardAbsolutePath2 = null;
	private ArrayList<ButtonGroup> questionRadioButtonsGroups = new ArrayList<ButtonGroup>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		frame.getContentPane().add(contentPanel);
		
		//Initialize student name
		JPanel namePanel = new JPanel();
		namePanel.add(new JLabel("Nome:\t"));
		JTextField nameTextField = new JTextField(40);
		namePanel.add(nameTextField);
		contentPanel.add(namePanel);

		//Initialize student foreign language
		JPanel languagePanel = new JPanel();
		languagePanel.add(new JLabel("Idioma:\t"));
		ButtonGroup languageRadioButtonsGroup = new ButtonGroup();
		JRadioButton optionEnglish = new JRadioButton("Inglês");
		optionEnglish.setSelected(true);
		languagePanel.add(optionEnglish);
		languageRadioButtonsGroup.add(optionEnglish);
		JRadioButton optionSpanish = new JRadioButton("Espanhol");
		languagePanel.add(optionSpanish);
		languageRadioButtonsGroup.add(optionSpanish);
		contentPanel.add(languagePanel);

		//Initialize first day answers card
		JPanel answersCardPanel1 = new JPanel();
		answersCardPanel1.add(new JLabel("Cartão resposta - 1° dia (1 a 90):\t"));
		JTextField answersCardTextField1 = new JTextField(20);
		answersCardPanel1.add(answersCardTextField1);
		JButton selectCardButton1 = new JButton("Selecionar cartão");
		answersCardPanel1.add(selectCardButton1);
		selectCardButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(contentPanel);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            answersCardAbsolutePath1 = file.getAbsolutePath();
		            answersCardTextField1.setText(file.getName());
		        }
			}
		});
		
		JButton loadAnswersButton1 = new JButton("Carregar respostas");
		answersCardPanel1.add(loadAnswersButton1);
		loadAnswersButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(answersCardAbsolutePath1 == null)
					System.out.println("Nenhum cartão resposta foi selecionado");
				else {
					AnswersCardReader reader = new AnswersCardReader(answersCardAbsolutePath1);
					for(int index = 1; index <= 90; index++) {
						QuestionOptionLetters optionSelected = reader.getSelectedOption(index);
						ButtonGroup questionRadioButtonsGroup = questionRadioButtonsGroups.get(index - 1);
						Enumeration<AbstractButton> options = questionRadioButtonsGroup.getElements();
						while(options.hasMoreElements()) {
							AbstractButton option = options.nextElement();
							if(optionSelected.equals(QuestionOptionLetters.VAZIA))
								option.setSelected(false);
							else if(optionSelected.toString().equals(option.getText()))
								option.setSelected(true);
						}
					}
				}
			}
		});
		contentPanel.add(answersCardPanel1);

		//Initialize second day answers card
		JPanel answersCardPanel2 = new JPanel();
		answersCardPanel2.add(new JLabel("Cartão resposta - 2° dia (91 a 180):\t"));
		JTextField answersCardTextField2 = new JTextField(20);
		answersCardPanel2.add(answersCardTextField2);
		JButton selectCardButton2 = new JButton("Selecionar cartão");
		answersCardPanel2.add(selectCardButton2);
		selectCardButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(contentPanel);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            answersCardAbsolutePath2 = file.getAbsolutePath();
		            answersCardTextField2.setText(file.getName());
		        }
			}
		});
		
		JButton loadAnswersButton2 = new JButton("Carregar respostas");
		answersCardPanel2.add(loadAnswersButton2);
		loadAnswersButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(answersCardAbsolutePath2 == null)
					System.out.println("Nenhum cartão resposta foi selecionado");
				else {
					AnswersCardReader reader = new AnswersCardReader(answersCardAbsolutePath2);
					for(int index = 91; index <= 180; index++) {
						QuestionOptionLetters optionSelected = reader.getSelectedOption(index);
						ButtonGroup questionRadioButtonsGroup = questionRadioButtonsGroups.get(index - 1);
						Enumeration<AbstractButton> options = questionRadioButtonsGroup.getElements();
						while(options.hasMoreElements()) {
							AbstractButton option = options.nextElement();
							if(optionSelected.equals(QuestionOptionLetters.VAZIA))
								option.setSelected(false);
							else if(optionSelected.toString().equals(option.getText()))
								option.setSelected(true);
						}
					}
				}
			}
		});
		contentPanel.add(answersCardPanel2);

		//Initialize selected options
		final List<QuestionOptionLetters> selectedOptions = new ArrayList<QuestionOptionLetters>();
		for(int index = 0; index < 180; index++) {
			JPanel questionPanel = new JPanel();
			questionPanel.add(new JLabel(index + 1 + ".\t"));
			ButtonGroup questionRadioButtonsGroup = new ButtonGroup();
			JRadioButton optionA = new JRadioButton("A");
			questionPanel.add(optionA);
			questionRadioButtonsGroup.add(optionA);
			JRadioButton optionB = new JRadioButton("B");
			questionPanel.add(optionB);
			questionRadioButtonsGroup.add(optionB);
			JRadioButton optionC = new JRadioButton("C");
			questionPanel.add(optionC);
			questionRadioButtonsGroup.add(optionC);
			JRadioButton optionD = new JRadioButton("D");
			questionPanel.add(optionD);
			questionRadioButtonsGroup.add(optionD);
			JRadioButton optionE = new JRadioButton("E");
			questionPanel.add(optionE);
			questionRadioButtonsGroup.add(optionE);
		
			contentPanel.add(questionPanel);
			questionRadioButtonsGroups.add(questionRadioButtonsGroup);
		}

		JButton submitButton = new JButton("Enviar");
		contentPanel.add(submitButton);		
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//Setting student foreign language
				QuestionsChecker.getInstance().setStudentName(nameTextField.getText());

				//Setting student foreign language
				Enumeration<AbstractButton> languages = languageRadioButtonsGroup.getElements();
				while(languages.hasMoreElements()) {
					AbstractButton option = languages.nextElement();
					if(option.isSelected()) {
						QuestionsChecker.getInstance().setForeignLanguage(ForeignLanguage.fromString(option.getText()));
					}
				}

				//Setting questions
				List<Question> questions = initializeQuestions();
				QuestionsChecker.getInstance().setQuestions(questions);		

				//Setting selected options
				for(ButtonGroup questionButtonGroup : questionRadioButtonsGroups) {
					
					Enumeration<AbstractButton> options = questionButtonGroup.getElements();
					boolean emptyQuestion = true;
					while(options.hasMoreElements()) {
						AbstractButton option = options.nextElement();
						if(option.isSelected()) {
							selectedOptions.add(QuestionOptionLetters.valueOf(option.getText()));
							emptyQuestion = false;
						}
					}
					if(emptyQuestion) {
						selectedOptions.add(QuestionOptionLetters.VAZIA);
					}
				}
				QuestionsChecker.getInstance().setSelectedOptions(selectedOptions);
				
				//Showing result
				QuestionsChecker.getInstance().showResult();
		        
		        //Fechar janela
		        frame.dispose();
			}
		});
		
		//Adicionar o scroll vertical
		JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.getContentPane().add(scrollPane);
	}
	
	private List<Question> initializeQuestions() {
		
		List<Question> questions = new ArrayList<Question>();
		//Ciências Humanas e suas Tecnologias - 1 a 45
		/*1*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*2*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*3*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*4*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*5*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*6*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*7*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*8*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*9*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.E));
		/*10*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*11*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*12*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*13*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*14*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*15*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*16*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*17*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*18*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*19*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*20*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*21*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*22*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*23*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*24*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*25*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*26*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*27*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*28*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*29*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*30*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.VAZIA));
		/*31*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*32*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*33*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*34*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*35*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*36*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*37*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*38*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*39*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*40*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*41*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*42*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*43*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*44*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*45*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		//Ciências da Natureza e suas Tecnologias - 46 a 90
		/*46*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.B));
		/*47*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.B));
		/*48*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*49*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*50*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.A));
		/*51*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.A));
		/*52*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.A));
		/*53*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.B));
		/*54*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.C));
		/*55*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*56*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.A));
		/*57*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.C));
		/*58*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.C));
		/*59*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.A));
		/*60*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.A));
		/*61*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.B));
		/*62*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.B));
		/*63*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.C));
		/*64*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.A));
		/*65*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.B));
		/*66*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*67*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*68*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*69*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.E));
		/*70*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.C));
		/*71*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.E));
		/*72*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*73*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.E));
		/*74*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.C));
		/*75*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*76*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*77*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*78*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*79*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*80*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*81*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*82*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*83*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*84*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*85*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*86*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*87*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*88*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*89*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		/*90*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.VAZIA));
		//Linguagens e suas Tecnologias - 91 a 135
		//Inglês
		if(QuestionsChecker.getInstance().getForeignLanguage().equals(ForeignLanguage.ENGLISH)) {
			/*91*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
			/*92*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
			/*93*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
			/*94*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
			/*95*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		}
		//Espanhol
		else {
			/*91*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
			/*92*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
			/*93*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
			/*94*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
			/*95*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		}
		/*96*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.VAZIA));
		/*97*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.VAZIA));
		/*98*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.VAZIA));
		/*99*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.VAZIA));
		/*100*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.VAZIA));
		/*101*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.VAZIA));
		/*102*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.VAZIA));
		/*103*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.VAZIA));
		/*104*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.VAZIA));
		/*105*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.VAZIA));
		/*106*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
		/*107*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*108*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*109*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
		/*110*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*111*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		/*112*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*113*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		/*114*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
		/*115*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*116*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*117*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*118*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*119*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
		/*120*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*121*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*122*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*123*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		/*124*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*125*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*126*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*127*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*128*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*129*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*130*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		/*131*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*132*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*133*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		/*134*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*135*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		//Matemática e suas Tecnologias - 136 a 180
		/*136*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*137*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));
		/*138*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*139*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*140*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*141*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.A));
		/*142*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.A));
		/*143*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*144*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*145*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));
		/*146*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*147*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*148*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*149*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*150*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));
		/*151*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));
		/*152*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));
		/*153*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.A));
		/*154*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*155*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*156*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*157*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*158*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));
		/*159*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));
		/*160*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*161*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));
		/*162*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*163*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*164*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.A));
		/*165*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*166*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*167*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*168*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*169*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*170*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*171*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*172*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*173*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*174*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*175*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*176*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*177*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*178*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*179*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*180*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));

		return questions;
	}
}