package br.com.automacao.paripassu;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteNovoCadastroAvaliado {

	private static WebDriver driver;
	private static WebDriverWait wait;
	private static final String URL_SITE = "http://teste.vaga-qa.paripassudev.com";
	@BeforeClass
	public static void setUp() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(URL_SITE);
		wait = new WebDriverWait(driver, 30);
	}

	@Test
	public void QuestionarioNovoAvaliado() throws InterruptedException {

		// interrogando os elementos tela de acesso
		WebElement usuario = driver.findElement(By.id("inputUsuario"));
		WebElement senha = driver.findElement(By.id("inputPassword"));
		WebElement entrar = driver.findElement(By.xpath("//button[@class='btn btn-primary ng-binding']"));

		// clicando e preenchendo os campos tela de acesso
		Thread.sleep(500);
		usuario.sendKeys("sandrogoncalves84@yahoo.com.br");
		senha.sendKeys("cf4c97e47f");
		entrar.click();
		Thread.sleep(5000);

		this.parteUM();
		
		Thread.sleep(5000);
		//aplicando o question�rio
		//menu aplica��o de question�rio
		WebElement aplicacaoQuestionario = driver.findElement(By.linkText("Aplica��o de question�rio"));
		aplicacaoQuestionario.click();
		Thread.sleep(500);
		WebElement novaAplicacao = driver.findElement(By.linkText("Nova aplica��o de question�rio"));
		novaAplicacao.click();
		Thread.sleep(5000);
		//seleciona o question�rio cadastrado
		WebElement novoQuestionario = driver.findElement(By.xpath("//a[@ng-click='goToEscolhaAvaliado(questionario)']"));
		novoQuestionario.click();
		Thread.sleep(5000);
		//seleciona o avaliado
		WebElement aplicacaoAvaliado = driver.findElement(By.xpath("//a[@ng-click='goToAplicacao(avaliado)']"));
		aplicacaoAvaliado.click();
		//localiza e insere topico 1
		Thread.sleep(2000);
		
		editTopicoQuestionario(1, "Pelo neg�cio da empresa, pela proposta e vis�o, pelos benef�cios e "
				+ "pela possibilidade de crescimento.");
		
		editTopicoQuestionario(2, "Atrav�s dos anos pude concluir que fazer o que se gosta � o melhor"
				+ " para a empresa e para o profissional, entretanto, se leva um tempo para ter "
				+ "certeza do que se gosta. Testar, analisar o software necessita de curiosidade"
				+ " do analista, conhecimento das t�cnicas e vontade de encontrar falhas. "
				+ "Acredito que encontrar uma falha � t�o importante quanto desenvolver."
				+ " � meu perfil, ser parceiro do desenvolvedor a ponto de trabalharmos juntos"
				+ " pra que o software obtenha qualidade, por isso testar � t�o significativo. ");
		
		editTopicoQuestionario(3, "Naturalmente ficamos ansiosos diante de um problema, o que "
				+ "me faz crer que ao conhecermos a situa��o possibilita que busquemos uma "
				+ "maneira mais eficaz para chegar � solu��o. Inevitavelmente a experi�ncia "
				+ "diminui a ansiedade, entretanto, na �rea de qualidade � improv�vel que n�o"
				+ " se encontre problemas, na verdade, encaro problemas como uma oportunidade "
				+ "de melhorar, de crescer. Dependendo do tamanho do problema eu o divido, acho "
				+ "mais f�cil resolver por etapas, j� tentei resolver tudo de uma s� vez � isso"
				+ " pode piorar as coisas. Tranquilidade, honestidade consigo e com os outros, "
				+ "paci�ncia e determina��o s�o fatores chaves, penso eu, para resolver um problema.");
		
		
		WebElement salvar = driver.findElement(By.linkText("Salvar"));
		salvar.click();
	}
	
	private void editTopicoQuestionario(int i, String texto) throws InterruptedException {
		List<WebElement> topicos = driver.findElements(By.cssSelector("tr[ng-click='goTo(topico.id)']"));
		
		if (topicos.size() >= i) {
			WebElement topico = topicos.get(i - 1);
			topico.click();
			Thread.sleep(500);

			WebElement questao = driver.findElement(By.xpath("//textarea[@ng-disabled='questao.disabled']"));
			questao.clear();
			questao.sendKeys(texto);
			
		}
	}

	private void parteUM() throws InterruptedException{
		// interrogando os elementos no cadastro do novo avaliado
		// Clica nos elementos no cadastro do novo avaliado
		WebElement avaliados = driver.findElement(By.linkText("Avaliados"));
		avaliados.click();
		Thread.sleep(500);
		WebElement novoAvaliado = driver.findElement(By.linkText("Novo avaliado"));
		novoAvaliado.click();

		// cadastrando novo avaliado
		Thread.sleep(3000);
		WebElement nomeAvaliado = driver.findElement(By.name("avaliadoNome"));
		nomeAvaliado.sendKeys("Sandro Gon�alves");
		Thread.sleep(3000);
		WebElement salvarAvaliado = driver.findElement(By.linkText("Salvar"));
		salvarAvaliado.click();

		// cadastrando novo question�rio
		Thread.sleep(5000);
		WebElement questionarios = driver.findElement(By.linkText("Questionarios"));
		questionarios.click();
		Thread.sleep(500);
		WebElement novoQuestionario = driver.findElement(By.linkText("Novo question�rio"));
		novoQuestionario.click();

		// selecionando e indo para a janela de t�picos
		Thread.sleep(3000);
		Select selectAvaliado = new Select(driver.findElement(By.name("questionarioMode")));
		Thread.sleep(500);
		selectAvaliado.selectByIndex(1);
		Thread.sleep(500);
		WebElement proximo = driver.findElement(By.linkText("Pr�ximo"));
		proximo.click();

		// Adicionando e editando t�picos
		Thread.sleep(5000);
		
		editLastTopico("Por que voc� est� interessado em trabalhar para esta empresa?");
		
		WebElement adicionarTopico = driver.findElement(By.xpath("//button[@ng-click='addTopic()']"));
		adicionarTopico.click();
		editLastTopico("Por que voc� trabalha na �rea de qualidade de software? ");

		adicionarTopico.click();
		editLastTopico("Como voc� lida com problemas?");
		//salvando o question�rio
		WebElement SalvarQuestionario = driver.findElement(By.xpath("//button[@ng-click='save()']"));
		SalvarQuestionario.click();
	}

	//respondendo o question�rio
	private void editLastTopico(String texto) throws InterruptedException {
		List<WebElement> topicos = driver.findElements(By.cssSelector("div[ng-click='edit(topic.id)']"));
		if (topicos.size() > 0) {
			topicos.get(topicos.size() -1).click();
			Thread.sleep(3000);
			WebElement nomeTopico = driver.findElement(By.name("userTopicName"));
			Thread.sleep(500);
			nomeTopico.clear();
			nomeTopico.sendKeys(texto);
			Thread.sleep(5000);
			WebElement salvarTopico1 = driver.findElement(By.linkText("Salvar"));
			salvarTopico1.click();
			Thread.sleep(1000);
		}
	}
}
