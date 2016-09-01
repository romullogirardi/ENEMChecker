package com.romullogirardi.checker.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.romullogirardi.checker.model.Enums.Discipline;
import com.romullogirardi.checker.model.Enums.ForeignLanguage;
import com.romullogirardi.checker.model.Enums.KnowledgeArea;
import com.romullogirardi.checker.model.Enums.QuestionOptionLetters;
import com.romullogirardi.checker.model.Question;
import com.romullogirardi.checker.model.QuestionsChecker;

public class MainWindow {

	private JFrame frame;
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
		QuestionsChecker.getInstance().setStudentName("Rômullo Girardi Moreira");

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
		QuestionsChecker.getInstance().setForeignLanguage(ForeignLanguage.SPANISH);

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
		/*1*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*2*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*3*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*4*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*5*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*6*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*7*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*8*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*9*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.E));
		/*10*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*11*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.E));
		/*12*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.E));
		/*13*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*14*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*15*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*16*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.E));
		/*17*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*18*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*19*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*20*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.E));
		/*21*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*22*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*23*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*24*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*25*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*26*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*27*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*28*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*29*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*30*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*31*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*32*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*33*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*34*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*35*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*36*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*37*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*38*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		/*39*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*40*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*41*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*42*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.A));
		/*43*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.C));
		/*44*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.D));
		/*45*/questions.add(new Question(KnowledgeArea.HUMAN_SCIENCE, Discipline.HUMAN_SCIENCE, QuestionOptionLetters.B));
		//Ciências da Natureza e suas Tecnologias - 46 a 90
		/*46*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.B));
		/*47*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*48*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.B));
		/*49*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*50*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.C));
		/*51*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.C));
		/*52*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.C));
		/*53*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*54*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.C));
		/*55*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.A));
		/*56*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.B));
		/*57*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.A));
		/*58*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.B));
		/*59*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.C));
		/*60*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*61*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*62*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.E));
		/*63*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.E));
		/*64*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.A));
		/*65*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.B));
		/*66*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.B));
		/*67*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.A));
		/*68*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.B));
		/*69*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.E));
		/*70*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*71*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.C));
		/*72*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*73*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.C));
		/*74*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
		/*75*/questions.add(new Question(KnowledgeArea.NATURAL_SCIENCE, Discipline.NATURAL_SCIENCE, QuestionOptionLetters.D));
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
			/*91*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
			/*92*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
			/*93*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
			/*94*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
			/*95*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
		}
		//Espanhol
		else {
			/*91*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
			/*92*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
			/*93*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
			/*94*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
			/*95*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		}
		/*96*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		/*97*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*98*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*99*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*100*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
		/*101*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		/*102*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*103*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*104*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*105*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
		/*106*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		/*107*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*108*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*109*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*110*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
		/*111*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		/*112*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*113*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*114*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*115*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
		/*116*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		/*117*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*118*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*119*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*120*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
		/*121*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		/*122*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*123*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*124*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*125*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
		/*126*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		/*127*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*128*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*129*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*130*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
		/*131*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.A));
		/*132*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.B));
		/*133*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.C));
		/*134*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.D));
		/*135*/questions.add(new Question(KnowledgeArea.LANGUAGES, Discipline.LANGUAGES, QuestionOptionLetters.E));
		//Matemática e suas Tecnologias - 136 a 180
		/*136*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*137*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*138*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*139*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*140*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*141*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*142*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*143*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*144*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*145*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*146*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.A));
		/*147*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*148*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*149*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*150*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.A));
		/*151*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*152*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*153*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*154*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.A));
		/*155*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.A));
		/*156*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*157*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*158*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));
		/*159*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*160*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));
		/*161*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*162*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*163*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.A));
		/*164*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*165*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));
		/*166*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.VAZIA));
		/*167*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*168*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*169*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*170*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));
		/*171*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.A));
		/*172*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*173*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*174*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*175*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));
		/*176*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.A));
		/*177*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.B));
		/*178*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.C));
		/*179*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.D));
		/*180*/questions.add(new Question(KnowledgeArea.MATHEMATICS, Discipline.MATHEMATICS, QuestionOptionLetters.E));

		return questions;
	}
}