package com.romullogirardi.checker.model;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.romullogirardi.checker.model.Enums.KnowledgeArea;
 
public class PDFGenerator {
 
	//CONSTANTS
	public final String PDF_FILE_NAME = "D:\\ROMULLO\\WORKSPACES\\workspace_jse\\ENEMChecker\\generated_files\\generated_file.pdf";
	private final String MAP_IMAGE_FILE_NAME = "D:\\ROMULLO\\WORKSPACES\\workspace_jse\\ENEMChecker\\assets\\logo_map.jpg";
//	public final String PDF_FILE_NAME = "/home/fmce779/GIRARDI/java/workspace_jse_pessoal/ENEMChecker/generated_files/generated_file.pdf";
//	private final String MAP_IMAGE_FILE_NAME = "/home/fmce779/GIRARDI/java/workspace_jse_pessoal/ENEMChecker/assets/logo_map.jpg";
	private final int NUMBER_OF_COLUMNS_CELLS = 15;
	private final int CELL_WIDTH = 35;
	private final int CELL_HEIGHT = 15;
	private final float TITLE_FONT_SIZE = 15; 
	private final float DEFAULT_FONT_SIZE = 12; 
	private final float QUESTION_FONT_SIZE = 7; 
	
	//VARIABLES
	Document document;
	int questionIndex = 0;
	
	//METHOD TO CREATE A PDF FILE
	public void generatePdf() throws DocumentException, IOException {
		
		document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(PDF_FILE_NAME));
        document.open();
        mountPDFContent();
        document.close();
    }
    
	private void mountPDFContent()  throws DocumentException, IOException {

		//Adicionar a logo do MAP
		Image logoMAP = Image.getInstance(MAP_IMAGE_FILE_NAME);
		logoMAP.setAlignment(Element.ALIGN_CENTER);
        document.add(logoMAP);
        
		//Adicionar o título (TODO: Centralizar)
		document.add(new Paragraph(" "));
		Paragraph title = new Paragraph("INTENSIVO ENEM 2018 - RESULTADO DO 2º SIMULADO", new Font(FontFamily.TIMES_ROMAN, TITLE_FONT_SIZE, 1, BaseColor.BLACK));
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
		document.add(new Paragraph(" "));
		document.add(new Paragraph(" "));

		//Adicionar o nome do aluno
		document.add(new Paragraph("Nome: " + QuestionsChecker.getInstance().getStudentName(), new Font(FontFamily.TIMES_ROMAN, DEFAULT_FONT_SIZE, 0, BaseColor.BLACK)));
		document.add(new Paragraph("Idioma: " + QuestionsChecker.getInstance().getForeignLanguage().toString(), new Font(FontFamily.TIMES_ROMAN, DEFAULT_FONT_SIZE, 0, BaseColor.BLACK)));
		document.add(new Paragraph(" "));
		
		//Adicionar a tabela de questões
		PdfPTable table = new PdfPTable(NUMBER_OF_COLUMNS_CELLS);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.setTotalWidth(NUMBER_OF_COLUMNS_CELLS * CELL_WIDTH);
		table.setLockedWidth(true);
		
		while(questionIndex < 176) {
			
			//Putting row
			table.addCell(createCell((questionIndex + 1) + ". " + QuestionsChecker.getInstance().getQuestions().get(questionIndex).showResult(),
					QuestionsChecker.getInstance().getQuestions().get(questionIndex++).isRight()));
			table.addCell(createCell((questionIndex + 1) + ". " + QuestionsChecker.getInstance().getQuestions().get(questionIndex).showResult(),
					QuestionsChecker.getInstance().getQuestions().get(questionIndex++).isRight()));
			table.addCell(createCell((questionIndex + 1) + ". " + QuestionsChecker.getInstance().getQuestions().get(questionIndex).showResult(),
					QuestionsChecker.getInstance().getQuestions().get(questionIndex++).isRight()));
			table.addCell(createCell((questionIndex + 1) + ". " + QuestionsChecker.getInstance().getQuestions().get(questionIndex).showResult(),
					QuestionsChecker.getInstance().getQuestions().get(questionIndex++).isRight()));
			table.addCell(createCell((questionIndex + 1) + ". " +  QuestionsChecker.getInstance().getQuestions().get(questionIndex).showResult(),
					QuestionsChecker.getInstance().getQuestions().get(questionIndex++).isRight()));
		}
		document.add(table);
		document.add(new Paragraph("LEGENDA: Resposta do gabarito / Resposta marcada no cartão-resposta", new Font(FontFamily.TIMES_ROMAN, QUESTION_FONT_SIZE, 0, BaseColor.BLACK)));
		
		//Adicionar o resultado
		document.add(new Paragraph(" "));
		document.add(new Paragraph("Linguagens e suas Tecnologias: " + String.format("%.2f",QuestionsChecker.getInstance().getPoints(KnowledgeArea.LANGUAGES)) + " pontos", new Font(FontFamily.TIMES_ROMAN, DEFAULT_FONT_SIZE, 0, BaseColor.BLACK)));
		document.add(new Paragraph("Ciências Humanas e suas Tecnologias: " + String.format("%.2f", QuestionsChecker.getInstance().getPoints(KnowledgeArea.HUMAN_SCIENCE)) + " pontos", new Font(FontFamily.TIMES_ROMAN, DEFAULT_FONT_SIZE, 0, BaseColor.BLACK)));
		document.add(new Paragraph("Ciências da Natureza e suas Tecnologias: " + String.format("%.2f",QuestionsChecker.getInstance().getPoints(KnowledgeArea.NATURAL_SCIENCE)) + " pontos", new Font(FontFamily.TIMES_ROMAN, DEFAULT_FONT_SIZE, 0, BaseColor.BLACK)));
		document.add(new Paragraph("Matemática e suas Tecnologias: " + String.format("%.2f",QuestionsChecker.getInstance().getPoints(KnowledgeArea.MATHEMATICS)) + " pontos", new Font(FontFamily.TIMES_ROMAN, DEFAULT_FONT_SIZE, 0, BaseColor.BLACK)));
		document.add(new Paragraph("MÉDIA DAS QUESTÕES OBJETIVAS: " + String.format("%.2f", QuestionsChecker.getInstance().getPointsAverage()) + " pontos", new Font(FontFamily.TIMES_ROMAN, DEFAULT_FONT_SIZE, 1, BaseColor.BLACK)));
	}
	
	private PdfPCell createCell(String text, boolean isRight) {
		
		PdfPCell cell = new PdfPCell(new Paragraph(text, new Font(FontFamily.TIMES_ROMAN, QUESTION_FONT_SIZE, 0, (isRight) ? BaseColor.BLUE : BaseColor.RED)));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell.setFixedHeight(CELL_HEIGHT);
		return cell;
	}
	
	public void openGeneratedFile() {
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File(PDF_FILE_NAME);
		        Desktop.getDesktop().open(myFile);
		    } catch (IOException e) {
		        System.out.println("Este computador não possui um leitor de PDF instalado");
		    }
		}
	}
}